<?php
session_start();
if (!isset($_SESSION['loggedin6'])) {
    header('Location: ../index3.php');
    exit();
}

$name = $_SESSION['name'];
include('connection.php');

    foreach ($_POST as $idsfromselect => $content) {
        
    }

    $result = mysqli_query($connection, "SELECT idpl From playlistname6 WHERE name = '$name'");

    while ($wiersz = mysqli_fetch_array($result)) {
        $idpl = $wiersz[0];
    }

    $result1 = mysqli_query($connection, "SELECT ids From film");

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        while ($wiersz1 = mysqli_fetch_array($result1)) {
            $ids = $wiersz1[0];
            if ($ids == $idsfromselect) {
                $sql = "DELETE FROM playlistdatabase6 WHERE idpl='$idpl' AND ids = '$ids'";
            }
        }
    }

    if ($connection->query($sql) === TRUE) {
        mysqli_close($connection);
        header("Location: inventory.php/?name=$name");
        exit();

    } else {
        echo "Error: " . $sql . "<br>" . $connection->error;
        mysqli_close($connection);
    }

    ?>