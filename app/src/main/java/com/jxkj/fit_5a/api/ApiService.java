package com.jxkj.fit_5a.api;


import com.jxkj.fit_5a.base.BlackListBean;
import com.jxkj.fit_5a.base.DeviceCourseData;
import com.jxkj.fit_5a.base.DeviceCourseTypeData;
import com.jxkj.fit_5a.base.DeviceData;
import com.jxkj.fit_5a.base.DeviceDrandData;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.GiftListData;
import com.jxkj.fit_5a.base.GiftLogListData;
import com.jxkj.fit_5a.base.HelpListData;
import com.jxkj.fit_5a.base.InterestLists;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.base.ParamData;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.PrizeListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.base.ShoppingCartListBean;
import com.jxkj.fit_5a.base.SignLogData;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.base.UserDetailData;
import com.jxkj.fit_5a.base.UserInfoData;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.entity.AdListData;
import com.jxkj.fit_5a.entity.AddressData;
import com.jxkj.fit_5a.entity.AddressModel;
import com.jxkj.fit_5a.entity.AdminInspireBean;
import com.jxkj.fit_5a.entity.AnnouncementList;
import com.jxkj.fit_5a.entity.BluetoothChannelData;
import com.jxkj.fit_5a.entity.BoxReceiveBean;
import com.jxkj.fit_5a.entity.CircleDetailsBean;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.entity.CircleTaskData;
import com.jxkj.fit_5a.entity.ClassificationAllData;
import com.jxkj.fit_5a.entity.CommentListBean;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.CommunityHomeInfoBean;
import com.jxkj.fit_5a.entity.ConnectRoomInfoBean;
import com.jxkj.fit_5a.entity.ConnectRoomUnfinishedBean;
import com.jxkj.fit_5a.entity.CreateOrderBean;
import com.jxkj.fit_5a.entity.DeviceProtocolCheckData;
import com.jxkj.fit_5a.entity.DiscountUsableNotBean;
import com.jxkj.fit_5a.entity.FavoriteQueryList;
import com.jxkj.fit_5a.entity.FollowFansList;
import com.jxkj.fit_5a.entity.GageRoomCreateBean;
import com.jxkj.fit_5a.entity.GameCompleteBean;
import com.jxkj.fit_5a.entity.GameRoomDetailsBean;
import com.jxkj.fit_5a.entity.GameRoomListBean;
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.entity.JoinQuickAndStartBean;
import com.jxkj.fit_5a.entity.LastUnreadMessageBeanList;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.jxkj.fit_5a.entity.LoginUserThirdInfo;
import com.jxkj.fit_5a.entity.MapDetailsBean;
import com.jxkj.fit_5a.entity.MapListSposrt;
import com.jxkj.fit_5a.entity.MedalListData;
import com.jxkj.fit_5a.entity.MessageSubtypeBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean_X;
import com.jxkj.fit_5a.entity.NotObtainedBean;
import com.jxkj.fit_5a.entity.OrderDetailsData;
import com.jxkj.fit_5a.entity.OssInfoBean;
import com.jxkj.fit_5a.entity.ParameterBean;
import com.jxkj.fit_5a.entity.PostOrderGwcInfo;
import com.jxkj.fit_5a.entity.PostOrderInfo;
import com.jxkj.fit_5a.entity.ProductBannerList;
import com.jxkj.fit_5a.entity.ProductDetailsBean;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.entity.ProductScListBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.QueryPopularMomentBean;
import com.jxkj.fit_5a.entity.QueryPopularMomentBean_sc;
import com.jxkj.fit_5a.entity.QueryPopularMomentDetailsBean;
import com.jxkj.fit_5a.entity.RankDetailsData;
import com.jxkj.fit_5a.entity.RankListData;
import com.jxkj.fit_5a.entity.RankStatsData;
import com.jxkj.fit_5a.entity.RechargeCreateBean;
import com.jxkj.fit_5a.entity.RecommendUser;
import com.jxkj.fit_5a.entity.RewardLogBean;
import com.jxkj.fit_5a.entity.ShowOrderInfo;
import com.jxkj.fit_5a.entity.SignatureBean;
import com.jxkj.fit_5a.entity.SpecListBaen;
import com.jxkj.fit_5a.entity.SportLogBean;
import com.jxkj.fit_5a.entity.SportLogDetailBean;
import com.jxkj.fit_5a.entity.SportLogStatsBean;
import com.jxkj.fit_5a.entity.StsTokenBean;
import com.jxkj.fit_5a.entity.SubmitFilesBean;
import com.jxkj.fit_5a.entity.TaskCircleQueryBean;
import com.jxkj.fit_5a.entity.TeachingMomentBean;
import com.jxkj.fit_5a.entity.TeachingMomentBeanWc;
import com.jxkj.fit_5a.entity.TeachingMomentListsBean;
import com.jxkj.fit_5a.entity.TemplateBean;
import com.jxkj.fit_5a.entity.TopicAllBean;
import com.jxkj.fit_5a.entity.TrainingCourseData;
import com.jxkj.fit_5a.entity.UserOwnInfo;
import com.jxkj.fit_5a.entity.UserReportBean;
import com.jxkj.fit_5a.entity.UserReportType;
import com.jxkj.fit_5a.entity.UserWalletData;
import com.jxkj.fit_5a.entity.VideoInfoBean;
import com.jxkj.fit_5a.entity.VerifyAppOauthQq;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.entity.WalletDetailsBean;
import com.jxkj.fit_5a.entity.WalletListBean;
import com.jxkj.fit_5a.entity.WxAccessTokenBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 任务列表
     *
     * @return 任务类型1:圈子任务2:日常任务3签到任务4活动任务
     */
    @GET(ConstValues.PORT_TASK + "api/v1/user/task/list")
    Observable<Result<TaskListBase>> getUserTaskList(@Query("type") Integer type);

    /**
     * 任务进度更新
     * userTaskId 用户任务id(仅圈子任务时使用)
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/task/update/speed")
    Observable<Result> updateUserTask(@Query("paramId") int paramId, @Query("value") String value,
                                      @Query("userTaskId") String userTaskId);

    /**
     * 用户详情
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/detail")
    Observable<Result<UserDetailData>> getUserDetail();

    /**
     * 获取指定参数
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/feign/parameter/get")
    Observable<Result<ParameterBean>> getParameter(@Query("name") String name);

    /**
     * 添加举报类型
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/user/report/add")
    Observable<Result> postUserReportType(@Body UserReportType mUserReportType);

    /**
     * 将用户从黑名单中移除
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/black/list/remove")
    Observable<Result> postRemoveBlackList(@Query("blUserId") String blUserId);

    /**
     * 获取热门动态信息
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/teaching/moment/query-home-popular")
    Observable<Result<TeachingMomentListsBean>> getQueryHomePopular();

    /**
     * 获取热门动态信息
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/teaching/moment/query-home-popular")
    Observable<Result<TeachingMomentListsBean>> getQueryHomePopular(@Query("page") int page,@Query("pageSize") int pageSize);


    /**
     * 获取所有官方动态分类
     */
    @GET(ConstValues.PORT_21 + "api/v1/official/moment/classification/all")
    Observable<Result<List<ClassificationAllData>>> getClassificationAll();


    /**
     * 获取自己的黑名单列表
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/black/list/own")
    Observable<Result<List<BlackListBean>>> getBlackList();

    /**
     * 将用户加入到黑名单
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/black/list/add")
    Observable<Result> postBlackList(@Query("blUserId") String blUserId);

    /**
     * 获取所有举报类型列表
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/user/report/type/list_all")
    Observable<Result<List<UserReportBean>>> getUserReportList();

    /**
     * 获取所有举报类型列表
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/teaching/moment/details")
    Observable<Result<TeachingMomentBean>> getTeachingMomentDetails(@Query("momentId") String momentId,@Query("publisherId") String publisherId);

    /**
     * 获取教学视频动态信息
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/teaching/moment/query")
    Observable<Result<List<TeachingMomentBean>>> getTeachingMomentQuery(@Query("keyword") String keyword,
            @Query("classificationId") Integer classificationId,@Query("deviceTypeId") Integer deviceTypeId,@Query("page") int page,@Query("pageSize") int pageSize);

    /**
     * 收藏  教学视频动态收藏相关
     */

    @POST(ConstValues.PORT_21 + "api/v1/teaching/favorite")
    Observable<Result> postTeachingFavorite(@Query("momentId") String momentId,@Query("momentPublisherId") String momentPublisherId);

    /**
     * 收藏  教学视频动态收藏相关
     */

    @POST(ConstValues.PORT_21 + "api/v1/teaching/favorite/cancel")
    Observable<Result> postTeachingFavoriteCancel(@Query("momentId") String momentId,@Query("momentPublisherId") String momentPublisherId);
    /**
     * 获取热门(推荐)动态信息
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/official/moment/query_popular")
    Observable<Result<List<QueryPopularMomentBean>>> getQueryPopularMoment(@Query("classificationId") int classificationId,@Query("page") int page,@Query("pageSize") int pageSize);

    /**
     * 获取动态信息
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/official/moment/details")
    Observable<Result<QueryPopularMomentDetailsBean>> getQueryPopularMomentDetails
            (@Query("momentId") String momentId,@Query("publisherId") String publisherId);

    /**
     * 个人主页数据
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/statistic/my")
    Observable<Result<UserInfoData>> getUserStatistic();

    /**
     * 获取用户所有余额
     * @return
     */
    @GET(ConstValues.PORT_8 + "api/v1/user/wallet/all")
    Observable<Result<UserWalletData>> getUserWalletAll();

    /**
     * 用户更新
     *
     * @return
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/update")
    Observable<Result> postUserUpdate(@Body PostUser.UserInfoUpdate userInfoUpdate);


    /**
     * 签到
     *
     * @return
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/sign/add")
    Observable<Result> addUserSign();

    /**
     * 今日是否签到
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/sign/log/today/sign")
    Observable<Result<Boolean>> addUserSignLog();


    /**
     * 会员规格列表
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/level/spec/list")
    Observable<Result<SpecListBaen>> getSpecList(@Query("levelId") String levelId);

    /**
     * 创建会员升级订单
     *
     * @return
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/order/level/create")
    Observable<Result> postCreateLevel(@Query("levelSpecId") String levelSpecId, @Query("hasAuto") boolean hasAuto);


    /**
     * 签到记录
     *
     * @return
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/sign/log/list")
    Observable<Result<SignLogData>> getUserSignLog(@Query("beginCreateTime") String beginCreateTime,
                                                   @Query("endCreateTime") String endCreateTime);

    /**
     * 礼物背包
     * flag:true只显示余额false只显示收到的礼物余额
     *
     * @return
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/gift/list")
    Observable<Result<GiftListData>> getUserGiftList(@Query("flag") boolean flag);

    /**
     * 赠送礼物
     * @return
     */

    @POST(ConstValues.PORT_1 + "api/v1/user/gift/give")
    Observable<Result> postUserGiftGive(@Body PostUser.UserGiftLogFormDTO mUserGiftLogFormDTO);

    /**
     * 礼物转换
     * @return
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/gift/transform")
    Observable<Result> postUserGiftTransform(@Query("ids") List<String> ids);


    /**
     * 礼物赠送记录
     * flag:true只显示余额false只显示收到的礼物余额
     * @return
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/gift/log/list")
    Observable<Result<GiftLogListData>> getUserGiftLogList(@Query("giveFlag") boolean flag,@Query("status") int status,@Query("page") int page,@Query("pageSize") int pageSize);

    /**
     * 礼券列表
     * 状态1待使用,2已使用,3已失效
     *
     * @return bugei
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/prize/list")
    Observable<Result<PrizeListData>> getUserPrizeList(@Query("status") int status,@Query("page") int page,@Query("pageSize") int pageSize);

    /**
     * 用户获取可以使用但未获取的商品满减券
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/prize/discount/usable_not_obtained")
    Observable<Result<DiscountUsableNotBean>> getusable_not_obtained();

    /**
     * 用户获取可以使用但未获取的商品抵扣券
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/prize/credit/usable_not_obtained")
    Observable<Result<PrizeListData>> usable_not_obtained(@Query("productId") String productId);

    /**
     * 领取礼券
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/prize/receive")
    Observable<Result> getPrizeReceive(@Query("couponId") int couponId);

    /**
     * 添加或取消收藏
     * collectFlag	是否收藏 1收藏 0 取消
     */

    @POST(ConstValues.PORT_3 + "api/v1/product/collect/collect")
    Observable<Result> getProductCollect(@Query("collectFlag") int collectFlag,@Query("productId") String productId);

    /**
     * 领取多个礼券
     */

    @POST(ConstValues.PORT_1 + "api/v1/user/prize/receives")
    Observable<Result> getPrizeReceives(@Query("couponIds") List<Integer> couponIds);

    /**
     * 创建充值订单
     */

    @POST(ConstValues.PORT_1 + "api/v1/user/order/recharge/create")
    Observable<Result<RechargeCreateBean>> postCreateRecharge(@Query("amount") String amount, @Query("couponId") String couponId);

    /**
     * 充值获取支付参数
     */

    @POST(ConstValues.PORT_1 + "api/v1/user/order/recharge/pay")
    Observable<Result> orderRechargePay(@Body PostUser.RechargeOrder mRechargeOrder);

    /**
     * 礼物金
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/medal/list")
    Observable<ResultList<MedalListData>> getUserMedalList1();

    /**
     * 勋章列表
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/medal/list")
    Observable<ResultList<MedalListData>> getUserMedalList();

    /**
     * 圈子任务列表
     */
    @GET(ConstValues.PORT_TASK + "api/v1/user/circle/task/list")
    Observable<ResultList<CircleTaskData>> getCircleTaskList(@Query("deviceType") String deviceType,@Query("status") String status);

    /**
     * 排行榜列表
     */
    @GET(ConstValues.PORT_1 + "api/v1/rank/list")
    Observable<Result<RankListData>> getRankList(@Query("type") int type);

    /**
     * 排行榜详情
     */
    @GET(ConstValues.PORT_1 + "api/v1/rank/details")
    Observable<Result<RankDetailsData>> getRankDetails(@Query("rankId") Integer rankId);

    /**
     * 排行榜排名\
     * 维度(1:天;2:周;3:月)
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/ranking/list/stats/calories/current")
    Observable<Result<RankStatsData>> getRankStatsList(@Query("dimension") int dimension);

    /**
     * 排行榜排名\
     * 维度(1:天;2:周;3:月)
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/ranking/list/stats/city/calories/current")
    Observable<Result<RankStatsData>> getRankStatsList_city(@Query("dimension") int dimension,@Query("cityAdCode") String cityAdCode);

    /**
     * 排行榜点赞
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/cal/rank/zan/day/like")
    Observable<Result> getStatsZan(@Query("calRankId") String calStatsId, @Query("dimension") int dimension);
    /**
     * 排行榜取消点赞
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/cal/rank/zan/day/cancel_like")
    Observable<Result> getCancelStatsZan(@Query("calRankId") String calStatsId, @Query("dimension") int dimension);


    /**
     * 注册成功后————兴趣列表
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/interest/query")
    Observable<Result<InterestLists>> getInterestList();

    /**
     * 获取教学分类信息
     */
    @GET(ConstValues.PORT_2 + "api/v1/teaching/classification/query")
    Observable<Result<List<ClassificationAllData>>> getClassificationQuery();

    /**
     * 帮助列表
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/help/query")
    Observable<Result<HelpListData>> getHelpList();

    /**
     * 广告列表
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/ad/query")
    Observable<Result<AdListData>> getAdList(@Query("seat") String seat);

    /**
     * 公告列表
     * @return
     */
    @GET(ConstValues.PORT_MESSAGE + "api/v1/announcement/list")
    Observable<Result<AnnouncementList>> getAnnouncementList();

    /**
     * 获取公告url
     * @return
     */
    @GET(ConstValues.PORT_MESSAGE + "api/v1/announcement/url")
    Observable<Result<String>> getAnnouncementUrl(@Query("id") String id);

    /**
     * 用户设备列表 未完善
     * deviceId 设备id
     * deviceNo	设备编号
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/user/device/query")
    Observable<Result> queryUserDeviceList();

    /**
     * 设备类型列表
     * 	设备分类(1,健身器材;2,心率设备)
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/type/query")
    Observable<Result<DeviceTypeData>> queryDeviceTypeLists(@Query("type") int type);

    /**
     * 设备品牌列表
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/brand/query")
    Observable<Result<DeviceDrandData>> queryDeviceBrandLists(@Query("page") String page, @Query("pageSize") String pageSize);


    /**
     * 设备型号列表
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/model/query")
    Observable<Result<DeviceData>> queryDeviceModelLists(
            @Query("deviceBrandId") String deviceBrandId, @Query("deviceTypeId") String deviceTypeId,@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 设备型号列表
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/model/instructions_for_use_url")
    Observable<Result<String>> queryInstructions_for_use_url(@Query("id") String id);


    /**
     * 设备型号列表
     * @return  [-16, -96, 56, -45, -101]
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/type/bluetooth/channel/query")
    Observable<ResultList<BluetoothChannelData>> getBluetoothChannel(@Query("deviceTypeId") String deviceTypeId, @Query("protocolName") String protocolName);


    /**
     *  检查协议参数是否符合
     * @param deviceBrandId 设备品牌id
     * @param deviceBrandParamId 设备品牌参数id(iconsole协议下为client id)
     * @param deviceModelId 设备型号id
     * @param deviceTypeId 设备类型id
     * @param deviceTypeParamId 设备类型参数id(iconsole协议下为meter id)
     * @param protocolName 	协议名称(仪器厂商, 目前只有iconsole)
     * @param deviceModelParamId 协议对应的型号参数id(iconsole协议下没有该字段)
     * @return deviceBrandId=56
     * &deviceBrandParamId=55
     * &deviceModelId=35
     * &deviceTypeId=3
     * &deviceTypeParamId=0
     * &protocolName=iconsole
     *
     * deviceBrandId=56
     * &deviceBrandParamId=55
     * &deviceModelId=24
     * &deviceTypeId=3
     * &deviceTypeParamId=0&
     * protocolName=iconsole
     */
    @POST(ConstValues.PORT_2 + "api/v1/device/protocol/check")
    Observable<Result<DeviceProtocolCheckData>> postDeviceProtocolCheck(@Query("deviceBrandId")String deviceBrandId,//
                                                                        @Query("deviceBrandParamId")String deviceBrandParamId,
                                                                        @Query("deviceModelId")String deviceModelId,
                                                                        @Query("deviceTypeId")String deviceTypeId,
                                                                        @Query("deviceTypeParamId")String deviceTypeParamId,
                                                                        @Query("protocolName")String protocolName,
                                                                        @Query("deviceModelParamId")String deviceModelParamId);
    //https://5afit.nbqichen.com/sysconfig/api/v1/device/protocol/
    // check?
    // deviceBrandId=2&
    // deviceBrandParamId=55&
    // deviceModelId=0&
    // deviceTypeId=3&
    // deviceTypeParamId=0&
    // protocolName=iconsole http response = {"code":"000000","mesg":"处理成功","sub_code":"000000","sub_mesg":"处理成功","time":"2021-03-31T07:39:34.586Z","data":{"result":false,"deviceBrandTypeModel":{"deviceBrand":{"id":56,"name":"HEAD FITNESS","protocolName":"iconsole","paramId":"55","img":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/device/1kL4w9LWwasXTRcNLcjteA.png"},"deviceType":{"id":9,"name":"室内单车/椭圆机","protocolName":"iconsole","paramId":"0","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png"},"deviceModel":null}}}


    /**
     * 设备课程类型列表
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/course/query")
    Observable<Result<DeviceCourseData>> queryDeviceCourseList(@Query("level") String level, @Query("type") String type);

    /**
     * 设备课程类型列表
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/course/type/query")
    Observable<Result<DeviceCourseTypeData>> queryDeviceCourseTypeList(@Query("deviceTypeId") String deviceTypeId);

    /**
     * 获取训练课程信息
     */
    @GET(ConstValues.PORT_2 + "api/v1/training/course/query")
    Observable<Result<List<TrainingCourseData>>> trainingCourseQueryList(@Query("deviceTypeId") String deviceTypeId);

    /**
     * 设备课程详情
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/course/details")
    Observable<Result<DeviceCourseData.ListBean>> queryDeviceCourseTypeDetails(@Query("id") String id);



    /**
     * 获取sts-token
     */
    @GET(ConstValues.PORT_5 + "api/v1/oss/info")
    Observable<Result<OssInfoBean>> getOssInfo();


    /**
     * 获取sts-token
     */
    @GET(ConstValues.PORT_5 + "api/v1/oss/signature")
    Observable<Result<SignatureBean>> getSignature(@Query("dir") String dir);


    /**
     * 获取sts-token
     */
    @GET(ConstValues.PORT_5 + "api/v1/sts/token")
    Observable<Result<StsTokenBean>> getStsToken();
    /**
     * 获取短信验证码
     * @return type:类型0注册1修改密码2登录
     *
     */
    @GET(ConstValues.PORT_5 + "api/v1/user/verify/getVerifyCode")
    Observable<Result> getVerifyCode(@Query("mobile") String mobile, @Query("type") int type);

    /**
     * 注册
     *
     * @return type:客户端类型1web2IOS3安卓4微信
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/verify/register")
    Observable<Result<LoginInfo>> userVerifyRegister(@Query("clientType") int clientType,
                                          @Query("phone") String phone, @Query("password") String password,
                                          @Query("verify") String verify);

    /**
     * 登录
     *
     * @return clientType:客户端类型1web2IOS3安卓4微信
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/verify/login")
    Observable<Result<LoginInfo>> userVerifyLogin(@Query("clientType") int clientType,
                                                  @Query("phone") String phone, @Query("password") String password,
                                                  @Query("verify") String verify);


    /**
     * 忘记密码
     *
     * @return type:类型0注册1修改密码2登录
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/verify/forgetPassword")
    Observable<Result> userForgetPassword(@Query("password") String password,
                                          @Query("phone") String phone,
                                          @Query("verify") String verify);

    /**
     * 修改密码，只有绑定手机之后才能使用
     *
     * @return type:类型0注册1修改密码2登录
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/bind/third/changePassword")
    Observable<Result> userChangePassword(@Query("oldPassword") String oldPassword,
                                          @Query("password") String password);

    /**
     *获取视频上传地址和凭证
     */
    @GET(ConstValues.PORT_5 + "api/v1/video/upload")
    Observable<Result<VideoInfoBean>> getUploadVideo(@Query("fileName") String fileName, @Query("title") String title, @Query("coverUrl") String coverUrl);

    /**
     *上传视频文件
     * @Query("fileName") String fileName, @Query("title") String title, @Query("coverUrl") String coverUrl
     */
    @Multipart
    @POST(ConstValues.PORT_5 + "api/v1/video/upload_video")
    Observable<Result> getUpload_Video(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    /**
     * 账号绑定 未完善
     *
     * @return clientType:客户端1web2ios3安卓4微信】
     * 登录方式1手机号码2微信3QQ4新浪5iconsole
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/bind/third/bind")
    Observable<Result<LoginInfo>> userThirdBind(@Query("clientType") int clientType, @Query("phone") String phone,
                                                    @Query("verify") String verify,@Query("password") String pas);
    /**
     * 获取用户已经绑定过的第三方信息
     */
    @GET(ConstValues.PORT_5 + "api/v1/user/bind/third/list")
    Observable<Result<List<LoginUserThirdInfo>>> getUserBind();

    /**
     *app 通过qq验证登陆
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/verify/qq/app/{name}/oauth")
    Observable<Result<VerifyAppOauthQq>> postVerifyAppOauth(@Path("name") String name, @Query("accessToken") String accessToken);

    /**
     *获取accessToken
     * @return
     *
    String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ConstValues.WX_APP_ID+
    "&secret="+ConstValues.WX_APP_SECRET+"&code="+code+"&grant_type=authorization_code";
     */
    @GET("https://api.weixin.qq.com/sns/oauth2/access_token")
    Observable<WxAccessTokenBean> getWx_access_token(@Query("grant_type") String grant_type, @Query("appid") String appid, @Query("secret") String secret, @Query("code") String code);

    /**
     * 微信登录
     * @return
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/verify/weixin/app/{name}/oauth")
    Observable<Result<VerifyAppOauthQq>> postVerifyAppOauthWx(@Path("name") String name,@Query("openId") String openId,@Query("accessToken") String accessToken);

    /**
     * 获取视频播放地址
     */
    @GET(ConstValues.PORT_5 + "api/v1/video/play_info")
    Observable<Result<VideoPlayInfoBean>> getPlay_info(@Query("definition") String definition, @Query("videoId") String videoId);

    /**
     * app授权登录
     *
     * @return
     */
    @POST("api/v1/user/verify/appEmpower")
    @FormUrlEncoded
    Observable<Result> verifyAppEmpower(@Field("accessToken") String accessToken,
                                        @Field("openId") String openId,
                                        @Field("clientType") int clientType,
                                        @Field("registrationId") String registrationId,
                                        @Field("inviteCode") String inviteCode);

    /**
     * 加入购物车
     * @return
     */
    @POST(ConstValues.PORT_3 + "api/v1/user/shoppingCart/add")
    Observable<Result> shoppingCartAdd(@Body PostOrderGwcInfo mPostOrderGwcInfo);
//


    /**
     * 购物车-修改商品数量
     */
    @POST(ConstValues.PORT_3 + "api/v1/user/shoppingCart/updateNum")
    Observable<Result> shoppingChangeQuantity(@Body PostOrderGwcInfo mPostOrderGwcInfo);


    /**
     * 购物车-删除购物车
     */
    @POST(ConstValues.PORT_3 + "api/v1/user/shoppingCart/deleteById")
    Observable<Result> shoppingChangeDelete(@Query("id") String id);

    /**
     * 购物车-用户购物车列表
     */
    @GET(ConstValues.PORT_3 + "api/v1/user/shoppingCart/list")
    Observable<Result<ShoppingCartListBean>> shoppingCartList();

    /**
     * 购物车-用户购物车列表
     */
    @GET(ConstValues.PORT_3 + "api/v1/user/shoppingCart/getNum")
    Observable<Result<Integer>> shoppingCartGetNum();
    /**
     * 商品列表
     */
    @GET(ConstValues.PORT_3 + "api/v1/product/list")
    Observable<Result<ProductListBean>> getProductList(@Query("hasHot") Integer hasHot,@Query("hasNew")Integer hasNew,@Query("type") String type);

    /**
     * 商品列表
     */
    @GET(ConstValues.PORT_3 + "api/v1/product/list")
    Observable<Result<ProductListBean>> getProductList(@Query("randFlag") Integer randFlag,@Query("hasHot") Integer hasHot,@Query("sort") Integer sort,
                                                       @Query("type") String type, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 收藏列表
     */
    @GET(ConstValues.PORT_3 + "api/v1/product/collect/list")
    Observable<Result<ProductScListBean>> getProductCollectList(@Query("keyword") String keyword, @Query("sort") Integer sort, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 商品列表
     */
    @GET(ConstValues.PORT_3 + "api/v1/product/list")
    Observable<Result<ProductListBean>> getProductList(@Query("keyword") String keyword,@Query("integralBegin") Integer integralBegin,
                                                       @Query("integralEnd") Integer integralEnd,
                                                       @Query("priceBegin") Integer priceBegin,
                                                       @Query("priceEnd") Integer priceEnd,
                                                       @Query("type") String type,
                                                       @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 积分商城
     */
    @GET(ConstValues.PORT_3 + "api/v1/shop/banner/list")
    Observable<Result<ProductBannerList>> getBannerList();

    /**
     * 商品详情
     */
    @GET(ConstValues.PORT_3 + "api/v1/product/details")
    Observable<Result<ProductDetailsBean>> getProductDetails(@Query("id") String id);

    /**
     * 商品评论列表
     */
    @GET(ConstValues.PORT_3 + "api/v1/comment/list")
    Observable<Result<CommentListBean>> getCommentList(@Query("productId") String productId,@Query("page") int page, @Query("pageSize") int pageSize);


    /**
     * 获取用户所有收货地址列表
     *
     * @return
     */
    @GET(ConstValues.PORT_3 + "api/v1/user/address/list")
    Observable<Result<AddressModel>> getUserAddress(@Query("page") String page, @Query("pageSize") String pageSize);


    /**
     * 设置默认地址
     *
     * @return
     */

    @POST(ConstValues.PORT_3 + "api/v1/user/address/updateDefault")
    Observable<Result> getSetDefault(@Query("id") String id);


    /**
     * 删除地址
     */
    @POST(ConstValues.PORT_3 + "api/v1/user/address/delete")
    Observable<Result> getDeleteAddress(@Query("id") String id);


    /**
     * 新增地址
     *
     * @return
     */
    @POST(ConstValues.PORT_3 + "api/v1/user/address/save")
    Observable<Result> getAddAddress(@Body AddressData data);

    /**
     * 修改地址
     *
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/address/update")
    Observable<Result> getUpdateAddress(@Body AddressData data);

    /**
     * 下单预览
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/showOrderInfo")
    Observable<Result<ShowOrderInfo>> postShowOrderInfo(@Body PostOrderInfo data);

    /**
     * 创建订单
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/createOrder")
    Observable<Result<CreateOrderBean>> postcreateOrder(@Body PostOrderInfo data);

    /**
     * 发货提醒
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/expediting")
    Observable<Result> postExpediting(@Query("orderId") String orderId);
    /**
     * 支付
     * orderId	订单Id
     * orderType	支付类型：1,小程序;2,公众号;3,app；4，扫码
     * payType	支付方式:1,支付宝支付;2,微信支付;3,银行卡支付;4,余额支付
     * subPayType	订单类型
     */

    @POST(ConstValues.PORT_3 +"api/v1/user/order/payOrder")
    Observable<Result<ParamData>> getOrderPayInfo(@Query("integral") String integral,
                                                      @Query("orderId") String orderId,
                                                      @Query("payType") String payType,
                                                      @Query("redId") String redId,
                                                      @Query("wxPayType") String wxPayType);
    /**
     * 取消订单
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/cancelOrder")
    Observable<Result> postCancelOrder(@Query("id") String id);


    /**
     * 删除订单
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/delete")
    Observable<Result> postDelete(@Query("orderId") String orderId);

    /**
     * 完成订单
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/finishOrder")
    Observable<Result> postFinishOrder(@Query("id") String id);

    /**
     * 订单评论
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/comment")
    Observable<Result> postCommentOrder(@Body PostUser.Comment comment);


    /**
     * 订单列表
     * @param page
     * @param pageSize
     * @param status 单个订单状态1,待支付;2,待发货;3,待收货;4,待评价;5,已完成;6,已取消;7,已过期;8,已结束
     * @return
     */
    @GET(ConstValues.PORT_3 +"api/v1/user/order/list")
    Observable<Result<OrderInfoData>> getOrderAll(@Query("page") int page,
                                                  @Query("pageSize") int pageSize,
                                                  @Query("status") String status);


    /**
     * 订单详情
     */
    @GET(ConstValues.PORT_3 +"api/v1/user/order/details")
    Observable<Result<OrderDetailsData>> getOrderDetails(@Query("id") String id);


    /**
     * 地图列表
     */
    @GET(ConstValues.PORT_4 + "api/v1/sport/map/list")
    Observable<Result<MapListSposrt>> getSportMapList(@Query("page") int page, @Query("pageSize") int pageSize,@Query("deviceTypeId") String deviceTypeId);


    /**
     * 地图详情
     *
     * @param id
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/map/details")
    Observable<Result<MapDetailsBean>> getMapDetails(@Query("id") String id);

    /**
     * 地图详情-随机
     * @param deviceTypeId
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/map/random")
    Observable<Result<MapDetailsBean>> getMapRandomDetails(@Query("deviceTypeId") String deviceTypeId);

    /**
     * 运动记录统计
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/log/stats")
    Observable<Result<SportLogStatsBean>> getSportLogStats(@Query("beignCreateTimestamp") String beignCreateTimestamp,
                                                           @Query("endCreateTimestamp") String endCreateTimestamp, @Query("deviceTypeId") String deviceTypeId);

    /**
     * 宝箱领取
     * @param
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/log/user_sync_iconsole")
    Observable<Result> getBoxReceive();

    /**
     * 任务奖励记录列表
     * @param
     */
    @GET(ConstValues.PORT_1 + "api/v1/task/reward/log/query")
    Observable<Result<RewardLogBean>> getRewardLogQuery(@Query("type") int type,@Query("beginCreateTime") String beginCreateTime,@Query("isGetFinishTask") Boolean isGetFinishTask);
    /**
     * 宝箱领取
     * @param
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/box/receive")
    Observable<Result<BoxReceiveBean>> getBoxReceive(@Query("boxId") String boxId, @Query("mapId") String mapId);


    /**
     * 文字模板列表
     */
    @GET(ConstValues.PORT_4 + "api/v1/str/template/query")
    Observable<Result<TemplateBean>> getTemplateList();


    /**
     * 运动记录添加
     */
    @POST(ConstValues.PORT_4 + "api/v1/user/sport/log/add")
    Observable<Result<String>> psotUserSportLog(@Body PostUser.SportLogInfo postUser);


    /**
     * 运动记录列表
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/log/list")
    Observable<Result<SportLogBean>> geSportLogList(@Query("beignCreateTimestamp") String beignCreateTimestamp,
                                                    @Query("endCreateTimestamp") String endCreateTimestamp,@Query("deviceTypeId") String deviceTypeId,@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 运动记录详情
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/log/details")
    Observable<Result<SportLogDetailBean>> geSportLogDetails(@Query("id") String logid);


    /**
     * 余额详情  	余额类型1金豆,2卡路里
     */
    @GET(ConstValues.PORT_8 + "api/v1/user/wallet/details")
    Observable<Result<WalletDetailsBean>> getWalletDetails(@Query("type") int type);


    /**
     * 余额详情  	余额类型1金豆,2卡路里
     */
    @GET(ConstValues.PORT_8 + "api/v1/user/wallet/list")
    Observable<Result<WalletListBean>> getWalletList(@Query("beginCreateTime") String beginCreateTime, @Query("endCreateTime") String endCreateTime,
                                                     @Query("inOrOut") int inOrOut, @Query("type") int type);


    /**
     * 上传文件
     *
     * @return
     */
    @Multipart
    @POST(ConstValues.POPT_LS + "region/api/v1/files")
    Observable<Result<SubmitFilesBean>> submitFiles(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);


    /**
     * 获取圈子列表
     *
     * @param deviceType
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/query")
    Observable<Result<CircleQueryJoinedBean>> getCircleQuery(@Query("deviceType") int deviceType,@Query("page") int page,@Query("pageSize") int pageSize);

    /**
     * 创建圈子
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/create")
    Observable<Result> getCircleCreate(@Body PostUser.CreateCircle postUser);

    /**
     * 获取圈子详情
     *
     * @param id
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/details")
    Observable<Result<CircleDetailsBean>> getCircleDetails(@Query("id") String id);

    /**
     * 用户加入圈子
     *
     * @param circleId
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/cricle/member/join")
    Observable<Result> getCircleJoin(@Query("circleId") String circleId);

    /**
     * 用户退出圈子
     *
     * @param circleId
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/cricle/member/quit")
    Observable<Result> getCircleQuit(@Query("circleId") String circleId);

    /**
     * 获取圈子预设任务列表
     *
     * @return
     */
    @GET(ConstValues.PORT_TASK + "api/v1/task/circle/target/query")
    Observable<Result<TaskCircleQueryBean>> getTaskCircleQuery(@Query("deviceType") int deviceType);


    /**
     * 获取所有分类以及话题
     */
    @GET(ConstValues.PORT_21 + "api/v1/topic/all")
    Observable<ResultList<TopicAllBean>> getTopicAll();

    /**
     * 获取话题详情
     */
    @GET(ConstValues.PORT_21 + "api/v1/topic/details")
    Observable<Result<TopicAllBean>> getTopicDetails(@Query("topicName") String topicName);


    /**
     * 子消息类型列表
     */
    @GET(ConstValues.PORT_MESSAGE + "api/v1/message/subtype/list")
    Observable<Result<List<MessageSubtypeBean>>> getMessageSubtypeList();


    /**
     * 子消息类型列表
     */
    @POST(ConstValues.PORT_MESSAGE + "api/v1/message/set_all_read")
    Observable<Result> setAllRead();


    /**
     *消息列表
     */
    @GET(ConstValues.PORT_MESSAGE + "api/v1/message/list")
    Observable<Result<LastUnreadMessageBeanList>> getMessageList(@Query("subType") String subType,@Query("page")int page,@Query("pageSize")int pageSize);

    /**
     *设置为已读
     * // api/v1/message/list
     */
    @POST(ConstValues.PORT_MESSAGE + "api/v1/message/set_read")
    Observable<Result> getMessagSetRead(@Query("ids") String ids);


    /**
     * 用户发布动态--社区
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/publish")
    Observable<Result> postPublishMoment(@Query("content") String content, @Query("contentType") String contentType,
                                         @Query("shareType") String shareType,  @Query("media") String media,
                                         @Query("position") String position, @Query("location") String location,
                                         @Query("topics") String[] topics,@Query("circleId") String circleId,
                                         @Query("isSyncPersonalMoment") boolean isSyncPersonalMoment);
//
//    /**
//     * 用户发布动态--社区
//     *
//     * @return
//     */
//    @POST(ConstValues.PORT_21 + "api/v1/moment/publish")
//    Observable<Result> postPublishMoment(@Query("content") String content, @Query("contentType") String contentType,
//                                         @Query("shareType") String shareType, @Query("media") String media,
//                                         @Query("position") String position, @Query("location") String location,
//                                         @Query("topics") String[] topics);

    /**
     * 用户发布动态--圈子
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/moment/publish")
    Observable<Result> postPublishMomentCircle(@Query("circleId") String circleId, @Query("content") String content,
                                               @Query("contentType") String contentType, @Query("shareType") String shareType,
                                               @Query("location") String location, @Query("media") String media,
                                               @Query("position") String position, @Query("topics") String topics);

    /**
     * 用户发布动态--删除圈子
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/moment/delete")
    Observable<Result> postDeleteMomentCircle(@Query("circleId") String circleId,  @Query("momentId") String momentId);

    /**
     * 获取热门(推荐)动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_popular")
    Observable<ResultList<QueryPopularBean>> getMomentQueryPopular(@Query("page")int page,@Query("pageSize")int pageSize);//首页1

    /**
     * 获取最新的动态
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_newest")
    Observable<ResultList<QueryPopularBean>> getQueryNewest(@Query("momentLocalMinId")String momentLocalMinId,@Query("limit")int limit);

    /**
     *查询激励语表
     */
    @GET(ConstValues.PORT_2 + "api/v1/inspire/get")
    Observable<Result<AdminInspireBean>> getAdminInspire();

    /**
     * 获取指定话题下的热门动态(按照热门指数来排序)
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_hot_by_topic")
    Observable<ResultList<QueryPopularBean>> query_hot_by_topic(@Query("keyword")String keyword,@Query("contentType")String contentType,@Query("topic")String topic,@Query("page")int page,@Query("pageSize")int pageSize);

    /**
     * 获取指定话题下的热门动态(按照热门指数来排序)
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_by_topic")
    Observable<ResultList<QueryPopularBean>> query_by_topic(@Query("keyword")String keyword,@Query("contentType")String contentType,@Query("topic")String topic,@Query("page")int page,@Query("pageSize")int pageSize);


    /**
     * 根据内容搜索发布的动态信息==圈子
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/moment/query_by_keyword")
    Observable<ResultList<QueryPopularBean>> getQueryByKeyword(@Query("keyword") String keyword,@Query("circleId") String circleId);

    /**
     * 获取自己发布的动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_own")
    Observable<ResultList<QueryPopularBean>> getQueryByPublisherOwn(@Query("momentLocalMinId") String momentLocalMinId, @Query("contentType") int contentType,@Query("limit") int limit);

    /**
     * 根据发布人获取动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_by_publisher")
    Observable<ResultList<QueryPopularBean>> getQueryByPublisher(@Query("momentLocalMinId") String momentLocalMinId
            , @Query("publisherId") String publisherId
            , @Query("contentType") int contentType);


    /**
     * 获取最近动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/moment/query_lately")
    Observable<ResultList<QueryPopularBean>> getQguery_lately(@Query("circleId") String circleId, @Query("contentType") int contentType);
    /**
     * 获取最近动态信息--topic
     */
    @GET(ConstValues.PORT_21 + "api/v1/topic/moment/query_lately")
    Observable<ResultList<QueryPopularBean>> getQguery_lately_topic(@Query("topicId") String topicId, @Query("contentType") int contentType);

    /**
     * 获取热门(推荐)动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/moment/query_popular")
    Observable<ResultList<QueryPopularBean>> getQuery_popular(@Query("circleId") String circleId, @Query("contentType") int contentType);

    /**
     * 社群首页
     */
    @GET(ConstValues.PORT_21 + "api/v1/community/home/info")
    Observable<Result<CommunityHomeInfoBean>> getCommunityHomeInfo();

    /**
     * 获取关注的人的动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_by_follower")
    Observable<Result<List<QueryPopularBean>>> query_by_follower(@Query("momentLocalMinId") String momentLocalMinId,@Query("limit") int limit);


    /**
     * 获取关注的人的动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_related")
    Observable<Result<List<QueryPopularBean>>> query_related(@Query("circleId") String circleId,@Query("momentId") String momentId,@Query("publisherId") String publisherId,@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 获取关注的圈子的动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_by_circle_follower")
    Observable<Result<List<QueryPopularBean>>> query_by_circle_follower(@Query("momentLocalMinId") String momentLocalMinId,@Query("limit") int limit);

    /**
     * 根据圈子id获取动态信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_by_circle")
    Observable<Result<List<QueryPopularBean>>> query_by_circle(@Query("circleId") String circleId,@Query("contentType") String contentType,@Query("keyword") String keyword,@Query("momentLocalMinId") String momentLocalMinId,@Query("limit") int limit);

    /**
     *
     * 获取推荐圈子
     */
    @GET(ConstValues.PORT_21 + "api/v1/community/home/recommend_circle")
    Observable<Result<List<CircleDetailsBean>>> recommend_circle(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     *
     * 获取热门话题
     */
    @GET(ConstValues.PORT_21 + "api/v1/community/home/hot_tpoic")
    Observable<Result<List<HotTopicBean>>> hot_tpoic(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     *
     * 获取推荐的用户
     */
    @GET(ConstValues.PORT_21 + "api/v1/community/home/recommend_user")
    Observable<Result<List<RecommendUser>>> recommend_user(@Query("page") int page, @Query("pageSize") int pageSize);


    /**
     * 获取用户已经加入的圈子列表
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/query_joined")
    Observable<Result<CircleQueryJoinedBean>> getCircleQueryJoined(@Query("userId")String userId,@Query("page") int page, @Query("pageSize") int pageSize);


    /**
     * 获取自己已经加入的圈子列表
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/query_own_joined")
    Observable<Result<CircleQueryJoinedBean>> getCircleQueryJoinedOwn(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 获取用户自身资料信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/user/profile/own")
    Observable<Result<UserOwnInfo>> getUserProfileOwn();

    /**
     * 获取用户基础资料信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/user/profile/query_bases")
    Observable<Result<List<UserOwnInfo>>> getUserBases(@Query("userIds") List<Integer> userIds);

    /**
     * 获取用户资料信息
     */
    @GET(ConstValues.PORT_21 + "api/v1/user/profile")
    Observable<Result<UserOwnInfo>> getUserProfile(@Query("userId") String userId);

    /**
     * 获取用户的粉丝列表
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/follow/fans")
    Observable<FollowFansList> getFollowFansList(@Query("userId") String userId,@Query("page") int page, @Query("pageSize") int pageSize);


    /**
     *获取用户的关注列表
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/follow/followers")
    Observable<FollowFansList> getFollowFollowers(@Query("userId") String userId,@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 关注用户
     * @param followerId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/follow")
    Observable<Result> postfollow(@Query("followerId") String followerId);

    /**
     * 关注用户
     * @param
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/follow/batch")
    Observable<Result> followBatch(@Query("followerIds") Integer[] followerIds);

    /**
     * 取消关注用户
     * @param followerId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/follow/cancel")
    Observable<Result> postfollowCancel(@Query("followerId") String followerId);

    /**
     * 获取用户的收藏列表
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/favorite/query")
    Observable<ResultList<FavoriteQueryList>>getFavoriteQueryOwn(@Query("userId") String userId,@Query("localMinId") String localMinId,@Query("limit") int limit);

    /**
     * 获取用户的收藏列表
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/official/favorite/query")
    Observable<ResultList<QueryPopularMomentBean_sc>>getFavoriteQueryOwn_gf(@Query("userId") String userId,@Query("localMinId") String localMinId,@Query("limit") int limit);

    /**
     * 获取自己的收藏列表
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/favorite/query_own")
    Observable<ResultList<FavoriteQueryList>>getFavoriteQueryOwn(@Query("localMinId") String localMinId,@Query("limit") int limit);

    /**
     * 获取用户的收藏列表-视频
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/teaching/favorite/query")
    Observable<ResultList<TeachingMomentBeanWc>>getFavoriteQuery_sp(@Query("userId") String userId,@Query("localMinId") String localMinId,@Query("limit") int limit);

    /**
     * 获取自己的收藏列表--视频
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/teaching/favorite/query_own")
    Observable<ResultList<TeachingMomentBeanWc>>getFavoriteQueryOwn_sp(@Query("localMinId") String localMinId, @Query("limit") int limit);

    /**
     * 获取自己的收藏列表
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/official/favorite/query_own")
    Observable<ResultList<QueryPopularMomentBean_sc>>getFavoriteQueryOwn_gf(@Query("localMinId") String localMinId, @Query("limit") int limit);

    /**
     * 收藏
     * 	圈子id(如果收藏的不是圈子里的动态，传0)
     */
    @POST(ConstValues.PORT_21 + "api/v1/favorite")
    Observable<Result> postFavorit(@Query("circleId") String circleId,
                                   @Query("momentId") String momentId,
                                   @Query("momentPublisherId") String momentPublisherId);

    /**
     * 收藏
     * 	圈子id(如果收藏的不是圈子里的动态，传0)
     */
    @POST(ConstValues.PORT_21 + "api/v1/official/favorite")
    Observable<Result> postFavorit_gf(@Query("momentId") String momentId,
                                   @Query("momentPublisherId") String momentPublisherId);


    /**
     * 取消收藏
     * 	圈子id(如果收藏的不是圈子里的动态，传0)
     */
    @POST(ConstValues.PORT_21 + "api/v1/favorite/cancel")
    Observable<Result> postFavoritCancel(@Query("circleId") String circleId,
                                         @Query("momentId") String momentId);

    /**
     * 取消收藏
     * 	圈子id(如果收藏的不是圈子里的动态，传0)
     */
    @POST(ConstValues.PORT_21 + "api/v1/official/favorite/cancel")
    Observable<Result> postFavoritCancel_gw(@Query("momentId") String momentId);


    /**
     * 用户点赞动态
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/like")
    Observable<Result> postLike(@Query("circleId") String circleId,@Query("momentId") String momentId,
                                   @Query("momentPublisherId") String momentPublisherId);

    /**
     * 用户点赞动态
     */
    @POST(ConstValues.PORT_21 + "api/v1/official/moment/like")
    Observable<Result> postLike_gf(@Query("momentId") String momentId,
                                   @Query("momentPublisherId") String momentPublisherId);

    /**
     * 用户取消点赞动态--圈子
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/like/cancel")
    Observable<Result> postLikeCancel(@Query("circleId") String circleId,@Query("momentId") String momentId,
                                         @Query("momentPublisherId") String momentPublisherId);

    /**
     * 用户取消点赞动态--圈子
     */
    @POST(ConstValues.PORT_21 + "api/v1/official/moment/like/cancel")
    Observable<Result> postLikeCancel_gf(@Query("momentId") String momentId,
                                         @Query("momentPublisherId") String momentPublisherId);

    /**
     * 用户点赞动态评论
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/comment/like")
    Observable<Result> postCommentLike(@Query("commentId") String commentId,
                                   @Query("momentId") String momentId);

    /**
     * 用户点赞动态评论
     */
    @POST(ConstValues.PORT_21 + "api/v1/official/moment/comment/like")
    Observable<Result> postCommentLike_gf(@Query("commentId") String commentId,
                                   @Query("momentId") String momentId);


    /**
     * 用户取消点赞动态评论
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/comment/like/cancel")
    Observable<Result> postCommentLikeCancel(@Query("commentId") String commentId,
                                         @Query("momentId") String momentId);

    /**
     * 用户取消点赞动态评论
     */
    @POST(ConstValues.PORT_21 + "api/v1/official/moment/comment/like/cancel")
    Observable<Result> postCommentLikeCancel_gf(@Query("commentId") String commentId,
                                         @Query("momentId") String momentId);
    /**
     * 获取热门话题
     * @return
     */
    @GET(ConstValues.PORT_21 +"api/v1/topic/hot")
    Observable<Result<List<HotTopicBean>>> getHotTopicList(@Query("keyword") String keyword,@Query("page") int page,@Query("pageSize")int pageSize);

    /**
     * 获取自己参与过的话题
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/topic/participated")
    Observable<Result<List<HotTopicBean>>> getTopicParticipated(@Query("keyword") String keyword,@Query("page") int page,@Query("pageSize")int pageSize);


    /**
     * 获取所有话题
     * @param keyword
     * @param page
     * @param pageSize
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/topic/all_topic")
    Observable<Result<List<HotTopicBean>>> getAllTopic(@Query("keyword") String keyword,@Query("page") int page,@Query("pageSize")int pageSize);


    /**
     * 根据内容搜索发布的动态信息
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/query_by_keyword")
    Observable<ResultList<QueryPopularBean>> getQuery_by_keyword(@Query("keyword") String keyword,@Query("contentType") String contentType,@Query("page") int page,@Query("pageSize")int pageSize);


    /**
     * 根据内容搜索发布的动态信息
     */
    @GET(ConstValues.PORT_21+"api/v1/community/home/search")
    Observable<Result<HomeSearchBean>> getHomeSearch(@Query("keyword") String keyword,@Query("searchType") String searchType,@Query("page") int page,@Query("pageSize")int pageSize);


    /**
     * 根据内容搜索发布的动态信息
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/query_by_keyword")
    Observable<ResultList<QueryPopularBean>> getCircleQuery_by_keyword(@Query("keyword") String keyword,@Query("circleId") String circleId,@Query("page") int page,@Query("pageSize")int pageSize);

    /**
     *获取动态信息
     * @param momentId
     * @param publisherId
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/details")
    Observable<Result<MomentDetailsBean>> getMomentDetails(@Query("circleId") String circleId,@Query("momentId") String momentId,@Query("publisherId") String publisherId);

    /**
     * 获取下几条图文信息
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/query_next_graphic")
    Observable<Result<MomentDetailsBean_X>> getQuery_next_graphic(@Query("currMomentId") String currMomentId, @Query("nextParam") String nextParam);

    /**
     * 获取下几条小视频信息
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/query_next_simple_video")
    Observable<Result<MomentDetailsBean_X>> query_next_simple_video(@Query("currMomentId") String currMomentId,@Query("nextParam") String nextParam);

    /**
     * 添加浏览量
     * @param circleId
     * @param momentId
     * @param momentPublisherId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/browse")
    Observable<Result> postBrows(@Query("circleId")String circleId,@Query("momentId")String momentId,@Query("momentPublisherId")String momentPublisherId);

    /**
     * 获取下几条图文信息
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/query_next_graphic")
    Observable<Result<MomentDetailsBean_X>> getQuery_next_graphic_circle(@Query("circleId") String circleId,@Query("currMomentId") String currMomentId,@Query("nextParam") String nextParam);

    /**
     * 获取下几条小视频信息
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/query_next_simple_video")
    Observable<Result<MomentDetailsBean_X>> query_next_simple_video_circle(@Query("circleId") String circleId,@Query("currMomentId") String currMomentId,@Query("nextParam") String nextParam);

    /**
     * 用户发布动态评论--圈子
     * @param content
     * @param contentType
     * @param momentId
     * @param momentPublisherId
     * @param replyCommentId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/moment/comment/publish")
    Observable<Result> postCommentMoment(@Query("circleId") String circleId,@Query("content")String content,@Query("contentType")int contentType,
                                         @Query("momentId") String momentId,@Query("momentPublisherId") String momentPublisherId,
                                         @Query("replyCommentId") String replyCommentId);
    /**
     * 用户发布动态评论--圈子
     * @param content
     * @param contentType
     * @param momentId
     * @param momentPublisherId
     * @param replyCommentId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/official/moment/comment/publish")
    Observable<Result> postCommentMoment_gf(@Query("content")String content,@Query("contentType")int contentType,
                                         @Query("momentId") String momentId,@Query("momentPublisherId") String momentPublisherId,
                                         @Query("replyCommentId") String replyCommentId);

    /**
     * 获取动态下评论信息
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/comment/query")
    Observable<ResultList<CommentMomentBean>> getCommentMoment(@Query("commentOrderType") String commentOrderType,
                                                               @Query("circleId") String circleId,
                                                                    @Query("momentId") String momentId,
                                                                    @Query("momentPublisherId")String momentPublisherId,
                                                                    @Query("page")int page, @Query("pageSize")int pageSize);

    /**
     * 获取动态下评论信息
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/official/moment/comment/query")
    Observable<ResultList<CommentMomentBean>> getCommentMoment_gf(@Query("commentOrderType") String commentOrderType,
                                                                    @Query("momentId") String momentId,
                                                                    @Query("momentPublisherId")String momentPublisherId,
                                                                    @Query("page")int page, @Query("pageSize")int pageSize);

    /**
     * 获取评论下的评论信息(回复评论的评论)
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/comment/query_reply")
    Observable<ResultList<CommentMomentBean>> getCommentQueryReply(@Query("circleId") String circleId,
                                                                         @Query("commentId") String commentId,
                                                                    @Query("momentId") String momentId,
                                                                    @Query("momentPublisherId")String momentPublisherId,
                                                                    @Query("page")int page, @Query("pageSize")int pageSize);

    /**
     * 获取评论下的评论信息(回复评论的评论)
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/official/moment/comment/query_reply")
    Observable<ResultList<CommentMomentBean>> getCommentQueryReply_gf(@Query("commentId") String commentId,
                                                                    @Query("momentId") String momentId,
                                                                    @Query("momentPublisherId")String momentPublisherId,
                                                                    @Query("page")int page, @Query("pageSize")int pageSize);


    /*在线运动房间服务（sport-room）-------------------------------↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/

    /**
     * 获取未完成的游戏
     */
    @POST(ConstValues.SPORT_GAME_SERVER + "api/v1/game/get_unfinished")
    Observable<Result<ConnectRoomUnfinishedBean>> get_unfinished();

    /**
     * 获取tcp连接信息
     */
    @GET(ConstValues.SPORT_ROOM_SERVER + "api/v1/game/room/get_connect_info")
    Observable<Result<ConnectRoomInfoBean>> get_connect_info();

    /**
     * 进入房间
     */
    @POST(ConstValues.SPORT_ROOM_SERVER + "api/v1/game/room/join")
    Observable<Result> gameRoomJoin(@Query("roomId") String roomId,@Query("password") String password);

    /**
     * 退出房间
     */
    @POST(ConstValues.SPORT_ROOM_SERVER + "api/v1/game/room/quit")
    Observable<Result> gameRoomQuit(@Query("roomId") String roomId);

    /**
     * 解散房间
     */
    @POST(ConstValues.SPORT_ROOM_SERVER + "api/v1/game/room/dismiss")
    Observable<Result> gameRoomDismiss(@Query("roomId") String roomId);

    /**
     * 开始游戏
     */
    @POST(ConstValues.SPORT_ROOM_SERVER + "api/v1/game/room/start")
    Observable<Result> gameRoomStart(@Query("roomId") String roomId);

    /**
     * 进入即时房间并开始游戏
     */
    @POST(ConstValues.SPORT_ROOM_SERVER + "api/v1/game/room/join_quick_and_start")
    Observable<Result<JoinQuickAndStartBean>> joinQuickAndStart(@Query("roomId") String roomId, @Query("password") String password);


    /**
     * 快速开始游戏
     */
    @POST(ConstValues.SPORT_ROOM_SERVER + "api/v1/game/room/quick-start")
    Observable<Result<JoinQuickAndStartBean>> get_gameRoomQuickStart(@Query("deviceTypeId") String deviceTypeId);
    /**
     * 获取可用的房间列表
     */
    @GET(ConstValues.SPORT_ROOM_SERVER + "api/v1/game/room/list")
    Observable<Result<GameRoomListBean>> get_gameRoomList(@Query("page") int page, @Query("pageSize") int pageSize,@Query("deviceTypeId") String deviceTypeId,@Query("type") String type);

    /**
     * 房间详情
     */
    @GET(ConstValues.SPORT_ROOM_SERVER + "api/v1/game/room/details")
    Observable<Result<GameRoomDetailsBean>> get_gameRoomDetails(@Query("roomId") String roomId);


    /**
     * 创建房间
     */
    @POST(ConstValues.SPORT_ROOM_SERVER + "api/v1/game/room/create")
    Observable<Result<GageRoomCreateBean>> gameCreateRoom(@Query("limitNumber") int limitNumber, @Query("mapId") String mapId, @Query("name") String name, @Query("password") String password, @Query("verification") boolean verification,@Query("type") String type);


    /**
     * 放弃游戏
     */
    @POST(ConstValues.SPORT_GAME_SERVER + "api/v1/game/given_up")
    Observable<Result> gameGivenUp(@Query("roomMemberId") String roomMemberId);

    /**
     * 完成游戏
     */
    @POST(ConstValues.SPORT_GAME_SERVER + "api/v1/game/complete")
    Observable<Result<GameCompleteBean>> gamComplete(@Query("calories") String calories, @Query("distance") String distance, @Query("roomMemberId") String roomMemberId, @Query("sportLogId") String sportLogId);

}
