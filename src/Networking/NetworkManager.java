package Networking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkManager {
    private ConnectionThread  connectionThread = new ConnectionThread();

    private Consumer<Serializable> onRecieveCallback;

    public NetworkManager(Consumer<Serializable> onRecieveCallback) {
        this.onRecieveCallback = onRecieveCallback;
        connectionThread.setDaemon(true);
    }

    public void startConnection() throws Exception {
        connectionThread.start();
    }

    public void send(Serializable data) throws  Exception {
        connectionThread.out.writeObject(data);
    }

    public void closeConnection() throws Exception {
        connectionThread.socket.close();
    }

    protected abstract boolean isServer();
    protected abstract String getIP();
    protected abstract int getPort();

    private class ConnectionThread extends Thread {
        private Socket socket;
        private ObjectOutputStream out;

        @Override
        public void run() {
            try (
                    // if server, create new ServerSocket server;
                    // if client, ServerSocket server = null;
                    ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                    // If server, wait for incoming connections
                    // If client, make socket for outgoing data
                    Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
                    // create output stream
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    // create input stream
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ) {
                onRecieveCallback.accept("Connection Established");
                this.socket = socket;
                this.out = out;

                // Disable message buffering
                socket.setTcpNoDelay(true);

                while (true) {
                    Serializable data = (Serializable) in.readObject();
                    onRecieveCallback.accept(data);
                }
            }
            catch (ConnectException e) {
                onRecieveCallback.accept("Could not connect to server");
            }
            catch (Exception e) {
                onRecieveCallback.accept("Connection closed");
            }
        }
    }
}
