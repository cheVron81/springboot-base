package chevron81.barebone.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class HealthCheckController {

    @GetMapping("ping")
    @SuppressWarnings("unused")
    public ResponseEntity<String> checkPing() {
        return ResponseEntity.ok("pong");
    }

}
