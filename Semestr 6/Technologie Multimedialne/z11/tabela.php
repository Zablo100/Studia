<?php
session_start();
if (!isset($_SESSION['loggedin11'])) {
    header('Location: logowanie/index3.php');
    exit();
}
$dbhost="localhost"; $dbuser="id19668897_buk"; $dbpassword="I1A3ahT~98PU\zFB"; $dbname="id19668897_z1";
$polaczenie = mysqli_connect($dbhost, $dbuser, $dbpassword, $dbname);
$rezultat = mysqli_query($polaczenie, "SELECT * FROM pomiary2");
print "<table cellpadding=5 border=1>";
print "<tr><td>id</td><td>x1</td><td>x2</td><td>x3</td><td>x4</td><td>x5</td><td>Data/Godzina</td></tr>\n";
while ($wiersz = mysqli_fetch_array ($rezultat)) 
{
$id = $wiersz[0];
$x1 = $wiersz[1];
$x2 = $wiersz[2];
$x3 = $wiersz[3];
$x4 = $wiersz[4];
$x5 = $wiersz[5];
$datetime = $wiersz[6];
print "<tr><td>$id</td><td>$x1</td><td>$x2</td><td>$x3</td><td>$x4</td><td>$x5</td><td>$datetime</td></tr>\n";
}
print "</table>";
mysqli_close($polaczenie);
?>