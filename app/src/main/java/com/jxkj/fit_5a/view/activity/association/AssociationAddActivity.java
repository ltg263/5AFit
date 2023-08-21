package com.jxkj.fit_5a.view.activity.association;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.utils.PictureUtil;
import com.jxkj.fit_5a.conpoment.utils.SharedAssociationUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ThirdLoginUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;
import com.jxkj.fit_5a.entity.MediaInfoBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.VideoInfoBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;
import com.jxkj.fit_5a.view.activity.mine.MineSetActivity;
import com.jxkj.fit_5a.view.adapter.SpPhotoAdapter;
import com.jxkj.fit_5a.view.map.LocationSelectActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AssociationAddActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.ll_topic)
    LinearLayout ll_topic;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.tv_gk)
    TextView mTvGk;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.rv_list_zp)
    RecyclerView mRvListZp;
    @BindView(R.id.tv_lefttext)
    TextView mTvLefttext;
    @BindView(R.id.iv_rightimg_two)
    ImageView mIvRightimgTwo;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.tv_topics)
    TextView mTvTopics;
    @BindView(R.id.tv_position)
    TextView mTvPosition;
    @BindView(R.id.rl_v)
    RelativeLayout rl_v;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.iv_v)
    ImageView iv_v;
    @BindView(R.id.iv_tb)
    ImageView iv_tb;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.ll_tb)
    LinearLayout ll_tb;
    @BindView(R.id.ll_zuo_f)
    LinearLayout ll_zuo_f;
    @BindView(R.id.tv_title_f)
    TextView tv_title_f;
    @BindView(R.id.iv_title_f)
    ImageView iv_title_f;
    private SpPhotoAdapter mSpPhotoAdapter;
    int shareType = 1;
    int type = 2;//2:图片;3:视频)
    String topic = "";
    String circleId = null;
