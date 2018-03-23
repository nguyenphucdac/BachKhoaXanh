package ambe.com.vn.bachkhoaxanh.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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
import java.util.List;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.adapters.LichSuAdapter;
import ambe.com.vn.bachkhoaxanh.models.LichSuTuoiCay;

/**
 * A simple {@link Fragment} subclass.
 */
public class LichSuFragment extends Fragment implements OnChartValueSelectedListener {


    private View view;
    private RecyclerView rcvLichSu;
    private ToggleButton toggleButton;
    private ArrayList<LichSuTuoiCay> arrLichSuTuoiCay;
    private LichSuAdapter lichSuAdapter;
    private CombinedChart mChart;



    public LichSuFragment() {
        // Required empty public constructor
    }


    public static LichSuFragment newInstance(){
        LichSuFragment lichSuFragment=new LichSuFragment();
        return lichSuFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_lich_su, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rcvLichSu = view.findViewById(R.id.rcv_lich_su);
        toggleButton = view.findViewById(R.id.tgb_thong_ke);
        mChart=view.findViewById(R.id.combine_chart);
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setOnChartValueSelectedListener(this);

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


        arrLichSuTuoiCay=new ArrayList<>();
        arrLichSuTuoiCay.add(new LichSuTuoiCay("00","CayXaCu_S01_00","900 ml","14:02 12/03/2018 GMT"));
        arrLichSuTuoiCay.add(new LichSuTuoiCay("00","CayXaCu_S01_00","900 ml","14:02 12/03/2018 GMT"));
        arrLichSuTuoiCay.add(new LichSuTuoiCay("00","CayXaCu_S01_00","900 ml","14:02 12/03/2018 GMT"));
        arrLichSuTuoiCay.add(new LichSuTuoiCay("00","CayXaCu_S01_00","900 ml","14:02 12/03/2018 GMT"));
        arrLichSuTuoiCay.add(new LichSuTuoiCay("00","CayXaCu_S01_00","900 ml","14:02 12/03/2018 GMT"));
        arrLichSuTuoiCay.add(new LichSuTuoiCay("00","CayXaCu_S01_00","900 ml","14:02 12/03/2018 GMT"));
        arrLichSuTuoiCay.add(new LichSuTuoiCay("00","CayXaCu_S01_00","900 ml","14:02 12/03/2018 GMT"));
        arrLichSuTuoiCay.add(new LichSuTuoiCay("00","CayXaCu_S01_00","900 ml","14:02 12/03/2018 GMT"));

        lichSuAdapter=new LichSuAdapter(getActivity(),arrLichSuTuoiCay);
        LinearLayoutManager ll=new LinearLayoutManager(getActivity());
        rcvLichSu.setLayoutManager(ll);
        rcvLichSu.setHasFixedSize(true);
        rcvLichSu.setAdapter(lichSuAdapter);


        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true){
                    rcvLichSu.setVisibility(View.GONE);
                    mChart.setVisibility(View.VISIBLE);
                } else {
                    rcvLichSu.setVisibility(View.VISIBLE);
                    mChart.setVisibility(View.GONE);
                }
            }
        });
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
        int[] data = new int[] { 1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 9 };

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

}
