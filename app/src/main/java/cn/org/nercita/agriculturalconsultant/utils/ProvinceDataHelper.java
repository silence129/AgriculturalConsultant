package cn.org.nercita.agriculturalconsultant.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell on 2016/11/6.
 */

public class ProvinceDataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "xzqh.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Create a helper object for the Events database
     */
    public ProvinceDataHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
