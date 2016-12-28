<?PHP
    include_once("connection.php");

if(isset($_POST['txtid']) && isset($_POST['txtworktime']) && isset($_POST['txtproject']) && 
	isset($_POST['txttask']) && isset($_POST['txtyear']) && isset($_POST['txtmonth']) && 
	isset($_POST['txtday']) && isset($_POST['txtweek']) && isset($_POST['txthour'])){
	   
    $id = $_POST['txtid'];
    $worktime = $_POST['txtworktime'];
    $project = $_POST['txtproject'];
	$task = $_POST['txttask'];
    $year = $_POST['txtyear'];
	$month = $_POST['txtmonth'];
	$day = $_POST['txtday'];
    $week = $_POST['txtweek'];
	$hour = $_POST['txthour'];

    $query = "INSERT INTO timedone(id, worktime, project, task, year, month, day, week, hour)
    VALUES ('$id', '$worktime', '$project', '$task', '$year', '$month', '$day', '$week', '$hour')"; 
	
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
            worktime <input type="text" name="txtworktime" value=""/><br/>
            project <input type="text" name="txtproject" value=""/><br/>
			Task <input type="text" name="txttask" value=""/><br/>
			year <input type="text" name="txtyear" value=""/><br/>
            month <input type="text" name="txtmonth" value=""/><br/>
			day <input type="text" name="txtday" value=""/><br/>
			week <input type="text" name="txtweek" value=""/><br/>
            hour <input type="text" name="txthour" value=""/><br/>
            <input type="submit" name="btnSubmit" value="Insert"/>
        </form>
    </body>
</html>