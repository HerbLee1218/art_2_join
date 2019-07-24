package com.art2join.authentication;


import java.io.Serializable;

/**
 * 接受数据解密后的数据
 */
public class DecodeRequestData implements Serializable {

    private String uid; // artoffice 用户的uid

    private String orgUid; // 机构uid


    @Override
    public String toString() {
        return "DecodeRequestData{" +
                "uid='" + uid + '\'' +
                ", orgUid='" + orgUid + '\'' +
                '}';
    }

    public DecodeRequestData() {
    }

    public DecodeRequestData(String uid, String orgUid) {
        this.uid = uid;
        this.orgUid = orgUid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOrgUid() {
        return orgUid;
    }

    public void setOrgUid(String orgUid) {
        this.orgUid = orgUid;
    }
}
