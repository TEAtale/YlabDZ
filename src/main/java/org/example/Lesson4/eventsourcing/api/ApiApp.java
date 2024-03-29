package org.example.Lesson4.eventsourcing.api;

import com.rabbitmq.client.*;
import org.example.Lesson4.RabbitMQUtil;

public class ApiApp {
  public static void main(String[] args) throws Exception {
    ConnectionFactory connectionFactory = initMQ();
    String exchangeName = "myExchange";
    String routingKey = "*";
    try (Connection connection = connectionFactory.newConnection();
         Channel channel = connection.createChannel()) {
      channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);

      for (int i = 0; i < 10; i++) {
        byte[] messageBodyBytes = ("fuck off"+i).getBytes();
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
