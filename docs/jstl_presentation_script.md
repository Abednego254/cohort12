# Presentation Script: JSTL Standard Tag Library Implementation

## Slide 1: Introduction to JSTL
**What to show:** Open `index.jsp` and point to the "JSTL Demo" link, or show the top of `jstl_demo.jsp`.
**What to say:**
> "Hello everyone. Today I will be presenting the implementation of the JSP Standard Tag Library, or JSTL, in our project. As we've seen, writing Java scriptlets inside JSP pages can make the code messy and hard to maintain. JSTL provides a collection of ready-made, XML-like tags that make our JSP pages cleaner, more readable, and better separated from backend logic. I have implemented the three core areas: Core, Formatting, and Functions."

## Slide 2: JSTL Core - Variables and Logic
**What to show:** Open `jstl_demo.jsp` and highlight the `<c:set>`, `<c:if>`, and `<c:choose>` sections.
**What to say:**
> "First, we have the Core library, prefixed with 'c'. Here, I'm using `<c:set>` to define variables like 'userName' and 'userRole' directly in the JSP scope. Instead of using Java 'if' statements in scriptlets, I've used `<c:if>` for simple checks and `<c:choose>` for more complex switch-like logic. This allows us to conditionally render HTML elements based on user roles, such as showing admin-only messages, in a much more structured way."

## Slide 3: JSTL Core - Dynamic Looping
**What to show:** Scroll to the `<c:forEach>` section in `jstl_demo.jsp`.
**What to say:**
> "One of the most powerful tags in the Core library is `<c:forEach>`. Instead of writing a Java 'for' loop to iterate through an array of frameworks, I use this tag to cleanly loop through the collection. It even provides a 'varStatus' attribute, which I've used here to automatically number each item in the list. This keeps our HTML structure intact and easy to read for frontend developers."

## Slide 4: JSTL Formatting (Fmt Library)
**What to show:** Scroll to the "JSTL Formatting Tags" section in `jstl_demo.jsp`.
**What to say:**
> "Next is the Formatting library, prefixed with 'fmt'. This is essential for internationalization and data presentation. I've used `<fmt:formatDate>` to take a standard Java Date object and format it into a human-readable pattern. I've also demonstrated `<fmt:formatNumber>`, which automatically handles currency symbols like 'KSH' and percentage formatting, ensuring that raw data is presented professionally to the user."

## Slide 5: JSTL Functions (Fn Library)
**What to show:** Scroll to the "JSTL Functions" table in `jstl_demo.jsp`.
**What to say:**
> "The third part is the Functions library, prefixed with 'fn'. These are used directly within Expression Language. I've implemented examples like `fn:length` to count characters, `fn:toUpperCase` for string transformation, and `fn:contains` to check for specific keywords. These functions allow us to perform common string manipulations on the fly without needing any helper methods in our Java classes."

## Slide 6: Live Demonstration & Conclusion
**What to show:** Open the browser and navigate to `http://localhost:8080/cohort12/jstl_demo.jsp`. Point out the formatted date, the loop results, and the function outputs.
**What to say:**
> "To wrap up, let's look at the live page. You can see the logic in action—the admin message is visible because the role was set to ADMIN, the list of frameworks is perfectly rendered, the date is formatted precisely, and the string functions have processed our test message. By using JSTL, we've eliminated scriptlets while keeping our application dynamic and powerful. Thank you."
