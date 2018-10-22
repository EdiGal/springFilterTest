package com.upright;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("getIntervalsGoResult")
    private Intervals intervals;

    public Result(Intervals intervals) {
        this.intervals = intervals;
    }

    public Intervals getIntervals() {
        return intervals;
    }

    public void setIntervals(Intervals intervals) {
        this.intervals = intervals;
    }
}
