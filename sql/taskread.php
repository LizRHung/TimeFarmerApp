<?PHP
	include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtPN'])){ 
        $id = $_POST['txtID'];
		$pn = $_POST['txtPN'];
   
		$query = "SELECT * FROM task WHERE id = '$id' AND projectname = '$pn' ORDER BY no ASC";
        
        $result = mysqli_query($conn, $query);
        
        while($row = mysqli_fetch_assoc($result)){
			$data[] = $row;
		}
		echo json_encode($data);
		exit;
    } 
?>

<html>
<head></head>
    <body>
        <form action="<?PHP $_PHP_SELF ?>" method="post">
         ID <input type="text" name="txtID" value="" /><br/>
		 Projectname <input type="text" name="txtPN" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>