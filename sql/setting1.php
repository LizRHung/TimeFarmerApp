<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtCycle'])  ) { 
        $id = $_POST['txtID'];
        $cycle = $_POST['txtCycle'];
		
		
        
		$query = "UPDATE time SET workcycle = '$cycle' WHERE id =  '$id'";
		
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
            Cycle <input type="number" name="txtCycle" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>