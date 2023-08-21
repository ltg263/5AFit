package com.jxkj.fit_5a.view.activity.exercise;


import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.entity.CircleTaskData;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MotorPatternActivity;
import com.jxkj.fit_5a.view.adapter.HomeTwoTaskSelect;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TaskSelectionActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private HomeTwoTaskSelect mHomeTwoTaskSelect;
    private String exercise_type;


    @Override
    protected int getContentView() {
        return R.layout.activity_task_selection;
    }

    @Override
    protected void initViews() {
        exercise_type = getIntent().getStringExtra("exercise_type");
        mHomeTwoTaskSelect = new HomeTwoTaskSelect(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeTwoTaskSelect);
        mHomeTwoTaskSelect.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CircleTaskData> data = mHomeTwoTaskSelect.getData();
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setSelect(false);
                }
                data.get(position).setSelect(true);
                mHomeTwoTaskSelect.notifyDataSetChanged();
            }
        });

        getCircleTaskList();
    }

    @OnClick({R.id.iv_back, R.id.tv_tiao_guo, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_tiao_guo:
            case R.id.tv_ok:
                if(exercise_type.equals("在线运动")){
//                    MotorPatternActivity.startIntentActivity(this);
                }else {
                    startActivity(new Intent(this, TaskStartActivity.class));
                }
                break;
        }
    }

    private void getCircleTaskList() {
        RetrofitUtil.getInstance().apiService()
                .getCircleTaskList(ConstValues_Ly.DEVICE_TYPE_ID_URL,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<CircleTaskData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<CircleTaskData> result) {
                        if(isDataInfoSucceed(result)){
                            mHomeTwoTaskSelect.setNewData(result.getData());
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
