package com.example.yangxiaolong.graduateapp.frament;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.activity.Comment_Collect_Activity;
import com.example.yangxiaolong.graduateapp.activity.Feedback_Activity;
import com.example.yangxiaolong.graduateapp.activity.MessageCenter_Activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_message extends Fragment implements View.OnClickListener{
private RelativeLayout RelativeLayout_MyMessage,RelativeLayout_Comment_Collect,RelativeLayout_feedback;



    public Fragment_message() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_message, container, false);
        this.RelativeLayout_MyMessage= (RelativeLayout) view.findViewById(R.id.RelativeLayout_MyMessage);
        this.RelativeLayout_Comment_Collect= (RelativeLayout) view.findViewById(R.id.RelativeLayout_Comment_Collect);
        this.RelativeLayout_feedback= (RelativeLayout) view.findViewById(R.id.RelativeLayout_feedback);

        this.RelativeLayout_MyMessage.setOnClickListener(this);
        this.RelativeLayout_Comment_Collect.setOnClickListener(this);
        this.RelativeLayout_feedback.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
         int item=v.getId();
        switch (item){
            case R.id.RelativeLayout_MyMessage:
                Intent intent=new Intent(getActivity(), MessageCenter_Activity.class);
                startActivity(intent);
                break;
            case  R.id.RelativeLayout_Comment_Collect:
                Intent intent1=new Intent(getActivity(), Comment_Collect_Activity.class);
                startActivity(intent1);
                break;
            case  R.id.RelativeLayout_feedback:
                Intent intent2=new Intent(getActivity(), Feedback_Activity.class);
                startActivity(intent2);
             break;
        }
    }
}
