<?PHP
    include_once("connection.php");

if(isset($_POST['txtid']) && isset($_POST['txtpn']) && isset($_POST['txttn']) && isset($_POST['txtth']) && isset($_POST['txttt']) ){
    $id = $_POST['txtid'];
    $pn = $_POST['txtpn'];
	$tn = $_POST['txttn'];
	$th = $_POST['txtth'];
	$tt = $_POST['txttt'];
	
    $query = "UPDATE task 
	SET tasktime='$tt', taskholder='$th' WHERE id='$id' AND projectname='$pn' AND taskname='$tn'";
    
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
            PN <input type="text" name="txtpn" value=""/><br/>
            TN <input type="text" name="txttn" value=""/><br/>
			TH <input type="text" name="txtth" value=""/><br/>
			TT <input type="text" name="txttt" value=""/><br/>
            <input type="submit" name="btnSubmit" value="Insert"/>
        </form>
    </body>
</html>