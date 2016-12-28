<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtStage']) && isset($_POST['txtTC'])  ) { 
        $id = trim($_POST['txtID']);
        $stage = trim($_POST['txtStage']);
		$tc = trim($_POST['txtTC']);
		
        
		$query = "UPDATE collect SET tomatoamount = '$tc', stage = '$stage' WHERE id = '$id'";
		$query2 = "UPDATE rank SET collect = '$tc' WHERE id = '$id'";
	
        $result = mysqli_query($conn, $query);
		$result2 = mysqli_query($conn, $query2);
        
        if($result > 0){
			echo "Update Successfully";   
		}
		else{
			echo "Something Error";   
		} 
		
		if($result2 > 0){
			echo "Update Successfully";   
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
            Stage <input type="number" name="txtStage" value="" /><br/>
			TomatoCount <input type="number" name="txtTC" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>