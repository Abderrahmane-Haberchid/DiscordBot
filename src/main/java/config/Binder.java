package config;

import services.UserService;
import services.impl.UserServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class Binder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(UserServiceImpl.class).to(UserService.class);  // Bind the implementation
    }
}

