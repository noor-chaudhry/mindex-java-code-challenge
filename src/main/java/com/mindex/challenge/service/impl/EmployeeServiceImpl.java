package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Reading employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    @Override
    public ReportingStructure reports(String id) {
        LOG.debug("Creating reporting structure for employee with id [{}]", id);

        Employee employee = read(id);
        int numberOfReports =  getNumberofReports(id);

        return new ReportingStructure(employee, numberOfReports);
    }

    public int getNumberofReports(String employeeId) {
        int total = 0;

        Employee employee = read(employeeId);
        if(employee == null){
            throw new RuntimeException("Employee not found");
        }

        List<Employee> reports = employee.getDirectReports();
        if(reports != null){
            for(Employee e : reports){
                total += 1 + getNumberofReports(e.getEmployeeId());
            }
        }

        return total;
    }

}
