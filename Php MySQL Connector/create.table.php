<?php
include 'connect.php';
$connection = $mysqliConnection;
try {
    $queryOne = "CREATE TABLE IF NOT EXISTS `dbjavacrud`.`tbluseraccount` (" .
                "`account_id` INT NOT NULL AUTO_INCREMENT," .
                "`username` VARCHAR(255) NOT NULL," .
                "`password` VARCHAR(255) NOT NULL," .
                "PRIMARY KEY (`account_id`)) ENGINE = InnoDB;";
    mysqli_query($connection, $queryOne);
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
    mysqli_query($connection, $queryTwo);
    $queryThree = "CREATE TABLE IF NOT EXISTS `dbjavacrud`.`tbljapanesevocabulary` (" .
                "`kanji_id` INT NOT NULL AUTO_INCREMENT," .
                "`level` VARCHAR(255) NOT NULL," .
                "`kanji` VARCHAR(255) NOT NULL," .
                "`furigana` VARCHAR(255) NOT NULL," .
                "`english` VARCHAR(255) NOT NULL," .
                "PRIMARY KEY (`kanji_id`)," .
                "UNIQUE (`kanji`)" .  // Add UNIQUE constraint to kanji column
                ") ENGINE = InnoDB;";
    mysqli_query($connection, $queryThree);
} catch (Exception $e) {
    echo 'Caught exception: ',  $e->getMessage(), "\n";
}
?>