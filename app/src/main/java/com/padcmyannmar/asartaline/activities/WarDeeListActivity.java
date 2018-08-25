package com.padcmyannmar.asartaline.activities;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.padcmyannmar.asartaline.R;
import com.padcmyannmar.asartaline.adapters.WarDeeAdapter;
import com.padcmyannmar.asartaline.data.models.WarDeeModel;
import com.padcmyannmar.asartaline.data.vos.WarDeeVO;
import com.padcmyannmar.asartaline.delegates.WarDeeDelegate;
import com.padcmyannmar.asartaline.events.ApiErrorEvent;
import com.padcmyannmar.asartaline.events.SuccessForceRefreshGetWarDeeEvent;
import com.padcmyannmar.asartaline.events.SuccessGetWarDeeEvent;
import com.padcmyannmar.asartaline.utils.WarDeeConstants;
import com.padcmyannmar.asartaline.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.mmtextview.MMFontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
/*2nd step of Controller Pattern */
public class WarDeeListActivity extends BaseActivity implements WarDeeDelegate{

    WarDeeAdapter mWarDeeAdapter;

    /*@BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;*/

    @BindView(R.id.rv_war_dee_restaurants)
    RecyclerView rvWarDeeRestaurants;

   /* @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_war_dee_list);

        MMFontUtils.initMMTextView(this);

        ButterKnife.bind(this, this);

        mWarDeeAdapter = new WarDeeAdapter(this);
        rvWarDeeRestaurants.setAdapter(mWarDeeAdapter);
        rvWarDeeRestaurants.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        //Load More Data and calculate user scroll reach end of the list
        rvWarDeeRestaurants.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean isListEndReached = false;

            @Override
            //This method is called when user start scroll and state change
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("", "addOnScrollListener:onScrollStateChanged " + newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()
                                == recyclerView.getAdapter().getItemCount() - 1
                        && !isListEndReached) {
                    isListEndReached = true;
                    WarDeeModel.getObjInstance().loaWarDeeList();
                }

            }

            @Override
            //This method is called during scrolling
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("", "OnScrollListener:onScrolled -dx:" + dx + "dy: " + dy);

                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int pastVisibleItems = ((LinearLayoutManager) rvWarDeeRestaurants.getLayoutManager())
                        .findFirstCompletelyVisibleItemPosition();

                if (visibleItemCount + pastVisibleItems < totalItemCount) {
                    isListEndReached = false;
                }

            }
        });

        //Load Response Data
        WarDeeModel.getObjInstance().loaWarDeeList();

        //ForceRefresh Data
        WarDeeModel.getObjInstance().forcedRefreshWarDeeList();

        //show loading jatster but never disappear this loading jaster
       // swipeRefreshLayout.setRefreshing(true);
        // load data when user pull down
       /* swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //call this method while loading jatster start loading
            @Override
            public void onRefresh() {
                WarDeeModel.getObjInstance().forcedRefreshWarDeeList();

            }
        });*/
        //vpEmpty.setEmptyData(R.drawable.empty_data_placeholder, getString(R.string.empty_msg));
        /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /* Don't refresh UI data at Stop state. Event Bus Listen is refresh UI data. That's why unregister Event Bus at Stop state. */
    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }    }

    /* 1st step for Broadcast Listening*/
    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /*2nd step of Controller Pattern */
    @Override
    public void onTapItemView(WarDeeVO warDees) {
        Intent intent = new Intent(getApplicationContext(),WarDeeDetailsActivity.class);
        //add id to intent
        intent.putExtra(WarDeeConstants.WAR_DEE_ID,warDees.getWarDeeId());
        startActivity(intent);
    }

    /* 2n Step Event Bus Listen Method */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetWarDee(SuccessGetWarDeeEvent event){
        Log.d("onSuccessGetWarDee", "onSuccessGetWarDee :" + event.getWarDeesList());
        // pass data to Adapter.
        mWarDeeAdapter.appendWarDeeList(event.getWarDeesList());
        //make disappear after loading
        //swipeRefreshLayout.setRefreshing(false);
        //Hide Empty View
        //vpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessForceRefreshGetWarDee(SuccessForceRefreshGetWarDeeEvent event) {
        mWarDeeAdapter.setmWarDeeList(event.getWarDeesList());
        //swipeRefreshLayout.setRefreshing(false);
    }

    /* Good user experience for users */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailGetNewProduct(ApiErrorEvent event) {
        //don't show loading jatster because it doesn't make sense loading jaster appear when data cannot load
        //swipeRefreshLayout.setRefreshing(false);
        //solve bad experience while loading fail
        //Show Empty View
       // vpEmpty.setVisibility(View.VISIBLE);
      //  Snackbar.make(swipeRefreshLayout, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();

        /*if (mWarDeeAdapter.getItemCount() < 0) {
            vpEmpty.setVisibility(View.VISIBLE);
        } else {
            vpEmpty.setVisibility(View.GONE);

        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
