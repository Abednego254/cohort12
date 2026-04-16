# Testing and Extension Guide

This guide explains how to test the current features of the Cohort 12 Training Portal and how to add new entities to the project using the generic framework.

## Part 1: How to Test the Project

### 1. Build and Deploy
Ensure you are in the `cohort12` directory and run:
```bash
mvn clean wildfly:deploy
```
*Note: Make sure your WildFly server is running.*

### 2. Access the Application
Open your browser and navigate to:
`http://localhost:8080/cohort12`

### 3. Login
- On the welcome page, click **Login**.
- Use the following credentials:
    - **Username**: `admin`
    - **Password**: `12345`

### 4. Registration and Listing Flow
- Once logged in, you will be on the **Home** (Dashboard) page.
- Select a registration link (e.g., **Register Course**).
- Fill out the form and click **Submit**.
- You will be automatically redirected to the corresponding list page (e.g., **Course List**).
- Verify that your entry appears in the table.

---

## Part 2: Step-by-Step Flow to Add a New Entity

The power of the generic framework is that you can add a new entity with **zero logic** in the servlet body. Let's say you want to add a `Unit` entity.

### Step 1: Create the Model Class
Create `src/main/java/app/model/Unit.java`.
- Annotate with `@Cohort12Form` and `@Cohort12Table`.
- Define fields and annotate them with `@Cohort12FormField` and `@Cohort12TableCol`.

```java
@Cohort12Form(label = "Unit Register", actionUrl = "./register_unit")
@Cohort12Table(label = "Units", tableUrl = "./unit_lists", registerUrl = "./register_unit")
public class Unit implements Serializable {
    @Cohort12FormField(label = "Unit Name", name = "unitName", placeholder = "Enter Unit Name")
    @Cohort12TableCol(label = "Unit Name")
    private String unitName;
    // Getters and Setters...
}
```

### Step 2: Create the Register Servlet
Create `src/main/java/app/action/UnitRegister.java`.
- Extend `BaseAction<Unit>`.
- Add `@WebServlet` with configuration.

```java
@WebServlet(name = "Register Unit", urlPatterns = { "/register_unit" },
    initParams = {
        @WebInitParam(name = "pageName", value = "Register - Unit"),
        @WebInitParam(name = "pageHeader", value = "Unit Registration")
    })
public class UnitRegister extends BaseAction<Unit> {
    // LEAVE EMPTY! BaseAction handles everything.
}
```

### Step 3: Create the List Servlet
Create `src/main/java/app/action/UnitList.java`.
- Extend `BaseAction<Unit>`.
- Override `doGet` to call `htmlTable`.

```java
@WebServlet("/unit_lists")
public class UnitList extends BaseAction<Unit> {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        // Standard HTML boilerplate...
        Cohort12Framework.htmlTable(writer, getType(), returnData(req.getSession()));
        // Standard Footer...
    }
}
```

### Step 4: Update the Dashboard
Open `app.action.HomePage.java` and add a link to your new unit pages in the navbar/menu.

```html
<a href='./register_unit'>Register Unit</a>
<a href='./unit_lists'>Unit List</a>
```

### Step 5: Compile and Run
Run `mvn clean compile` and redeploy. Your new entity is live!
