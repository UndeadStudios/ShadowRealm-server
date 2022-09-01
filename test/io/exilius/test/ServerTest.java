package io.exilius.test;

import io.exilius.Server;
import io.exilius.ServerConfiguration;
import io.exilius.ServerState;

public class ServerTest {

    private final ServerConfiguration configuration;

    public ServerTest(ServerConfiguration configuration) {
        this.configuration = configuration;
        try {
            Server.startServerless(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerTest(ServerState serverState) {
        this(TestServerConfiguration.getConfiguration(serverState));
    }

    public ServerConfiguration getConfiguration() {
        return configuration;
    }
}
