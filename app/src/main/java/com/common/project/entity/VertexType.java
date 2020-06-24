package com.common.project.entity;

public class VertexType {
    int number;
    String sight;
    String description;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSight() {
        return sight;
    }

    public void setSight(String sight) {
        this.sight = sight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "VertexType{" +
                "number=" + number +
                ", sight='" + sight + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
