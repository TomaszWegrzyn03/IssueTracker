package com.example.IssueTracker.user;
import com.example.IssueTracker.issue.Issue;
import com.example.IssueTracker.project.Project;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity(name = "users") @Table @AllArgsConstructor @NoArgsConstructor @Getter @Setter @EqualsAndHashCode
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    @Column(updatable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, columnDefinition = "varchar(50)", name = "username")
    private String username;

    @Column( columnDefinition = "varchar(300)", name = "password")
    private String password;

    @Column(nullable = false, columnDefinition = "varchar(50)", name = "email")
    private String email;

    @Column(nullable = false, columnDefinition = "date", name = "created_at")
    private LocalDate createdAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "projectUsers")
    private List<Project> projects;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "issueUsers")
    private List<Issue> issues;

    @Column(nullable = false,  name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(nullable = false, name = "locked")
    private boolean locked;

    @Column(nullable = false, name = "enabled")
    private boolean enabled;

    public User(Long userId, String username) {
        this.userId = userId;
        this.username = username;
        this.email = "email";
        this.userRole = UserRole.ROLE_USER;
        this.createdAt = LocalDate.now();
        this.enabled = true;
        this.locked = false;

    }

    public User(String username, String email, UserRole roleUser, String password, LocalDate now, boolean b, boolean b1) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

