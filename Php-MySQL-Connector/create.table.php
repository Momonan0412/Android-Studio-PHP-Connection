<?php
include 'connect.php';
$connection = $mysqliConnection;
try {
    $queryOne = "CREATE TABLE IF NOT EXISTS `dbjavacrud`.`tbluseraccount` (" .
                "`account_id` INT NOT NULL AUTO_INCREMENT," .
                "`username` VARCHAR(255) NOT NULL," .
                "`password` VARCHAR(255) NOT NULL," .
                "PRIMARY KEY (`account_id`)) ENGINE = InnoDB;";
    $resultOne = mysqli_query($connection, $queryOne);
    $queryTwo = "CREATE TABLE IF NOT EXISTS `dbjavacrud`.`tbluserprofile` (" .
                "`profile_id` INT NOT NULL AUTO_INCREMENT," .
                "`account_id` INT NOT NULL," .
                "`firstname` VARCHAR(255) NOT NULL," .
                "`lastname` VARCHAR(255) NOT NULL," .
                "`gender` VARCHAR(10) NOT NULL," .
                "`email` VARCHAR(255) NOT NULL," .
                "`student_id` VARCHAR(255) NOT NULL," .
                "`prog_languages` VARCHAR(255) NOT NULL," .
                "PRIMARY KEY (`profile_id`)," .
                "FOREIGN KEY (`account_id`) REFERENCES `tbluseraccount`(`account_id`) " .
                "ON DELETE CASCADE" .
                ") ENGINE = InnoDB;";
    $resultTwo = mysqli_query($connection, $queryTwo);
    $queryThree = "CREATE TABLE IF NOT EXISTS `dbjavacrud`.`tbljapanesevocabulary` (" .
                "`kanji_id` INT NOT NULL AUTO_INCREMENT," .
                "`level` VARCHAR(255) NOT NULL," .
                "`kanji` VARCHAR(255) NOT NULL," .
                "`furigana` VARCHAR(255) NOT NULL," .
                "`english` VARCHAR(255) NOT NULL," .
                "PRIMARY KEY (`kanji_id`)," .
                "UNIQUE (`kanji`)" .  // Add UNIQUE constraint to kanji column
                ") ENGINE = InnoDB;";
    $resultThree = mysqli_query($connection, $queryThree);
    
    // Check the result of each query and add status messages to the response array
    if($resultOne !== false){
        $response['messageOne'] = "Table user account created successfully";
    } else {
        $response['messageOne'] = "Error creating table user account: " . mysqli_error($connection);
    }
    
    if($resultTwo !== false){
        $response['messageTwo'] = "Table user profile created successfully";
    } else {
        $response['messageTwo'] = "Error creating table user profile: " . mysqli_error($connection);
    }
    
    if($resultThree !== false){
        $response['messageThree'] = "Table japanese vocabulary created successfully";
    } else {
        $response['messageThree'] = "Error creating table japanese vocabulary: " . mysqli_error($connection);
    }
    
    // Set overall success status based on all table creation results
    $response['success'] = ($resultOne !== false && $resultTwo !== false && $resultThree !== false);
} catch (Exception $e) {
    // Exception occurred, set error message in response
    $response['success'] = false;
    $response['errorMessage'] = "An error occurred: " . $e->getMessage();
}
// Encode response array to JSON and echo it
echo json_encode($response);
?>