<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtShort']) ) { 
        $id = $_POST['txtID'];
        $short = $_POST['txtShort'];
	
        
		$query = "UPDATE time SET shortbreak = '$short' WHERE id =  '$id'";
		
        $result = mysqli_query($conn, $query);
        
        if($result > 0){
			echo "Insert Successfully";   
		}
		else{
        echo "Something Error";   
		} 
	}
?>
<html>
<head><title>Login</title></head>
    <body>
       
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            ID <input type="text" name="txtID" value="" /><br/>
            Short <input type="number" name="txtShort" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>