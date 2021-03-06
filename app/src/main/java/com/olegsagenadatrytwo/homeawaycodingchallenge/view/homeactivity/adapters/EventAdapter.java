package com.olegsagenadatrytwo.homeawaycodingchallenge.view.homeactivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.olegsagenadatrytwo.homeawaycodingchallenge.R;
import com.olegsagenadatrytwo.homeawaycodingchallenge.entities.Event;
import com.olegsagenadatrytwo.homeawaycodingchallenge.view.detailactivity.DetailActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{

    public static final String FAVORITES = "favorites";
    private List<Event> events = new ArrayList<>();
    private Context context;

    public EventAdapter(List<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    public void setEvents(List<Event> events){
        this.events = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_initial_info_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvEventTitle.setText(events.get(position).getTitle());
        holder.tvEventCity.setText(events.get(position).getVenue().getDisplayLocation());
        String d = events.get(position).getDatetimeLocal();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvEventDate.setText(new SimpleDateFormat("E, dd MMMM yyyy hh:mm:ss a").format(date));

        String imageURL = events.get(position).getPerformers().get(0).getImage();
        if(imageURL != null){
            Glide.with(context).load(imageURL).into(holder.ivImage);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.defaultimage, context.getApplicationContext().getTheme()));
            } else {
                holder.ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.defaultimage));
            }
        }

        SharedPreferences sharedPreferences = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString(String.valueOf(events.get(position).getId()), "default");
        if(!id.equals("default")){
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.accent));
        }else{
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.icons));

        }

        //onClick for each Item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Detail Activity
                Intent intent = new Intent(context, DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("event", events.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvEventTitle;
        private TextView tvEventCity;
        private TextView tvEventDate;
        private ImageView ivImage;

        ViewHolder(View itemView) {
            super(itemView);
            tvEventTitle = (TextView) itemView.findViewById(R.id.tvEventTitle);
            tvEventCity = (TextView) itemView.findViewById(R.id.tvEventCity);
            tvEventDate = (TextView) itemView.findViewById(R.id.tvEventDate);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }

}
