<?php
include 'connect.php';
if(!empty($_POST['data'])){

    $level = $_POST['level'];
    $kanji = $_POST['kanji'];
    $furigana = $_POST['furigana'];
    $english = $_POST['english'];

    $connection = $mysqliConnection;
    try {
        $query = "INSERT INTO `dbjavacrud`.tbljapanesevocabulary (level, kanji, furigana, english) VALUES (?, ?, ?, ?)";
        $stmt = $mysqli->prepare($query);
        $stmt->bind_param("sssS", $level, $kanji, $furigana, $english);
        $stmt->execute();
        $stmt->close();
        echo "Data inserted successfully!";
    } catch (Exception $e) {
        echo 'Caught exception: ',  $e->getMessage(), "\n";
    }
}
?>