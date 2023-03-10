package com.jxkj.fit_5a.api;


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
import com.jxkj.fit_5a.base.SignLogData;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.base.UserDetailData;
import com.jxkj.fit_5a.base.UserInfoData;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.entity.AdListData;
import com.jxkj.fit_5a.entity.AddressData;
import com.jxkj.fit_5a.entity.AddressModel;
import com.jxkj.fit_5a.entity.AdminInspireBean;
import com.jxkj.fit_5a.entity.BluetoothChannelData;
import com.jxkj.fit_5a.entity.CircleDetailsBean;
import com.jxkj.fit_5a.entity.CircleQueryBean;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.entity.CircleTaskData;
import com.jxkj.fit_5a.entity.CommentListBean;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.CommunityHomeInfoBean;
import com.jxkj.fit_5a.entity.CreateOrderBean;
import com.jxkj.fit_5a.entity.DeviceProtocolCheckData;
import com.jxkj.fit_5a.entity.DiscountUsableNotBean;
import com.jxkj.fit_5a.entity.FavoriteQueryList;
import com.jxkj.fit_5a.entity.FollowFansList;
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.jxkj.fit_5a.entity.MapDetailsBean;
import com.jxkj.fit_5a.entity.MapListSposrt;
import com.jxkj.fit_5a.entity.MedalListData;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean_X;
import com.jxkj.fit_5a.entity.NotObtainedBean;
import com.jxkj.fit_5a.entity.OrderDetailsData;
import com.jxkj.fit_5a.entity.OssInfoBean;
import com.jxkj.fit_5a.entity.PostOrderInfo;
import com.jxkj.fit_5a.entity.ProductDetailsBean;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.RankDetailsData;
import com.jxkj.fit_5a.entity.RankListData;
import com.jxkj.fit_5a.entity.RankStatsData;
import com.jxkj.fit_5a.entity.ShowOrderInfo;
import com.jxkj.fit_5a.entity.SignatureBean;
import com.jxkj.fit_5a.entity.SpecListBaen;
import com.jxkj.fit_5a.entity.SportLogBean;
import com.jxkj.fit_5a.entity.SportLogDetailBean;
import com.jxkj.fit_5a.entity.SportLogStatsBean;
import com.jxkj.fit_5a.entity.StsTokenBean;
import com.jxkj.fit_5a.entity.SubmitFilesBean;
import com.jxkj.fit_5a.entity.TaskCircleQueryBean;
import com.jxkj.fit_5a.entity.TemplateBean;
import com.jxkj.fit_5a.entity.TopicAllBean;
import com.jxkj.fit_5a.entity.UserOwnInfo;
import com.jxkj.fit_5a.entity.VideoInfoBean;
import com.jxkj.fit_5a.entity.VideoPlayAuthBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.entity.WalletDetailsBean;

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
import retrofit2.http.Query;

public interface ApiService {

    /**
     * ????????????
     *
     * @return ????????????1:????????????2:????????????3????????????4????????????
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/task/list")
    Observable<Result<TaskListBase>> getUserTaskList(@Query("type") Integer type);

    /**
     * ??????????????????
     * userTaskId ????????????id(????????????????????????)
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/task/update/speed")
    Observable<Result> updateUserTask(@Query("paramId") int paramId, @Query("value") String value,
                                      @Query("userTaskId") String userTaskId);

    /**
     * ????????????
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/detail")
    Observable<Result<UserDetailData>> getUserDetail();

    /**
     * ??????????????????
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/statistic/my")
    Observable<Result<UserInfoData>> getUserStatistic();

    /**
     * ????????????
     * @return
     */
    @POST("api/v1/user/verify/weixin/app/oauth")
    @FormUrlEncoded
    Observable<Result> register(@Field("code") String code);
    /**
     * ????????????
     *
     * @return
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/update")
    Observable<Result> postUserUpdate(@Body PostUser.UserInfoUpdate userInfoUpdate);


    /**
     * ??????
     *
     * @return
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/sign/add")
    Observable<Result> addUserSign();

    /**
     * ??????????????????
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/sign/log/today/sign")
    Observable<Result<Boolean>> addUserSignLog();


    /**
     * ??????????????????
     *
     * @return
     */
    @GET(ConstValues.PORT_1 + "api/v1/level/spec/list")
    Observable<Result<SpecListBaen>> getSpecList(@Query("levelId") String levelId);

