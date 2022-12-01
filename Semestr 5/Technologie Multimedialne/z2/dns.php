<?php
$result = dns_get_record("pbs.edu.pl");
print_r($result);
?>

<?php
$ip = gethostbyname('pbs.edu.pl');
echo '<BR />';
echo  $ip .'<BR />';
$ip = $_SERVER["REMOTE_ADDR"];
echo $ip. '<BR />';
$hostname = gethostbyaddr("8.8.8.8");
echo $hostname. '<BR />';
$hostname = gethostbyaddr($_SERVER['REMOTE_ADDR']);
echo $hostname;
?>