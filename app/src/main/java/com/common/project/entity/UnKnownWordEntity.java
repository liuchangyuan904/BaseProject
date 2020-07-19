package com.common.project.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UnKnownWordEntity {
    @Id
    private Long wordId;
    private int index;
    private String wordHead;
    private String wordTrans;
    private String dateTime;
    @Generated(hash = 1639902498)
    public UnKnownWordEntity(Long wordId, int index, String wordHead,
            String wordTrans, String dateTime) {
        this.wordId = wordId;
        this.index = index;
        this.wordHead = wordHead;
        this.wordTrans = wordTrans;
        this.dateTime = dateTime;
    }
    @Generated(hash = 552183819)
    public UnKnownWordEntity() {
    }
    public Long getWordId() {
        return this.wordId;
    }
    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }
    public String getWordHead() {
        return this.wordHead;
    }
    public void setWordHead(String wordHead) {
        this.wordHead = wordHead;
    }
    public String getWordTrans() {
        return this.wordTrans;
    }
    public void setWordTrans(String wordTrans) {
        this.wordTrans = wordTrans;
    }
    public int getIndex() {
        return this.index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getDateTime() {
        return this.dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
