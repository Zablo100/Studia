<?php declare(strict_types=1); // włączenie typowania zmiennych w PHP >=7
session_start();
if (!isset($_SESSION['loggedin']))
{
header('Location: index3.php');
exit();
}
?>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
    <title>Zabłocki</title>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<BODY>
<a href="komunikator.php">Powrót do wiadomości</a>
<?php

$ipaddress = $_SERVER["REMOTE_ADDR"];
function ip_details($ip) {
$json = file_get_contents ("http://ipinfo.io/{$ip}/geo");
$details = json_decode ($json);
return $details;
}

$details = ip_details($ipaddress);
$loc = $details -> loc;
$ip = $details -> ip;
$city = $details -> city;

echo '<table>
  <tr>
    <th>Data</th>
    <th>Adres IP</th>
    <th>Lokalizacja</th>
    <th>Współrzędnę</th>
    <th>Mapa</th>
    <th>Przeglądarka</th>
    <th>Ekran</th>
    <th>Okno</th>
    <th>Kolory</th>
    <th>Ciasteczka</th>
    <th>Java</th>
    <th>Język</th>
  </tr>';
  
  $link = mysqli_connect("localhost", "id19668897_buk", "I1A3ahT~98PU\zFB", "id19668897_z1");
 if(!$link) { echo"Błąd: ". mysqli_connect_errno()." ".mysqli_connect_error(); }
 mysqli_query($link, "SET NAMES 'utf8'");
 
 $username = $_SESSION['user'];
 $result = mysqli_query($link, "SELECT * FROM goscieportalu3 WHERE username='$username'"); 

  while($row=mysqli_fetch_array($result)){
     $list[] = $row;
}
 
if(isset($list)){ 
foreach($list as $user){
    echo "<tr>
    <td>$user[datetime]</td>
    <td>$ip</td>
    <td>$city</td>
    <td>$loc</td>
    <td><a href='https://www.google.pl/maps/place/$loc'>Link</a></td>
    <td>$user[browser]</td>
    <td>$user[screen]</td>
    <td>$user[cscreen]</td>
    <td>$user[colors]</td>
    <td>$user[cookies]</td>
    <td>$user[java]</td>
    <td>$user[lang]</td>
  </tr>";
}
}

?>
</table>
<br/>
<a href="logout.php">Wyloguj</a>

<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</BODY>
</HTML>
