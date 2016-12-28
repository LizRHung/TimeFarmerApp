<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtFID']) ) { 
        $id = $_POST['txtID'];
        $fid = $_POST['txtFID'];
        
			
		$query = "DELETE FROM friend WHERE id = '$id' AND fid = '$fid'";
		
		$query2 = "DELETE FROM friend WHERE id = '$fid' AND fid = '$id'";
		
		
		$result = mysqli_query($conn, $query);
		$result2 = mysqli_query($conn, $query2);
		
		
		if($result > 0){
			if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){
				echo "success";
				exit;
			}
			echo "Delete Project Successfully";   
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
			echo "Delete Project Successfully";   
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
            ID <input type="text" name="txtID" value="" /><br/>
            FID <input type="text" name="txtFID" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>