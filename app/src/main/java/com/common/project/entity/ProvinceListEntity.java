package com.common.project.entity;

import java.util.List;

public class ProvinceListEntity {

    /**
     * msg : null
     * status : 0
     * data : [{"id":"1","createTime":"2020-06-28 18:23:18","updateTime":"2020-06-28 18:23:18","name":"北京","parentId":"0","weather":null},{"id":"3","createTime":"2020-06-28 18:23:39","updateTime":"2020-06-28 18:23:39","name":"江苏省","parentId":"0","weather":null},{"id":"17","createTime":"2020-06-28 19:58:36","updateTime":"2020-06-28 19:58:36","name":"天津","parentId":"0","weather":null},{"id":"19","createTime":"2020-06-28 20:01:59","updateTime":"2020-06-28 20:04:25","name":"重庆","parentId":"0","weather":null},{"id":"21","createTime":"2020-06-28 20:05:07","updateTime":"2020-06-28 20:05:07","name":"上海","parentId":"0","weather":null},{"id":"23","createTime":"2020-06-28 20:08:27","updateTime":"2020-06-28 20:08:27","name":"广东省","parentId":"0","weather":null}]
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
         * id : 1
         * createTime : 2020-06-28 18:23:18
         * updateTime : 2020-06-28 18:23:18
         * name : 北京
         * parentId : 0
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
