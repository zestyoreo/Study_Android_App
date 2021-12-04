package com.example.studyapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, Lifecycle lc, int NumOfTabs) {
        super(fm,lc);
        this.mNumOfTabs = NumOfTabs;
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new PlanFragment();
            case 1: return new ExamFragment();
            case 2: return new AssignmentFragment();
            case 3: return new LectureFragment();
            default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return mNumOfTabs;
    }
}