/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package touched.agi.java.app;


import touched.agi.java.app.server.Server;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        new Server(5000).Startup();
    }
}
