package com.codility.tasks.spring.healthcheck;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/healthcheck")
public class HealthcheckController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> healthcheck(@RequestParam(value = "format", required = false) String format) {
        if (format == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse("Missing 'format' parameter"));
        }

        Map<String, Object> response = new HashMap<>();
        switch (format) {
            case "short" -> {
                response.put("status", "OK");
                return ResponseEntity.ok(response);
            }
            case "full" -> {
                response.put("status", "OK");
                response.put("currentTime", ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
                return ResponseEntity.ok(response);
            }
            default -> {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(errorResponse("Invalid 'format' parameter: " + format));
            }
        }
    }

    private Map<String, String> errorResponse(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        return error;
    }
}
