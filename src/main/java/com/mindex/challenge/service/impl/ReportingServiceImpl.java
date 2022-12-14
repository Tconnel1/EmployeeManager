package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public ReportingStructure getReportingStructureForId(String employeeId) {
        final Employee employee = employeeRepository.findByEmployeeId(employeeId);
        int totalReports = employee.getDirectReports().size();
        for (Employee subEmployee: employee.getDirectReports()) {
            totalReports = getReportingStructureForId(subEmployee, totalReports);
        }
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(totalReports);
        return reportingStructure;
    }

    private int getReportingStructureForId(Employee employee, int numberOfReports) {
        for (Employee subEmployee : employee.getDirectReports()) {
            numberOfReports += getReportingStructureForId(subEmployee, 1);
        }
        return numberOfReports;
    }
}
