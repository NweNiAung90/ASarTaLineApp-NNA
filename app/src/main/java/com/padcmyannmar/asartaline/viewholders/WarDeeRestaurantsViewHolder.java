package com.padcmyannmar.asartaline.viewholders;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.padcmyannmar.asartaline.R;
import com.padcmyannmar.asartaline.data.vos.WarDeeVO;
import com.padcmyannmar.asartaline.delegates.WarDeeDelegate;
import com.padcmyannmar.asartaline.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WarDeeRestaurantsViewHolder extends RecyclerView.ViewHolder {
    //restaurant
    @BindView(R.id.iv_restaurant)
    public ImageView ivRestaurant;
    //name
    @BindView(R.id.tv_name)
    public TextView tvName;
    //taste
    @BindView(R.id.tv_taste)
    public TextView tvTaste;
    //price range
    @BindView(R.id.tv_price_range)
    public TextView tvPriceRange;
    private WarDeeDelegate mWarDeeDelegate;
    private WarDeeVO mWarDee;

    /* relay to activity */
    public WarDeeRestaurantsViewHolder(View itemView, WarDeeDelegate warDeeDelegate) {
        super(itemView);

        //Bind with ButterKnife
        ButterKnife.bind(this, itemView);

        mWarDeeDelegate = warDeeDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add param to know user tap which itemView
                mWarDeeDelegate.onTapItemView(mWarDee);

            }

        });
    }

    @SuppressLint("StringFormatInvalid")
    public void bindData(WarDeeVO warDee) {
        this.mWarDee = warDee;

        if (!warDee.getImages().isEmpty()) {
            //get image from network
            GlideApp.with(ivRestaurant.getContext())
                    .load(warDee.getImages().get(0))
                    .placeholder(R.drawable.img_placeholder)
                    .error(R.drawable.img_errorstop)
                    .into(ivRestaurant);
        } else {
            //hide null image place
            ivRestaurant.setVisibility(View.GONE);
        }

        tvName.setText(warDee.getName());

        tvTaste.setText(mWarDee.getGeneralTastes().get(0).getTaste());

        tvPriceRange.setText(tvPriceRange.getContext()
               .getResources().getString(R.string.format_price_range, warDee.getPriceRangeMin(), warDee.getPriceRangeMax()));

    }
}