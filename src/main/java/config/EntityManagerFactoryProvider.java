package config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.ext.Provider;
import org.glassfish.hk2.api.Factory;

@Provider
public class EntityManagerFactoryProvider implements Factory<EntityManagerFactory> {

    @Override
    public EntityManagerFactory provide() {
        // Create and return the EntityManagerFactory
        return Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    @Override
    public void dispose(EntityManagerFactory entityManagerFactory) {
        // Clean up resources
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}

