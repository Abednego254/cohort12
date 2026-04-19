package app.framework;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class DatabaseListener implements ServletContextListener {

    private static final String DB_URL_NO_DB = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "cohort12_db";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String USER = "root";
    private static final String PASS = "Ciumbe@254";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Starting Database Initialization...");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Create database if it does not exist
            try (Connection conn = DriverManager.getConnection(DB_URL_NO_DB, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                String createDbSql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
                stmt.executeUpdate(createDbSql);
                System.out.println("Database checked/created successfully.");
            }

            // 2. Initialize Single JDBC Connection for the application
            Connection appConnection = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // Set Connection to ServletContext
            ServletContext context = sce.getServletContext();
            context.setAttribute("dbConnection", appConnection);

            // 3. Automatically create tables for models
            createTables(appConnection);

            System.out.println("Database Initialized and Connection Stored in ServletContext.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    private void createTables(Connection connection) throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        classes.add(app.model.Student.class);
        classes.add(app.model.Trainer.class);
        classes.add(app.model.School.class);
        classes.add(app.model.Person.class);
        classes.add(app.model.Course.class);

        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Cohort12Form.class)) {
                String tableName = clazz.getSimpleName().toLowerCase();
                StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
                sql.append("id INT AUTO_INCREMENT PRIMARY KEY, ");

                Field[] fields = clazz.getDeclaredFields();
                boolean hasFields = false;

                for (Field field : fields) {
                    if (field.isAnnotationPresent(Cohort12FormField.class)) {
                        String columnName = field.getName();
                        sql.append(columnName).append(" VARCHAR(255), ");
                        hasFields = true;
                    }
                }

                if (hasFields) {
                    // Remove last comma and space
                    sql.setLength(sql.length() - 2);
                    sql.append(")");

                    try (Statement stmt = connection.createStatement()) {
                        stmt.execute(sql.toString());
                        System.out.println("Table ensured: " + tableName);
                    }
                }
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
