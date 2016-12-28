<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txttime'])  ) { 
        $id = trim($_POST['txtID']);
		$time = trim($_POST['txttime']);
        
		$query = "UPDATE rank SET time = '$time' WHERE id = '$id'";
	
        $result = mysqli_query($conn, $query);
        
        if($result > 0){
			echo "Update Successfully";   
			exit;
		}
		else{
			echo "Something Error";   
		exit;
		} 
	}
?>
<html>
<head><title>Login</title></head>
    <body>
       
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            ID <input type="text" name="txtID" value="" /><br/>
			Time <input type="number" name="txttime" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>