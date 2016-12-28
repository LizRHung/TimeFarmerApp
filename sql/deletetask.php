<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtPN']) && isset($_POST['txtTN']) ) { 
        $id = $_POST['txtID'];
        $pn = $_POST['txtPN'];
		$tn = $_POST['txtTN'];
        
			
		$query = "DELETE FROM task WHERE id = '$id' AND projectname = '$pn' AND taskname='$tn'";
		
		
		$result = mysqli_query($conn, $query);
		
		
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
		
    } 
?>
<html>
    <body>
       
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            ID <input type="text" name="txtID" value="" /><br/>
            ProjectName <input type="text" name="txtPN" value="" /><br/>
			TaskName <input type="text" name="txtTN" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>