    /**
     * ????????????????????????
     *
     * @return
     */
    @POST(ConstValues.PORT_1 + "api/v1/user/order/level/create")
    Observable<Result> postCreateLevel(@Query("levelSpecId") String levelSpecId, @Query("hasAuto") boolean hasAuto);


    /**
     * ????????????
     *
     * @return
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/sign/log/list")
    Observable<Result<SignLogData>> getUserSignLog(@Query("beginCreateTime") String beginCreateTime,
                                                   @Query("endCreateTime") String endCreateTime);

    /**
     * ????????????
     * flag:true???????????????false??????????????????????????????
     *
     * @return
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/gift/list")
    Observable<Result<GiftListData>> getUserGiftList(@Query("flag") boolean flag);


    /**
     * ??????????????????
     * flag:true???????????????false??????????????????????????????
     * @return
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/gift/log/list")
    Observable<Result<GiftLogListData>> getUserGiftLogList(@Query("giveFlag") boolean flag);

    /**
     * ????????????
     * ??????1?????????,2?????????,3?????????
     *
     * @return bugei
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/prize/list")
    Observable<Result<PrizeListData>> getUserPrizeList(@Query("status") int status);

    /**
     * ??????????????????????????????????????????????????????
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/prize/discount/usable_not_obtained")
    Observable<Result<DiscountUsableNotBean>> getusable_not_obtained();

    /**
     * ??????????????????????????????????????????????????????
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/prize/credit/usable_not_obtained")
    Observable<Result<NotObtainedBean>> usable_not_obtained(@Query("productId") String productId);

    /**
     * ????????????
     */

    @POST(ConstValues.PORT_1 + "api/v1/user/prize/receive")
    Observable<Result> getPrizeReceive(@Query("couponId") int couponId);

    /**
     * ??????????????????
     */

    @POST(ConstValues.PORT_1 + "api/v1/user/prize/receives")
    Observable<Result> getPrizeReceives(@Query("couponIds") List<Integer> couponIds);

    /**
     * ?????????
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/medal/list")
    Observable<ResultList<MedalListData>> getUserMedalList1();

    /**
     * ????????????
     */

    @GET(ConstValues.PORT_1 + "api/v1/user/medal/list")
    Observable<ResultList<MedalListData>> getUserMedalList();

    /**
     * ??????????????????
     */
    @GET(ConstValues.PORT_1 + "api/v1/user/circle/task/list")
    Observable<ResultList<CircleTaskData>> getCircleTaskList();

    /**
     * ???????????????
     */
    @GET(ConstValues.PORT_1 + "api/v1/rank/list")
    Observable<Result<RankListData>> getRankList(@Query("type") int type);

    /**
     * ???????????????
     */
    @GET(ConstValues.PORT_1 + "api/v1/rank/details")
    Observable<Result<RankDetailsData>> getRankDetails(@Query("rankId") Integer rankId);

