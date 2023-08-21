package com.jxkj.fit_5a.conpoment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jxkj.fit_5a.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PileAvertView extends LinearLayout {

    @BindView(R.id.pile_view)
    PileView pileView;

    private Context context = null;
    public static final int VISIBLE_COUNT = 5;//默认显示个数

    public PileAvertView(Context context) {
        this(context, null);
        this.context = context;
    }

    public PileAvertView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_group_pile_avert, this);
        ButterKnife.bind(view);
    }

    public void setAvertImages(List<String> imageList) {
        setAvertImages(imageList,VISIBLE_COUNT);
    }

    //如果imageList>visiableCount,显示List最上面的几个
    public void setAvertImages(List<String> imageList, int visibleCount) {
        List<String> visibleList = null;
        if (imageList.size() > visibleCount) {
            visibleList = imageList.subList(imageList.size() - 1 - visibleCount, imageList.size() - 1);
        }
        pileView.removeAllViews();
        for (int i = 0; i < imageList.size(); i++) {
            RoundImageView image= (RoundImageView) LayoutInflater.from(context).inflate(R.layout.item_group_round_avert, pileView, false);
            Glide.with(context).load(imageList.get(i)).into(image);
            Log.w("-->>:","img:"+imageList.get(i));
            pileView.addView(image);
        }
    }

}
