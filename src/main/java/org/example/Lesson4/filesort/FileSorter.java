package org.example.Lesson4.filesort;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
public interface FileSorter {
  File sort(File data) throws IOException, SQLException, ClassNotFoundException;
}
