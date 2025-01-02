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

import com.student.Student.Entity.SubjectEntity;
import com.student.Student.Services.SubjectSer;

@RestController
@RequestMapping("subject")
public class SubjectController {
    @Autowired 
    private SubjectSer subjectSer;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<SubjectEntity> se=subjectSer.getAllSubject();
        return new ResponseEntity<>(se,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postEntity(@RequestBody SubjectEntity subjectEntity){
        try{
            SubjectEntity se=subjectSer.postEntry(subjectEntity);
            return new ResponseEntity<>(se,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("{codee}")
    public ResponseEntity<?> getByCode(@PathVariable String codee){
        try{
            Optional<SubjectEntity> se=subjectSer.getSubjecByCode(codee);
            if(se.isPresent()){
                return new ResponseEntity<>(se.get(),HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("No Data with this Code...",HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("{codee}")
    public ResponseEntity<?> putEntry(@RequestBody SubjectEntity see,@PathVariable String codee){
        try{
            Optional<SubjectEntity> se=subjectSer.getSubjecByCode(codee);
            if(se.isPresent()){
                SubjectEntity oldSe=se.get();
                oldSe.setCode(see.getCode()!="" && see.getCode()!=null ? see.getCode() :oldSe.getCode() );
                oldSe.setSubname(see.getSubname()!="" && see.getSubname()!=null ? see.getSubname() :oldSe.getSubname());
                oldSe.setTh_pr(see.getTh_pr()!="" && see.getTh_pr()!=null ? see.getTh_pr() :oldSe.getTh_pr());
                subjectSer.postEntry(oldSe);
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

    @DeleteMapping("{codee}")
    public ResponseEntity<?> deleteEntry(@PathVariable String codee){
        try{
            return new ResponseEntity<>(subjectSer.DeleteBySubjecByCode(codee),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}