package com.example.springd3.controll;

import com.example.springd3.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeControl {
    ArrayList<Employee> employees=new ArrayList();

    @GetMapping
    public ResponseEntity getAllEmployee(){
        return ResponseEntity.status(200).body(employees);
    }

    @PostMapping
    public ResponseEntity postEmployee(@RequestBody @Valid Employee employee, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        employees.add(employee);
        return ResponseEntity.status(201).body("employee added");
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid Employee employee , @PathVariable String id , Errors errors){
        Integer index=getIndex(id);
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        employees.set(index,employee);
        return ResponseEntity.status(200).body(employees.get(index).getName()+" has updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id){
        Integer index=getIndex(id);
        if(index==null){
            return ResponseEntity.status(400).body("there is no employee");
        }
        employees.remove((int)index);
        return ResponseEntity.status(200).body("employee deleted");
    }

    @PutMapping("apply/{id}")
    public ResponseEntity applyAnnual(@PathVariable String id){
        Integer index=getIndex(id);
        if (employees.get(index).getAnnualLeave()==0){
            return ResponseEntity.status(400).body("You don't have enough days");
        }else if (employees.get(index).isOnLeave()==false){
            employees.get(index).setOnLeave(true);
            employees.get(index).setAnnualLeave((employees.get(index).getAnnualLeave()-1));
            return ResponseEntity.status(200).body("Employee  applied to annual leave");
        } else{
            return ResponseEntity.status(400).body("employee already apply annual leave");
        }
    }

    public Integer getIndex(String id){
        for (int i = 0; i < this.employees.size(); i++) {
            if (employees.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }
}
