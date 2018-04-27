package ambe.com.vn.bachkhoaxanh.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;


import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.map.MapView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    private View view;
    private MapView mapView;
    private FloatingActionButton fabSearch;
    private boolean show = true;
    private FloatingActionButton fabSearchTree;
    private FloatingActionButton fabSearchWater;


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
        mapView = view.findViewById(R.id.map_view);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fabSearch = view.findViewById(R.id.fab_search);
        fabSearchTree = view.findViewById(R.id.fab_search_tree);
        fabSearchWater = view.findViewById(R.id.fab_search_water);


        fabSearch.setOnClickListener(this);
        fabSearchWater.setOnClickListener(this);
        fabSearchTree.setOnClickListener(this);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fab_search:
                showAndHideFab();
                break;
            case R.id.fab_search_water:
                searchWater();
                break;
            case R.id.fab_search_tree:
                searchTree();
                break;

        }

    }

    private void searchTree() {
        hideFab();
        mapView.xuLyChiDuongTuListCay();

    }

    private void searchWater() {
        hideFab();
        mapView.xuLyChiDuongTuListDcn();
    }

    private void showAndHideFab() {

        if (show == false) {
            hideFab();
        } else {
            showFab();
        }

    }

    private void showFab() {
        fabSearchWater.show();
        fabSearchTree.show();
        show = false;

    }

    private void hideFab() {
        fabSearchTree.hide();
        fabSearchWater.hide();
        show = true;

    }
}
