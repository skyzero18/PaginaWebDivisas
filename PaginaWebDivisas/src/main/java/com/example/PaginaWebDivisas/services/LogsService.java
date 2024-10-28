package com.example.PaginaWebDivisas.services;
import com.example.PaginaWebDivisas.models.Logs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface LogsService {
    List<Logs> getAllLogs();
    Page<Logs> getLogs(Pageable pageable);
    Logs getLogById(Long id);
    Logs saveLog(Logs logs);
    Logs patchLog(Long id, Map<String, Object> updates);
    void deleteLog(Long id);
}
