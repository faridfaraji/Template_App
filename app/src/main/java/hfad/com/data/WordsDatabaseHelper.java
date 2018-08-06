package hfad.com.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

/**
 * Created by Farid on 2018-02-24.
 */
public class WordsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "wordBank";
    public static final String TABLE_NAME = "WORDS";
    private static final int DB_VERSION = 1;


    public WordsDatabaseHelper(Context context) {
        super(context, DB_NAME,  null,  DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        updateMyDatabase(sqLiteDatabase, 1, DB_VERSION);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {


        updateMyDatabase(sqLiteDatabase,  oldVersion,  newVersion);

    }


    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){


        updateMyDatabase(sqLiteDatabase,  oldVersion,  newVersion);



    }




    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){

        if(oldVersion <2){
            db.execSQL("CREATE TABLE WORDS ("+
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + " NAME TEXT,"
                    +" DESCRIPTION TEXT, "
                    +" SEARCHDWORD INTEGER);");

            insertWord(db, "Bonjure", "Hello" );
            insertWord(db, "surtout", "most of all");
            insertWord(db, "Sans", "without" );
            insertWord(db, "Maison", "house" );
            insertWord(db, "Eau", "water" );
            insertWord(db, "Ca", "this" );


        }




    }


    public static void insertWord(SQLiteDatabase db,String name, String description){

        ContentValues words = new ContentValues();

        words.put("NAME", name);
        words.put("DESCRIPTION", description);
        words.put("SEARCHDWORD", 0);
        db.insert("WORDS",null, words);

    }

    public Cursor getListContent(SQLiteDatabase db){

        return db.query("WORDS",new String[]{"_id, NAME"}, null,null,
                null,null,null);

    }



    private class UpdateWordsTask extends AsyncTask<Integer, Void, Boolean>{


        @Override
        protected void onPreExecute(){
        }


        @Override
        protected Boolean doInBackground(Integer... integers) {
            return null;
        }

        @Override
        protected void onPostExecute(Boolean success){


        }



    }

}
