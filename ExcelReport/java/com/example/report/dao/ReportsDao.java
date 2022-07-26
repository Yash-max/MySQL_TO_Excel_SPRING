package com.example.report.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.report.model.Reports;

public interface ReportsDao extends JpaRepository<Reports, Long> {

}
