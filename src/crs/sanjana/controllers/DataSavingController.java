package crs.sanjana.controllers;

import crs.sanjana.models.DataModel;

import java.io.*;
import java.util.ArrayList;

public class DataSavingController {
  public static final String savingPath = "./data/data.bin";
  public static boolean serialize(String pathToFile , ArrayList<DataModel> personsList) {
    File personSerialized = new File(pathToFile);
    FileOutputStream fileOutputStream = null;
    ObjectOutputStream objectOutputStream = null;
    boolean success = false;
    try{
      fileOutputStream = new FileOutputStream(personSerialized);
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(personsList);
      success = true;
    } catch (Exception exception){
      success = false;
    }
    return success;
  }

  public static ArrayList<DataModel> deserialize(String pathToSerializedFile) {
    File fileToRead = new File(pathToSerializedFile);
    FileOutputStream fileOutputStream = null;
    ObjectOutputStream objectOutputStream = null;
    ArrayList<DataModel> person = null;
    try {
      FileInputStream fileInputStream = new FileInputStream(fileToRead);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      person = (ArrayList<DataModel>) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException deserializationException) {
      System.err.println(deserializationException.getMessage());
    }
    return person;
  }
}