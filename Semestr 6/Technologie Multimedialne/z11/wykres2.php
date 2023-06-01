<?php
session_start();
if (!isset($_SESSION['loggedin11'])) {
    header('Location: logowanie/index3.php');
    exit();
}
$dbhost="localhost"; $dbuser="id19668897_buk"; $dbpassword="I1A3ahT~98PU\zFB"; $dbname="id19668897_z1";
$polaczenie = mysqli_connect($dbhost, $dbuser, $dbpassword, $dbname);
$rezultat = mysqli_query($polaczenie, "SELECT * FROM pomiary2 ORDER BY id DESC LIMIT 1");

while ($wiersz = mysqli_fetch_array ($rezultat)) 
{
$id = $wiersz[0];
$x1 = $wiersz[1];
$x2 = $wiersz[2];
$x3 = $wiersz[3];
$x4 = $wiersz[4];
$x5 = $wiersz[5];
$datetime = $wiersz[6];
}
mysqli_close($polaczenie);

require_once 'phplot.php';
$plot = new PHPlot();
$data = array( array('Tytuł',0,$x1),array('',1,$x2),array('',2,$x3),array('',3,$x4), array('',4,$x5),);
$plot -> SetDataValues($data);
$plot -> SetDataType('data-data');
$plot -> SetTitle('Wykres na podstawia ostatniego rekordu z bazy danych'); // opcjonalny tytuł wykresu
$plot -> DrawGraph();
?>