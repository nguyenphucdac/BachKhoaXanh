package ambe.com.vn.bachkhoaxanh.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import ambe.com.vn.bachkhoaxanh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaNhanFragment extends Fragment {

    private View view;
    private ImageView imgAvatar;
    private TextView txtTenNv;
    private TextView txtId;
    private TextView txtAcc;
    private EditText editPass;
    private ImageView imgShowPass;
    private int show=1;


    public CaNhanFragment() {
        // Required empty public constructor
    }


    public static CaNhanFragment newInstance(){
        CaNhanFragment caNhanFragment=new CaNhanFragment();
        return caNhanFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtTenNv=view.findViewById(R.id.txt_ten_nv);
        imgAvatar=view.findViewById(R.id.img_avatar_nv);
        txtId=view.findViewById(R.id.txt_ma_nv);
        txtAcc=view.findViewById(R.id.txt_tai_khoan);
        editPass=view.findViewById(R.id.edit_pass);
        imgShowPass=view.findViewById(R.id.img_show_pass);



        imgShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (show == 1) {
                    show = 0;
                    imgShowPass.setImageResource(R.drawable.show);
                    editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else if (show == 0){
                    show =1;
                    imgShowPass.setImageResource(R.drawable.hide);
                    editPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
            }
        });

    }
}
