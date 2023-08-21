package com.jxkj.fit_5a.entity;

import androidx.annotation.NonNull;

public class MediaInfoBean {

    /**
     * imageUrl : http://5a-fit-oss.nbqichen.com/video/cover/4450DBEBB7FD72852C8BAE1C5C8DBB99.jpg
     * type : 3
     * vedioId : 2900752ca76047ed8e3f195f5d8d7325
     * vedioDuration : 1.8016666173934937
     */

    private String imageUrl;
    private String type;
    private String vedioId;
    private String vedioDuration;


    public MediaInfoBean(String imageUrl, String type) {
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public MediaInfoBean(String imageUrl, String type, String vedioId, String vedioDuration) {
        this.imageUrl = imageUrl;
        this.type = type;
        this.vedioId = vedioId;
        this.vedioDuration = vedioDuration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVedioId() {
        return vedioId;
    }

    public void setVedioId(String vedioId) {
        this.vedioId = vedioId;
    }

    public String getVedioDuration() {
        return vedioDuration;
    }

    public void setVedioDuration(String vedioDuration) {
        this.vedioDuration = vedioDuration;
    }

    //{
    //        "imageUrl":"http://5a-fit-oss.nbqichen.com/video/cover/4450DBEBB7FD72852C8BAE1C5C8DBB99.jpg",
    //        "type":"3",
    //        "vedioId":"2900752ca76047ed8e3f195f5d8d7325",
    //        "vedioDuration":1.8016666173934937
    //    }


    @NonNull
    @Override
    public String toString() {
        if(type.equals("3")){
            return "{" +
                    "\"imageUrl\":\""+imageUrl+"\"," +
                    "\"type\":\""+type+"\"," +
                    "\"vedioId\":\""+vedioId+"\"," +
                    "\"vedioDuration\":\""+vedioDuration+"\"" +
                    "}";
        }
        return "{" +
                "\"imageUrl\":\""+imageUrl+"\"," +
                "\"type\":\""+type+"\"" +
                "}";
    }
}
