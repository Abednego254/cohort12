<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<hr style="margin-top: 40px; border: 0; border-top: 1px solid #ccc;">
<footer style="margin-bottom: 20px; color: #7f8c8d; font-size: 0.9em;">
    <div style="display: flex; gap: 30px; margin-bottom: 15px;">
        <a href="./index.jsp" style="color: #3498db; text-decoration: none;">🏠 Home</a>
        <a href="./contact_us.jsp" style="color: #3498db; text-decoration: none;">📬 Contact Us</a>
        <a href="./el_operators_demo.jsp" style="color: #3498db; text-decoration: none;">🔬 EL Operators Demo</a>
        <a href="./login" style="color: #3498db; text-decoration: none;">🔐 Login</a>
    </div>

    <section style="background: #ecf0f1; padding: 15px; border-radius: 5px;">
        <h4 style="margin-top: 0; color: #2c3e50;">🔬 EL Operator Quick Test (Footer)</h4>
        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 10px;">
            <div>
                <strong>Relational:</strong>
                10 &gt; 5 is <span style="color: ${10 > 5 ? '#27ae60' : '#e74c3c'}; font-weight: bold;">${10 > 5}</span>
            </div>
            <div>
                <strong>Operational:</strong>
                10 + 5 is <span style="color: #2980b9; font-weight: bold;">${10 + 5}</span>
            </div>
            <div>
                <strong>Logical:</strong>
                (5 &gt; 3) and (2 &lt; 4) is <span style="color: ${(5 > 3) and (2 < 4) ? '#27ae60' : '#e74c3c'}; font-weight: bold;">${(5 > 3) and (2 < 4)}</span>
            </div>
            <div>
                <strong>Empty Check:</strong>
                param.test is empty? <span style="font-weight: bold;">${empty param.test}</span>
            </div>
        </div>
    </section>

    <p style="margin-top: 15px;">&copy; 2026 Cohort 12 Training Portal. All rights reserved.</p>
</footer>