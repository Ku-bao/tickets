package com.example.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tickets.model.Log;

public interface LogRepository extends JpaRepository<Log, Long> {
}
