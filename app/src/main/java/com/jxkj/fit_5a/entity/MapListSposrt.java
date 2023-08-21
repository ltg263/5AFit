package com.jxkj.fit_5a.entity;


import java.io.Serializable;
import java.util.List;

public class MapListSposrt {

    /**
     * list : [{"id":"11","name":"测试","imgUrl":"1","minLevel":0,"distance":10,"info":[[1,1],[1,3]],"boxs":[{"distance":1,"latlng":[1,1],"sportBox":{"id":"0","name":"","rewardList":[{"id":0,"name":"","imgUrl":"","explain":"","type":0,"detail":"","rate":0}],"probArray":[1],"aliasArray":[],"hasDel":false},"id":""}]},{"id":"12","name":"1221","imgUrl":"1","minLevel":1,"distance":10,"info":[[1,2],[12,12]],"boxs":[{"distance":1,"latlng":[1,1],"sportBox":{"id":"1","name":"123","rewardList":[{"id":1,"name":"勋章奖励1","imgUrl":"11","explain":"1234","type":2,"detail":"3","rate":0.3}],"probArray":[1],"aliasArray":[null],"hasDel":false},"id":"1"}]},{"id":"13","name":"经典地图","imgUrl":"1","minLevel":1,"distance":10945,"info":[[116.385056,39.891994],[116.385415,39.895125],[116.382856,39.897203],[116.382529,39.900465],[116.378812,39.903036],[116.380244,39.905739],[116.38019,39.912066],[116.38901,39.920045],[116.403322,39.920255],[116.413622,39.917214],[116.420831,39.910486],[116.420059,39.906529],[116.417055,39.902836],[116.414565,39.899537],[116.410703,39.897555],[116.402292,39.892353],[116.389846,39.891365]],"boxs":[{"distance":100,"latlng":[116.38255801979953,39.900175511969294],"sportBox":{"id":"1","name":"123","rewardList":[{"id":1,"name":"勋章奖励1","imgUrl":"11","explain":"1234","type":2,"detail":"3","rate":0.3}],"probArray":[1],"aliasArray":[null],"hasDel":false},"id":"1"}]},{"id":"14","name":"经典地图","imgUrl":"123","minLevel":0,"distance":10945,"info":[[116.385056,39.891994],[116.385415,39.895125],[116.382856,39.897203],[116.382529,39.900465],[116.378812,39.903036],[116.380244,39.905739],[116.38019,39.912066],[116.38901,39.920045],[116.403322,39.920255],[116.413622,39.917214],[116.420831,39.910486],[116.420059,39.906529],[116.417055,39.902836],[116.414565,39.899537],[116.410703,39.897555],[116.402292,39.892353],[116.389846,39.891365]],"boxs":[{"distance":1000,"latlng":[116.38255801979953,39.900175511969294],"sportBox":{"id":"1","name":"123","rewardList":[{"id":1,"name":"勋章奖励1","imgUrl":"11","explain":"1234","type":2,"detail":"3","rate":0.3}],"probArray":[1],"aliasArray":[null],"hasDel":false},"id":"1"}]},{"id":"15","name":"经典地图","imgUrl":"123","minLevel":0,"distance":10945,"info":[[116.385056,39.891994],[116.385415,39.895125],[116.382856,39.897203],[116.382529,39.900465],[116.378812,39.903036],[116.380244,39.905739],[116.38019,39.912066],[116.38901,39.920045],[116.403322,39.920255],[116.413622,39.917214],[116.420831,39.910486],[116.420059,39.906529],[116.417055,39.902836],[116.414565,39.899537],[116.410703,39.897555],[116.402292,39.892353],[116.389846,39.891365]],"boxs":[{"distance":1000,"latlng":[116.38255801979953,39.900175511969294],"sportBox":{"id":"1","name":"123","rewardList":[{"id":1,"name":"勋章奖励1","imgUrl":"11","explain":"1234","type":2,"detail":"3","rate":0.3}],"probArray":[1],"aliasArray":[null],"hasDel":false},"id":"1"}]},{"id":"16","name":"经典地图","imgUrl":"123","minLevel":0,"distance":10945,"info":[[116.385056,39.891994],[116.385415,39.895125],[116.382856,39.897203],[116.382529,39.900465],[116.378812,39.903036],[116.380244,39.905739],[116.38019,39.912066],[116.38901,39.920045],[116.403322,39.920255],[116.413622,39.917214],[116.420831,39.910486],[116.420059,39.906529],[116.417055,39.902836],[116.414565,39.899537],[116.410703,39.897555],[116.402292,39.892353],[116.389846,39.891365]],"boxs":[{"distance":1000,"latlng":[116.38255801979953,39.900175511969294],"sportBox":{"id":"1","name":"123","rewardList":[{"id":1,"name":"勋章奖励1","imgUrl":"11","explain":"1234","type":2,"detail":"3","rate":0.3}],"probArray":[1],"aliasArray":[null],"hasDel":false},"id":"1"}]}]
     * totalCount : 0
     */

