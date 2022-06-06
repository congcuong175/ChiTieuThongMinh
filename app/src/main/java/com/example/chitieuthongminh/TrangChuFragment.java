package com.example.chitieuthongminh;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chitieuthongminh.Adapter.ChiTieuAdapter;
import com.example.chitieuthongminh.Api.ApiService;
import com.example.chitieuthongminh.Entity.ChiTietChiTieu;
import com.example.chitieuthongminh.Entity.ChiTietEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TrangChuFragment extends Fragment {
    RecyclerView recyclerView;
    ChiTieuAdapter chiTieuAdapter;
    FloatingActionButton btn_add;
    List<ChiTietEntity> arrayList;
    Handler handler2 =new Handler();
    int chitieu=0,thunhap=0;
    Runnable runnable2;
    TextView tv_time_now,tv_sodu,tv_tongchitieu,tv_tongthunhap;
    public TrangChuFragment() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rcv_listChitieu);
        chiTieuAdapter=new ChiTieuAdapter(getActivity());
        btn_add=view.findViewById(R.id.btn_add);
        tv_time_now=view.findViewById(R.id.tv_time_now);
        tv_sodu=view.findViewById(R.id.tv_sodu);
        tv_tongchitieu=view.findViewById(R.id.tv_tongchitieu);
        tv_tongthunhap=view.findViewById(R.id.tv_tongthunhap);
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/MM/yyyy");
        tv_time_now.setText(simpleDateFormat.format(calendar.getTime())+" số dư");

         arrayList=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chiTieuAdapter);

        chiTieuAdapter.setData(arrayList);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_trangChuFragment_to_addChiTieuFragment);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }
    public void getData(){
        arrayList.clear();
        chitieu=0;
        thunhap=0;
        try {
            ApiService.apiservice.getCT().enqueue(new Callback<List<ChiTietEntity>>() {
                @Override
                public void onResponse(Call<List<ChiTietEntity>> call, Response<List<ChiTietEntity>> response) {
                    if(response.body()!=null){
                        for (ChiTietEntity ct:response.body()
                        ) {
                            arrayList.add(ct);
                            for (ChiTietChiTieu ctct:ct.getList()
                            ) {
                                if(ctct.isChiTieu()){
                                    chitieu+=ctct.getTien();

                                }
                                else {
                                    thunhap+=ctct.getTien();
                                }
                            }
                        }
                        chiTieuAdapter.setData(arrayList);
                        tv_tongchitieu.setText(currencyFormat(chitieu+""));
                        tv_tongthunhap.setText(currencyFormat(thunhap+""));
                        tv_sodu.setText(currencyFormat((thunhap-chitieu)+""));
                    }
                }

                @Override
                public void onFailure(Call<List<ChiTietEntity>> call, Throwable t) {

                }
            });
        }catch (Exception ex){

        }

    }
    @Override
    public void onResume() {
        super.onResume();
        handler2.postDelayed(runnable2=new Runnable() {
            @Override
            public void run() {
                getData();
                handler2.postDelayed(runnable2,3000);
            }
        },1000);
    }


    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0");
        return formatter.format(Double.parseDouble(amount));
    }
}