package com.siri.hrassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siri.hrassist.model.Employee;

public interface HRRepository extends JpaRepository<Employee, Integer>

{

}
