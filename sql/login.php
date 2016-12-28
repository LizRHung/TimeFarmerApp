<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtPassword']) ) { 
        $id = $_POST['txtID'];
        $password = $_POST['txtPassword'];
        
       $query = "SELECT * FROM users WHERE id = '$id' 
        AND password = '$password'";
        
        $result = mysqli_query($conn, $query);
        
        if($result->num_rows > 0){ 
			echo "success";
			exit;
		}
		else{
			echo "Wrong id and password"; 
			exit;
		}
    } 
?>
<html>
<head><title>Login</title></head>
    <body>
       
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            ID <input type="text" name="txtID" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>