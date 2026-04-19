<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JSP Bean Demo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding: 40px;
        }
        .container {
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: auto;
        }
        h2 { color: #333; }
        .data { margin-bottom: 20px; }
    </style>
</head>
<body>

<div class="container">
    <h2>Student Details via jsp:useBean</h2>

    <%-- 1. Using jsp:useBean to instantiate app.model.Student --%>
    <jsp:useBean id="student" class="app.model.Student" scope="request" />

    <%-- 2. Using jsp:setProperty to set values. We can also use param="" to map HTTP parameters. --%>
    <%-- Here we are hardcoding values just to demonstrate, but in a real form you could use property="*" --%>
    <jsp:setProperty name="student" property="name" value="Jane Doe" />
    <jsp:setProperty name="student" property="course" value="Software Engineering" />
    <jsp:setProperty name="student" property="campus" value="Nairobi" />

    <div class="data">
        <%-- 3. Using jsp:getProperty to display the values --%>
        <p><strong>Name:</strong> <jsp:getProperty name="student" property="name" /></p>
        <p><strong>Course:</strong> <jsp:getProperty name="student" property="course" /></p>
        <p><strong>Campus:</strong> <jsp:getProperty name="student" property="campus" /></p>
    </div>

    <p><a href="./index.jsp">Back to Home</a></p>

    <jsp:include page="footer.jsp" />
</div>

</body>
</html>
