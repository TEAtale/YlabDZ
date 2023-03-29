package org.example.Lesson4.filesort;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.util.Arrays;
import javax.sql.DataSource;

public class FileSortImpl implements FileSorter {
  private final DataSource dataSource;

  public FileSortImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public File sort(File data) throws IOException, SQLException {
    // read data from file
    long[] longs = readDataFromFile(data);

    // sort data using database
    sortDataUsingDB(longs);

    // write sorted data to new file

    return writeDataToFile(longs);
  }
  private long[] readDataFromFile(File dataFile) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(dataFile));
    long dataSize = Files.lines(dataFile.toPath()).count();
    long[] data = new long[(int) dataSize];
    String line;
    int i = 0;
      while ((line = reader.readLine()) != null) {
        data[i++] = Long.parseLong(line);
      }
    reader.close();
    return Arrays.copyOf(data, i); // trim array to actual size
  }

  private void sortDataUsingDB(long[] data) throws SQLException {

    // establish connection to database
    Connection conn = dataSource.getConnection();

    // prepare batch insert statement
    PreparedStatement ps = conn.prepareStatement("INSERT INTO numbers (val) VALUES (?)");
    for (long value : data) {
      ps.setLong(1, value);
      ps.addBatch();
    }

    // execute batch insert statement
    ps.executeBatch();

    // sort data using SQL query
    ps = conn.prepareStatement("SELECT val FROM numbers ORDER BY val DESC");
    ResultSet rs = ps.executeQuery();
    int i = 0;
    while (rs.next()) {
      data[i++] = rs.getLong("val");
    }
    // close database resources
    rs.close();
    ps.close();
    conn.close();
  }
  private File writeDataToFile(long[] data) throws IOException {
    File file = File.createTempFile("sorted_data", ".txt");
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    for (long value : data) {
      writer.write(Long.toString(value));
      writer.newLine();
    }
    writer.close();

    return file;
  }
}
