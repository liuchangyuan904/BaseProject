package com.common.project.entity;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private Long id;
    private String name;
    private Map<Node,Integer> child=new HashMap<Node,Integer>();

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

    public Map<Node, Integer> getChild() {
        return child;
    }

    public void setChild(Map<Node, Integer> child) {
        this.child = child;
    }
}
