package com.meizi.haokan.online;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meizi.haokan.R;

/**
     * A placeholder fragment containing a simple view.
     */
    public  class OnlineViewlistFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public OnlineViewlistFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static OnlineViewlistFragment newInstance(int sectionNumber) {
            OnlineViewlistFragment fragment = new OnlineViewlistFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_online_web, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }



    }