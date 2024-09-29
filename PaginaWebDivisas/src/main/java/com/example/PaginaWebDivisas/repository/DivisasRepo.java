package com.example.PaginaWebDivisas.repository;
import com.example.PaginaWebDivisas.models.Divisas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisasRepo extends JpaRepository<Divisas, Long> {
}
