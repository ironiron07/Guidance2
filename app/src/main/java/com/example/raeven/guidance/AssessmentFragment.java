package com.example.raeven.guidance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AssessmentFragment extends Fragment {
    Button btnNext;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;

    DatabaseReference reference;

    String accountNo,accountType;
    String answer;
    public AssessmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_assessment, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("Appointment");

        DashboardActivity dashActivity = (DashboardActivity) getActivity();
        accountNo = dashActivity.getAccount();
        accountType = dashActivity.getAccountType();

        btnNext = (Button)v.findViewById(R.id.q1next);
        r1 = (RadioButton)v.findViewById(R.id.radioButton1);
        r2 = (RadioButton)v.findViewById(R.id.radioButton2);
        r3 = (RadioButton)v.findViewById(R.id.radioButton3);
        r4 = (RadioButton)v.findViewById(R.id.radioButton4);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r1.isChecked())answer = "Academic performance";
                else if(r2.isChecked())answer = "Academic placement";
                else if(r3.isChecked())answer = "Personal";
                else if(r4.isChecked())answer = "Social";
                else{
                    Toast.makeText(getActivity(),"Fill up before continuing",Toast.LENGTH_SHORT).show();
                }
                reference.child(accountNo).child("Answer1").setValue(answer);

                Fragment assFragment2 = new AssesmentFragment2();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder, assFragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return v;
    }

}
