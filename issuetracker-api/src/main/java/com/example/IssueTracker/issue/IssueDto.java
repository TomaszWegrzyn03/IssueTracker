package com.example.IssueTracker.issue;

import com.example.IssueTracker.user.SimpleUserDto;

import java.util.List;


public record IssueDto(Long Id, String title, String description, List<SimpleUserDto> users)
{
}
