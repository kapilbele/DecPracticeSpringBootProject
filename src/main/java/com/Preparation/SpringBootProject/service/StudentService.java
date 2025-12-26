package com.Preparation.SpringBootProject.service;

import com.Preparation.SpringBootProject.entity.Student;
import com.Preparation.SpringBootProject.exception.ResourceNotFoundException;
import com.Preparation.SpringBootProject.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    protected StudentRepo studentRepo;

    public Student saveStudentDetails(Student student) {

        // set parent reference in child
        if (student.getAddressess() != null) {
            student.getAddressess()
                    .forEach(address -> address.setStudent(student));
        }
        return studentRepo.save(student);
    }
    public void deleteByIdStudent(long id)
    {
       if (!studentRepo.existsById(id))
       {
           throw new ResourceNotFoundException("Resource not found exception for ID: "+id);
       }
       else
       {
           studentRepo.deleteById(id);
       }
    }

    public Student getByStudentID(long id) throws ResourceNotFoundException
    {
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + id
                ));
    }

    public List<Student> getAllStudents()
    {
        return studentRepo.findAll();
    }

    public List<Student> getByStudentName(String name)
    {
       return studentRepo.findByName(name);
    }

    public List<Student> getAllStudentsByCity(String city)
    {
        return studentRepo.getAllStudentsByCity(city);
    }

    @Transactional
    public int updateAddressTypeByStudIDAndCity(String type, Long id, String city)
    {
       int result= studentRepo.updateAddressTypeByStudIDAndCity(type, id, city);
       if (result>0)
       {
           return result;
       }
       else {
           throw new ResourceNotFoundException("Resource not found with id: "+id);
       }
    }

    @Transactional
    public int updateMobileNo(Long id, String mobile)
    {
        int result= studentRepo.updateMobileNo(id, mobile);
        if (result>0)
        {
            return result;
        }
        else {
            throw new ResourceNotFoundException("Resource not found with id: "+id);
        }
    }
}
