<?PHP
	include_once("connection.php"); 
    if( isset($_POST['txtID']) ){ 
        $id = $_POST['txtID'];
   
		$query = "SELECT * FROM project WHERE id = '$id' ORDER BY no ASC";
        
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
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>