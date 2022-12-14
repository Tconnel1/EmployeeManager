package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private String employeeId;
    private String salary;
    private Date effectiveDate;

    public Compensation() {

    }

    public void setEmployee(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployee() {
        return employeeId;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }
}
