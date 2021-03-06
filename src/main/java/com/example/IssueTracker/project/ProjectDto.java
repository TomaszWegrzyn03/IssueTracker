package com.example.IssueTracker.project;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectDto {

    private Long projectId;

    private String title;

    private String description;

    private Date createdAt;

}
