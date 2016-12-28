<?PHP
    include_once("connection.php");

if(isset($_POST['txtid']) && isset($_POST['txtusername']) && 
   isset($_POST['txtpassword']) && isset($_POST['txtbirthday']) && isset($_POST['txtgender'])){
    $id = $_POST['txtid'];
    $username = $_POST['txtusername'];
    $password = $_POST['txtpassword'];
    $birthday = $_POST['txtbirthday'];
	$gender = $_POST['txtgender'];

    $query = "INSERT INTO users(id, username, password, birthday, gender )
    VALUES ('$id', '$username', '$password', '$birthday', '$gender')"; 
	
	$query2 = "INSERT INTO `time`(`id`, `workcycle`, `shortbreak`, `longbreak`, `breakcycle`, `remind`) VALUES ('$id',25,5,10,2,0)";
	
    $query3 = "INSERT INTO `collect`(`id`, `tomatoamount`, `stage`) VALUES ('$id',0,0)";
	
	$query4 = "INSERT INTO `rank`(`id`, `score`, `collect`, `time`) VALUES ('$id','0','0','0')";
	
    $result = mysqli_query($conn, $query);
	$result2 = mysqli_query($conn, $query2);
	$result3 = mysqli_query($conn, $query3);
	$result4 = mysqli_query($conn, $query4);

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
	
	if($result2 > 0){
        if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
            echo "success";
            exit;
        }
        echo "Insert time Successfully";   
    }
    else{
        if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
            echo "failed";
            exit;
        }
        echo " time Error";   
    }
	
	if($result3 > 0){
        if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
            echo "success";
            exit;
        }
        echo "Insert tomato Successfully";   
    }
    else{
        if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
            echo "failed";
            exit;
        }
        echo "tomato Error";   
    }
	
	if($result4 > 0){
        if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
            echo "success";
            exit;
        }
        echo "Insert rank Successfully";   
    }
    else{
        if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
            echo "failed";
            exit;
        }
        echo "rank Error";   
    }
}
    
?>
<html>

    <body>
        
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            ID <input type="text" name="txtid" value=""/><br/>
            Username <input type="text" name="txtusername" value=""/><br/>
            Password <input type="text" name="txtpassword" value=""/><br/>
            Birthday <input type="text" name="txtbirthday" value=""/><br/>
			Gender <input type="text" name="txtgender" value=""/><br/>
            <input type="submit" name="btnSubmit" value="Insert"/>
        </form>
    </body>
</html>