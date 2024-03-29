package com.jxkj.fit_5a.view.search;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.view.activity.mine.JiaoXueSpSearchActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingSearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索界面
 * 编号：1200
 */
public class SearchGoodsActivity extends BaseActivity {


    @BindView(R.id.tv_top_title)
    EditText searchEt;
    @BindView(R.id.activity_search_goods_search_tv)
    TextView mSearchTv;
    @BindView(R.id.rl_actionbar)
    RelativeLayout llActionbar;
    @BindView(R.id.flowlayout)
    ShoppingFlowLayout flowLayout;
    @BindView(R.id.flowlayout_rm)
    ShoppingFlowLayout flowLayoutRm;
    @BindView(R.id.activity_search_goods_history_ll)
    LinearLayout mHistoryLl;
    private String searchStr = null;
    private String searchType = SearchResultGoodsActivity.tabListBlq[0];
    public static SearchGoodsActivity activity;
    @Override
    protected int getContentView() {
        return R.layout.activity_search_goods;
    }
    @Override
    protected void onResume() {
        super.onResume();
        setHistorySearchData();
        if(searchStr!=null){
            searchEt.setSelection(searchStr.length());//将光标移至文字末尾
            getWindow().setSoftInputMode(WindowManager.LayoutParams
                    .SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    protected void initViews() {
        searchType = getIntent().getStringExtra("searchType");
        if(searchType.equals(SearchResultGoodsActivity.tabListBlq[0])){
            searchEt.setHint("搜索动态");
        }else if(searchType.equals(SearchResultGoodsActivity.tabListBlq[1])){
            searchEt.setHint("搜索用户");
        }else if(searchType.equals(SearchResultGoodsActivity.tabListBlq[2])){
            searchEt.setHint("搜索话题");
        }else if(searchType.equals(SearchResultGoodsActivity.tabListBlq[3])){
            searchEt.setHint("搜索圈子");
        }else if(searchType.equals(SearchResultGoodsActivity.tabListBlq[4])){
            searchEt.setHint("搜索教学");
        }else if(searchType.equals(SearchResultGoodsActivity.tabListBlq[5])){
            searchEt.setHint("搜索商品");
        }else if(searchType.equals(SearchResultGoodsActivity.tabListBlq[6])){
            searchEt.setHint("搜索收藏商品");
        }
        activity = this;
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        setHistorySearchData();
        setHistorySearchDataRm();
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(searchEt.getText().toString().trim().length()>0){
                        searchStr = searchEt.getText().toString().trim();
                        startSearchResultActivity(searchEt.getText().toString().trim());
                    }else {
                        Toast.makeText(SearchGoodsActivity.this,"请输入搜索内容",Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void setHistorySearchData() {
        List<String> mHistoryList = SearchHistorySpUtil.getSearchHistory(SearchGoodsActivity.this,"goods","goodsName");
        //往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        if (flowLayout != null) {
            flowLayout.removeAllViews();
        }
        for (int i = 0; i < mHistoryList.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(19, 5, 19, 5);
            tv.setText(mHistoryList.get(i));
            tv.setMaxEms(10);
            tv.setSingleLine();
                    tv.setBackgroundResource(R.drawable.shap_f5f5f5_15);
            tv.setLayoutParams(layoutParams);
            final String name = mHistoryList.get(i);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchStr = name;
                    searchEt.setText(name);
                    startSearchResultActivity(searchEt.getText().toString().trim());

                }
            });
            flowLayout.addView(tv, layoutParams);
        }
    }
    private void setHistorySearchDataRm() {
        List<String> list = new ArrayList<>();
        list.add("123456");
        list.add("1234");
        list.add("123455456");
        list.add("12asdfads6");
        //往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        if (flowLayoutRm != null) {
            flowLayoutRm.removeAllViews();
        }
        for (int i = 0; i < list.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(19, 5, 19, 5);
            tv.setText(list.get(i));
            tv.setMaxEms(10);
            tv.setSingleLine();
            tv.setBackgroundResource(R.drawable.shap_f5f5f5_15);
            tv.setLayoutParams(layoutParams);
            final String name = list.get(i);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchStr = name;
                    searchEt.setText(name);
                    startSearchResultActivity(searchEt.getText().toString().trim());
                }
            });
            flowLayoutRm.addView(tv, layoutParams);
        }
    }

    @OnClick({R.id.ll_back, R.id.activity_search_goods_search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.activity_search_goods_search_tv:
                if(searchEt.getText().toString().trim().length()>0){
                    startSearchResultActivity(searchEt.getText().toString().trim());
                }else {
                    Toast.makeText(SearchGoodsActivity.this,"请输入搜索内容",Toast.LENGTH_LONG).show();

                }
                break;
                default:
        }
    }

    private void startSearchResultActivity(String inputText){
        SearchHistorySpUtil.saveSearchHistory(SearchGoodsActivity.this, "goods", "goodsName", inputText);
        if(searchType.equals(SearchResultGoodsActivity.tabListBlq[6])){
            IntentUtils.getInstence().intent(SearchGoodsActivity.this, ShoppingSearchActivity.class,
                    "type","sc","search", inputText);
        }else if(searchType.equals(SearchResultGoodsActivity.tabListBlq[5])){
            IntentUtils.getInstence().intent(SearchGoodsActivity.this, ShoppingSearchActivity.class,
                    "search", inputText);
        }else if(searchType.equals(SearchResultGoodsActivity.tabListBlq[4])){
            IntentUtils.getInstence().intent(SearchGoodsActivity.this, JiaoXueSpSearchActivity.class,
                    "search", inputText);
        }else{
            IntentUtils.getInstence().intent(SearchGoodsActivity.this, SearchResultGoodsActivity.class,
                    "search", inputText, "searchType", searchType);
        }
    }
}
