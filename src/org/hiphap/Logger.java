package org.hiphap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
  private static Logger instance;
  private final boolean DEBUG_MODE = true;
  private File logFile;
  private BufferedWriter writer;

  private Logger() {
    if (DEBUG_MODE) {
      try {
        String path = "logs";
        File directory = new File(path);
        directory.mkdir();
        LocalDateTime moment = LocalDateTime.now();
        String timeStamp = moment.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        this.logFile = new File(path.concat(File.separator).concat(timeStamp).concat(".log"));
        this.writer = new BufferedWriter(new FileWriter(logFile));
        writer.write("Execution log started on " + moment.toLocalDate().
            format(DateTimeFormatter.ofPattern("dd LLLL yyyy")));
        writer.write(", at " + moment.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        writer.write("\n----------\n");
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          if (writer != null) {
            writer.close();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static Logger getInstance() {
    if (instance == null) {
      instance = new Logger();
    }
    return instance;
  }

  public void write(String message) {
    if (DEBUG_MODE) {
      try {
        this.writer = new BufferedWriter(new FileWriter(logFile));
        message += "\n";
        writer.append(message);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          writer.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
