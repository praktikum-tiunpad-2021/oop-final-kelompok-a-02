package Puzzle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DialogController {

    @FXML
    private Button closeBtn;

    @FXML
    private Button shuffleBtn;

    @FXML
    void handleCloseBtn(ActionEvent event) {
        GameController.stage.close();
    }

    @FXML
    void handleShuffleBtn(ActionEvent event) {
        GameController.stage.close();
        GameController.board.shuffle();
    }

}
