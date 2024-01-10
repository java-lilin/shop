package com.common.service;

import java.util.Map;

public interface RabbitMQService extends AmqpService{

    void sendMessage(String exchange,Object message, String routingKey, Map<String, Object> properties) throws Exception;

    void sendOrder(String exchange,Object order, String routingKey) throws Exception;

}
