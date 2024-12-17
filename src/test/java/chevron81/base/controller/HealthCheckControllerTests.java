package chevron81.base.controller;

import chevron81.base.api.UrlConstants;
import chevron81.base.api.controller.HealthCheckController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthCheckControllerTests {


    final String HTTP_LOCALHOST = "http://localhost:";

    @LocalServerPort
    @SuppressWarnings("unused")
    private int port;

    @Autowired
    @SuppressWarnings("unused")
    private TestRestTemplate restTemplate;

    @Test
    void checkPing() {
        final String url = this.HTTP_LOCALHOST + this.port + UrlConstants.HEALTH_BASE_PATH + UrlConstants.HEALTH_PING_PART;
        final ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getBody()).isEqualTo(HealthCheckController.PING_ANSWER);
    }

}