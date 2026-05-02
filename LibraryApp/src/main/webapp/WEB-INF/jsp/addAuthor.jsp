<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Author</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; }
        .form-container { background-color: #fff; padding: 20px; border-radius: 5px; max-width: 500px; margin: auto; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"] { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; }
        input[type="submit"] { background-color: #4CAF50; color: white; border: none; padding: 10px 15px; cursor: pointer; border-radius: 4px; }
        input[type="submit"]:hover { background-color: #45a049; }
        .error { color: red; margin-bottom: 15px; }
        .back-link { display: inline-block; margin-top: 15px; color: #2196F3; text-decoration: none; }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Add New Author</h2>
    
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form:form action="/addAuthor" modelAttribute="author" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <form:input path="name" id="name" required="required"/>
        </div>
        
        <div class="form-group">
            <label for="nationality">Nationality:</label>
            <form:input path="nationality" id="nationality" required="required"/>
        </div>
        
        <div class="form-group">
            <input type="submit" value="Save Author"/>
        </div>
    </form:form>

    <a href="/" class="back-link">Back to Home</a>
</div>

</body>
</html>
