package com.mindex.challenge.data;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class Compensation {
    private Employee employee;
    private double salary;
    private Instant effectiveDate;

    public Compensation() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
