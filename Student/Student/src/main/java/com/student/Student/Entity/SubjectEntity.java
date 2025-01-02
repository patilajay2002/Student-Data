package com.student.Student.Entity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("subject")
@Data
@NoArgsConstructor
public class SubjectEntity {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String code;
    @NonNull
    @Indexed(unique = true)
    private String subname;
    @NonNull
    private String th_pr;
}
