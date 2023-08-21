package com.jxkj.fit_5a.view.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ShoppingCartListBean;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.AddandView;
import com.jxkj.fit_5a.entity.PostOrderGwcInfo;
import com.jxkj.fit_5a.view.activity.mine.ShoppingGwcActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopCarGoodsAdapter extends BaseQuickAdapter<ShoppingCartListBean.ListBean, BaseViewHolder> {


    public ShopCarGoodsAdapter(List<ShoppingCartListBean.ListBean> data) {
        super(R.layout.item_shop_car_goodes, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingCartListBean.ListBean item) {
        if(item==null){
            return;
        }
        GlideImageUtils.setGlideImage(mContext,item.getImgUrl(),helper.getView(R.id.iv_img));
//        String str = "+ ￥<big><big>"+StringUtil.getValue(item.getSalePrice())+"</big></big></font>";
        helper.setText(R.id.tv_name,item.getProductName())
                .setVisible(R.id.iv_add,false).setVisible(R.id.tv_add,false).setVisible(R.id.add,true)
                .addOnClickListener(R.id.iv_select).setVisible(R.id.tv_type,true)
                .setText(R.id.tv_type,item.getSkuName())
                .setVisible(R.id.tv_spuSupplyType,false)
                .setText(R.id.tv_price,"+ ￥"+StringUtil.getValue(item.getSalePrice()))//Html.fromHtml(str)
                .setText(R.id.tv_jf,item.getSaleIntegral()).setVisible(R.id.tv_price,true);
        if(Double.parseDouble(item.getSalePrice())==0){
            helper.setVisible(R.id.tv_price,false);
        }
        AddandView mAddandView = helper.getView(R.id.add);
        ImageView mIvSelect = helper.getView(R.id.iv_select);
        mIvSelect.setSelected(item.isSelected()?true:false);
        mAddandView.setValue((int) Double.parseDouble(item.getNum()));

        if(StringUtil.isNotBlank(item.getSkuName())){
            helper.setText(R.id.tv_spuSupplyType, item.getSkuName()).setVisible(R.id.tv_spuSupplyType,true);
        }
//        if(item.getCartSpuDTO().getSpuSupplyType().equals("1")){
//            helper.setText(R.id.tv_spuSupplyType,"自营");
//        }else if(item.getCartSpuDTO().getSpuSupplyType().equals("2")){
//            helper.setText(R.id.tv_spuSupplyType,"供应商");
//        }

        mAddandView.setOnNumberChangedListener(new AddandView.OnNumberChangedListener() {
            @Override
            public void OnNumberChanged(int vs, Boolean isAdd) {
                Log.w("onFocusChange","isAdd："+isAdd);
                Log.w("onFocusChange","数量是："+vs);
                if(isAdd!=null){
                    if(isAdd){
                        vs++;
                    }else{
                        vs--;
                    }
                }
                PostOrderGwcInfo mPostOrderGwcInfo = new PostOrderGwcInfo();
                Log.w("onFocusChange","数量是："+vs);
                int finalVs = vs;
                if(finalVs==0 && isAdd!=null && !isAdd){
//                    String [] ids = {item.getId()};
                    List<Long> ids = new ArrayList<>();
                    ids.add(Long.parseLong(item.getId()));
//                    String id = Arrays.toString(ids);
//                    Log.w("ids","ids :"+id);
//                    mPostOrderGwcInfo.setIds(id);
                    shoppingChangeDelete(mContext,item.getId(), new ShoppingCartInterface() {
                        @Override
                        public void isResult(Boolean isResult, String num) {
                            if(isResult){
                                ((ShoppingGwcActivity)mContext).shoppingCartList();
                            }
                        }
                    });
                    return;
                }
                mPostOrderGwcInfo.setId(item.getId());
//                mPostOrderGwcInfo.setSkuId(item.getSkuId());
//                mPostOrderGwcInfo.setProductId(item.getProductId());
                mPostOrderGwcInfo.setNum(""+vs);
                mPostOrderGwcInfo.setUserId(SharedUtils.getUserId() + "");
                shoppingChangeQuantity(mContext,mPostOrderGwcInfo, new ShoppingCartInterface() {
                    @Override
                    public void isResult(Boolean isResult,String num) {
                        if(isResult){
//                                        ((MainActivity)mContext).updateUI();
//                                        if(mContext instanceof MainActivity){
//                                            ((MainActivity)mContext).setShopCarNum(num);
//                                        }
                            mAddandView.setShowValue(finalVs);
                            item.setNum(finalVs+"");
                            if(item.isSelected()){
                                ((ShoppingGwcActivity)mContext).onItemChildClick_ivAllSele(item.isSelected());
                            }
                        }
                    }
                });
                return;
//                if(isAdd){
//                    vs++;
//                    shoppingChangeQuantity(mContext, "1",item.getSkuId(),true, new ShoppingCartInterface() {
//                        @Override
//                        public void isResult(Boolean isResult,String num) {
//                            if(isResult){
////                                ((MainActivity)mContext).updateUI();
////                                if(mContext instanceof MainActivity){
////                                    ((MainActivity)mContext).setShopCarNum(num);
////                                }
//                                mAddandView.add();
//                            }
//                        }
//                    });
//                }else{
//                    vs--;
//                    shoppingChangeQuantity(mContext, "1",item.getSkuId(),false, new ShoppingCartInterface() {
//                        @Override
//                        public void isResult(Boolean isResult,String num) {
//                            if(isResult){
////                                ((MainActivity)mContext).updateUI();
////                                if(mContext instanceof MainActivity){
////                                    ((MainActivity)mContext).setShopCarNum(num);
////                                }
//                                mAddandView.jian();
//                            }
//                        }
//                    });
//                }
            }
        });
    }
    public static void shoppingChangeQuantity(Context mContext, PostOrderGwcInfo mPostOrderGwcInfo, ShoppingCartInterface mShoppingCartInterface) {
        ((BaseActivity)mContext).show();
        RetrofitUtil.getInstance().apiService()
                .shoppingChangeQuantity(mPostOrderGwcInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        ((BaseActivity)mContext).dismiss();
                        if(result.getCode()==0) {
                            if(mShoppingCartInterface!=null){
                                mShoppingCartInterface.isResult(true,"");
//                                getShopCarNum(mShoppingCartInterface);
                            }
                        }else{
                            if(mShoppingCartInterface!=null){
                                mShoppingCartInterface.isResult(false,"");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ((BaseActivity)mContext).dismiss();
                        Log.w("onError:","解析错误："+e);
                        if(mShoppingCartInterface!=null){
                            mShoppingCartInterface.isResult(false,"");
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
    public static void shoppingChangeDelete(Context mContext, String id, ShoppingCartInterface mShoppingCartInterface) {
        ((BaseActivity)mContext).show();
        RetrofitUtil.getInstance().apiService()
                .shoppingChangeDelete(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        ((BaseActivity)mContext).dismiss();
                        if(result.getCode()==0) {
                            if(mShoppingCartInterface!=null){
                                mShoppingCartInterface.isResult(true,"");
//                                getShopCarNum(mShoppingCartInterface);
                            }
                        }else{
                            if(mShoppingCartInterface!=null){
                                mShoppingCartInterface.isResult(false,"");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ((BaseActivity)mContext).dismiss();
                        Log.w("onError:","解析错误："+e);
                        if(mShoppingCartInterface!=null){
                            mShoppingCartInterface.isResult(false,"");
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public interface ShoppingCartInterface {
        /**
         * 确定
         */
        public void isResult(Boolean isResult,String num);
    }
}
