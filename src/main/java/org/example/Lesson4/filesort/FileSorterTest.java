package org.example.Lesson4.filesort;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.example.Lesson4.DbUtil;

public class FileSorterTest {
  public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
    DataSource dataSource = initDb();
    File data = new File("data.txt");
    FileSorter fileSorter = new FileSortImpl(dataSource);
    File res = fileSorter.sort(data);
    System.out.println(res.getAbsolutePath());
  }
  public static DataSource initDb() throws SQLException {
    String createSortTable = "" 
                                 + "drop table if exists numbers;" 
                                 + "CREATE TABLE if not exists numbers (\n"
                                 + "\tval bigint\n"
                                 + ");";
    DataSource dataSource = DbUtil.buildDataSource();
    DbUtil.applyDdl(createSortTable, dataSource);
    return dataSource;
  }
}
