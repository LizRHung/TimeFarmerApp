<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtPN']) ) { 
        $id = $_POST['txtID'];
        $pn = $_POST['txtPN'];
        
			$query = "DELETE FROM project WHERE id = '$id' AND projectname = '$pn'"; 
			
			$query2 = "DELETE FROM task WHERE id = '$id' AND projectname = '$pn'";
		
		
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
			echo "Delete Task Successfully";   
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
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>