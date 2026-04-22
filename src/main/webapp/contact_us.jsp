<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Us</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f4f6f8;
        }

        .container {
            width: 90%;
            max-width: 900px;
            margin: 50px auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .contact-info {
            text-align: center;
            margin-bottom: 30px;
            color: #555;
        }

        .contact-info p {
            margin: 5px 0;
        }

        form {
            display: grid;
            gap: 15px;
        }

        input, textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }

        textarea {
            resize: vertical;
            min-height: 120px;
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 12px;
            font-size: 16px;
            border-radius: 6px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .footer {
            text-align: center;
            margin-top: 20px;
            font-size: 13px;
            color: #888;
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
    <h1>Contact Us</h1>

    <div class="contact-info">
        <p>Email: support@example.com</p>
        <p>Phone: +254 700 000 000</p>
        <p>Location: Nairobi, Kenya</p>
    </div>

    <form action="./contact_us_display.jsp" method="post">
        <input type="text" name="name" placeholder="Your Name" required>
        <input type="email" name="email" placeholder="Your Email" required>
        <input type="text" name="subject" placeholder="Subject">
        <textarea name="message" placeholder="Your Message" required></textarea>

        <button type="submit">Send Message</button>
    </form>

    <div class="footer">
        &copy; 2026 Your Company. All rights reserved.
    </div>
</div>

</body>
</html>