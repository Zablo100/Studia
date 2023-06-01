<?php session_start(); 
if (!isset($_SESSION['loggedin']))
{
header('Location: index3.php');
exit();
}

$user = $_SESSION['user'];
$post = $_POST['post'];
$recipient = $_POST['recipient'];
if (IsSet($_POST['post']))
{
	
	$connection = mysqli_connect("localhost", "id19668897_buk", "I1A3ahT~98PU\zFB", "id19668897_z1");
	if (!$connection)
	{
		exit;
	}
	$result = mysqli_query($connection, "INSERT INTO messages (message, user, recipient) VALUES ('$post', '$user', '$recipient');") or die ("DB error");
	mysqli_close($connection);
}
header ('Location: komunikator.php');
?>