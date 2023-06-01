<?php
session_start();
if (!isset($_SESSION['loggedin11'])) {
    header('Location: logowanie/index3.php');
    exit();
}
$dbhost="localhost"; $dbuser="id19668897_buk"; $dbpassword="I1A3ahT~98PU\zFB"; $dbname="id19668897_z1";
$polaczenie = mysqli_connect($dbhost, $dbuser, $dbpassword, $dbname);
if (!$polaczenie) 
{
echo "SQL error 1." . PHP_EOL;
echo "Errno: " . mysqli_connect_errno() . PHP_EOL;
echo "Error: " . mysqli_connect_error() . PHP_EOL;
exit;
}
header("Content-Type: text/event-stream");
while (!connection_aborted()) 
{ 
$rezultat = mysqli_query($polaczenie, "SELECT * FROM scada ORDER BY id DESC LIMIT 5") or die ("SQL error 2: $dbname");

$test = [];
$test2 = [];
$test3 = [];
$test4 = [];
$test5 = [];

$f = [];
$w = [];
$k = [];
$z = [];
$co = [];

while ($wiersz = mysqli_fetch_array ($rezultat)) 
{
$id = $wiersz[0];
$x1 = $wiersz[1];
$x2 = $wiersz[2];
$x3 = $wiersz[3];
$x4 = $wiersz[4];
$x5 = $wiersz[5];
$x6 = $wiersz[7];
$x7 = $wiersz[8];
$x8 = $wiersz[9];
$x9 = $wiersz[10];
$x10 = $wiersz[11];

$datetime = $wiersz[6];
array_push($test, $x1);
array_push($test2, $x2);
array_push($test3, $x3);
array_push($test4, $x4);
array_push($test5, $x5);

array_push($f, $x6);
array_push($w, $x7);
array_push($k, $x8);
array_push($z, $x9);
array_push($co, $x10);
}
echo 'data:'.$test[0]."\t".$test2[0]."\t".$test3[0]."\t".$test4[0]."\t".$test5[0]."\t".$f[0]."\t".$w[0]."\t".$z[0]."\t".$k[0]."\t".$co[0]."\t".$test[0]."\t".$test[1]."\t".$test[2]."\t".$test[3]."\t".$test[4]."\t".$test2[0]."\t".$test2[1]."\t".$test2[2]."\t".$test2[3]."\t".$test2[4]."\t".$test3[0]."\t".$test3[1]."\t".$test3[2]."\t".$test3[3]."\t".$test3[4]."\t".$test4[0]."\t".$test4[1]."\t".$test4[2]."\t".$test4[3]."\t".$test4[4]."\t".$test5[0]."\t".$test5[1]."\t".$test5[2]."\t".$test5[3]."\t".$test5[4]."\n\n";
// flush the output buffer and send echoed messages to the browser
while (ob_get_level() > 0) { ob_end_flush(); }
flush();
sleep(1); // Sleep for 1 seconds
}
mysqli_close($polaczenie);
?>