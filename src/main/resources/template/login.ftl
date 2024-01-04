<!DOCTYPE html>
<html>
<head>
    <title>Login to main</title>
    <meta charset="utf-8"/>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:300);

        body {
            font-family: "Roboto", sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(90deg, rgba(141,194,111,1) 0%, rgba(118,184,82,1) 50%);
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        h1 {
            padding: 20px;
            text-align: center;
        }

        #message h2 {
            color: red;
        }

        .form, form {
            background-color: #fff;
            padding: 20px;
            margin: 20px auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 360px;
            text-align: center;
        }

        table {
            margin: auto;
            font-size: 18px;
        }

        label {
            margin-right: 10px;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"],
        input[type="phone"],
        input[type="username"] {
            font-family: "Roboto", sans-serif;
            outline: 0;
            background: #f2f2f2;
            width: 100%;
            border: 0;
            margin: 0 0 15px;
            padding: 15px;
            box-sizing: border-box;
            font-size: 14px;
        }

        input[type="submit"], .login-button, .signup-button {
            font-family: "Roboto", sans-serif;
            text-transform: uppercase;
            outline: 0;
            background: #4CAF50;
            width: 100%;
            border: none;
            padding: 15px;
            color: #FFFFFF;
            font-size: 14px;
            -webkit-transition: all 0.3s ease;
            transition: all 0.3s ease;
            cursor: pointer;
        }

        input[type="submit"]:hover, .login-button:hover, .signup-button:hover {
            background: #43A047;
        }

        div {
            text-align: center;
            margin: 20px;
        }

        a {
            color: blue;
            text-decoration: none;
        }

    </style>
</head>
<body>
<div style="position: relative;">
    <h1>Sign in</h1>
    <div style="position: absolute; top: 0; right: 0;">
        <a href="/pomodoro/registration"><button type="button" class="signup-button">Register</button></a>
    </div>
</div>

<div id = "message"><h2>${message}</h2></div>
<form method="post" action="/pomodoro/usercheck">
    <table>
        <tr>
            <td><label>Login:</label></td>
            <td><input type="text" name="username" placeholder="enter username"></td>
        </tr>
        <tr>
            <td><label>Password: </label></td>
            <td><input type="password" name="password" placeholder="enter password"></td>
        </tr>
    </table>
    <div><input type="submit" value="sign in"></div>
</form>
</body>
</html>