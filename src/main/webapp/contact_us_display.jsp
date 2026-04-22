<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Message Received</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 20px;
        }

        .card {
            background: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            text-align: center;
            max-width: 500px;
        }

        .icon {
            font-size: 50px;
            color: #28a745;
            margin-bottom: 20px;
        }

        h1 {
            color: #333;
            margin-bottom: 10px;
        }

        p {
            color: #555;
            margin-bottom: 20px;
        }

        a.button {
            display: inline-block;
            padding: 12px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 6px;
        }

        a.button:hover {
            background-color: #0056b3;
        }

        nav {
            background: #34495e;
            padding: 10px 15px;
            border-radius: 5px;
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
            margin-bottom: 20px;
            max-width: 500px;
        }
        nav a {
            color: #ecf0f1;
            text-decoration: none;
            font-size: 14px;
            padding: 6px 12px;
            border-radius: 4px;
            background: rgba(255,255,255,0.1);
        }
        nav a:hover { background: rgba(255,255,255,0.25); }
    </style>
</head>
<body>

<nav>
    <a href="./index.jsp">🏠 Home</a>
    <a href="./contact_us.jsp">📬 Contact Us</a>
    <a href="./el_operators_demo.jsp">🔬 EL Operators Demo</a>
    <a href="./login">🔐 Login</a>
</nav>

<div class="card">
    <h1>Thank You!</h1>

    <%-- empty operator: checks if param is null or empty string --%>
    <%-- ternary operator: condition ? valueIfTrue : valueIfFalse --%>

    <%-- Relational: param.name != '' displayed via empty check --%>
    Name: ${empty param.name ? 'Anonymous' : param.name}<br/>

    <%-- empty: safely display email or fallback --%>
    Email: ${empty param.email ? 'Not provided' : param.email}<br/>

    <%-- ternary + empty: subject is optional --%>
    Subject: ${empty param.subject ? '(No subject)' : param.subject}<br/>

    <%-- ternary + empty: message is required but guard anyway --%>
    Message: ${empty param.message ? '(No message)' : param.message}<br/>

    <%-- Logical AND: both name and email are filled in --%>
    <p>
        Complete submission: <strong>${!(empty param.name) && !(empty param.email)}</strong>
    </p>

    <%-- Relational eq: role-based greeting --%>
    <p>
        ${param.name eq 'Admin' ? 'Welcome, Administrator!' : 'Thank you for reaching out!'}
    </p>

    <p>Your message has been successfully submitted. We will get back to you shortly.</p>

    <a href="index.jsp" class="button">Back to Home</a>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>