package com.common.project.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class BuildingEntity {
    @Id
    private Long id;

    private String buildingName;
    private String buildingNumber;
    private String buildingDesc;
    private String ids;


    @Generated(hash = 110085811)
    public BuildingEntity(Long id, String buildingName, String buildingNumber,
            String buildingDesc, String ids) {
        this.id = id;
        this.buildingName = buildingName;
        this.buildingNumber = buildingNumber;
        this.buildingDesc = buildingDesc;
        this.ids = ids;
    }

    @Generated(hash = 465582889)
    public BuildingEntity() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getBuildingDesc() {
        return buildingDesc;
    }

    public void setBuildingDesc(String buildingDesc) {
        this.buildingDesc = buildingDesc;
    }

    @Override
    public String toString() {
        return "BuildingEntity{" +
                "id=" + id +
                ", buildingName='" + buildingName + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", buildingDesc='" + buildingDesc + '\'' +
                ", ids='" + ids + '\'' +
                '}';
    }

    public String getIds() {
        return this.ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
