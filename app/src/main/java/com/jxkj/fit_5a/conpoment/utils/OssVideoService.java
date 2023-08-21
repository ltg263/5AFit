package com.jxkj.fit_5a.conpoment.utils;

public class OssVideoService {
    //视频上传
    public static void upLoadVideo(String videoPath) {

//        String Auth = "eyJTZWN1cml0eVRva2VuIjoiQ0FJUzBBUjFxNkZ0NUIyeWZTaklyNG52TGU3MTJZcEZnN2VJV0g3RHFETm1PY3hxdWZETmlEejJJSDlJZEhWb0FPOGZ2dlUwbTJ0WTdQc1psclV4L1NmdTJrS3ZSaHBrUnZ2WkVwUHR3eklpai9nTFpaRWlhelJteWhlZm81WG1QWEZUUd5akt2aW9TIiwiRXhwaXJhdGlvbiI6IjM2MDAiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSJ9"
//        String Address = "eyJFbmRwb2ludCI6Ih0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6Im91Jzdi8zMmIxYWFkNi0xNmQ0ZTMzZWU0Mi8zMmIxYWFkNi0xNmQ0ZTMzZWU0Mi5tcDQifQ=="
//
//        self.uploader = new VODUploadClient.init()
//        weak var weakSelf = self
//        let lister:VODUploadListener = VODUploadListener.init()
//
//        //上传开始：(^OnUploadStartedListener) (UploadFileInfo* fileInfo),fileInfo中存放的是视频信息
//        lister.started = {(info) in
//                XLOG("开始上传")
//                XLOG(info ?.filePath)
//        weakSelf ?.uploader ?.setUploadAuthAndAddress(info, uploadAuth:Auth, uploadAddress:Address)
//         }
//
//        //上传进度:(UploadFileInfo* fileInfo, long uploadedSize, long totalSize), uploadedSize为上传数据量， totalSize为总文件大小
//        lister.progress = {(info, uploadeSize, totalSize)in
//        XLOG("\(uploadeSize)===== 上传中 =====\(totalSize)")
//         }
//
//        //上传完成:(UploadFileInfo* fileInfo, VodUploadResult* result), result存放了视频id或者文件（图片）的文件名
//        lister.finish = {(info, result)in
//        XLOG("上完成")
//        XLOG(result)
//        XLOG(result ?.videoId)
//         }
//
//        //上传失败:(UploadFileInfo* fileInfo, NSString *code, NSString * message)，code为错误码，messgae为错误信息
//        lister.failure = {(info, code, msg)in
//        XLOG("上传失败")
//        XLOG(msg)
//         }
//
//        //上传凭证过期：(^OnUploadTokenExpiredListener) ()在这个回调中重新获取上传凭证
//        lister.expire = {()in
//                XLOG("凭证过期了")
//                weakSelf ?.uploader ?.resume(withAuth:Auth)
//         }
//
//        //重新获取凭证：(^OnUploadRertyResumeListener) ()这个回调表示重新获取上传凭证成功
//        lister.retryResume = {()in
//                XLOG("要重新获取凭证")
//        }
//
//        //重新上传：(^OnUploadRertyListener) ()这个回调表示重新开始上传
//        lister.retry = {()in
//                XLOG("要重新上传了")
//        }
//
//        self.uploader ?.setListener(lister)
//
//
//        let vodInfo:VodInfo = VodInfo.init()
//        vodInfo.title = "laOla-iOS-Test1"
//        vodInfo.desc = ""
//        vodInfo.cateId = 0
//        vodInfo.tags = ""
//
//        self.uploader ?.addFile(videoPath, vodInfo:vodInfo)
//        self.uploader ?.start()
    }
}
