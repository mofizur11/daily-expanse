package com.ideasoft.dailyexpense;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {


    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "expanse_database";

    // below int is our database version
    private static final int DB_VERSION = 5;

    // below variable is for our table name.
    private static final String TABLE_NAME = "expanse_table";

    // below variable is for our expanse type column
    private static final String EXPANSE_TYPE = "expanse";

    // below variable id for our expanse amount column.
    private static final String EXPANSE_AMOUNT = "amount";

    // below variable for our expanse date column.
    private static final String EXPANSE_DATE = "date";

    // below variable is for our expanse time column.
    private static final String EXPANSE_TIME = "time";


    // below variable is for our id column.
    private static final String ID_COL = "id";

    private static final String EXPENSE_DOCUMENT = "image";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EXPANSE_TYPE + " TEXT,"
                + EXPANSE_AMOUNT + " TEXT,"
                + EXPANSE_DATE + " TEXT,"
                + EXPANSE_TIME + " TEXT,"
                + EXPENSE_DOCUMENT + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new expanse to our sqlite database.
    public void addNewExpanse(String expanseType, String expanseAmount, String expanseDate, String expanseTime, String image) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(EXPANSE_TYPE, expanseType);
        values.put(EXPANSE_AMOUNT, expanseAmount);
        values.put(EXPANSE_DATE, expanseDate);
        values.put(EXPANSE_TIME, expanseTime);
        values.put(EXPENSE_DOCUMENT, image);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the expanse.
    public ArrayList<ExpanseModal> readExpanse() {
        // on below line we are creating a
        // database for reading our database.
        try {
            SQLiteDatabase db = this.getReadableDatabase();


            // on below line we are creating a cursor with query to read data from database.
            Cursor cursorExpanse = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC ", null);

            // on below line we are creating a new array list.
            ArrayList<ExpanseModal> expanseModalArrayList = new ArrayList<>();

            // moving our cursor to first position.
            if (cursorExpanse.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    expanseModalArrayList.add(new ExpanseModal(cursorExpanse.getInt(0),
                            cursorExpanse.getString(1),
                            cursorExpanse.getString(2),
                            cursorExpanse.getString(3),
                            cursorExpanse.getString(4),
                            cursorExpanse.getString(5)));
                } while (cursorExpanse.moveToNext());
                // moving our cursor to next.
            }
            // at last closing our cursor
            // and returning our array list.
            cursorExpanse.close();
            return expanseModalArrayList;
        } catch (Exception e) {
            return null;
        }
    }


    @SuppressLint("Range")
    public String fetch_amount(int id) {

        SQLiteDatabase sq = this.getReadableDatabase();

        String q = "SELECT " + EXPANSE_AMOUNT + " FROM " + TABLE_NAME + " WHERE " + ID_COL + " = " + id;

        Cursor c = sq.rawQuery(q, null);
        String s = "";

        c.moveToFirst();

        if (c.moveToFirst()) {
            s = c.getString(c.getColumnIndex(EXPANSE_AMOUNT + ""));
        }

        return s;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void delete_expense(int id) {
        getWritableDatabase().delete(TABLE_NAME, ID_COL + "=?", new String[]{String.valueOf(id)});

    }


    public void update_expense(int id, String expenseType, String expenseAmount, String expenseDate, String expenseTime) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_COL, id);
        contentValues.put(EXPANSE_TYPE, expenseType);
        contentValues.put(EXPANSE_AMOUNT, expenseAmount);
        contentValues.put(EXPANSE_DATE, expenseDate);
        contentValues.put(EXPANSE_TIME, expenseTime);
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_COL + "=?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }


    public Cursor calculateAllAmount() {
        SQLiteDatabase sq = getReadableDatabase();
        return sq.rawQuery("SELECT SUM(" + EXPANSE_AMOUNT + ") AS TOTALAMOUNT FROM " + TABLE_NAME, null);
    }

    public Cursor showAmount(String fromDate, String toDate) {
        SQLiteDatabase sq = getReadableDatabase();
        return sq.rawQuery("SELECT SUM(" + EXPANSE_AMOUNT + ") AS MYTOTAL FROM expanse_table WHERE date>=? and date<?",
                new String[]{fromDate, toDate});

    }


}
