<?PHP
    include_once("connection.php");

if(isset($_POST['txtid']) && isset($_POST['txtpname']) && isset($_POST['txttname']) && 
	isset($_POST['txtttime']) && isset($_POST['txtttype']) && isset($_POST['txttholder'])){
	   
    $id = $_POST['txtid'];
    $projectname = $_POST['txtpname'];
    $taskname = $_POST['txttname'];
	$tasktime = $_POST['txtttime'];
    $timetype = $_POST['txtttype'];
	$taskholder = $_POST['txttholder'];

    $query = "INSERT INTO task(id, projectname, taskname, tasktime, timetype, taskholder)
    VALUES ('$id', '$projectname', '$taskname', '$tasktime', '$timetype', '$taskholder')"; 
	
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
            ProjectName <input type="text" name="txtpname" value=""/><br/>
            Taskname <input type="text" name="txttname" value=""/><br/>
			TaskTime <input type="text" name="txtttime" value=""/><br/>
			TimeType <input type="text" name="txtttype" value=""/><br/>
            Taskholder <input type="text" name="txttholder" value=""/><br/>
            <input type="submit" name="btnSubmit" value="Insert"/>
        </form>
    </body>
</html>