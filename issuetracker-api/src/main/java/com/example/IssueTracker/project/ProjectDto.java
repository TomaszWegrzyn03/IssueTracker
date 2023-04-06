package com.example.IssueTracker.project;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectDto {
    private Long projectId;
    private String title;
    private String description;
    private LocalDate createdAt;

}
