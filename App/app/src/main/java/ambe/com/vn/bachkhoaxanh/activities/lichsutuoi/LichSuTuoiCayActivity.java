package ambe.com.vn.bachkhoaxanh.activities.lichsutuoi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.adapters.LichSuTuoiCayAdapter;
import ambe.com.vn.bachkhoaxanh.models.LichSuTuoiCay;
import ambe.com.vn.bachkhoaxanh.presenters.lichsutuoi.LichSuTuoiCayPresenter;
import ambe.com.vn.bachkhoaxanh.utils.Api;

public class LichSuTuoiCayActivity extends AppCompatActivity implements ILichSuTuoiCayView {

    private String idCay;
    private Toolbar toolbar;
    private RecyclerView rcvLichSuTuoiCay;
    private LichSuTuoiCayAdapter lichSuTuoiCayAdapter;
    private LichSuTuoiCayPresenter lichSuTuoiCayPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_tuoi);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {

        toolbar = findViewById(R.id.tb_lich_su_tuoi_cay);
        rcvLichSuTuoiCay = findViewById(R.id.rcv_lich_su_tuoi);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        idCay = bundle.getString("IDCAY");
        getSupportActionBar().setTitle(idCay);


        LinearLayoutManager ln = new LinearLayoutManager(this);
        rcvLichSuTuoiCay.setLayoutManager(ln);
        lichSuTuoiCayPresenter = new LichSuTuoiCayPresenter(this);

        lichSuTuoiCayPresenter.getLichSuTuoiCuaCay(getApplicationContext(), idCay, Api.apiGetLichSuTuoiCuaCay);


    }

    @Override
    public void showListLichSuTuoi(ArrayList<LichSuTuoiCay> arrayList) {
        lichSuTuoiCayAdapter = new LichSuTuoiCayAdapter(getApplicationContext(), arrayList);
        rcvLichSuTuoiCay.setAdapter(lichSuTuoiCayAdapter);
        lichSuTuoiCayAdapter.notifyDataSetChanged();

    }

    @Override
    public void showListLichSuTuoiFail(String mess) {

        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();

    }
}
