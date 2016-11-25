package com.example.raeven.guidance;

import android.provider.BaseColumns;

/**
 * Created by Viana on 9/25/2016.
 */
public class UserContract
{
    private UserContract()
    {
        //To avoid instantiating anywhere
    }

    public static class UserDetails implements BaseColumns
    {
        public static final String TABLE_NAME = "USER_DETAILS_TABLE";
        public static final String NAME_COLUMN = "USER_COLUMN_NAME";
        public static final String STUDNUMBER_COLUMN = "USER_COLUMN_STUDNUMBER";
        public static final String PASSWORD_COLUMN = "USER_COLUMN_PASSWORD";
        public static final String ADDRESS_COLUMN = "USER_COLUMN_ADDRESS";
        public static final String CONTACTNUMBER_COLUMN = "USER_COLUMN_CONTACTNUMBER";
        public static final String COURSE_COLUMN = "USER_COLUMN_COURSE";
        public static final String USERTYPE_COLUMN = "USER_COLUMN_USERTYPE";
        //0 for student 1 for admin



    }
}
