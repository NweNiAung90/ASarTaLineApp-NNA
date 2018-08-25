package com.padcmyannmar.asartaline.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyannmar.asartaline.R;
import com.padcmyannmar.asartaline.data.vos.WarDeeVO;
import com.padcmyannmar.asartaline.delegates.WarDeeDelegate;
import com.padcmyannmar.asartaline.viewholders.WarDeeRestaurantsViewHolder;

import java.util.ArrayList;
import java.util.List;

//------------------------------------------------------------------------------
/* 1 Adapter tightly connected with 1 View Holder */
public class WarDeeAdapter extends RecyclerView.Adapter<WarDeeRestaurantsViewHolder> {

    private WarDeeDelegate mWarDeeDelegate;

    private List<WarDeeVO> mWarDeeList;

    public WarDeeAdapter(WarDeeDelegate mWarDeeDelegate) {
        this.mWarDeeDelegate = mWarDeeDelegate;
        mWarDeeList = new ArrayList<>();

    }

    //Framework call these methods.
    //call onCreateViewHolder() when Recycler View needs to create item view
    @NonNull
    @Override
    public WarDeeRestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_war_dee_restaurants,parent,false);
        return new WarDeeRestaurantsViewHolder(view,mWarDeeDelegate);
    }

    /*call onBindViewHolder for adding data to Item View and Bind data to Item View when user scroll down*/
    @Override
    public void onBindViewHolder(@NonNull WarDeeRestaurantsViewHolder holder, int position) {
        //Adjust Adapter to change POC to live
        holder.bindData(mWarDeeList.get(position));
    }

    @Override
    public int getItemCount() {
        return mWarDeeList.size();
    }

    //insert()
    public void setmWarDeeList(List<WarDeeVO> mWarDeeList) {
        this.mWarDeeList = mWarDeeList;
        //refresh all itemViews
        notifyDataSetChanged();
    }

    //to append news data behind old data
    public void appendWarDeeList(List<WarDeeVO> warDeeList) {
        warDeeList.addAll(warDeeList);
        notifyDataSetChanged();
    }
}
