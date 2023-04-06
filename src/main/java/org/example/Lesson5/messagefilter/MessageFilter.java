package org.example.Lesson5.messagefilter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public interface MessageFilter {
    void checkMessage() throws SQLException, IOException, TimeoutException;
    void sendMessage(String message) throws SQLException;


}
