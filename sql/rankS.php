<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtscore'])) { 
        $id = trim($_POST['txtID']);
        $score = trim($_POST['txtscore']);
        
		$query = "UPDATE rank SET score = '$score' WHERE id = '$id'";
	
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
            Score <input type="number" name="txtscore" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>