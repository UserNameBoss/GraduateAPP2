package com.example.yangxiaolong.graduateapp.frament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangxiaolong.graduateapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_my extends Fragment {


    public Fragment_my() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_fragment_my, container, false);
    }

}
