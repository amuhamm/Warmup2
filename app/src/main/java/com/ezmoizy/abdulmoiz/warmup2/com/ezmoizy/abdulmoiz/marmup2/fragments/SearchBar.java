package com.ezmoizy.abdulmoiz.warmup2.com.ezmoizy.abdulmoiz.marmup2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.ezmoizy.abdulmoiz.warmup2.R;

/**
 * Created by Moiz on 25/03/2016.
 */
public class SearchBar extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_bar, container, false);


        // Get a reference to the AutoCompleteTextView
        AutoCompleteTextView textView = (AutoCompleteTextView) view.findViewById(R.id.autocomplete);

        // Create an ArrayAdapter containing country names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.list_item,BUILDINGS);


        // Set the adapter for the AutoCompleteTextView
        textView.setAdapter(adapter);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // Display a Toast Message when the user clicks on an item in the AutoCompleteTextView
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Toast.makeText(getActivity().getApplicationContext(), "Item selected: " + arg0.getAdapter().getItem(arg2), Toast.LENGTH_SHORT).show();
                hideSoftKeyboard(getActivity());
            }
        });

        return view;
    }

    static final String[] BUILDINGS = new String[] { "Alumni Memorial Building",
            "AN Bourns Science Building ABB",
            "Burke Science Building BSB",
            "Chester New Hall CNH",
            "Commons Building",
            "Communications Research Library",
            "David Braley Athletics Centre DBAC",
            "Degroote School Of Business",
            "ET Clarke Centre",
            "General Sciences",
            "Gilmour Hall GH",
            "Hamilton Hall HH",
            "HG Thode Library",
            "Information Technology Building ITB",
            "Institute For Applied Health Sciences IAHS",
            "Ivor Wynne Centre",
            "John Hodgins Building JHE",
            "Kenneth Taylor Hall KTH",
            "Life Sciences Building",
            "McMaster University Student Centre MUSC",
            "Michael DeGroote Centre for Learning and Discovery MDCL",
            "Mills Library",
            "Museum Of Art",
            "Nuclear Research Building",
            "Psychology Building",
            "Refectory",
            "Ron Joyce Stadium",
            "T13",
            "T28",
            "T29",
            "Tandem Accelerator",
            "Togo Salmon Hall TSH",
            "University Hall" };

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

}
