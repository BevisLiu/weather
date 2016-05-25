package com.android.bevis.weather.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.bevis.weather.db.CoolWeatherOpenHelper;

/**
 * Created by 47984 on 2016/5/25.
 */
public class CoolWeatherDB {

    /**
     * Database name
     */
    public static final String DB_NAME = "cool_weather";

    /**
     * Database version
     */
    private static final int VERSION = 1;
    
    private static CoolWeatherDB sCoolWeatherDB;
    
    private SQLiteDatabase mDatabase;
    
    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        mDatabase = dbHelper.getWritableDatabase();
    }

    /**
     * get CoolWeather instance
     */
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (sCoolWeatherDB == null) {
            sCoolWeatherDB = new CoolWeatherDB(context);
        }
        return sCoolWeatherDB;
    }

    /**
     * save the Province instance to Database
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            mDatabase.insert("Province", null,values);
        }
    }
    
    
}
