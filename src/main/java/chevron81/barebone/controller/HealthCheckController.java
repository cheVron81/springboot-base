package chevron81.barebone.controller;

import chevron81.barebone.util.Health;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health")
@RestController
public class HealthCheckController {


    //private static final Logger LOGGER = LogManager.getLogger(HealthCheckController.class);

    @GetMapping("/ping")
    @SuppressWarnings("unused")
    public ResponseEntity<String> checkPing() {
        return ResponseEntity.ok("pong");
    }


    @GetMapping("/status")
    @SuppressWarnings("unused")
    public ResponseEntity<Health> getHealthCheck() {
        return ResponseEntity.ok(new Health());
    }


}
