package com.example.IssueTracker.user;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.IssueTracker.project.Project;
import com.example.IssueTracker.project.ProjectRepository;
import com.example.IssueTracker.user.User;
import com.example.IssueTracker.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    private User testUser1;
    private User testUser2;
    private User testUser3;

    @BeforeEach
    public void setUp() {
        // Create some test users
        testUser1 = new User(1L, "user1");
        testUser2 = new User(2L, "user2");
        testUser3 = new User(3L, "user3");

        // Save the test users to the repository
        userRepository.save(testUser1);
        userRepository.save(testUser2);
        userRepository.save(testUser3);
    }

    @AfterEach
    public void tearDown() {
        // Delete all test users from the repository
        userRepository.deleteAllTestUsers();
    }

    @Test
    public void itShouldFindUserByUsername() {
        // Test that findByUsername returns the correct user
        Optional<User> foundUser = userRepository.findByUsername("user1");
        assertTrue(foundUser.isPresent());
        assertEquals(foundUser.get().getUserId(), testUser1.getUserId());
        assertEquals(foundUser.get().getUsername(), testUser1.getUsername());

    }
    @Test
    public void testFindUsersByProjectId() {
        // Set up a project and add some users to it
        List<User> users= new ArrayList<>();
        users.add(testUser1);
        users.add(testUser2);
        users.add(testUser3);
        Project project = new Project(1L, "title", "desc", LocalDate.now(), null, users);

        projectRepository.save(project);

        // Test that findUsersByProjectId returns the correct users for the project
        List<User> foundUsers = userRepository.findUsersByProjectId(project.getProjectId());
        assertEquals(3, foundUsers.size());
        assertTrue(users.equals(foundUsers));
    }

    @Test
    public void isShouldNotFindAnyUser() {
        // Test that findByUsername returns an empty optional if the user is not found
        Optional<User> foundUser = userRepository.findByUsername("NonexistentUser");
        assertFalse(foundUser.isPresent());
    }

    @Test
    public void itShouldFindNoUsersByProjectId() {
        // Set up a project with no users
        Project project = new Project(1l, "title", "desc", LocalDate.now(), null, null);
        projectRepository.save(project);

        // Test that findUsersByProjectId returns an empty list if the project has no users
        List<User> foundUsers = userRepository.findUsersByProjectId(project.getProjectId());
        assertEquals(0, foundUsers.size());
    }

}