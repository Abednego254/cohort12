# Explaining The Generic Framework Implementation

By merging the power of **Java Generics**, **Java Reflection**, and **Custom Annotations**, we have transitioned the `cohort12` application from dealing with explicit classes (like `School` and `Person`) into a dynamic, scaleable web application framework.

Here is a breakdown of exactly what was done to explain to your class.

## 1. Creating the Generic Base Servlet (`CohortServeltAction<T>`)

Instead of creating separate servlets with hard-coded logic for registering every single entity (e.g., repeating the saving logic inside a `RegisterPage`), we converted `CohortServeltAction` into a generic Servlet that extends `HttpServlet`.

**How it works using Reflection:**
```java
public Class<T> getEntityClass() {
    return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
}
```
Whenever a subclass like `PersonRegister extends CohortServeltAction<Person>` is created, the base class uses Java Reflection to inspect its own generic superclass (`CohortServeltAction<Person>`). By calling `.getActualTypeArguments()[0]`, it dynamically extracts `Person.class` without us ever needing to pass it manually!

The `doGet` and `doPost` methods then use this extracted class to dynamically render the form (via `Cohort12Framework`) and automatically parse/save form submission using `BeanUtils.populate()`.

## 2. Annotating the Entity Classes

We modified both the `School` and `Person` classes to use the custom annotations taught within the class (`@Cohort12Form`, `@Cohort12Table`, `@Cohort12FormField`, `@Cohort12TableCol`).

**For example, `Person.java` now looks like this:**
```java
@Cohort12Form(label = "Register Person", actionUrl = "./register_person")
@Cohort12Table(label = "Persons", tableUrl = "./list_registered")
public class Person implements Serializable {

    @Cohort12FormField(label = "Name", name = "name", placeholder = "Enter Full Name")
    @Cohort12TableCol(label = "Name")
    private String name;
    // ...
}
```
* **Form Action Binding:** `School` tells the framework its web form should submit to `./register_school` and `Person` submits to `./register_person`.
* **Table Binding:** Both entities declare they want their tables dynamically listed at `./list_registered`.

## 3. Fixing the Framework Hardcoding

In `Cohort12Framework.htmlTable()`, there was a hardcoded HTML anchor taking the user back to the `"./register"` URL, which broke our customized endpoints.
We changed this using Reflection to dynamically read the actual destination from the `@Cohort12Form` annotation attached to the class:
```java
Cohort12Form formAnnot = clazz.getAnnotation(Cohort12Form.class);
String actionUrl = formAnnot != null ? formAnnot.actionUrl() : "./register";
writer.println("<a href=\"" + actionUrl + "\">&larr; Register " + cohort12Table.label() + " </a>");
```

## 4. Making the Dashboard (`ListRegisteredPage`) Generic

The `ListRegisteredPage.java` initially possessed hard-coded lines dedicated only to loading `<School>`. We refactored it to simply iterate over an array of our known entity classes (`School.class`, `Person.class`).

For each class, the Dashboard dynamically deduces the Session storage key (e.g. `SCHOOL_DB`, `PERSON_DB`) and parses the generic HTML configuration using the `Cohort12Framework.htmlTable` function.
You can now freely add cars, buildings, or user types, and by just adding their Class to the `knownClasses` list, the application will render their data grids!

> [!TIP]
> **Summary to the class:**
> We abstracted away redundant HTML generation and Servlet request handling logic into a Generic superclass (`CohortServeltAction`). Then, we used Custom Annotations to uniquely "tag" our POJOs (`Person` and `School`), which Java Reflection reads at runtime to automatically output the matching Forms, Fields, Tables, and Endpoint URLs.
