package com.student.Student.Entity;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("student")
public class StudentEntity {

    @Id
    private ObjectId id;
    @NonNull    
    private String sname;
    @NonNull
    private String dob;
    @NonNull
    private String address;
    @NonNull
    @Indexed(unique = true)
    private String mobile;
    @NonNull
    private String subject;
    @NonNull
    private String education;
    
}
