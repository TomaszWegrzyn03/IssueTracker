package com.example.IssueTracker.issue;
import com.example.IssueTracker.user.SimpleUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter @AllArgsConstructor @NoArgsConstructor @Getter
public final class IssueDto {
    private Long Id;
    private String title;
    private String description;
    private List<SimpleUserDto> users;
}
