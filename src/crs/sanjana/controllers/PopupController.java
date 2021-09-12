package crs.sanjana.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopupController {
  @FXML
  private Label errorModalLabel;

  @FXML
  private Label modalLabel;

  @FXML
  private Label deletePopupLabel;

  @FXML
  private Button closeButton;

  @FXML
  void handleCloseButton(ActionEvent event) {
    Stage currentStage = (Stage) this.closeButton.getScene().getWindow();
    currentStage.close();
  }

  public void setErrorPopup(String errorPopupMassage){
    if(errorPopupMassage.startsWith("For input string")){
      this.errorModalLabel.setText("Error: Empty field(s)");

    } else {
      this.errorModalLabel.setText(errorPopupMassage);

    }
  }
  public void setPopup(String modalMessage){
    if(modalMessage.startsWith("For input string")){
      this.modalLabel.setText("418: I'm a teapot😹");

    } else {
      this.modalLabel.setText(modalMessage);

    }
  }

  public void setDeletePopup(String delPopupMessage){
    if(delPopupMessage.startsWith("For input string")){
      this.deletePopupLabel.setText("418: I'm a teapot😹");

    } else {
      this.deletePopupLabel.setText(delPopupMessage);

    }
  }
}

