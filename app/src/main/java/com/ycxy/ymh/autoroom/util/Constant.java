package com.ycxy.ymh.autoroom.util;

/**
 * Created by Y&MH on 2017-10-12.
 */

public class Constant {
    public static final String CLASSROOM = "classroom";
    public static final String BUILDING = "building";
    public static final String BUILDINGPOSITION = "buildingposition";
    public static final String USERNAMEANDPASSWORD = "usernameandpassword";
    public static String URLLOGIN = "http://211.159.187.11:8080/classroomTest/Login";
    public static String URLREGISTER = "http://211.159.187.11:8080/classroomTest/Resight";
    public static String USERNAME = "username";
    public static String BUILDINGJSON = "buildings";
    // type = 1 关掉整层教学楼 buildingId
    public static String CLOSEALLBUILDIGN = "http://211.159.187.11:8080/classroomTest/CloseLamp?type=1&buildingId=";
    // type = 2  `关掉整层教学楼 buildingId=4&floorNum=
    public static String FLOORNUM = "&floorNum=";
    public static String CLOSEALLLAYER = "http://211.159.187.11:8080/classroomTest/CloseLamp?type=1&buildingId=4&floorNum=";
    // type = 3 关掉单层教室 classroomId
    public static String CLOSECLASSROOM = "http://211.159.187.11:8080/classroomTest/CloseLamp?type=3&classroomId=";

    public static String LOCALNETIP = "192.168.43.70";
    public static int LOCALNETPORT = 1010;
    public static String CMDCLOSE = "GCR200/r/n";
    public static String CMDOPEN = "GCR201/r/n";
}
