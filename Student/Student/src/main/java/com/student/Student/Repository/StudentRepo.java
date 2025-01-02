package com.student.Student.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.student.Student.Entity.StudentEntity;

public interface StudentRepo extends MongoRepository<StudentEntity,ObjectId> {  
    StudentEntity findByMobile(String mobile);
} 
