package com.example.myapplicationj9;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cities_1 extends AppCompatActivity implements list_fragment.listItem,sightsAdapter.SightSelected{
    ActionBar actionBar;
    TextView tv_Detail;
    TextView tv_Name;
    ImageView showLess;
    ImageView ivRate5;
    ImageView ivRate4;
    ImageView ivRate3;
    ImageView ivRate2;
    ImageView ivRate1;
    Button btnOpenMap;
    ImageView iv_main_image;

    private int cityIndex;
    private int sightIndex;

    RecyclerView.Adapter msightsAdapter;
    RecyclerView.Adapter commentAdapter;
    RecyclerView recyclerViewComment;
    RecyclerView recyclerViewSight;
    RecyclerView.LayoutManager layoutManagerSight;
    RecyclerView.LayoutManager layoutManagerComment;
    RecyclerView.Adapter contentAdapter;
    RecyclerView recyclerViewContent;
    RecyclerView.LayoutManager layoutManagerContent;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_1);

        actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        FragmentManager manager= getSupportFragmentManager();
        manager.beginTransaction()
                .show(manager.findFragmentById(R.id.list_frag))
                .hide(manager.findFragmentById(R.id.detail_frag))
                .hide(manager.findFragmentById(R.id.sight_frag))
                .commit();

    }

    void setCitiesViews(){

        iv_main_image =findViewById(R.id.iv_city_image);
        btnOpenMap=findViewById(R.id.btn_take_me_there);
        showLess=findViewById(R.id.show_less);
        ivRate1=findViewById(R.id.star_1);
        ivRate2=findViewById(R.id.star_2);
        ivRate3=findViewById(R.id.star_3);
        ivRate4=findViewById(R.id.star_4);
        ivRate5=findViewById(R.id.star_5);
        tv_Name =findViewById(R.id.tv_city_name);
        tv_Detail =findViewById(R.id.tv_city_details);
        recyclerViewSight =findViewById(R.id.recycler_view);
        recyclerViewComment=findViewById(R.id.recycle_view_comment);
        layoutManagerComment = new LinearLayoutManager(this);

    }
    void setSightsViews(){
        iv_main_image =findViewById(R.id.iv_sight_image);
        btnOpenMap=findViewById(R.id.btn_take_me_there_sight);
        showLess=findViewById(R.id.show_less_sight);
        ivRate1=findViewById(R.id.star_1_sight);
        ivRate2=findViewById(R.id.star_2_sight);
        ivRate3=findViewById(R.id.star_3_sight);
        ivRate4=findViewById(R.id.star_4_sight);
        ivRate5=findViewById(R.id.star_5_sight);
        tv_Name =findViewById(R.id.tv_sight_name);
        tv_Detail =findViewById(R.id.tv_sight_details);
        recyclerViewContent=findViewById(R.id.recycler_view_sight);
        recyclerViewComment=findViewById(R.id.recycle_view_comment_sight);
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


    public void onSightSelectedItem(Sight sight) {
        sightIndex=list_fragment.cities.get(cityIndex).getSights().indexOf(sight);
        FragmentManager manager= getSupportFragmentManager();
        manager.beginTransaction()
                .hide(manager.findFragmentById(R.id.list_frag))
                .hide(manager.findFragmentById(R.id.detail_frag))
                .show(manager.findFragmentById(R.id.sight_frag))
                .addToBackStack("detail_frag")
                .commit();
        setSightsViews();
        setSightslistener();

        String name=sight.getName();
        tv_Name.setText(sight.getName());
        tv_Detail.setText(sight.getDetails());
        iv_main_image.setImageResource(sight.getImageSrcId());
        ArrayList<Comment> comments=sight.getComments();


        commentAdapter=new CommentAdapter(comments);
        layoutManagerComment= new LinearLayoutManager(this);
        recyclerViewComment.setLayoutManager(layoutManagerComment);
        recyclerViewComment.setHasFixedSize(true);
        recyclerViewComment.setAdapter(commentAdapter);

        contentAdapter=new ContentAdapter(this,sight.getContents());
        layoutManagerContent = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        recyclerViewContent.setHasFixedSize(true);
        recyclerViewContent.setLayoutManager(layoutManagerContent);
        recyclerViewContent.setAdapter(contentAdapter);

    }

    @Override
    public void onItemSelected(int index) {
        City currCity=list_fragment.cities.get(index);
        FragmentManager manager= getSupportFragmentManager();
        manager.beginTransaction()
                .hide(manager.findFragmentById(R.id.list_frag))
                .show(manager.findFragmentById(R.id.detail_frag))
                .hide(manager.findFragmentById(R.id.sight_frag))
                .addToBackStack(null)
                .commit();

        setCitiesViews();
        setCitieslistener();
        tv_Name.setText(currCity.getName());
        tv_Detail.setText(currCity.getDetails());
        iv_main_image.setImageResource(currCity.getImageSrcID());
        ArrayList<Comment> comments=currCity.getComments();


        commentAdapter=new CommentAdapter(comments);
        recyclerViewComment.setHasFixedSize(true);
        layoutManagerComment= new LinearLayoutManager(this);
        recyclerViewComment.setLayoutManager(layoutManagerComment);
        recyclerViewComment.setAdapter(commentAdapter);

        msightsAdapter=new sightsAdapter(this,currCity.getSights());
        layoutManagerSight= new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        recyclerViewSight.setHasFixedSize(true);
        recyclerViewSight.setLayoutManager(layoutManagerSight);
        recyclerViewSight.setAdapter(msightsAdapter);

    }


    void setCitieslistener()
    {
        iv_main_image.setOnClickListener(new View.OnClickListener() {
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
        tv_Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_Detail.setMaxLines(Integer.MAX_VALUE);
                showLess.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            }
        });
        showLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMax(tv_Detail)){
                    tv_Detail.setMaxLines(5);
                    showLess.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                else
                {
                    tv_Detail.setMaxLines(Integer.MAX_VALUE);
                    showLess.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
            }
        });
    }
    void setSightslistener()
    {
        iv_main_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SightName=list_fragment.cities.get(cityIndex).getSights().get(sightIndex).getName();
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("google.navigation:q="+SightName+",+Egypt"));
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
        tv_Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_Detail.setMaxLines(Integer.MAX_VALUE);
                showLess.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            }
        });
        showLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMax(tv_Detail)){
                    tv_Detail.setMaxLines(5);
                    showLess.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                else
                {
                    tv_Detail.setMaxLines(Integer.MAX_VALUE);
                    showLess.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
            }
        });
    }
}
