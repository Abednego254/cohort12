# Context Dependency Injection (CDI) Explanation

This document explains the implementation of Context Dependency Injection (CDI) in the `cohort12` project, following the architectural patterns demonstrated by the trainer.

## What is CDI?

**Context Dependency Injection (CDI)** is a Jakarta EE specification that provides a powerful and type-safe way to manage the lifecycle and dependencies of Java objects (beans). It is an implementation of the **Inversion of Control (IoC)** principle.

### Key Principles

1.  **Inversion of Control (IoC)**: Instead of the application code creating and managing objects, this responsibility is handed over to a container (e.g., WildFly).
2.  **Dependency Injection (DI)**: The container "injects" required dependencies into an object automatically, typically using the `@Inject` annotation.

## Implementation Details

### 1. Enabling CDI (`beans.xml`)
To enable CDI in a web application, an empty `beans.xml` file must be present in the `WEB-INF` directory.
- **Path**: `src/main/webapp/WEB-INF/beans.xml`
- **Purpose**: Signals to the application server that it should scan the archive for CDI beans.

### 2. Managed Beans (`Validate.java`)
We created a `Validate` utility class and marked it with `@ApplicationScoped`.
- **Annotation**: `@ApplicationScoped` ensures that only one instance of this bean exists for the entire duration of the application.
- **Path**: `src/main/java/app/utility/validation/Validate.java`

### 3. Injecting Dependencies
In `TrainerRegister.java`, we removed manual instantiation and used the `@Inject` annotation.
- **Before**: (Manual creation if any, though the framework was empty)
- **After**:
    ```java
    @Inject
    Validate validate;
    ```
- **Benefit**: The `validate` object is automatically provided by the WildFly container at runtime.

## Benefits of CDI

- **Loose Coupling**: Components don't need to know how to create their dependencies.
- **Testability**: Dependencies can be easily mocked during unit testing.
- **Lifecycle Management**: The container manages when beans are created and destroyed based on their scope (`@RequestScoped`, `@SessionScoped`, `@ApplicationScoped`, etc.).
- **Type Safety**: Errors are caught at deployment or compile time rather than runtime.

---
*Created as part of the cohort12 CDI implementation task.*
