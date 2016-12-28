<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtInterval']) ) { 
        $id = $_POST['txtID'];
        $interval = $_POST['txtInterval'];
	
        
		$query = "UPDATE time SET breakcycle = '$interval' WHERE id =  '$id'";
		
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
            interval <input type="number" name="txtInterval" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>