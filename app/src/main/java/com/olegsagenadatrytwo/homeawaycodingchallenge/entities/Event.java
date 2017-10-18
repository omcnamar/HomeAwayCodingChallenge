
package com.olegsagenadatrytwo.homeawaycodingchallenge.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Event implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("datetime_local")
    @Expose
    private String datetimeLocal;
    @SerializedName("visible_until_utc")
    @Expose
    private String visibleUntilUtc;
    @SerializedName("datetime_tbd")
    @Expose
    private Boolean datetimeTbd;
    @SerializedName("date_tbd")
    @Expose
    private Boolean dateTbd;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("performers")
    @Expose
    private List<Performer> performers = null;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("announce_date")
    @Expose
    private String announceDate;
    @SerializedName("time_tbd")
    @Expose
    private Boolean timeTbd;
    @SerializedName("stats")
    @Expose
    private Stats_ stats;
    @SerializedName("datetime_utc")
    @Expose
    private String datetimeUtc;
    @SerializedName("taxonomies")
    @Expose
    private List<Taxonomy_> taxonomies = null;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("is_open")
    @Expose
    private Boolean isOpen;
    @SerializedName("short_title")
    @Expose
    private String shortTitle;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("links")
    @Expose
    private List<Object> links = null;

    protected Event(Parcel in) {
        id = in.readInt();
        title = in.readString();
        createdAt = in.readString();
        url = in.readString();
        datetimeLocal = in.readString();
        visibleUntilUtc = in.readString();
        type = in.readString();
        announceDate = in.readString();
        datetimeUtc = in.readString();
        shortTitle = in.readString();
        stats = in.readParcelable(Stats_.class.getClassLoader());
        venue = in.readParcelable(Venue.class.getClassLoader());
        performers = new ArrayList<>();
        performers = in.readArrayList(Performer.class.getClassLoader());
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDatetimeLocal() {
        return datetimeLocal;
    }

    public void setDatetimeLocal(String datetimeLocal) {
        this.datetimeLocal = datetimeLocal;
    }

    public String getVisibleUntilUtc() {
        return visibleUntilUtc;
    }

    public void setVisibleUntilUtc(String visibleUntilUtc) {
        this.visibleUntilUtc = visibleUntilUtc;
    }

    public Boolean getDatetimeTbd() {
        return datetimeTbd;
    }

    public void setDatetimeTbd(Boolean datetimeTbd) {
        this.datetimeTbd = datetimeTbd;
    }

    public Boolean getDateTbd() {
        return dateTbd;
    }

    public void setDateTbd(Boolean dateTbd) {
        this.dateTbd = dateTbd;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getAnnounceDate() {
        return announceDate;
    }

    public void setAnnounceDate(String announceDate) {
        this.announceDate = announceDate;
    }

    public Boolean getTimeTbd() {
        return timeTbd;
    }

    public void setTimeTbd(Boolean timeTbd) {
        this.timeTbd = timeTbd;
    }

    public Stats_ getStats() {
        return stats;
    }

    public void setStats(Stats_ stats) {
        this.stats = stats;
    }

    public String getDatetimeUtc() {
        return datetimeUtc;
    }

    public void setDatetimeUtc(String datetimeUtc) {
        this.datetimeUtc = datetimeUtc;
    }

    public List<Taxonomy_> getTaxonomies() {
        return taxonomies;
    }

    public void setTaxonomies(List<Taxonomy_> taxonomies) {
        this.taxonomies = taxonomies;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getLinks() {
        return links;
    }

    public void setLinks(List<Object> links) {
        this.links = links;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(createdAt);
        dest.writeString(url);
        dest.writeString(datetimeLocal);
        dest.writeString(visibleUntilUtc);
        dest.writeString(type);
        dest.writeString(announceDate);
        dest.writeString(datetimeUtc);
        dest.writeString(shortTitle);
        dest.writeParcelable(stats, 0);
        dest.writeParcelable(venue, 0);
        dest.writeList(performers);
    }
}
