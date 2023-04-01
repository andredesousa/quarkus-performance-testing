package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("AppController")
@ExtendWith(MockitoExtension.class)
public class AppControllerTest {

    @Mock
    transient AppService appService;

    @InjectMocks
    transient AppController appController;

    @Test
    @DisplayName("#hello returns 'Hello!' message")
    void helloMethod() {
        when(appService.hello()).thenReturn("Hello!");

        assertEquals("Hello!", appController.hello());
    }
}
