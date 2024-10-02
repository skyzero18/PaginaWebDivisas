package com.example.PaginaWebDivisas.services;
import com.example.PaginaWebDivisas.models.Logs;
import com.example.PaginaWebDivisas.repository.LogsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LogsServiceImpl implements LogsService {
    @Autowired
    private LogsRepo logsRepo;

    @Override
    public List<Logs> getAllLogs() {return logsRepo.findAll();}

    @Override
    public Logs getLogById(Long id) {
        Optional<Logs> logs = logsRepo.findById(id);
        if (logs.isPresent()) {
            return logs.get();
        }
        throw new RuntimeException("no se encontraron divisas con id " + id);

    }

    @Override
    public Logs saveLog(Logs logs) {
        return logsRepo.save(logs);
    }

    @Override
    public Logs patchLog(Long id, Map<String, Object> updates) {
        Logs existingLog = getLogById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre":
                    existingLog.setNombre((String) value);
                    break;
            }
        });

        return logsRepo.save(existingLog);
    }


    @Override
    public void deleteLog(Long id) {
        logsRepo.deleteById(id);
    }
}
