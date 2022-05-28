package com.example.IssueTracker.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository  extends JpaRepository<Issue, Long> {
}
