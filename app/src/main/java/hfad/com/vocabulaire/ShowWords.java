package hfad.com.vocabulaire;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class ShowWords extends AppCompatActivity {
    public static final String TITLE_ID = "currtitle";
    private static String title;
    public static String theWord;
    private Cursor cursor;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        theWord = intent.getStringExtra("THE_WORD");

        setContentView(R.layout.activity_show_words);

         Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);


        //title = intent.getStringExtra(TITLE_ID);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_word, menu);
        return true;
    }*/



    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()) {

            default:
                return true;


        }

    }




}
