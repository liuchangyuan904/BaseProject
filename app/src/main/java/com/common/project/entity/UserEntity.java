package com.common.project.entity;

public class UserEntity {

    /**
     * msg : null
     * status : 0
     * data : {"id":"3","createTime":"2020-06-29 21:48:05","updateTime":"2020-06-29 21:48:05","username":"11","password":"1111","cityId":null,"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMSIsImlzcyI6ImVjaGlzYW4iLCJleHAiOjE1OTQwNDMzMDMsImlhdCI6MTU5MzQzODUwMywicm9sIjoiW10ifQ.vLVi5oTZHuztIFnlIeN6nbmgIUX9bPVDTRf--zyLXHF5mxT5vN73gCJ7Uc7uwVqy85VfCAfxdYILbK3_oIf0kQ"}
     */

    private Object msg;
    private int status;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * createTime : 2020-06-29 21:48:05
         * updateTime : 2020-06-29 21:48:05
         * username : 11
         * password : 1111
         * cityId : null
         * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMSIsImlzcyI6ImVjaGlzYW4iLCJleHAiOjE1OTQwNDMzMDMsImlhdCI6MTU5MzQzODUwMywicm9sIjoiW10ifQ.vLVi5oTZHuztIFnlIeN6nbmgIUX9bPVDTRf--zyLXHF5mxT5vN73gCJ7Uc7uwVqy85VfCAfxdYILbK3_oIf0kQ
         */

        private String id;
        private String createTime;
        private String updateTime;
        private String username;
        private String password;
        private Object cityId;
        private String token;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getCityId() {
            return cityId;
        }

        public void setCityId(Object cityId) {
            this.cityId = cityId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
