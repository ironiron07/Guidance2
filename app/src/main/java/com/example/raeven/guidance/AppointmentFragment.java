package com.example.raeven.guidance;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentFragment extends Fragment {

    Button openChat, btnBack, setAppointment;
    EditText txtSetAppointment;


    public AppointmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_appointment, container, false);

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
            }
        });
    }
}
