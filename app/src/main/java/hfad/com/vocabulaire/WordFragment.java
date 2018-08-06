package hfad.com.vocabulaire;


import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordFragment extends Fragment {






    public WordFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment




        String word = ShowWords.theWord;
        String definition = (String) MainActivity.premierList.get(word);



        View view = inflater.inflate(R.layout.fragment_word, container, false);

        TextView defView = (TextView)view.findViewById(R.id.definition);

        TextView wordView = (TextView)view.findViewById(R.id.the_word);
        defView.setText(definition);
        wordView.setText(word);
        setHasOptionsMenu(true);



        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater ) {
        inflater.inflate(R.menu.save_word, menu);
    }




}
