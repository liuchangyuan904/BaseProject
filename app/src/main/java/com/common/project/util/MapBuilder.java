package com.common.project.util;

import com.common.project.MyApplication;
import com.common.project.dao.ArcCellDao;
import com.common.project.dao.BuildingEntityDao;
import com.common.project.entity.ArcCell;
import com.common.project.entity.BuildingEntity;
import com.common.project.entity.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MapBuilder {
    List<Node> nodeList;

    public List<Node> build(Set<Node> open, Set<Node> close) {
        nodeList = new ArrayList<>();
        BuildingEntityDao buildingEntityDao = MyApplication.getInstances().getDaoSession().getBuildingEntityDao();
        ArcCellDao arcCellDao = MyApplication.getInstances().getDaoSession().getArcCellDao();
        List<BuildingEntity> buildingEntityList = buildingEntityDao.queryBuilder().list();
        //初始化所有节点
        for (int i = 0; i < buildingEntityList.size(); i++) {
            BuildingEntity buildingEntity = buildingEntityList.get(i);
            Node node = new Node();
            node.setId(buildingEntity.getId());
            node.setName(buildingEntity.getBuildingName());
            open.add(node);
            nodeList.add(node);
        }

        for (int i = 0; i < nodeList.size(); i++) {
            //获取srcId是node ID的路径
            Node node = nodeList.get(i);
            List<ArcCell> srcArcCellList = arcCellDao.queryBuilder().where(ArcCellDao.Properties.SrcId.eq(node.getId())).list();
            if (srcArcCellList != null) {
                for (int j = 0; j < srcArcCellList.size(); j++) {
                    BuildingEntity childBuildEntity = buildingEntityDao.queryBuilder().where(BuildingEntityDao.Properties.Id.eq(srcArcCellList.get(j).getDesId())).unique();
                    node.getChild().put(getNode(childBuildEntity.getId()),srcArcCellList.get(j).getAdj());
                }
            }
            //获取destId 是node ID的路径列表
            List<ArcCell> destArcCellList = arcCellDao.queryBuilder().where(ArcCellDao.Properties.DesId.eq(node.getId())).list();
            if (destArcCellList != null) {
                for (int j = 0; j < destArcCellList.size(); j++) {
                    BuildingEntity childBuildEntity = buildingEntityDao.queryBuilder().where(BuildingEntityDao.Properties.Id.eq(destArcCellList.get(j).getDesId())).unique();
                    node.getChild().put(getNode(childBuildEntity.getId()),destArcCellList.get(j).getAdj());
                }
            }
        }
        return nodeList;
    }

    private Node getNode(Long id) {
        for (int i = 0; i < nodeList.size(); i++) {
            if (id==nodeList.get(i).getId()){
                return nodeList.get(i);
            }
        }
        return null;
    }
}
