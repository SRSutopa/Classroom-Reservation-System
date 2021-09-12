package crs.sanjana.models;

import crs.sanjana.controllers.PopupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupModel {
  public static void showErrorPop(String errorMessage, Stage parentStage)  {
    FXMLLoader fxmlLoader = new FXMLLoader(PopupModel.class.getResource("../scenes/ErrorPopup.fxml"));
    try {
      Pane  pane = (Pane) fxmlLoader.load();
      PopupController errorView = fxmlLoader.getController();
      errorView.setErrorPopup(errorMessage);
      Scene errorViewScene = new Scene(pane);
      Stage errorStage = new Stage();
      errorStage.setScene(errorViewScene);
      errorStage.initOwner(parentStage);
      errorStage.initModality(Modality.APPLICATION_MODAL);
      errorStage.setTitle("Error");
      Image image = new Image(PopupModel.class.getResourceAsStream("../resources/nsu_logo.png"));
      errorStage.getIcons().add(image);
      errorStage.setMaximized(false);
      errorStage.setResizable(false);
      errorStage.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void showPop(String popupMsg, Stage parentStage, String popupTitle)  {
    FXMLLoader fxmlLoader = new FXMLLoader(PopupModel.class.getResource("../scenes/SuccessPopup.fxml"));
    try {
      Pane  pane = (Pane) fxmlLoader.load();
      PopupController popupView = fxmlLoader.getController();
      popupView.setPopup(popupMsg);
      Scene popupViewScene = new Scene(pane);
      Stage popupStage = new Stage();
      popupStage.setScene(popupViewScene);
      popupStage.initOwner(parentStage);
      popupStage.initModality(Modality.APPLICATION_MODAL);
      popupStage.setTitle(popupTitle);
      Image image = new Image(PopupModel.class.getResourceAsStream("../resources/nsu_logo.png"));
      popupStage.getIcons().add(image);
      popupStage.setMaximized(false);
      popupStage.setResizable(false);
      popupStage.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void showDelPopup(String delPopMsg, Stage parentStage, String delPopupTitle)  {
    FXMLLoader fxmlLoader = new FXMLLoader(PopupModel.class.getResource("../scenes/DeletePopup.fxml"));
    try {
      Pane  pane = (Pane) fxmlLoader.load();
      PopupController delPopupView = fxmlLoader.getController();
      delPopupView.setPopup(delPopMsg);
      Scene delPopupViewScene = new Scene(pane);
      Stage delPopStage = new Stage();
      delPopStage.setScene(delPopupViewScene);
      delPopStage.initOwner(parentStage);
      delPopStage.initModality(Modality.APPLICATION_MODAL);
      delPopStage.setTitle(delPopupTitle);
      Image image = new Image(PopupModel.class.getResourceAsStream("../resources/nsu_logo.png"));
      delPopStage.getIcons().add(image);
      delPopStage.setMaximized(false);
      delPopStage.setResizable(false);
      delPopStage.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
