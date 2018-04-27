package ambe.com.vn.bachkhoaxanh.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.models.LichSuTuoiCay;
import ambe.com.vn.bachkhoaxanh.utils.Api;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AMBE on 10/04/2018.
 */

public class LichSuTuoiCayAdapter extends RecyclerView.Adapter<LichSuTuoiCayAdapter.ItemViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<LichSuTuoiCay> arrayList;

    public LichSuTuoiCayAdapter(Context context, ArrayList<LichSuTuoiCay> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_lich_su_tuoi_cua_cay, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        LichSuTuoiCay lichSuTuoiCay = arrayList.get(position);
        long dateTime = Long.parseLong(lichSuTuoiCay.getThoiGian());
        Date date = new Date(dateTime);

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        holder.txtThoiGian.setText(dt.format(date));
        holder.txtTenNv.setText(lichSuTuoiCay.getThanhVienObject().getTenTaiKhoan());
        holder.txtLuongNuoc.setText(lichSuTuoiCay.getLuongNuocDaTuoi() + " Lít");
        String urlAvatar = "http://" + Api.ip + ":9999" + lichSuTuoiCay.getThanhVienObject().getAnhThanhVien();
        Picasso.with(context).load(urlAvatar).error(R.drawable.unknown_user).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imageView;
        private TextView txtTenNv;
        private TextView txtLuongNuoc;
        private TextView txtThoiGian;

        public ItemViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_item_ls_anh_nv);
            txtTenNv = itemView.findViewById(R.id.txt_item_ls_ten_tv);
            txtLuongNuoc = itemView.findViewById(R.id.txt_item_ls_luong_nuoc);
            txtThoiGian = itemView.findViewById(R.id.txt_item_ls_thoi_gian);
        }
    }
}
