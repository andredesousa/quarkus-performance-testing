package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("AppService")
@ExtendWith(MockitoExtension.class)
public class AppServiceTest {

    @InjectMocks
    transient AppService service;

    @Test
    @DisplayName("#hello returns 'Hello World!' message")
    void helloMethod() {
        assertEquals("Hello World!", service.hello());
    }
}
