package com.ycxy.ymh.autoroom.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Y&MH on 2017-10-15.
 */

public class FloorBean extends DataSupport implements Serializable {
    /**
     * classroomName : 101
     * classroomId : 1
     * lampTotal : 9
     * lamp : 4
     */

    private String classroomName;
    @Column(unique = true)
    private int classroomId;
    private int lampTotal;
    private int lamp;

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getLampTotal() {
        return lampTotal;
    }

    public void setLampTotal(int lampTotal) {
        this.lampTotal = lampTotal;
    }

    public int getLamp() {
        return lamp;
    }

    public void setLamp(int lamp) {
        this.lamp = lamp;
    }

    @Override
    public String toString() {
        return "FloorBean{" +
                "classroomName='" + classroomName + '\'' +
                ", classroomId=" + classroomId +
                ", lampTotal=" + lampTotal +
                ", lamp=" + lamp +
                '}';
    }
}
