package ambe.com.vn.bachkhoaxanh.map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.activities.MainActivity;
import ambe.com.vn.bachkhoaxanh.models.Cay;
import ambe.com.vn.bachkhoaxanh.models.DiemCapNuoc;
import ambe.com.vn.bachkhoaxanh.models.ThanhVien;

/**
 * Created by AMBE on 18/03/2018.
 */

public class MapView extends View {
    private int x, y, bit;
    private ThanhVien thanhVien;

    private Paint p;
    private ArrayList<Bitmap> arrBitMap;
    private ArrayList<Cay> arrCay;
    private ArrayList<DiemCapNuoc> arrDiemCapNuoc;

    public MapView(Context context, int x, int y, int bit) {
        super(context);
        this.x = x;
        this.y = y;
        this.bit = bit;


    }


    public MapView(Context context) {
        super(context);




    }

    public MapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        arrBitMap = new ArrayList<>();
        arrBitMap.add(BitmapFactory.decodeResource(getResources(), R.drawable.brick));
        arrBitMap.add(BitmapFactory.decodeResource(getResources(), R.drawable.tree));
        arrBitMap.add(BitmapFactory.decodeResource(getResources(), R.drawable.rock));
        arrBitMap.add(BitmapFactory.decodeResource(getResources(), R.drawable.water));
        thanhVien=new ThanhVien("NV01","NV@Gmail.com","123","DuongTheCuong","NV", MainActivity.widthScreen/2,(MainActivity.heightScreen/4), BitmapFactory.decodeResource(getResources(),R.drawable.user));
//
//        arrCay=new ArrayList<>();
//        arrCay.add(new Cay(BitmapFactory.decodeResource(getResources(),R.drawable.user),100,100));
//        arrCay.add(new Cay(BitmapFactory.decodeResource(getResources(),R.drawable.user),100,100));
//        arrCay.add(new Cay(BitmapFactory.decodeResource(getResources(),R.drawable.user),150,150));

        arrDiemCapNuoc=new ArrayList<>();
        arrDiemCapNuoc.add(new DiemCapNuoc("DCN_01",49,49,"10 Lit","Còn nước"));

        p = new Paint();
        p.setColor(Color.RED);

        for (DiemCapNuoc diemCapNuoc : arrDiemCapNuoc){
            diemCapNuoc.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.water));
        }

//        for (Cay cay : arrCay){
//            canvas.drawBitmap(cay.getBitmap(),cay.getToaDoX(),cay.getToaDoY(),p);
//        }

        canvas.drawBitmap(thanhVien.getBitmap(),thanhVien.getToaDoX(),thanhVien.getToaDoY(),p);

        // map cho xiaomi redmi 5plus
        int[][] map = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0,0},
                {0, 4, 4, 4, 4, 4, 4, 0, 0, 4, 4, 4, 4, 0, 0, 4, 4, 4, 4, 4, 4,0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,0},
                {0, 4, 2, 2, 2, 2, 4, 2, 4, 4, 2, 2, 4, 4, 2, 2, 2, 2, 2, 4, 4,0},
                {0, 4, 4, 4, 4, 2, 4, 2, 4, 4, 0, 0, 4, 4, 2, 4, 4, 4, 2, 4, 4,0},
                {0, 4, 4, 1, 4, 2, 4, 2, 4, 4, 0, 0, 4, 4, 2, 4, 3, 4, 2, 4, 4,0},
                {0, 4, 4, 4, 4, 2, 4, 2, 4, 4, 0, 0, 4, 4, 2, 4, 4, 4, 2, 4, 4,0},
                {0, 4, 4, 1, 4, 2, 4, 2, 4, 4, 0, 0, 4, 4, 2, 4, 4, 4, 2, 4, 4,0},
                {0, 4, 4, 4, 4, 2, 4, 2, 4, 4, 0, 0, 4, 4, 2, 4, 1, 4, 2, 4, 4,0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,0},
                {0, 4, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2,0},
                {0, 4, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2,0},
                {0, 4, 4, 2, 4, 4, 2, 4, 4, 4, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 3,0},
                {0, 4, 4, 2, 4, 4, 2, 4, 4, 4, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 3,0},
                {0, 4, 4, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2,0},
                {0, 4, 4, 2, 4, 4, 2, 4, 4, 4, 0, 0, 4, 4, 2, 2, 2, 2, 2, 2, 2,0},
                {0, 4, 4, 2, 4, 4, 2, 4, 4, 4, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4,0},
                {0, 4, 2, 2, 2, 2, 2, 2, 4, 4, 0, 0, 4, 4, 2, 2, 2, 2, 2, 4, 4,0},
                {0, 4, 2, 2, 2, 2, 2, 2, 4, 4, 0, 0, 4, 4, 4, 4, 4, 4, 2, 4, 4,0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 4, 4, 4, 1, 1, 4, 2, 4, 4,0},
                {0, 4, 1, 4, 1, 4, 1, 4, 4, 4, 0, 0, 4, 4, 4, 1, 1, 4, 2, 4, 4,0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 4, 4, 4, 1, 1, 4, 2, 4, 4,0},
                {0, 4, 2, 2, 2, 4, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4,0},
                {0, 4, 2, 2, 2, 4, 2, 2, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 4, 4,0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,0},
                {0, 4, 2, 2, 2, 4, 2, 2, 4, 4, 1, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4,0},
                {0, 4, 2, 2, 2, 4, 2, 2, 4, 4, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4,0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 4, 4, 4, 4, 2, 4, 4, 4, 4,0},
                {0, 4, 2, 2, 2, 4, 2, 2, 4, 4, 0, 0, 4, 4, 4, 4, 2, 4, 4, 4, 4,0},
                {0, 4, 2, 2, 2, 4, 2, 2, 4, 4, 0, 0, 4, 4, 1, 4, 2, 4, 1, 4, 4,0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 4, 4, 4, 4, 2, 4, 4, 4, 4,0},
                {0, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4, 3, 3,0},
                {0, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3,0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0,0},
        };
