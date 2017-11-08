package tk.mybatis.springboot.dto;

import java.io.Serializable;

/**
 * Created by Dong_Liu
 * date：2017/11/8
 */
public class WeatherResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Weather data; // 消息数据
    private String status; // 消息状态
    private String desc; // 消息描述

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
