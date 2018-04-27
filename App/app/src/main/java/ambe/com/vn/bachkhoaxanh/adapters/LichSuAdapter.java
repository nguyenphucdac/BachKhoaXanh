package ambe.com.vn.bachkhoaxanh.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.models.LichSuTuoiCay;

/**
 * Created by AMBE on 21/03/2018.
 */

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.ItemViewHolder> {

    private Context context;
    private ArrayList<LichSuTuoiCay> arrayList;
    private LayoutInflater inflater;

    public LichSuAdapter(Context context, ArrayList<LichSuTuoiCay> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_lich_su_tuoi_cay_cua_nv, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        LichSuTuoiCay ls = arrayList.get(position);
        holder.txtTenCay.setText(ls.getCayObject().getIdCay());
        long dateTime = Long.parseLong(ls.getThoiGian());
        Date date = new Date(dateTime);

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        holder.txtThoiGian.setText(dt.format(date));
        holder.txtLuongNuoc.setText(ls.getLuongNuocDaTuoi()+" Lít");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView txtLuongNuoc;
        private TextView txtThoiGian;
        private TextView txtTenCay;

        public ItemViewHolder(View itemView) {
            super(itemView);

            txtLuongNuoc = itemView.findViewById(R.id.txt_luong_nuoc);
            txtThoiGian = itemView.findViewById(R.id.txt_thoi_gian_tuoi);
            txtTenCay = itemView.findViewById(R.id.txt_ten_cay_tuoi);
        }
    }
}
