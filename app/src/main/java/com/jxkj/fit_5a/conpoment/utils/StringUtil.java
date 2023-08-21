package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;


import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/4.
 */

public class StringUtil {

    public static String replaceNull(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }

    public static String replaceNullToZero(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        return str;
    }

    public static String replaceNullToOne(String str) {
        if (TextUtils.isEmpty(str)) {
            return "1";
        }
        return str;
    }

    public static String format(String format, Object v1, Object v2) {
        return String.format(format, v1, v2);
    }

    public static String replaceHtmlImgToAbsolutelyUrl(String baseUrl, String html) {
        Pattern pattern = Pattern.compile("src\\s*=\\s*\"(.+?)\"");
        Matcher matcher = pattern.matcher(html);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String str = matcher.group(0);
            if (!str.contains("http://")) {
                matcher.appendReplacement(sb, str.substring(0, 5) + baseUrl + str.substring(6));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return ("".equals(str) || "null".equals(str) || str == null);
    }

    /**
     * 替换非utf8字符，慎用  会出现死循环
     *
     * @param text
     * @return
     */
    public static String filterOffUtf8Mb4(String text) {
        try {
            byte[] bytes = text.getBytes("UTF-8");
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            int i = 0;
            while (i < bytes.length) {
                short b = bytes[i];
                if (b > 0) {
                    buffer.put(bytes[i++]);
                    continue;
                }
                b += 256;
                if ((b ^ 0xC0) >> 4 == 0) {
                    buffer.put(bytes, i, 2);
                    i += 2;
                } else if ((b ^ 0xE0) >> 4 == 0) {
                    buffer.put(bytes, i, 3);
                    i += 3;
                } else if ((b ^ 0xF0) >> 4 == 0) {
                    i += 4;
                }
            }
            buffer.flip();
            return new String(buffer.array(), "utf-8");
        } catch (Exception e) {
            Log.e("Exception", e.getMessage().toString());
        }
        return text;
    }

    public static boolean isUTF8(String key) {
        try {
            key.getBytes("utf-8");
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return FALSE，包含图片
     */
    public static boolean containsEmoji(String source) {
        if (source.equals("")) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {

        if (!containsEmoji(source)) {
            return source;//如果不包含，直接返回
        }
        //到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            } else {
            }
        }

        if (buf == null) {
            return source;//如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {
                //这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }

    }

    /**
     * 监听输入框输的变化
     */
    public static void etSearchChangedListener(final EditText et, final View btn, final EtChange etChange) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && et.getText().toString().trim().length() != 0) {
                    etChange.etYes();
                }
                if (et.getText().toString().trim().length() == 0) {
                    if (btn != null) {
                        btn.setSelected(false);
                    }
                    etChange.etNo();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public static String getTimeToYMD(long seconds, String layout) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat(layout);
        return sdf.format(d).toString();
    }

    /**
     * 描述: 将字符串转成毫秒数 格式年月日
     */
    public static long getMsToTime(String time, String layout) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat(layout).parse(time));
            return c.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public abstract static class EtChange {
        //有内容
        public abstract void etYes();

        //无内容
        public void etNo() {
        }
    }

    /**
     * 将html文本内容中包含img标签的图片，宽度变为屏幕宽度，高度根据宽度比例自适应
     **/
    public static String getNewContent(String htmltext) {
        try {
            if (!htmltext.contains("&nbsp;") && htmltext.contains("&nbsp")) {
                htmltext = htmltext.replaceAll("&nbsp", "&nbsp;");
            }
            Document doc = Jsoup.parse(htmltext);

            Elements elementsAll = doc.getAllElements();
            for (Element span : elementsAll) {
//                Elements p = span.getElementsByTag("p");
////                p.attr("style","font-size:16px;width:100%;margin:1rem 0px");
////                Elements h1 = span.getElementsByTag("h1");
                if (!span.toString().contains("<html") &&
                        !span.toString().contains("<head") &&
                        !span.toString().contains("<body") &&
                        !span.toString().contains("<div")) {//.p:last-child{margin-bottom!important}
                    span.attr("style", "font-size:16px;width:100%;margin-bottom:1rem;line-height:26px;letter-spacing:1px;");
                }
            }
            Elements head = doc.getElementsByTag("head");
            head.get(0).html("<style>*{border:0;margin:0;padding:0;};p:last-child{margin-bottom:0px;!important}</style>");
            Elements elements = doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("width", "100%").attr("height", "auto");
                String str = element.attr("src");
                if (str.contains("image/png;base64")) {
                    element.attr("src", "");
                }
            }
            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }

    /**
     * 得到网页中图片的地址
     */
    public static ArrayList<String> getImgStr(String htmlStr) {
        ArrayList<String> pics = new ArrayList<>();
        String img = "";
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    public static int getTotalPage(int totalCount, int pageSize) {
        return (totalCount + pageSize - 1) / pageSize;
    }

    public static List<Double> getAverageV(double maxV) {
        List<Double> lists = new ArrayList<>();
        double b = Math.ceil(maxV / 5);
        double c = b;
        lists.add(0d);
        for (int i = 0; i < 5; i++) {
            c = c + b;
            lists.add(c);
        }
        return lists;
    }

    public static void loginNo(Context mContext) {
        ToastUtils.showShort( "请先登录");
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    public static int getPos(Date currTime) {
//        Date currTime = new Date(); // 当前日期baidu
        GregorianCalendar cale = new GregorianCalendar(); // 格里高利日历
        cale.setTime(currTime); // 绑定当前日期
        cale.set(Calendar.DAY_OF_MONTH, 1); // 变为本月第一天
        if (cale.getFirstDayOfWeek() == Calendar.SUNDAY) { // 每周以星zhi期日开始dao
            return cale.get(Calendar.DAY_OF_WEEK) - 1;
        } else { // 每周以星期一开始（一般不会再有其它情况）
            return cale.get(Calendar.DAY_OF_WEEK);
        }
    }

    /**
     * 功能描述：返回日期
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getNewContent1(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }


    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }


    public static String getLocalVideoDuration(Context mContext,String videoPath) {
        //除以 1000 返回是秒
        int duration;
        try {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//            mmr.setDataSource(videoPath);
            mmr.setDataSource(mContext, Uri.parse(videoPath));
//            mmr.setDataSource(mContext, videoPaths);
            duration = Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)) / 1000;

            //时长(毫秒)
//            String duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);
            //宽
            String width = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
            //高
            String height = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return String.valueOf(duration);
    }
    public static String getRealPathFromURI(Context context, String videoPath) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query( Uri.parse(videoPath),  proj, null, null, null);
            if(cursor!=null){
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            }
            return videoPath;
        }catch (Exception e){
            e.printStackTrace();
            return videoPath;
        }

        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    /**
     * 获取上个月的天数
     * @return
     */
    public static int getMonthLastDay() {
        Calendar cal = Calendar.getInstance();
//        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
//        int dow = cal.get(Calendar.DAY_OF_WEEK);
//        int dom = cal.get(Calendar.DAY_OF_MONTH);
//        int doy = cal.get(Calendar.DAY_OF_YEAR);
//
//        System.out.println("当期时间: " + cal.getTime());
//        System.out.println("日期: " + day);
//        System.out.println("月份: " + month);
//        System.out.println("年份: " + year);
//        System.out.println("一周的第几天: " + dow);  // 星期日为一周的第一天输出为 1，星期一输出为 2，以此类推
//        System.out.println("一月中的第几天: " + dom);
//        System.out.println("一年的第几天: " + doy);
        Date day = new Date(year, month, 0);
        return day.getDate();
    }

    /**
     * 获取过去7天内的日期数组
     *
     * @param intervals intervals天内
     * @return 日期数组
     */
    public static ArrayList<String> getDays(int intervals, String type) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = intervals - 1; i >= 0; i--) {
            pastDaysList.add(getPastDate(i, type));
        }
        return pastDaysList;
    }

    public static String getTimeGeShiYw(long date) {
        if (date < 60) {
            return date + "s";
        } else if (date > 60 && date < 3600) {
            long m = date / 60;
            long s = date % 60;
            return m + "m" + s + "s";
        } else {
            long h = date / 3600;
            long m = (date % 3600) / 60;
            long s = (date % 3600) % 60;
            return h + "h" + m + "m" + s + "s";
        }
    }

    public static String getTimeGeShi(long date) {
        if (date <= 60) {
            return date + "秒";
        } else if (date <= 3600) {
            long m = date / 60;
            long s = date % 60;
            return m + "分" + s + "秒";
        } else {
            long h = date / 3600;
            long m = (date % 3600) / 60;
            long s = (date % 3600) % 60;
            return h + "小时" + m + "分" + s + "秒";
        }
    }

    public static String getTimeGeShiH(long date) {
        if (date < 60) {
            return date + "'";
        } else if (date > 60 && date < 3600) {
            long m = date / 60;
            long s = date % 60;
            return m + "'" + s + "'";
        } else {
            long h = date / 3600;
            long m = (date % 3600) / 60;
            long s = (date % 3600) % 60;
            return h + "'" + m + "'" + s + "'";
        }
    }

    public static Double getTimeFenMiao(long date) {
        if (date < 60) {
            return Double.valueOf("0." + date);
        } else {
            long m = date / 60;
            long s = date % 60;
            return Double.valueOf(m + "." + s);
        }
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @param type
     * @return
     */
    public static String getPastDate(int past, String type) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(type);
        String result = format.format(today);
        return result;
    }


    public static ArrayList<String> getDayMonth7() {
        ArrayList<String> lists = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int flag = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, 1 - flag);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        return lists;
    }

