package com.common.project.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DayStudyEntity {
    @Id
    private Long dayStudyId;
    private String dayTime;
    private String wordHead;
    private int index;
    private String trans;
    @Generated(hash = 1358694225)
    public DayStudyEntity(Long dayStudyId, String dayTime, String wordHead,
            int index, String trans) {
        this.dayStudyId = dayStudyId;
        this.dayTime = dayTime;
        this.wordHead = wordHead;
        this.index = index;
        this.trans = trans;
    }
    @Generated(hash = 1292885569)
    public DayStudyEntity() {
    }
    public Long getDayStudyId() {
        return this.dayStudyId;
    }
    public void setDayStudyId(Long dayStudyId) {
        this.dayStudyId = dayStudyId;
    }
    public String getDayTime() {
        return this.dayTime;
    }
    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }
    public String getWordHead() {
        return this.wordHead;
    }
    public void setWordHead(String wordHead) {
        this.wordHead = wordHead;
    }
    public int getIndex() {
        return this.index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getTrans() {
        return this.trans;
    }
    public void setTrans(String trans) {
        this.trans = trans;
    }
}
