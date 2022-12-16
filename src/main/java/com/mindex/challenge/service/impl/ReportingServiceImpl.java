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

    /**
     * Returns the Reporting Structure for a specified employee
     * @param employeeId - the employee who reporting structure is being retrieved
     * @return the reporting structure for the employee
     */
    public ReportingStructure getReportingStructureForId(String employeeId) {
        LOG.debug("Fetching Reporting Structure for employee with id [{}]", employeeId);
        final Employee employee = employeeRepository.findByEmployeeId(employeeId);
        int totalReports = employee.getDirectReports().size();

        // for each direct report, we need to fetch their direct reports to get the full list of employee reporting to the desired employee
        if(employee.getDirectReports() != null) {
            for (Employee subEmployee : employee.getDirectReports()) {
                subEmployee = employeeRepository.findByEmployeeId(subEmployee.getEmployeeId());
                totalReports = getNumberOfEmployeesReportingToDirectReport(subEmployee, totalReports);
            }
        }

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(totalReports);
        return reportingStructure;
    }

    /**
     * Retrieves a total number of employees reporting to the specified employee
     * @param employee - employee being checked for direct reports
     * @param numberOfReports - current number of direct report being added to.
     * @return an integer equal to the total number of employees directly reporting to employee
     */
    private int getNumberOfEmployeesReportingToDirectReport(Employee employee, int numberOfReports) {
        // iterate through each direct report of the specified employee and add the amount to the total.
        // then checks that direct report for their direct reports.
        if(employee.getDirectReports() != null) {
            for (Employee subEmployee : employee.getDirectReports()) {
                numberOfReports += 1;
                subEmployee = employeeRepository.findByEmployeeId(subEmployee.getEmployeeId());
                getNumberOfEmployeesReportingToDirectReport(subEmployee, numberOfReports);
            }
        }
        return numberOfReports;
    }
}
