<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtID']) && isset($_POST['txtCycle']) && isset($_POST['txtShort']) && isset($_POST['txtLong']) && isset($_POST['txtInterval']) && isset($_POST['txtRemind']) && isset($_POST['txtScreen']) ) { 
        $id = $_POST['txtID'];
        $cycle = $_POST['txtCycle'];
		$short = $_POST['txtShort'];
		$long = $_POST['txtLong'];
		$interval = $_POST['txtInterval'];
		$remind = $_POST['txtRemind'];
		$screen = $_POST['txtScreen'];
		
        
		$query = "UPDATE time SET workcycle = '$cycle',
					shortbreak =  '$short',
					longbreak =  '$long',
					breakcycle =  '$interval',
					remind =  '$remind',
					locksreen = '$screen' WHERE id =  '$id'";
		
        $result = mysqli_query($conn, $query);
        
        if($result > 0){
			echo "Insert Successfully";   
		}
		else{
        echo "Something Error";   
		} 
	}
?>
<html>
<head><title>Login</title></head>
    <body>
       
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            ID <input type="text" name="txtID" value="" /><br/>
            Cycle <input type="number" name="txtCycle" value="" /><br/>
			short <input type="number" name="txtShort" value="" /><br/>
			long <input type="number" name="txtLong" value="" /><br/>
			interval <input type="number" name="txtInterval" value="" /><br/>
			remind <input type="number" name="txtRemind" value="" /><br/>
			screen <input type="boolean" name="txtScreen" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>