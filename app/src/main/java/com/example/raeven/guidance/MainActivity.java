
package com.example.raeven.guidance;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private UserDatabaseHelper _dbHelper = null;
    private SQLiteDatabase _database = null;

    EditText username;
    EditText password;
    Button login;
    String user = "",pass = "";

    String check;

    DatabaseReference _counselor;
    DatabaseReference _student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        _counselor = database.getReference("Accounts").child("Counselors");
        _student = database.getReference("Accounts").child("Student");

        //region SQLITE
        try
        {
            //Creates the db upon
            // instantiation (executes on create)
            _dbHelper = new UserDatabaseHelper(getApplicationContext());
            _database = _dbHelper.getWritableDatabase();
            //Toast.makeText(getApplicationContext(), "You good bro", Toast.LENGTH_SHORT).show();
        }

        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Something went wsssssrong.", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
            startActivity(new Intent(this, MainActivity.class)); // go back to main screen
        }
        //endregion
    }

    protected void onStart(){
        super.onStart();

        login = (Button)findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText username = (EditText)findViewById(R.id.editText_studentNumber);
                EditText password = (EditText)findViewById(R.id.editText_password);
                user = username.getText().toString();
                pass = password.getText().toString();
                int test = 0;
                if(pass.trim().length() != 0 && user.trim().length() != 0) {
                    _counselor.child(user).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                                String hello = map.get("Password");
                                String hello2 = map.get("StudentNo");
                                String name = map.get("Name");
                                if (user.equals(hello2) && pass.equals(hello)) {
                                    Toast.makeText(getApplicationContext(), "Hello " + name + "!", Toast.LENGTH_SHORT).show();
                                    check = "1";
                                    Intent intent = new Intent(getApplicationContext(), DashboardActivityAdmin.class);
                                    intent.putExtra("Number", hello2);
                                    intent.putExtra("accountType", "counselor");
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(getApplicationContext(), "Invalid User/Password", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                    if(check != "1") {
                        _student.child(user).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                                    String hello = map.get("Password");
                                    String hello2 = map.get("StudentNo");
                                    String name = map.get("Name");
                                    if (user.equals(hello2) && pass.equals(hello)) {
                                        Toast.makeText(getApplicationContext(), "Hello " + name + "!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                        intent.putExtra("Number", hello2);
                                        intent.putExtra("accountType", "student");
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Invalid User/Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registerAccount (View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}
