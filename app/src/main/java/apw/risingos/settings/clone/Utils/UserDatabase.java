package apw.risingos.settings.clone.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import apw.risingos.settings.clone.Utils.UserDatabase;

public class UserDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserName.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "UserName";
    private static final String COLUMN_ID = "id";
    private static final String COLUM_NAME = "name";

    public UserDatabase(Context ctxt) {
        super(ctxt, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE "
                        + TABLE_NAME
                        + "("
                        + COLUMN_ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUM_NAME
                        + " TEXT"
                        + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void setUserName(String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM_NAME, name);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[] {"1"});
        } else {
            values.put(COLUMN_ID, 1);
            db.insert(TABLE_NAME, null, values);
        }
        db.close();
    }

    public String getUserName() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =
                db.query(
                        TABLE_NAME,
                        new String[] {COLUM_NAME},
                        COLUMN_ID + "=?",
                        new String[] {"1"},
                        null,
                        null,
                        null);
        if (cursor != null && cursor.moveToNext()) {
            String name = cursor.getString(0);
            cursor.close();
            return name;
        }
        return null;
    }
}
