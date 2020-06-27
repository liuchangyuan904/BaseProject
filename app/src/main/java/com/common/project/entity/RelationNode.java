package com.common.project.entity;

import java.util.ArrayList;

public class RelationNode {
    private Long id;
    private String name;
    public ArrayList<RelationNode> relationNodes = new ArrayList<RelationNode>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<RelationNode> getRelationNodes() {
        return relationNodes;
    }

    public void setRelationNodes(ArrayList<RelationNode> relationNodes) {
        this.relationNodes = relationNodes;
    }
}
