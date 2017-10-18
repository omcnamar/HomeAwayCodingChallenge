
package com.olegsagenadatrytwo.homeawaycodingchallenge.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats_ implements Parcelable{

    @SerializedName("highest_price")
    @Expose
    private Object highestPrice;
    @SerializedName("lowest_price")
    @Expose
    private Object lowestPrice;
    @SerializedName("listing_count")
    @Expose
    private Object listingCount;
    @SerializedName("lowest_price_good_deals")
    @Expose
    private Object lowestPriceGoodDeals;
    @SerializedName("average_price")
    @Expose
    private Object averagePrice;

    protected Stats_(Parcel in) {
        averagePrice = in.readString();
    }

    public static final Creator<Stats_> CREATOR = new Creator<Stats_>() {
        @Override
        public Stats_ createFromParcel(Parcel in) {
            return new Stats_(in);
        }

        @Override
        public Stats_[] newArray(int size) {
            return new Stats_[size];
        }
    };

    public Object getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(Object highestPrice) {
        this.highestPrice = highestPrice;
    }

    public Object getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Object lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public Object getListingCount() {
        return listingCount;
    }

    public void setListingCount(Object listingCount) {
        this.listingCount = listingCount;
    }

    public Object getLowestPriceGoodDeals() {
        return lowestPriceGoodDeals;
    }

    public void setLowestPriceGoodDeals(Object lowestPriceGoodDeals) {
        this.lowestPriceGoodDeals = lowestPriceGoodDeals;
    }

    public Object getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Object averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(averagePrice != null) {
            dest.writeString(averagePrice.toString());
        }else{
            dest.writeString("");
        }
    }
}
