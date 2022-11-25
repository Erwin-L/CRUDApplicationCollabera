package com.example.CRUDApplicationCollabera.controller;

import com.example.CRUDApplicationCollabera.model.Employee;
import com.example.CRUDApplicationCollabera.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        try{
            List<Employee> employeeList = new ArrayList<>();
            employeeRepo.findAll().forEach(employeeList::add);

            if(employeeList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Optional<Employee> employeeData = employeeRepo.findById(id);

        if(employeeData.isPresent()){
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee employeeObj = employeeRepo.save(employee);

        return new ResponseEntity<>(employeeObj, HttpStatus.OK);

    }

    @PostMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee newEmployeeData){
       Optional<Employee> oldEmployeeData = employeeRepo.findById(id);

        if(oldEmployeeData.isPresent()){
           Employee updatedEmployeeData =  oldEmployeeData.get();

           updatedEmployeeData.setName(newEmployeeData.getName());

           updatedEmployeeData.setPosition(newEmployeeData.getPosition());

           updatedEmployeeData.setAge(newEmployeeData.getAge());

           Employee employeeObj = employeeRepo.save(updatedEmployeeData);
           return new ResponseEntity<>(employeeObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id){
        employeeRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
