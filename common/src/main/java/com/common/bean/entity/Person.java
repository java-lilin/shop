package com.common.bean.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/16  15:39
 */
@XmlRootElement
public class Person {
    private String name;
    private int age;

    // 必须提供无参数构造函数
    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
