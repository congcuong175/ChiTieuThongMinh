package com.example.chitieuthongminh.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChiTietEntity {
    @SerializedName("Ngayt")
    String ngay;
    @SerializedName("List")
    List<ChiTietChiTieu> list;

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public List<ChiTietChiTieu> getList() {
        return list;
    }

    public void setList(List<ChiTietChiTieu> list) {
        this.list = list;
    }

    public ChiTietEntity(String ngay, List<ChiTietChiTieu> list) {
        this.ngay = ngay;
        this.list = list;
    }
}
