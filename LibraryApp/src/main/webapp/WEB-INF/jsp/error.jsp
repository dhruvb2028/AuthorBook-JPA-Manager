<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; text-align: center; }
        .error-container { background-color: #fff; padding: 30px; border-radius: 5px; max-width: 600px; margin: auto; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        h1 { color: #f44336; }
        p { font-size: 16px; color: #333; }
        .back-link { display: inline-block; margin-top: 20px; color: #2196F3; text-decoration: none; padding: 10px 20px; border: 1px solid #2196F3; border-radius: 4px; }
        .back-link:hover { background-color: #2196F3; color: white; }
    </style>
</head>
<body>

<div class="error-container">
    <h1>An Error Occurred</h1>
    <p>${message}</p>
    <a href="/" class="back-link">Return to Home</a>
</div>

</body>
</html>
