# Cohort 12 Generic Registration Framework Refactor

This document explains the changes made to the Cohort 12 project to implement a generic registration framework, aligning with the latest architectural patterns.

## 1. Architectural Shift

Previously, each entity (Person, School, etc.) required a dedicated servlet with its own logic for handling GET and POST requests. This led to code duplication and made adding new entities tedious.

The new architecture uses **Java Generics and Reflection** to centralize all logic in a single `BaseAction<T>` class.

## 2. Key Components

### `BaseAction<T>` (Generic Servlet)
Located in `app.action.BaseAction`, this class provides the core functionality:
- **`doGet()`**: Automatically renders the registration form using the entity class's annotations.
- **`doPost()`**: Automatically populates the entity object from the request parameters using `BeanUtilsBean` and saves it to the session.
- **`getType()`**: Uses reflection to determine the specific entity class (e.g., `Person.class`) at runtime.

### Metadata-Driven Configuration
Registration servlets (e.g., `PersonRegister`, `SchoolRegister`) are now minimalist. They simply extend `BaseAction<ModelClass>` and provide configuration via annotations:
```java
@WebServlet(name = "Register Person",
        urlPatterns = { "/register_person" },
        initParams = {
                @WebInitParam(name = "pageName", value = "Register - Cohort 12"),
                @WebInitParam(name = "pageHeader", value = "Person Registration")
        })
public class PersonRegister extends BaseAction<Person> {
    // No logic required here!
}
```

### Package Reorganization
To improve maintainability, classes have been moved into logical packages:
- `app.model`: Data entities (Person, School, Student).
- `app.framework`: Custom annotations and form/table generation logic.
- `app.action`: Servlets and page controllers.
- `app.filter`: Security and session filters.

## 3. How to Add a New Entity

To register a completely new entity with ZERO logic in the servlet body:
1.  **Create the Model Class**: Define your entity in `app.model` and annotate it with `@Cohort12Form` and `@Cohort12Table`.
2.  **Create the Register Servlet**: Create a class in `app.action` that extends `BaseAction<YourModel>` and add the `@WebServlet` annotation with appropriate `initParams`.
3.  **Create the List Servlet**: Create a class in `app.action` that extends `BaseAction<YourModel>` and override `doGet` to call `Cohort12Framework.htmlTable`.

The `BaseAction` will take care of the rest!
