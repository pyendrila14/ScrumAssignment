package com.restrosectives.scrumceremony.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
