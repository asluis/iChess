package OnlineConnection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class Network {
	private ConnectionThread connect = new ConnectionThread();
	private Consumer<Serializable> onRecieveCallback;
	
	public Network(Consumer<Serializable> onRecieveCallback) {
		this.onRecieveCallback = onRecieveCallback;
		
		connect.setDaemon(true);
	}
	
	public void startConnection() throws Exception { connect.start(); }
	public void closeConnection() throws Exception { connect.socket.close(); }
	
	public void sendData(Serializable data) throws Exception { connect.out.writeObject(data); }
	
	protected abstract boolean isServer();
	protected abstract String getIP();
	protected abstract int getPort();
	
	private class ConnectionThread extends Thread {
        private Socket socket;
        private ObjectOutputStream out;
        @Override
        public void run() {
            try (
                ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
            	
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
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
                onRecieveCallback.accept("Error connecting to server");
            }
            catch (Exception e) {
                onRecieveCallback.accept("Connection closed");
            }
        }
    }
}