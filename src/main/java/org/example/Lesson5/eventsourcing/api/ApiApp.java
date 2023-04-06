package org.example.Lesson5.eventsourcing.api;

import com.rabbitmq.client.*;
import org.example.Lesson4.RabbitMQUtil;

import java.nio.charset.StandardCharsets;

public class ApiApp {
  public static void main(String[] args) throws Exception {
    ConnectionFactory connectionFactory = initMQ();
    String exchangeName = "myExchange";
    String routingKey = "*";
    try (Connection connection = connectionFactory.newConnection();
         Channel channel = connection.createChannel()) {
      channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);

      for (int i = 10; i < 20; i++) {
        byte[] messageBodyBytes = ("test "+i).getBytes();
        channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);
        System.out.println("test " + i);
      }
    }
    // Тут пишем создание PersonApi, запуск и демонстрацию работы
  }

  private static ConnectionFactory initMQ() throws Exception {
    return RabbitMQUtil.buildConnectionFactory();
  }
}
