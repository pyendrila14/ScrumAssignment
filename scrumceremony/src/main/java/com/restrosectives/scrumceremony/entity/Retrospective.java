package com.restrosectives.scrumceremony.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Document
public class Retrospective {

    @Id
    private String id;

    private String name;
    private String summary;
    @NonNull
    private String date;
    @NonNull
    private ArrayList<String> participants;
    private List<Feedback> feedback;

}
