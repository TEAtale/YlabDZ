package org.example.Lesson4.movie;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

public class MovieLoaderImpl implements MovieLoader {
  private DataSource dataSource;
  public MovieLoaderImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void loadData(File file) {
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      conn = dataSource.getConnection();

      String sql = "INSERT INTO movie (year, length, title, subject, actors, actress, director, popularity, awards) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      stmt = conn.prepareStatement(sql);

      BufferedReader reader = new BufferedReader(new FileReader(file));
      reader.readLine();
      reader.readLine();
      String line;
      while ((line = reader.readLine()) != null) {
        String[] fields = line.split(";");

        Movie movie = new Movie();
        movie.setYear(fields[0].isEmpty() ? null : Integer.parseInt(fields[0]));
        movie.setLength(fields[1].isEmpty() ? null : Integer.parseInt(fields[1]));
        movie.setTitle(fields[2]);
        movie.setSubject(fields[3]);
        movie.setActors(fields[4]);
        movie.setActress(fields[5]);
        movie.setDirector(fields[6]);
        movie.setPopularity(fields[7].isEmpty() ? null : Integer.parseInt(fields[7]));
        movie.setAwards(fields[8].equals("YES"));

        stmt.setObject(1, movie.getYear(), java.sql.Types.INTEGER);
        stmt.setObject(2, movie.getLength(), java.sql.Types.INTEGER);
        stmt.setString(3, movie.getTitle());
        stmt.setString(4, movie.getSubject());
        stmt.setString(5, movie.getActors());
        stmt.setString(6, movie.getActress());
        stmt.setString(7, movie.getDirector());
        stmt.setObject(8, movie.getPopularity(), java.sql.Types.INTEGER);
        stmt.setBoolean(9, movie.getAwards());

        stmt.executeUpdate();
      }
      reader.close();

    } catch (SQLException | IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmt != null) stmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (conn != null) conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}


