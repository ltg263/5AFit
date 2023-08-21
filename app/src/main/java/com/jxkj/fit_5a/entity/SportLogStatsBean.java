package com.jxkj.fit_5a.entity;

import java.util.List;

public class SportLogStatsBean {

    /**
     * stats : {"datestr":"20210203,20210104","avgCalories":3,"totalCalories":6,"avgDuration":49,"totalDuration":98,"totalDistance":0.4002,"avgDistance":0.2001,"bai":0}
     * list : [{"datestr":"20210203","avgCalories":2,"totalCalories":2,"avgDuration":34,"totalDuration":34,"totalDistance":0.1001,"avgDistance":0.1001,"bai":0},{"datestr":"20210104","avgCalories":4,"totalCalories":4,"avgDuration":64,"totalDuration":64,"totalDistance":0.3001,"avgDistance":0.3001,"bai":0}]
     */

    private StatsBean stats;
    private List<ListBean> list;

    public StatsBean getStats() {
        return stats;
    }

    public void setStats(StatsBean stats) {
        this.stats = stats;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class StatsBean {
        /**
         * datestr : 20210203,20210104
         * avgCalories : 3
         * totalCalories : 6
         * avgDuration : 49
         * totalDuration : 98
         * totalDistance : 0.4002
         * avgDistance : 0.2001
         * bai : 0
         */

        private String datestr;
        private double avgCalories;
        private int totalCalories;
        private int avgDuration;
        private int totalDuration;
        private double totalDistance;
        private double avgDistance;
        private double bai;

        public String getDatestr() {
            return datestr;
        }

        public void setDatestr(String datestr) {
            this.datestr = datestr;
        }

        public double getAvgCalories() {
            return avgCalories;
        }

        public void setAvgCalories(double avgCalories) {
            this.avgCalories = avgCalories;
        }

        public int getTotalCalories() {
            return totalCalories;
        }

        public void setTotalCalories(int totalCalories) {
            this.totalCalories = totalCalories;
        }

        public int getAvgDuration() {
            return avgDuration;
        }

        public void setAvgDuration(int avgDuration) {
            this.avgDuration = avgDuration;
        }

        public int getTotalDuration() {
            return totalDuration;
        }

        public void setTotalDuration(int totalDuration) {
            this.totalDuration = totalDuration;
        }

        public double getTotalDistance() {
            return totalDistance;
        }

        public void setTotalDistance(double totalDistance) {
            this.totalDistance = totalDistance;
        }

        public double getAvgDistance() {
            return avgDistance;
        }

        public void setAvgDistance(double avgDistance) {
            this.avgDistance = avgDistance;
        }

        public double getBai() {
            return bai;
        }

        public void setBai(double bai) {
            this.bai = bai;
        }
    }

    public static class ListBean {
        /**
         * datestr : 20210203
         * avgCalories : 2
         * totalCalories : 2
         * avgDuration : 34
         * totalDuration : 34
         * totalDistance : 0.1001
         * avgDistance : 0.1001
         * bai : 0
         */

        private String datestr;
        private int avgCalories;
        private int totalCalories;
        private int avgDuration;
        private int totalDuration;
        private double totalDistance;
        private double avgDistance;
        private double bai;

        public String getDatestr() {
            return datestr;
        }

        public void setDatestr(String datestr) {
            this.datestr = datestr;
        }

        public int getAvgCalories() {
            return avgCalories;
        }

        public void setAvgCalories(int avgCalories) {
            this.avgCalories = avgCalories;
        }

        public int getTotalCalories() {
            return totalCalories;
        }

        public void setTotalCalories(int totalCalories) {
            this.totalCalories = totalCalories;
        }

        public int getAvgDuration() {
            return avgDuration;
        }

        public void setAvgDuration(int avgDuration) {
            this.avgDuration = avgDuration;
        }

        public int getTotalDuration() {
            return totalDuration;
        }

        public void setTotalDuration(int totalDuration) {
            this.totalDuration = totalDuration;
        }

        public double getTotalDistance() {
            return totalDistance;
        }

        public void setTotalDistance(double totalDistance) {
            this.totalDistance = totalDistance;
        }

        public double getAvgDistance() {
            return avgDistance;
        }

        public void setAvgDistance(double avgDistance) {
            this.avgDistance = avgDistance;
        }

        public double getBai() {
            return bai;
        }

        public void setBai(double bai) {
            this.bai = bai;
        }
    }
}
