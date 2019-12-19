package com.mzy.gmall.bean;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class UmsMember implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long memberLevelId;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private long status;
    private java.sql.Timestamp createTime;
    private String icon;
    private long gender;
    private java.sql.Date birthday;
    private String city;
    private String job;
    private String personalizedSignature;
    private long sourceType;
    private long integration;
    private long growth;
    private long luckeyCount;
    private long historyIntegration;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(long memberLevelId) {
        this.memberLevelId = memberLevelId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }


    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }


    public long getSourceType() {
        return sourceType;
    }

    public void setSourceType(long sourceType) {
        this.sourceType = sourceType;
    }


    public long getIntegration() {
        return integration;
    }

    public void setIntegration(long integration) {
        this.integration = integration;
    }


    public long getGrowth() {
        return growth;
    }

    public void setGrowth(long growth) {
        this.growth = growth;
    }


    public long getLuckeyCount() {
        return luckeyCount;
    }

    public void setLuckeyCount(long luckeyCount) {
        this.luckeyCount = luckeyCount;
    }


    public long getHistoryIntegration() {
        return historyIntegration;
    }

    public void setHistoryIntegration(long historyIntegration) {
        this.historyIntegration = historyIntegration;
    }

}
