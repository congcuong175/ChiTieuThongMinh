package com.example.chitieuthongminh.Entity;

import com.google.gson.annotations.SerializedName;

public class LoaiThuNhapEntity {
    @SerializedName("maLoaiTN")
    int maLoai;
    @SerializedName("tenLoaiTN")
    String tenLoai;
    @SerializedName("icon")
    int icon;

    public LoaiThuNhapEntity() {
    }

    public LoaiThuNhapEntity(int maLoai, String tenLoai, int icon) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.icon = icon;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
