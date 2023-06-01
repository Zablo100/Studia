<?php
session_start();
if (!isset($_SESSION['loggedin6'])) {
    header('Location: ../index3.php');
    exit();
}
include('connection.php');

    $name = $_GET["name"];
    $result = mysqli_query($connection, "SELECT idpl From playlistname6 WHERE name = '$name'");

    while ($wiersz = mysqli_fetch_array($result)) {
        $idpl = $wiersz[0];
    }

    $sql = "DELETE FROM playlistdatabase6 WHERE idpl='$idpl'";
    $sql1 = "DELETE FROM playlistname6 WHERE idpl='$idpl'";

    if ($connection->query($sql) === TRUE) {
        if ($connection->query($sql1) === TRUE) {
            mysqli_close($connection);
           header("Location: ../playlist.php");
            exit();

        } else {
            echo "Error: " . $sql1 . "<br>" . $connection->error;
            mysqli_close($connection);
        }

        exit();

    } else {
        echo "Error: " . $sql . "<br>" . $connection->error;
        mysqli_close($connection);
    }

    ?>