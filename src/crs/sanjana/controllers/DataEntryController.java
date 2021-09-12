package crs.sanjana.controllers;

import crs.sanjana.models.PopupModel;
import crs.sanjana.models.ReservationModel;
import crs.sanjana.models.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class DataEntryController implements Serializable {

  @FXML // fx:id="facultyInitialTextField"
  private TextField facultyInitialTextField; // Value injected by FXMLLoader

  @FXML // fx:id="emailTextField"
  private TextField emailTextField; // Value injected by FXMLLoader

  @FXML // fx:id="facultyIDTextField"
  private TextField facultyIDTextField; // Value injected by FXMLLoader

  @FXML // fx:id="departmentCombo"
  private ComboBox<String> departmentCombo; // Value injected by FXMLLoader

  @FXML // fx:id="semesterCombo"
  private ComboBox<String> semesterCombo;  // Value injected by FXMLLoader

  @FXML // fx:id="totalStudentTextField"
  private TextField totalStudentTextField; // Value injected by FXMLLoader

  @FXML // fx:id="purposeSelectionField"
  private ComboBox<String> purposeSelectionField; // Value injected by FXMLLoader

  @FXML // fx:id="saveButton"
  private Button saveButton; // Value injected by FXMLLoader

  @FXML // fx:id="clearButton"
  private Button clearButton; // Value injected by FXMLLoader

  @FXML
  private ComboBox<String> classRoomCombo;

  @FXML // fx:id="bookingDatePicker"
  private DatePicker bookingDatePicker; // Value injected by FXMLLoader

  @FXML
  private ComboBox<String> courseCodeCombo;

  @FXML // fx:id="chooseProfilePhotoButton"
  private Button chooseProfilePhotoButton; // Value injected by FXMLLoader

  @FXML // fx:id="chosenProfileImage"
  private ImageView chosenProfileImage; // Value injected by FXMLLoader

  @FXML // fx:id="informationListView"
  private ListView<DataModel> informationListView; // Value injected by FXMLLoader

  @FXML // fx:id="editDetailsButton"
  private Button editDetailsButton; // Value injected by FXMLLoader

  @FXML // fx:id="viewDetailsButton"
  private Button viewDetailsButton; // Value injected by FXMLLoader

  @FXML
  private Button deleteButton;

  private String profilePath = null;

  private ArrayList<DataModel> facultyArrayList = null;

  private ObservableList<DataModel> facultyObservableList = null;

  private int indexOfSelectedPerson = -1;

  private void updateProfilePicture(){
    Image image = new Image("file://" + this.profilePath);
    this.chosenProfileImage.setImage(image);
  }

  @FXML
  void handleChooseProfileButtonClicked(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = (Stage) this.chooseProfilePhotoButton.getScene().getWindow();
    File selectedFile = fileChooser.showOpenDialog(primaryStage);
    if (selectedFile != null) {
      String selectedFilePath = selectedFile.toURI().getPath();
      this.profilePath = selectedFilePath;
      this.updateProfilePicture();
    }
  }

  @FXML

  void handleSaveButtonClicked(ActionEvent event) {
    String facultyInitial = this.facultyInitialTextField.getText();
    String email1 = this.emailTextField.getText();
    String facultyID = this.facultyIDTextField.getText();
    String department = this.departmentCombo.getSelectionModel().getSelectedItem();
    String currentSemester = this.semesterCombo.getSelectionModel().getSelectedItem();
    String studentList = this.totalStudentTextField.getText();
    String purpose = this.purposeSelectionField.getSelectionModel().getSelectedItem();
    String classRoomName = this.classRoomCombo.getSelectionModel().getSelectedItem();
    LocalDate bookingDate = this.bookingDatePicker.getValue();
    String course = this.courseCodeCombo.getSelectionModel().getSelectedItem();
    String pathToProfile = this.profilePath;
    try {
      DataModel faculty = new DataModel(facultyInitial, email1, facultyID, email1, department, currentSemester, Integer.parseInt(studentList),
              purpose, classRoomName, bookingDate,course, pathToProfile);
      if(this.indexOfSelectedPerson != -1){
        this.facultyObservableList.set(this.indexOfSelectedPerson, faculty);
        this.informationListView.refresh();
        resetFields();
        Stage primaryStage = (Stage) this.saveButton.getScene().getWindow();
        PopupModel.showPop("Reservation Updated",primaryStage, "Updated");
      } else{
        this.facultyObservableList.add(faculty);
        System.out.println(faculty);
        resetFields();
        Stage primaryStage = (Stage) this.saveButton.getScene().getWindow();
        PopupModel.showPop("Reservation Saved",primaryStage, "Saved");
      }
      boolean save = DataSavingController.serialize(DataSavingController.savingPath, this.facultyArrayList);
      if (!save) {
        throw new Exception("Invalid");
      }
    } catch (Exception exception) {
      Stage primaryStage = (Stage) this.saveButton.getScene().getWindow();
      PopupModel.showErrorPop(exception.getMessage(),primaryStage);
    }
  }
  public void resetFields(){
    this.facultyInitialTextField.setText("");
    this.emailTextField.setText("");
    this.facultyIDTextField.setText("");
    this.totalStudentTextField.setText("");
    this.bookingDatePicker.setValue(null);
    this.bookingDatePicker.setPromptText("Select Date");
    this.profilePath = null;
    this.chosenProfileImage.setImage(null);
  }

  @FXML
  void handleClearButtonClicked(ActionEvent event) {
    this.facultyInitialTextField.setText("");
    this.emailTextField.setText("");
    this.facultyIDTextField.setText("");
    this.totalStudentTextField.setText("");
    this.bookingDatePicker.setValue(null);
    this.profilePath = null;
    this.chosenProfileImage.setImage(null);

    //Now Show The Clear Modal ⬇
    Stage primaryStage = (Stage) this.saveButton.getScene().getWindow();
    primaryStage.show();
    PopupModel.showPop("Cleared All Fields",primaryStage, "Cleared!");

  }

  @FXML
  void handleEditButtonClicked(ActionEvent event) {
    this.indexOfSelectedPerson = this.informationListView.getSelectionModel().getSelectedIndex();
    if(this.indexOfSelectedPerson != -1){
      DataModel savedFaculty = this.informationListView.getItems().get(this.indexOfSelectedPerson);
      String facultyInitial = savedFaculty.getFacultyInit();
      String email1 = savedFaculty.getEmail();
      String facultyID = savedFaculty.getID();
      String department = savedFaculty.getDepartment();
      String currentSemester = savedFaculty.getSemester();
      String studentList = String.valueOf(savedFaculty.getStudentList());
      String purpose = savedFaculty.getPurpose();
      String classRoomName = savedFaculty.getClassroomName();
      LocalDate bookingDate = savedFaculty.getLocalDate();
      String courseCode = savedFaculty.getCourseCode();
      String pathToProfile = savedFaculty.getProfilePath();

      this.updateUIWithSavedPerson(facultyInitial,email1,facultyID,department,currentSemester,studentList,purpose,classRoomName,bookingDate,courseCode, pathToProfile);

    }
  }
  private void updateUIWithSavedPerson(String facultyInitial,String email1,String facultyID,String department, String currentSemester,String studentList,
                                       String purpose,String classRoomName,LocalDate bookingDate,String course, String pathToProfile){

    this.facultyInitialTextField.setText(facultyInitial);
    this.emailTextField.setText(email1);
    this.facultyIDTextField.setText(facultyID);
    this.totalStudentTextField.setText(studentList);

    this.departmentCombo.setSelectionModel(departmentCombo.getSelectionModel());    //Set Department
    this.departmentCombo.getSelectionModel().select(department);    //Select Department

    this.semesterCombo.setSelectionModel(semesterCombo.getSelectionModel());    //Set Semester
    this.semesterCombo.getSelectionModel().select(currentSemester); //Get Semester

    this.purposeSelectionField.setSelectionModel(purposeSelectionField.getSelectionModel()); // Set Purpose
    this.purposeSelectionField.getSelectionModel().select(purpose); //Get Purpose

    this.classRoomCombo.setSelectionModel(classRoomCombo.getSelectionModel());// Set Classroom
    this.classRoomCombo.getSelectionModel().select(classRoomName); //Get Classroom

    this.courseCodeCombo.setSelectionModel(courseCodeCombo.getSelectionModel());// Set Course
    this.courseCodeCombo.getSelectionModel().select(course); //Get Course

    this.bookingDatePicker.setValue(bookingDate);
    this.updateProfilePicture();
    Image image = new Image("file://" + pathToProfile);
    this.chosenProfileImage.setImage(image);
    this.profilePath = pathToProfile;
  }
  //Handle Delete Option
  @FXML
  void handleDeleteButton(ActionEvent event) {
    this.indexOfSelectedPerson = this.informationListView.getSelectionModel().getSelectedIndex();
    if(this.indexOfSelectedPerson != -1){
      DataModel savedFaculty = this.informationListView.getItems().remove(this.indexOfSelectedPerson);
      facultyObservableList.remove(savedFaculty);
      DataSavingController.serialize(DataSavingController.savingPath, this.facultyArrayList);
      this.informationListView.refresh();
      //Now Show The Update Modal ⬇
      Stage primaryStage = (Stage) this.saveButton.getScene().getWindow();
      PopupModel.showDelPopup("Reservation Deleted",primaryStage, "Deleted");
    }
  }

  //View & Handle Single Reservation
  @FXML
  void handleViewButtonClicked(ActionEvent event) {
    this.indexOfSelectedPerson = this.informationListView.getSelectionModel().getSelectedIndex();
    if(this.indexOfSelectedPerson != -1){
      DataModel faculty = this.informationListView.getItems().get(this.indexOfSelectedPerson);
      try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/ResDetailsScene.fxml"));
        Pane pane = (Pane) fxmlLoader.load();
        ReservationModel reservationModel = fxmlLoader.getController();
        reservationModel.transferFacultyObject(faculty);
        Scene detailViewScene = new Scene(pane);
        Stage primaryStage = (Stage) this.viewDetailsButton.getScene().getWindow();
        primaryStage.setScene(detailViewScene);
        primaryStage.setTitle("Classroom Reservation Detail of: " + faculty.getFacultyInit());
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.show();
      } catch (Exception exception){
        Stage primaryStage = (Stage) this.viewDetailsButton.getScene().getWindow();
        PopupModel.showErrorPop(exception.getMessage(),primaryStage);
      }
    }
  }

  @FXML
  void handleListView(MouseEvent event) {}

  @FXML // fx:id="showMeDate"
  private Label showMeDate; //Main Screen Date Label

  //initialize
  public void initialize() {

    //Set Date in Main Screen
    SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd MMM, yyyy");
    Date todayDate = new Date();
    this.showMeDate.setText(String.valueOf(formatter.format(todayDate)));

    //Load Department List
    List < String > department = Arrays.asList(
            "Department of Electrical and Computer Engineering",
            "Department of Civil and Environmental Engineering",
            "Department of Architecture",
            "Department of Mathematics & Physics",
            "Department of Accounting & Finance",
            "Department of Economics",
            "Department of Management",
            "Department of Marketing & International Business",
            "Department of English & Modern Languages",
            "Department of Political Science & Sociology",
            "Department of Law",
            "Department of History & Philosophy",
            "Department of Biochemistry and Microbiology",
            "Department of Environmental Science & Management",
            "Department of Pharmaceutical Sciences",
            "Department of Public Health"
    );

    ObservableList<String> departmentObservableList = FXCollections.observableArrayList(department);
    this.departmentCombo.setItems(departmentObservableList);

    //Load Semester List
    List<String> semesterList = Arrays.asList(
            "Spring 2021",
            "Summer 2021",
            "Fall 2021"
    );
    ObservableList<String> semesterObservableList = FXCollections.observableArrayList(semesterList);
    this.semesterCombo.setItems(semesterObservableList);

    //Load Class Purpose List
    List<String> purposeOption = Arrays.asList(
            "Mid-Exam",
            "Final-Exam",
            "Makeup-Class"
    );
    ObservableList<String> purposeObservableList = FXCollections.observableArrayList(purposeOption);
    this.purposeSelectionField.setItems(purposeObservableList);
    //Load Course List
    File fileToRead = new File("data/courseData.bin");
    ArrayList<String> course = new ArrayList<>();
    try (Scanner fileReader = new Scanner(fileToRead)) {
      while (fileReader.hasNext()) {
        course.add(fileReader.next());
        ObservableList<String> courseObservableList = FXCollections.observableArrayList(course);
        this.courseCodeCombo.setItems(courseObservableList);
      }
    } catch (FileNotFoundException fileNotFoundException) {
      System.err.println(fileNotFoundException.getMessage());
    }
    //Load Classroom List
    File fileToReadClassroom = new File("data/classroomData.bin");
    ArrayList<String> classroom = new ArrayList<>();
    try (Scanner fileReader = new Scanner(fileToReadClassroom)) {
      while (fileReader.hasNext()) {
        classroom.add(fileReader.next());
        ObservableList<String> classroomObservableList = FXCollections.observableArrayList(classroom);
        this.classRoomCombo.setItems(classroomObservableList);
      }
    } catch (FileNotFoundException fileNotFoundException) {
      System.err.println(fileNotFoundException.getMessage());
    }

    try{
      this.facultyArrayList = DataSavingController.deserialize(DataSavingController.savingPath);
    } catch (Exception exception){
      System.out.println(exception.getMessage());
    }
    if (this.facultyArrayList == null) {
      this.facultyArrayList = new ArrayList<>();
    }
    this.facultyObservableList = FXCollections.observableList(facultyArrayList);
    this.informationListView.setItems(facultyObservableList);
  }
}
