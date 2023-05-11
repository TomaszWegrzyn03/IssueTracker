package com.example.IssueTracker.user;

import lombok.*;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SimpleUserDto {
    private Long userId;
    private String username;

}
