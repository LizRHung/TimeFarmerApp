<?PHP
    include_once("connection.php");

if(isset($_POST['txtid']) && isset($_POST['txtfid']) ){
	   
    $id = $_POST['txtid'];
    $fid = $_POST['txtfid'];
    

    $query = "INSERT INTO friend(id, fid)
    VALUES ('$id', '$fid')"; 
	
	$query2 = "INSERT INTO friend(id, fid)
    VALUES ('$fid', '$id')"; 
	
    $result = mysqli_query($conn, $query);
	$result2 = mysqli_query($conn, $query2);
	

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
            FID <input type="text" name="txtfid" value=""/><br/>
            <input type="submit" name="btnSubmit" value="Insert"/>
        </form>
    </body>
</html>