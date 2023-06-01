<?php declare(strict_types=1); // włączenie typowania zmiennych w PHP >=7
session_start(); // zapewnia dostęp do zmienny sesyjnych w danym pliku
if (!isset($_SESSION['loggedin']))
{
header('Location: index3.php');
exit();
}

$connection = mysqli_connect("localhost", "id19668897_buk", "I1A3ahT~98PU\zFB", "id19668897_z1");
if (!$connection)
{
	echo " MySQL Connection error." . PHP_EOL;
	echo "Errno: " . mysqli_connect_errno() . PHP_EOL;
	echo "Error: " . mysqli_connect_error() . PHP_EOL;
	exit;
}
$THISuser = $_SESSION['user'];
$result = mysqli_query($connection, "Select * from messages WHERE recipient='$THISuser' OR user='$THISuser' Order by idk Desc Limit 15") or die ("DB error: $dbname");
print "<TABLE CELLPADDING=5 BORDER=1>";
print "<TR><TD>id</TD><TD>From</TD><TD>To</TD><TD>Date/Time</TD><TD>Message</TD></TR>\n";
while ($row = mysqli_fetch_array ($result))
{
	$id = $row[0];
	$date = $row[1];
	$message= $row[2];
	$user = $row[3];
	$recipient = $row[4];
	if(file_exists($message))
                {
                    $file = '';
                    $file = mime_content_type($message);
                    
                    if(strstr($file, "video")){
                        print "<TR><TD>$id</TD><TD>$user</TD><TD>$recipient</TD><TD>$date</TD><TD>
                            <video width='150' height='150' controls autoplay muted>
                            <source src='$message' type='video/mp4'>
                            Format not supported by browser.
                            </video></TD></TR>\n";
                    }
                    else if(strstr($file, "image"))
                    {
                        print "<TR><TD>$id</TD><TD>$user</TD><TD>$recipient</TD><TD>$date</TD><TD><img src='$message' class='Image' width='150' height='150'></TD></TR>\n";
                    }
                    else if(strstr($file, "audio"))
                    {
                        print "<TR><TD>$id</TD><TD>$user</TD><TD>$recipient</TD><TD>$date</TD><TD>
                            <audio controls autoplay muted>
                              <source src='$message' type='audio/wav'>
                              <source src='$message' type='audio/mpeg'>
                              Format not supported by browser.
                            </audio>
                        </TD></TR>\n";
                    }
                }
                else
                {
                    print "<TR><TD>$id</TD><TD>$user</TD><TD>$recipient</TD><TD>$date</TD><TD>$message</TD></TR>\n";
                }
}
print "</TABLE>";
mysqli_close($connection);
?>
