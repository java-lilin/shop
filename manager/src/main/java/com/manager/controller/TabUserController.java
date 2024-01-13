package com.manager.controller;

import com.common.util.Json;
import com.manager.bean.dto.TabUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/11  17:08
 */
@Api("注册用户信息")
@RestController
@RequestMapping("user")
public class TabUserController {

    @ApiOperation("注册用户接口")
    @PostMapping("/register")
    public Json register(@RequestBody TabUserDto tabUserDto){

        return Json.success();
    }

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>(500);
        Map<String, String> hashMap = new HashMap<>(500);

        // 创建两个线程并启动它们
        Thread thread1 = new Thread(() -> {
            for (int index1 = 0; index1 < 1000; index1++) {
                concurrentHashMap.put(Integer.toString(index1), Integer.toBinaryString(index1));

                hashMap.put(Integer.toString(index1), Integer.toBinaryString(index1));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int index2 = 1000; index2 < 2000; index2++) {
                concurrentHashMap.put(Integer.toString(index2), Integer.toBinaryString(index2));

                hashMap.put(Integer.toString(index2), Integer.toBinaryString(index2));
            }
        });

        thread1.start();
        thread2.start();

        // 等待两个线程结束
        thread1.join();
        thread2.join();

        System.out.println("ConcurrentHashMap size: " + concurrentHashMap.size());
        System.out.println("HashMap size: " + hashMap.size());
    }

}
