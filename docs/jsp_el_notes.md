# JSP Expression Language (EL) — Lesson Notes

> **Trainer reference:** [github.com/mikebavon/cohort12](https://github.com/mikebavon/cohort12)
> **Date:** April 21, 2026

---

## What is JSP Expression Language (EL)?

JSP Expression Language (EL) is a **simplified scripting mechanism** built into JSP that lets you access data from scoped objects, request parameters, and JavaBeans **without writing Java code directly in the page**.

| Old way (Scriptlet)                             | New way (EL)                         |
|-------------------------------------------------|--------------------------------------|
| `<%= application.getInitParameter("name") %>`  | `${applicationScope.name}`           |
| `<%= request.getParameter("email") %>`         | `${param.email}`                     |
| `<%= myBean.getScheduleType() %>`              | `${myBean.scheduleType}`             |
| `<%= 1 + 2 %>`                                 | `${1+2}`                             |

EL is **cleaner, safer (less scriptlet code), and easier to read.**

---

## Enabling EL on a JSP Page

By default, EL may be disabled depending on the web.xml configuration or the servlet version. Always add this attribute to the `<%@ page %>` directive:

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
```

The key attribute is **`isELIgnored="false"`** — this tells the JSP engine to process `${...}` expressions.

---

## EL Implicit Objects

EL provides a set of pre-defined objects you can use inside `${...}` without any imports or declarations:

| EL Implicit Object    | What it maps to                                      | Example                              |
|-----------------------|------------------------------------------------------|--------------------------------------|
| `applicationScope`    | `ServletContext` attributes (context-wide)           | `${applicationScope.applicationName}`|
| `sessionScope`        | `HttpSession` attributes                             | `${sessionScope.username}`           |
| `requestScope`        | `HttpServletRequest` attributes                      | `${requestScope.message}`            |
| `param`               | `request.getParameter(name)` — single value          | `${param.email}`                     |
| `paramValues`         | `request.getParameterValues(name)` — array           | `${paramValues.hobbies[0]}`          |
| `header`              | HTTP request headers                                 | `${header["User-Agent"]}`            |
| `cookie`              | Cookies from the request                             | `${cookie.sessionId.value}`          |
| `initParam`           | `ServletContext` init parameters (from web.xml)      | `${initParam.appVersion}`            |
| `pageScope`           | Page-level attributes                                | `${pageScope.myVar}`                 |

---

## EL Arithmetic & Operators

EL supports expressions directly:

```jsp
SUM OF TWO NUMBERS: ${1 + 2}      <%-- renders: 3 --%>
IS ADMIN: ${user.role == 'admin'}  <%-- boolean check --%>
GREETING: ${empty sessionUser ? 'Guest' : sessionUser.name}
```

---

## Files Changed in This Implementation

### 1. [NEW] `app/model/Schedule.java`

A **JavaBean** that represents a training schedule entry. It follows the JavaBean contract (no-arg constructor + getters/setters) so it can be used with `<jsp:useBean>` and accessed via EL.

```java
package app.model;

import java.io.Serializable;

public class Schedule implements Serializable {

    private String scheduleType = "Weekday Only Classes";
    private String scheduleTime = "6:00 PM - 9:00 PM";

    public String getScheduleType() { return scheduleType; }
    public void setScheduleType(String scheduleType) { this.scheduleType = scheduleType; }

    public String getScheduleTime() { return scheduleTime; }
    public void setScheduleTime(String scheduleTime) { this.scheduleTime = scheduleTime; }
}
```

> **Key point:** `implements Serializable` is required for beans stored in session scope.

---

### 2. [NEW] `app/model/ContactUs.java`

A **JavaBean** for contact form data (name, email, subject, message). Its properties are accessible via `${param.*}` EL since EL `param` maps directly to HTTP request parameters.

```java
package app.model;

import java.io.Serializable;

public class ContactUs implements Serializable {
    private String name;
    private String email;
    private String subject;
    private String message;
    // standard getters and setters...
}
```

---

### 3. [MODIFIED] `src/main/webapp/index.jsp`

Three EL concepts are demonstrated here:

#### a) Enable EL on the page
```diff
- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
+ <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
```

#### b) `applicationScope` — Read a context-level attribute
```diff
- Welcome to <%= application.getInitParameter("applicationName") %> Training PORTAL
+ Welcome to ${applicationScope.applicationName} Training PORTAL
```
`applicationScope` reads attributes set on the `ServletContext`. The value `applicationName` is loaded from `web.xml` as a `<context-param>` at startup.

#### c) EL Arithmetic expression
```diff
+ SUM OF TWO NUMBER ${1+2}
```
Demonstrates that EL can evaluate arithmetic expressions directly — no Java needed.

#### d) `jsp:useBean` + `jsp:setProperty` + EL Bean Access

**Before** (hardcoded HTML):
```jsp
<section>
    <h2>Upcoming Schedule</h2>
    <p>Weekday Classes: 6:00 PM - 8:00 PM</p>
    <p>Weekend Bootcamps: 9:00 AM - 1:00 PM</p>
</section>
```

**After** (EL with JavaBean):
```jsp
<jsp:useBean id="weekdaySchedule" class="app.model.Schedule" />
<jsp:setProperty name="weekdaySchedule" property="scheduleType" />
<jsp:setProperty name="weekdaySchedule" property="scheduleTime" />

<jsp:useBean id="weekendSchedule" class="app.model.Schedule" />
<jsp:setProperty name="weekendSchedule" property="scheduleType" value="Weekend Only Bootcamps" />
<jsp:setProperty name="weekendSchedule" property="scheduleTime" value="9:00 AM - 3:00 PM" />

<section>
    <h2>Upcoming Schedule</h2>
    <p>${weekdaySchedule.scheduleType} : ${weekdaySchedule.scheduleTime}</p>
    <p>${weekendSchedule.scheduleType} : ${weekendSchedule.scheduleTime}</p>
</section>
```

**How it works:**
- `<jsp:useBean>` creates (or finds) an instance of `Schedule` in page scope with the given `id`.
- `<jsp:setProperty property="scheduleType" />` (no `value`) reads the HTTP request parameter named `scheduleType` and injects it into the bean — useful for form-bound beans.
- `<jsp:setProperty property="scheduleType" value="Weekend Only Bootcamps" />` sets a **literal hardcoded value** into the bean property.
- `${weekdaySchedule.scheduleType}` calls `weekdaySchedule.getScheduleType()` via reflection.

---

### 4. [MODIFIED] `src/main/webapp/contact_us_display.jsp`

This page receives the submitted contact form and displays the user's input. Previously used scriptlets; now uses the `param` EL implicit object.

#### a) Enable EL
```diff
+ <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
  <!DOCTYPE html>
```

#### b) `param` — Read request parameters with EL

**Before** (scriptlets):
```jsp
Name:    <%= request.getParameter("name") %><br/>
Email:   <%= request.getParameter("email") %><br/>
Subject: <%= request.getParameter("subject") %><br/>
Message: <%= request.getParameter("message") %><br/>
```

**After** (EL `param` object):
```jsp
Name:    ${param.name}<br/>
Email:   ${param.email}<br/>
Subject: ${param.subject}<br/>
Message: ${param.message}<br/>
```

`${param.name}` is equivalent to `request.getParameter("name")`. The `param` EL implicit object holds all submitted HTTP request parameters as a `Map<String, String>`.

#### c) Fix Back to Home link
```diff
- <a href="index.html" class="button">Back to Home</a>
+ <a href="index.jsp" class="button">Back to Home</a>
```

---

### 5. [MODIFIED] `src/main/webapp/contact_us.jsp`

The form now submits directly to `contact_us_display.jsp` which uses EL to render the data:

```diff
- <form action="./contact_us_submitted.jsp" method="post">
+ <form action="./contact_us_display.jsp" method="post">
```

---

## Summary of All EL Patterns Used

| Pattern                                    | EL Implicit Object  | What it Does                                         |
|--------------------------------------------|---------------------|------------------------------------------------------|
| `${applicationScope.applicationName}`      | `applicationScope`  | Reads a `ServletContext` attribute                   |
| `${1+2}`                                   | _(arithmetic)_      | Evaluates to `3`                                     |
| `${weekdaySchedule.scheduleType}`          | _(bean property)_   | Calls `weekdaySchedule.getScheduleType()`            |
| `${weekendSchedule.scheduleTime}`          | _(bean property)_   | Calls `weekendSchedule.getScheduleTime()`            |
| `${param.name}`                            | `param`             | Calls `request.getParameter("name")`                 |
| `${param.email}`                           | `param`             | Calls `request.getParameter("email")`                |
| `${param.subject}`                         | `param`             | Calls `request.getParameter("subject")`              |
| `${param.message}`                         | `param`             | Calls `request.getParameter("message")`              |

---

## How to Test

1. **Build & Deploy** the project to Tomcat (`mvn package` then deploy the WAR)
2. Open `http://localhost:8080/cohort12/`
   - You should see *"Good Morning"* or *"Good Afternoon"* greeting
   - Application name displays from `applicationScope` (not a scriptlet)
   - `SUM OF TWO NUMBER 3` appears in the header
   - Schedule section shows: `Weekday Only Classes : 6:00 PM - 9:00 PM` and `Weekend Only Bootcamps : 9:00 AM - 3:00 PM`
3. Open `http://localhost:8080/cohort12/contact_us.jsp`
   - Fill in Name, Email, Subject, Message and click **Send Message**
   - The display page (`contact_us_display.jsp`) shows your submitted data using `${param.*}` EL

> [!TIP]
> If EL expressions appear as **literal text** (e.g. `${param.name}`) in the browser, check that `isELIgnored="false"` is set on the `<%@ page %>` directive.

> [!NOTE]
> The `${1+2}` arithmetic demo is intentional — it shows that EL is not just for object access but also evaluates full expressions. EL supports `+`, `-`, `*`, `/`, `%`, `==`, `!=`, `<`, `>`, `&&`, `||`, `!`, and the `empty` operator.
