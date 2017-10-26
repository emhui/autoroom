package com.ycxy.ymh.autoroom.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by Y&MH on 2017-10-15.
 */

public class Person extends DataSupport{
    @Column(nullable = true)
    private int id;
    @Column(defaultValue = MD5)
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
