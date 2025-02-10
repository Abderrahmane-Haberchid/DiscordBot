package config;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationPath("/rest")
public class JaxConfig extends ResourceConfig {

    public JaxConfig() {
        register(new Binder());
        packages("main.api", "main.services");
        register(new EntityManagerFactoryProvider());
        // Enable detailed logging
        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));

    }
}

