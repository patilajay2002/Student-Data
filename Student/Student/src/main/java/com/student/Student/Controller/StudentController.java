package com.student.Student.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.Student.Entity.StudentEntity;
import com.student.Student.Services.StudentSer;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired 
    private StudentSer studentSer;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<StudentEntity> se=studentSer.getAllStudent();
        return new ResponseEntity<>(se,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postEntity(@RequestBody StudentEntity studentEntity){
        try{
            StudentEntity se=studentSer.postStudnetEntry(studentEntity);
            return new ResponseEntity<>(se,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("{mobilee}")
    public ResponseEntity<?> getByMobile(@PathVariable String mobilee){
        try{
            Optional<StudentEntity> se=studentSer.getByMobileNo(mobilee);
            if(se.isPresent()){
                return new ResponseEntity<>(se.get(),HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("No Data with this Mobile...",HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("{mobilee}")
    public ResponseEntity<?> putEntryStudent(@RequestBody StudentEntity see,@PathVariable String mobilee){
        try{
            Optional<StudentEntity> se=studentSer.getByMobileNo(mobilee);
            if(se.isPresent()){
                StudentEntity oldSe=se.get();
                oldSe.setSname(see.getSname()!="" && see.getSname()!=null ? see.getSname() :oldSe.getSname() );
                oldSe.setAddress(see.getAddress()!="" && see.getAddress()!=null ? see.getAddress() :oldSe.getAddress());
                oldSe.setDob(see.getDob()!="" && see.getDob()!=null ? see.getDob() :oldSe.getDob());
                oldSe.setEducation(see.getEducation()!="" && see.getEducation()!=null ? see.getEducation() :oldSe.getEducation());
                oldSe.setSubject(see.getSubject()!="" && see.getSubject()!=null ? see.getSubject() :oldSe.getSubject());
                oldSe.setMobile(see.getMobile()!="" && see.getMobile()!=null ? see.getMobile() :oldSe.getMobile());

                studentSer.postStudnetEntry(oldSe);
                return new ResponseEntity<>(oldSe,HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("No Data with this Code...",HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("{mobilee}")
    public ResponseEntity<?> deleteEntry(@PathVariable String mobilee){
        try{
            return new ResponseEntity<>(studentSer.deleteByMobile(mobilee),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
