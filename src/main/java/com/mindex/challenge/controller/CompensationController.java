package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/compensation")
    public Compensation create(@RequestBody String employeeId) {
        LOG.debug("Received compensation create request for employee: [{}]", employeeId);

        return compensationService.create(employeeId);
    }

    @GetMapping("/compensation/{employeeId}")
    public Compensation read(@PathVariable String employeeId) {
        LOG.debug("Received compensation read request for employeeId: [{}]", employeeId);

        return compensationService.read(employeeId);
    }

    @GetMapping("/compensation/read-all")
    public List<Compensation> readAll() {
        LOG.debug("Received compensation read request");

        return compensationService.readAll();
    }

}
