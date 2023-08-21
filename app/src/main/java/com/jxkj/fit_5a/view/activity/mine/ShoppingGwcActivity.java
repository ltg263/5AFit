package com.jxkj.fit_5a.view.activity.mine;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ShoppingCartListBean;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.PostOrderGwcInfo;
import com.jxkj.fit_5a.entity.PostOrderInfo;
import com.jxkj.fit_5a.view.activity.mine.order.OrderAffirmActivity;
import com.jxkj.fit_5a.view.adapter.ShopCarGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingGwcActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rv_shop_list)
    RecyclerView mRvShopList;
    @BindView(R.id.rl_not_shop)
    RelativeLayout mRlNotShop;
    @BindView(R.id.ll1)
    LinearLayout mLl1;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.iv_all)
    ImageView mIvAll;
    private ShopCarGoodsAdapter mShopCarGoodsAdapter;
    List<String> checkedSkuIdList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_shopping_gwc;
    }

    @Override
    protected void initViews() {
        tvTitle.setText("购物车");
        mIvBack.setImageDrawable(getDrawable(R.drawable.icon_back_h));
        mShopCarGoodsAdapter = new ShopCarGoodsAdapter(null);
        mRvShopList.setAdapter(mShopCarGoodsAdapter);
        mShopCarGoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingCartListBean.ListBean mItemListBean = mShopCarGoodsAdapter.getData().get(position);
                mItemListBean.setSelected(!mItemListBean.isSelected());
                if(mItemListBean.isSelected()){
                    mIvAll.setSelected(true);
                }
                onItemChildClick_ivAllSele(false);
            }
        });
        show();
        shoppingCartList();
    }

    public void onItemChildClick_ivAllSele(boolean isSelected) {
        checkedSkuIdList.clear();
        double toPrice = 0;
        double toJf = 0;
        for(int i=0;i<mShopCarGoodsAdapter.getData().size();i++){
            ShoppingCartListBean.ListBean mSkuDTO = mShopCarGoodsAdapter.getData().get(i);

            if(isSelected){
                mShopCarGoodsAdapter.getData().get(i).setSelected(true);
            }
            if(!mShopCarGoodsAdapter.getData().get(i).isSelected()){
                mIvAll.setSelected(false);
            }else{
                checkedSkuIdList.add(mShopCarGoodsAdapter.getData().get(i).getId());
                toPrice += Double.parseDouble(mShopCarGoodsAdapter.getData().get(i).getNum())
                        *Double.parseDouble(mSkuDTO.getSalePrice());
                toJf += Double.parseDouble(mShopCarGoodsAdapter.getData().get(i).getNum())
                        *Double.parseDouble(mSkuDTO.getSaleIntegral());

            }
        }
        mTvPrice.setText(StringUtil.getValue(toJf)+" + ￥"+StringUtil.getValue(toPrice));
        if(toPrice==0){
            mTvPrice.setText(StringUtil.getValue(toJf));
        }
        mShopCarGoodsAdapter.notifyDataSetChanged();
    }


    public void shoppingCartList() {
        RetrofitUtil.getInstance().apiService()
                .shoppingCartList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ShoppingCartListBean>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ShoppingCartListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData() != null && result.getData().getList() != null && result.getData().getList().size() > 0) {
                                mRlNotShop.setVisibility(View.GONE);
                                mRvShopList.setVisibility(View.VISIBLE);
                                mLl1.setVisibility(View.VISIBLE);
                                List<ShoppingCartListBean.ListBean> lists = result.getData().getList();
                                double toPrice = 0,toJf=0;
                                for(int i=0;i<lists.size();i++){
                                    lists.get(i).setSelected(true);
                                    ShoppingCartListBean.ListBean mSkuDTO =lists.get(i);
                                    if(mSkuDTO!=null){
                                        toPrice += Double.parseDouble(lists.get(i).getNum())
                                                *Double.parseDouble(mSkuDTO.getSalePrice());
                                        toJf += Double.parseDouble(lists.get(i).getNum())
                                                *Double.parseDouble(mSkuDTO.getSaleIntegral());
                                    }
                                    checkedSkuIdList.add(lists.get(i).getId());
                                }
                                mIvAll.setSelected(true);
                                mShopCarGoodsAdapter.setNewData(result.getData().getList());
                                mTvPrice.setText(StringUtil.getValue(toJf)+" + ￥"+StringUtil.getValue(toPrice));
                                if(toPrice==0){
                                    mTvPrice.setText(StringUtil.getValue(toJf));
                                }
                            }else{
                                mRlNotShop.setVisibility(View.VISIBLE);
                                mLl1.setVisibility(View.GONE);
                                mRvShopList.setVisibility(View.GONE);
                                mTvLogin.setText("去购买");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                        Log.w("onError:","解析错误："+e);
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }

    @OnClick({R.id.iv_back,R.id.iv_all, R.id.tv_all, R.id.tv_login, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_all:
            case R.id.tv_all:
                if(!mIvAll.isSelected()){
                    mIvAll.setSelected(true);
                    onItemChildClick_ivAllSele(true);
                }
                break;
            case R.id.tv_commit:
                if(checkedSkuIdList.size()==0){
                    ToastUtils.showShortToast(this,"无选中商品");
                    return;
                }
                postShowOrderInfo();
                break;

        }
    }
    private void postShowOrderInfo() {
        PostOrderInfo info = new PostOrderInfo();
        info.setOrderType("2");
        List<PostOrderInfo.EntityListBean> entityList = new ArrayList<>();
        for(int i = 0;i<checkedSkuIdList.size();i++){
            PostOrderInfo.EntityListBean entityListBean = new PostOrderInfo.EntityListBean();
            entityListBean.setCartId(checkedSkuIdList.get(i));
            entityList.add(entityListBean);
        }
        info.setEntityList(entityList);
        info.setUserId(SharedUtils.getUserId() + "");
        Log.w("info:", "info:" + info.toString());
        Intent intent = new Intent(this, OrderAffirmActivity.class);
        intent.putExtra("info",info);
        startActivity(intent);
    }

}



