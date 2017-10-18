package com.olegsagenadatrytwo.homeawaycodingchallenge.view.homeactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olegsagenadatrytwo.homeawaycodingchallenge.R;
import com.olegsagenadatrytwo.homeawaycodingchallenge.entities.SeatGeek;
import com.olegsagenadatrytwo.homeawaycodingchallenge.injection.homeactivity.DaggerHomeActivityComponent;
import com.olegsagenadatrytwo.homeawaycodingchallenge.injection.homeactivity.HomeActivityModule;
import com.olegsagenadatrytwo.homeawaycodingchallenge.view.homeactivity.adapters.EventAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

//import android.support.v7.widget.DividerItemDecoration;

public class HomeActivity extends AppCompatActivity implements HomeActivityContract.View, SearchView.OnQueryTextListener {

    public static final String TAG = "HomeActivity";
    @Inject
    HomeActivityPresenter presenter;
    @BindView(R.id.rvRecyclerView)
    RecyclerView mRvRecyclerView;
    @BindView(R.id.searchView)
    SearchView mSearchView;
    @BindView(R.id.tvError)
    TextView mTvError;
    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //bind ButterKnife
        ButterKnife.bind(this);

        //perform set up
        setUpDagger();
        initRecyclerView();
        initPresenter();

        //make the default request
        presenter.makeRestCallForEvents("predators");
        mSearchView.setOnQueryTextListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * method to set Up Dagger
     */
    private void setUpDagger() {
        DaggerHomeActivityComponent.create().insert(this);
        DaggerHomeActivityComponent.builder().homeActivityModule(new HomeActivityModule()).build().insert(this);
    }

    /**
     * method that initializes Recycler View
     */
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRvRecyclerView.getContext(), layoutManager.getOrientation());
        mRvRecyclerView.setLayoutManager(layoutManager);
        mRvRecyclerView.addItemDecoration(dividerItemDecoration);
        mRvRecyclerView.setItemAnimator(itemAnimator);
    }

    /**
     * method that initializes Presenter
     */
    private void initPresenter() {
        presenter.attachView(this);
        presenter.attachRemote();
    }

    /**
     * method that will show the user error (no results found)
     */
    @Override
    public void showError(String error) {

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvError.getLayoutParams();
        params.height = 70;
        mTvError.setLayoutParams(params);
        mTvError.setText(error);
    }

    /**
     * method that will update UI after the request was made
     */
    @Override
    public void updateUI(SeatGeek seatGeek) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvError.getLayoutParams();
        params.height = 0;
        mTvError.setLayoutParams(params);
        if(adapter == null) {
            adapter = new EventAdapter(seatGeek.getEvents(), getApplicationContext());
            mRvRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            if(seatGeek.getEvents().size() == 0){
                showError("No Results");
            }
        }else{
            adapter.setEvents(seatGeek.getEvents());
            adapter.notifyDataSetChanged();
            if(seatGeek.getEvents().size() == 0){
                showError("No Results");
            }
        }
    }

    /**
     * method to submit query
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.makeRestCallForEvents(query);
        return false;
    }

    /**
     * method to submit query every time text changes in the search view
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        presenter.makeRestCallForEvents(newText);
        return false;
    }
}
