package com.Preparation.SpringBootProject.service;

import com.Preparation.SpringBootProject.entity.Address;
import com.Preparation.SpringBootProject.entity.Student;
import com.Preparation.SpringBootProject.repository.StudentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    StudentRepo studentRepo;

    @InjectMocks
    StudentService studentService;

    private Student createStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setDept("CSE");
        student.setDob(LocalDate.of(2000, 5, 15));
        student.setMobile("9876543210");
        student.setEmail("john.doe@example.com");

        List<Address> addresses = createAddresses(student);
        student.setAddressess(addresses);

        return student;
    }
    private List<Address> createAddresses(Student student) {
        Address addr1 = new Address();
        addr1.setType("PERMANENT");
        addr1.setCity("Mumbai");
        addr1.setState("MH");
        addr1.setPincode("400001");
        addr1.setStudent(student);

        Address addr2 = new Address();
        addr2.setType("CURRENT");
        addr2.setCity("Pune");
        addr2.setState("MH");
        addr2.setPincode("411001");
        addr2.setStudent(student);

        List<Address> addresses = new ArrayList<>();
        addresses.add(addr1);
        addresses.add(addr2);
        return addresses;
    }

    //with Address
    @Test
    void saveStudentDetails_shouldSetStudentInAddresses_andSaveStudent() {
        Student student = createStudent();
        List<Address> addresses = createAddresses(student);
        when(studentRepo.save(student)).thenReturn(student);
        Student savedStd = studentService.saveStudentDetails(student);
        assertNotNull(savedStd);
        assertEquals(student, savedStd);
        for (Address address: addresses)
        {
            assertEquals(student,address.getStudent());
        }
        verify(studentRepo,times(1)).save(student);
    }

    //with null Address
    @Test
    void saveStudentDetails_shouldSaveStudent_whenAddressesAreNull() {
        Student student = createStudent();
        student.setAddressess(null);
        when(studentRepo.save(student)).thenReturn(student);
        Student savedStd = studentService.saveStudentDetails(student);
        assertNotNull(savedStd);
        verify(studentRepo, times(1)).save(student);
    }
}
