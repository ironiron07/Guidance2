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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AssesmentFragment2 extends Fragment {

    Button btnNext;
    EditText q2ans;
    String accountNo,accountType;
    DatabaseReference reference;
    public AssesmentFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_assesment_fragment2, container, false);
        btnNext = (Button)v.findViewById(R.id.q2next);
        q2ans = (EditText)v.findViewById(R.id.q2Answer);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("Appointment");

        DashboardActivity dashActivity = (DashboardActivity) getActivity();
        accountNo = dashActivity.getAccount();
        accountType = dashActivity.getAccountType();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(accountNo).child("Answer2").setValue(q2ans.getText().toString());

                Fragment assFragment3 = new AssessmentFragment3();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder, assFragment3);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return v;
    }

}
