<?php
session_start();
if (!isset($_SESSION['loggedin5'])) {
    header('Location: index3.php');
    exit();
}
$current_user = $_SESSION['user'];
$current_idu = $_SESSION['idu'];
$name = $_GET['name'];
$_SESSION['playlistname'] = $name;

echo "<p>Logged in: $current_user</br></p>";
echo "Playlista <b>" . $name . "</b><br><br>";
include('connection.php');

$result = mysqli_query($connection, "SELECT name From musictype ORDER BY idmt DESC");
$result1 = mysqli_query($connection, "SELECT playlistname.name, playlistname.public, users5.username From playlistname INNER JOIN users5 ON playlistname.idu=users5.id Where playlistname.name='$name'");
$playlist = mysqli_query($connection, "SELECT idpl FROM playlistname WHERE name= '$name'");

while ($row = mysqli_fetch_array($playlist)) {
    $idpl = $row[0];
}
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
    <?php
    while ($row = mysqli_fetch_array($result1)) {
        $public = $row[1];
        $login = $row[2];
    }

    if ($login == $current_user) {
        echo "<a href='../playlistaddsongpage.php/?name=$name'>Dodaj utwory do playlisty</a><br>";
        echo "<a href='../removesongpage.php/?name=$name'>Usuń muzykę z playlisty</a><br>";
        echo "<a href='../removeplaylist.php/?name=$name'>Całkowicie usuń playlistę</a><br><br>";
    }
    ?>


    <?php

    $result1 = mysqli_query($connection, "SELECT song.title, song.musician, musictype.name, song.filename, song.ids From song INNER JOIN musictype ON song.idmt=musictype.idmt ORDER BY song.title ASC");

    if (isset($_POST['search'])) {
        $title = $_POST['title'];
        $musician = $_POST['musician'];
        $type = $_POST['type'];
        $result1 = mysqli_query($connection, "SELECT song.title, song.musician, musictype.name, song.filename, song.ids From song INNER JOIN musictype ON song.idmt=musictype.idmt Where song.title='$title' or song.musician='$musician' or musictype.name='$type' ORDER BY song.title ASC");
    }

    if (isset($_POST['cancel'])) {
        $result1 = mysqli_query($connection, "SELECT song.title, song.musician, musictype.name, song.filename, song.ids From song INNER JOIN musictype ON song.idmt=musictype.idmt ORDER BY song.title ASC");
    }

    ?>

    Utwory:

    <?php
    $array = array();
    $result3 = mysqli_query($connection, "SELECT playlistdatabase.ids From playlistname INNER JOIN playlistdatabase ON playlistdatabase.idpl=playlistname.idpl WHERE playlistname.name='$name'");

    while ($wiersz1 = mysqli_fetch_array($result3)) {
        $idsdb = $wiersz1[0];
        array_push($array, $idsdb);
    }

    echo "<TABLE CELLPADDING=5 BORDER=1>";

    echo "<TR><TD>Tytuł</TD><TD>Wykonawca</TD><TD>Gatunek</TD><TD>Utwór</TD></TR>\n";

    while ($wiersz = mysqli_fetch_array($result1)) {
        $tytul = $wiersz[0];
        $wykonawca = $wiersz[1];
        $gatunek = $wiersz[2];
        $utwor = "../songs/" . $wiersz[3];
        $imageFileType = strtolower(pathinfo($utwor, PATHINFO_EXTENSION));

        if ($imageFileType == "mp3") {
            $file = "<audio controls> <source src='$utwor' type='audio/mpeg'></audio>";
        } else {
            $file = "";
        }

        $ids = $wiersz[4];
        if (in_array($ids, $array)) {
            echo "<TR><TD>$tytul</TD><TD>$wykonawca</TD><TD>$gatunek</TD><TD>$file</TD></TR>\n";
        }
    }

    echo "</TABLE>";
    mysqli_close($connection);
    ?>
    <br>
        <a href='../playlist.php'>Powrót</a><br><br>
    <a href='../logout.php'>Wyloguj</a><br><br>
</body>
</html>