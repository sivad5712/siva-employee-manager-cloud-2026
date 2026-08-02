package com.siva.employeemanager.controller;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DatabaseController {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/database")
    public Map<String, String> checkDatabase() {
        String databaseName =
                jdbcTemplate.queryForObject("SELECT DATABASE()", String.class);

        String mysqlVersion =
                jdbcTemplate.queryForObject("SELECT VERSION()", String.class);

        return Map.of(
                "status", "CONNECTED",
                "database", databaseName,
                "mysqlVersion", mysqlVersion
        );
    }
}
