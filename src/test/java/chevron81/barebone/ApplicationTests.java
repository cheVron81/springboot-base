package chevron81.barebone;

import chevron81.barebone.util.Health;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @LocalServerPort
    @SuppressWarnings("unused")
    private int port;

    @Autowired
    @SuppressWarnings("unused")
    private TestRestTemplate restTemplate;

    @Test
    void checkPing() {
        final String url = "http://localhost:" + this.port + "/health/ping";
        final ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("pong");
    }

    @Test
    void checkStatus() {
        final String url = "http://localhost:" + this.port + "/health/status";
        final ResponseEntity<Health> response = this.restTemplate.getForEntity(url, Health.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(Health.RUNNING);
    }

    @Test
    void contextLoads() {
    }

}
