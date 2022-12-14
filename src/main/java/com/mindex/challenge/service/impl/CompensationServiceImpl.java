package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
    @Autowired
    private CompensationRepository compensationRepository;

    public Compensation create(String employeeId) {
        LOG.debug("Creating compensation for employee [{}]", employeeId);

        Compensation compensation = new Compensation();
        compensation.setEmployee(employeeId);
        compensationRepository.insert(compensation);

        return compensation;
    }
    public  Compensation read(String employeeId) {
        LOG.debug("Creating employee with id [{}]", employeeId);

        Compensation compensation = compensationRepository.findByEmployeeId(employeeId);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        return compensation;
    }

    public List<Compensation> readAll() {
        List<Compensation> compensation = compensationRepository.findAll();

        if (compensation == null) {
            throw new RuntimeException("empty: ");
        }

        return compensation;
    }
}
