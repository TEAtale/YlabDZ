package org.example.Lesson4.persistentmap;

import java.sql.SQLException;
import javax.sql.DataSource;

import org.example.Lesson4.DbUtil;

public class PersistenceMapTest {
  public static void main(String[] args) throws SQLException {
    DataSource dataSource = initDb();
    PersistentMap persistentMap = new PersistentMapImpl(dataSource);
    persistentMap.init("map1");
    persistentMap.put("1", "one");
    persistentMap.put("2", "two");
    persistentMap.put("3", "three");
    if (persistentMap.containsKey("1")){
    System.out.println(persistentMap.get("1"));}
    persistentMap.remove("2");
    System.out.println(persistentMap.getKeys());// Написать код демонстрации работы
    persistentMap.clear();
  }
  public static DataSource initDb() throws SQLException {
    String createMapTable = "" 
                                + "drop table if exists persistent_map; " 
                                + "CREATE TABLE if not exists persistent_map (\n"
                                + "   map_name varchar,\n"
                                + "   KEY varchar,\n"
                                + "   value varchar\n"
                                + ");";
    DataSource dataSource = DbUtil.buildDataSource();
    DbUtil.applyDdl(createMapTable, dataSource);
    return dataSource;
  }
}