    private String totalCount;
    private List<ListBean> list;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable {
        /**
         * id : 11
         * name : 测试
         * imgUrl : 1
         * minLevel : 0
         * distance : 10
         * info : [[1,1],[1,3]]
         * boxs : [{"distance":1,"latlng":[1,1],"sportBox":{"id":"0","name":"","rewardList":[{"id":0,"name":"","imgUrl":"","explain":"","type":0,"detail":"","rate":0}],"probArray":[1],"aliasArray":[],"hasDel":false},"id":""}]
         */

        private String id;
        private String name;
        private String imgUrl;
        private String minLevel;
        private String distance;
        private String difficultyLevel;
        private List<List<String>> info;
        private List<BoxsBean> boxs;

        public void setDifficultyLevel(String difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
        }

        public String getDifficultyLevel() {
            return difficultyLevel;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getMinLevel() {
            return minLevel;
        }

        public void setMinLevel(String minLevel) {
            this.minLevel = minLevel;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public List<List<String>> getInfo() {
            return info;
        }

        public void setInfo(List<List<String>> info) {
            this.info = info;
        }

        public List<BoxsBean> getBoxs() {
            return boxs;
        }

        public void setBoxs(List<BoxsBean> boxs) {
            this.boxs = boxs;
        }

        public static class BoxsBean  implements Serializable{
            /**
             * distance : 1
             * latlng : [1,1]
             * sportBox : {"id":"0","name":"","rewardList":[{"id":0,"name":"","imgUrl":"","explain":"","type":0,"detail":"","rate":0}],"probArray":[1],"aliasArray":[],"hasDel":false}
             * id :
             */

            private String distance;
            private SportBoxBean sportBox;
            private String id;
            private List<String> latlng;

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public SportBoxBean getSportBox() {
                return sportBox;
            }

            public void setSportBox(SportBoxBean sportBox) {
                this.sportBox = sportBox;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public List<String> getLatlng() {
                return latlng;
            }

            public void setLatlng(List<String> latlng) {
                this.latlng = latlng;
            }

            public static class SportBoxBean  implements Serializable{
                /**
                 * id : 0
                 * name :
                 * rewardList : [{"id":0,"name":"","imgUrl":"","explain":"","type":0,"detail":"","rate":0}]
                 * probArray : [1]
                 * aliasArray : []
                 * hasDel : false
                 */

                private String id;
                private String name;
                private boolean hasDel;
                private List<RewardListBean> rewardList;
                private List<String> probArray;
                private List<?> aliasArray;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public boolean isHasDel() {
                    return hasDel;
                }

                public void setHasDel(boolean hasDel) {
                    this.hasDel = hasDel;
                }

                public List<RewardListBean> getRewardList() {
                    return rewardList;
                }

                public void setRewardList(List<RewardListBean> rewardList) {
                    this.rewardList = rewardList;
                }

                public List<String> getProbArray() {
                    return probArray;
                }

                public void setProbArray(List<String> probArray) {
                    this.probArray = probArray;
                }

                public List<?> getAliasArray() {
                    return aliasArray;
                }

                public void setAliasArray(List<?> aliasArray) {
                    this.aliasArray = aliasArray;
                }

                public static class RewardListBean  implements Serializable{
                    /**
                     * id : 0
                     * name :
                     * imgUrl :
                     * explain :
                     * type : 0
                     * detail :
                     * rate : 0
                     */

                    private String id;
                    private String name;
                    private String imgUrl;
                    private String explain;
                    private String type;
                    private String detail;
                    private String rate;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getImgUrl() {
                        return imgUrl;
                    }

                    public void setImgUrl(String imgUrl) {
                        this.imgUrl = imgUrl;
                    }

                    public String getExplain() {
                        return explain;
                    }

                    public void setExplain(String explain) {
                        this.explain = explain;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getDetail() {
                        return detail;
                    }

                    public void setDetail(String detail) {
                        this.detail = detail;
                    }

                    public String getRate() {
                        return rate;
                    }

                    public void setRate(String rate) {
                        this.rate = rate;
                    }
                }
            }
        }
    }
}
