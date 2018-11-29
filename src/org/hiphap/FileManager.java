package org.hiphap;

import java.io.*;

public class FileManager {
  public static Object loadBinaryDataFromFile(String filename) {
    FileInputStream fileInputStream;
    try {
      fileInputStream = new FileInputStream(filename);
    } catch (FileNotFoundException e) {
      return null;
    }
    try {
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Object result = objectInputStream.readObject();
      objectInputStream.close();
      return result;
    } catch (IOException | ClassNotFoundException e) {
      // Very small chances of this happening; we won't handle this.
    }
    return null;
  }

  public static void saveBinaryDataToFile(String filename, Object object) {
    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;
    try {
      fileOutputStream = new FileOutputStream(filename);
      try {
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
      } catch (IOException e) {
        Logger.getInstance().write(e.toString());
        // some chances that this might happen; we don't handle this in our case;
      }
    } catch (FileNotFoundException e) {
      // can't really happen
    }
  }
}