// i =26==34

        for (int i = 0; i < 34; i++) {
            for (int j = 0; j < 22; j++) {
                int x = j * 49;
                int y = i * 49;
                int bit = map[i][j];
                if (bit != 4) {

                    canvas.drawBitmap(arrBitMap.get(bit), x, y, p);
                }


            }
        }

        for (DiemCapNuoc diemCapNuoc:arrDiemCapNuoc){
            canvas.drawBitmap(diemCapNuoc.getBitmap(),diemCapNuoc.getToaDoX(),diemCapNuoc.getToaDoY(),p);
        }

 //map cho may ao


//        int[][] map = {
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0,0},
//                {0, 3, 3, 4, 4, 4, 4, 0, 0, 4, 4, 4, 4, 0, 0, 4, 4, 4, 4, 4, 4,0},
//                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,0},
//                {0, 4, 2, 2, 2, 2, 4, 2, 4, 4, 2, 2, 4, 4, 2, 2, 2, 2, 2, 4, 4,0},
//                {0, 4, 4, 4, 4, 2, 4, 2, 4, 4, 0, 0, 4, 4, 2, 4, 4, 4, 2, 4, 4,0},
//                {0, 4, 4, 1, 4, 2, 4, 2, 4, 4, 0, 0, 4, 4, 2, 4, 3, 4, 2, 4, 4,0},
//                {0, 4, 4, 4, 4, 2, 4, 2, 4, 4, 0, 0, 4, 4, 2, 4, 4, 4, 2, 4, 4,0},
//                {0, 4, 4, 1, 4, 2, 4, 2, 4, 4, 0, 0, 4, 4, 2, 4, 4, 4, 2, 4, 4,0},
//                {0, 4, 4, 4, 4, 2, 4, 2, 4, 4, 0, 0, 4, 4, 2, 4, 1, 4, 2, 4, 4,0},
//                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,0},
//                {0, 4, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 4, 4,0},
//                {0, 4, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4,0},
//                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 4, 2, 4, 4,0},
//                {0, 4, 4, 1, 4, 1, 4, 1, 4, 4, 3, 3, 4, 4, 4, 1, 1, 4, 2, 4, 4,0},
//                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4,0},
//                {0, 4, 2, 2, 2, 4, 2, 2, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 4, 4,0},
//                {0, 4, 2, 2, 2, 4, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,0},
//                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4,0},
//                {0, 4, 4, 1, 4, 1, 4, 4, 4, 4, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4,0},
//                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 4, 4, 4, 4, 2, 4, 4, 4, 4,0},
//                {0, 4, 2, 2, 4, 2, 2, 2, 4, 4, 0, 0, 4, 4, 4, 4, 2, 4, 4, 4, 4,0},
//                {0, 4, 2, 2, 4, 2, 2, 2, 4, 4, 0, 0, 4, 4, 1, 4, 2, 4, 1, 4, 4,0},
//                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 4, 4, 4, 4, 2, 4, 4, 4, 4,0},
//                {0, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4,0},
//                {0, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3,0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0,0},
//        };
//
//
//
//            for (int i = 0; i < 26; i++) {
//                for (int j = 0; j < 22; j++) {
//                    int x = j * 35;
//                    int y = i * 35;
//                    int bit = map[i][j];
//                    if (bit != 4) {
//
//                        canvas.drawBitmap(arrBitMap.get(bit), x, y, p);
//                    }
//
//
//            }
//        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        float a= thanhVien.getToaDoX();
        float b= thanhVien.getToaDoY();

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //Check if the x and y position of the touch is inside the bitmap
                if( x < a +48 && x > a  && y < b +48 && y > b)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Dương Thế Cường");
                    builder.create().show();
                }
                if ( x > 49 && x < 49 +40 && y > 49 && y < 49+40){

                    AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                    builder.setTitle("Điểm cấp nước: " + arrDiemCapNuoc.get(0).getIdDiemCapNuoc());
                    builder.setMessage(" Tình trạng: " + arrDiemCapNuoc.get(0).getTinhTrang() +"\n"+" Lượng nước tối đa: "+arrDiemCapNuoc.get(0).getLuongNuocToiDa());
                    builder.setPositiveButton("Chỉ đường", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // chỉ đường đến
                            Toast.makeText(getContext(), "Chưa làm", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();;
                        }
                    });
                    builder.create().show();
                }
                return true;
        }

        return false;
    }
}
