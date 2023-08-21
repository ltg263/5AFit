package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.view.adapter.FacilityManageAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FacilityManageActivity extends BaseActivity {
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
    @BindView(R.id.rv_lsydsb_list)
    RecyclerView mRvLsydsbList;
    @BindView(R.id.rv_lsxlsb_list)
    RecyclerView mRvLsxlsbList;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
    private FacilityManageAdapter mFacilityManageAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_facility_manage;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("设备管理");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_add_right));
        mTvRighttext.setText("新增");

        queryUserDeviceList();
    }

    private void queryUserDeviceList() {
        RetrofitUtil.getInstance().apiService()
                .queryUserDeviceList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            initRvUi();
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

    private void initRvUi() {
//        List<String> list = new ArrayList<>();
//        list.add("");
//        list.add("");
//        list.add("");

        mFacilityManageAdapter = new FacilityManageAdapter(null);

        mRvLsydsbList.setLayoutManager(new LinearLayoutManager(this));
        mRvLsydsbList.setHasFixedSize(true);
        mRvLsydsbList.setAdapter(mFacilityManageAdapter);

        mRvLsxlsbList.setLayoutManager(new LinearLayoutManager(this));
        mRvLsxlsbList.setHasFixedSize(true);
        mRvLsxlsbList.setAdapter(mFacilityManageAdapter);
    }


    @OnClick({R.id.ll_back, R.id.iv_rightimg, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_rightimg:
                startActivity(new Intent(this, FacilityAddSbActivity.class));
                break;
            case R.id.tv_ok:
                break;
        }
    }
    public static void intentActivity(Context mContext){

        mContext.startActivity(new Intent(mContext, FacilityAddSbActivity.class));
//        mContext.startActivity(new Intent(mContext,FacilityManageActivity.class));
    }
}
