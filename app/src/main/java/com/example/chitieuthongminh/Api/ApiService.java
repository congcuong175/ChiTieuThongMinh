package com.example.chitieuthongminh.Api;

import com.example.chitieuthongminh.Entity.ChiTietChiTieu;
import com.example.chitieuthongminh.Entity.ChiTietEntity;
import com.example.chitieuthongminh.Entity.LoaiChiTieuEntity;
import com.example.chitieuthongminh.Entity.LoaiThuNhapEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson=new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiservice=new Retrofit.Builder()
            .baseUrl("https://apiqlchitieu-ra4.conveyor.cloud/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("api/LOAICHITIEUx")
    Call<List<LoaiChiTieuEntity>> getDSLCT();
    @GET("api/LOAICHITIEUx")
    Call<LoaiChiTieuEntity> getDSLCTBYID(@Query("id")int id);
    @PUT("api/LOAICHITIEUx")
    Call<Void> updateLCT(@Query("id")int id,@Body LoaiChiTieuEntity loaiChiTieuEntity);
    @POST("api/LOAICHITIEUx")
    Call<Void> themLCT(@Body LoaiChiTieuEntity loaiChiTieuEntity);
    @DELETE("api/LOAICHITIEUx")
    Call<Void> xoaLCT(@Query("id") int id);


    @GET("api/LOAITHUNHAPs")
    Call<List<LoaiThuNhapEntity>> getDSLTN();
    @GET("api/LOAITHUNHAPs")
    Call<LoaiThuNhapEntity> getDSLTNBYID(@Query("id")int id);
    @PUT("api/LOAITHUNHAPs")
    Call<Void> updateLTN(@Query("id")int id,@Body LoaiThuNhapEntity loaiThuNhapEntity);
    @POST("api/LOAITHUNHAPs")
    Call<Void> themLTN(@Body LoaiThuNhapEntity loaiThuNhapEntity);
    @DELETE("api/LOAITHUNHAPs")
    Call<Void> xoaLTN(@Query("id") int id);


    @GET("api/DBOChiTieux")
    Call<List<ChiTietEntity>> getCT();
    @GET("api/DBOChiTieux")
    Call<ChiTietChiTieu> getCTBYID(@Query("id")int id);
    @PUT("api/DBOChiTieux")
    Call<Void> updateCT(@Query("id")int id,@Body ChiTietChiTieu loaiThuNhapEntity);
    @POST("api/DBOChiTieux")
    Call<Void> themCT(@Body ChiTietChiTieu loaiThuNhapEntity);
    @DELETE("api/DBOChiTieux")
    Call<Void> xoaCT(@Query("id") int id);
}
