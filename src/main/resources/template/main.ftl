<!DOCTYPE html>
<html>
<head>
    <title>pomodoro</title>
    <link href="https://fonts.googleapis.com/css2?family=Pixelify+Sans:wght@700&display=swap" rel="stylesheet">
    <style>
        body, html {
            background-size: cover;
            text-align: center;
            margin: 0;
            padding: 0;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;

        }

        header {
            width: 515px;
            margin: 0 auto;
        }

        a:hover {
            cursor: pointer;
            color: #43A047;
            text-decoration: none;
        }

        h1 {
            font-family: 'Lato', sans-serif;
            font-size: 70px;
            text-align: center;
            color: white;
            margin-bottom: 0px;
        }

        h2 {
            color: white;
            font-family: 'Lato', sans-serif;
            font-size: 17px;
            margin-bottom: 50px;
        }

        #pom {
            color: white;
            text-decoration: none;
        }

        #pom:hover {
            color: #DDDDDD;
            text-decoration: none;
        }

        #clock {
            margin-top: 50px;
            width: 250px;
            margin: 0 auto;
            background: rgba(255, 255, 255, 0.4);
            font-family: 'Lato', sans-serif;
            font-size: 40px;
            text-align: center;
            border-radius: 50px;
        }

        #start {
            font-family: 'Lato', sans-serif;
            font-size: 40px;
            color: white;
        }

        #start:hover {
            color: #43A047;
        }

        #reset:hover {
            color: #43A047;
        }

        #reset {
            font-family: 'Lato', sans-serif;
            font-size: 40px;
            color: white;
        }

        .container {
            text-align: center;

        }
        .no-select {
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -khtml-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        .navbar img {
            width: 50px;
            height: 50px;
            position: absolute;
            top: 10px;
            right: 10px;
            transition: transform 0.3s ease; /* Плавное изменение трансформации */
        }

        .navbar img:hover {
            transform: scale(1.1); /* Увеличение размера изображения */
        }
    </style>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<header>
    <div class="button-container">
        <a class="no-select" onclick='start()' id='start'>Start</a>
        <a class="no-select" onclick="reset()" id='reset'>Reset</a>
    </div>
    <div class="no-select" id="clock">
        <a onclick='up()' id='up'>+</a>
        <p id='time'></p>
        <a onclick='down()' id='down'>-</a>
    </div>
    <div class="navbar">
        <a href="/pomodoro/profile">
            <img src="/pomodoro/resources/images/service/profile.png" alt="profile">
        </a>
    </div>
</header>
<script>
    var abrv = 2500;
    var time;
    var stopped = false;
    var timer;

    function convert() {
        var temp = "" + abrv;
        if (temp[2] === '0' && temp[3] === '0') {
            time = temp[0] + temp[1];
        } else if (abrv < 1000) {
            time = temp[0];
        }
    }

    function down() {
        if (parseInt(abrv) > 100) {
            abrv = parseInt(abrv) - 100;
        }
        convert();
        $('#time').html(time);
        stopped = false;
    }

    function up() {
        if (parseInt(time) < 60) {
            abrv = parseInt(abrv) + 100;
        }
        convert();
        $('#time').html(time);
        stopped = false;
    }

    $(document).ready(function() {
        convert();
        $('#time').html(time);
    });

    function start() {
        if (stopped === false) {
            seconds = parseInt(time) * 60;
        }
        stopped = false;
        $('#start').html('Stop');
        document.getElementById('down').setAttribute('onclick', '');
        document.getElementById('up').setAttribute('onclick', '');
        document.getElementById('start').setAttribute('onclick', 'stop()');

        timer = setTimeout(countDown, 1000);

        function countDown() {
            if (seconds > 0) {
                seconds--;
            }
            if (seconds > 0 && stopped === false) {
                timer = setTimeout(countDown, 1000);
            }
            if (stopped === false && seconds % 60 >= 10) {
                $('#time').html(Math.floor(seconds / 60) + ':' + seconds % 60);
            } else if (seconds % 60 < 10 && seconds % 60 >= 0 && seconds / 60 !== 0) {
                $('#time').html(Math.floor(seconds / 60) + ':0' + seconds % 60);
            } else if (seconds / 60 === 0) {
                $('#time').html('DONE');
                alert("Timer is done!");
            }
        }
    }

    function stop() {
        clearTimeout(timer);
        stopped = true;
        $('#start').html('Start');
        document.getElementById('down').setAttribute('onclick', 'down()');
        document.getElementById('up').setAttribute('onclick', 'up()');
        document.getElementById('start').setAttribute('onclick', 'start()');
        if (seconds % 60 >= 10) {
            $('#time').html(Math.floor(seconds / 60) + ':' + seconds % 60);
        } else {
            $('#time').html(Math.floor(seconds / 60) + ':0' + seconds % 60);
        }
    }

    function reset() {
        if (stopped === false) {
            clearTimeout(timer);
            document.getElementById('down').setAttribute('onclick', 'down()');
            document.getElementById('up').setAttribute('onclick', 'up()');
            document.getElementById('start').setAttribute('onclick', 'start()');
            $('#start').html('Start');
            stopped = true;
        }

        seconds = 1500;
        abrv = 2500;
        time = '25';
        $('#time').html(time);
    }
</script>
</body>
</html>
</header>