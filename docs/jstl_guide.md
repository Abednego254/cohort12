# JSTL Implementation Guide: Core, Formatting, and Functions

This document summarizes the implementation of the **JSP Standard Tag Library (JSTL)** in the Cohort 12 project. JSTL is a collection of ready-made tags that replace complex Java scriptlets (`<% ... %>`), making JSP pages cleaner, more readable, and easier to maintain.

## 1. JSTL Core Library (Prefix: `c`)
The Core library provides essential tags for logic, variable management, and looping.

- **`<c:set>`**: Used to define a variable in a specific scope (page, request, session, or application).
- **`<c:out>`**: Used to display the value of a variable. It is safer than EL expressions because it can escape XML/HTML characters automatically.
- **`<c:if>`**: A simple conditional tag that executes its body if the test condition is true.
- **`<c:choose>`**: Works like a Java `switch` statement. It uses `<c:when>` for cases and `<c:otherwise>` for the default case.
- **`<c:forEach>`**: Used to iterate over collections like Arrays, Lists, or Maps.

## 2. JSTL Formatting Library (Prefix: `fmt`)
This library is used to format and display data based on locales and specific patterns.

- **`<fmt:formatDate>`**: Formats a `java.util.Date` object into a readable string (e.g., `dd-MM-yyyy`).
- **`<fmt:formatNumber>`**: Formats numbers as currency, percentages, or with specific decimal places.

## 3. JSTL Functions Library (Prefix: `fn`)
Functions are used within EL expressions to manipulate strings and collections.

- **`${fn:length()}`**: Returns the number of items in a collection or the length of a string.
- **`${fn:toUpperCase()}`**: Converts a string to uppercase.
- **`${fn:contains()}`**: Checks if a string contains a specific substring.
- **`${fn:substring()}`**: Extracts a part of a string.

## Why use JSTL?
1. **Cleaner Code**: XML-like tags are easier to read for web designers than raw Java code.
2. **Maintenance**: Separates presentation logic from business logic.
3. **Reusability**: Standardized tags work across all JSP-compliant servers.

## Files Modified/Created:
1. **`pom.xml`**: Added JSTL API and Implementation dependencies.
2. **`jstl_demo.jsp`**: A new page demonstrating all the features mentioned above.
3. **`index.jsp`**: Added a navigation link to the new demo.
