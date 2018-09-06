package com.anmnight.home.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/7/24 17:25
 * anmnight@qq.com
 */
public class Version implements Parcelable {


    public String appId;
    public Long versionNo;
    public Boolean isMount;

    public Version(){}


    protected Version(Parcel in) {
        appId = in.readString();
        if (in.readByte() == 0) {
            versionNo = null;
        } else {
            versionNo = in.readLong();
        }
        byte tmpIsMount = in.readByte();
        isMount = tmpIsMount == 0 ? null : tmpIsMount == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(appId);
        if (versionNo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(versionNo);
        }
        dest.writeByte((byte) (isMount == null ? 0 : isMount ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Version> CREATOR = new Creator<Version>() {
        @Override
        public Version createFromParcel(Parcel in) {
            return new Version(in);
        }

        @Override
        public Version[] newArray(int size) {
            return new Version[size];
        }
    };
}
