<?php
session_start();
if (!isset($_SESSION['loggedin5'])) {
    header('Location: index3.php');
    exit();
}

$current_user = $_SESSION['user'];
echo "<p>Logged in: $current_user</br></p>";

include('connection.php');
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

    <a href='playlistaddpage.php'>Utwórz nową playlistę</a><br><br>

    <?php

    $result1 = mysqli_query($connection, "SELECT playlistname.name, playlistname.public, users5.username From playlistname INNER JOIN users5 ON playlistname.idu=users5.id WHERE users5.username='$current_user' or playlistname.public='1' ORDER BY playlistname.name ASC");
    
    if (isset($_POST['cancel'])) {
        $result1 = mysqli_query($connection, "SELECT playlistname.name, playlistname.public, users5.username From playlistname INNER JOIN users5 ON playlistname.idu=user5.id WHERE users5.username='$current_user' or playlistname.public='1' ORDER BY playlistname.name ASC");
    }
    
    if (isset($_POST['search'])) {
        $pname = $_POST['pname'];
        $result1 = mysqli_query($connection, "SELECT playlistname.name, playlistname.public, users5.username From playlistname INNER JOIN users5 ON playlistname.idu=user5.id Where playlistname.name='$pname' AND user.login='$current_user' ORDER BY playlistname.name ASC");
    }


    echo "<TABLE CELLPADDING=5 BORDER=1>";

    echo "<TR><TD>Nazwa</TD><TD>Publiczna</TD><TD>Utworzona przez</TD></TR>\n";

    while ($wiersz = mysqli_fetch_array($result1)) {
        $name = $wiersz[0];
        if ($wiersz[1] == 1) {
            $public = "TAK";
        } else {
            $public = "NIE";
        }
        $author = $wiersz[2];
        echo "<TR><TD><a href='inventory.php/?name=$name'>$name</TD><TD>$public</TD><TD>$author</TD></TR>\n";
    }
    echo "</TABLE>";
    mysqli_close($connection);
    ?>
    <br>
    <a href='index2.php'>Powrót</a><br>
    <a href='logout.php'>Wyloguj</a><br>
</body>
</html>