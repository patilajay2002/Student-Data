package com.student.Student.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.student.Student.Entity.SubjectEntity;
import com.student.Student.Repository.SubjectRepo;

@Component
public class SubjectSer {
    @Autowired
    private SubjectRepo subjectRepo;

    public List<SubjectEntity> getAllSubject(){
        
        return subjectRepo.findAll();
    }

    public SubjectEntity postEntry(SubjectEntity subjectEntity){
        SubjectEntity se=subjectRepo.save(subjectEntity);
        return se;
    }

    public Optional<SubjectEntity> getSubjecByCode(String codee){
        return Optional.of(subjectRepo.findByCode(codee));
    }


    public boolean DeleteBySubjecByCode(String codee){
        Optional<SubjectEntity> see=getSubjecByCode(codee);
        if (see.isPresent()){
            subjectRepo.delete(see.get());
            return true;
        }
        else{
            return false;
        }
    }


}
