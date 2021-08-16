package touched.agi.java.app.server;

import org.asteriskjava.fastagi.AgiServerThread;
import org.asteriskjava.fastagi.DefaultAgiServer;

public class Server {
    /**
     * Variables
     */
    int Port;
    DefaultAgiServer agiServer;
    AgiServerThread agiThread;

    /**
     *
     * @param port -> Define a port for the FastAgi to run on. [Default Port 4375]
     */
    public Server(int port) {
        Port = port;
    }

    /**
     * Startup the FastAgi server and initialize an Agi thread.
     */
    public void Startup() {
        try {
            this.agiServer = new DefaultAgiServer();
            this.agiServer.setPort(Port);
            this.agiThread = new AgiServerThread(agiServer);
            this.agiThread.setDaemon(false);
            this.agiThread.startup();

        } catch (Exception e) {
            System.err.printf("System failed to start due to error: %s", e);
            this.Shutdown();
        }
    }

    /**
     * Shutdown the active FastAgi Server and Agi Thread
     */
    public void Shutdown() {
        try {
            if (this.agiServer != null) {
                if (this.agiThread != null) {
                    this.agiThread.shutdown();
                    this.agiThread = null;
                    System.out.println("FasAGI Thread Shutdown.");
                }
                this.agiServer.shutdown();
                this.agiServer = null;
                System.out.println("FasAGI Server Shutdown.");
            }
        } catch (Exception e) {
            System.err.printf("FasAGI failed to shutdown due to error: %s", e);
        }
    }
}