    /**
     * ???????????????\
     * ??????(1:???;2:???;3:???)
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/ranking/list/stats/calories/current")
    Observable<Result<RankStatsData>> getRankStatsList(@Query("dimension") int dimension);

    /**
     * ???????????????
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/cal/rank/zan/day/like")
    Observable<Result> getStatsZan(@Query("calRankId") String calStatsId, @Query("dimension") int dimension);
    /**
     * ?????????????????????
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/cal/rank/zan/day/cancel_like")
    Observable<Result> getCancelStatsZan(@Query("calRankId") String calStatsId, @Query("dimension") int dimension);


    /**
     * ???????????????????????????????????????
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/interest/query")
    Observable<Result<InterestLists>> getInterestList();


    /**
     * ????????????
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/help/query")
    Observable<Result<HelpListData>> getHelpList();

    /**
     * ????????????
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/ad/query")
    Observable<Result<AdListData>> getAdList();

    /**
     * ?????????????????? ?????????
     * deviceId ??????id
     * deviceNo	????????????
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/user/device/query")
    Observable<Result> queryUserDeviceList();

    /**
     * ??????????????????
     * 	????????????(1,????????????;2,????????????)
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/type/query")
    Observable<Result<DeviceTypeData>> queryDeviceTypeLists(@Query("type") int type);

    /**
     * ??????????????????
     *
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/brand/query")
    Observable<Result<DeviceDrandData>> queryDeviceBrandLists(@Query("page") String page, @Query("pageSize") String pageSize);


    /**
     * ??????????????????
     * @return
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/model/query")
    Observable<Result<DeviceData>> queryDeviceModelLists(
            @Query("deviceBrandId") String deviceBrandId, @Query("deviceTypeId") String deviceTypeId);


    /**
     * ??????????????????
     * @return  [-16, -96, 56, -45, -101]
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/type/bluetooth/channel/query")
    Observable<ResultList<BluetoothChannelData>> getBluetoothChannel(@Query("deviceTypeId") String deviceTypeId, @Query("protocolName") String protocolName);


    /**
     *  ??????????????????????????????
     * @param deviceBrandId ????????????id
     * @param deviceBrandParamId ??????????????????id(iconsole????????????client id)
     * @param deviceModelId ????????????id
     * @param deviceTypeId ????????????id
     * @param deviceTypeParamId ??????????????????id(iconsole????????????meter id)
     * @param protocolName 	????????????(????????????, ????????????iconsole)
     * @param deviceModelParamId ???????????????????????????id(iconsole????????????????????????)
     * @return
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
    // protocolName=iconsole http response = {"code":"000000","mesg":"????????????","sub_code":"000000","sub_mesg":"????????????","time":"2021-03-31T07:39:34.586Z","data":{"result":false,"deviceBrandTypeModel":{"deviceBrand":{"id":56,"name":"HEAD FITNESS","protocolName":"iconsole","paramId":"55","img":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/device/1kL4w9LWwasXTRcNLcjteA.png"},"deviceType":{"id":9,"name":"????????????/?????????","protocolName":"iconsole","paramId":"0","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png"},"deviceModel":null}}}


    /**
     * ????????????????????????
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/course/query")
    Observable<Result<DeviceCourseData>> queryDeviceCourseList(@Query("level") String level, @Query("deviceId") String deviceId,
                                                               @Query("type") String type);

    /**
     * ????????????????????????
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/course/type/query")
    Observable<Result<DeviceCourseTypeData>> queryDeviceCourseTypeList(@Query("deviceId") String deviceId);

    /**
     * ??????????????????
     */
    @GET(ConstValues.PORT_2 + "api/v1/device/course/details")
    Observable<Result<DeviceCourseData.ListBean>> queryDeviceCourseTypeDetails(@Query("id") String id);



    /**
     * ??????sts-token
     */
    @GET(ConstValues.PORT_5 + "api/v1/oss/info")
    Observable<Result<OssInfoBean>> getOssInfo();


    /**
     * ??????sts-token
     */
    @GET(ConstValues.PORT_5 + "api/v1/oss/signature")
    Observable<Result<SignatureBean>> getSignature(@Query("dir") String dir);


    /**
     * ??????sts-token
     */
    @GET(ConstValues.PORT_5 + "api/v1/sts/token")
    Observable<Result<StsTokenBean>> getStsToken();
    /**
     * ?????????????????????
     * @return type:??????0??????1????????????2??????
     *
     */
    @GET(ConstValues.PORT_5 + "api/v1/user/verify/getVerifyCode")
    Observable<Result> getVerifyCode(@Query("mobile") String mobile, @Query("type") int type);

    /**
     * ??????
     *
     * @return type:??????0??????1????????????2??????
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/verify/register")
    Observable<Result<LoginInfo>> userVerifyRegister(@Query("clientType") int clientType,
                                          @Query("phone") String phone, @Query("password") String password,
                                          @Query("verify") String verify);

    /**
     * ??????
     *
     * @return clientType:???????????????1web2IOS3??????4??????
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/verify/login")
    Observable<Result<LoginInfo>> userVerifyLogin(@Query("clientType") int clientType,
                                                  @Query("phone") String phone, @Query("password") String password,
                                                  @Query("verify") String verify);


    /**
     * ????????????
     *
     * @return type:??????0??????1????????????2??????
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/verify/forgetPassword")
    Observable<Result> userForgetPassword(@Query("password") String password,
                                          @Query("phone") String phone,
                                          @Query("verify") String verify);

    /**
     * ???????????????????????????????????????????????????
     *
     * @return type:??????0??????1????????????2??????
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/bind/third/changePassword")
    Observable<Result> userChangePassword(@Query("oldPassword") String oldPassword,
                                          @Query("password") String password);

    /**
     *?????????????????????????????????
     */
    @GET(ConstValues.PORT_5 + "api/v1/video/upload")
    Observable<Result<VideoInfoBean>> getUploadVideo(@Query("fileName") String fileName, @Query("title") String title, @Query("coverUrl") String coverUrl);

