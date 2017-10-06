package be.ap.edu.examenandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tim on 6/10/2017.
 */

public class MapSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "map.db";
    private static final String TABLE_LOCATIONS = "locations";
    private static final int DATABASE_VERSION = 5;

    public MapSQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOCATIONS_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "(_id INTEGER PRIMARY KEY, longitude REAL, latitude REAL, beschrijving TEXT)";
        db.execSQL(CREATE_LOCATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        onCreate(db);
    }

    public void addLocation(Double latitude, Double longitude, String beschrijving) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_CONTACTS, null, null);

        ContentValues values = new ContentValues();
        values.put("latitude", latitude);
        values.put("longitude", longitude);
        values.put("beschrijving", beschrijving);

        db.insert(TABLE_LOCATIONS, null, values);
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ TABLE_LOCATIONS);
        db.close();
    }

    public void getAllLocations(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLE_LOCATIONS, null);



    }
}
