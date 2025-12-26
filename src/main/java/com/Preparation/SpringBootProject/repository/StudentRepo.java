package com.Preparation.SpringBootProject.repository;

import com.Preparation.SpringBootProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long>{
    List<Student> findByName(String name);

    @Query("select distinct s from Student s JOIN s.addressess a where a.city=?1")
    List<Student> getAllStudentsByCity(String city);

    @Modifying
    @Query("""
            update Address a 
            set a.type=?1 
            where a.student.id=?2 
            and a.city=?3
            """)
    int updateAddressTypeByStudIDAndCity(String type, Long id, String city);

    @Modifying
    @Query("""
            update Student s
            set s.mobile=:mobile
            where s.id=:id
            """)
    int updateMobileNo(@Param("id") Long id, @Param("mobile") String mobile);
}
