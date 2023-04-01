package app;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppService {

    public String hello() {
        return "Hello World!";
    }
}
