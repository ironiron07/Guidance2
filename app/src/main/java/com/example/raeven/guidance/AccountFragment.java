package com.example.raeven.guidance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    TextView textAccount;
    TextView textName;
    TextView textContact;
    TextView textEmail;
    TextView textCourse;

    DatabaseReference _counselor;
    DatabaseReference _student;

    String accountNo;
    String accountType;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        _counselor = database.getReference("Accounts").child("Counselors");
        _student = database.getReference("Accounts").child("Student");

//        MainActivity dashActivity = (MainActivity) getActivity();
//        accountNo = dashActivity.getAccount();
//        accountType = dashActivity.getAccountType();
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        Bundle bundle = this.getArguments();
        accountType = bundle.getString("accType");
        accountNo = bundle.getString("number");

        textName = (TextView)v.findViewById(R.id.textName);
        textAccount = (TextView)v.findViewById(R.id.textAccount);
        textContact = (TextView)v.findViewById(R.id.textContact);
        textEmail = (TextView)v.findViewById(R.id.textEmail);
        textCourse = (TextView)v.findViewById(R.id.textCourse);

        if(accountType.equals("student")){
            _student.child(accountNo).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                        textName.setText(map.get("Name"));
                        textAccount.setText(map.get("StudentNo"));
                        textContact.setText(map.get("ContactNo"));
                        textEmail.setText(map.get("Email"));
                        textCourse.setText(map.get("Program"));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            _counselor.child(accountNo).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                        textName.setText(map.get("Name"));
                        textAccount.setText(map.get("StudentNo"));
                        textContact.setText(map.get("ContactNo"));
                        textEmail.setText(map.get("Email"));
                        textCourse.setText(map.get("Program"));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }



        return v;
    }

}
