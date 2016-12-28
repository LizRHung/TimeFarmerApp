<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtRemind']) ) { 
        $id = $_POST['txtID'];
        $remind = $_POST['txtRemind'];
	
        
		$query = "UPDATE time SET remind = '$remind' WHERE id =  '$id'";
		
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
            remind <input type="number" name="txtRemind" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>