<?PHP
    include_once("connection.php");

if(isset($_POST['txtid']) && isset($_POST['txtname']) && 
   isset($_POST['txttime']) && isset($_POST['txttimetype']) && isset($_POST['txttype'])){
	   
    $id = $_POST['txtid'];
    $projectname = $_POST['txtname'];
    $projecttime = $_POST['txttime'];
	$timetype = $_POST['txttimetype'];
    $projecttype = $_POST['txttype'];

    $query = "INSERT INTO project(id, projectname, projecttime, timetype,  projecttype)
    VALUES ('$id', '$projectname', '$projecttime', '$timetype', '$projecttype')"; 
	
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
            ProjectName <input type="text" name="txtname" value=""/><br/>
            ProjectTime <input type="text" name="txttime" value=""/><br/>
			TimeType <input type="text" name="txttimetype" value=""/><br/>
            ProjectType <input type="text" name="txttype" value=""/><br/>
            <input type="submit" name="btnSubmit" value="Insert"/>
        </form>
    </body>
</html>