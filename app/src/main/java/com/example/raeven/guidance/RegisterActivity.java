package com.example.raeven.guidance;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private UserDatabaseHelper _dbHelper = null;
    private SQLiteDatabase _database = null;

    EditText name ;
    EditText studentNo;
    EditText password;
    EditText password2;
    EditText email;
    EditText contact;
    EditText program;

    RadioButton student;
    RadioButton councilor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void register (View view) {
        name = (EditText)findViewById(R.id.register_name);
        studentNo = (EditText)findViewById(R.id.register_studNumber);
        password = (EditText)findViewById(R.id.register_password);
        password2 = (EditText)findViewById(R.id.register_password2);
        email = (EditText)findViewById(R.id.register_Email);
        contact = (EditText)findViewById(R.id.register_contactNumber);
        program = (EditText)findViewById(R.id.register_Course);

        student = (RadioButton)findViewById(R.id.radioButton_Student);
        councilor = (RadioButton)findViewById(R.id.radioButton_Councilor);

        int accountType = 0;
        if(student.isChecked()){
            accountType = 1;
        }
        else if(councilor.isChecked())
        {
            accountType = 2;
        }
        try {
            registerAccount(name.getText().toString(), studentNo.getText().toString(), password.getText().toString(), email.getText().toString(), contact.getText().toString(), program.getText().toString(), accountType);

            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(myIntent);
        }catch (Exception ex){
            Toast.makeText(this,"Error" + ex,Toast.LENGTH_LONG);
        }


    }

    public void registerAccount(String name, String StudentNumber, String password, String Email, String Contact, String Program, int AccountType){
        User user = new User(name, StudentNumber, password, Email, Contact, Program, AccountType);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Accounts");

        if(AccountType == 1) {
            myRef.child("Student").child(String.valueOf(StudentNumber)).child("Name").setValue(user.get_name());
            myRef.child("Student").child(String.valueOf(StudentNumber)).child("StudentNo").setValue(user.get_studentNumber());
            myRef.child("Student").child(String.valueOf(StudentNumber)).child("Password").setValue(user.get_password());
            myRef.child("Student").child(String.valueOf(StudentNumber)).child("Email").setValue(user.get_email());
            myRef.child("Student").child(String.valueOf(StudentNumber)).child("ContactNo").setValue(user.get_contactNumber());
            myRef.child("Student").child(String.valueOf(StudentNumber)).child("Program").setValue(user.get_course());
        }
        else{
            myRef.child("Counselors").child(String.valueOf(StudentNumber)).child("Name").setValue(user.get_name());
            myRef.child("Counselors").child(String.valueOf(StudentNumber)).child("StudentNo").setValue(user.get_studentNumber());
            myRef.child("Counselors").child(String.valueOf(StudentNumber)).child("Password").setValue(user.get_password());
            myRef.child("Counselors").child(String.valueOf(StudentNumber)).child("Email").setValue(user.get_email());
            myRef.child("Counselors").child(String.valueOf(StudentNumber)).child("ContactNo").setValue(user.get_contactNumber());
            myRef.child("Counselors").child(String.valueOf(StudentNumber)).child("Program").setValue(user.get_course());
        }
    }
}


