package com.example.IssueTracker;

import com.example.IssueTracker.issue.IssueRepository;
import com.example.IssueTracker.project.ProjectRepository;
import com.example.IssueTracker.user.User;
import com.example.IssueTracker.user.UserRepository;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

@CrossOrigin
@SpringBootApplication
public class IssueTrackerApplication {

	public static void main(String[] args) {

			SpringApplication.run(IssueTrackerApplication.class, args);




	}

}
