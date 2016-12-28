<?PHP
	session_start();
	include_once("connection.php"); 
    if( isset($_POST['txtID'])){ 
        $id = $_POST['txtID'];
   
		$query = "SELECT * FROM `friend` WHERE id = '$id'";
        
        $result = mysqli_query($conn, $query);
        
        while($row = mysqli_fetch_assoc($result)){
			$data[] = $row[fid];
			
			$query2 = "SELECT * FROM `rank` WHERE id = '$row[fid]' ORDER BY score DESC";
			
			$result2 = mysqli_query($conn, $query2);

			while($row2 = mysqli_fetch_assoc($result2)){
				$data2[] = $row2;
			}
		}
		
		$score = array();
		foreach ($data2 as $key => $row3)
		{
			$score[$key] = $row3['score'];
		}
		array_multisort($score, SORT_DESC, $data2);
		
		echo json_encode($data2);
		exit;
    } 
?>

<html>
<head></head>
    <body>
        <form action="<?PHP $_PHP_SELF ?>" method="post">
         ID <input type="text" name="txtID" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>