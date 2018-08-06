package hfad.com.vocabulaire;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import hfad.com.data.MyMap;
import hfad.com.data.WordsDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Cursor cursor;
    private SQLiteDatabase db;




    public static MyMap premierList = new MyMap();

    public static List<String> wordBankTitles = new ArrayList<>();

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        premierList.nameList("premierList");
        premierList.put("Bonjure", "Hello");
        premierList.put("Sans", "Without");


        Button create = (Button) findViewById(R.id.create_list);
        create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showPopup();

            }

        });

        EditText editText = (EditText) findViewById(R.id.search_word);

        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                //createList(s.toString());
                showResult(s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do something before Text Change
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Do something while Text Change
            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            //createList(editText.getText().toString());

            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {



                if (i == EditorInfo.IME_NULL
                        && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

                    Intent intent = new Intent(MainActivity.this, ShowWords.class);
                    intent.putExtra("THE_WORD", textView.getText().toString());
                    startActivity(intent);


                    return true;

                }
                return true;
            }
        });


        ListView listView = (ListView) findViewById(R.id.list_search);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String str  = ((TextView) view).getText().toString();
                Intent intent = new Intent(MainActivity.this, ShowWords.class);
                intent.putExtra("THE_WORD", str);
                startActivity(intent);
        }
    });


        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.account:
                        Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                    case R.id.mycart:
                        Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();
                    default:
                        return true;
                }




            }
        });







    }


    private void showPopup() {
        try {

            LayoutInflater inflater = (LayoutInflater) MainActivity.this.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.pop_up, null);
            int density = (int) MainActivity.this.getResources().getDisplayMetrics().density;

            final PopupWindow pw = new PopupWindow(layout, (int) 300 * density, (int) 150 * density, true);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
            pw.setFocusable(true);
            pw.update();
            Button Close = (Button) layout.findViewById(R.id.close_popup);
            Close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pw.dismiss();

                }

            });


            Button create_list = (Button) layout.findViewById(R.id.ok);
            EditText text = (EditText) layout.findViewById(R.id.set_title);

            create_list.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    pw.dismiss();

                    EditText text = (EditText) layout.findViewById(R.id.set_title);
                    String title = text.getText().toString();
                    wordBankTitles.add(title);

                    Intent intent = new Intent(MainActivity.this, ShowWords.class);

                    intent.putExtra(ShowWords.TITLE_ID, title);

                    startActivity(intent);

                }

            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void createList(String theWord) {
    ListView listView = (ListView) findViewById(R.id.list_search);

        ArrayList<String> listItems = new ArrayList<String>();


        for(int i=0; i<premierList.size(); i++){

           if (doMatch(theWord, (String) premierList.getKey(i)))

               listItems.add((String) premierList.getKey(i));

       }


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);

    }



    private boolean doMatch(String first, String second){

        char[] first2  = first.toLowerCase().toCharArray();
        char[] second2 = second.toLowerCase().toCharArray();

        if (first.length() == 0)
            return false;


        for(int i = 0; i < first.length(); i++)
        {
            if (first2[i] != second2[i])
            {
                return false;
            }
        }

        return true;


    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }



    private void showResult( String word) {




        ListView listView = (ListView) findViewById(R.id.list_search);

        ArrayList<String> listItems = new ArrayList<String>();

        SQLiteOpenHelper wordsDatabaseHelper = new WordsDatabaseHelper(this);


        db = wordsDatabaseHelper.getReadableDatabase();




        cursor = db.query("WORDS",new String[]{"_id, NAME"}, null,null,
                null,null,null);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();}
        else{
            while(cursor.moveToNext()){
                if (doMatch(word, cursor.getString(1)))
                 listItems.add(cursor.getString(1));

            }

        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);

    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        cursor.close();



    }


}