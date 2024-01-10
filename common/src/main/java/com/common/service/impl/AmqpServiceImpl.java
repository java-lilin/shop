package com.common.service.impl;

import com.common.service.AmqpService;
import com.common.service.RabbitMQService;
import com.common.util.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @ClassName AmqpServiceImpl
 * @Description rmq服务类
 * @Author admin
 * @Date 2020/3/1820:24
 * @Version 1.0
 */

@Component(value="amqpService")
@Slf4j
public class AmqpServiceImpl implements AmqpService, RabbitMQService {
    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;



    /**
     * 创建队列
     *
     * @param queueName
     * @return
     */
    @Override
    public int createQueue(String queueName) {
        int ret = 0;
        try{
            amqpAdmin.declareQueue(new Queue(queueName));
        }catch (Exception e){
            ret = -1;
        }
        return ret;
    }

    /**
     * 删除队列
     *
     * @param queueName
     * @return
     */
    @Override
    public int deleteQueue(String queueName) {
        int ret = 0;
        try{
            amqpAdmin.deleteQueue(queueName);
        }catch (Exception e){
            ret = -1;
        }
        return ret;
    }

    /**
     * 创建DirectExchange交换器
     *
     * @param exchangeName
     * @return
     */
    @Override
    public int createDirectExchange(String exchangeName) {
        int ret = 0;
        try{
            amqpAdmin.declareExchange(new DirectExchange(exchangeName));
        }catch (Exception e){
            ret = -1;
        }
        return ret;
    }

    /**
     * 创建FanoutExchange交换器
     *
     * @param exchangeName
     * @return
     */
    @Override
    public int createFanoutExchange(String exchangeName) {
        int ret = 0;
        try{
            amqpAdmin.declareExchange(new FanoutExchange(exchangeName));
        }catch (Exception e){
            ret = -1;
        }
        return ret;
    }

    /**
     * 创建TopicExchange交换器
     *
     * @param exchangeName
     * @return
     */
    @Override
    public int createTopicExchange(String exchangeName) {
        int ret = 0;
        try{
            amqpAdmin.declareExchange(new TopicExchange(exchangeName));
        }catch (Exception e){
            ret = -1;
        }
        return ret;
    }

    /**
     * 删除交换器
     *
     * @param exchangeName
     * @return
     */
    @Override
    public int deleteExchange(String exchangeName) {
        int ret = 0;
        try{
            amqpAdmin.deleteExchange(exchangeName);
        }catch (Exception e){
            ret = -1;
        }
        return ret;
    }

    /**
     * 队列与交换器绑定
     *
     * @param queueName
     * @param exchangeName
     * @param routingKey
     * @param arguments
     */
    @Override
    public int declareBinding(String queueName, String exchangeName, String routingKey, Map<String, Object> arguments) {
        int ret = 0;
        try{
            amqpAdmin.declareBinding(new Binding(queueName,Binding.DestinationType.QUEUE,exchangeName,routingKey,arguments));
        }catch (Exception e){
            ret = -1;
        }
        return ret;
    }

    /**
     * 取消队列与交换器的绑定
     *
     * @param queueName
     * @param exchangeName
     * @param routingKey
     * @param arguments
     * @return
     */
    @Override
    public int removeBinding(String queueName, String exchangeName, String routingKey, Map<String, Object> arguments) {
        int ret = 0;
        try{
            amqpAdmin.removeBinding(new Binding(queueName,Binding.DestinationType.QUEUE,exchangeName,routingKey,arguments));
        }catch (Exception e){
            ret = -1;
        }
        return ret;
    }

    /**
     * 发送普通消息
     * @param routingKey 路由键
     * @param object 消息内容
     * @return
     */
    @Override
    public int send(String routingKey, Object object) {
        int ret = 0;
        try{
            amqpTemplate.convertAndSend(routingKey,object);
        }catch (Exception e){
            ret = -1;
        }
        return ret;
    }

    /**
     * 通过交换器发送消息
     * @param exchangeName 交换器名称
     * @param routingKey 路由/发送消息的规则，选填
     * @param object 消息内容
     * @return
     */
    @Override
    public int send(String exchangeName, String routingKey, Object object) {
        int ret = 0;
        try{
            amqpTemplate.convertAndSend(exchangeName,routingKey,object);
        }catch (Exception e){
            ret = -1;
        }
        return ret;
    }

    //回调函数: confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData: 发送确认 " + correlationData);
            log.info("ack: " + ack);
            if(!ack){
                log.info(Const.IM_GROUP_CONST.ERROR);
            }
        }
    };

    //回调函数: return返回
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode,
                                    String replyText, String exchange, String routingKey) {
            log.info("return exchange: {}, routingKey: {}, replyCode: {}, replyText: {}",
                    exchange, routingKey, replyCode, replyText);
        }
    };

    @Override
    public void sendMessage(String exchange,Object message, String routingKey, Map<String, Object> properties) throws Exception {
        Message msg = new Message(message.toString().getBytes("UTF8"),new MessageProperties());
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一
        String id = UUID.randomUUID().toString();
        log.info("sendMessage send id: {}", id);
        CorrelationData correlationData = new CorrelationData(id);
        rabbitTemplate.convertAndSend(exchange, routingKey, msg, correlationData);
    }

    @Override
    public void sendOrder(String exchange,Object order, String routingKey) throws Exception {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一
        String id = UUID.randomUUID().toString();
        log.info("sendOrder id: {}", id);
        CorrelationData correlationData = new CorrelationData(id);
        rabbitTemplate.convertAndSend(exchange, routingKey, order, correlationData);
    }

    @Override
    public int createNoDurableQueue(String queueName) {
        int ret = 0;
        try{
            amqpAdmin.declareQueue(new Queue(queueName,false));
        }catch (Exception e){
            ret = -1;
        }
        return ret;
    }
}
