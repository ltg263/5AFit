package com.jxkj.fit_5a.entity;

import java.util.List;

public class VideoPlayInfoBean {

    /**
     * playInfoList : [{"width":0,"height":0,"size":0,"playURL":"https://outin-4785a87249b211ebbe9000163e1c35d5.oss-cn-shanghai.aliyuncs.com/sv/4ea34ea6-1773dac9d33/4ea34ea6-1773dac9d33.mp4?Expires=1611736701&OSSAccessKeyId=LTAIwkKSLcUfI2u4&Signature=QxIgJNZQxk40QllxXao6b4wR3rE%3D","bitrate":"","definition":"OD","duration":"0.0","format":"mp4","fps":"","encrypt":0,"plaintext":"","complexity":"","streamType":"video","rand":"","jobId":"6d6368afb0294943b60c17c77bedc71502","preprocessStatus":"UnPreprocess","watermarkId":"","status":"Normal","creationTime":"2021-01-26T07:50:58Z","modificationTime":"2021-01-26T07:50:58Z","encryptType":"","narrowBandType":"0","specification":"Original"}]
     * videoBase : {"outputType":"oss","coverURL":"http://5a-fit-oss.nbqichen.com/moment/31fb3750ec16a9bd146cbde9890f20c7.jpg","duration":"0.0","status":"Uploading","title":"6c3a733bf1a73870de467be4cf95d280","videoId":"6d6368afb0294943b60c17c77bedc715","mediaType":"video","creationTime":"2021-01-26T07:50:58Z","transcodeMode":"NoTranscode","thumbnailList":[]}
     */

    private VideoBaseBean videoBase;
    private List<PlayInfoListBean> playInfoList;

    public VideoBaseBean getVideoBase() {
        return videoBase;
    }

    public void setVideoBase(VideoBaseBean videoBase) {
        this.videoBase = videoBase;
    }

    public List<PlayInfoListBean> getPlayInfoList() {
        return playInfoList;
    }

    public void setPlayInfoList(List<PlayInfoListBean> playInfoList) {
        this.playInfoList = playInfoList;
    }

    public static class VideoBaseBean {
        /**
         * outputType : oss
         * coverURL : http://5a-fit-oss.nbqichen.com/moment/31fb3750ec16a9bd146cbde9890f20c7.jpg
         * duration : 0.0
         * status : Uploading
         * title : 6c3a733bf1a73870de467be4cf95d280
         * videoId : 6d6368afb0294943b60c17c77bedc715
         * mediaType : video
         * creationTime : 2021-01-26T07:50:58Z
         * transcodeMode : NoTranscode
         * thumbnailList : []
         */

        private String outputType;
        private String coverURL;
        private String duration;
        private String status;
        private String title;
        private String videoId;
        private String mediaType;
        private String creationTime;
        private String transcodeMode;
        private List<?> thumbnailList;

        public String getOutputType() {
            return outputType;
        }

        public void setOutputType(String outputType) {
            this.outputType = outputType;
        }

        public String getCoverURL() {
            return coverURL;
        }

        public void setCoverURL(String coverURL) {
            this.coverURL = coverURL;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public String getTranscodeMode() {
            return transcodeMode;
        }

        public void setTranscodeMode(String transcodeMode) {
            this.transcodeMode = transcodeMode;
        }

        public List<?> getThumbnailList() {
            return thumbnailList;
        }

        public void setThumbnailList(List<?> thumbnailList) {
            this.thumbnailList = thumbnailList;
        }
    }

    public static class PlayInfoListBean {
        /**
         * width : 0
         * height : 0
         * size : 0
         * playURL : https://outin-4785a87249b211ebbe9000163e1c35d5.oss-cn-shanghai.aliyuncs.com/sv/4ea34ea6-1773dac9d33/4ea34ea6-1773dac9d33.mp4?Expires=1611736701&OSSAccessKeyId=LTAIwkKSLcUfI2u4&Signature=QxIgJNZQxk40QllxXao6b4wR3rE%3D
         * bitrate :
         * definition : OD
         * duration : 0.0
         * format : mp4
         * fps :
         * encrypt : 0
         * plaintext :
         * complexity :
         * streamType : video
         * rand :
         * jobId : 6d6368afb0294943b60c17c77bedc71502
         * preprocessStatus : UnPreprocess
         * watermarkId :
         * status : Normal
         * creationTime : 2021-01-26T07:50:58Z
         * modificationTime : 2021-01-26T07:50:58Z
         * encryptType :
         * narrowBandType : 0
         * specification : Original
         */

