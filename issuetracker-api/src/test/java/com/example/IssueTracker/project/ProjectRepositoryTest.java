package com.example.IssueTracker.project;

import com.example.IssueTracker.user.User;
import com.example.IssueTracker.user.UserRepository;
import com.example.IssueTracker.user.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAllTestUsers();
        projectRepository.deleteAllTestProjects();
    }

    @Test
    void itShouldFindAllUserProjectsByUsername() {
        //given
        String username = "ExampleUsername";
        User user = new User(1L, username, "email", "password",LocalDate.now(),
                null,null,UserRole.ROLE_USER,false,true);
        userRepository.save(user);
        List<User> projectUsers =new ArrayList<>();
        projectUsers.add(user);
        Project project1 = new Project(1L,"TestProject","TestProject", LocalDate.now(),null, projectUsers);
        Project project2 = new Project(2L,"TestProject","TestProject", LocalDate.now(),null, projectUsers);
        projectRepository.save(project1);
        projectRepository.save(project2);

        //when
        List<Project> allProjects = projectRepository.findAll();
        List<Project> projectsFoundByUsername = projectRepository.findByProjectUsers_Username(username);

        //then
        assertEquals(allProjects,projectsFoundByUsername);
    }

    @Test
    void itShouldFindOnlyOneUserProjectByUsername() {
        //given
        String username1 = "ExampleUsername1";
        String username2 = "ExampleUsername2";
        User user1 = new User(1L, username1, "email", "password",LocalDate.now(),
                null,null,UserRole.ROLE_USER,false,true);
        User user2 = new User(2L, username2, "email", "password", LocalDate.now(),
                null,null,UserRole.ROLE_USER,false,true);
        userRepository.save(user1);
        userRepository.save(user2);
        List<User> projectUsers1 =new ArrayList<>();
        projectUsers1.add(user1);
        List<User> projectUsers2 =new ArrayList<>();
        projectUsers2.add(user2);
        Project project1 = new Project(1L,"TestProject","TestProject", LocalDate.now(),null, projectUsers1);
        Project project2 = new Project(2L,"TestProject","TestProject", LocalDate.now(),null, projectUsers2);
        projectRepository.save(project1);
        projectRepository.save(project2);

        //when
        List<Project> projectsFoundByUsername = projectRepository.findByProjectUsers_Username(username2);

        //then
        assertEquals(projectsFoundByUsername.size(), 1);
    }
    @Test
    void itShouldNotFindAnyUser() {
        //given
        String username1 = "ExampleUsername1";
        User user1 = new User((long)1, username1, "email", "password",LocalDate.now(),
                null,null,UserRole.ROLE_USER,false,true);
        userRepository.save(user1);
        List<User> projectUsers1 =new ArrayList<>();
        projectUsers1.add(user1);
        Project project1 = new Project(1L,"TestProject","TestProject", LocalDate.now(),null, projectUsers1);
        projectRepository.save(project1);

        //when
        List<Project> projectsFoundByUsername = projectRepository.findByProjectUsers_Username("wrong");

        //then
        assertTrue(projectsFoundByUsername.isEmpty());
    }

    @Test
    void itShouldDeleteProject() {
        //given
        Long projectId = (long)1;
        Project project = new Project(projectId,"TestProject","TestProject", LocalDate.now(),null, null);
        projectRepository.save(project);

        //when
        projectRepository.deleteProject(projectId);
        List<Project> projects = projectRepository.findAll();

        //then
        assertTrue(projects.isEmpty());
    }

    @Test
    void itShouldNotDeleteProject() {
        //given
        Long projectId1 = (long)1;
        Long projectId2 = (long)2;
        Project project1 = new Project(projectId1,"Project1","Project1",LocalDate.now(),null, null);
        Project project2 = new Project(projectId2,"Project2","Project2",LocalDate.now(),null, null);
        projectRepository.save(project1);
        projectRepository.save(project2);

        //when
        projectRepository.deleteById(projectId2);
        List<Project> notDeletedProjects = projectRepository.findAll();

        //then

    }
}