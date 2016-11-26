package com.example.raeven.guidance;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragmentAdmin.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragmentAdmin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragmentAdmin extends Fragment implements View.OnClickListener{

    public HomeFragmentAdmin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_fragment_admin, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Appointment");
        // Inflate the layout for this fragment

        //Button btnName = (Button)getActivity().findViewById(R.id.button5);
        ScrollView sv = (ScrollView)v.findViewById(R.id.scrollView);
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);
        String i = "iron";
        Button b = new Button(getActivity());
        b.setText(i);
        b.setOnClickListener(this);
        ll.addView(b);


        return v;
    }

    @Override
    public void onClick(View v) {
//        User user = ;
//
//        String studentNo = ((Button)v).getText().toString();
//        user.set_studentNumber(studentNo);

        AppointmentFragment appointmentFragment = new AppointmentFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.fragmentHolder, appointmentFragment,
                appointmentFragment.getTag()).commit();
    }
}
