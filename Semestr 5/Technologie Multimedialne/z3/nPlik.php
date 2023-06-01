<?php session_start();
if (!isset($_SESSION['loggedin']))
{
header('Location: index3.php');
exit();
}

$connection = mysqli_connect("localhost", "id19668897_buk", "I1A3ahT~98PU\zFB", "id19668897_z1");
if (!$connection)
{
	exit;
}

$user = $_SESSION['user'];
$recipient = $_POST['recipient'];
$target_dir = "users/$user";
$target_file = $target_dir . "/". basename($_FILES["fileToUpload"]["name"]);
if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file))
 { 

	$connection = mysqli_connect("localhost", "id19668897_buk", "I1A3ahT~98PU\zFB", "id19668897_z1");
	if (!$connection)
	{
		echo " MySQL Connection error." . PHP_EOL;
		echo "Errno: " . mysqli_connect_errno() . PHP_EOL;
		echo "Error: " . mysqli_connect_error() . PHP_EOL;
		exit;
	}
	$result = mysqli_query($connection, "INSERT INTO messages (message, user, recipient) VALUES ('$target_file', '$user', '$recipient');") or die ("DB error");
	mysqli_close($connection);

 }
 else { echo "Error uploading file."; }

 header('Location: komunikator.php');

?>
