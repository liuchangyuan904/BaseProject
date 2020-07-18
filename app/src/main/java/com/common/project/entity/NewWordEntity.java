package com.common.project.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class NewWordEntity {
    @Id
    private Long wordId;
    private int index;
    private String wordHead;
    private String wordTrans;
    @Generated(hash = 1473010524)
    public NewWordEntity(Long wordId, int index, String wordHead,
            String wordTrans) {
        this.wordId = wordId;
        this.index = index;
        this.wordHead = wordHead;
        this.wordTrans = wordTrans;
    }
    @Generated(hash = 2110336128)
    public NewWordEntity() {
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
}
