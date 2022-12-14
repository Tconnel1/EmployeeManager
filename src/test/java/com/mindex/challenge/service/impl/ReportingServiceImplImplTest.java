package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportingServiceImplImplTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    ReportingServiceImpl reportingServiceImpl;

    @Test
    public void getReportingStructureForId_onlyDirectReports_returnReportingStructure() {
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("John");
        testEmployee.setDirectReports(createReportTree());

        when(employeeRepository.findByEmployeeId(anyString())).thenReturn(testEmployee);

        ReportingStructure result = reportingServiceImpl.getReportingStructureForId("John");
        assertEquals(4, result.getNumberOfReports());
    }

    @Test
    public void getReportingStructureForId_directAndSubReports_returnReportingStructure() {
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("John");
        testEmployee.setDirectReports(createReportTree());
        testEmployee.getDirectReports().forEach(employee -> employee.setDirectReports(createReportTree()));

        when(employeeRepository.findByEmployeeId(anyString())).thenReturn(testEmployee);

        ReportingStructure result = reportingServiceImpl.getReportingStructureForId("John");
        assertEquals(20, result.getNumberOfReports());
    }

    private List<Employee> createReportTree() {
        Employee testEmployee1 = new Employee();
        Employee testEmployee2 = new Employee();
        Employee testEmployee3 = new Employee();
        Employee testEmployee4 = new Employee();

        testEmployee1.setEmployeeId("Ted");
        testEmployee1.setDirectReports(Collections.emptyList());
        testEmployee2.setEmployeeId("Bill");
        testEmployee2.setDirectReports(Collections.emptyList());
        testEmployee3.setEmployeeId("Geoff");
        testEmployee3.setDirectReports(Collections.emptyList());
        testEmployee4.setEmployeeId("Aaron with an a");
        testEmployee4.setDirectReports(Collections.emptyList());

        List<Employee> employees = new ArrayList<>();
        employees.add(testEmployee1);
        employees.add(testEmployee2);
        employees.add(testEmployee3);
        employees.add(testEmployee4);
        return employees;
    }
}
