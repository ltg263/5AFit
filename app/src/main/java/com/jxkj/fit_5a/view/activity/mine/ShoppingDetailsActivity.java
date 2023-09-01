package com.jxkj.fit_5a.view.activity.mine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.PrizeListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogChoicePackage;
import com.jxkj.fit_5a.conpoment.view.JudgeNestedScrollView;
import com.jxkj.fit_5a.conpoment.view.PopupWindowYhq;
import com.jxkj.fit_5a.conpoment.view.SquareBannerLayout;
import com.jxkj.fit_5a.entity.AddressData;
import com.jxkj.fit_5a.entity.NotObtainedBean;
import com.jxkj.fit_5a.entity.ParameterBean;
import com.jxkj.fit_5a.entity.PostOrderGwcInfo;
import com.jxkj.fit_5a.entity.PostOrderInfo;
import com.jxkj.fit_5a.entity.ProductDetailsBean;
import com.jxkj.fit_5a.view.activity.mine.order.AddressActivity;
import com.jxkj.fit_5a.view.activity.mine.order.OrderAffirmActivity;
import com.jxkj.fit_5a.view.adapter.ShoppingPingJiaAdapter;
import com.jxkj.fit_5a.view.fragment.HomeFourFragment;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_gui_ge)
    TextView tv_gui_ge;
    @BindView(R.id.tv_commentTotal)
    TextView tv_commentTotal;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.iv_shoucang)
    ImageView iv_shoucang;
    @BindView(R.id.banner)
    SquareBannerLayout mBanner;
    @BindView(R.id.jnsw)
    JudgeNestedScrollView jnsw;
    @BindView(R.id.rl_pin_lun)
    RecyclerView mRlPinLun;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_sales)
    TextView tvSales;
    @BindView(R.id.tv_sp_youhuiq)
    TextView tv_sp_youhuiq;
    @BindView(R.id.ll_evalute)
    LinearLayout llEvalute;
    @BindView(R.id.ll_sp_youhuiq)
    LinearLayout ll_sp_youhuiq;
    @BindView(R.id.ll_gg)
    LinearLayout llGg;
    @BindView(R.id.web)
    WebView mWebView;
    private String id;
    private ShoppingPingJiaAdapter mShoppingPingJiaAdapter;
    List<ProductDetailsBean.SpecsLisBean> specsLis;//规格
    String imgUrl = "";
    private AddressData addressData;
    private List<ProductDetailsBean.SkuListBean> skuList;//规格
    int collectFlag = 0;
    public static void startActivity(Context mContext, String id) {
        Intent intent = new Intent(mContext, ShoppingDetailsActivity.class);
        intent.putExtra("id", id);
        mContext.startActivity(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_shopping_details;
    }

    @Override
    protected void initViews() {
        id = getIntent().getStringExtra("id");
        initTitle();
        getParameter();
    }

    private void initTitle() {
        tvTitle.setText("商品详情");
        mIvBack.setImageDrawable(getDrawable(R.drawable.icon_back_h));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_shipin_fxd));
        initRv();
        getProductDetails(id);
        usable_not_obtained();
    }

    @Override
    protected void onResume() {
        super.onResume();
        shoppingCartGetNum();
    }

    private void initRv() {
        mShoppingPingJiaAdapter = new ShoppingPingJiaAdapter(null);
        mRlPinLun.setLayoutManager(new LinearLayoutManager(this));
        mRlPinLun.setHasFixedSize(true);
        mRlPinLun.setAdapter(mShoppingPingJiaAdapter);

        mShoppingPingJiaAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.ll_back,R.id.ll_sp_youhuiq, R.id.iv_shoucang,R.id.ll_gg, R.id.tv_gui_ge,R.id.tv_gwc,R.id.tv_kf,
            R.id.tv_address, R.id.ll_all_evalute, R.id.tv_ok,R.id.tv_ok_jrgwc, R.id.tv_ok_ljdh,R.id.iv_rightimg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_rightimg:
                HttpRequestUtils.shareDataText(this,
                        "https://api.5afit.com/html/productInfo.html?id="+id,imgUrl
                        ,tvName.getText().toString(),tvIntro.getText().toString());
                break;
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_kf:
                if(StringUtil.isBlank(ext1) || StringUtil.isBlank(paramValue)){
                    ToastUtils.showShort("客服信息获取失败");
                    return;
                }
                HomeFourFragment.showEditTextKefuKuang(this,ext1,paramValue);
                break;
            case R.id.ll_gg:
            case R.id.tv_gui_ge:
                if (specsLis != null && specsLis.size() > 0 && skuList != null && skuList.size() > 0) {
                    ShowChoicePackageDialog(false);
                } else {
                    ToastUtils.showShort("无规格");
                }
                break;
            case R.id.tv_address:
                AddressActivity.startActivity(ShoppingDetailsActivity.this, 2);
                break;
            case R.id.ll_all_evalute:
                IntentUtils.getInstence().intent(this, CommentListActivity.class, "id", id);
                break;
            case R.id.iv_shoucang:
                if(collectFlag==1){
                    getProductCollect(0);
                }else{
                    getProductCollect(1);
                }
                break;
            case R.id.ll_sp_youhuiq:
                popupWindw();
                break;
            case R.id.tv_ok_jrgwc:
                if (specsLis != null && specsLis.size() > 0 && skuList != null && skuList.size() > 0) {
                    ShowChoicePackageDialog(true);
                } else {
                    postShowOrderInfo(true);
                }
                break;
            case R.id.tv_gwc:
                IntentUtils.getInstence().intent(this,ShoppingGwcActivity.class);
                break;
            case R.id.tv_ok_ljdh:
            case R.id.tv_ok:
                if (specsLis != null && specsLis.size() > 0 && skuList != null && skuList.size() > 0) {
                    ShowChoicePackageDialog(false);
                } else {
                    postShowOrderInfo(false);
                }
                break;
        }
    }

    String paramValue="";
    String ext1="";
    private void getParameter() {
        RetrofitUtil.getInstance().apiService()
                .getParameter("customer.service")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ParameterBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ParameterBean> result) {
                        if (result.getCode()==0) {
                            try {
                                JSONObject mObj = new JSONObject(result.getData().getParamValue());
                                paramValue = mObj.getString("paramValue");
                                ext1 = mObj.getString("ext1");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                return;
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    PopupWindowYhq window;
    private void popupWindw() {
        window = new PopupWindowYhq(this, listYhqs, new PopupWindowYhq.GiveDialogInterface() {
            @Override
            public void btnConfirm(int topicId) {
                if(topicId==-1){
                    List<Integer> lists = new ArrayList<>();
                    for (int i = 0; i < listYhqs.size(); i++) {
                        lists.add(listYhqs.get(i).getId());
                    }
                    getPrizeReceives(lists);
                }else{
                    getPrizeReceive(topicId);
                }
            }
        });
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.setOutsideTouchable(true);
        window.showAtLocation(tvName, Gravity.BOTTOM,  0, 0);
    }

    private void getProductCollect(int collectFlag) {
        RetrofitUtil.getInstance().apiService()
                .getProductCollect(collectFlag,id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            if(collectFlag==1){
                                ToastUtils.showShort("收藏成功");
                            }else{
                                ToastUtils.showShort("取消收藏");
                            }
                            getProductDetails(id);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void shoppingCartGetNum() {
        RetrofitUtil.getInstance().apiService()
                .shoppingCartGetNum()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<Integer> result) {
                        if(isDataInfoSucceed(result)){
                            view.setVisibility(View.VISIBLE);
                            if(result.getData()==0){
                                view.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getPrizeReceive(int id) {
        RetrofitUtil.getInstance().apiService()
                .getPrizeReceive(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            ToastUtils.showShort("领取成功");
                            usable_not_obtained();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getPrizeReceives(List<Integer> lists) {
        RetrofitUtil.getInstance().apiService()
                .getPrizeReceives(lists)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            ToastUtils.showShort("领取成功");
                            usable_not_obtained();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20) {
            if (resultCode == 1) {
                addressData = (AddressData) data.getSerializableExtra("address");
                if (addressData == null) {
                    mTvAddress.setText("请选择收货地址");
                } else {
                    mTvAddress.setText(addressData.getRegions() + addressData.getLocation());
                }
            }
        }

    }

    private void postShowOrderInfo(boolean isGwc) {
        if(isGwc){
            PostOrderGwcInfo mPostOrderGwcInfo = new PostOrderGwcInfo();
            mPostOrderGwcInfo.setSkuId(skuId);
            mPostOrderGwcInfo.setProductId(id);
            mPostOrderGwcInfo.setNum("1");
            mPostOrderGwcInfo.setUserId(SharedUtils.getUserId() + "");
            shoppingCartAdd(mPostOrderGwcInfo);
            return;
        }
        PostOrderInfo info = new PostOrderInfo();
        info.setOrderType("2");
        if (addressData != null) {
            info.setAddressId(addressData.getId());
        }
        List<PostOrderInfo.EntityListBean> entityList = new ArrayList<>();
        PostOrderInfo.EntityListBean entityListBean = new PostOrderInfo.EntityListBean();
        entityListBean.setSkuId(skuId);
        entityListBean.setProductId(id);
        entityListBean.setQuantity("1");
        entityList.add(entityListBean);
        info.setEntityList(entityList);
        info.setUserId(SharedUtils.getUserId() + "");
        Log.w("info:", "info:" + info.toString());

        Intent intent = new Intent(this, OrderAffirmActivity.class);
        intent.putExtra("info", info);
        startActivity(intent);
    }
    private void shoppingCartAdd(PostOrderGwcInfo mPostOrderGwcInfo) {
        RetrofitUtil.getInstance().apiService()
                .shoppingCartAdd(mPostOrderGwcInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            ToastUtils.showShort("已加入购物车");
                            shoppingCartGetNum();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    String skuId = null;
    DialogChoicePackage choicePackageDialog;
    boolean isGwcLs = false;
    private void ShowChoicePackageDialog(boolean isGwc) {
        isGwcLs = isGwc;
        if (choicePackageDialog != null) {
            choicePackageDialog.showDialog();
            return;
        }
        choicePackageDialog = new DialogChoicePackage(
                ShoppingDetailsActivity.this, specsLis, skuList,
                new DialogChoicePackage.OnChoicePackageDialogListener() {
                    @Override
                    public void addListener(int pos, String text) {
                        skuId = skuList.get(pos).getId();
                        tv_gui_ge.setText(skuList.get(pos).getSpecText());
                        tvPrice.setText(skuList.get(pos).getDeductIntegral());
                        if (Double.valueOf(skuList.get(pos).getPrice()) != 0) {
                            String str = "+ <font color=\"#FFB300\">¥ </font>" + skuList.get(pos).getPrice();
                            tvSales.setText(Html.fromHtml(str));
                        } else {
                            tvSales.setText("");
                        }
                    }

                    @Override
                    public void btn_Ok() {
                        postShowOrderInfo(isGwcLs);
                    }
                });
        choicePackageDialog.showDialog();
    }


    private void getProductDetails(String id) {
        RetrofitUtil.getInstance().apiService()
                .getProductDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductDetailsBean> result) {
                        if (isDataInfoSucceed(result)) {
                            initViewUi(result.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    private void initViewUi(ProductDetailsBean detailsBean) {
        skuList = detailsBean.getSkuList();
        initBanner(detailsBean.getImgs());
        initWebView(detailsBean.getDetails());
        imgUrl = detailsBean.getImgUrl();
        specsLis = detailsBean.getSpecsLis();
        iv_shoucang.setImageResource(R.drawable.ic_shopping_no);
        collectFlag = detailsBean.getCollectFlag();
        if(collectFlag==1){
            iv_shoucang.setImageResource(R.drawable.ic_shopping);
        }
        tvPrice.setText(detailsBean.getDeductIntegral());
        if (Double.valueOf(detailsBean.getPrice()) != 0) {
            String str = "+ <font color=\"#FFB300\">¥ </font>" + detailsBean.getPrice();
            tvSales.setText(Html.fromHtml(str));
        } else {
            tvSales.setText("");
        }
        if (skuList != null && skuList.size() > 0) {
            skuId = skuList.get(0).getId();
            llGg.setVisibility(View.VISIBLE);
            tv_gui_ge.setText(skuList.get(0).getSpecText());
//            tvPrice.setText(skuList.get(0).getDeductIntegral());
            if (Double.valueOf(skuList.get(0).getPrice()) != 0) {
                String str = "+ <font color=\"#FFB300\">¥ </font>" + skuList.get(0).getPrice();
//                tvSales.setText(Html.fromHtml(str));
            } else {
//                tvSales.setText("");
            }
        }
        tvName.setText(detailsBean.getName());
        tvIntro.setText(detailsBean.getSubTitle());
        tv_commentTotal.setText("宝贝评价(" + detailsBean.getCommentTotal() + "）");
        mShoppingPingJiaAdapter.setNewData(detailsBean.getCommentList());
    }

    private void initWebView(String details) {
        WebSettings webSettings = mWebView.getSettings();//获取webview设置属性
        webSettings.setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        webSettings.setBlockNetworkImage(false); // 解决图片不显示
        //支持javascript
//        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.loadData(
                StringUtil.getNewContent1(details), "text/html; charset=UTF-8", null);//这种写法可以正确解码
    }


    private void initBanner(String imgs) {
        String[] strArr = imgs.split(",");
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Log.i("tag", "你点了第" + position + "张轮播图:" + titles.get(position));
            }
        });

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(Arrays.asList(strArr));
        //设置banner动画效果
//        mTopBanner.setBannerAnimation(Transformer.Stack);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    List<PrizeListData.ListBean> listYhqs = new ArrayList<>();
    private void usable_not_obtained() {
        RetrofitUtil.getInstance().apiService()
                .usable_not_obtained(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<PrizeListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<PrizeListData> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData() != null && result.getData().getList() != null && result.getData().getList().size() > 0) {
                                ll_sp_youhuiq.setVisibility(View.VISIBLE);
                                listYhqs.clear();
                                listYhqs.addAll(result.getData().getList());
                                if(window!=null && window.isShowing()){
                                    window.setNotifyDataSetChanged(listYhqs);
                                }
                            }else{
                                ll_sp_youhuiq.setVisibility(View.GONE);
                                if(window!=null && window.isShowing()){
                                    window.dismiss();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
