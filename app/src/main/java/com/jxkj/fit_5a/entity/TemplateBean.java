package com.jxkj.fit_5a.entity;

import java.util.List;

public class TemplateBean {

    /**
     * list : [{"content":"","endInterval":{},"id":"","params":[{"color":"","label":"","size":"","value":""}],"startInterval":{},"typeStr":""}]
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
         * content :
         * endInterval : {}
         * id :
         * params : [{"color":"","label":"","size":"","value":""}]
         * startInterval : {}
         * typeStr :
         */

        private String content;
        private Double endInterval;
        private String id;
        private Double startInterval;
        private String typeStr;
        private List<ParamsBean> params;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Double getEndInterval() {
            return endInterval;
        }

        public void setEndInterval(Double endInterval) {
            this.endInterval = endInterval;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Double getStartInterval() {
            return startInterval;
        }

        public void setStartInterval(Double startInterval) {
            this.startInterval = startInterval;
        }

        public String getTypeStr() {
            return typeStr;
        }

        public void setTypeStr(String typeStr) {
            this.typeStr = typeStr;
        }

        public List<ParamsBean> getParams() {
            return params;
        }

        public void setParams(List<ParamsBean> params) {
            this.params = params;
        }

        public static class EndIntervalBean {
        }

        public static class StartIntervalBean {
        }

        public static class ParamsBean {
            /**
             * color :
             * label :
             * size :
             * value :
             */

            private String color;
            private String label;
            private String size;
            private String value;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
