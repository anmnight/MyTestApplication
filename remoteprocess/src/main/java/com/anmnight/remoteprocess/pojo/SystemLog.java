package com.anmnight.remoteprocess.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/27 13:39
 * anmnight@qq.com
 */

@Entity(tableName = "system_log")
public class SystemLog implements Parcelable {

    public long timeSeed;
    public String timeString;
    public float availableMemory, processCpu, totalCpu;
    public float usedBattery;

    public SystemLog(){

    }

    protected SystemLog(Parcel in) {
        id = in.readLong();
        timeSeed = in.readLong();
        timeString = in.readString();
        availableMemory = in.readFloat();
        processCpu = in.readFloat();
        totalCpu = in.readFloat();
        usedBattery = in.readFloat();
    }

    public static final Creator<SystemLog> CREATOR = new Creator<SystemLog>() {
        @Override
        public SystemLog createFromParcel(Parcel in) {
            return new SystemLog(in);
        }

        @Override
        public SystemLog[] newArray(int size) {
            return new SystemLog[size];
        }
    };

    @PrimaryKey(autoGenerate = true)
    public long id;

    public long getTimeSeed() {
        return timeSeed;
    }

    public void setTimeSeed(long timeSeed) {
        this.timeSeed = timeSeed;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public float getAvailableMemory() {
        return availableMemory;
    }

    public void setAvailableMemory(float availableMemory) {
        this.availableMemory = availableMemory;
    }

    public float getProcessCpu() {
        return processCpu;
    }

    public void setProcessCpu(float processCpu) {
        this.processCpu = processCpu;
    }

    public float getTotalCpu() {
        return totalCpu;
    }

    public void setTotalCpu(float totalCpu) {
        this.totalCpu = totalCpu;
    }

    public float getUsedBattery() {
        return usedBattery;
    }

    public void setUsedBattery(float usedBattery) {
        this.usedBattery = usedBattery;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeLong(timeSeed);
        parcel.writeString(timeString);
        parcel.writeFloat(availableMemory);
        parcel.writeFloat(processCpu);
        parcel.writeFloat(totalCpu);
        parcel.writeFloat(usedBattery);
    }
}
