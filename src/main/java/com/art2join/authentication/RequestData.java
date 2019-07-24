package com.art2join.authentication;

import java.io.Serializable;


/**
 * 网络请求参数
 */
public class RequestData implements Serializable {

    private String md5; // 就算的md5数值

    private String uuid; // 请求的uuid

    private String timeStamp; // 计算的时间戳

    private String orgUid; // 机构的uuid


    @Override
    public String toString() {
        return "RequestData{" +
                "md5='" + md5 + '\'' +
                ", uuid='" + uuid + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", orgUid='" + orgUid + '\'' +
                '}';
    }

    public RequestData() {
    }

    public RequestData(String md5, String uuid, String timeStamp, String orgUid) {
        this.md5 = md5;
        this.uuid = uuid;
        this.timeStamp = timeStamp;
        this.orgUid = orgUid;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getOrgUid() {
        return orgUid;
    }

    public void setOrgUid(String orgUid) {
        this.orgUid = orgUid;
    }
}
