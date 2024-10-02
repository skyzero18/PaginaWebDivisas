package com.example.PaginaWebDivisas.controllers;
import com.example.PaginaWebDivisas.models.Logs;
import com.example.PaginaWebDivisas.services.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LogsController {

    @Autowired
    private LogsService logsService;

    @GetMapping
    public List<Logs> getAllLogs() {
        return logsService.getAllLogs();
    }

    @GetMapping("/{id}")
    public Logs getLogById(@PathVariable Long id) {
        return logsService.getLogById(id);
    }

    @PostMapping
    public Logs createDivisa(@RequestBody Logs logs) {
        return logsService.saveLog(logs);
    }

    @PatchMapping("/{id}")
    public Logs patchLogs(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return logsService.patchLog(id, updates);
    }

    @DeleteMapping("/{id}")
    public void deleteLog(@PathVariable Long id) {
        logsService.deleteLog(id);
    }
}
