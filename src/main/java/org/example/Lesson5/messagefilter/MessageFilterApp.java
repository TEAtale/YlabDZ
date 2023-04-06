package org.example.Lesson5.messagefilter;

import com.rabbitmq.client.ConnectionFactory;
import org.example.Lesson4.DbUtil;
import org.example.Lesson4.RabbitMQUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;


public class MessageFilterApp {
    public static void main(String[] args) throws SQLException, IOException, TimeoutException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        MessageFilter messageFilter = applicationContext.getBean(MessageFilter.class);
        messageFilter.checkMessage();
    }
}