//    0 用户相关 1动态相关 2圈子相关 3商城商品相关 4商城商品评论相关
//     *             5商品封面(商品列表) 6商品轮播图 7商城商品详情 8商城商品规格封面 9商城商品分类
//     *             10礼物 11任务 12勋章 13兴趣 14帮助 15广告 16运动
    int OSSFile_type = 1;

    QueryPopularBean mQueryPopularBean;
    @Override
    protected int getContentView() {
        return R.layout.activity_community_add;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        type = getIntent().getIntExtra("type",0);
//        mQueryPopularBean = (QueryPopularBean) intent.getSerializableExtra("mData");
        List<QueryPopularBean> mData = SharedAssociationUtils.singleton().getSharedHistoryEquipment();
        if(mData!=null){
            for(int i=0;i<mData.size();i++){
                if(type == -1){
                    mQueryPopularBean = mData.get(0);
                }
                if(type == 2 && 2 == Integer.parseInt(mData.get(i).getContentType())){
                    mQueryPopularBean = mData.get(i);
                }

                if(type == 3 && 3 == Integer.parseInt(mData.get(i).getContentType())){
                    mQueryPopularBean = mData.get(i);
                }
            }
        }
        initRvXq();
        if(mQueryPopularBean!=null){//草稿箱
            media = mQueryPopularBean.getMedia();
            mEtContent.setText(mQueryPopularBean.getContent());

            if (type == 2) {
                if(StringUtil.isNotBlank(media) && media.contains("imageUrl")){
                    try {
                        JSONArray array = new JSONArray(media);
                        for(int i=0;i<array.length();i++){
                            listUrls.add(new MediaInfoBean(array.getJSONObject(i).getString("imageUrl"),"2"));
                            LocalMedia mLocalMedia = new LocalMedia();
                            mLocalMedia.setPath("-3");
                            mLocalMedia.setCompressPath(array.getJSONObject(i).getString("imageUrl"));
                            mSpPhotoAdapter.addData(mSpPhotoAdapter.getData().size() - 1, mLocalMedia);
                        }
                        if (mSpPhotoAdapter.getData().size() > 6 && mSpPhotoAdapter.getData().contains("-1")) {
                            mSpPhotoAdapter.remove(mSpPhotoAdapter.getData().size() - 1);
                        }
                        mSpPhotoAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (type == 3) {
                if(StringUtil.isNotBlank(media) && media.contains("imageUrl")){
                    try {
                        JSONArray array = new JSONArray(media);
                       String imageUrl= array.getJSONObject(0).getString("imageUrl");
                       String vedioId= array.getJSONObject(0).getString("vedioId");
                       String vedioDuration = array.getJSONObject(0).getString("vedioDuration");
                        listUrls.add(new MediaInfoBean(imageUrl,"3",vedioId, vedioDuration));
                        mRvListZp.setVisibility(View.GONE);
                        rl_v.setVisibility(View.VISIBLE);
                        GlideImageUtils.setGlideImage(AssociationAddActivity.this,imageUrl, iv_v);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        circleId = intent.getStringExtra("circleId");
        topic = intent.getStringExtra("topic");
        mTvTitle.setText("社群动态");
        if(StringUtil.isNotBlank(circleId)){
//            OSSFile_type = 2;
            ll_tb.setVisibility(View.VISIBLE);
            ll_zuo_f.setVisibility(View.VISIBLE);
            tv_title_f.setText(intent.getStringExtra("circleName"));
            ll_topic.setVisibility(View.GONE);
            mTvTitle.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
            mTvGk.setVisibility(View.GONE);
            GlideImgLoader.loadImageViewWithCirclr(this,intent.getStringExtra("circleIcon"),iv_title_f);
        }
        if(StringUtil.isNotBlank(topic)){
            topics = topic;
            mTvTitle.setText("话题动态");
            ll_topic.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mTvRighttext.setText("发布");
        mTvRighttext.setTextColor(getResources().getColor(R.color.color_text_theme));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_fabu));
    }

    private void initRvXq() {
        mRvListZp.setLayoutManager(new GridLayoutManager(this, 4));
        mRvListZp.setHasFixedSize(true);
        mSpPhotoAdapter = new SpPhotoAdapter(null);
        mRvListZp.setAdapter(mSpPhotoAdapter);
        List<LocalMedia> list = new ArrayList<>();
        LocalMedia mLocalMedia = new LocalMedia();
        mLocalMedia.setPath("-1");
        list.add(mLocalMedia);
        mSpPhotoAdapter.setNewData(list);
        mSpPhotoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mSpPhotoAdapter.getData().get(position).getPath().equals("-1")) {
//                    if (mSpPhotoAdapter.getData().size() == 1 || type == -1) {
//                        initPopupWindow();
//                        return;
//                    }
                    if (type == 2) {
                        MatisseUtils.gotoSelectPhoto(AssociationAddActivity.this, 10 - mSpPhotoAdapter.getData().size(), false);
                    } else {
                        MatisseUtils.gotoSelectVideo(AssociationAddActivity.this);
                    }
                }
            }
        });

        mSpPhotoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mSpPhotoAdapter.remove(position);
                listUrls.remove(position);
                if (!mSpPhotoAdapter.getData().get(mSpPhotoAdapter.getData().size() - 1).getPath().equals("-1")) {
                    LocalMedia mLocalMedia = new LocalMedia();
                    mLocalMedia.setPath("-1");
                    mSpPhotoAdapter.addData(mLocalMedia);
                }
            }
        });
    }

    String position = null;
    String location = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    Log.w("selectList","selectList:"+selectList.size());
                    if (type == 2) {
                        setIMaaa(selectList);
                    }
                    if (type == 3) {
                        setVideo(selectList);
                    }
                    break;
                case 2:
                    double latitude = data.getDoubleExtra("latitude", 0.0);
                    double longitude = data.getDoubleExtra("longitude", 0.0);
                    location = "[" + longitude + "," + latitude + "]";
                    position = data.getStringExtra("address");
                    mTvPosition.setText(position);
