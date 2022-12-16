package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
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

    private final Employee testEmployee = new Employee();
    private final Employee testEmployee1 = new Employee();
    private final Employee testEmployee2 = new Employee();
    private final Employee testEmployee3 = new Employee();
    private final Employee testEmployee4 = new Employee();
    @Before
    public void setup() {
        testEmployee.setEmployeeId("John");
        testEmployee.setDirectReports(createReportTree());

        when(employeeRepository.findByEmployeeId("John")).thenReturn(testEmployee);
        when(employeeRepository.findByEmployeeId("Ted")).thenReturn(testEmployee1);
        when(employeeRepository.findByEmployeeId("Bill")).thenReturn(testEmployee2);
        when(employeeRepository.findByEmployeeId("Geoff")).thenReturn(testEmployee3);
        when(employeeRepository.findByEmployeeId("Aaron with an a")).thenReturn(testEmployee4);
    }

    @Test
    public void getReportingStructureForId_onlyDirectReports_returnReportingStructure() {
        ReportingStructure result = reportingServiceImpl.getReportingStructureForId("John");
        assertEquals(4, result.getNumberOfReports());
    }

    private List<Employee> createReportTree() {
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
