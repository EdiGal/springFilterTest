package com.upright;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Intervals {

    @SerializedName("intervals")
    ArrayList<Interval> intervals = new ArrayList<>();

    public Intervals(ArrayList<Interval> intervals) {
        this.intervals = intervals;
    }

    public ArrayList<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(ArrayList<Interval> intervals) {
        this.intervals = intervals;
    }
}
