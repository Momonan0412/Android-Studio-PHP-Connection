<?php
include 'connect.php';
if (!empty($_POST['data'])) {
    $level = $_POST['level'];
    $kanji = $_POST['kanji'];
    $furigana = $_POST['furigana'];
    $english = $_POST['english'];
    try {
        $query = "INSERT INTO `dbjavacrud`.`tbljapanesevocabulary` (level, kanji, furigana, english) VALUES (?, ?, ?, ?)";
        $stmt = $mysqliConnection->prepare($query);
        $stmt->bind_param("ssss", $level, $kanji, $furigana, $english);
        $stmt->execute();
        $stmt->close();

        // Set success message in response
        $response['success'] = true;
        $response['message'] = "Data inserted successfully!";
    } catch (Exception $e) {
        // Exception occurred, set error message in response
        $response['success'] = false;
        $response['message'] = "An error occurred: " . $e->getMessage();
    }
} else {
    // No data received, set error message in response
    $response['success'] = false;
    $response['message'] = "No data received";
}

// Encode response array to JSON and echo it
echo json_encode($response);
?>
