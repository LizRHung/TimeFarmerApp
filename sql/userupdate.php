<?PHP
    include_once("connection.php");

if(isset($_POST['txtid']) && isset($_POST['txtusername']) && isset($_POST['txtbirthday']) && isset($_POST['txtgender']) ){
    $id = $_POST['txtid'];
    $username = $_POST['txtusername'];
	$birthday = $_POST['txtbirthday'];
	$gender = $_POST['txtgender'];
	
    $query = "UPDATE users 
	SET username='$username', birthday='$birthday', gender='$gender' WHERE id='$id'";
    
    $result = mysqli_query($conn, $query);

    if($result > 0){
        if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
            echo "success";
            exit;
        }
        echo "Insert Successfully";   
    }
    else{
        if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
            echo "failed";
            exit;
        }
        echo "Something Error";   
    }
}
    
?>
<html>

    <body>
        
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            ID <input type="text" name="txtid" value=""/><br/>
            Username <input type="text" name="txtusername" value=""/><br/>
            Birthday <input type="text" name="txtbirthday" value=""/><br/>
			Gender <input type="text" name="txtgender" value=""/><br/>
            <input type="submit" name="btnSubmit" value="Insert"/>
        </form>
    </body>
</html>