package com.upright;

import com.google.gson.annotations.SerializedName;

public class Interval {

    @SerializedName("beginTime")
    private String beginTime;
    @SerializedName("endTime")
    private String endTime;

    public Interval(String beginTime, String endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interval)) return false;

        Interval interval = (Interval) o;

        if (!getBeginTime().equals(interval.getBeginTime())) return false;
        return getEndTime().equals(interval.getEndTime());
    }

    @Override
    public int hashCode() {
        int result = getBeginTime().hashCode();
        result = 31 * result + getEndTime().hashCode();
        return result;
    }
}
