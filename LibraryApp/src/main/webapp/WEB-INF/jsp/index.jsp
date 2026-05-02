<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; }
        h1, h2 { color: #333; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; background-color: #fff; }
        table, th, td { border: 1px solid #ddd; }
        th, td { padding: 12px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        a.button { display: inline-block; padding: 10px 15px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px; }
        a.button:hover { background-color: #45a049; }
        .action-link { color: #2196F3; text-decoration: none; }
        .action-link:hover { text-decoration: underline; }
    </style>
</head>
<body>

    <h1>Library Management System</h1>
    
    <h2>Books</h2>
    <a href="/addBook" class="button">Add New Book</a>
    <br><br>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Genre</th>
                <th>Author</th>
                <th>Nationality</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.genre}</td>
                    <td>${book.author.name}</td>
                    <td>${book.author.nationality}</td>
                    <td>
                        <a href="/updateBook/${book.id}" class="action-link">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Authors</h2>
    <a href="/addAuthor" class="button">Add New Author</a>
    <br><br>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Nationality</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="author" items="${authors}">
                <tr>
                    <td>${author.id}</td>
                    <td>${author.name}</td>
                    <td>${author.nationality}</td>
                    <td>
                        <a href="/updateAuthor/${author.id}" class="action-link">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
