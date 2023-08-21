package com.jxkj.fit_5a.entity;

import java.util.List;

public class StatsZanBean {

    /**
     * list : [{"id":1,"userId":1,"nickName":"daixiping","avatar":"https://avatar.csdnimg.cn/C/5/9/2_ltg263.jpg","type":1,"kcal":1,"zan":2,"ranking":1,"hasZan":true}]
     * totalCount : 0
     */

    private int totalCount;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * userId : 1
         * nickName : daixiping
         * avatar : https://avatar.csdnimg.cn/C/5/9/2_ltg263.jpg
         * type : 1
         * cal : 1
         * zan : 2
         * ranking : 1
         * hasZan : true
         */

        private int id;
        private int userId;
        private String nickName;
        private String avatar;
        private int type;
        private int cal;
        private int zan;
        private int ranking;
        private boolean hasZan;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCal() {
            return cal;
        }

        public void setCal(int cal) {
            this.cal = cal;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public boolean isHasZan() {
            return hasZan;
        }

        public void setHasZan(boolean hasZan) {
            this.hasZan = hasZan;
        }
    }
}
