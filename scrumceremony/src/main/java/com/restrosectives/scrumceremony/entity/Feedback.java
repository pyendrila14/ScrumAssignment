package com.restrosectives.scrumceremony.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Feedback {

    @Id
    private String id;

    private String name;
    private String feedbackBody;
    private String feedbackType;
    private String retrospectiveId;
}