        private int width;
        private int height;
        private int size;
        private String playURL;
        private String bitrate;
        private String definition;
        private String duration;
        private String format;
        private String fps;
        private int encrypt;
        private String plaintext;
        private String complexity;
        private String streamType;
        private String rand;
        private String jobId;
        private String preprocessStatus;
        private String watermarkId;
        private String status;
        private String creationTime;
        private String modificationTime;
        private String encryptType;
        private String narrowBandType;
        private String specification;
        private String imageUrl;
        private MomentDetailsBean data;

        @Override
        public String toString() {
            return "PlayInfoListBean{" +
                    "width=" + width +
                    ", height=" + height +
                    ", size=" + size +
                    ", playURL='" + playURL + '\'' +
                    ", bitrate='" + bitrate + '\'' +
                    ", definition='" + definition + '\'' +
                    ", duration='" + duration + '\'' +
                    ", format='" + format + '\'' +
                    ", fps='" + fps + '\'' +
                    ", encrypt=" + encrypt +
                    ", plaintext='" + plaintext + '\'' +
                    ", complexity='" + complexity + '\'' +
                    ", streamType='" + streamType + '\'' +
                    ", rand='" + rand + '\'' +
                    ", jobId='" + jobId + '\'' +
                    ", preprocessStatus='" + preprocessStatus + '\'' +
                    ", watermarkId='" + watermarkId + '\'' +
                    ", status='" + status + '\'' +
                    ", creationTime='" + creationTime + '\'' +
                    ", modificationTime='" + modificationTime + '\'' +
                    ", encryptType='" + encryptType + '\'' +
                    ", narrowBandType='" + narrowBandType + '\'' +
                    ", specification='" + specification + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", data=" + data +
                    '}';
        }

        public void setData(MomentDetailsBean data) {
            this.data = data;
        }

        public MomentDetailsBean getData() {
            return data;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getPlayURL() {
            return playURL;
        }

        public void setPlayURL(String playURL) {
            this.playURL = playURL;
        }

        public String getBitrate() {
            return bitrate;
        }

        public void setBitrate(String bitrate) {
            this.bitrate = bitrate;
        }

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getFps() {
            return fps;
        }

        public void setFps(String fps) {
            this.fps = fps;
        }

        public int getEncrypt() {
            return encrypt;
        }

        public void setEncrypt(int encrypt) {
            this.encrypt = encrypt;
        }

        public String getPlaintext() {
            return plaintext;
        }

        public void setPlaintext(String plaintext) {
            this.plaintext = plaintext;
        }

        public String getComplexity() {
            return complexity;
        }

        public void setComplexity(String complexity) {
            this.complexity = complexity;
        }

        public String getStreamType() {
            return streamType;
        }

        public void setStreamType(String streamType) {
            this.streamType = streamType;
        }

        public String getRand() {
            return rand;
        }

        public void setRand(String rand) {
            this.rand = rand;
        }

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

        public String getPreprocessStatus() {
            return preprocessStatus;
        }

        public void setPreprocessStatus(String preprocessStatus) {
            this.preprocessStatus = preprocessStatus;
        }

        public String getWatermarkId() {
            return watermarkId;
        }

        public void setWatermarkId(String watermarkId) {
            this.watermarkId = watermarkId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public String getModificationTime() {
            return modificationTime;
        }

        public void setModificationTime(String modificationTime) {
            this.modificationTime = modificationTime;
        }

        public String getEncryptType() {
            return encryptType;
        }

        public void setEncryptType(String encryptType) {
            this.encryptType = encryptType;
        }

        public String getNarrowBandType() {
            return narrowBandType;
        }

        public void setNarrowBandType(String narrowBandType) {
            this.narrowBandType = narrowBandType;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }
    }
}
