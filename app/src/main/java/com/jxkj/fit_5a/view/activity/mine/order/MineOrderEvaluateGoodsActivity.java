package com.jxkj.fit_5a.view.activity.mine.order;

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
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.utils.PictureUtil;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.RatingBar;
import com.jxkj.fit_5a.view.adapter.SpPhotoAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MineOrderEvaluateGoodsActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_lefttext)
    TextView mTvLefttext;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.iv_rightimg_two)
    ImageView mIvRightimgTwo;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_spec1)
    TextView mTvSpec1;
    @BindView(R.id.tv_spec2)
    TextView mTvSpec2;
    @BindView(R.id.ratingbar)
    RatingBar mRatingbar;
    @BindView(R.id.et_con)
    EditText mEtCon;
    @BindView(R.id.rv_photo)
    RecyclerView mRvPhoto;
    @BindView(R.id.iv_nm)
    ImageView mIvNm;
    @BindView(R.id.ratingbar1)
    RatingBar mRatingbar1;
    @BindView(R.id.ratingbar2)
    RatingBar mRatingbar2;
    @BindView(R.id.tv_fabu)
    TextView mTvFabu;
    @BindView(R.id.tv_r)
    TextView mTvR;
    @BindView(R.id.tv_r1)
    TextView mTvR1;
    @BindView(R.id.tv_r2)
    TextView mTvR2;
    private int isShow = 1;
    private int rank1 = 5;
    private int rank2 = 5;
    private int rank3 = 5;
    private SpPhotoAdapter mSpPhotoAdapter;
    OrderInfoData.ListBean.ProductListBean mProductListBean;
    @Override
    protected int getContentView() {
        return R.layout.activity_evaluate_goods;
    }

    private void initTitle() {
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mTvTitle.setText("评价");
    }

    @Override
    protected void initViews() {
        mProductListBean = (OrderInfoData.ListBean.ProductListBean) getIntent().getSerializableExtra("ProductListBean");
        initTitle();
        mTvName.setText(mProductListBean.getName());
        mTvSpec1.setText("规格："+(StringUtil.isNotBlank(mProductListBean.getSkuName())?mProductListBean.getSkuName():"无"));

        initRvXq();

        mIvNm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShow!=1){
                    isShow= 1;
                    mIvNm.setImageDrawable(MineOrderEvaluateGoodsActivity.this.getResources().getDrawable(R.drawable.wxz_));
                }else{
                    isShow= 0;
                    mIvNm.setImageDrawable(MineOrderEvaluateGoodsActivity.this.getResources().getDrawable(R.drawable.wxz));
                }
            }
        });

        mTvFabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCommentOrder();
            }
        });

        mRatingbar.setStar(5);
        mRatingbar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                rank1 = (int) ratingCount;
                setTvEvaluatel(rank1, mTvR);
            }
        });
        mRatingbar1.setStar(5);
        mRatingbar1.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                rank2 = (int) ratingCount;
                setTvEvaluatel(rank2, mTvR1);
            }
        });
        mRatingbar2.setStar(5);
        mRatingbar2.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                rank3 = (int) ratingCount;
                setTvEvaluatel(rank3, mTvR2);
            }
        });


    }


    private void setTvEvaluatel(int ratingCount, TextView tv_evaluate) {
        switch (ratingCount) {
            case 5:
                tv_evaluate.setText("非常满意");
                break;
            case 4:
                tv_evaluate.setText("满意");
                break;
            case 3:
                tv_evaluate.setText("一般");
                break;
            case 2:
                tv_evaluate.setText("差");
                break;
            case 1:
            case 0:
                tv_evaluate.setText("非常差");
                break;
            default:
                break;
        }
    }

    private void initRvXq() {
        mRvPhoto.setLayoutManager(new GridLayoutManager(this, 4));
        mRvPhoto.setHasFixedSize(true);
        mSpPhotoAdapter = new SpPhotoAdapter(null);
        mRvPhoto.setAdapter(mSpPhotoAdapter);
        List<LocalMedia> list = new ArrayList<>();
        LocalMedia mLocalMedia = new LocalMedia();
        mLocalMedia.setPath("-1");
        list.add(mLocalMedia);
        mSpPhotoAdapter.setNewData(list);
        mSpPhotoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mSpPhotoAdapter.getData().get(position).getPath().equals("-1")) {
                    MatisseUtils.gotoSelectPhoto(MineOrderEvaluateGoodsActivity.this, 10 - mSpPhotoAdapter.getData().size(), false);
                }
            }
        });

        mSpPhotoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mSpPhotoAdapter.remove(position);
