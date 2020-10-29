package cg.mokano.bzv.covid.views.frags;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cg.mokano.bzv.covid.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragApercu extends Fragment {


    public FragApercu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_apercu, container, false);
    }

}
