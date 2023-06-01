<?php
session_start();
if (!isset($_SESSION['loggedin6'])) {
    header('Location: index3.php');
    exit();
}

$current_user = $_SESSION['user'];
echo "<p>Zalogowano jako: $current_user</br></p>";
include('connection.php');
if (isset($_SESSION['loggedin6'])) {
    $result = mysqli_query($connection, "SELECT name From filmtype ORDER BY name ASC");
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
    <form method='post' action='upload.php' enctype='multipart/form-data'>
        Tytuł: <input type="text" name="title" maxlength="40" size="20" required="required"><br>
        Wykonawca: <input type="text" name="musician" maxlength="20" size="20" required="required"><br>
        Tekst utworu: <input type="text" name="lyrics" maxlength="20" size="20"><br>
        Gatunek: 
        <?php 
        echo "<select name='type' maxlength='40'>";
            while ($row = mysqli_fetch_array($result)) {
                $gerne = $row[0];
                echo "  <option value='$gerne'>$gerne</option>";
            }
            echo "  </select><br>";
            mysqli_close($connection);
        ?>

        Utwór: <input type='file' accept=".mp4" name='fileToUpload' id='fileToUpload'>
            <input type='submit' value='Prześlij' />
    </form>

    <br><a href='index2.php'>Powrót</a>

    <br><a href='logout.php'>Wyloguj</a><br><br>

</body>



</html>