<?php
$ipaddress = $_SERVER["REMOTE_ADDR"];
function ip_details($ip) {
$json = file_get_contents ("http://ipinfo.io/{$ip}/geo");
$details = json_decode ($json);
return $details;
}
$details = ip_details($ipaddress);
echo $details -> region; echo '<BR />';
echo $details -> country; echo '<BR />';
echo $details -> city; echo '<BR />';
echo $details -> loc; echo '<BR />';
echo $details -> ip; echo '<BR />';
$loc = $details -> loc;
echo "<a href='https://www.google.pl/maps/place/$loc'>LINK</a>";
?>
