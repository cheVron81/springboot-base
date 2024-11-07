package chevron81.barebone.controller;

import chevron81.barebone.util.Health;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthCheckControllerTests {


    final String HTTP_LOCALHOST = "http://localhost:";

    @Value("${health.ping.path}")
    @SuppressWarnings("unused")
    private String pingPath;

    @Value("${health.status.path}")
    @SuppressWarnings("unused")
    private String statusPath;

    @Value("${health.base.path}")
    @SuppressWarnings("unused")
    private String basePath;

    @LocalServerPort
    @SuppressWarnings("unused")
    private int port;

    @Autowired
    @SuppressWarnings("unused")
    private TestRestTemplate restTemplate;

    @Test
    void checkPing() {
        final String url = this.HTTP_LOCALHOST + this.port + this.basePath + this.pingPath;
        final ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getBody()).isEqualTo(HealthCheckController.PING_ANSWER);
    }

    @Test
    void checkStatus() {
        final String url = this.HTTP_LOCALHOST + this.port + this.basePath + this.statusPath;
        final ResponseEntity<Health> response = this.restTemplate.getForEntity(url, Health.class);
        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(Health.RUNNING);
    }
}