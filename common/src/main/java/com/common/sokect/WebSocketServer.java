package com.common.sokect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/12  9:32
 */

@ServerEndpoint("/websocket/{id}")
@Component
public class WebSocketServer {

    private static Log log = LogFactory.getLog(WebSocketServer.class);
    // 当前在线连接数,要求线程安全。
    private static int count = 0;
    // concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    // 与某个客户端的连接会话通过它来给客户端发送数据，
    private Session session;
    // 每个连接的连接标识
    private String id = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        this.session = session;
        webSocketSet.add(this);
        addCount();
        this.id = id;
        log.info("有一连接打开！当前连接数为" + getCount());
        try {
            sendMessage("connect success");
        } catch (IOException e) {
            log.error("websocket exception: " , e);
        }
    }

    /**
     *   连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subCount();
        log.info("有一连接关闭！当前连接数为" + getCount());
    }

    /**
     *       收到客户端（前端）消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info(id + " , message:" + message);
        // 群发消息
        for (WebSocketServer socket : webSocketSet) {
            try {
                socket.sendMessage(message);
            } catch (IOException e) {
                log.error("websocket exception: " , e);
            }
        }
    }

    /**
     *   异常
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("websocket exception: " , error);
    }

    /**
     *      服务器端主动推送信息到前端
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendMessage(String id , String message) throws IOException {
        log.info(id + " , message:" + message);
        for (WebSocketServer socket : webSocketSet) {
            if (socket.id.equals(id)) {
                socket.sendMessage(message);
            }
        }
    }

    public static synchronized int getCount() {
        return count;
    }

    public static synchronized void addCount() {
        WebSocketServer.count++;
    }

    public static synchronized void subCount() {
        WebSocketServer.count--;
    }
}