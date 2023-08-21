package com.jxkj.fit_5a.view.activity.home;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.SignLogData;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.base.UserInfoData;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;
import com.jxkj.fit_5a.view.adapter.HomeSignHdrwAdapter;
import com.jxkj.fit_5a.view.adapter.HomeSignRcrwAdapter;
import com.jxkj.fit_5a.view.adapter.HomeSignRlAdapter;
import com.jxkj.fit_5a.view.adapter.HomeSignTopAdapter;
import com.jxkj.fit_5a.view.adapter.MineRwzxAdapter;

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

public class TaskSignActivity extends BaseActivity {
    @BindView(R.id.tv_jifen_num)
    TextView mTvJifenNum;
    @BindView(R.id.iv_show_type)
    ImageView mIvShowType;
    @BindView(R.id.rv_rl_list)
    RecyclerView mRvRlList;
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.tv_go_sign)
    TextView mTvGoSign;
    @BindView(R.id.rv_rcrw_list)
    RecyclerView mRvRcrwList;
    @BindView(R.id.rv_hdrw_list)
    RecyclerView mRvHdrwList;
    @BindView(R.id.ll_rl)
    LinearLayout mLlRl;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_qiandao_tian)
    TextView tv_qiandao_tian;
    @BindView(R.id.tv_title)
    TextView tv_title;
    int type = 1;//周的形式
    HomeSignRlAdapter mHomeSignRlAdapter;
    HomeSignTopAdapter mHomeSignTopAdapter;
    @Override
    protected int getContentView() {
        return R.layout.activity_task_sign;
    }

    @Override
    protected void initViews() {
        mTvJifenNum.setText(SharedUtils.singleton().get(ConstValues.MY_INTEGRAL,""));
        Calendar cal = Calendar.getInstance();
        year = String.valueOf(cal.get(Calendar.YEAR));
        month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if(month.length()==1){
            month = "0"+month;
        }

        mHomeSignRlAdapter = new HomeSignRlAdapter(null);
        mRvRlList.setLayoutManager(new GridLayoutManager(this,7));
        mRvRlList.setHasFixedSize(true);
        mRvRlList.setAdapter(mHomeSignRlAdapter);
        mHomeSignRlAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        mHomeSignTopAdapter = new HomeSignTopAdapter(null);
        mRvTopList.setLayoutManager(new GridLayoutManager(this,7));
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeSignTopAdapter);

        mHomeSignTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
        mTvGoSign.setText("签到领积分");
        getUserSignLog();
        getUserTaskList(2);
        getUserTaskList(3);

    }

    private void getUserStatistic() {
        RetrofitUtil.getInstance().apiService()
                .getUserStatistic()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserInfoData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserInfoData> result) {
                        if (isDataInfoSucceed(result)) {
                            UserInfoData data = result.getData();
                            if(data!=null){
                                mTvJifenNum.setText(data.getIntegral());
                                SharedUtils.singleton().put(ConstValues.MY_BALANCE,data.getBalance()+"");
                                SharedUtils.singleton().put(ConstValues.MY_COUPON_COUNT,data.getCouponCount()+"");
                                SharedUtils.singleton().put(ConstValues.MY_GIFTCOUNT,data.getGiftCount()+"");
                                SharedUtils.singleton().put(ConstValues.MY_INTEGRAL,data.getIntegral()+"");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void getUserTaskList(int i) {
        RetrofitUtil.getInstance().apiService()
                .getUserTaskList(i)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<TaskListBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TaskListBase> result) {
                        if(isDataInfoSucceed(result)){
                            if(i == 3){
                                if(result.getData()!=null && result.getData().getList()!=null && result.getData().getList().size()>0){
                                    tv_title.setText("连续签到15天，有惊喜！");
                                    String str = "已连续签到：<font color=\"#E84039\"><big><big>"
                                            +(int)result.getData().getList().get(0).getCurCount()+"</big></big></font>" +" 天";
                                    tv_qiandao_tian.setText(Html.fromHtml(str));
                                }
                                return;
                            }
                            if(i == 2){
                                getUserTaskList(4);
//                                HomeSignRcrwAdapter mHomeSignRcrwAdapter = new HomeSignRcrwAdapter(result.getData().getList());
//                                mRvRcrwList.setLayoutManager(new LinearLayoutManager(TaskSignActivity.this));
//                                mRvRcrwList.setHasFixedSize(true);
//                                mRvRcrwList.setAdapter(mHomeSignRcrwAdapter);
//
//                                mHomeSignRcrwAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//                                    @Override
//                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                                        MainActivity.isRenWu = true;
//                                        IntentUtils.getInstence().intent(TaskSignActivity.this,MainActivity.class);
//                                    }
//                                });
                                MineRwzxAdapter mMineRwzxAdapter = new MineRwzxAdapter(result.getData().getList());
                                mRvRcrwList.setLayoutManager(new LinearLayoutManager(TaskSignActivity.this));
                                mRvRcrwList.setHasFixedSize(true);
                                mRvRcrwList.setAdapter(mMineRwzxAdapter);

                                mMineRwzxAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        MainActivity.isRenWu = true;
                                        IntentUtils.getInstence().intent(TaskSignActivity.this,MainActivity.class);
                                    }
                                });

                            }else{
//                                HomeSignHdrwAdapter mHomeSignHdrwAdapter = new HomeSignHdrwAdapter(result.getData().getList());
//                                mRvHdrwList.setLayoutManager(new LinearLayoutManager(TaskSignActivity.this));
//                                mRvHdrwList.setHasFixedSize(true);
//                                mRvHdrwList.setAdapter(mHomeSignHdrwAdapter);
//
//                                mHomeSignHdrwAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//                                    @Override
//                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                                        MainActivity.isRenWu = true;
//                                        IntentUtils.getInstence().intent(TaskSignActivity.this,MainActivity.class);
//                                    }
//                                });

                                MineRwzxAdapter mMineRwzxAdapter = new MineRwzxAdapter(result.getData().getList());
                                mRvHdrwList.setLayoutManager(new LinearLayoutManager(TaskSignActivity.this));
                                mRvHdrwList.setHasFixedSize(true);
                                mRvHdrwList.setAdapter(mMineRwzxAdapter);

                                mMineRwzxAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        MainActivity.isRenWu = true;
                                        IntentUtils.getInstence().intent(TaskSignActivity.this,MainActivity.class);
                                    }
                                });
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

    String year;
    String month;
    int day=0;
    private void getUserSignLog() {
        mTvTime.setText(year+"."+month);
        //2020-11-01 01:00:00
        String beginCreateTime = year+"-"+month+"-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        try {
            day = StringUtil.getDaysOfMonth(sdf.parse(year+"-"+month));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String endCreateTime = year+"-"+month+"-"+day+" 00:00:00";
        RetrofitUtil.getInstance().apiService()
                .getUserSignLog(beginCreateTime,endCreateTime)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SignLogData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SignLogData> result) {
                        if(isDataInfoSucceed(result)){
                            initTop(result.getData().getList());
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
    private void initTop(List<SignLogData.ListBean> listData) {
        List<SignLogData.ListBean> listRl = new ArrayList<>();
        int pos = 0;//星期几开始
        try {
            pos = StringUtil.getPos(new SimpleDateFormat("yyyy-MM-dd").parse(year+"-"+month+"-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(int i=0;i<pos;i++){
            listRl.add(null);
        }
        for(int i=0;i<day;i++){
            SignLogData.ListBean listBean = new SignLogData.ListBean();
            for (int j= 0;j<listData.size();j++){
                String str = (i+1)+"";
                if((i+1)<10){
                    str = "0"+(i+1);
                }
                String singDate = year+month+str;
                if(Integer.valueOf(month)<10){
//                    singDate = year+month+str;
                }
                if((listData.get(j).getSignDate()).equals(singDate)){
                    listBean = listData.get(j);
                    listBean.setSig(true);
                }
                if((listData.get(j).getSignDate()).equals(StringUtil.getTimeToYMD(System.currentTimeMillis(),"yyyyMMdd"))){
                    mTvGoSign.setText("已签到");
                }
            }
            listBean.setSj(""+(i+1));
            listRl.add(listBean);
        }
        int num = 0;
        boolean isCurr = false;
        for(int i= listRl.size()-1;i>=0;i--){
            if(listRl.get(i)!=null && Integer.parseInt(StringUtil.getTimeToYMD(System.currentTimeMillis(),"dd"))==Integer.parseInt(listRl.get(i).getSj())){
                isCurr = true;
                if(listRl.get(i).isSig()){
                    num++;
                }
            }
            if(isCurr){
                if(listRl.get(i)==null || Integer.parseInt(StringUtil.getTimeToYMD(System.currentTimeMillis(),"dd"))!=Integer.parseInt(listRl.get(i).getSj())){
                    if(!listRl.get(i).isSig()){
                        isCurr = false;
                    }else{
                        num++;
                    }
                }
            }
        }
//        tv_title.setText("当前积分  |  连续签到"+num+"天");
        Calendar cal = Calendar.getInstance();
        mHomeSignRlAdapter.setSetCurrTime(false);
        if(cal.get(Calendar.YEAR)==Integer.valueOf(year) && cal.get(Calendar.MONTH)+1==Integer.valueOf(month)){
            mHomeSignRlAdapter.setSetCurrTime(true);
        }
        mHomeSignRlAdapter.setNewData(listRl);


        List<SignLogData.ListBean> listRl7 = new ArrayList<>();
        ArrayList<String> currentDays = StringUtil.getDayMonth7();
        for(int i=0;i<currentDays.size();i++){
            SignLogData.ListBean listBean = new SignLogData.ListBean();
            for (int j= 0;j<listData.size();j++){
                if(listData.get(j).getSignDate().equals(currentDays.get(i))){
                    listBean = listData.get(j);
                    listBean.setSig(true);
                }
            }

            listBean.setSj(currentDays.get(i).substring(6,8));
            listRl7.add(listBean);
        }

        if(cal.get(Calendar.YEAR)==Integer.valueOf(year) && cal.get(Calendar.MONTH)+1==Integer.valueOf(month)){
            mHomeSignTopAdapter.setNewData(listRl7);
        }
    }



    @OnClick({R.id.iv_back, R.id.iv_show_type, R.id.tv_go_sign,R.id.tv_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_show_type:
                if(type==1){
                    type = 2;
                    mLlRl.setVisibility(View.VISIBLE);
                    mRvRlList.setVisibility(View.VISIBLE);
                    mRvTopList.setVisibility(View.GONE);
                    mIvShowType.setImageDrawable(this.getResources().getDrawable(R.drawable.icon_sign_jgg1));
                }else{
                    type = 1;
                    mLlRl.setVisibility(View.GONE);
                    mRvRlList.setVisibility(View.GONE);
                    mRvTopList.setVisibility(View.VISIBLE);
                    mIvShowType.setImageDrawable(this.getResources().getDrawable(R.drawable.icon_sign_jgg));
                }
                break;
            case R.id.tv_go_sign:
                if(!mTvGoSign.getText().toString().equals("已签到")){
                    addUserSign();
                }
                break;
            case R.id.tv_time:
                PickerViewUtils.selectorDate(this,
                        new boolean[]{true,true,false,false,false,false}, new PickerViewUtils.GetTimeInterface() {
                    @Override
                    public void getTime(Date time) {
                        year = new SimpleDateFormat("yyyy").format(time);
                        month = new SimpleDateFormat("MM").format(time);
                        getUserSignLog();
                    }
                });
                break;
        }
    }
    private void addUserSign() {
        show();
        RetrofitUtil.getInstance().apiService()
                .addUserSign()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        dismiss();
                        if(isDataInfoSucceed(result)){
                            mTvGoSign.setText("已签到");
                            Calendar cal = Calendar.getInstance();
                            year = String.valueOf(cal.get(Calendar.YEAR));
                            month = String.valueOf(cal.get(Calendar.MONTH) + 1);
                            if(month.length()==1){
                                month = "0"+month;
                            }
                            getUserSignLog();
                            getUserStatistic();
                            getUserTaskList(3);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                        ToastUtils.showShort("系统异常"+e);
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }
}
//