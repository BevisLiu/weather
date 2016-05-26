package com.android.bevis.weather.model;

/**
 * Created by bevis on 2016/5/25.
 */
public class Province {
    
    private int id;
    private String provinceName;
    private String provinceCode;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
        
    }

    public String getProvinceName() {
        return provinceName;
    }
}
