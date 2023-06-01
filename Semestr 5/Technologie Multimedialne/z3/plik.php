<?php declare(strict_types=1); // włączenie typowania zmiennych w PHP >=7
session_start(); // zapewnia dostęp do zmienny sesyjnych w danym pliku
if (!isset($_SESSION['loggedin']))
{
header('Location: index3.php');
exit();
}
$user = $_SESSION['user'];
print "Zalogowano jako $user
<br></br>";


$connection = mysqli_connect("localhost", "id19668897_buk", "I1A3ahT~98PU\zFB", "id19668897_z1");
if (!$connection)
{
	echo " MySQL Connection error." . PHP_EOL;
	echo "Errno: " . mysqli_connect_errno() . PHP_EOL;
	echo "Error: " . mysqli_connect_error() . PHP_EOL;
	exit;
}
?>

<form action='nPlik.php' method='post' enctype='multipart/form-data'>
Select file to upload:
<input type='file' name='fileToUpload' id='fileToUpload'><br>
<label for='recipient' class='form-label'>Odbiorca:</label>
<select class='form-control' name='recipient' id='recipient'>";
<?php $result = mysqli_query($connection, "Select username from users3 Where username!='$user'") or die ('DB error');
while($row = mysqli_fetch_array ($result)){
    print "<option value='$row[0]'>$row[0]</option>";
}?>
</select>
<input type='submit' value='Upload' name='submit'>
</form><br>

<form action='komunikator.php' method='POST'>
 <input type='submit' value='Back'>
</form>