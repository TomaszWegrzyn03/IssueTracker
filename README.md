A Jira-like application that allows issues/bug tracking, task assigning and Kanban type project management.

  •	Backend: Java 17, Spring, Hibernate and PostgreSQL.
  •	Full JWT based authentication system - loging in, registering and assigning tasks/bugs to chosen users.
  •	Various endpoints allowing good communication with client.


# Endpoints 
<h1> Issues </h1>
<h2> GET List of all Issues. </h2>
Public endpoint for retreving all posted Issues.
<h2> GET Issue by id. </h2>
<h2> GET List of all Issues in Project by Project Id</h2>
<h2> POST Issue. </h2>
<h2> DELETE Issue. </h2>
<h2> UPDATE  Issue. </h2>

<h1> Projects </h1>
<h2> GET List of all Projects. </h2>
<h2> GET Project by id. </h2>
<h2> GET All Projects based on user Id</h2>
<h2> POST Project. </h2>
<h2> DELETE Project. </h2>
<h2> UPDATE  Project. </h2>

# Authentication

This application uses JWT based authentication and authorization. Any user can create an account under endpoint:

with exaple body
<code>
{
"username":"user",
"password":"user"
"email": "user@gmail.com"
}
  </code>

  
# Tests 
![image](https://github.com/TomaszWegrzyn03/IssueTracker/assets/101212671/2a06e050-f44e-473f-808f-45a2ebb922b4) <br>
![image](https://github.com/TomaszWegrzyn03/IssueTracker/assets/101212671/eff52ef3-b691-43ed-86a3-ad2a7572183c)