    public static String getDayMonth7(int day) {
        String str = String.valueOf(day);
        if (str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * int double 两位小时
     *
     * @param obj
     * @return
     */
    public static String getValue(String obj) {
        Log.w("getValue", "obj:" + obj);
        if (isIntegerForDouble(Double.valueOf(obj))) {
            return getValue(Double.valueOf(obj));
        }
        return String.format("%.2f", Double.valueOf(obj));
    }

    /**
     * int double 两位小时
     *
     * @param obj
     * @return
     */
    public static String getValue(double obj) {
        if (isIntegerForDouble(obj)) {
            return String.valueOf((int) obj);
        }
        return String.format("%.2f", obj);
    }

    /**
     * 判断double是否是整数
     *
     * @param obj
     * @return
     */
    public static boolean isIntegerForDouble(double obj) {
        double eps = 1e-10;  // 精度范围
        return obj - Math.floor(obj) < eps;
    }


    public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,(int) height, matrix, true);
        return bitmap;
    }
    /**
     * 获取版本名称
     *
     * @param context 上下文
     *
     * @return 版本名称
     */
    public static String getVersionName(Context context) {

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String isDouble(double test) {
        if (!(test - Math.floor(test) < 1e-10)) {
            return String.format("%.2f", test).replace(".00","");
        }
        return String.valueOf((int) Math.round(test));
    }

    /**
     * 将 int 转换为 ip 字符串
     *
     * @param ipInt 用 int 表示的 ip 值
     * @return ip字符串，如 127.0.0.1
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String int2Ip(int ipInt) {
        String[] ipString = new String[4];
        for (int i = 0; i < 4; i++) {
            // 每 8 位为一段，这里取当前要处理的最高位的位置
            int pos = i * 8;
            // 取当前处理的 ip 段的值
            int and = ipInt & (255 << pos);
            // 将当前 ip 段转换为 0 ~ 255 的数字，注意这里必须使用无符号右移
            ipString[i] = String.valueOf(and >>> pos);
        }
        return String.join(".", ipString);
    }
    public static String getBai_V(List<PostUser.SportLogInfo.DetailsBean.LogsBean> logs) {
        double userWeiget = Double.parseDouble(SharedUtils.singleton().get(ConstValues.USER_WEIGHT, "0"));//体重
        double heartbeat_pj = 0;
        double heartbeat = 0;
        for(int i=0;i<logs.size();i++){
            heartbeat+=Double.parseDouble(logs.get(i).getHeartRate());
        }
        if(heartbeat!=0){
            heartbeat_pj = heartbeat/logs.size();
        }
        String userBirthday = SharedUtils.singleton().get(ConstValues.USER_BIRTHDAY, "");//年龄
        int age = 23;
        if(StringUtil.isNotBlank(userBirthday)){
            age = getAgeFromBirthTime(userBirthday);
        }
        int userGender = Integer.parseInt(SharedUtils.singleton().get(ConstValues.USER_GENDER, "0"));//性别
        int ageM = getAgeM(userGender,age);
        double bai = ageM*(1.5472*(heartbeat_pj/(220d-age))-0.5753)/3;
        Log.w("---》》》","bai:"+bai);
//        if(bai<=0 && userWeiget>0){
//            bai = 0;
//            for(int i=0;i<logs.size();i++){
//                double aa = Double.parseDouble(logs.get(i).getWatt()) * 0.0143 / 5.05 / 1000 / userWeiget / 3.5;
//                if(aa>0){
//                    bai+=aa;
//                }
//                Log.w("-------------->>>>>","bai:"+bai);
//            }
//        }
        if(bai<=0){
            bai = 0;
        }
        return isDouble(bai);
    }

    public static int getAgeM(int userGender,int age) {
        if(18<=age && age<=25){
            return userGender==1?39:37;
        }else if(26<=age && age<=35){
            return userGender==1?37:35;
        }else if(36<=age && age<=45){
            return userGender==1?33:30;
        }else if(46<=age && age<=55){
            return userGender==1?30:27;
        }else if(56<=age && age<=65){
            return userGender==1?27:24;
        }else if(age>65){
            return userGender==1?24:21;
        }
        return 0;
    }
    // 根据年月日计算年龄,birthTimeString:"1994-11-14 00:00:00"
    public static int getAgeFromBirthTime(String birthTimeString) {
        // 先截取到字符串中的年、月、日
        Calendar calendar = Calendar.getInstance();
        if(StringUtil.isNotBlank(birthTimeString)){
            try {
                calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(birthTimeString));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        int selectYear = calendar.get(Calendar.YEAR);
        int selectMonth = calendar.get(Calendar.MONTH);
        int selectDay = calendar.get(Calendar.DAY_OF_MONTH);

        // 得到当前时间的年、月、日
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayNow = cal.get(Calendar.DATE);


        // 用当前年月日减去生日年月日
        int yearMinus = yearNow - selectYear;
        int monthMinus = monthNow - selectMonth;
        int dayMinus = dayNow - selectDay;

        int age = yearMinus;// 先大致赋值
        if (yearMinus < 0) {// 选了未来的年份
            age = 0;
        } else if (yearMinus == 0) {// 同年的，要么为1，要么为0
            if (monthMinus < 0) {// 选了未来的月份
                age = 0;
            } else if (monthMinus == 0) {// 同月份的
                if (dayMinus < 0) {// 选了未来的日期
                    age = 0;
                } else if (dayMinus >= 0) {
                    age = 1;
                }
            } else if (monthMinus > 0) {
                age = 1;
            }
        } else if (yearMinus > 0) {
            if (monthMinus < 0) {// 当前月>生日月
            } else if (monthMinus == 0) {// 同月份的，再根据日期计算年龄
                if (dayMinus < 0) {
                } else if (dayMinus >= 0) {
                    age = age + 1;
                }
            } else if (monthMinus > 0) {
                age = age + 1;
            }
        }
        return age;
    }

    public static Bitmap decodeUriAsBitmapFromNet(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput( true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory. decodeStream(is);

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }
}
