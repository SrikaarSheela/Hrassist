package com.siri.hrassist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siri.hrassist.exception.ResourceNotFoundException;
import com.siri.hrassist.model.Employee;
import com.siri.hrassist.repository.HRRepository;

@RestController
@RequestMapping("/siri/api/")
public class EmployeeController 
{
	@Autowired
	HRRepository hr;
	
	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee x)
    {

       return hr.save(x);
    }
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
        return hr.findAll();
    }
	
	// get employee by id rest api
	@GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) 
	{
        Employee x = hr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(x);
    }
	
	// update employee rest api
	@PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee y)
	{
        Employee x = hr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        
        x.setFirstName(y.getFirstName());
        x.setLastName(y.getLastName());
        x.setEmailId(y.getEmailId());

        Employee z = hr.save(x);
        return ResponseEntity.ok(z);
	}
	
	// delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Integer id)
    {
        Employee e = hr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        hr.delete(e);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}

