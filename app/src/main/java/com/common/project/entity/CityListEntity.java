package com.common.project.entity;

import java.util.List;

public class CityListEntity {

    /**
     * msg : null
     * status : 0
     * data : [{"id":"4","createTime":"2020-06-28 18:23:45","updateTime":"2020-06-28 18:23:49","name":"南京市","parentId":"3","weather":null},{"id":"5","createTime":"2020-06-28 18:23:56","updateTime":"2020-06-28 19:44:45","name":"无锡市","parentId":"3","weather":null},{"id":"6","createTime":"2020-06-28 19:40:33","updateTime":"2020-06-28 19:44:48","name":"苏州市","parentId":"3","weather":null},{"id":"7","createTime":"2020-06-28 19:43:42","updateTime":"2020-06-28 19:44:51","name":"常州市","parentId":"3","weather":null},{"id":"8","createTime":"2020-06-28 19:44:42","updateTime":"2020-06-28 19:45:00","name":"扬州市","parentId":"3","weather":null},{"id":"9","createTime":"2020-06-28 19:45:19","updateTime":"2020-06-28 19:45:19","name":"镇江市","parentId":"3","weather":null},{"id":"10","createTime":"2020-06-28 19:55:10","updateTime":"2020-06-28 19:55:10","name":"连云港市","parentId":"3","weather":null},{"id":"11","createTime":"2020-06-28 19:55:34","updateTime":"2020-06-28 19:55:34","name":"盐城市","parentId":"3","weather":null},{"id":"12","createTime":"2020-06-28 19:55:51","updateTime":"2020-06-28 19:55:51","name":"徐州市","parentId":"3","weather":null},{"id":"13","createTime":"2020-06-28 19:56:10","updateTime":"2020-06-28 19:56:10","name":"宿迁市","parentId":"3","weather":null},{"id":"14","createTime":"2020-06-28 19:57:12","updateTime":"2020-06-28 19:57:12","name":"南通市","parentId":"3","weather":null},{"id":"15","createTime":"2020-06-28 19:57:32","updateTime":"2020-06-28 19:57:32","name":"淮安市","parentId":"3","weather":null},{"id":"16","createTime":"2020-06-28 19:58:02","updateTime":"2020-06-28 19:58:02","name":"泰州市","parentId":"3","weather":null}]
     */

    private Object msg;
    private int status;
    private List<DataBean> data;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * createTime : 2020-06-28 18:23:45
         * updateTime : 2020-06-28 18:23:49
         * name : 南京市
         * parentId : 3
         * weather : null
         */

        private String id;
        private String createTime;
        private String updateTime;
        private String name;
        private String parentId;
        private Object weather;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public Object getWeather() {
            return weather;
        }

        public void setWeather(Object weather) {
            this.weather = weather;
        }
    }
}
