<?php
session_start();
if (!isset($_SESSION['loggedin6'])) {
    header('Location: ../index3.php');
    exit();
}

$current_user = $_SESSION['user'];
$name = $_GET['name'];
$_SESSION['name'] = $name;

echo "<p>Logged in: $current_user</br></p>";
echo "Playlista <b>" . $name . "</b><br><br>";
include('connection.php');
    ?>
    <form method='post' action='../removesong.php'>
        Utwory:
        <?php
        $array = array();
        $result1 = mysqli_query($connection, "SELECT film.title, film.director, filmtype.name, film.ids From film INNER JOIN filmtype ON film.idmt=filmtype.idmt");
        $result3 = mysqli_query($connection, "SELECT playlistdatabase6.ids From playlistname6 INNER JOIN playlistdatabase6 ON playlistdatabase6.idpl=playlistname6.idpl WHERE playlistname6.name='$name'");

        while ($wiersz1 = mysqli_fetch_array($result3)) {
            $idsdb = $wiersz1[0];
            array_push($array, $idsdb);
        }

        echo "<TABLE CELLPADDING=5 BORDER=1>";
        echo "<TR><TD>Dodaj</TD><TD>Tytuł</TD><TD>Wykonawca</TD><TD>Gatunek</TD></TR>\n";

        while ($wiersz = mysqli_fetch_array($result1)) {

            $tytul = $wiersz[0];
            $wykonawca = $wiersz[1];
            $gatunek = $wiersz[2];
            $ids = $wiersz[3];
            if (in_array($ids, $array)) {
                echo "<TR><TD><input type='submit' value='Usuń' name='$ids'><br></TD><TD>$tytul</TD><TD>$wykonawca</TD><TD>$gatunek</TD></TR>\n";
            }
        }
        echo "</TABLE>";
        mysqli_close($connection);
        echo "<br><a href='..//inventory.php/?name=$name'>Powrót</a><br>
              <a href='../logout.php'>Wyloguj</a><br><br>";
        ?>
    </form>
