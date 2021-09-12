package crs.sanjana.models;
import exception.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Formatter;

public class DataModel implements Serializable {

  private String facultyInit;
  private String email;
  private String ID;
  private String department;
  private String semester;
  private int studentList;
  private String purpose;
  private String classroomName;
  private String courseCode;
  private LocalDate localDate;
  private String profilePath;

  public DataModel(String facultyInit, String email, String ID, String email1, String department,
                   String semester, int studentList, String purpose, String classroomName, LocalDate localDate, String courseCode, String profilePath) throws Exception {
    this.setEmail(email);
    this.setFacultyInit(facultyInit);
    this.setID(ID);
    this.setDepartment(department);
    this.setSemester(semester);
    this.setStudentList(studentList);
    this.setPurpose(purpose);
    this.setClassroomName(classroomName);
    this.setLocalDate(localDate);
    this.setCourseCode(courseCode);
    this.setProfilePath(profilePath);
  }


  public String getFacultyInit() {
    return facultyInit;
  }
  public void setFacultyInit(String facultyInit) throws Exception {
    if (facultyInit.length() > 4 || facultyInit.length() == 0 ) {
      throw new InvalidFacultyInitial("Invalid Faculty Initial");
    }
    this.facultyInit = facultyInit;
  }

  public String getID() {
    return ID;
  }

  public void setID(String ID) throws Exception {
    if (ID.equals("")){
      throw new InvalidID("Invalid ID");
    }
    else if (ID.length() < 7 || ID.length() > 10) {
      throw new InvalidID("Invalid ID");
    }
    this.ID = ID;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) throws Exception {
    this.department = department;
  }

  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) throws Exception {
    if (semester.equals("")){
      throw new InvalidSemester("Invalid Semester");
    } else if(!semester.equals("Summer 2021")){
      throw  new InvalidSemester("Invalid Semester");
    }
    this.semester = semester;
  }

  public int getStudentList() {
    return studentList;
  }

  public void setStudentList(int studentList) throws Exception {
    if ((studentList < 15) || (studentList > 45)) {
      throw new InvalidStudentList("Invalid Student List");
    }
    this.studentList = studentList;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) throws Exception {
    this.purpose = purpose;
  }

  public String getClassroomName() {
    return classroomName;
  }

  public void setClassroomName(String classroomName) throws Exception {
    if (classroomName.equals("")){
      throw new InvalidClassroom("Invalid Classroom");
    }
    this.classroomName = classroomName;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) throws Exception {
    //Holiday List
    String[] holidays = new String[]{
            "2021-08-15",//National Mourning Day
            "2021-08-20",//Ashura
            "2021-08-30",//Krishna Janmashtami
            "2021-10-15",//Vijayadashami
            "2021-10-19",//Prophet's Birthday
            "2021-12-16",//Victory Day
            "2021-12-25",//Christmas Day
            "2022-02-21",//Language Movement Day
            "2022-03-17",//Sheikh Mujibur Rahman's Birthday
            "2022-03-26",//Independence Day
            "2022-04-30",//Shab-e-Barat
            "2022-04-14",//Bengali New Year
            "2022-03-29",//Jumu'atul-Wida & Shab-e-Qadr
            "2022-05-01",//May Day
            "2022-05-03",// }
            "2022-05-04",// Eid al-Fitr
            "2022-05-05",// }
            "2022-05-16",//Buddha Purnima
            "2022-07-10",//Eid al-Adha
            "2022-08-09",//Ashura
            "2022-08-15",//National Mourning Day
            "2022-08-19",//Krishna Janmashtami
            "2022-10-05",//Vijayadashami
            "2022-10-08",//Prophet's Birthday
            "2022-12-16",//Victory Day
            "2022-12-25",//Christmas Day
    };
    List<String> list = Arrays.asList(holidays);// Convert String Array to List
    //Date Validation
    if (localDate.equals(null)){
      throw new InvalidDate("Invalid Date");
    } else if(localDate.isBefore(LocalDate.now()) ){
      throw  new InvalidDate("Can't select past dates");
    } else if (list.contains(String.valueOf(localDate))){
      throw new InvalidDate("Can't be booked: Public Holiday");
    }
    this.localDate = localDate;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) throws Exception {
    if (courseCode.equals("")){
      throw new InvalidCourseCode("Invalid Course Code");
    }
    this.courseCode = courseCode;
  }

  public String getProfilePath() {
    return profilePath;
  }

  public void setProfilePath(String profilePath) throws Exception {
    if (profilePath.equals(null)){
      throw new InvalidProfilePicture("Invalid Profile picture");
    }
    this.profilePath = profilePath;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) throws Exception {
    if (email.equals("")){
      throw new InvalidEmail("Invalid Email");
    }
    else if (email.contains("@northsouth.edu")) {
      this.email = email;
    }else{
      throw new InvalidEmail("Invalid Email");
    }
  }

  @Override
  public String toString() {
    Formatter formatter = new Formatter(new StringBuilder());
    formatter.format("%s %s %s %s %s %s %s %s %s %s %s",this.facultyInit,this.email,this.ID,this.department,this.semester,this.courseCode,this.studentList,
      this.purpose,this.classroomName,this.localDate,this.profilePath);
    return formatter.toString();
  }
}
