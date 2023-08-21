package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.CustomPopWindow;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.WalletDetailsBean;
import com.jxkj.fit_5a.entity.WalletListBean;
import com.jxkj.fit_5a.view.activity.association.AssociationAddActivity;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;
import com.jxkj.fit_5a.view.adapter.MineJinDouAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineJinDouActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_jifen_num)
    TextView mTvJifenNum;
    @BindView(R.id.tv_logPay)
    TextView tv_logPay;
    @BindView(R.id.tv_not)
    TextView tv_not;
    @BindView(R.id.tv_time)
    TextView tv_time;
    private MineJinDouAdapter mMineJinDouAdapter;
    private CustomPopWindow distancePopWindow;
    String beginCreateTime,endCreateTime;
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_jindou;
    }

    @Override
    protected void initViews() {
        initRv();
    }
    private void initRv() {
        mTvJifenNum.setText(SharedUtils.singleton().get(ConstValues.MY_BALANCE,""));
        tv_time.setText(StringUtil.getTimeToYMD(System.currentTimeMillis(),"yyyy.MM"));
        mMineJinDouAdapter = new MineJinDouAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMineJinDouAdapter);

        mMineJinDouAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        tv_time.setText(StringUtil.getTimeToYMD(System.currentTimeMillis(),"yyyy.MM"));
        String ym = getLastMonth(0);
        beginCreateTime = ym.replace(".","-")+"-01 00:00:00";
        int day = getDaysByYearMonth(ym.substring(0,ym.indexOf(".")),ym.substring(ym.indexOf(".")+1));
        endCreateTime = ym.replace(".","-")+"-"+day+" 00:00:00";
        inOrOut = 2;
        getWalletList();
    }

    @OnClick({R.id.iv_back, R.id.tv_right_text,R.id.tv_cz,R.id.tv_logPay,R.id.iv_1,R.id.iv_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right_text:
                WebViewActivity.startActivityIntent(this, ConstValues.USER_JDGZ_URL,"金豆规则");
                break;
            case R.id.iv_1:
                String ym = getLastMonth(-1);
                beginCreateTime = ym.replace(".","-")+"-01 00:00:00";
                int day = getDaysByYearMonth(ym.substring(0,ym.indexOf(".")),ym.substring(ym.indexOf(".")+1));
                endCreateTime = ym.replace(".","-")+"-"+day+" 00:00:00";
                tv_time.setText(ym);
                getWalletList();
                break;
            case R.id.iv_2:
                String ym1 = getLastMonth(1);
                beginCreateTime = ym1.replace(".","-")+"-01 00:00:00";
                int day1 = getDaysByYearMonth(ym1.substring(0,ym1.indexOf(".")),ym1.substring(ym1.indexOf(".")+1));
                endCreateTime = ym1.replace(".","-")+"-"+day1+" 00:00:00";
                tv_time.setText(ym1);
                getWalletList();
                break;
            case R.id.tv_logPay:
                if(distancePopWindow!=null){
                    distancePopWindow.showAsDropDown(tv_logPay, -100, 0);
                    return;
                }
                initPopupWindow();
                break;
            case R.id.tv_cz:
                startActivity(new Intent(this,MineJinDouCzActivity.class));
                break;
        }
    }
    public String getLastMonth(int x) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
        Date date = null;
        try {
            date = format.parse(tv_time.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + x); // 设置为上一个月
        return format.format(calendar.getTime());
    }

    private int getDaysByYearMonth(String year, String month){
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, Integer.valueOf(year));
        a.set(Calendar.MONTH, Integer.valueOf(month) - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        System.out.println(maxDate);
        return maxDate;
    }
    private void initPopupWindow() {
        View view = getLayoutInflater().inflate(R.layout.popup_window_cqxc, null, false);
        TextView tv_tp= view.findViewById(R.id.tv_tp);
        TextView tv_sp= view.findViewById(R.id.tv_sp);
        tv_tp.setText("支出记录");
        tv_sp.setText("收入记录");
        tv_tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_logPay.setText("支出记录");
                inOrOut = 2;
                getWalletList();
                distancePopWindow.dissmiss();

            }
        });
        tv_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_logPay.setText("收入记录");
                inOrOut = 1;
                getWalletList();
                distancePopWindow.dissmiss();
            }
        });
        //创建并显示popWindow
        distancePopWindow = new CustomPopWindow.PopupWindowBuilder(MineJinDouActivity.this)
                .setView(view)
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
//                        rbDistance.setChecked(false);
                    }
                })
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()//创建PopupWindow
                .showAsDropDown(tv_logPay, -100, 0);//显示PopupWindow
    }
    int inOrOut;
    private void getWalletList() {
        tv_not.setVisibility(View.VISIBLE);
        mRvList.setVisibility(View.GONE);
        RetrofitUtil.getInstance().apiService()
                .getWalletList(beginCreateTime,endCreateTime,inOrOut,1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<WalletListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<WalletListBean> result) {
                        if(isDataInfoSucceed(result)){
                            mMineJinDouAdapter.setNewData(result.getData().getList());
                            if(mMineJinDouAdapter.getData().size()>0){
                                tv_not.setVisibility(View.GONE);
                                mRvList.setVisibility(View.VISIBLE);
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
