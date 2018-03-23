package ambe.com.vn.bachkhoaxanh.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import java.util.ArrayList;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.adapters.LichSuAdapter;
import ambe.com.vn.bachkhoaxanh.models.LichSuTuoiCay;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);


        return view;
    }


}
