<?php session_start();

$link = mysqli_connect("localhost", "id19668897_buk", "I1A3ahT~98PU\zFB", "id19668897_z1");
 if(!$link) { echo"Błąd: ". mysqli_connect_errno()." ".mysqli_connect_error(); }
 mysqli_query($link, "SET NAMES 'utf8'");
 
 
//  $result = mysqli_query($link, "SELECT * FROM goscieportalu"); 
//  //$rekord = mysqli_fetch_array($result);
//   while($row=mysqli_fetch_array($result)){
//      $list[] = $row;
// }
 
// foreach($list as $user){
//     echo $user['lang'];
//     echo "<BR/>";
// }

$ipaddress = $_SERVER["REMOTE_ADDR"];
function ip_details($ip) {
$json = file_get_contents ("http://ipinfo.io/{$ip}/geo");
$details = json_decode ($json);
return $details;
}

$details = ip_details($ipaddress);
$loc = $details -> loc;
$ip = $details -> ip;

include("/storage/ssd5/897/19668897/public_html/z2/Browser/src/Browser.php");
$browser = new Browser();
$platform = $browser->getPlatform();
$version = $browser->getVersion();
$browserName = $browser->getBrowser();
$info = $browserName;
$info .= " ";
$info .= $version;
$info .= " ";
$info .= $platform;

$screen = $_POST["res"];
$cscreen = $_POST["cscreen"];
$colors = $_POST["colors"];
$cookies = $_POST["cookies"];
$java = $_POST["java"];
$lang = $_POST["lang"];
$user = $_SESSION['user'];


$sql = "INSERT INTO goscieportalu5 (ipaddress, browser, screen, cscreen, colors, cookies, java, lang, username) VALUES 
('$ip', '$info', '$screen', '$cscreen', '$colors', '$cookies', '$java', '$lang', '$user')";
        if (mysqli_query($link , $sql)){
            mysqli_close($link); // zamknięcie połączenia z BD
            echo "Tak";
        }else{
            echo mysqli_error($link);
        }

?>