package org.example.Lesson4.eventsourcing.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.rabbitmq.client.*;
import org.example.Lesson4.DbUtil;
import org.example.Lesson4.RabbitMQUtil;

public class DbApp {

  public static void main(String[] args) throws Exception {
    DataSource dataSource = initDb();
    java.sql.Connection conn = dataSource.getConnection();
    ConnectionFactory connectionFactory = initMQ();
    String queueName = "myQueue1";
    Connection connection = connectionFactory.newConnection();
         Channel channel = connection.createChannel();
      channel.queueDeclare(queueName, false, false, false, null);
      System.out.println("Waiting for messages...");
      Consumer consumer = new DefaultConsumer(channel) {
        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
          String message = new String(body, StandardCharsets.UTF_8);
          System.out.println("Received message: " + message);
          /*try {
            assert false;
            PreparedStatement stmt = conn.prepareStatement(message);
            stmt.executeUpdate();
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }*/
          // Здесь можно написать код для обработки полученного сообщения
        }
      };
      channel.basicConsume(queueName, true, consumer);
    // тут пишем создание и запуск приложения работы с БД
  }

  private static ConnectionFactory initMQ() throws Exception {
    return RabbitMQUtil.buildConnectionFactory();
  }
  
  private static DataSource initDb() throws SQLException {
    String ddl = "" 
                     + "drop table if exists person;" 
                     + "create table if not exists person (\n"
                     + "person_id bigint primary key,\n"
                     + "first_name varchar,\n"
                     + "last_name varchar,\n"
                     + "middle_name varchar\n"
                     + ")";
    DataSource dataSource = DbUtil.buildDataSource();
    DbUtil.applyDdl(ddl, dataSource);
    return dataSource;
  }
}
