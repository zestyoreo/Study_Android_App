package com.example.studyapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Date;

public class AssignmentFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private EventListAdapter mAdapter;
    private DataBase DB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assignment, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        DB = new DataBase(getActivity());
        LinkedList<event> E;
        try{
            E = DB.getAssignments();
        }catch(Exception e){
            E = new LinkedList<event>();
        }

//        st.set(121,12,10,21,0,0);
//        en.set(121,12,10,23,0,0);
//        E.add(new Event(0,"CS 213","Read Up on everything there is to read about Algorithms",st.getTime(),en.getTime(),0));
//        st.set(121,12,11,21,0,0);
//        en.set(121,12,11,23,0,0);
//        E.add(new Event(1,"CS 207","Read Up on everything there is to read about Numbers",st.getTime(),en.getTime(),0));
//        st.set(121,12,12,21,0,0);
//        en.set(121,12,12,23,0,0);
//        E.add(new Event(2,"CS 215","Read Up on everything there is to read about Data",st.getTime(),en.getTime(),0));
        mAdapter = new EventListAdapter(getContext(), E);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}