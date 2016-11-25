package com.example.raeven.guidance;

/**
 * Created by Viana on 9/25/2016.
 */
public class User
{
    private String _name;
    private String _studentNumber;
    private String _password;
    private String _email;
    private String _contactNumber;
    private String _course;
    private int _userType;

    public User(String _name, String _studentNumber, String _password, String _email, String _contactNumber, String _course, int _userType) {
        this._name = _name;
        this._studentNumber = _studentNumber;
        this._password = _password;
        this._email = _email;
        this._contactNumber = _contactNumber;
        this._course = _course;
        this._userType = _userType;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_studentNumber() {
        return _studentNumber;
    }

    public void set_studentNumber(String _studentNumber) {
        this._studentNumber = _studentNumber;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_contactNumber() {
        return _contactNumber;
    }

    public void set_contactNumber(String _contactNumber) {
        this._contactNumber = _contactNumber;
    }

    public String get_course() {
        return _course;
    }

    public void set_course(String _course) {
        this._course = _course;
    }

    public int get_userType() {
        return _userType;
    }

    public void set_userType(int _userType) {
        this._userType = _userType;
    }
}
