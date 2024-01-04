<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <meta charset="utf-8"/>

    <script>
        function validateEmail(email) {
            let re = /\S+@\S+\.\S+/;
            return re.test(email);
        }

        //В JavaScript, пустая строка ("") будет преобразована в false при приведении к булевому типу.
        function validatePassword(password, password1) {
            if (password.trim() === "" || password1.trim() === "" || password != password1) {
                return false;
            }
            if (password.length < 6) {
                return false;
            }
            return true;
        }


        function validatePhone(phone) {
            let re = /(8|(\+7))[0-9]{10}/;
            return re.test(phone);
        }

        function validate() {
            let password = document.getElementById('pwd').value;
            let password1 = document.getElementById('pwd1').value;
            let email = document.getElementById('email').value;
            let phone = document.getElementById('phone').value;

            if (!validatePassword(password, password1)) {
                let d = document.getElementById('error');
                d.innerHTML = '<span style="color:red">Passwords do not match or are empty!</span>';
                return false;
            }


            if (!validateEmail(email)) {
                let d = document.getElementById('error');
                d.innerHTML = '<span style="color:red">Invalid email format!</span>';
                return false;
            }

            if (!validatePhone(phone)) {
                let d = document.getElementById('error');
                d.innerHTML = '<span style="color:red">Invalid phone format!</span>';
                return false;
            }

            return true;
        }
    </script>

    <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:300);

        body {
            background: #76b852; /* fallback for old browsers */
            background: rgb(141,194,111);
            background: linear-gradient(90deg, rgba(141,194,111,1) 0%, rgba(118,184,82,1) 50%);
            font-family: "Roboto", sans-serif;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        /* Новые стили для формы */
        .form, form {
            position: relative;
            z-index: 1;
            background: #FFFFFF;
            max-width: 360px;
            margin: 0 auto;
            padding: 45px;
            text-align: center;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }

        /* Стили для ввода */
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

        /* Стили для кнопок */
        input[type="submit"], .login-button {
            font-family: "Roboto", sans-serif;
            text-transform: uppercase;
            outline: 0;
            background: #4CAF50;
            width: 100%;
            border: 0;
            padding: 15px;
            color: #FFFFFF;
            font-size: 14px;
            -webkit-transition: all 0.3s ease;
            transition: all 0.3s ease;
            cursor: pointer;
        }

        input[type="submit"]:hover, .login-button:hover {
            background: #43A047;
        }

    </style>
</head>


<body>

<div style="position: relative; text-align: center;">
    <h1>Registration</h1>
    <div style="position: absolute; top: 0; right: 0;">
        <a href="/pomodoro/login"><button type="button" class="login-button">login</button></a>
    </div>
</div>


<div id="error">${error!" "} </div>
<form method="post" action="/pomodoro/registration" onsubmit="return validate()"> <!-- validate  -->
    <table>
        <tr>
            <td><label>Name: </label></td>
            <td><input type="text" name="name" placeholder="your name" required ></td>
        </tr>
        <tr>
            <td><label>Login: </label></td>
            <td><input type="text" name="username" placeholder="login"></td>
        </tr>
        <tr>
            <td><label>E-mail: </label></td>
            <td><input id="email" type="text" name="email" placeholder="email"></td>
        </tr>
        <tr>
            <td><label>Phone number: </label></td>
            <td><input id="phone" type="text" name="phone" placeholder="+71111111111" ></td>
        </tr>
        <tr>
            <td><label>Password: </label></td>
            <td><input id="pwd" type="password" name="password" placeholder="create a password"></td>
        </tr>
        <tr>
            <td><label>Repeat password: </label></td>
            <td><input id="pwd1" type="password" placeholder="repeat password"></td>
        </tr>
    </table>
    <div>
        <input type="submit" value="sign up">
    </div>R
</form>

</body>
</html>
