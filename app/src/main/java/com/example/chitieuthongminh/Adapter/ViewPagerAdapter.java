package com.example.chitieuthongminh.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.chitieuthongminh.ChiTieuFragment;
import com.example.chitieuthongminh.PhanLoaiFragment;
import com.example.chitieuthongminh.ThuNhapFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ChiTieuFragment();
            case 1:
                return new ThuNhapFragment();
            default:
                return new ChiTieuFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Chi Tiêu";
                break;
            case 1:
                title = "Thu Nhập";

        }
        return title;
    }
}
