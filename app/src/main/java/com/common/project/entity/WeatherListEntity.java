package com.common.project.entity;

import java.util.List;

public class WeatherListEntity {

    /**
     * msg : null
     * status : 0
     * data : [{"id":"2","createTime":"2020-06-28 18:23:26","updateTime":"2020-06-28 18:23:29","name":"北京市","parentId":"1","weather":{"id":"3","createTime":"2020-06-28 20:24:58","updateTime":"2020-06-29 23:02:47","areaId":"2","wendu":33,"shidu":48,"pm25":80,"weather":"多云","date":"2020-07-05"}},{"id":"4","createTime":"2020-06-28 18:23:45","updateTime":"2020-06-28 18:23:49","name":"南京市","parentId":"3","weather":{"id":"6","createTime":"2020-06-28 22:48:28","updateTime":"2020-06-29 23:03:04","areaId":"4","wendu":25,"shidu":86,"pm25":65,"weather":"中雨","date":"2020-07-05"}},{"id":"5","createTime":"2020-06-28 18:23:56","updateTime":"2020-06-28 19:44:45","name":"无锡市","parentId":"3","weather":{"id":"9","createTime":"2020-06-28 22:51:16","updateTime":"2020-06-29 23:03:22","areaId":"5","wendu":28,"shidu":82,"pm25":67,"weather":"小雨","date":"2020-07-05"}},{"id":"6","createTime":"2020-06-28 19:40:33","updateTime":"2020-06-28 19:44:48","name":"苏州市","parentId":"3","weather":{"id":"12","createTime":"2020-06-28 22:53:52","updateTime":"2020-06-29 23:03:41","areaId":"6","wendu":32,"shidu":76,"pm25":80,"weather":"雷阵雨","date":"2020-07-05"}},{"id":"7","createTime":"2020-06-28 19:43:42","updateTime":"2020-06-28 19:44:51","name":"常州市","parentId":"3","weather":{"id":"15","createTime":"2020-06-28 22:58:16","updateTime":"2020-06-29 23:03:55","areaId":"7","wendu":30,"shidu":69,"pm25":67,"weather":"多云","date":"2020-07-05"}},{"id":"8","createTime":"2020-06-28 19:44:42","updateTime":"2020-06-28 19:45:00","name":"扬州市","parentId":"3","weather":{"id":"18","createTime":"2020-06-28 23:00:05","updateTime":"2020-06-29 23:04:06","areaId":"8","wendu":28,"shidu":80,"pm25":72,"weather":"中雨","date":"2020-07-05"}},{"id":"9","createTime":"2020-06-28 19:45:19","updateTime":"2020-06-28 19:45:19","name":"镇江市","parentId":"3","weather":{"id":"21","createTime":"2020-06-28 23:03:37","updateTime":"2020-06-29 23:04:21","areaId":"9","wendu":36,"shidu":72,"pm25":42,"weather":"阴","date":"2020-07-05"}},{"id":"10","createTime":"2020-06-28 19:55:10","updateTime":"2020-06-28 19:55:10","name":"连云港市","parentId":"3","weather":{"id":"24","createTime":"2020-06-28 23:08:17","updateTime":"2020-06-29 23:04:33","areaId":"10","wendu":36,"shidu":82,"pm25":84,"weather":"小雨","date":"2020-07-05"}},{"id":"11","createTime":"2020-06-28 19:55:34","updateTime":"2020-06-28 19:55:34","name":"盐城市","parentId":"3","weather":{"id":"27","createTime":"2020-06-28 23:13:28","updateTime":"2020-06-29 23:04:49","areaId":"11","wendu":36,"shidu":64,"pm25":78,"weather":"多云","date":"2020-07-05"}},{"id":"12","createTime":"2020-06-28 19:55:51","updateTime":"2020-06-28 19:55:51","name":"徐州市","parentId":"3","weather":{"id":"30","createTime":"2020-06-28 23:15:06","updateTime":"2020-06-29 23:05:08","areaId":"12","wendu":34,"shidu":82,"pm25":79,"weather":"中雨","date":"2020-07-05"}},{"id":"13","createTime":"2020-06-28 19:56:10","updateTime":"2020-06-28 19:56:10","name":"宿迁市","parentId":"3","weather":{"id":"33","createTime":"2020-06-28 23:18:46","updateTime":"2020-06-29 23:05:22","areaId":"13","wendu":35,"shidu":61,"pm25":69,"weather":"多云","date":"2020-07-05"}},{"id":"14","createTime":"2020-06-28 19:57:12","updateTime":"2020-06-28 19:57:12","name":"南通市","parentId":"3","weather":{"id":"36","createTime":"2020-06-28 23:24:02","updateTime":"2020-06-29 23:05:35","areaId":"14","wendu":31,"shidu":67,"pm25":53,"weather":"多云","date":"2020-07-05"}},{"id":"15","createTime":"2020-06-28 19:57:32","updateTime":"2020-06-28 19:57:32","name":"淮安市","parentId":"3","weather":{"id":"39","createTime":"2020-06-28 23:26:24","updateTime":"2020-06-29 23:05:48","areaId":"15","wendu":30,"shidu":85,"pm25":69,"weather":"中雨","date":"2020-07-05"}},{"id":"16","createTime":"2020-06-28 19:58:02","updateTime":"2020-06-28 19:58:02","name":"泰州市","parentId":"3","weather":{"id":"42","createTime":"2020-06-28 23:30:07","updateTime":"2020-06-29 23:05:59","areaId":"16","wendu":31,"shidu":74,"pm25":63,"weather":"阴","date":"2020-07-05"}},{"id":"18","createTime":"2020-06-28 19:58:52","updateTime":"2020-06-28 19:58:52","name":"天津市","parentId":"17","weather":{"id":"45","createTime":"2020-06-28 23:35:01","updateTime":"2020-06-29 23:06:19","areaId":"18","wendu":35,"shidu":55,"pm25":89,"weather":"晴","date":"2020-07-05"}},{"id":"20","createTime":"2020-06-28 20:04:46","updateTime":"2020-06-28 20:04:46","name":"重庆市","parentId":"19","weather":{"id":"48","createTime":"2020-06-28 23:37:16","updateTime":"2020-06-29 23:06:33","areaId":"20","wendu":28,"shidu":86,"pm25":52,"weather":"大雨","date":"2020-07-05"}},{"id":"22","createTime":"2020-06-28 20:05:19","updateTime":"2020-06-28 20:05:19","name":"上海市","parentId":"21","weather":{"id":"51","createTime":"2020-06-28 23:40:32","updateTime":"2020-06-29 23:06:48","areaId":"22","wendu":35,"shidu":69,"pm25":80,"weather":"多云","date":"2020-07-05"}},{"id":"24","createTime":"2020-06-28 20:08:40","updateTime":"2020-06-28 20:08:40","name":"广州市","parentId":"23","weather":{"id":"54","createTime":"2020-06-28 23:42:35","updateTime":"2020-06-29 23:06:59","areaId":"24","wendu":35,"shidu":78,"pm25":68,"weather":"阴","date":"2020-07-05"}},{"id":"25","createTime":"2020-06-28 20:08:57","updateTime":"2020-06-28 20:08:57","name":"深圳市","parentId":"23","weather":{"id":"57","createTime":"2020-06-28 23:45:29","updateTime":"2020-06-29 23:07:18","areaId":"25","wendu":35,"shidu":70,"pm25":70,"weather":"多云","date":"2020-07-05"}}]
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
         * id : 2
         * createTime : 2020-06-28 18:23:26
         * updateTime : 2020-06-28 18:23:29
         * name : 北京市
         * parentId : 1
         * weather : {"id":"3","createTime":"2020-06-28 20:24:58","updateTime":"2020-06-29 23:02:47","areaId":"2","wendu":33,"shidu":48,"pm25":80,"weather":"多云","date":"2020-07-05"}
         */

        private String id;
        private String createTime;
        private String updateTime;
        private String name;
        private String parentId;
        private WeatherBean weather;

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

        public WeatherBean getWeather() {
            return weather;
        }

        public void setWeather(WeatherBean weather) {
            this.weather = weather;
        }

        public static class WeatherBean {
            /**
             * id : 3
             * createTime : 2020-06-28 20:24:58
             * updateTime : 2020-06-29 23:02:47
             * areaId : 2
             * wendu : 33
             * shidu : 48
             * pm25 : 80
             * weather : 多云
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
}
