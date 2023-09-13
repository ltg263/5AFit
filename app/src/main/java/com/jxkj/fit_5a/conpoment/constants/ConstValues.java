package com.jxkj.fit_5a.conpoment.constants;

import com.jxkj.fit_5a.netty_client.game.service.GameConnectService;

/**
 * Created by Administrator on 2017/9/1.2285
 */

public class ConstValues {
    public static String ABC = "https://oss.5afit.com/device/7sdWe5yOR07wGeINGI2Og.jpg";
    /**
     * 应用名称2954.72  2961.54
     */
    public static String APPNAME_ENGLISH = "5AFit";
    public static String CITY_AD_CODE = "cityAdCode";

    public static String APP_ID_TENCENT = "101964667";
    public static final String APP_AUTHORITIES="com.jxkj.fit_5a.fileprovider";
    /**
     * 文件夹父路径
     */
    public static final String FILE_ROOT_DIRECTORY = "/yzdg";
    public static final String FILE_DIRECTORY_IMG = FILE_ROOT_DIRECTORY+"/img";

    /**sharedpreference 判断是否已登录字段*/
    public static final String ISLOGIN = "islogin";
    public static final String ISNOTFIRST = "isNotFirst";
    public static final String IMAGE_URL_FIRST = "imageUrl_First";
    public static final String IMAGE_URL_FIRST_TYPE = "imageUrl_First_type";
    public static final String IMAGE_URL_FIRST_PLAY = "imageurl_first_play";
    public static final String USERID = "user_id";
    public static final String TOKEN = "token";
    public static final String USER_PHONE = "user_phone";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_NAME = "user_name";
    public static final String USER_AGE = "user_age";
    public static final String USER_EXPLAIN = "user_explain";
    public static final String USER_IMG = "user_img";
    public static final String USER_LEVELNAME = "user_levelName";
    public static final String PHONE = "phone";
    public static final String LAT = "lat";
    public static final String LON = "lon";
    public static final String NICKNAME = "nickname";
    public static final String THIRD_LOGIN_BIND_INFO = "third-login-temp-header";
    public static final String INTEGRALJUMP = "integral_jump";
    public static final String OPENSTART = "openstart";
    public static final String USER_DETAIL = "user_detail";

    public static final String MY_BALANCE = "balance";//金豆
    public static final String MY_GIFTCOUNT = "giftCount";//礼物
    public static final String MY_COUPON_COUNT = "couponCount";//券
    public static final String MY_INTEGRAL = "integral";//积分
    public static final String USER_BIRTHDAY = "user_birthday";//	生日
    public static final String USER_GENDER = "user_gender";//	性别
    public static final String USER_HEIGHT = "user_height";//	身高cm
    public static final String USER_WEIGHT = "user_weight";//	体重kg
    public static final String USER_INTEREST = "user_interest";//	兴趣

    public static final String USER_THREE_HT_PAGE = "user_three_ht_page";//	页数

    public static final String LOGIN_USER_TYPE = "login_user_type";//用户登录类型   1：安利  else 5a
    public static final String accessKeyId = "accessKeyId";
    public static final String accessKeySecret = "accessKeySecret";
    public static final String endpoint = "endpoint";
    public static final String bucketName = "bucketName";
    public static final String SecurityToken = "SecurityToken";
    public static final String dir = "dir";
    public static final String host = "host";

    /**
     * 服务器后台地址
     */
//    public static final String BASE_URL = "http://api.zjduon.com/zulin/";
//    public static final String BASE_URL = "http://192.168.2.130:8087/gtbl/";
//    public static final String BASE_URL = "https://5afit.nbqichen.com";
    public static final String BASE_URL = "https://api.5afit.com";
    public static final String BASE_URL_DETAIL = "http://admin.zjduon.com/";

    public static final String WX_APP_ID = "wxdc42a7cf4a99be02";
    public static final String WX_APP_SECRET = "3a48f38098cc604274f3e46f3c400242";

    public static final String PORT_1 = "user/";//用户 user
    public static final String PORT_2 = "sysconfig/";//系统相关设置 sysconfig
    public static final String PORT_3 = "shop/";//商城
//    public static final String PORT_3 = "http://192.168.2.175:9503/";//商城
    public static final String PORT_4 = "sport/";//运动
    public static final String PORT_4_AL = "sport-al/";//安利运动
    public static final String PORT_5 = "third/";//第三方登录 third
//    public static final String PORT_5 = "http://192.168.2.145:9505/";//积分商城
    public static final String PORT_8 = "pay/";//支付相关 pay
    public static final String PORT_21 = "community-service/";//社群
    public static final String PORT_TASK = "task/";//任务(task)
    public static final String PORT_MESSAGE = "message/";//任务(task)
//    public static final String PORT_21 = "http://192.168.2.145:9021/";//社群
    public static final String POPT_LS = "";//没有
    public static final String SPORT_ROOM_SERVER = "sport-room/";//在线运动房间服务（sport-room）
    public static final String SPORT_ROOM_SERVER_AL = "sport-al-room/";//在线运动房间服务（sport-room）
    public static final String SPORT_GAME_SERVER = "sport-game/";//在线运动游戏服务（sport-game）

    public static final String SPORT_GAME_SERVER_AL = "sport-al-game/";//在线运动游戏服务（sport-game）

    //默认连接超时时间
    public static final int DEFAULT_TIMEOUT =60;
    public static final int PAGE_SIZE =10;

    /**
     * 海德官方动态
     */
    public static final String USER_HANDE_GF = BASE_URL+"/help/index.html#/articleInfo?";
    /**
     * 用户协议
     */
    public static final String USER_XY_URL = BASE_URL+"/singlePage.html?name=useagreement";
    /**
     * 隐私政策
     */
    public static final String USER_YSZC_URL = BASE_URL+"/singlePage.html?name=privacypolicy";
    /**
     * 器材帮助
     */
    public static final String USER_QCBZ_URL = BASE_URL+"/help/index.html#/helpEquip";
    /**
     * 常见问题
     */
    public static final String USER_CJWT_URL = BASE_URL+"/help/index.html#/help";
    /**
     * 公司介绍
     */
    public static final String USER_GYWM_URL = BASE_URL+"/singlePage.html?name=companyprofile";
    /**
     * 会员攻略
     */
    public static final String USER_HYGL_URL = BASE_URL+"/singlePage.html?name=memberstrategy";
    /**
     * 金豆规则
     */
    public static final String USER_JDGZ_URL = BASE_URL+"/singlePage.html?name=jindouguize";
    /**
     * 充值服务条款
     */
    public static final String USER_CZFWTK_URL = BASE_URL+"/singlePage.html?name=chongzhifuwutiaokuan";
    /**
     * 会员服务协议：
     */
    public static final String USER_HYFWXY_URL = BASE_URL+"/singlePage.html?name=huiyuanxieyifuwu";
}
