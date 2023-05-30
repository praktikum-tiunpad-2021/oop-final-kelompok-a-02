module Puzzle {
   	requires javafx.controls;
    	requires javafx.fxml;
	opens Puzzle to javafx.fxml;
	exports Puzzle;
}