package com.common.project.util;

import com.common.project.MyApplication;
import com.common.project.dao.ArcCellDao;
import com.common.project.entity.ArcCell;
import com.common.project.entity.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    Set<Node> open = new HashSet<Node>();
    Set<Node> close = new HashSet<Node>();
    Map<Node, Integer> path = new HashMap<Node, Integer>();//封装路径距离
    Map<Node, String> pathInfo = new HashMap<Node, String>();//封装路径信息
    MapBuilder mapBuilder = new MapBuilder();
    List<Node> nodeList;

    public Node getNode(Long startId) {
        if (nodeList == null) {
            nodeList = mapBuilder.build(open, close);
        }
        for (int i = 0; i < nodeList.size(); i++) {
            if (startId == nodeList.get(i).getId()) {
                return nodeList.get(i);
            }
        }
        return null;
    }

    public void init(Node startNode, Node endNode) {
        if (nodeList == null) {
            nodeList = mapBuilder.build(open, close);
        }
        Long startId = startNode.getId();
        for (int i = 0; i < nodeList.size(); i++) {
            if (startId != nodeList.get(i).getId()) {
                path.put(nodeList.get(i), getDistance(startId, nodeList.get(i).getId()));
                pathInfo.put(nodeList.get(i), startNode.getName() + ">" + nodeList.get(i).getName());
            }
        }

    }

    public void closeAddStart(Node startNode) {
        close.add(startNode);
    }

    public void computePath(Node start) {
        Node nearest = getShortestPath(start);//取距离start节点最近的子节点,放入close
        if (nearest == null) {
            return;
        }

        close.add(nearest);
        open.remove(nearest);
        Map<Node, Integer> childs = nearest.getChild();
        Set<Node> nodes = childs.keySet();
        for (Node child : nodes) {
            if (open.contains(child)) {//如果子节点在open中
                Integer newCompute = path.get(nearest) + childs.get(child);
                if (path.get(child) > newCompute) {//之前设置的距离大于新计算出来的距离
                    path.put(child, newCompute);
                    pathInfo.put(child, pathInfo.get(nearest) + "->" + child.getName());
                }
            }
        }
        computePath(start);//重复执行自己,确保所有子节点被遍历
        computePath(nearest);//向外一层层递归,直至所有顶点被遍历
    }

    public String printPathInfo(String endName) {
        Set<Map.Entry<Node, String>> pathInfos = pathInfo.entrySet();
        for (Map.Entry<Node, String> pathInfo : pathInfos) {
            System.out.println(pathInfo.getKey() + ":" + pathInfo.getValue());
            if (pathInfo.getValue().endsWith(endName)) {
                Node key = pathInfo.getKey();
                Integer integer = path.get(key);
                if (integer==Integer.MAX_VALUE){
                    return "无法到达！";
                }
                return "最短路径为："+pathInfo.getValue();
            }
        }
        return "";
    }

    /**
     * 获取与node最近的子节点
     */
    private Node getShortestPath(Node node) {
        System.out.println(node.getName());
        Node res = null;
        int minDis = Integer.MAX_VALUE;
        Map<Node, Integer> childs = node.getChild();
        for (Node child : childs.keySet()) {
            if (open.contains(child)) {
                int distance = childs.get(child);
                if (distance < minDis) {
                    minDis = distance;
                    res = child;
                }
            }
        }
        return res;
    }

    private Integer getDistance(Long startId, Long id) {
        ArcCell unique1 = MyApplication.getInstances().getDaoSession().getArcCellDao().queryBuilder().where(ArcCellDao.Properties.SrcId.eq(startId), ArcCellDao.Properties.DesId.eq(id)).unique();
        if (unique1 != null) {
            return unique1.getAdj();
        }
        ArcCell unique2 = MyApplication.getInstances().getDaoSession().getArcCellDao().queryBuilder().where(ArcCellDao.Properties.SrcId.eq(id), ArcCellDao.Properties.DesId.eq(startId)).unique();
        if (unique2 != null) {
            return unique2.getAdj();
        }
        return Integer.MAX_VALUE;
    }



    /***********获取所有路径*************/

}
