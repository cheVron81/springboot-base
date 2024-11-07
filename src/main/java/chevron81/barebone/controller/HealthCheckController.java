package chevron81.barebone.controller;

import chevron81.barebone.util.Health;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("${health.base.path}")
@RestController
public class HealthCheckController {

    public static final String PING_ANSWER = "pong";

    //private static final Logger LOGGER = LogManager.getLogger(HealthCheckController.class);

    @GetMapping("${health.ping.path}")
    @SuppressWarnings("unused")
    public ResponseEntity<String> checkPing() {
        return ResponseEntity.ok(PING_ANSWER);
    }


    @GetMapping("${health.status.path}")
    @SuppressWarnings("unused")
    public ResponseEntity<Health> getHealthCheck() {
        return ResponseEntity.ok(new Health());
    }


}
