package org.hiphap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class was used during development for easily writing error messages to log files.
 * Such functionality was needed because the system console was used for the user interface,
 * thus making debugging sometimes difficult. The DEBUG_MODE flag should be set to false in
 * production.
 */
public class Logger {
  private static Logger instance;
  private final boolean DEBUG_MODE = false;
  private File logFile;
  private PrintWriter writer;

  private Logger() {
    if (DEBUG_MODE) {
      try {
        String path = "logs";
        File directory = new File(path);
        directory.mkdir();
        LocalDateTime moment = LocalDateTime.now();
        String timeStamp = moment.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        this.logFile = new File(path.concat(File.separator).concat(timeStamp).concat(".log"));
        this.writer = new PrintWriter(new BufferedWriter(new FileWriter(logFile)));
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
        this.writer = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)));
        writer.println(message);
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
