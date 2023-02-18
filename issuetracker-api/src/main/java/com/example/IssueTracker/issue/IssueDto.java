package com.example.IssueTracker.issue;
import com.example.IssueTracker.user.SimpleUserDto;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public record IssueDto(Long Id, String title, String description, List<SimpleUserDto> users)
{
}
