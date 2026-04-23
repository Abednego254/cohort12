<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL Demo - Core, Fmt, Functions</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 40px; background-color: #f0f2f5; color: #333; }
        .container { background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); max-width: 900px; margin: auto; }
        h1 { color: #1a73e8; border-bottom: 2px solid #e8f0fe; padding-bottom: 10px; }
        h2 { color: #202124; background: #f8f9fa; padding: 10px; border-left: 5px solid #1a73e8; border-radius: 4px; margin-top: 30px; }
        .demo-box { padding: 15px; border: 1px dashed #ccc; margin: 10px 0; background: #fff; border-radius: 8px; }
        code { background: #f1f3f4; padding: 2px 5px; border-radius: 4px; color: #d93025; font-weight: bold; }
        .nav-link { display: inline-block; margin-bottom: 20px; color: #1a73e8; text-decoration: none; font-weight: 500; }
        .nav-link:hover { text-decoration: underline; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>

<div class="container">
    <a href="index.jsp" class="nav-link">← Back to Home</a>
    <h1>JSTL (JSP Standard Tag Library) Demonstration</h1>
    <p>JSTL replaces complex Java scriptlets with clean, readable XML-like tags.</p>

    <!-- 1. JSTL CORE -->
    <h2>1. JSTL Core Tags (Prefix: <code>c</code>)</h2>
    <div class="demo-box">
        <h3>Variable Setting & Output</h3>
        <c:set var="userName" value="Abednego Kaume" scope="request" />
        <p>Using <code>&lt;c:set&gt;</code> to define a variable and <code>&lt;c:out&gt;</code> to display it:</p>
        <strong>User Name:</strong> <c:out value="${userName}" default="Guest User" />
    </div>

    <div class="demo-box">
        <h3>Conditional Logic (If/Choose)</h3>
        <c:set var="userRole" value="ADMIN" />
        
        <p><strong>Example of &lt;c:if&gt;:</strong></p>
        <c:if test="${userRole == 'ADMIN'}">
            <span style="color: green; font-weight: bold;">✅ You have Administrative access.</span>
        </c:if>

        <p><strong>Example of &lt;c:choose&gt; (Switch-case):</strong></p>
        <c:choose>
            <c:when test="${userRole == 'ADMIN'}">
                <p>Welcome, Administrator! You have full control.</p>
            </c:when>
            <c:when test="${userRole == 'EDITOR'}">
                <p>Welcome, Editor! You can modify content.</p>
            </c:when>
            <c:otherwise>
                <p>Welcome, Viewer! You have read-only access.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="demo-box">
        <h3>Looping (forEach)</h3>
        <p>Iterating through a list of items:</p>
        <%
            String[] frameworks = {"Spring Boot", "Jakarta EE", "React", "Vue.js", "Angular"};
            request.setAttribute("frameworks", frameworks);
        %>
        <ul>
            <c:forEach var="tech" items="${frameworks}" varStatus="status">
                <li>Item ${status.count}: <strong>${tech}</strong></li>
            </c:forEach>
        </ul>
    </div>

    <!-- 2. JSTL FORMATTING -->
    <h2>2. JSTL Formatting Tags (Prefix: <code>fmt</code>)</h2>
    <div class="demo-box">
        <c:set var="today" value="<%= new java.util.Date() %>" />
        <c:set var="price" value="12500.75" />

        <p><strong>Date Formatting:</strong></p>
        <ul>
            <li>Full Date: <fmt:formatDate value="${today}" type="both" dateStyle="full" timeStyle="full" /></li>
            <li>Custom Pattern: <fmt:formatDate value="${today}" pattern="dd-MM-yyyy HH:mm:ss" /></li>
        </ul>

        <p><strong>Number/Currency Formatting:</strong></p>
        <ul>
            <li>Currency (KSH): <fmt:formatNumber value="${price}" type="currency" currencySymbol="KSH" /></li>
            <li>Percentage: <fmt:formatNumber value="0.85" type="percent" /></li>
        </ul>
    </div>

    <!-- 3. JSTL FUNCTIONS -->
    <h2>3. JSTL Functions (Prefix: <code>fn</code>)</h2>
    <div class="demo-box">
        <c:set var="message" value="JSTL is better than Scriptlets!" />
        <p>Original Message: <code>${message}</code></p>
        
        <table>
            <tr>
                <th>Function</th>
                <th>Expression</th>
                <th>Result</th>
            </tr>
            <tr>
                <td>Length</td>
                <td><code>\${fn:length(message)}</code></td>
                <td>${fn:length(message)}</td>
            </tr>
            <tr>
                <td>Uppercase</td>
                <td><code>\${fn:toUpperCase(message)}</code></td>
                <td>${fn:toUpperCase(message)}</td>
            </tr>
            <tr>
                <td>Contains</td>
                <td><code>\${fn:contains(message, 'better')}</code></td>
                <td>${fn:contains(message, 'better')}</td>
            </tr>
            <tr>
                <td>Substring</td>
                <td><code>\${fn:substring(message, 0, 4)}</code></td>
                <td>${fn:substring(message, 0, 4)}</td>
            </tr>
        </table>
    </div>

</div>

</body>
</html>
