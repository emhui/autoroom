package com.ycxy.ymh.autoroom.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Y&MH on 2017-10-15.
 */

public class BuildingBean extends DataSupport implements Serializable {
    /**
     * buildingId : 1
     * buildingName : 二教楼东一
     * floorTotal : 7
     * floor : [[{"classroomName":"101","classroomId":1,"lampTotal":9,"lamp":4},{"classroomName":"102","classroomId":2,"lampTotal":9,"lamp":5},{"classroomName":"105","classroomId":7,"lampTotal":8,"lamp":3},{"classroomName":"106","classroomId":8,"lampTotal":8,"lamp":1},{"classroomName":"107","classroomId":9,"lampTotal":8,"lamp":2},{"classroomName":"108","classroomId":10,"lampTotal":8,"lamp":3},{"classroomName":"109","classroomId":11,"lampTotal":9,"lamp":4},{"classroomName":"110","classroomId":12,"lampTotal":8,"lamp":7},{"classroomName":"111","classroomId":13,"lampTotal":8,"lamp":2},{"classroomName":"112","classroomId":14,"lampTotal":9,"lamp":2},{"classroomName":"113","classroomId":15,"lampTotal":8,"lamp":1},{"classroomName":"114","classroomId":16,"lampTotal":8,"lamp":4},{"classroomName":"115","classroomId":17,"lampTotal":8,"lamp":0},{"classroomName":"116","classroomId":18,"lampTotal":9,"lamp":3},{"classroomName":"117","classroomId":19,"lampTotal":8,"lamp":0},{"classroomName":"118","classroomId":20,"lampTotal":8,"lamp":0},{"classroomName":"119","classroomId":21,"lampTotal":8,"lamp":1},{"classroomName":"120","classroomId":22,"lampTotal":8,"lamp":2}],[{"classroomName":"201","classroomId":23,"lampTotal":8,"lamp":0},{"classroomName":"202","classroomId":24,"lampTotal":9,"lamp":9},{"classroomName":"203","classroomId":25,"lampTotal":8,"lamp":3},{"classroomName":"204","classroomId":26,"lampTotal":8,"lamp":1},{"classroomName":"205","classroomId":27,"lampTotal":8,"lamp":2},{"classroomName":"206","classroomId":28,"lampTotal":8,"lamp":3},{"classroomName":"207","classroomId":29,"lampTotal":8,"lamp":4},{"classroomName":"208","classroomId":30,"lampTotal":8,"lamp":7}],[{"classroomName":"301","classroomId":31,"lampTotal":8,"lamp":2},{"classroomName":"302","classroomId":32,"lampTotal":8,"lamp":2},{"classroomName":"303","classroomId":33,"lampTotal":8,"lamp":1},{"classroomName":"304","classroomId":34,"lampTotal":8,"lamp":4},{"classroomName":"305","classroomId":35,"lampTotal":9,"lamp":0},{"classroomName":"306","classroomId":36,"lampTotal":9,"lamp":3},{"classroomName":"307","classroomId":37,"lampTotal":8,"lamp":0},{"classroomName":"308","classroomId":38,"lampTotal":8,"lamp":0},{"classroomName":"309","classroomId":39,"lampTotal":8,"lamp":1},{"classroomName":"310","classroomId":40,"lampTotal":8,"lamp":2},{"classroomName":"311","classroomId":41,"lampTotal":8,"lamp":0},{"classroomName":"312","classroomId":42,"lampTotal":8,"lamp":9},{"classroomName":"313","classroomId":43,"lampTotal":8,"lamp":3},{"classroomName":"314","classroomId":44,"lampTotal":8,"lamp":1},{"classroomName":"315","classroomId":45,"lampTotal":8,"lamp":2},{"classroomName":"316","classroomId":46,"lampTotal":8,"lamp":3},{"classroomName":"317","classroomId":47,"lampTotal":8,"lamp":4},{"classroomName":"318","classroomId":48,"lampTotal":8,"lamp":7}],[{"classroomName":"401","classroomId":49,"lampTotal":8,"lamp":2},{"classroomName":"402","classroomId":50,"lampTotal":8,"lamp":2},{"classroomName":"403","classroomId":51,"lampTotal":8,"lamp":1},{"classroomName":"404","classroomId":52,"lampTotal":8,"lamp":4},{"classroomName":"405","classroomId":53,"lampTotal":8,"lamp":0},{"classroomName":"406","classroomId":54,"lampTotal":9,"lamp":3},{"classroomName":"407","classroomId":55,"lampTotal":8,"lamp":0},{"classroomName":"408","classroomId":56,"lampTotal":8,"lamp":0},{"classroomName":"409","classroomId":57,"lampTotal":8,"lamp":1},{"classroomName":"410","classroomId":58,"lampTotal":8,"lamp":2},{"classroomName":"411","classroomId":59,"lampTotal":8,"lamp":0},{"classroomName":"412","classroomId":60,"lampTotal":8,"lamp":9},{"classroomName":"413","classroomId":61,"lampTotal":8,"lamp":3},{"classroomName":"414","classroomId":62,"lampTotal":8,"lamp":1},{"classroomName":"415","classroomId":63,"lampTotal":8,"lamp":2}],[{"classroomName":"501","classroomId":64,"lampTotal":8,"lamp":3},{"classroomName":"502","classroomId":65,"lampTotal":8,"lamp":4},{"classroomName":"503","classroomId":66,"lampTotal":8,"lamp":7},{"classroomName":"504","classroomId":67,"lampTotal":8,"lamp":2},{"classroomName":"505","classroomId":68,"lampTotal":8,"lamp":2},{"classroomName":"506","classroomId":69,"lampTotal":8,"lamp":1},{"classroomName":"507","classroomId":70,"lampTotal":8,"lamp":4},{"classroomName":"508","classroomId":71,"lampTotal":8,"lamp":0},{"classroomName":"509","classroomId":72,"lampTotal":9,"lamp":3},{"classroomName":"510","classroomId":73,"lampTotal":8,"lamp":0},{"classroomName":"511","classroomId":74,"lampTotal":8,"lamp":0},{"classroomName":"512","classroomId":75,"lampTotal":8,"lamp":1}],[{"classroomName":"601","classroomId":76,"lampTotal":8,"lamp":2},{"classroomName":"602","classroomId":77,"lampTotal":8,"lamp":0},{"classroomName":"603","classroomId":78,"lampTotal":8,"lamp":9},{"classroomName":"604","classroomId":79,"lampTotal":8,"lamp":3},{"classroomName":"605","classroomId":80,"lampTotal":8,"lamp":1},{"classroomName":"606","classroomId":81,"lampTotal":8,"lamp":2},{"classroomName":"607","classroomId":82,"lampTotal":8,"lamp":3},{"classroomName":"608","classroomId":83,"lampTotal":9,"lamp":4},{"classroomName":"609","classroomId":84,"lampTotal":8,"lamp":7},{"classroomName":"610","classroomId":85,"lampTotal":8,"lamp":2},{"classroomName":"611","classroomId":86,"lampTotal":8,"lamp":2},{"classroomName":"612","classroomId":87,"lampTotal":8,"lamp":1},{"classroomName":"613","classroomId":88,"lampTotal":8,"lamp":4},{"classroomName":"614","classroomId":89,"lampTotal":8,"lamp":0},{"classroomName":"615","classroomId":90,"lampTotal":9,"lamp":3}],[{"classroomName":"701","classroomId":91,"lampTotal":8,"lamp":0},{"classroomName":"702","classroomId":92,"lampTotal":8,"lamp":0},{"classroomName":"703","classroomId":93,"lampTotal":8,"lamp":1},{"classroomName":"704","classroomId":94,"lampTotal":8,"lamp":2},{"classroomName":"705","classroomId":95,"lampTotal":9,"lamp":0},{"classroomName":"706","classroomId":96,"lampTotal":8,"lamp":9},{"classroomName":"707","classroomId":97,"lampTotal":8,"lamp":3},{"classroomName":"708","classroomId":98,"lampTotal":8,"lamp":1},{"classroomName":"709","classroomId":99,"lampTotal":8,"lamp":2},{"classroomName":"710","classroomId":100,"lampTotal":8,"lamp":6}]]
     */

    @Column(unique = true)
    private int buildingId;
    private String buildingName;
    private int floorTotal;
    private List<List<FloorBean>> floor;

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getFloorTotal() {
        return floorTotal;
    }

    public void setFloorTotal(int floorTotal) {
        this.floorTotal = floorTotal;
    }

    public List<List<FloorBean>> getFloor() {
        return floor;
    }

    public void setFloor(List<List<FloorBean>> floor) {
        this.floor = floor;
    }
}
