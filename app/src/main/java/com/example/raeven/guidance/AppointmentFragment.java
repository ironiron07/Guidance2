package com.example.raeven.guidance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentFragment extends Fragment {

    Button openChat, btnBack, setAppointment;
    EditText txtSetAppointment;
    String studentNumber;

    DatabaseReference reference;
    DatabaseReference _student;

    String name;

    public AppointmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_appointment, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        _student = database.getReference("Accounts").child("Student");


        Bundle bundle = this.getArguments();

        studentNumber = bundle.getString("btnText");
        final TextView lblName = (TextView)v.findViewById(R.id.lblAppointmentWith);

        _student.child(studentNumber).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //Toast.makeText(getActivity(), ""+dataSnapshot.child("Name").getValue(), Toast.LENGTH_SHORT).show();
                    lblName.setText(dataSnapshot.child("Name").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Inflate the layout for this fragment
        openChat = (Button) v.findViewById(R.id.btnOpenChat);
        setAppointment = (Button) v.findViewById(R.id.btnSetAppointment);
        btnBack = (Button) v.findViewById(R.id.btnBack);
        txtSetAppointment = (EditText) v.findViewById(R.id.txtSetAppoinment);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        openChat.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ChatFragment chatFragment = new ChatFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentHolder, chatFragment,
                        chatFragment.getTag()).commit();
            }
        });

        setAppointment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Appointment set at: " + txtSetAppointment.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().show(fragmentManager.findFragmentById(R.id.adminHome)).commit();
                fragmentManager.beginTransaction().remove(fragmentManager.findFragmentById(R.id.appointmentFrag)).commit();
            }
        });
    }
}
