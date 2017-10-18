package com.olegsagenadatrytwo.homeawaycodingchallenge.view.detailactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.olegsagenadatrytwo.homeawaycodingchallenge.R;
import com.olegsagenadatrytwo.homeawaycodingchallenge.entities.Event;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String TAG = "Detail";
    public static final String FAVORITES = "favorites";
    @BindView(R.id.my_settings_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.ivDetailImage)
    ImageView mIvDetailImage;
    @BindView(R.id.tvEventTitle)
    TextView mTvEventTitle;
    @BindView(R.id.tvAddress)
    TextView mTvAddress;
    @BindView(R.id.tvDate)
    TextView mTvData;
    @BindView(R.id.tvPrice)
    TextView mTvPrice;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        //get Intent
        Intent intent = getIntent();
        event = intent.getParcelableExtra("event");

        //show user the details of the selected item
        mTvEventTitle.setText(event.getTitle());
        mTvAddress.setText(event.getVenue().getExtendedAddress());
        mTvData.setText(String.valueOf(event.getDatetimeUtc()));
        mTvPrice.setText(event.getStats().getAveragePrice().toString());
        String imageURL = event.getPerformers().get(0).getImage();
        Glide.with(this).load(imageURL).into(mIvDetailImage);

        actionBarSetUp();
    }

    /**
     * action bar set up
     */
    private void actionBarSetUp() {
        myToolbar = (Toolbar) findViewById(R.id.my_settings_toolbar);
        myToolbar.setTitle("detail");
        myToolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_text));
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    /**
     * create options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        //when creating action bar check the shared preference if the user has liked this event
        SharedPreferences sharedPreferences = getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString(String.valueOf(event.getId()), "default");
        if(id.equals("default")){
            menu.findItem(R.id.actionLike).setIcon(R.drawable.ic_unlike);

        }else{
            menu.findItem(R.id.actionLike).setIcon(R.drawable.ic_like_48dp);
        }
        return true;
    }

    /**
     * options for action bar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //case back button
            case android.R.id.home:
                onBackPressed();
                break;

            //case when the user clicks the heart(like) button
            case R.id.actionLike:

                //check the shared preference if the user liked current item
                SharedPreferences sharedPreferences = getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
                String id = sharedPreferences.getString(String.valueOf(event.getId()), "default");

                SharedPreferences.Editor editor = sharedPreferences.edit();
                //if user did not like the item, add it to liked
                if(id.equals("default")){
                    editor.putString(String.valueOf(event.getId()), String.valueOf(event.getId()));
                    editor.apply();
                    item.setIcon(R.drawable.ic_like_48dp);
                    Toast.makeText(this, "Added to Favorites!" + event.getId(), Toast.LENGTH_SHORT).show();

                //if user has already liked this event remove the like
                }else{
                    editor.remove(String.valueOf(event.getId())).commit();
                    item.setIcon(R.drawable.ic_unlike);
                    Toast.makeText(this, "Removed from Favorites!" + event.getId(), Toast.LENGTH_SHORT).show();

                }
                break;

        }
        return true;
    }
}
