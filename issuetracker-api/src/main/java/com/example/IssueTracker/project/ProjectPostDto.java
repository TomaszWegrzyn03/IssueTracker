package com.example.IssueTracker.project;

import com.example.IssueTracker.user.SimpleUserDto;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data  @AllArgsConstructor  @NoArgsConstructor  @Getter  @Setter
public class ProjectPostDto {

    private String title;

    private String description;

    private List<SimpleUserDto> projectUsers = new ArrayList<>();

}
