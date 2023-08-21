package com.jxkj.fit_5a.entity;

public class UserReportType {

    /**
     * relationParam  : {"momentKeyPo ":{"circleId ":"-324","momentId ":1642422272948000,"publisherId":324},"type ":1}
     * reportedUserId  : 324
     * type  : 444
     */

    private RelationParamBean relationParam;
    private int reportedUserId;
    private int type;

    @Override
    public String toString() {
        return "UserReportType{" +
                "relationParam=" + relationParam +
                ", reportedUserId=" + reportedUserId +
                ", type=" + type +
                '}';
    }

    public RelationParamBean getRelationParam() {
        return relationParam;
    }

    public void setRelationParam(RelationParamBean relationParam) {
        this.relationParam = relationParam;
    }

    public int getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(int reportedUserId) {
        this.reportedUserId = reportedUserId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class RelationParamBean {
        @Override
        public String toString() {
            return "RelationParamBean{" +
                    "momentKeyPo=" + momentKeyPo +
                    ", type=" + type +
                    '}';
        }

        /**
         * momentKeyPo  : {"circleId ":"-324","momentId ":1642422272948000,"publisherId":324}
         * type  : 1
         */

        private MomentKeyPoBean momentKeyPo;
        private int type;

        public MomentKeyPoBean getMomentKeyPo() {
            return momentKeyPo;
        }

        public void setMomentKeyPo(MomentKeyPoBean momentKeyPo) {
            this.momentKeyPo = momentKeyPo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public static class MomentKeyPoBean {
            @Override
            public String toString() {
                return "MomentKeyPoBean{" +
                        "circleId='" + circleId + '\'' +
                        ", momentId=" + momentId +
                        ", publisherId=" + publisherId +
                        '}';
            }

            /**
             * circleId  : -324
             * momentId  : 1642422272948000
             * publisherId : 324
             */

            private String circleId;
            private long momentId;
            private int publisherId;

            public String getCircleId() {
                return circleId;
            }

            public void setCircleId(String circleId) {
                this.circleId = circleId;
            }

            public long getMomentId() {
                return momentId;
            }

            public void setMomentId(long momentId) {
                this.momentId = momentId;
            }

            public int getPublisherId() {
                return publisherId;
            }

            public void setPublisherId(int publisherId) {
                this.publisherId = publisherId;
            }
        }
    }

}
