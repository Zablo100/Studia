<?php session_start();
if (!isset($_SESSION['loggedin6'])) {
    header('Location: index3.php');
    exit();
}
$current_user = $_SESSION['user'];

    $idu = $_SESSION['idu'];
    $nazwa = $_POST['nazwa'];
    $playlist = "";

    if (isset($_POST['public'])) {
        $check = 1;
    } else {
        $check = 0;
    }

    include('connection.php');
    $result = mysqli_query($connection, "SELECT name FROM playlistname6 WHERE name='$nazwa'");
    while ($row = mysqli_fetch_array($result)) {
        $playlist = $row[0];
    }

    if ($playlist) {
        echo "Playlista już istnieje. <a href='playlistaddpage.php'>Spróbuj ponownie</a>";
        mysqli_close($connection);
        exit();
    }

    $sql = "INSERT INTO playlistname6 (idu, name, public) 
            Values ('$idu','$nazwa','$check')";

    if ($connection->query($sql) === TRUE) {
        mysqli_close($connection);
        header("Location: playlist.php");
        exit();
    } else {
        echo "Error: " . $sql . "<br>" . $connection->error;
        mysqli_close($connection);
    }
?>