<?php
session_start();
if (!isset($_SESSION['loggedin5'])) {
    header('Location: index3.php');
    exit();
}
$current_user = $_SESSION['user'];
echo "<p>Logged in: $current_user</br></p>";
?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
</head>
<body>

    <form method='POST' action='playlistadd.php' enctype='multipart/form-data'>

        Utwórz nową playliste:<br>
        Nazwa :<input type='text' required="required" name='nazwa'><br>
        <input type="checkbox" name="public">Playlista publiczna <br>
        <input type='submit' value='Utwórz playlistę' name='submit'><br><br>

        <a href='index2.php'>Powrót</a><br>
        <a href='logout.php'>Wyloguj</a><br><br>

</body>
</html>