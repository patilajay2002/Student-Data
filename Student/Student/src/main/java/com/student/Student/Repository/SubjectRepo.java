package com.student.Student.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.student.Student.Entity.SubjectEntity;

public interface SubjectRepo extends MongoRepository<SubjectEntity,String> {  
    SubjectEntity findByCode(String code);
    SubjectEntity findBysubname(String subname);
} 
