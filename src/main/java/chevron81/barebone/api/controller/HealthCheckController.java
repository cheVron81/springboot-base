package chevron81.barebone.api.controller;

import chevron81.barebone.api.UrlConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(UrlConstants.HEALTH_BASE_PATH)
@RestController
@SuppressWarnings("unused")
public class HealthCheckController {

    public static final String PING_ANSWER = "pong";

    @GetMapping(UrlConstants.HEALTH_PING_PATH)
    public ResponseEntity<String> getPing() {
        return ResponseEntity.ok(HealthCheckController.PING_ANSWER);
    }

}