package com.example.IssueTracker.issue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;
    private IssueServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest= new IssueServiceImpl(issueRepository);
    }

    @Test
    void itShouldGetAllIssues(){
        //when
        underTest.getAllIssues();
        //then
        verify(issueRepository).findAll();
    }

}