package org.hiphap;

/**
 * Any class implementing this interface is expected to return a single line {@link String}
 * object containing the CVS formatted data within a particular object of that class
 * <p>
 * NOTE: For this particular project we use the pipe symbol '|' as a separator, to avoid
 * issues with, for example, commas appearing in addresses, etc.
 */
public interface CsvPrintable {
  String getCsvString();
}
