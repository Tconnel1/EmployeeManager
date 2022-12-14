package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.impl.ReportingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private ReportingServiceImpl reportingServiceImpl;

    @GetMapping("/employee/reporting-structure/{employeeId}")
    public ReportingStructure getReportingStructureForEmployee(@PathVariable String employeeId) {
        LOG.debug("Retrieving reporting structure for employeeId: [{}]", employeeId);

        return reportingServiceImpl.getReportingStructureForId(employeeId);
    }
}
