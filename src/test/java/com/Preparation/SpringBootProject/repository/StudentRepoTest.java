package com.Preparation.SpringBootProject.repository;

import com.Preparation.SpringBootProject.entity.Address;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import com.Preparation.SpringBootProject.entity.Student;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class StudentRepoTest {

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void updateAddressTypeByStudIDAndCity_shouldUpdateType() {
        Student student = new Student();
        student.setName("John");
        student.setDept("CSE");
        student.setDob(LocalDate.of(2000, 5, 15));
        student.setMobile("9876543210");
        student.setEmail("john.doe@example.com");
        student = studentRepo.save(student);

        Address address = new Address();
        address.setCity("Delhi");
        address.setType("Home");
        address.setState("MH");
        address.setPincode("411001");
        address.setStudent(student);
        addressRepo.save(address);

        int updatedCount = studentRepo
                .updateAddressTypeByStudIDAndCity(
                        "OFFICE",
                        student.getId(),
                        "Delhi"
                );

        entityManager.flush();
        entityManager.clear();
        assertEquals(1, updatedCount);
        Address updatedAddress =
                addressRepo.findById(address.getId()).orElseThrow();

        assertEquals("OFFICE", updatedAddress.getType());
    }
}
