package com.common.controller;

import com.common.sokect.WebSocketServer;
import io.github.kylinhunter.commons.uid.UidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/12  9:40
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class TestControllerForCommon {

    //触发WebSocket，向前端发送数据
    @RequestMapping("/push/{id}")
    public String push(@PathVariable String id, String message) {
        try {
            WebSocketServer.sendMessage(id , message);
            return "success" ;
        } catch (IOException e) {
            log.debug("异常信息:"+e);
        }
        return "failed" ;
    }

    public static void main(String[] args) {
        UidGenerator worker = new UidGenerator(3, 4, 1);
        for (int i = 0; i < 10; i++) {
            long nextId = worker.nextId();
            System.out.println(nextId + "=>" + worker.parse(nextId));
        }
    }
}
