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
	
	/**
	 * Starts a connection online.
	 */
	public void startConnection() throws Exception { connect.start(); }
	
	/**
	 * Closes connection.
	 */
	public void closeConnection() throws Exception { connect.socket.close(); }
	
	/**
	 * Sends any serializable data over network, we serialize MoveData and send that to handle the online chess game.
	 * @param Serializable Data
	 */
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
            	// If it is a server create a new ServerSocket with the port else else if not a server set to null.
                ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
            	// If socket is a server accept connection and create a new socket with ip adress and port.
                Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
            	
            	//Output stream to send objects.
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            	//Input stream to receive objects.
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ) {
                onRecieveCallback.accept("Connection successful");
                this.socket = socket;
                this.out = out;
                //Disables buffering and delay.	
                socket.setTcpNoDelay(true);

                while (true) {
                	// Receive serializable data.
                    Serializable data = (Serializable) in.readObject();
                    onRecieveCallback.accept(data);
                }
            }
            catch (ConnectException e) {
                onRecieveCallback.accept("Error connecting to server");
            }
            catch (Exception d) {
                onRecieveCallback.accept("Connection closed");
            }
        }
    }
}
