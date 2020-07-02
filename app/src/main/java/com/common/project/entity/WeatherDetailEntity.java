package com.common.project.entity;

import java.util.List;

public class WeatherDetailEntity {

    /**
     * msg : null
     * status : 0
     * data : [{"id":"6","createTime":"2020-06-28 22:48:28","updateTime":"2020-06-29 23:03:04","areaId":"4","wendu":25,"shidu":86,"pm25":65,"weather":"中雨","date":"2020-07-05"},{"id":"5","createTime":"2020-06-28 22:45:34","updateTime":"2020-06-29 23:02:59","areaId":"4","wendu":27,"shidu":84,"pm25":69,"weather":"小雨","date":"2020-07-04"},{"id":"4","createTime":"2020-06-28 22:44:49","updateTime":"2020-06-29 23:02:53","areaId":"4","wendu":34,"shidu":60,"pm25":74,"weather":"阴","date":"2020-07-03"}]
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
         * id : 6
         * createTime : 2020-06-28 22:48:28
         * updateTime : 2020-06-29 23:03:04
         * areaId : 4
         * wendu : 25
         * shidu : 86
         * pm25 : 65
         * weather : 中雨
         * date : 2020-07-05
         */

        private String id;
        private String createTime;
        private String updateTime;
        private String areaId;
        private int wendu;
        private int shidu;
        private int pm25;
        private String weather;
        private String date;

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

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public int getWendu() {
            return wendu;
        }

        public void setWendu(int wendu) {
            this.wendu = wendu;
        }

        public int getShidu() {
            return shidu;
        }

        public void setShidu(int shidu) {
            this.shidu = shidu;
        }

        public int getPm25() {
            return pm25;
        }

        public void setPm25(int pm25) {
            this.pm25 = pm25;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
