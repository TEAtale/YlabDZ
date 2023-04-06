package org.example.Lesson4.persistentmap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Класс, методы которого надо реализовать 
 */
public class PersistentMapImpl implements PersistentMap {
  private DataSource dataSource;
  private Connection connection;
  private String name;
  public PersistentMapImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }
  @Override
  public void init(String name) {
    this.name = name;
    try {
      connection = dataSource.getConnection();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean containsKey(String key) throws SQLException {
    String sql = "SELECT * FROM persistent_map WHERE map_name = ? AND key = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1,name);
      statement.setString(2,key);
      ResultSet resultSet = statement.executeQuery();
      return resultSet.next();
    }
  }

  @Override
  public List<String> getKeys() throws SQLException {
    String sql = "SELECT key FROM persistent_map WHERE map_name = ?";
    List<String> keys = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        keys.add(resultSet.getString("key"));
      }
    }
    return keys;
  }

  @Override
  public String get(String key) throws SQLException {
    String sql = "SELECT value FROM persistent_map WHERE map_name = ? AND key = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, name);
      statement.setString(2, key);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getString("value");
      } else {
        return null;
      }
    }
  }

  @Override
  public void remove(String key) throws SQLException {
    String sql = "DELETE FROM persistent_map WHERE map_name = ? AND key = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, name);
      statement.setString(2, key);
      statement.executeUpdate();
    }
  }

  @Override
  public void put(String key, String value) throws SQLException {
    String sql = "DELETE FROM persistent_map WHERE map_name = ? AND key = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, name);
      statement.setString(2, key);
      statement.executeUpdate();
    }
    sql = "INSERT INTO persistent_map (map_name, key, value) VALUES (?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, name);
      statement.setString(2, key);
      statement.setString(3, value);
      statement.executeUpdate();
    }
  }

  @Override
  public void clear() throws SQLException {
    String sql = "DELETE FROM persistent_map WHERE map_name = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, name);
      statement.executeUpdate();
    }
  }
}
