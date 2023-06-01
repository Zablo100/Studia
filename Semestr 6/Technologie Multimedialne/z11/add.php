<?php
session_start();
if (!isset($_SESSION['loggedin11'])) {
    header('Location: logowanie/index3.php');
    exit();
}
$x1 = $_POST['x1'];
$x2 = $_POST['x2'];
$x3 = $_POST['x3'];
$x4 = $_POST['x4'];
$x5 = $_POST['x5'];
$dbhost="localhost"; $dbuser="id19668897_buk"; $dbpassword="I1A3ahT~98PU\zFB"; $dbname="id19668897_z1";
$polaczenie = mysqli_connect($dbhost, $dbuser, $dbpassword, $dbname);
$result = mysqli_query($polaczenie, "INSERT INTO pomiary2 (x1,x2,x3,x4,x5) VALUES ($x1,$x2,$x3,$x4,$x5)");
mysqli_close($polaczenie);
header('Location: index.php');
?>