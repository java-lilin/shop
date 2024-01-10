package com.common.service;

import java.util.Map;

public interface AmqpService {
    /**
     * 创建队列
     * @param queueName
     * @return
     */
    public int createQueue(String queueName);

    /**
     * 删除队列
     * @param queueName
     * @return
     */
    public int deleteQueue(String queueName);

    /**
     * 创建DirectExchange交换器
     * @param exchangeName
     * @return
     */
    public int createDirectExchange(String exchangeName);

    /**
     * 创建FanoutExchange交换器
     * @param exchangeName
     * @return
     */
    public int createFanoutExchange(String exchangeName);

    /**
     * 创建TopicExchange交换器
     * @param exchangeName
     * @return
     */
    public int createTopicExchange(String exchangeName);

    /**
     * 删除交换器
     * @param exchangeName
     * @return
     */
    public int deleteExchange(String exchangeName);

    /**
     * 队列与交换器绑定
     * @param queueName
     * @param exchangeName
     * @param routingKey
     * @param arguments
     */
    public int declareBinding(String queueName, String exchangeName, String routingKey, Map<String, Object> arguments);

    /**
     * 取消队列与交换器的绑定
     * @param queueName
     * @param exchangeName
     * @param routingKey
     * @param arguments
     * @return
     */
    public int removeBinding(String queueName, String exchangeName, String routingKey, Map<String, Object> arguments);


    public int send(String routingKey, Object object);

    public int send(String exchangeName, String routingKey, Object object);

    public void sendMessage(String exchange,Object message, String routingKey, Map<String, Object> properties) throws Exception;

    public void sendOrder(String exchange,Object order, String routingKey) throws Exception;

    public int createNoDurableQueue(String queueName);
}
