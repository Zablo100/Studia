<?php session_start();
if (!isset($_SESSION['loggedin5'])) {
    header('Location: index3.php');
    exit();
}
$current_user = $_SESSION['user'];

    $idu = $_SESSION['idu'];
    $title = $_POST['title'];
    $musician = $_POST['musician'];
    $lyrics = $_POST['lyrics'];
    $type = $_POST['type'];

    include('connection.php');

    $result = mysqli_query($connection, "SELECT idmt FROM musictype WHERE name='$type'");
    while ($row = mysqli_fetch_array($result)) {
        $idmt = $row[0];
    }


    function like_match($pattern, $subject) {
        $pattern = str_replace('%', '.*', preg_quote($pattern, '/'));
        return (bool) preg_match("/^{$pattern}$/i", $subject);
    }

    $_SESSION['target'] = "";
    $target_dir = "songs";
    $target_dir2 = $target_dir;
    $basenazwa = basename($_FILES["fileToUpload"]["name"]);
    $target_file = $target_dir2 . "/" . $basenazwa;

    if(like_match("%.mp3", $basenazwa)) {

        if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
            $endnazwa = htmlspecialchars(basename($_FILES["fileToUpload"]["name"]));
            $_SESSION['nazwa'] = $endnazwa;
            $_SESSION['target'] = $target_dir  ."/".  $endnazwa;
            $sql = "INSERT INTO song (title, musician, idu, filename ,lyrics, idmt) 
                Values ('$title','$musician','$idu','$endnazwa','$lyrics','$idmt')";

            if ($connection->query($sql) === TRUE) {
                mysqli_close($connection);
                header('Location: index2.php');
                exit();

            } else {
                echo "Error: " . $sql . "<br>" . $connection->error;
                mysqli_close($connection);
            }

        } else {
            echo "Error uploading file.";
        }

    } else {
        echo "Plik jest w złym formacie<a href='select.php'>Spróbuj ponownie</a>";
    }
    ?>
</body>