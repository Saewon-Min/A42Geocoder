package com.kosmo.a42geocoder;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "KOSMO";


    Geocoder geocoder;
    TextView tvResult;
    EditText etLatitude;
    EditText etLongitude;
    EditText etAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Geocoder 객체 생성
        geocoder = new Geocoder(this);
        tvResult = findViewById(R.id.result);
        etLatitude = findViewById(R.id.lattitude);
        etLongitude = findViewById(R.id.longitude);
        etAddress = findViewById(R.id.adress);


    }

    // 위.경도 가져와서 주소로 변환하기
    public void onBtn1Clicked(View v){

        List<Address> list = null;
        // 입력 상자의 위.경도 값을 가져옴
        String latitude = etLatitude.getText().toString();
        String longitude = etLongitude.getText().toString();

        try{
            // 위.경도를 매개변수로 주소 얻어오기
            list = geocoder.getFromLocation(
                    Double.parseDouble(latitude),
                    Double.parseDouble(longitude),
                    10); // 위.경도는 Double형으로 Boxing 처리 후 전달함

        }catch (NumberFormatException e){
            e.printStackTrace();
        }catch (IOException e){
            tvResult.setText("에러 : "+e.getMessage());
            e.printStackTrace();
        }

        // 결과값이 있다면 텍스트뷰에 설정한다.
        if(list != null){
            tvResult.setText(list.get(0).toString());
        }

    }

    // 주소를 위.경도로 변환
    public void onBtn2Clicked(View v){

        List<Address> list = null;
        String address = etAddress.getText().toString();

        try{
            // 주소를 매개변수로 위.경도를 얻어온다.
            list = geocoder.getFromLocationName(address, 10);

        }catch (IOException e){
            tvResult.setText("에러 : "+e.getMessage());
            e.printStackTrace();
        }

        if(list != null){
            tvResult.setText(list.get(0).toString());
        }

    }


}