    /**
     *??????????????????
     * @Query("fileName") String fileName, @Query("title") String title, @Query("coverUrl") String coverUrl
     */
    @Multipart
    @POST(ConstValues.PORT_5 + "api/v1/video/upload_video")
    Observable<Result> getUpload_Video(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    /**
     * ???????????? ?????????
     *
     * @return clientType:?????????1web2ios3??????4?????????
     * ????????????1????????????2??????3QQ4??????5iconsole
     */
    @POST(ConstValues.PORT_5 + "api/v1/user/bind/third/bind")
    Observable<Result> userThirdBind(@Query("clientType") int clientType, @Query("loginType") int loginType, @Query("phone") String phone,
                                     @Query("verify") String verify, @Query("gender") String gender, @Query("nickName") String nickName,
                                     @Query("portraitUri") String portraitUri);


    /**
     * ????????????????????????
     */
    @GET(ConstValues.PORT_5 + "api/v1/video/play_auth")
    Observable<Result<VideoPlayAuthBean>> getPlay_auth(@Query("videoId") String videoId);

    /**
     * ????????????????????????
     */
    @GET(ConstValues.PORT_5 + "api/v1/video/play_info")
    Observable<Result<VideoPlayInfoBean>> getPlay_info(@Query("definition") String definition, @Query("videoId") String videoId);

    /**
     * app????????????
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
//


    /**
     * ????????????
     */
    @GET(ConstValues.PORT_3 + "api/v1/product/list")
    Observable<Result<ProductListBean>> getProductList(@Query("hasHot") Integer hasHot);

    /**
     * ????????????
     */
    @GET(ConstValues.PORT_3 + "api/v1/product/details")
    Observable<Result<ProductDetailsBean>> getProductDetails(@Query("id") String id);

    /**
     * ??????????????????
     */
    @GET(ConstValues.PORT_3 + "api/v1/comment/list")
    Observable<Result<CommentListBean>> getCommentList(@Query("productId") String productId);


    /**
     * ????????????????????????????????????
     *
     * @return
     */
    @GET(ConstValues.PORT_3 + "api/v1/user/address/list")
    Observable<Result<AddressModel>> getUserAddress(@Query("page") String page, @Query("pageSize") String pageSize);


    /**
     * ??????????????????
     *
     * @return
     */

    @POST(ConstValues.PORT_3 + "api/v1/user/address/updateDefault")
    Observable<Result> getSetDefault(@Query("id") String id);


    /**
     * ????????????
     */
    @POST(ConstValues.PORT_3 + "api/v1/user/address/delete")
    Observable<Result> getDeleteAddress(@Query("id") String id);


    /**
     * ????????????
     *
     * @return
     */
    @POST(ConstValues.PORT_3 + "api/v1/user/address/save")
    Observable<Result> getAddAddress(@Body AddressData data);

    /**
     * ????????????
     *
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/address/update")
    Observable<Result> getUpdateAddress(@Body AddressData data);

    /**
     * ????????????
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/showOrderInfo")
    Observable<Result<ShowOrderInfo>> postShowOrderInfo(@Body PostOrderInfo data);

    /**
     * ????????????
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/createOrder")
    Observable<Result<CreateOrderBean>> postcreateOrder(@Body PostOrderInfo data);

    /**
     * ????????????
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/expediting")
    Observable<Result> postExpediting(@Query("orderId") String orderId);
    /**
     * ??????
     * orderId	??????Id
     * orderType	???????????????1,?????????;2,?????????;3,app???4?????????
     * payType	????????????:1,???????????????;2,????????????;3,???????????????;4,????????????
     * subPayType	????????????
     */

    @POST(ConstValues.PORT_3 +"api/v1/user/order/payOrder")
    Observable<Result<ParamData>> getOrderPayInfo(@Query("integral") String integral,
                                                      @Query("orderId") String orderId,
                                                      @Query("payType") String payType,
                                                      @Query("redId") String redId,
                                                      @Query("wxPayType") String wxPayType);
    /**
     * ????????????
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/cancelOrder")
    Observable<Result> postCancelOrder(@Query("id") String id);


    /**
     * ????????????
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/delete")
    Observable<Result> postDelete(@Query("orderId") String orderId);

    /**
     * ????????????
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/finishOrder")
    Observable<Result> postFinishOrder(@Query("id") String id);

    /**
     * ????????????
     * @return
     */
    @POST(ConstValues.PORT_3 +"api/v1/user/order/comment")
    Observable<Result> postCommentOrder(@Body PostUser.Comment comment);


    /**
     * ????????????
     * @param page
     * @param pageSize
     * @param status ??????????????????1,?????????;2,?????????;3,?????????;4,?????????;5,?????????;6,?????????;7,?????????;8,?????????
     * @return
     */
    @GET(ConstValues.PORT_3 +"api/v1/user/order/list")
    Observable<Result<OrderInfoData>> getOrderAll(@Query("page") int page,
                                                  @Query("pageSize") int pageSize,
                                                  @Query("status") String status);


    /**
     * ????????????
     */
    @GET(ConstValues.PORT_3 +"api/v1/user/order/details")
    Observable<Result<OrderDetailsData>> getOrderDetails(@Query("id") String id);

    /**
     * ????????????
     */
    @GET(ConstValues.PORT_4 + "api/v1/sport/map/list")
    Observable<Result<MapListSposrt>> getSportMapList(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * ????????????
     *
     * @param id
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/map/details")
    Observable<Result<MapDetailsBean>> getMapDetails(@Query("id") String id);

    /**
     * ????????????
     *
     * @param deviceTypeId
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/map/random")
    Observable<Result<MapDetailsBean>> getMapRandomDetails(@Query("deviceTypeId") String deviceTypeId);

    /**
     * ??????????????????
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/log/stats")
    Observable<Result<SportLogStatsBean>> getSportLogStats(@Query("beignCreateTimestamp") String beignCreateTimestamp,
                                                           @Query("endCreateTimestamp") String endCreateTimestamp, @Query("deviceTypeId") String deviceTypeId);

    /**
     * ????????????
     * @param
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/log/user_sync_iconsole")
    Observable<Result> getBoxReceive();
    /**
     * ????????????
     * @param
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/box/receive")
    Observable<Result> getBoxReceive(@Query("boxId") String boxId, @Query("mapId") String mapId);


    /**
     * ??????????????????
     */
    @GET(ConstValues.PORT_4 + "api/v1/str/template/query")
    Observable<Result<TemplateBean>> getTemplateList();


    /**
     * ??????????????????
     */
    @POST(ConstValues.PORT_4 + "api/v1/user/sport/log/add")
    Observable<Result> psotUserSportLog(@Body PostUser.SportLogInfo postUser);


    /**
     * ??????????????????
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/log/list")
    Observable<Result<SportLogBean>> geSportLogList(@Query("beignCreateTimestamp") String beignCreateTimestamp,
                                                    @Query("endCreateTimestamp") String endCreateTimestamp,@Query("deviceTypeId") String deviceTypeId,@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * ??????????????????
     */
    @GET(ConstValues.PORT_4 + "api/v1/user/sport/log/details")
    Observable<Result<SportLogDetailBean>> geSportLogDetails(@Query("id") String logid);


    /**
     * ????????????  	????????????1??????,2?????????
     */
    @GET(ConstValues.PORT_8 + "api/v1/user/wallet/details")
    Observable<Result<WalletDetailsBean>> getWalletDetails(@Query("type") int type);


    /**
     * ????????????
     *
     * @return
     */
    @Multipart
    @POST(ConstValues.POPT_LS + "region/api/v1/files")
    Observable<Result<SubmitFilesBean>> submitFiles(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);


    /**
     * ????????????
     *
     * @param deviceType
     * @return 2226.21
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/query")
    Observable<Result<CircleQueryBean>> getCircleQuery(@Query("deviceType") int deviceType);

    /**
     * ????????????
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/create")
    Observable<Result> getCircleCreate(@Body PostUser.CreateCircle postUser);

    /**
     * ??????????????????
     *
     * @param id
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/details")
    Observable<Result<CircleDetailsBean>> getCircleDetails(@Query("id") int id);

    /**
     * ??????????????????
     *
     * @param circleId
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/cricle/member/join")
    Observable<Result> getCircleJoin(@Query("circleId") int circleId);

    /**
     * ??????????????????
     *
     * @param circleId
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/cricle/member/quit")
    Observable<Result> getCircleQuit(@Query("circleId") int circleId);

    /**
     * ??????????????????????????????
     *
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/task/query")
    Observable<Result<TaskCircleQueryBean>> getTaskCircleQuery(@Query("deviceType") int deviceType);


    /**
     * ??????????????????????????????
     */
    @GET(ConstValues.PORT_21 + "api/v1/topic/all")
    Observable<ResultList<TopicAllBean>> getTopicAll();


    /**
     * ??????????????????--??????
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/publish")
    Observable<Result> postPublishMoment(@Query("content") String content, @Query("contentType") String contentType,
                                         @Query("shareType") String shareType, @Query("media") String media,
                                         @Query("position") String position, @Query("location") String location,
                                         @Query("topics") String[] topics);

    /**
     * ??????????????????--??????
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/moment/publish")
    Observable<Result> postPublishMomentCircle(@Query("circleId") int circleId, @Query("content") String content,
                                               @Query("contentType") String contentType, @Query("shareType") String shareType,
                                               @Query("location") String location, @Query("media") String media,
                                               @Query("position") String position, @Query("topics") String topics);

    /**
     * ??????????????????--????????????
     *
     * @return
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/moment/delete")
    Observable<Result> postDeleteMomentCircle(@Query("circleId") int circleId,  @Query("momentId") String momentId);

    /**
     * ????????????(??????)????????????
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_popular")
    Observable<ResultList<QueryPopularBean>> getMomentQueryPopular(@Query("page")int page,@Query("pageSize")int pageSize);//??????1

    /**
     *??????????????????
     */
    @GET(ConstValues.PORT_2 + "api/v1/inspire/get")
    Observable<Result<AdminInspireBean>> getAdminInspire();

    /**
     * ????????????(??????)????????????---??????
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_popular")
    Observable<ResultList<QueryPopularBean>> getMomentQueryPopularTopic(@Query("contentType")String contentType,@Query("topic")String topic,@Query("page")int page,@Query("pageSize")int pageSize);


    /**
     * ???????????????????????????????????????==??????
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/moment/query_by_keyword")
    Observable<ResultList<QueryPopularBean>> getQueryByKeyword(@Query("keyword") String keyword,@Query("circleId") String circleId);

    /**
     * ?????????????????????????????????
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_own")
    Observable<ResultList<QueryPopularBean>> getQueryByPublisherOwn(@Query("momentLocalMinId") int momentLocalMinId
            , @Query("contentType") int contentType);

    /**
     * ?????????????????????????????????
     */
    @GET(ConstValues.PORT_21 + "api/v1/moment/query_by_publisher")
    Observable<ResultList<QueryPopularBean>> getQueryByPublisher(@Query("momentLocalMinId") int momentLocalMinId
            , @Query("publisherId") String publisherId
            , @Query("contentType") int contentType);


    /**
     * ????????????????????????
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/moment/query_lately")
    Observable<ResultList<QueryPopularBean>> getQguery_lately(@Query("circleId") int circleId, @Query("contentType") int contentType);
    /**
     * ????????????????????????--topic
     */
    @GET(ConstValues.PORT_21 + "api/v1/topic/moment/query_lately")
    Observable<ResultList<QueryPopularBean>> getQguery_lately_topic(@Query("topicId") String topicId, @Query("contentType") int contentType);

    /**
     * ????????????(??????)????????????
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/moment/query_popular")
    Observable<ResultList<QueryPopularBean>> getQuery_popular(@Query("circleId") int circleId, @Query("contentType") int contentType);

    /**
     * ????????????
     */
    @GET(ConstValues.PORT_21 + "api/v1/community/home/info")
    Observable<Result<CommunityHomeInfoBean>> getCommunityHomeInfo();


    /**
     * ???????????????????????????????????????
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/query_joined")
    Observable<Result<CircleQueryJoinedBean>> getCircleQueryJoined(@Query("userId")String userId,@Query("page") int page, @Query("pageSize") int pageSize);


    /**
     * ???????????????????????????????????????
     */
    @GET(ConstValues.PORT_21 + "api/v1/circle/query_own_joined")
    Observable<Result<CircleQueryJoinedBean>> getCircleQueryJoinedOwn(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * ??????????????????????????????
     */
    @GET(ConstValues.PORT_21 + "api/v1/user/profile/own")
    Observable<Result<UserOwnInfo>> getUserProfileOwn();

    /**
     * ????????????????????????
     */
    @GET(ConstValues.PORT_21 + "api/v1/user/profile")
    Observable<Result<UserOwnInfo>> getUserProfile(@Query("userId") String userId);

    /**
     * ???????????????????????????
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/follow/fans")
    Observable<FollowFansList> getFollowFansList(@Query("userId") String userId,@Query("page") int page, @Query("pageSize") int pageSize);


    /**
     *???????????????????????????
     * @return
     */
    @GET(ConstValues.PORT_21 + "api/v1/follow/followers")
    Observable<FollowFansList> getFollowFollowers(@Query("userId") String userId,@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * ????????????
     * @param followerId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/follow")
    Observable<Result> postfollow(@Query("followerId") String followerId);

    /**
     * ??????????????????
     * @param followerId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/follow/cancel")
    Observable<Result> postfollowCancel(@Query("followerId") String followerId);

    /**
     * ???????????????????????????
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/favorite/query")
    Observable<ResultList<FavoriteQueryList>>getFavoriteQueryOwn(@Query("userId") String userId);

    /**
     * ??????
     * 	??????id(?????????????????????????????????????????????0)
     */
    @POST(ConstValues.PORT_21 + "api/v1/favorite")
    Observable<Result> postFavorit(@Query("circleId") String circleId,
                                   @Query("momentId") String momentId,
                                   @Query("momentPublisherId") String momentPublisherId);


    /**
     * ????????????
     * 	??????id(?????????????????????????????????????????????0)
     */
    @POST(ConstValues.PORT_21 + "api/v1/favorite/cancel")
    Observable<Result> postFavoritCancel(@Query("circleId") String circleId,
                                         @Query("momentId") String momentId);

    /**
     * ??????????????????
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/like")
    Observable<Result> postLike(@Query("momentId") String momentId,
                                   @Query("momentPublisherId") String momentPublisherId);

    /**
     * ??????????????????--??????
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/moment/like")
    Observable<Result> postLikeCircle(@Query("circleId") String circleId,@Query("momentId") String momentId,
                                   @Query("momentPublisherId") String momentPublisherId);


    /**
     * ????????????????????????
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/like/cancel")
    Observable<Result> postLikeCancel(@Query("momentId") String momentId,
                                         @Query("momentPublisherId") String momentPublisherId);


    /**
     * ????????????????????????--??????
     */
    @POST(ConstValues.PORT_21 + "api/v1/circle/moment/like/cancel")
    Observable<Result> postLikeCancelCircle(@Query("circleId") String circleId,@Query("momentId") String momentId,
                                         @Query("momentPublisherId") String momentPublisherId);

    /**
     * ????????????????????????
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/comment/like")
    Observable<Result> postCommentLike(@Query("commentId") String commentId,
                                   @Query("momentId") String momentId);


    /**
     * ??????????????????????????????
     */
    @POST(ConstValues.PORT_21 + "api/v1/moment/comment/like/cancel")
    Observable<Result> postCommentLikeCancel(@Query("commentId") String commentId,
                                         @Query("momentId") String momentId);

    /**
     * ??????????????????
     * @return
     */
    @GET(ConstValues.PORT_21 +"api/v1/topic/hot")
    Observable<ResultList<HotTopicBean>> getHotTopicList(@Query("keyword") String keyword,@Query("page") int page,@Query("pageSize")int pageSize);

    /**
     * ??????????????????????????????
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/topic/participated")
    Observable<ResultList<HotTopicBean>> getTopicParticipated(@Query("keyword") String keyword,@Query("page") int page,@Query("pageSize")int pageSize);


    /**
     * ??????????????????
     * @param keyword
     * @param page
     * @param pageSize
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/topic/all_topic")
    Observable<ResultList<HotTopicBean>> getAllTopic(@Query("keyword") String keyword,@Query("page") int page,@Query("pageSize")int pageSize);


    /**
     * ???????????????????????????????????????
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/query_by_keyword")
    Observable<ResultList<QueryPopularBean>> getQuery_by_keyword(@Query("keyword") String keyword,@Query("contentType") String contentType,@Query("page") int page,@Query("pageSize")int pageSize);


    /**
     * ???????????????????????????????????????
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/query_by_keyword")
    Observable<ResultList<QueryPopularBean>> getCircleQuery_by_keyword(@Query("keyword") String keyword,@Query("circleId") String circleId,@Query("page") int page,@Query("pageSize")int pageSize);

    /**
     *??????????????????--??????
     * @param momentId
     * @param publisherId
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/details")
    Observable<Result<MomentDetailsBean>> getMomentDetails(@Query("momentId") String momentId,@Query("publisherId") String publisherId);

    /**
     * ???????????????????????????
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/query_next_graphic")
    Observable<Result<MomentDetailsBean_X>> getQuery_next_graphic(@Query("currMomentId") String currMomentId, @Query("nextParam") String nextParam);

    /**
     * ??????????????????????????????
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/query_next_simple_video")
    Observable<Result<MomentDetailsBean_X>> query_next_simple_video(@Query("currMomentId") String currMomentId,@Query("nextParam") String nextParam);

    @POST(ConstValues.PORT_21+"api/v1/browse")
    Observable<Result> postBrows(@Query("circleId")String circleId,@Query("momentId")String momentId,@Query("momentPublisherId")String momentPublisherId);

    /**
     *??????????????????--??????
     * @param momentId
     * @param publisherId
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/details")
    Observable<Result<MomentDetailsBean>> getMomentDetailsCircle(@Query("circleId") String circleId,@Query("momentId") String momentId,@Query("publisherId") String publisherId);

    /**
     * ???????????????????????????
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/query_next_graphic")
    Observable<Result<MomentDetailsBean_X>> getQuery_next_graphic_circle(@Query("circleId") String circleId,@Query("currMomentId") String currMomentId,@Query("nextParam") String nextParam);

    /**
     * ??????????????????????????????
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/query_next_simple_video")
    Observable<Result<MomentDetailsBean_X>> query_next_simple_video_circle(@Query("circleId") String circleId,@Query("currMomentId") String currMomentId,@Query("nextParam") String nextParam);
    /**
     * ????????????????????????
     * @param content
     * @param contentType
     * @param momentId
     * @param momentPublisherId
     * @param replyCommentId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/moment/comment/publish")
    Observable<Result> postCommentMoment(@Query("content")String content,@Query("contentType")int contentType,
                                         @Query("momentId") String momentId,@Query("momentPublisherId") String momentPublisherId,
                                         @Query("replyCommentId") String replyCommentId);

    /**
     * ????????????????????????--??????
     * @param content
     * @param contentType
     * @param momentId
     * @param momentPublisherId
     * @param replyCommentId
     * @return
     */
    @POST(ConstValues.PORT_21+"api/v1/circle/moment/comment/publish")
    Observable<Result> postCommentMomentCircle(@Query("circleId") String circleId,@Query("content")String content,@Query("contentType")int contentType,
                                         @Query("momentId") String momentId,@Query("momentPublisherId") String momentPublisherId,
                                         @Query("replyCommentId") String replyCommentId);

    /**
     * ???????????????????????????
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/comment/query")
    Observable<ResultList<CommentMomentBean>> getCommentMoment(@Query("momentId") String momentId,
                                                               @Query("momentPublisherId") String momentPublisherId,
                                                               @Query("page")int page, @Query("pageSize")int pageSize);

    /**
     * ???????????????????????????--??????
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/comment/query")
    Observable<ResultList<CommentMomentBean>> getCommentMomentCircle(@Query("circleId") String circleId,@Query("momentId") String momentId,
                                                               @Query("momentPublisherId") String momentPublisherId,
                                                               @Query("page")int page, @Query("pageSize")int pageSize);

    /**
     * ??????????????????????????????(?????????????????????)
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/moment/comment/query_reply")
    Observable<ResultList<CommentMomentBean>> getCommentQueryReply(@Query("commentId") String commentId,
                                                                    @Query("momentId") String momentId,
                                                                    @Query("momentPublisherId")String momentPublisherId,
                                                                    @Query("page")int page, @Query("pageSize")int pageSize);

    /**
     * ??????????????????????????????(?????????????????????)--??????
     * @return
     */
    @GET(ConstValues.PORT_21+"api/v1/circle/moment/comment/query_reply")
    Observable<ResultList<CommentMomentBean>> getCommentQueryReplyCircle(@Query("circleId") String circleId,
                                                                         @Query("commentId") String commentId,
                                                                    @Query("momentId") String momentId,
                                                                    @Query("momentPublisherId")String momentPublisherId,
                                                                    @Query("page")int page, @Query("pageSize")int pageSize);

}
