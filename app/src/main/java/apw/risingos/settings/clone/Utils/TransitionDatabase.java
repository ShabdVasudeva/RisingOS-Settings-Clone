package apw.risingos.settings.clone.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import apw.risingos.settings.clone.Utils.TransitionDatabase;

public class TransitionDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TransitionDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Transitions";
    private static final String COLUMN_ID = "id";
    private static final String COLUM_NAME = "opinion";

    public TransitionDatabase(Context ctxt) {
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
                        + " INTEGER"
                        + ")";
        db.execSQL(CREATE_TABLE);
        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUM_NAME, 0);
        db.insert(TABLE_NAME, null, initialValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void setSwitchState(boolean state) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM_NAME, state ? 1 : 0);
        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.update(TABLE_NAME, values, COLUMN_ID + "= ?", new String[] {"1"});
        db.close();
    }

    public boolean getSwitchState() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_NAME, new String[] {COLUM_NAME}, null, null, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            int state = cursor.getInt(0);
            cursor.close();
            return state == 1;
        }
        return false;
    }
}
