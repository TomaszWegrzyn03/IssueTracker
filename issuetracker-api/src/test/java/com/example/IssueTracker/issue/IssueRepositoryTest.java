package com.example.IssueTracker.issue;
import com.example.IssueTracker.project.Project;
import com.example.IssueTracker.project.ProjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IssueRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    IssueRepository issueRepository;

    @AfterEach
    void tearDown() {
        projectRepository.deleteAll();
        issueRepository.deleteAll();
    }

    @Test
    void itShouldFindIssuesByProjectId() {
        //given
        Long projectId = (long)1;
        Project project = new Project(projectId,"TestProject","TestProject",LocalDate.now(),null, null);
        Issue issue1 = new Issue("Issue1","Issue1",
                "Issue1","Issue1",
                "Issue1",projectId, LocalDate.now(),null);
        Issue issue2 = new Issue("Issue2","Issue2",
                "Issue2","Issue2",
                "Issue2",projectId, LocalDate.now(),null);
        List<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);
        projectRepository.save(project);
        issueRepository.saveAll(issues);

        //when
        List<Issue> issuesFoundByProjectId = issueRepository.findIssueByProjectId(projectId);

        //then
        assertEquals(issues, issuesFoundByProjectId);
    }

    @Test
    void itShouldNotFindAnyIssues() {
        //given
        Long projectId1 = (long)1;
        Long projectId2 = (long)2;
        Project project1 = new Project(projectId1,"Project1","Project1",LocalDate.now(),null, null);
        Project project2 = new Project(projectId2,"Project2","Project2",LocalDate.now(),null, null);
        Issue issue1 = new Issue("Issue1","Issue1",
                "Issue1","Issue1",
                "Issue1",projectId1, LocalDate.now(),null);
        Issue issue2 = new Issue("Issue2","Issue2",
                "Issue2","Issue2",
                "Issue2",projectId1, LocalDate.now(),null);
        List<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);
        projectRepository.save(project1);
        projectRepository.save(project2);
        issueRepository.saveAll(issues);

        //when
        List<Issue> issuesFoundByProjectId = issueRepository.findIssueByProjectId(projectId2);

        //then
        assertTrue(issuesFoundByProjectId.isEmpty());
    }

    @Test
    void itShouldDeleteAllIssuesByProjectId() {
        //given
        Long projectId = (long)1;
        Project project = new Project(projectId,"TestProject","TestProject",LocalDate.now(),null, null);
        Issue issue1 = new Issue("Issue1","Issue1",
                "Issue1","Issue1",
                "Issue1",projectId, LocalDate.now(),null);
        Issue issue2 = new Issue("Issue2","Issue2",
                "Issue2","Issue2",
                "Issue2",projectId, LocalDate.now(),null);
        List<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);
        projectRepository.save(project);
        issueRepository.saveAll(issues);

        //when
        issueRepository.deleteAllIssuesByProjectId(projectId);
        List<Issue> issuesFoundByProjectId = issueRepository.findIssueByProjectId(projectId);

        //then
        assertTrue(issuesFoundByProjectId.isEmpty());
    }

    @Test
    void itShouldNotDeleteAnyIssues() {
        //given
        Long projectId1 = (long)1;
        Long projectId2 = (long)2;
        Project project1 = new Project(projectId1,"Project1","Project1",LocalDate.now(),null, null);
        Project project2 = new Project(projectId2,"Project2","Project2",LocalDate.now(),null, null);
        Issue issue1 = new Issue("Issue1","Issue1",
                "Issue1","Issue1",
                "Issue1",projectId1, LocalDate.now(),null);
        Issue issue2 = new Issue("Issue2","Issue2",
                "Issue2","Issue2",
                "Issue2",projectId1, LocalDate.now(),null);
        List<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);
        projectRepository.save(project1);
        projectRepository.save(project2);
        issueRepository.saveAll(issues);

        //when
        issueRepository.deleteAllIssuesByProjectId(projectId2);
        List<Issue> issuesFoundByProjectId = issueRepository.findIssueByProjectId(projectId1);

        //then
        assertEquals(issues, issuesFoundByProjectId);
    }
    @Test
    void itShouldDeleteIssue() {
        //given
        Long projectId = (long)1;
        Project project = new Project(projectId,"TestProject","TestProject",LocalDate.now(),null, null);
        Issue issue1 = new Issue("Issue1","Issue1",
                "Issue1","Issue1",
                "Issue1",projectId, LocalDate.now(),null);
        List<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        projectRepository.save(project);
        issueRepository.saveAll(issues);

        //when
        issueRepository.deleteIssue(issue1.getIssueId());
        List<Issue> issuesFoundByProjectId = issueRepository.findIssueByProjectId(projectId);

        //then
        assertTrue(issuesFoundByProjectId.isEmpty());
    }

    @Test
    void itShouldDeleteOnlyOneIssue() {
        //given
        Long projectId = (long)1;
        Project project = new Project(projectId,"TestProject","TestProject",LocalDate.now(),null, null);
        Issue issue1 = new Issue("Issue1","Issue1",
                "Issue1","Issue1",
                "Issue1",projectId, LocalDate.now(),null);
        Issue issue2 = new Issue("Issue2","Issue2",
                "Issue2","Issue2",
                "Issue2",projectId, LocalDate.now(),null);
        List<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);
        projectRepository.save(project);
        issueRepository.saveAll(issues);

        //when
        issueRepository.deleteIssue(issue1.getIssueId());
        List<Issue> listWithOneIssue = new ArrayList<>();
        listWithOneIssue.add(issue2);
        List<Issue> issuesFoundByProjectId = issueRepository.findIssueByProjectId(projectId);

        //then
        assertEquals(issuesFoundByProjectId,listWithOneIssue);
    }


}