//                    mTvPosition.setText("详细地址："+address+"\n经度："+longitude+"\n纬度："+latitude);
//                    Success/storage/emulated/0/DCIM/Camera/20210111_100058.jpg
                    break;
                case 3:
                    topics = data.getStringExtra("topics");
                    mTvTopics.setText("");
                    if(StringUtil.isNotBlank(topics)){
                        mTvTopics.setText(topics);
                    }
                    break;
            }
        }
    }
    String topics = null;
    private void setVideo(List<LocalMedia> selectList) {
        String path = PictureUtil.getVideoThumb(this,selectList.get(0).getPath());
        if (StringUtil.isNotBlank(path)) {
            HttpRequestUtils.postOSSFile(OSSFile_type, new HttpRequestUtils.OSSClientInterface() {
                @Override
                public void succeed(double pos) {
                    if (pos == 0) {
                        ToastUtils.showShortToast(AssociationAddActivity.this,"获取oss信息错误");
                        return;
                    }
                    double w = selectList.get(0).getWidth();
                    double h = selectList.get(0).getHeight();
                    String fileImgName = StringUtil.stringToMD5(path) + ".jpg";
                    HttpRequestUtils.initOSSClient(AssociationAddActivity.this, fileImgName, path, new HttpRequestUtils.OSSClientInterface() {
                        @Override
                        public void succeed(double pos) {
                            if (pos == 101) {
                                String coverUrl = SharedUtils.singleton().get(ConstValues.host, "")
                                        + "/" + SharedUtils.singleton().get(ConstValues.dir, "") + "/" + fileImgName+"?imageScale="+StringUtil.getValue(w/h);
                                Log.w("listUrls", "coverUrl:" + coverUrl);
                                String fileVideoName = StringUtil.stringToMD5(selectList.get(0).getPath());
                                HttpRequestUtils.getUploadVideo(fileVideoName + ".mp4", "动态视频_Android", coverUrl, new HttpRequestUtils.VideoInterface() {
                                    @Override
                                    public void succeed(VideoInfoBean result) {
                                        if(result.getStatusCode()==200){
                                            listUrls.clear();
                                            listUrls.add(new MediaInfoBean(coverUrl,"3",result.getVideoId(),StringUtil.getLocalVideoDuration(AssociationAddActivity.this,selectList.get(0).getPath())));
                                            mRvListZp.setVisibility(View.GONE);
                                            rl_v.setVisibility(View.VISIBLE);
                                            GlideImageUtils.setGlideImage(AssociationAddActivity.this, coverUrl, iv_v);
                                            HttpRequestUtils.initAcc(AssociationAddActivity.this,
                                                    StringUtil.getRealPathFromURI(AssociationAddActivity.this,selectList.get(0).getPath()),result.getUploadAuth(),result.getUploadAddress(),coverUrl);
                                            Log.w("listUrls", "listUrls:" + listUrls.toString());
                                        }
                                    }
                                });
                            }
                        }
                    });
                }

            });
        }
    }

    List<MediaInfoBean> listUrls = new ArrayList<>();

    private void setIMaaa(List<LocalMedia> selectList) {
        HttpRequestUtils.postOSSFile(OSSFile_type, new HttpRequestUtils.OSSClientInterface() {
            @Override
            public void succeed(double pos) {
                if (pos == 0) {
                    ToastUtils.showShortToast(AssociationAddActivity.this,"获取oss信息错误");
                    return;
                }
                if (selectList.size() > 0) {
                    postImg(selectList, 0);
                }
            }
        });
    }

    private void postImg(List<LocalMedia> selectList, int i) {
        String path = PictureUtil.compressBmpFileToTargetSize(new File(selectList.get(i).getRealPath()), 1024 * 1024).getPath();
        double w = selectList.get(i).getWidth();
        double h = selectList.get(i).getHeight();
        String fileName = StringUtil.stringToMD5(path) + ".jpg";
        i++;
        int finalI = i;
        HttpRequestUtils.initOSSClient(AssociationAddActivity.this, fileName, path, new HttpRequestUtils.OSSClientInterface() {
            @Override
            public void succeed(double pos) {
                if (pos == 101) {
                    String urlpath = SharedUtils.singleton().get(ConstValues.host, "")
                            + "/" + SharedUtils.singleton().get(ConstValues.dir, "") + "/" + fileName+"?imageScale="+StringUtil.getValue(w/h);
                    listUrls.add(new MediaInfoBean(urlpath,"2"));
                    mSpPhotoAdapter.addData(mSpPhotoAdapter.getData().size() - 1, selectList.get(finalI - 1));
                    if (mSpPhotoAdapter.getData().size() > 6 && mSpPhotoAdapter.getData().contains("-1")) {
                        mSpPhotoAdapter.remove(mSpPhotoAdapter.getData().size() - 1);
                    }
                    mSpPhotoAdapter.notifyDataSetChanged();
                    if (selectList.size() > finalI) {
                        postImg(selectList, finalI);
                    } else {
                        Log.w("listUrls111111", "listUrls:" + listUrls.toString());
                    }
                }
            }
        });
    }
    boolean isSyncPersonalMoment = true;
    private List<String> mFeedTypeList = new ArrayList<>();

    @OnClick({R.id.ll_back, R.id.iv_tb,R.id.iv_close, R.id.tv_righttext, R.id.iv_rightimg, R.id.tv_gk, R.id.tv_topics, R.id.tv_position})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                postPublishMomentCgx();
                break;
            case R.id.iv_tb:
                if(isSyncPersonalMoment){
                    iv_tb.setImageResource(R.drawable.icon_select_yd_yse);
                }else{
                    iv_tb.setImageResource(R.drawable.icon_select_yd_no);
                }
                isSyncPersonalMoment = !isSyncPersonalMoment;
                break;
            case R.id.iv_close:
                listUrls.clear();
                rl_v.setVisibility(View.GONE);
                mRvListZp.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_rightimg:
            case R.id.tv_righttext:
                postPublishMoment();
                break;
            case R.id.tv_topics:
                Intent intent = new Intent(AssociationAddActivity.this, TopicTabsActivity.class);
                intent.putExtra("type","1");
                startActivityForResult(intent, 3);
                break;
            case R.id.tv_position:
                startActivityForResult(new Intent(AssociationAddActivity.this, LocationSelectActivity.class), 2);
                break;
            case R.id.tv_gk:
                mFeedTypeList.clear();
                mFeedTypeList.add("公开可见");
                mFeedTypeList.add("粉丝可见");
                mFeedTypeList.add("私密");
                PickerViewUtils.selectorCustom(this, mFeedTypeList, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvGk.setText(mFeedTypeList.get(pos));
                        if (pos == 0) {
                            shareType = 1;
                        } else if (pos == 1) {
                            shareType = 2;
                        } else if (pos == 2) {
                            shareType = 3;
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void onBackPressed() {
        postPublishMomentCgx();
    }

    private void postPublishMomentCgx() {
        SharedAssociationUtils.singleton().updateSharedHistoryEquipmentUpdate_Type(type);
//        if(mQueryPopularBean!=null){
//            int pos = getIntent().getIntExtra("position",0);
//            SharedAssociationUtils.singleton().updateSharedHistoryEquipmentUpdate(pos);
//        }
        String content = mEtContent.getText().toString();
        mTvPosition.getText().toString();
        media = listUrls.toString();
        String[] str = null;
        if(StringUtil.isNotBlank(topics)){
            str = new String[1];
            str[0]=topics;
        }
        QueryPopularBean mQueryPopularBean = new QueryPopularBean();
        mQueryPopularBean.setContent(content);
        mQueryPopularBean.setSimpleContent(content);
        if(listUrls.size()>0){
            mQueryPopularBean.setMedia(media);
        }
        mQueryPopularBean.setTopicArr(topics);
        mQueryPopularBean.setContentType(type+"");
        mQueryPopularBean.setIsSyncPersonalMoment(isSyncPersonalMoment);
        mQueryPopularBean.setCircleId(circleId);
        mQueryPopularBean.setCircleName(getIntent().getStringExtra("circleName"));
        mQueryPopularBean.setCircleIcon(getIntent().getStringExtra("circleIcon"));
        mQueryPopularBean.setTimestamp(System.currentTimeMillis());
        mQueryPopularBean.setTopicArr(topic);
        if(StringUtil.isBlank(mQueryPopularBean.getContent())
                && StringUtil.isBlank(mQueryPopularBean.getMedia())){
            finish();
            return;
        }
        DialogUtils.showUnificationDialog(this, "保留草稿","是否保留草稿？", "不保留","保留",true,false,
                new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String pos) {
                        if(pos.equals("2")){
                            SharedAssociationUtils.singleton().putSharedHistoryEquipment(mQueryPopularBean);
                        }
                        finish();
                    }
                });
    }

    String media;

    private void postPublishMoment() {
        String content = mEtContent.getText().toString();
        mTvPosition.getText().toString();
        if (StringUtil.isBlank(content)) {
            ToastUtils.showShortToast(AssociationAddActivity.this,"内容不能为空");
            return;
        }
        if (content.length()>500) {
            ToastUtils.showShortToast(AssociationAddActivity.this,"内容不能大于500字");
            return;
        }
        media = listUrls.toString();
        String[] str = null;
        if(StringUtil.isNotBlank(topics)){
           str = new String[1];
            str[0]=topics;
        }
        show();
        RetrofitUtil.getInstance().apiService()
                .postPublishMoment(content, type + "", shareType + "",media,
                        position, location, str,circleId,isSyncPersonalMoment)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
//                            DialogUtils.showDialogCgCircle(AssociationAddActivity.this, "发布成功", 1,
//                                    new DialogUtils.DialogLyInterface() {
//                                        @Override
//                                        public void btnConfirm() {
//                                            AssociationAddActivity.this.finish();
//                                        }
//                                    });
                            DialogUtils.showUnificationDialog(AssociationAddActivity.this, "发布成功",
                                    "恭喜您的动态已通过审核","确定",false,new DialogUtils.UnificationDialogInterface() {
                                        @Override
                                        public void bntClickListener(String str) {
                                            AssociationAddActivity.this.finish();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });

    }

    public static void startActivityAddAssociation(Context mContext, int type,String circleId,String circleName,
                                                   String circleIcon,String topics,QueryPopularBean mData,int position) {
        Intent intent = new Intent(mContext, AssociationAddActivity.class);
        intent.putExtra("mData", mData);
        intent.putExtra("type", type);
        intent.putExtra("circleId", circleId);
        intent.putExtra("circleName", circleName);
        intent.putExtra("circleIcon", circleIcon);
        intent.putExtra("topics", topics);
        intent.putExtra("position", position);
        mContext.startActivity(intent);
    }

    public static void startActivityAddAssociation(Context mContext, int type,String circleId,String circleName,String circleIcon,String topics) {
        Intent intent = new Intent(mContext, AssociationAddActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("circleId", circleId);
        intent.putExtra("circleName", circleName);
        intent.putExtra("circleIcon", circleIcon);
        intent.putExtra("topics", topics);
        mContext.startActivity(intent);
    }

}
