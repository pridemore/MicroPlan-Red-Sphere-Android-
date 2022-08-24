package com.example.microplanredsphereandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    private static final String TAG = "Home Fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d(TAG, "ON HOME");

        ImageView backIcon=view.findViewById(R.id.left_icon);
        ImageView menu=view.findViewById(R.id.right_icon);
        TextView title=view.findViewById(R.id.title);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),login.class);
                startActivity(intent);
            }
        });
        return view;
    }
}