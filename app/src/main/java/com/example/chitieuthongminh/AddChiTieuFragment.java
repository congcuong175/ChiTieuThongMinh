package com.example.chitieuthongminh;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.chitieuthongminh.Adapter.LCTAdapter;
import com.example.chitieuthongminh.Adapter.LTNAdapter;
import com.example.chitieuthongminh.Adapter.ViewPagerAdapter;
import com.example.chitieuthongminh.Api.ApiService;
import com.example.chitieuthongminh.Entity.ChiTietChiTieu;
import com.example.chitieuthongminh.Entity.LoaiChiTieuEntity;
import com.example.chitieuthongminh.Entity.LoaiThuNhapEntity;
import com.google.android.material.tabs.TabLayout;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddChiTieuFragment extends Fragment {

    public AddChiTieuFragment() {
        // Required empty public constructor
    }

    LinearLayout date_picker_dialog, time_picker_dialog;
    EditText edt_tien, tv_ghichu;
    TextView tv_ngaythang, tv_gio;
    Button btn_hoantat_add_chitieuorthunhap;
    ImageView imv_icon_add;
    ListView list_chitieu_dialog,list_thunhap_dialog;
LCTAdapter lctAdapter;
List<LoaiChiTieuEntity> list;
LTNAdapter ltnAdapter;
    List<LoaiThuNhapEntity> list2;
    boolean ktloaichitieu=true;
    int maphanloai=0;
    Dialog dialog;
    Dialog dialogld;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edt_tien = view.findViewById(R.id.edt_nhaptien);
        showDialogLoader();
        tv_ngaythang = view.findViewById(R.id.tv_ngaythang);
        date_picker_dialog = view.findViewById(R.id.date_picker_dialog);
        time_picker_dialog = view.findViewById(R.id.time_picker_dialog);
        btn_hoantat_add_chitieuorthunhap = view.findViewById(R.id.btn_hoantat_add_chitieuorthunhap);
        imv_icon_add=view.findViewById(R.id.imv_icon_add);
        list=new ArrayList<>();
        list2=new ArrayList<>();
        lctAdapter=new LCTAdapter(getContext(),R.layout.item_layout_dialog,list);
        ltnAdapter=new LTNAdapter(getContext(),R.layout.item_layout_dialog,list2);
        tv_gio = view.findViewById(R.id.tv_gio);
        tv_ghichu = view.findViewById(R.id.tv_ghichu);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tv_ngaythang.setText(simpleDateFormat.format(calendar.getTime()));

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
        tv_gio.setText(simpleDateFormat1.format(calendar.getTime()));

        showDialog();
        date_picker_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });
        time_picker_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonGio();
            }
        });
        btn_hoantat_add_chitieuorthunhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                ChiTietChiTieu chiTietChiTieu = new ChiTietChiTieu();
                chiTietChiTieu.setMaLoaiChiTieu(maphanloai);
                chiTietChiTieu.setChiTieu(ktloaichitieu);
                chiTietChiTieu.setNgayThang(tv_ngaythang.getText().toString());
                chiTietChiTieu.setThoiGian(tv_gio.getText().toString());
                chiTietChiTieu.setGhiChu(tv_ghichu.getText().toString());

                chiTietChiTieu.setTien(Integer.parseInt(edt_tien.getText().toString()));
                ApiService.apiservice.themCT(chiTietChiTieu).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        dialog.hide();
                        Navigation.findNavController(view).navigate(R.id.action_addChiTieuFragment_to_trangChuFragment);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dialog.hide();
                    }
                });
            }
        });
        imv_icon_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

    }

    private void showDialog() {
         dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialogphanloai);
        dialog.setCancelable(false);
        list_chitieu_dialog=dialog.findViewById(R.id.list_chitieu_dialog);
        list_thunhap_dialog=dialog.findViewById(R.id.list_thunhap_dialog);
        list_thunhap_dialog.setAdapter(ltnAdapter);
        list_chitieu_dialog.setAdapter(lctAdapter);
        ApiService.apiservice.getDSLCT().enqueue(new Callback<List<LoaiChiTieuEntity>>() {
            @Override
            public void onResponse(Call<List<LoaiChiTieuEntity>> call, Response<List<LoaiChiTieuEntity>> response) {
                for (LoaiChiTieuEntity ct:response.body()
                ) {
                    list.add(ct);
                }
                lctAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<LoaiChiTieuEntity>> call, Throwable t) {

            }
        });
        ApiService.apiservice.getDSLTN().enqueue(new Callback<List<LoaiThuNhapEntity>>() {
            @Override
            public void onResponse(Call<List<LoaiThuNhapEntity>> call, Response<List<LoaiThuNhapEntity>> response) {
                for (LoaiThuNhapEntity tn:response.body()
                     ) {
                    list2.add(tn);
                }
                ltnAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<LoaiThuNhapEntity>> call, Throwable t) {

            }
        });
        list_chitieu_dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imv_icon_add.setImageResource(list.get(i).getIcon());
                ktloaichitieu=true;
                maphanloai=list.get(i).getMaLoai();
                dialog.hide();
            }
        });
        list_thunhap_dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imv_icon_add.setImageResource(list2.get(i).getIcon());
                ktloaichitieu=false;
                maphanloai=list2.get(i).getMaLoai();
                dialog.hide();
            }
        });
        dialog.show();
    }
    public void showDialogLoader(){
        dialogld=new Dialog(getContext());
        dialogld.setContentView(R.layout.itemloaddata);
        dialogld.setCancelable(false);
        Window window=dialogld.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams=window.getAttributes();
        layoutParams.gravity= Gravity.CENTER;
        window.setAttributes(layoutParams);
    }
    private void ChonNgay() {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                tv_ngaythang.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void ChonGio() {
        Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendar.set(0, 0, 0, i, i1);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                tv_gio.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, gio, phut, true);
        timePickerDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_chi_tieu, container, false);
    }
}