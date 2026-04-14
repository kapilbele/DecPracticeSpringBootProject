package com.Preparation.SpringBootProject.controller;

import com.Preparation.SpringBootProject.entity.Address;
import com.Preparation.SpringBootProject.entity.Student;
import com.Preparation.SpringBootProject.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

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

    @Test
    void testSaveStudentDetails() {
        Student student = createStudent();
        when(studentService.saveStudentDetails(any(Student.class))).thenReturn(student);
        ResponseEntity<String> response = studentController.saveStudentDetails(student);
        verify(studentService).saveStudentDetails(student);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Added new Student...", response.getBody());
    }

    @Test
    void testDeleteByIdStudent() {
        long studentId = 1L;
        doNothing().when(studentService).deleteByIdStudent(studentId);
        ResponseEntity<String> response = studentController.deleteByIdStudent(studentId);
        verify(studentService, times(1)).deleteByIdStudent(studentId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Student deleted successfully...!" + studentId, response.getBody());
    }

    @Test
    void testGetByStudentID() {
        long studentId = 1L;
        Student student = createStudent();
        when(studentService.getByStudentID(studentId)).thenReturn(student);
        ResponseEntity<Student> response =
                studentController.getByStudentID(studentId);
        verify(studentService, times(1)).getByStudentID(studentId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    void getAllStudents()
    {
        Student student=createStudent();
        List<Student> sList=new ArrayList<>();
        sList.add(student);
        when(studentService.getAllStudents()).thenReturn(sList);
        List<Student> studentList=studentController.getAllStudents();
        verify(studentService, times(1)).getAllStudents();
        assertEquals(1, studentList.size());
        assertEquals(student, studentList.get(0));
    }

    @Test
    void getByStudentName()
    {
        String name = "John Doe";
        Student student = createStudent();
        List<Student> sList = new ArrayList<>();
        sList.add(student);
        when(studentService.getByStudentName(name)).thenReturn(sList);
        ResponseEntity<List<Student>> response= studentController.getByStudentName(name);
        verify(studentService,times(1)).getByStudentName(name);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sList, response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(student, response.getBody().get(0));
    }

    @Test
    void getAllStudentsByCity()
    {
        String city = "Mumbai";
        Student student = createStudent();
        List<Student> sList = new ArrayList<>();
        sList.add(student);
        when(studentService.getAllStudentsByCity(city)).thenReturn(sList);
        ResponseEntity<List<Student>> response= studentController.getAllStudentsByCity(city);
        verify(studentService,times(1)).getAllStudentsByCity(city);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sList, response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(student, response.getBody().get(0));
    }

    @Test
    void updateAddressTypeByStudIDAndCity() {
        String city = "Mumbai";
        long studentId = 1L;
        String type = "CURRENT";
        when(studentService.updateAddressTypeByStudIDAndCity(type, studentId, city)).thenReturn(1);
        ResponseEntity<String> response = studentController.updateAddressTypeByStudIDAndCity(studentId,city,type);
        verify(studentService,times(1)).updateAddressTypeByStudIDAndCity(type, studentId, city);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Address updated successfully...!", response.getBody());
    }

    @Test
    void updateMobileNo() {
        long studentId = 1L;
        String mobile = "9876543210";
        when(studentService.updateMobileNo(studentId, mobile)).thenReturn(1);
        ResponseEntity<String> response = studentController.updateMobileNo(studentId, mobile);
        verify(studentService,times(1)).updateMobileNo(studentId, mobile);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Mobile No updated successfully...!", response.getBody());
    }
}


