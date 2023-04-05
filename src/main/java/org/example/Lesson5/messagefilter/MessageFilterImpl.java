package org.example.Lesson5.messagefilter;

import com.rabbitmq.client.*;
import org.example.Lesson4.DbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeoutException;

@Component
public class MessageFilterImpl implements MessageFilter{

    private final Config config;

    @Autowired
    public MessageFilterImpl(Config config) {
        this.config = config;
    }


    @Override
    public void checkMessage() throws SQLException {
        ConnectionFactory factory = config.connectionFactory();
        DataSource dataSource = initDb();
        DatabaseMetaData metaData = config.dataSource().getConnection().getMetaData();
        String queueName = "input";
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
            System.out.println("Waiting for messages...");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("Received message: " + message);
                String[] words = message.split("\\W");
                for (int i = 0; i < words.length; i++) {
                    BufferedReader reader = new BufferedReader(new FileReader("NewWords.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (words[i].equals(line)) {
                            char first = words[i].charAt(0);
                            char last = words[i].charAt(words[i].length() - 1);
                            String stars = words[i].substring(1, words[i].length() - 1).replaceAll("[a-zA-Z]", "*");
                            words[i] = Character.toString(first) + stars + Character.toString(last);
                            message = message.replaceAll(line, words[i]);
                        }
                    }
                }
                sendMessage(message);
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(String message) {
        ConnectionFactory factory = config.connectionFactory();
        String queueName = "output";
        String exchangeName = "myExchange1";
        String routingKey = "*";
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
            byte[] messageBodyBytes = message.getBytes();
            channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);
            System.out.println("Sent message" + message);
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public DataSource initDb() throws SQLException {
        String createMapTable = ""
                + "drop table if exists message_filter; "
                + "CREATE TABLE if not exists message_filter (\n"
                + "   id bigserial NOT NULL,\n"
                + "   word varchar \n"
                + ");";
        DataSource dataSource = config.dataSource();
        try (java.sql.Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createMapTable);
        }
        return dataSource;
    }
}
