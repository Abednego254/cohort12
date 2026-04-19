# JSP and Database Assignment Implementation Guide

This document explains the changes made to the `cohort12` project to satisfy both the JSP actions integration and the generic ORM database assignment.

## 1. JSP Actions Implementation

To integrate the topics taught in the previous session and the screenshot provided:

- **`jsp:forward`, `jsp:param`, and `jsp:include`:** 
  The files `contact_us.jsp`, `contact_us_submitted.jsp`, `contact_us_display.jsp`, `footer.jsp`, and `index.jsp` were copied directly from the instructor's repository (`mikebavon/cohort12`). These demonstrate forwarding requests with parameters and including standard footers exactly as covered in class.
  
- **`jsp:useBean`, `jsp:setProperty`, and `jsp:getProperty`:**
  A new file named `student_bean_demo.jsp` was created. This file explicitly demonstrates how to initialize the `app.model.Student` class as a bean, populate its fields (like `name`, `course`, `campus`), and display them on the web page.

## 2. Generic ORM Database Integration

The application was refactored to use a real MySQL database instead of standard Java `HttpSession` storage.

### A. Database Auto-Creation via ServletContextListener
- **`DatabaseListener.java`:** Added this class equipped with the `@WebListener` annotation. On application startup, it connects to MySQL (via JDBC) and executes `CREATE DATABASE IF NOT EXISTS cohort12_db`.
- **Singleton Connection:** After ensuring the database exists, it establishes a singleton JDBC `Connection` object and stores it in the `ServletContext` to be accessed by Servlets application-wide.

### B. Auto-Table Generation via Reflection
- Within the `DatabaseListener`, the system scans the `app.model` package for any model classes annotated with the custom framework's `@Cohort12Form`.
- It dynamically uses Reflection to read the class fields annotated with `@Cohort12FormField` and automatically builds and executes a `CREATE TABLE IF NOT EXISTS` SQL statement for every identified entity (e.g., `student`, `course`, `trainer`, `school`, `person`). 

### C. Dynamic Queries in BaseAction
- **`BaseAction.java` Refactoring:** The class was modified to leverage the JDBC connection from the `ServletContext` rather than `HttpSession`.
- **Dynamic `INSERT`:** The `doPost` method was updated to dynamically generate an `INSERT INTO` query by iterating through the object's properties (via Reflection) and mapping them to `PreparedStatement` parameters.
- **Dynamic `SELECT`:** The `returnData` method was updated to dynamically generate a `SELECT * FROM [table]` query, map the returned `ResultSet` columns back into model fields, and instantiate the list of entities automatically.

## 3. How to Test

1. **Start the Database Service:** Ensure that your local MySQL server is up and running.
   ```bash
   sudo systemctl start mysqld
   ```
2. **Build the Application:** From your project root, build the `cohort12` application using Maven.
   ```bash
   mvn clean install
   ```
3. **Deploy:** Deploy the output `target/cohort12.war` to your Java EE application server (like Wildfly).
4. **Verify Database:** Open a MySQL client and verify that the `cohort12_db` was successfully auto-created on application deployment, along with its tables (`student`, `trainer`, etc.).
5. **Test the UI:** 
   - Visit `http://localhost:8080/cohort12/index.jsp` to test the standard JSP functionalities (`contact_us.jsp`).
   - Visit `http://localhost:8080/cohort12/student_bean_demo.jsp` to verify the `jsp:useBean` actions.
   - Navigate to the standard register paths (`/register_student`, `/student_lists`) and submit forms to observe the data dynamically written to and read from the MySQL database instead of a temporary session.
