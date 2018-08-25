package com.padcmyannmar.asartaline.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.padcmyannmar.asartaline.R;
import com.padcmyannmar.asartaline.data.models.WarDeeModel;
import com.padcmyannmar.asartaline.data.vos.WarDeeVO;
import com.padcmyannmar.asartaline.utils.WarDeeConstants;
import com.padcmyannmar.asartaline.viewpods.EmptyViewPod;

import org.mmtextview.MMFontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WarDeeDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_pasta)
    ImageView ivPasta;

    @BindView(R.id.tv_war_dee_name)
    TextView tvWarDeeName;

    @BindView(R.id.tv_price_tag)
    TextView tvPriceTag;

    @BindView(R.id.tv_war_dee_description)
    TextView tvWarDeeDescription;

    @BindView(R.id.btn_plus)
    Button btnPlus;

    @BindView(R.id.tv_count)
    TextView tvCount;

    @BindView(R.id.btn_minus)
    Button btn_Minus;

    /*@BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;*/

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_war_dee_details);

        MMFontUtils.initMMTextView(this);

        //Bind Obj to UI Component
        ButterKnife.bind(this,this);

        //Get warDeeId from from putExtra()
        String warDeeId = getIntent().getStringExtra(WarDeeConstants.WAR_DEE_ID);

        Log.d("WarDeeDetailsActivity", "warDeeId : " + warDeeId);

        //Retrieve ID
        WarDeeVO warDee = WarDeeModel.getObjInstance().getWarDeeById(warDeeId);
        //Check news before show Empty View List
        if (warDee != null) {
            bindData(warDee);
        } else {
           // vpEmpty.setVisibility(View.VISIBLE);
            //hide coordinatorLayout when data cann't show
            coordinatorLayout.setVisibility(View.GONE);
        }
        /*vpEmpty.setEmptyData("https://www.iconspng.com/images/sad-frog-feels-bad-man-meme/sad-frog-feels-bad-man-meme.jpg"
                , getString(R.string.empty_msg_news_details));*/

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 0 ){
                    count --;
                    tvCount.setText(count);
                }
            }
        });
        btn_Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvCount.setText(count);
            }
        });

    }


    @SuppressLint("StringFormatInvalid")
    private void bindData(WarDeeVO warDee){
        Glide.with(ivPasta.getContext())
                .load(warDee.getImages().get(0))
                .into(ivPasta);

        tvWarDeeName.setText(warDee.getName());

        tvWarDeeDescription.setText(warDee.getGeneralTastes().get(0).getTasteDesc());

        //Ks: for multi language
        tvPriceTag.setText(tvPriceTag.getContext()
                .getResources().getString(R.string.format_price,warDee.getPriceRangeMin()));


    }
}
