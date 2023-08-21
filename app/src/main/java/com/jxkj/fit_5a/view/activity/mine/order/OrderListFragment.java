package com.jxkj.fit_5a.view.activity.mine.order;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.view.adapter.OrderListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderListFragment extends BaseFragment {
    @BindView(R.id.rv_shopping_cart)
    RecyclerView rvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout lv_not;

    private Bundle bundle;
    private int totalPage;

    @Override
    protected int getContentView() {
        return R.layout.fragment_order_list;
    }

    private List<OrderInfoData.ListBean> orderDataList = new ArrayList<>();
    private int type = 0;
    OrderListAdapter orderListAdapter;
    public static boolean isRefresh = false;
    @Override
    public void onResume() {
        super.onResume();
        if(isRefresh){
            isRefresh = false;
            page = 1;
            orderDataList.clear();
            getData();
        }
    }

    @Override
    protected void initViews() {
        bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        initOrder();
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                orderDataList.clear();
                getData();
            }
        });
    }

    @Override
    public void initImmersionBar() {
        if(orderListAdapter.getData().size()==0){
            refreshData();
        }
    }

    private void initOrder() {

        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderListAdapter = new OrderListAdapter(orderDataList);
        rvList.setAdapter(orderListAdapter);
//        orderListAdapter.setMyOrderAdapterListener(new OrderListAdapter.MyOrderAdapterListener() {
//            @Override
//            public void setLxcj(int position) {
////                IntentUtils.getInstence().callPhone(getActivity(),orderDataList.get(position).getTel());
//            }
//
//            @Override
//            public void setQxdd(int position) {
//                getCancelOrder(orderDataList.get(position).getId()+"");
//            }
//
//            @Override
//            public void setQzf(int position) {
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("id", orderDataList.get(position).getId() + "");
//                bundle1.putString("price", orderDataList.get(position).getRealAmount() + "");
//                bundle1.putString("time", orderDataList.get(position).getExpireTime() + "");
////                IntentUtils.getInstence().intent(getActivity(), ShoppingPaymentActivity.class, "order", bundle1);
//            }
//
//            @Override
//            public void setQrsh(int position) {
//                postFinishOrder(orderDataList.get(position).getId()+"");
//            }
//
//            @Override
//            public void setQpj(int position) {
//                IntentUtils.getInstence().intent(getActivity(), MineOrderEvaluateGoodsActivity.class, "orderId", orderDataList.get(position).getId());
//            }
//
//            @Override
//            public void setZlyd(int position) {
//                getAgainOrder(orderDataList.get(position).getId()+"");
//            }
//
//            @Override
//            public void setSc(int position) {
//                DialogUtils.showDialogHint(getActivity(), "您确定要取消此订单吗？", false, new DialogUtils.ErrorDialogInterface() {
//                    @Override
//                    public void btnConfirm() {
//                        postDelete(orderDataList.get(position).getId()+"");
//                    }
//                });
//            }
//
//            @Override
//            public void setSqtk(int position) {
////                MineOrderSqtkGoodsActivity.startActivityIntent(getActivity(),orderDataList.get(position));
//            }
//        });
        orderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.getInstence().intent(getActivity(), OrderDetailsActivity.class, "id",
                        orderListAdapter.getData().get(position).getId());
            }
        });

//        getData();
    }

    private void getCancelOrder(String id) {
        RetrofitUtil.getInstance().apiService()
                .postCancelOrder(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            getData();
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

    private void getAgainOrder(String id) {
        RetrofitUtil.getInstance().apiService()
                .postExpediting(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("已通知卖家");
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

    private void postDelete(String id) {
        RetrofitUtil.getInstance().apiService()
                .postDelete(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("已通知卖家");
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

    private void postFinishOrder(String id) {
        RetrofitUtil.getInstance().apiService()
                .postFinishOrder(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {

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

    public void refreshData() {
        page = 1;
        orderDataList.clear();
        show(getActivity());
        getData();
    }

    private int page = 1;

    private void getData() {
        String status = null;
        if(type !=0){
            status = String.valueOf(type);
        }
        RetrofitUtil.getInstance().apiService()
                .getOrderAll(page, ConstValues.PAGE_SIZE, status)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<OrderInfoData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result<OrderInfoData> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData().getList().size()>0){
                                lv_not.setVisibility(View.GONE);
                                refreshLayout.setVisibility(View.VISIBLE);
                            }else if(page==1){
                                lv_not.setVisibility(View.VISIBLE);
                                refreshLayout.setVisibility(View.GONE);
                            }
                            orderDataList.addAll(result.getData().getList());
                            orderListAdapter.notifyDataSetChanged();
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadMore();
                            totalPage = StringUtil.getTotalPage(result.getData().getTotalCount(), ConstValues.PAGE_SIZE);
                            if(totalPage <= page){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }
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
