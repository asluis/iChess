package OnlineConnection;

import java.io.Serializable;
import java.util.function.Consumer;

public class Client extends Network {
	private String ip;
	private int port;
	
    public Client(String ip, int port, Consumer<Serializable> onRecieveCallback) {
        super(onRecieveCallback);
        this.ip = ip;
        this.port = port;
    }
	
	public String getIP() { return ip; }
	public int getPort() { return port; }
	
	public void setIp(String ip) { this.ip = ip; }
	public void setPort(int port) { this.port = port; }
	
	protected boolean isServer() { return false; }
	
}
