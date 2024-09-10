package apw.risingos.settings.clone.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import apw.risingos.settings.clone.Utils.SwitchState;

public class SwitchState extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "switchState.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "SwitchState";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_STATE = "state";

    public SwitchState(Context ctxt) {
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
                        + COLUMN_STATE
                        + " INTEGER"
                        + ")";
        db.execSQL(CREATE_TABLE);
        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_STATE, 0);
        db.insert(TABLE_NAME, null, initialValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(arg0);
    }

    public void setSwitchState(boolean state) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATE, state ? 1 : 0);
        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.update(TABLE_NAME,values,COLUMN_ID + "= ?",new String[]{"1"});
        db.close();
    }

    public boolean getSwitchState() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_NAME, new String[] {COLUMN_STATE}, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int state = cursor.getInt(0);
            cursor.close();
            return state == 1;
        }
        return false;
    }
}