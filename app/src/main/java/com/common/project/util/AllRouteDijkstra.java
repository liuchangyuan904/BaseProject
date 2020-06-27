package com.common.project.util;

import android.content.Context;
import android.util.Log;

import com.common.project.MyApplication;
import com.common.project.activity.QueryActivity;
import com.common.project.dao.ArcCellDao;
import com.common.project.dao.BuildingEntityDao;
import com.common.project.entity.ArcCell;
import com.common.project.entity.BuildingEntity;
import com.common.project.entity.RelationNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class AllRouteDijkstra {
    private static final String TAG = "AllRouteDijkstra";
    private Context context;
    RelationNode[] relationNodes;
    /* 临时保存路径节点的栈 */
    public static Stack<RelationNode> stack = new Stack<RelationNode>();
    /* 存储路径的集合 */
    public static ArrayList<Object[]> sers = new ArrayList<Object[]>();
    private DataCallBack dataCallBack;


    /* 判断节点是否在栈中 */
    private static boolean isNodeInStack(RelationNode node) {
        Iterator<RelationNode> it = stack.iterator();
        while (it.hasNext()) {
            RelationNode node1 = (RelationNode) it.next();
            if (node == node1)
                return true;
        }
        return false;
    }

    static String route="";

    /* 此时栈中的节点组成一条所求路径，转储并打印输出 */
    private  void showAndSavePath() {
        Object[] o = stack.toArray();
        for (int i = 0; i < o.length; i++) {
            RelationNode nNode = (RelationNode) o[i];
            if (i < (o.length - 1))
                route = route + nNode.getName() + "->";
            else {
                route = route + nNode.getName();
            }
        }
        sers.add(o); /* 转储 */
        Log.d(TAG, "showAndSavePath: " + route);
        dataCallBack.onResult(route);
        route = "";
    }
    public void setCallBack(DataCallBack dataCallBack){
        this.dataCallBack=dataCallBack;
    }
    public interface DataCallBack{
        void onResult(String result);
    }

    public void init(Context context) {
        this.context = context;
        List<BuildingEntity> buildingEntityList = MyApplication.getInstances().getDaoSession().getBuildingEntityDao().queryBuilder().list();
        if (buildingEntityList != null) {
            relationNodes = new RelationNode[buildingEntityList.size()];
        }
        build();
    }

    /*
     * 寻找路径的方法
     * cNode: 当前的起始节点currentNode
     * pNode: 当前起始节点的上一节点previousNode
     * sNode: 最初的起始节点startNode
     * eNode: 终点endNode
     */
    public  boolean getPaths(RelationNode cNode, RelationNode pNode, RelationNode sNode, RelationNode eNode) {
        RelationNode nNode = null;
        /* 如果符合条件判断说明出现环路，不能再顺着该路径继续寻路，返回false */
        if (cNode != null && pNode != null && cNode == pNode)
            return false;

        if (cNode != null) {
            int i = 0;
            /* 起始节点入栈 */
            stack.push(cNode);
            /* 如果该起始节点就是终点，说明找到一条路径 */
            if (cNode == eNode) {
                /* 转储并打印输出该路径，返回true */
                showAndSavePath();
                return true;
            }
            /* 如果不是,继续寻路 */
            else {
                /*
                 * 从与当前起始节点cNode有连接关系的节点集中按顺序遍历得到一个节点
                 * 作为下一次递归寻路时的起始节点
                 */
                nNode = cNode.getRelationNodes().get(i);
                while (nNode != null) {
                    /*
                     * 如果nNode是最初的起始节点或者nNode就是cNode的上一节点或者nNode已经在栈中 ，
                     * 说明产生环路 ，应重新在与当前起始节点有连接关系的节点集中寻找nNode
                     */
                    if (pNode != null
                            && (nNode == sNode || nNode == pNode || isNodeInStack(nNode))) {
                        i++;
                        if (i >= cNode.getRelationNodes().size())
                            nNode = null;
                        else
                            nNode = cNode.getRelationNodes().get(i);
                        continue;
                    }
                    /* 以nNode为新的起始节点，当前起始节点cNode为上一节点，递归调用寻路方法 */
                    if (getPaths(nNode, cNode, sNode, eNode))/* 递归调用 */ {
                        /* 如果找到一条路径，则弹出栈顶节点 */
                        stack.pop();
                    }
                    /* 继续在与cNode有连接关系的节点集中测试nNode */
                    i++;
                    if (i >= cNode.getRelationNodes().size())
                        nNode = null;
                    else
                        nNode = cNode.getRelationNodes().get(i);
                }
                /*
                 * 当遍历完所有与cNode有连接关系的节点后，
                 * 说明在以cNode为起始节点到终点的路径已经全部找到
                 */
                stack.pop();
                Log.d(TAG, "getPaths: 全部找到");
                return false;
            }
        } else
            return false;
    }

    private void build() {
        BuildingEntityDao buildingEntityDao = MyApplication.getInstances().getDaoSession().getBuildingEntityDao();
        ArcCellDao arcCellDao = MyApplication.getInstances().getDaoSession().getArcCellDao();
        List<BuildingEntity> buildingEntityList = buildingEntityDao.queryBuilder().list();
        //初始化所有节点
        for (int i = 0; i < buildingEntityList.size(); i++) {
            BuildingEntity buildingEntity = buildingEntityList.get(i);
            RelationNode node = new RelationNode();
            node.setId(buildingEntity.getId());
            node.setName(buildingEntity.getBuildingName());
            relationNodes[i] = node;
        }

        for (int i = 0; i < relationNodes.length; i++) {
            //获取srcId是node ID的路径
            RelationNode node = relationNodes[i];
            List<ArcCell> srcArcCellList = arcCellDao.queryBuilder().where(ArcCellDao.Properties.SrcId.eq(node.getId())).list();
            if (srcArcCellList != null) {
                for (int j = 0; j < srcArcCellList.size(); j++) {
                    BuildingEntity childBuildEntity = buildingEntityDao.queryBuilder().where(BuildingEntityDao.Properties.Id.eq(srcArcCellList.get(j).getDesId())).unique();
                    node.getRelationNodes().add(getNode(childBuildEntity.getId()));
                }
            }
            //获取destId 是node ID的路径列表
            List<ArcCell> destArcCellList = arcCellDao.queryBuilder().where(ArcCellDao.Properties.DesId.eq(node.getId())).list();
            if (destArcCellList != null) {
                for (int j = 0; j < destArcCellList.size(); j++) {
                    BuildingEntity childBuildEntity = buildingEntityDao.queryBuilder().where(BuildingEntityDao.Properties.Id.eq(destArcCellList.get(j).getDesId())).unique();
                    node.getRelationNodes().add(getNode(childBuildEntity.getId()));
                }
            }
        }
    }

    public RelationNode getNode(Long id) {
        for (int i = 0; i < relationNodes.length; i++) {
            if (id == relationNodes[i].getId()) {
                return relationNodes[i];
            }
        }
        return null;
    }

}
