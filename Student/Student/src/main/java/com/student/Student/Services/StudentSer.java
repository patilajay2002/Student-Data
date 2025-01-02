package com.student.Student.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.student.Student.Entity.StudentEntity;
import com.student.Student.Repository.StudentRepo;

@Component
public class StudentSer {
    @Autowired
    private StudentRepo studentRepo;

    public List<StudentEntity> getAllStudent(){
        return studentRepo.findAll();
    }

    public StudentEntity postStudnetEntry(StudentEntity studentEntity){
        StudentEntity se=studentRepo.save(studentEntity);
        return se;
    }

    public Optional<StudentEntity> getByMobileNo(String mobile){
        StudentEntity se=studentRepo.findByMobile(mobile);
        return Optional.of(se);
    }


    public boolean deleteByMobile(String mobile){
            Optional<StudentEntity> se=getByMobileNo(mobile);
            if(se.isPresent()){
                studentRepo.delete(se.get());
                return true;
            }
            else{
                return false; 
            }
    }

}
