package com.common.project.entity;

public class MGraph {
    /**
     * 景点
     */
    VertexType vex[]=new VertexType[20];
    /**
     * 图中的边，顶点与顶点间的距离
     */
    ArcCell arcs[][]=new ArcCell[20][20];
    int vexNum,arcNum;
    public MGraph(){
        for (int i = 0; i <20 ; i++) {
            VertexType vertexType=new VertexType();
            vex[i]=vertexType;
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                ArcCell arcCell=new ArcCell();
                arcs[i][j]=arcCell;
            }
        }
    }
    public VertexType[] getVex() {
        return vex;
    }

    public void setVex(VertexType[] vex) {
        this.vex = vex;
    }

    public ArcCell[][] getArcs() {
        return arcs;
    }

    public void setArcs(ArcCell[][] arcs) {
        this.arcs = arcs;
    }

    public int getVexNum() {
        return vexNum;
    }

    public void setVexNum(int vexNum) {
        this.vexNum = vexNum;
    }

    public int getArcNum() {
        return arcNum;
    }

    public void setArcNum(int arcNum) {
        this.arcNum = arcNum;
    }
}
