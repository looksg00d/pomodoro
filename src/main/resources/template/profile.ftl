<!DOCTYPE html>
<html>
<head>
    <meta charset = "utf-8"/>
    <title>Profile</title>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }

        #visitor-info {
            background-color: #fff;
            padding: 20px;
            margin: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }


        .profile-details h1 {
            margin: 0;
            font-size: 24px;
            color: #43A047;
        }

        .profile-details p {
            margin: 5px 0;
            font-size: 16px;
        }
        .navbar {
            display: flex;
            justify-content: flex-end;
        }

        .navbar a {
            text-decoration: none;
            color: white;
            background-color: #43A047;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .navbar a:hover {
            background-color: #369036;
        }

        .notes-container {
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .note {
            border: 1px solid #ddd;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
        }

        .note p {
            margin: 5px 0;
        }

        /* Стили для формы добавления заметок */
        form.add-note {
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        form.add-note textarea {
            width: calc(100% - 20px);
            padding: 10px;
        }

        form.add-note input[type="submit"] {
            margin-top: 10px;
            padding: 10px 20px;
            background-color: #43A047;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        form.add-note input[type="submit"]:hover {
            background-color: #369036;
        }

        .navbar .main-page {
            background-color: #43A047; /* Исходный цвет кнопки */
            transition: background-color 0.3s;
        }

        .navbar .main-page:hover {
            background-color: #369036; /* Затемненный цвет при наведении */
        }

        .note {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border: 1px solid #ddd;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
        }

        .note-content {
            flex-grow: 1;
            overflow-wrap: break-word;
            white-space: normal;
            max-width: 100%;
        }


        .delete-btn {
            margin-left: 10px;
            border: none;
            background: transparent;
            cursor: pointer;
            font-size: 16px;
        }

        .delete-btn:hover {
            color: #ff0000;
        }

    </style>
</head>
<body>
<div id="visitor-info">
    <div class="profile-details">
        <h1>${user.name}</h1>
        <p><strong>Login:</strong> ${user.username}</p>
        <p><strong>Email:</strong> ${user.email}</p>
    </div>
    <div class="navbar">
        <a href="/pomodoro/main" class="main-page">Main page</a>
    </div>
</div>

<!-- Форма для добавления заметки -->
<form action="/pomodoro/notes" method="post" class="add-note">
    <textarea name="content" rows="4" placeholder="Type your note here..."></textarea>
    <br>
    <input type="submit" value="Add note">
</form>

<!-- Список заметок -->
<div class="notes-container">
    <#list notes as note>
        <div class="note">
            <div class="note-content">
                <p><strong>Date:</strong> ${note.creationDate?string("yyyy-MM-dd HH:mm:ss")}</p>
                <p><strong>Note:</strong> ${note.content}</p>
            </div>
            <!-- Форма для удаления заметки -->
            <form action="/pomodoro/deleteNote" method="post" class="delete-form">
                <input type="hidden" name="noteId" value="${note.noteId}">
                <button type="submit" class="delete-btn">×</button>
            </form>
        </div>
    </#list>
</div>



</body>
</html>