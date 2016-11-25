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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AssessmentFragment3 extends Fragment {

    Button btnDone;
    EditText q3answer;
    EditText q4answer;
    DatabaseReference reference;
    String accountNo,accountType;
    public AssessmentFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_assessment_fragment3, container, false);

        btnDone = (Button)v.findViewById(R.id.q3done);
        q4answer = (EditText)v.findViewById(R.id.q4ans);
        q3answer = (EditText)v.findViewById(R.id.q3Answer);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("Appointment");

        DashboardActivity dashActivity = (DashboardActivity) getActivity();
        accountNo = dashActivity.getAccount();
        accountType = dashActivity.getAccountType();
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(accountNo).child("Answer3").setValue(q3answer.getText().toString());
                reference.child(accountNo).child("Answer4").setValue(q4answer.getText().toString());
                reference.child(accountNo).child("Status").setValue("Pending");

                Fragment acc = new AccountFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder, acc);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Toast.makeText(getActivity(),"Request for counselling sent",Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}
