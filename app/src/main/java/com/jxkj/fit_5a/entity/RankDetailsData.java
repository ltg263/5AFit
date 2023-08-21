package com.jxkj.fit_5a.entity;

import java.util.List;

public class RankDetailsData {

    /**
     * clearTime :
     * createTime :
     * id : 0
     * rankRewards : [{"endRank":0,"id":0,"imgUrl":"","name":"","rankId":0,"rankRewardId":0,"rewards":[{"detail":"","explain":"","hasDel":0,"id":0,"imgUrl":"","name":"","status":0,"type":0}],"startRank":0}]
     * status : 0
     * type : 0
     * typeStr :
     */

    private String clearTime;
    private String createTime;
    private int id;
    private int status;
    private int type;
    private String typeStr;
    private List<RankRewardsBean> rankRewards;

    public String getClearTime() {
        return clearTime;
    }

    public void setClearTime(String clearTime) {
        this.clearTime = clearTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public List<RankRewardsBean> getRankRewards() {
        return rankRewards;
    }

    public void setRankRewards(List<RankRewardsBean> rankRewards) {
        this.rankRewards = rankRewards;
    }

    public static class RankRewardsBean {
        /**
         * endRank : 0
         * id : 0
         * imgUrl :
         * name :
         * rankId : 0
         * rankRewardId : 0
         * rewards : [{"detail":"","explain":"","hasDel":0,"id":0,"imgUrl":"","name":"","status":0,"type":0}]
         * startRank : 0
         */

        private int endRank;
        private int id;
        private String imgUrl;
        private String name;
        private int rankId;
        private int rankRewardId;
        private int startRank;
        private List<RewardsBean> rewards;

        public int getEndRank() {
            return endRank;
        }

        public void setEndRank(int endRank) {
            this.endRank = endRank;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRankId() {
            return rankId;
        }

        public void setRankId(int rankId) {
            this.rankId = rankId;
        }

        public int getRankRewardId() {
            return rankRewardId;
        }

        public void setRankRewardId(int rankRewardId) {
            this.rankRewardId = rankRewardId;
        }

        public int getStartRank() {
            return startRank;
        }

        public void setStartRank(int startRank) {
            this.startRank = startRank;
        }

        public List<RewardsBean> getRewards() {
            return rewards;
        }

        public void setRewards(List<RewardsBean> rewards) {
            this.rewards = rewards;
        }

        public static class RewardsBean {
            /**
             * detail :
             * explain :
             * hasDel : 0
             * id : 0
             * imgUrl :
             * name :
             * status : 0
             * type : 0
             */

            private String detail;
            private String explain;
            private int hasDel;
            private int id;
            private String imgUrl;
            private String name;
            private int status;
            private int type;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }

            public int getHasDel() {
                return hasDel;
            }

            public void setHasDel(int hasDel) {
                this.hasDel = hasDel;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
