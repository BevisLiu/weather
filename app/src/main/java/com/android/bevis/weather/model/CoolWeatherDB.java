package com.android.bevis.weather.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.bevis.weather.db.CoolWeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bevis on 2016/5/25.
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

    /**
     * get all of the province in Country from Database
     */

    public List<Province> loadProvince() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = mDatabase.query("Province", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }

        return list;
    }

    /**
     * save the city instance to Datebase
     */

    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            mDatabase.insert("city", null, values);
        }
    }

    /**
     * get all of the city in a province from Database
     */

    public List<City> loadCities(int provinceId) {

        List<City> list = new ArrayList<City>();
        Cursor cursor = mDatabase.query("City", null, "province_id = ?", new String[]{String.valueOf(provinceId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());

        }

        return list;
    }

    /**
     * save the county instance to Database
     */

    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            mDatabase.insert("County", null, values);
        }
    }

    /**
     * get all of the county in a city from Database
     */
    public List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = mDatabase.query("County", null, "city_id = ?", new String[]{String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {

                County county = new County();
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            } while (cursor.moveToNext());
        }

        return list;
    }
}
