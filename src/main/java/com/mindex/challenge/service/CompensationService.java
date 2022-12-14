package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

import java.util.List;

public interface CompensationService {
    Compensation create(String employeeId);
    Compensation read(String id);
    List<Compensation> readAll();
}
