# Presentation Script: Dynamic ORM and JSP Actions Integration

## Slide 1: Introduction & Assignment Overview
**What to show:** Title slide with "Integrating RDBMS, Dynamic ORM, and JSP Actions" or just the `index.jsp` homepage.
**What to say:**
> "Hello everyone. Today I will be presenting my solution to our latest assignment. The goal was to transition our web application from using temporary HTTP sessions for data storage to a persistent relational database (MySQL). Additionally, we were tasked with automatically generating the database, tables, and SQL queries dynamically using Java Reflection. I will also be showcasing the integration of JSP standard actions including `include`, `forward`, `param`, and `useBean`."

## Slide 2: The ServletContextListener (Database Auto-Creation)
**What to show:** Open `app/framework/DatabaseListener.java` and highlight the `contextInitialized` method. Show the SQL execution `CREATE DATABASE IF NOT EXISTS cohort12_db`.
**What to say:**
> "To begin, we created a `DatabaseListener` annotated with `@WebListener`. This listener acts upon the application's startup. Inside `contextInitialized`, it establishes an initial connection to MySQL to verify and automatically create our `cohort12_db` database if it doesn't already exist. It then establishes a persistent Singleton JDBC Connection for the entire application, storing it within the `ServletContext` so any servlet can access it without opening new connections."

## Slide 3: Auto Table Generation via Reflection
**What to show:** Scroll down in `DatabaseListener.java` and highlight the `createTables` method. Focus on the `Field[] fields = clazz.getDeclaredFields();` loop.
**What to say:**
> "Once the connection is established, the listener dynamically scans our registered model classes like `Student`, `Trainer`, and `Course`. Using Java Reflection, it reads any field annotated with `@Cohort12FormField`. It then dynamically strings together a `CREATE TABLE IF NOT EXISTS` SQL query, automatically mapping our Java object fields to database columns. This means if we add a new field to `Student.java` in the future, the database table will update automatically on the next fresh deployment."

## Slide 4: Dynamic Queries (INSERT & SELECT)
**What to show:** Open `app/action/BaseAction.java`. Highlight the `saveToDb()` method, then scroll to the `returnData()` method.
**What to say:**
> "Moving over to the action layer, I refactored `BaseAction` to fetch the JDBC connection from the `ServletContext`. Instead of appending to a list in a session, the `doPost` method now passes the entity to a `saveToDb` method. This method uses Reflection to read the object's properties and dynamically generates a parameterized `INSERT INTO` SQL statement. Similarly, the `returnData` method generates a `SELECT * FROM table` query, and automatically repopulates our Java objects from the `ResultSet`."

## Slide 5: JSP Standard Actions
**What to show:** Open `contact_us_submitted.jsp` to show `<jsp:forward>` and `<jsp:param>`, then open `index.jsp` to show `<jsp:include>`.
**What to say:**
> "As for the frontend, we successfully integrated the standard JSP actions taught in the last session. In `contact_us_submitted.jsp`, we use `<jsp:forward>` to pass the request to the display page, injecting values using `<jsp:param>`. Across our pages like `index.jsp`, we modularized the layout by injecting our common footer using `<jsp:include page='footer.jsp' />`."

## Slide 6: JSP Beans
**What to show:** Open `student_bean_demo.jsp` and highlight the `<jsp:useBean>`, `<jsp:setProperty>`, and `<jsp:getProperty>` tags. Then open the browser and navigate to `http://localhost:8080/cohort12/student_bean_demo.jsp`.
**What to say:**
> "Finally, to cover the remaining topics from the screenshot, I created a demo utilizing Java Beans natively within JSP. In this file, `<jsp:useBean>` automatically instantiates our `Student` model. We then use `<jsp:setProperty>` to assign data to it, and `<jsp:getProperty>` to render those values directly into the HTML, all without writing a single line of backend Servlet Java code."

## Slide 7: Live Demonstration
**What to show:** Open the MySQL terminal. Run `show databases;` and `show tables;` from `cohort12_db`. Then open the browser, navigate to `/register_student`, fill out the form, submit it, and check the MySQL terminal again using `SELECT * FROM student;`.
**What to say:**
> "Let me prove that it works in real-time. As you can see in MySQL, the database and tables were automatically generated. I will now register a new Student on the web portal. (Submit form). Now, querying the database... the record is permanently stored! The dynamic ORM mapping is completely functional. Thank you."
