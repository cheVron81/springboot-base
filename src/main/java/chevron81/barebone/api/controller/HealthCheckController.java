package chevron81.barebone.api.controller;

import chevron81.barebone.api.UrlConstants;
import chevron81.barebone.util.Health;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(UrlConstants.HEALTH_BASE_PATH)
@RestController
public class HealthCheckController {

    public static final String PING_ANSWER = "pong";

    @GetMapping(UrlConstants.HEALTH_PING_PATH)
    @SuppressWarnings("unused")
    public ResponseEntity<String> getPing() {
        return ResponseEntity.ok(HealthCheckController.PING_ANSWER);
    }

    @GetMapping(UrlConstants.HEALTH_STATUS_PATH)
    @SuppressWarnings("unused")
    public ResponseEntity<Health> getStatus() {
        return ResponseEntity.ok(new Health());
    }
}