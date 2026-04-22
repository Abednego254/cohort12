<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EL Operators Demo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 40px;
        }

        h1 { color: #2c3e50; }
        h2 { color: #34495e; margin-top: 30px; border-bottom: 2px solid #3498db; padding-bottom: 5px; }

        .container {
            max-width: 800px;
            margin: 0 auto;
        }

        form {
            background: #ffffff;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }

        label { display: block; margin-top: 12px; font-weight: bold; color: #555; }

        input[type="number"], select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        button {
            margin-top: 20px;
            background-color: #3498db;
            color: white;
            border: none;
            padding: 12px 30px;
            border-radius: 5px;
            font-size: 15px;
            cursor: pointer;
        }

        button:hover { background-color: #2980b9; }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }

        th {
            background-color: #2c3e50;
            color: white;
            padding: 12px 16px;
            text-align: left;
        }

        td { padding: 10px 16px; border-bottom: 1px solid #eee; }
        tr:last-child td { border-bottom: none; }

        .true  { color: #27ae60; font-weight: bold; }
        .false { color: #e74c3c; font-weight: bold; }

        .note {
            background: #eaf4fb;
            border-left: 4px solid #3498db;
            padding: 12px 16px;
            border-radius: 4px;
            margin-bottom: 20px;
            color: #2c3e50;
            font-size: 14px;
        }

        nav {
            background: #34495e;
            padding: 10px 15px;
            border-radius: 5px;
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
            margin-bottom: 20px;
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

        .result-section {
            display: ${empty param.a ? 'none' : 'block'};
        }
    </style>
</head>
<body>
<div class="container">

    <nav>
        <a href="./index.jsp">🏠 Home</a>
        <a href="./contact_us.jsp">📬 Contact Us</a>
        <a href="./el_operators_demo.jsp">🔬 EL Operators Demo</a>
        <a href="./login">🔐 Login</a>
    </nav>

    <h1>EL Logical &amp; Relational Operators Demo</h1>

    <div class="note">
        Enter two numbers below and click <strong>Evaluate</strong>.
        The page will use <strong>JSP Expression Language (EL)</strong>
        to evaluate relational and logical operators — with <strong>zero Java scriptlets</strong>.
    </div>

    <%-- Self-submitting form — posts back to the same page --%>
    <form action="el_operators_demo.jsp" method="post">
        <label for="a">Number A:</label>
        <input type="number" id="a" name="a" value="${param.a}" placeholder="e.g. 10" required />

        <label for="b">Number B:</label>
        <input type="number" id="b" name="b" value="${param.b}" placeholder="e.g. 5" required />

        <label for="role">User Role:</label>
        <select id="role" name="role">
            <option value="admin"  ${param.role eq 'admin'  ? 'selected' : ''}>Admin</option>
            <option value="student" ${param.role eq 'student' ? 'selected' : ''}>Student</option>
            <option value="guest"  ${param.role eq 'guest'  ? 'selected' : ''}>Guest</option>
        </select>

        <button type="submit">Evaluate</button>
    </form>

    <%-- Only show results section after form is submitted --%>
    <div class="result-section">

        <h2>Input Values</h2>
        <table>
            <tr><th>Variable</th><th>Value</th></tr>
            <tr><td>A</td><td>${param.a}</td></tr>
            <tr><td>B</td><td>${param.b}</td></tr>
            <tr><td>Role</td><td>${param.role}</td></tr>
        </table>

        <%-- ============================================================ --%>
        <%-- RELATIONAL OPERATORS                                          --%>
        <%-- ============================================================ --%>
        <h2>Relational Operators</h2>
        <table>
            <tr>
                <th>Expression</th>
                <th>Symbolic</th>
                <th>Alphabetic (Alias)</th>
                <th>Result</th>
            </tr>
            <tr>
                <td>A == B</td>
                <td>${param.a} == ${param.b}</td>
                <td>${param.a} eq ${param.b}</td>
                <td class="${param.a == param.b ? 'true' : 'false'}">
                    ${param.a == param.b}
                </td>
            </tr>
            <tr>
                <td>A != B</td>
                <td>${param.a} != ${param.b}</td>
                <td>${param.a} ne ${param.b}</td>
                <td class="${param.a != param.b ? 'true' : 'false'}">
                    ${param.a != param.b}
                </td>
            </tr>
            <tr>
                <td>A &lt; B</td>
                <td>${param.a} &lt; ${param.b}</td>
                <td>${param.a} lt ${param.b}</td>
                <td class="${param.a < param.b ? 'true' : 'false'}">
                    ${param.a < param.b}
                </td>
            </tr>
            <tr>
                <td>A &gt; B</td>
                <td>${param.a} &gt; ${param.b}</td>
                <td>${param.a} gt ${param.b}</td>
                <td class="${param.a > param.b ? 'true' : 'false'}">
                    ${param.a > param.b}
                </td>
            </tr>
            <tr>
                <td>A &lt;= B</td>
                <td>${param.a} &lt;= ${param.b}</td>
                <td>${param.a} le ${param.b}</td>
                <td class="${param.a <= param.b ? 'true' : 'false'}">
                    ${param.a <= param.b}
                </td>
            </tr>
            <tr>
                <td>A &gt;= B</td>
                <td>${param.a} &gt;= ${param.b}</td>
                <td>${param.a} ge ${param.b}</td>
                <td class="${param.a >= param.b ? 'true' : 'false'}">
                    ${param.a >= param.b}
                </td>
            </tr>
        </table>

        <%-- ============================================================ --%>
        <%-- LOGICAL OPERATORS                                             --%>
        <%-- ============================================================ --%>
        <h2>Logical Operators</h2>
        <table>
            <tr>
                <th>Expression</th>
                <th>Symbolic</th>
                <th>Alphabetic (Alias)</th>
                <th>Result</th>
            </tr>
            <tr>
                <td>(A &gt; 0) AND (B &gt; 0)</td>
                <td>(${param.a} &gt; 0) &amp;&amp; (${param.b} &gt; 0)</td>
                <td>(${param.a} gt 0) and (${param.b} gt 0)</td>
                <td class="${(param.a > 0) && (param.b > 0) ? 'true' : 'false'}">
                    ${(param.a > 0) && (param.b > 0)}
                </td>
            </tr>
            <tr>
                <td>(A &gt; 0) OR (B &gt; 0)</td>
                <td>(${param.a} &gt; 0) || (${param.b} &gt; 0)</td>
                <td>(${param.a} gt 0) or (${param.b} gt 0)</td>
                <td class="${(param.a > 0) || (param.b > 0) ? 'true' : 'false'}">
                    ${(param.a > 0) || (param.b > 0)}
                </td>
            </tr>
            <tr>
                <td>NOT (A == B)</td>
                <td>!(${param.a} == ${param.b})</td>
                <td>not (${param.a} eq ${param.b})</td>
                <td class="${!(param.a == param.b) ? 'true' : 'false'}">
                    ${!(param.a == param.b)}
                </td>
            </tr>
            <tr>
                <td>(A &gt; B) AND (Role == 'admin')</td>
                <td>(${param.a} &gt; ${param.b}) &amp;&amp; (role == 'admin')</td>
                <td>(${param.a} gt ${param.b}) and (role eq 'admin')</td>
                <td class="${(param.a > param.b) && (param.role eq 'admin') ? 'true' : 'false'}">
                    ${(param.a > param.b) && (param.role eq 'admin')}
                </td>
            </tr>
            <tr>
                <td>(A &lt; B) OR (Role == 'admin')</td>
                <td>(${param.a} &lt; ${param.b}) || (role == 'admin')</td>
                <td>(${param.a} lt ${param.b}) or (role eq 'admin')</td>
                <td class="${(param.a < param.b) || (param.role eq 'admin') ? 'true' : 'false'}">
                    ${(param.a < param.b) || (param.role eq 'admin')}
                </td>
            </tr>
        </table>

        <%-- ============================================================ --%>
        <%-- EMPTY OPERATOR & TERNARY                                      --%>
        <%-- ============================================================ --%>
        <h2>Special Operators: empty &amp; Ternary ( ? : )</h2>
        <table>
            <tr>
                <th>Expression</th>
                <th>EL Expression</th>
                <th>Result</th>
            </tr>
            <tr>
                <td>Is A empty?</td>
                <td>empty param.a</td>
                <td class="${empty param.a ? 'true' : 'false'}">
                    ${empty param.a}
                </td>
            </tr>
            <tr>
                <td>Is role empty?</td>
                <td>empty param.role</td>
                <td class="${empty param.role ? 'true' : 'false'}">
                    ${empty param.role}
                </td>
            </tr>
            <tr>
                <td>A is positive?</td>
                <td>${param.a} &gt; 0 ? "Positive" : "Non-positive"</td>
                <td>${param.a > 0 ? 'Positive' : 'Non-positive'}</td>
            </tr>
            <tr>
                <td>Who is logged in?</td>
                <td>empty sessionScope.user ? "Guest" : sessionScope.user</td>
                <td>${empty sessionScope.user ? 'Guest' : sessionScope.user}</td>
            </tr>
            <tr>
                <td>Access Level</td>
                <td>role eq 'admin' ? "Full Access" : "Limited Access"</td>
                <td><strong>${param.role eq 'admin' ? 'Full Access' : 'Limited Access'}</strong></td>
            </tr>
        </table>

    </div><%-- end result-section --%>

    <jsp:include page="footer.jsp" />

</div>
</body>
</html>