//                mPicList.remove(position);
                LocalMedia mLocalMedia = new LocalMedia();
                mLocalMedia.setPath("-1");
                if (!mSpPhotoAdapter.getData().contains(mLocalMedia)) {
                    mSpPhotoAdapter.addData(mLocalMedia);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    postOSSFile(selectList);
                    break;
            }
        }
    }


    List <String> listUrls= new ArrayList<>();

    private void postOSSFile(List<LocalMedia> selectList) {
        HttpRequestUtils.postOSSFile(2,new HttpRequestUtils.OSSClientInterface() {
            @Override
            public void succeed(double pos) {
                if(pos==0){
                    ToastUtils.showShort("获取oss信息错误");
                    return;
                }
                if(selectList.size()>0){
                    postImg(selectList,0);
                }
            }
        });
    }

    private void postImg(List<LocalMedia> selectList, int i) {
        String path = PictureUtil.compressBmpFileToTargetSize(new File(selectList.get(i).getRealPath()),1024*1024).getPath();
        String fileName = StringUtil.stringToMD5(path)+".jpg";
        i++;
        int finalI = i;
        HttpRequestUtils.initOSSClient(MineOrderEvaluateGoodsActivity.this, fileName,path, new HttpRequestUtils.OSSClientInterface() {
            @Override
            public void succeed(double pos) {
                if(pos==101){
                    String urlpath= SharedUtils.singleton().get(ConstValues.host,"")
                            +"/"+SharedUtils.singleton().get(ConstValues.dir,"")+"/"+fileName;
                    listUrls.add(urlpath);
                    mSpPhotoAdapter.addData(mSpPhotoAdapter.getData().size() - 1, selectList.get(finalI-1));
                    if (mSpPhotoAdapter.getData().size() > 6 && mSpPhotoAdapter.getData().contains("-1")) {
                        mSpPhotoAdapter.remove(mSpPhotoAdapter.getData().size() - 1);
                    }
                    mSpPhotoAdapter.notifyDataSetChanged();
                    if(selectList.size()>finalI){
                        postImg(selectList, finalI);
                    }else{
                        Log.w("listUrls","listUrls:"+listUrls.toString());
                    }
                }
            }
        });
    }

    private void postCommentOrder() {
        if(StringUtil.isBlank(mProductListBean.getOrderId()) || StringUtil.isBlank(mEtCon.getText().toString())){
            ToastUtils.showShort("内容不能为空");
            return;
        }
        PostUser.Comment comment = new PostUser.Comment();
        comment.setOrderId(mProductListBean.getOrderId());
        comment.setUserId(SharedUtils.getUserId()+"");
        comment.setServiceScore(rank3+"");
        comment.setDeliveryScore(rank2+"");
        List<PostUser.Comment.ProductCommentsBean> productComments = new ArrayList<>();
        PostUser.Comment.ProductCommentsBean productCommentsBean = new PostUser.Comment.ProductCommentsBean();
        productCommentsBean.setContent(mEtCon.getText().toString());
        String imgStr = listUrls.toString().replace("[","");
        imgStr.replace("]","");
        productCommentsBean.setImgStr(imgStr.trim());
        productCommentsBean.setProductId(mProductListBean.getProductId());
        productCommentsBean.setIsShow(isShow+"");
        productCommentsBean.setProductScore(rank1+"");
        productComments.add(productCommentsBean);
        comment.setProductComments(productComments);
        RetrofitUtil.getInstance().apiService()
                .postCommentOrder(comment)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("评论成功");
                            IntentUtils.getInstence().intent(MineOrderEvaluateGoodsActivity.this, MainActivity.class);
                            MineOrderEvaluateGoodsActivity.this.finish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }

}
