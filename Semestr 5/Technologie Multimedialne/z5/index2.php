<?php
session_start();
include('connection.php');
if (!isset($_SESSION["loggedin5"])) {
  header("Location: index3.php");
  exit();
}
$current_user = $_SESSION['user'];

echo "<p>Logged in: $current_user</br></p>";

if (isset($_SESSION['loggedin5'])) {
    $result = mysqli_query($connection, "SELECT name From musictype ORDER BY idmt DESC");
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
    <a href='select.php'>Dodaj muzykę</a><br>
    <a href='playlist.php'>Playlisty</a><br>
    <a href='playlistaddpage.php'>utwórz nową playliste!</a><br><br>
    

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

    echo "Dostępna muzyka:";
    echo "<TABLE CELLPADDING=5 BORDER=1>";
    echo "<TR><TD>Tytuł</TD><TD>Wykonawca</TD><TD>Gatunek</TD><TD>Utwór</TD></TR>\n";

    while ($wiersz = mysqli_fetch_array($result1)) {
        $tytul = $wiersz[0];
        $wykonawca = $wiersz[1];
        $gatunek = $wiersz[2];
        $utwor = "songs/" . $wiersz[3];
        $ids = $wiersz[4];
        $imageFileType = strtolower(pathinfo($utwor, PATHINFO_EXTENSION));

        if ($imageFileType == "mp3") {
            $file = "<audio controls> <source id='$ids' src='$utwor' type='audio/mpeg'></audio>";
        } else {
            $file = "";
        }
        echo "<TR><TD>$tytul</TD><TD>$wykonawca</TD><TD>$gatunek</TD><TD>$file</TD></TR>\n";
    }
    echo "</TABLE>";
    mysqli_close($connection);
    ?>
    <br>
    <a href='index4.php'>Wizyty portalu</a><br>
    <a href='logout.php'>Wyloguj</a><br>
</body>
</html>