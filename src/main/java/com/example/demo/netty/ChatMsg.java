package com.example.demo.netty;

import java.io.Serializable;

/**
 * websocket 定义消息
 * Created by 张帆 on 2019/11/22.
 */
public class ChatMsg implements Serializable {

    private static final long serialVersionUID = -1311691171844999654L;

    private String kitchenId;

    private String type;

    private String partyId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(String kitchenId) {
        this.kitchenId = kitchenId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
}
