package com.ezmoizy.abdulmoiz.warmup2.com.ezmoizy.abdulmoiz.marmup2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ezmoizy.abdulmoiz.warmup2.R;

/**
 * Created by Moiz on 18/03/2016.
 */

public class BuildingsIndexFragment extends Fragment {

    ListView buildingsList;
    int[] toViews = {android.R.id.text1};
    private static String[] buildings = {"Alumni Memorial Building",
            "AN Bourns Science Building (ABB)",
            "Burke Science Building (BSB)",
            "Chester New Hall (CNH)",
            "Commons Building",
            "Communications Research Library",
            "David Braley Athletics Centre (DBAC)",
            "Degroote School Of Business",
            "ET Clarke Centre",
            "General Sciences",
            "Gilmour Hall (GH)",
            "Hamilton Hall (HH)",
            "HG Thode Library",
            "Information Technology Building (ITB)",
            "Institute For Applied Health Sciences (IAHS)",
            "Ivor Wynne Centre",
            "John Hodgins Building (JHE)",
            "Kenneth Taylor Hall (KTH)",
            "Life Sciences Building",
            "McMaster University Student Centre (MUSC)",
            "Michael DeGroote Centre for Learning and Discovery (MDCL)",
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
            "Togo Salmon Hall (TSH)",
            "University Hall"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.buildings_list, container, false);
        buildingsList = (ListView) view.findViewById(R.id.buildingsList);
        populateListView();
        return view;
    }

    private void populateListView(){

        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, buildings);

        //configure the list view.
        buildingsList.setAdapter(adapter);
    }

}
