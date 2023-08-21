package com.jxkj.fit_5a.view.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.DeviceCourseData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.view.activity.exercise.CoursePatternActivity;
import com.jxkj.fit_5a.view.adapter.CourseSelectionAdapter;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CourseSelectionFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_introduct)
    TextView tv_introduct;
    CourseSelectionAdapter mCourseSelectionAdapter;
    @Override
    protected int getContentView() {
        return R.layout.fragment_course_selection;
    }

    @Override
    protected void initViews() {
        tv_introduct.setText(getArguments().getString("introduct"));
        initTabs();
        queryDeviceCourseList();
    }
    private void queryDeviceCourseList() {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceCourseList(null,getArguments().getString("type"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceCourseData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceCourseData> result) {
                        if(isDataInfoSucceed(result)){
                            mCourseSelectionAdapter.setNewData(result.getData().getList());
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

    private void initTabs() {

        mCourseSelectionAdapter = new CourseSelectionAdapter(null);

        mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mCourseSelectionAdapter);

        mCourseSelectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.getInstence().intent(getActivity(),CoursePatternActivity.class,"id",mCourseSelectionAdapter.getData().get(position).getId());
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
}
