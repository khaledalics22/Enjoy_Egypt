package com.example.myapplicationj9;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Cities_1 extends AppCompatActivity implements list_fragment.listItem{
    ActionBar actionBar;

    TextView cityDetail;
    TextView cityName;
    ImageView showLess;
    ImageView ivRate5;
    ImageView ivRate4;
    ImageView ivRate3;
    ImageView ivRate2;
    ImageView ivRate1;
    Button btnOpenMap;
    private int cityIndex;
    ImageView cityImage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_1);

        actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        FragmentManager manager= getSupportFragmentManager();
        manager.beginTransaction()
                .hide(manager.findFragmentById(R.id.detail_frag))
                .show(manager.findFragmentById(R.id.list_frag))
                .commit();

        assignAllViews();
        setAllListener();
    }
    void assignAllViews(){
        cityImage=findViewById(R.id.iv_city_image);
        btnOpenMap=findViewById(R.id.btn_take_me_there);
        showLess=findViewById(R.id.show_less);
        ivRate1=findViewById(R.id.star_1);
        ivRate2=findViewById(R.id.star_2);
        ivRate3=findViewById(R.id.star_3);
        ivRate4=findViewById(R.id.star_4);
        ivRate5=findViewById(R.id.star_5);
        cityName=findViewById(R.id.tv_city_name);
        cityDetail=findViewById(R.id.tv_city_details);

    }
    @TargetApi(16)
    boolean isMax(TextView tv)
    {
        if (tv.getMaxLines()!=Integer.MAX_VALUE)
        {
            return false;
        }
        return true ;
    }
    @Override
    public void onItemSelected(int index) {
        FragmentManager manager= getSupportFragmentManager();
        manager.beginTransaction()
                .show(manager.findFragmentById(R.id.detail_frag))
                .hide(manager.findFragmentById(R.id.list_frag))
                .addToBackStack(null)
                .commit();
        cityIndex=index;
        City currCity=list_fragment.cities.get(index);
        cityName.setText(currCity.getName());
        cityDetail.setText(currCity.getDetails());
        cityImage.setImageResource(currCity.getImageSrcID());
        ArrayList<Comment> comments=currCity.getComments();
        ListView  listView= findViewById(R.id.lv_preview);
        CommentAdapter adapter=new CommentAdapter(this,comments);
        listView.setAdapter(adapter);
    }


    @Override
    public void showBackArrow() {
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }
    void setAllListener()
    {
        cityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String cityName=list_fragment.cities.get(cityIndex).getName();
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("google.navigation:q="+cityName+",+Egypt"));
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);

                }
            }
        });

        ivRate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ivRate1.setImageResource(R.drawable.star_yellow);
                ivRate2.setImageResource(R.drawable.star_holo);
                ivRate3.setImageResource(R.drawable.star_holo);
                ivRate4.setImageResource(R.drawable.star_holo);
                ivRate5.setImageResource(R.drawable.star_holo);
                list_fragment.cities.get(cityIndex).addRate(""+1);


            }
        });

        ivRate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ivRate1.setImageResource(R.drawable.star_yellow);
                ivRate2.setImageResource(R.drawable.star_yellow);
                ivRate3.setImageResource(R.drawable.star_holo);
                ivRate4.setImageResource(R.drawable.star_holo);
                ivRate5.setImageResource(R.drawable.star_holo);
                list_fragment.cities.get(cityIndex).addRate(""+2);

            }
        });

        ivRate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ivRate1.setImageResource(R.drawable.star_yellow);
                ivRate2.setImageResource(R.drawable.star_yellow);
                ivRate3.setImageResource(R.drawable.star_yellow);
                ivRate4.setImageResource(R.drawable.star_holo);
                ivRate5.setImageResource(R.drawable.star_holo);
                list_fragment.cities.get(cityIndex).addRate(""+3);

            }
        });

        ivRate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ivRate1.setImageResource(R.drawable.star_yellow);
                ivRate2.setImageResource(R.drawable.star_yellow);
                ivRate3.setImageResource(R.drawable.star_yellow);
                ivRate4.setImageResource(R.drawable.star_yellow);
                ivRate5.setImageResource(R.drawable.star_holo);
                list_fragment.cities.get(cityIndex).addRate(""+4);

            }
        });

        ivRate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ivRate1.setImageResource(R.drawable.star_yellow);
                ivRate2.setImageResource(R.drawable.star_yellow);
                ivRate3.setImageResource(R.drawable.star_yellow);
                ivRate4.setImageResource(R.drawable.star_yellow);
                ivRate5.setImageResource(R.drawable.star_yellow);
                list_fragment.cities.get(cityIndex).addRate(""+5);

            }
        });
        cityDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityDetail.setMaxLines(Integer.MAX_VALUE);
                showLess.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            }
        });
        showLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMax(cityDetail)){
                    cityDetail.setMaxLines(5);
                    showLess.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                else
                {
                    cityDetail.setMaxLines(Integer.MAX_VALUE);
                    showLess.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
            }
        });
    }
}
