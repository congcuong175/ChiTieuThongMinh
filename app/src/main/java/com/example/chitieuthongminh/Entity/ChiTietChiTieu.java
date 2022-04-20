package com.example.chitieuthongminh.Entity;

public class ChiTietChiTieu {
    int maChiTieu;
    int maLoaiChiTieu;
    String tenChiTieu;
    String thoiGian;
    String ngayThang;
    int tien;
    String ghiChu;
    int idicon;
    boolean isChiTieu;

    public int getIdicon() {
        return idicon;
    }

    public void setIdicon(int idicon) {
        this.idicon = idicon;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public ChiTietChiTieu() {
    }

    public boolean isChiTieu() {
        return isChiTieu;
    }

    public void setChiTieu(boolean chiTieu) {
        isChiTieu = chiTieu;
    }

    public ChiTietChiTieu(int maLoaiChiTieu, String thoiGian, String ngayThang, int tien, String ghiChu, boolean isChiTieu) {
        this.maLoaiChiTieu = maLoaiChiTieu;
        this.thoiGian = thoiGian;
        this.ngayThang = ngayThang;
        this.tien = tien;
        this.ghiChu = ghiChu;
        this.isChiTieu = isChiTieu;
    }

    public ChiTietChiTieu(int maChiTieu, int maLoaiChiTieu, String tenChiTieu, String thoiGian, String ngayThang, int tien, String ghiChu, int idicon, boolean isChiTieu) {
        this.maChiTieu = maChiTieu;
        this.maLoaiChiTieu = maLoaiChiTieu;
        this.tenChiTieu = tenChiTieu;
        this.thoiGian = thoiGian;
        this.ngayThang = ngayThang;
        this.tien = tien;
        this.ghiChu = ghiChu;
        this.idicon = idicon;
        this.isChiTieu = isChiTieu;
    }

    public String getTenChiTieu() {
        return tenChiTieu;
    }

    public void setTenChiTieu(String tenChiTieu) {
        this.tenChiTieu = tenChiTieu;
    }

    public int getMaChiTieu() {
        return maChiTieu;
    }

    public void setMaChiTieu(int maChiTieu) {
        this.maChiTieu = maChiTieu;
    }

    public int getMaLoaiChiTieu() {
        return maLoaiChiTieu;
    }

    public void setMaLoaiChiTieu(int maLoaiChiTieu) {
        this.maLoaiChiTieu = maLoaiChiTieu;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }


}
