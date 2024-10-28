package com.example.PaginaWebDivisas.services;
import com.example.PaginaWebDivisas.models.Logs;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface LogsService {
    Page<Logs> getAllLogs(int page, int size);
    Logs getLogById(Long id);
    Logs saveLog(Logs logs);
    Logs patchLog(Long id, Map<String, Object> updates);
    void deleteLog(Long id);
}
