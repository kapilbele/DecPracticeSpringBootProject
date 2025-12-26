package com.Preparation.SpringBootProject.controller;

import com.Preparation.SpringBootProject.entity.Student;
import com.Preparation.SpringBootProject.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Student")
public class StudentController {

    @Autowired
    protected StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<String> saveStudentDetails(@Valid @RequestBody Student student)
    {
        studentService.saveStudentDetails(student);
        return new ResponseEntity<>("Added new Student...", HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteByIdStudent(@PathVariable long id)
    {
        studentService.deleteByIdStudent(id);
        return new ResponseEntity<>("Student deleted successfully...!"+id,HttpStatus.OK);
    }

    @GetMapping("/getByStudentID/{id}")
    public ResponseEntity<Student> getByStudentID(@PathVariable long id)
    {
        return new ResponseEntity<Student>(studentService.getByStudentID(id),HttpStatus.OK);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    //api is using JPA queries
    @GetMapping("/getByStudentName")
    public ResponseEntity<List<Student>> getByStudentName(@RequestParam String name)
    {
        return new ResponseEntity<List<Student>>(studentService.getByStudentName(name),HttpStatus.OK);
    }

    @GetMapping("/getAllStudentsByCity")
    public ResponseEntity<List<Student>> getAllStudentsByCity(@RequestParam String city)
    {
        return new ResponseEntity<List<Student>>(studentService.getAllStudentsByCity(city),HttpStatus.OK);
    }

    @PatchMapping("/updateAddressType/{id}/address")
    public ResponseEntity<String> updateAddressTypeByStudIDAndCity(@PathVariable Long id, @RequestParam String city, @RequestParam String type)
    {
        studentService.updateAddressTypeByStudIDAndCity(type, id, city);
        return new ResponseEntity<String>("Address updated successfully...!",HttpStatus.OK);
    }

    @PatchMapping("/updateMobileNo/{id}")
    public ResponseEntity<String> updateMobileNo(@PathVariable Long id, @RequestParam String mobile)
    {
        studentService.updateMobileNo(id,mobile);
        return new ResponseEntity<String>("Mobile No updated successfully...!",HttpStatus.OK);
    }

}
