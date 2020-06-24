package com.common.project.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 边的类型
 */
@Entity
public class ArcCell {
    @Id
    Long id;

    Long srcId;
    Long desId;
    int adj;


    @Generated(hash = 215978873)
    public ArcCell(Long id, Long srcId, Long desId, int adj) {
        this.id = id;
        this.srcId = srcId;
        this.desId = desId;
        this.adj = adj;
    }


    @Generated(hash = 1791666638)
    public ArcCell() {
    }

    @Override
    public String toString() {
        return "ArcCell{" +
                "id=" + id +
                ", srcId=" + srcId +
                ", desId=" + desId +
                ", adj=" + adj +
                '}';
    }

    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getSrcId() {
        return this.srcId;
    }


    public void setSrcId(Long srcId) {
        this.srcId = srcId;
    }


    public Long getDesId() {
        return this.desId;
    }


    public void setDesId(Long desId) {
        this.desId = desId;
    }


    public int getAdj() {
        return this.adj;
    }


    public void setAdj(int adj) {
        this.adj = adj;
    }
}
