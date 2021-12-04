package OnlineConnection;

import java.io.Serializable;
import java.util.function.Consumer;

public class Server extends Network {

    private int port;
    private String ip;

    public Server(String ip, int port, Consumer<Serializable> onRecieveCallback) {
        super(onRecieveCallback);
        this.port = port;
        this.ip = ip;
    }

    @Override
    protected boolean isServer() { return true; }

    @Override
    protected String getIP() { ip.equals("") ? null : ip }

    @Override
    protected int getPort() { return port; }
}