package ambe.com.vn.bachkhoaxanh.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.activities.lichsutuoi.ILichSuTuoiCayView;
import ambe.com.vn.bachkhoaxanh.activities.login.LoginActivity;
import ambe.com.vn.bachkhoaxanh.activities.main.MainActivity;
import ambe.com.vn.bachkhoaxanh.adapters.LichSuAdapter;
import ambe.com.vn.bachkhoaxanh.models.LichSuTuoiCay;
import ambe.com.vn.bachkhoaxanh.presenters.lichsutuoi.LichSuTuoiCayPresenter;
import ambe.com.vn.bachkhoaxanh.utils.Api;

/**
 * A simple {@link Fragment} subclass.
 */
public class LichSuFragment extends Fragment implements OnChartValueSelectedListener, View.OnClickListener, ILichSuTuoiCayView {


    private View view;
    private RecyclerView rcvLichSu;
    private ToggleButton toggleButton;
    private LichSuAdapter lichSuAdapter;
    private CombinedChart mChart;
    private LinearLayout lnlLichSuTnv;
    private LinearLayout lnlLichSuTv;
    public static int[] arrThang;
    public static int[] data = new int[12];
    private Button btnDangNhapTnv;
    private LichSuTuoiCayPresenter lichSuTuoiCayPresenter;


    public LichSuFragment() {
        // Required empty public constructor
    }


    public static LichSuFragment newInstance() {
        LichSuFragment lichSuFragment = new LichSuFragment();
        return lichSuFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lich_su, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rcvLichSu = view.findViewById(R.id.rcv_lich_su);
        toggleButton = view.findViewById(R.id.tgb_thong_ke);
        lnlLichSuTnv = view.findViewById(R.id.lnl_tnv_lich_su);
        lnlLichSuTv = view.findViewById(R.id.lnl_tv_lich_su);
        btnDangNhapTnv = view.findViewById(R.id.btn_tnv_lich_su_dang_nhap);
        mChart = view.findViewById(R.id.combine_chart);
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setOnChartValueSelectedListener(this);


        if (MainActivity.TNV.equals("TNV")) {
            lnlLichSuTnv.setVisibility(View.VISIBLE);
            lnlLichSuTv.setVisibility(View.GONE);
        } else {
            lnlLichSuTv.setVisibility(View.VISIBLE);
            lnlLichSuTnv.setVisibility(View.GONE);
            lichSuTuoiCayPresenter = new LichSuTuoiCayPresenter(this);
            lichSuTuoiCayPresenter.getLichSuTuoiCuaCay(getContext(), MainActivity.THANH_VIEN.getIdThanhVien(), Api.apiGetLichSuTuoiCuaNv);

        }




        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    rcvLichSu.setVisibility(View.GONE);
                    mChart.setVisibility(View.VISIBLE);
                } else {
                    rcvLichSu.setVisibility(View.VISIBLE);
                    mChart.setVisibility(View.GONE);
                }
            }
        });

        btnDangNhapTnv.setOnClickListener(this);
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(getActivity(), "Value: "
                + e.getY()
                + ", index: "
                + h.getX()
                + ", DataSet index: "
                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

    private static DataSet dataChart() {

        LineData d = new LineData();




        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < 12; index++) {
            entries.add(new Entry(index, data[index]));
        }


        LineDataSet set = new LineDataSet(entries, "Request Ots approved");
        set.setColor(Color.CYAN);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.GREEN);
        set.setCircleRadius(5f);
        set.setFillColor(Color.GREEN);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.GREEN);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tnv_lich_su_dang_nhap:
                xuLyDangNhap();
                break;
        }

    }

    private void xuLyDangNhap() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showListLichSuTuoi(ArrayList<LichSuTuoiCay> arrayList) {
        lichSuAdapter = new LichSuAdapter(getActivity(), arrayList);
        LinearLayoutManager ll = new LinearLayoutManager(getActivity());
        rcvLichSu.setLayoutManager(ll);
        rcvLichSu.setHasFixedSize(true);
        rcvLichSu.setAdapter(lichSuAdapter);

        arrThang = new int[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            long dateTime = Long.parseLong(arrayList.get(i).getThoiGian());
            Date date = new Date(dateTime);
            arrThang[i] = ((date.getMonth()));

        }

        Arrays.sort(arrThang);



        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 12; i++) {
                    data[i] = 0;
                }
                for (int i = 0; i < arrThang.length; i++) {

                    data[arrThang[i]]++;

                }

                YAxis rightAxis = mChart.getAxisRight();
                rightAxis.setDrawGridLines(false);
                rightAxis.setAxisMinimum(0f);

                YAxis leftAxis = mChart.getAxisLeft();
                leftAxis.setDrawGridLines(false);
                leftAxis.setAxisMinimum(0f);

                final List<String> xLabel = new ArrayList<>();
                xLabel.add("Jan");
                xLabel.add("Feb");
                xLabel.add("Mar");
                xLabel.add("Apr");
                xLabel.add("May");
                xLabel.add("Jun");
                xLabel.add("Jul");
                xLabel.add("Aug");
                xLabel.add("Sep");
                xLabel.add("Oct");
                xLabel.add("Nov");
                xLabel.add("Dec");

                XAxis xAxis = mChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setAxisMinimum(0f);
                xAxis.setGranularity(1f);
                xAxis.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return xLabel.get((int) value % xLabel.size());
                    }
                });

                CombinedData data = new CombinedData();
                LineData lineDatas = new LineData();
                lineDatas.addDataSet((ILineDataSet) dataChart());

                data.setData(lineDatas);

                xAxis.setAxisMaximum(data.getXMax() + 0.25f);

                mChart.setData(data);
                mChart.invalidate();

            }
        }).start();





    }

    @Override
    public void showListLichSuTuoiFail(String mess) {

        Toast.makeText(getActivity(), "Chưa tưới cây nào =))) best of lười", Toast.LENGTH_SHORT).show();

    }
}
