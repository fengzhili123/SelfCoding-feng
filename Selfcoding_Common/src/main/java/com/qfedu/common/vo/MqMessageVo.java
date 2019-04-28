package com.qfedu.common.vo;

/**
 * @Author: 冯志立
 * @Date: 2019/4/2 20:09
 */
public class MqMessageVo {
    private int type; //消息类型
    private long id; //唯一ID
    private Object data; //数据源

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
