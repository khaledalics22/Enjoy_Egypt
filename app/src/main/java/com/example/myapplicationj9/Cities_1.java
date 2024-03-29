package com.example.myapplicationj9;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class Cities_1 extends AppCompatActivity implements list_fragment.listItem,sightsAdapter.SightSelected,sight_frag.stopVideoInterface{
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
    TextView tv_distanceCity;
    ImageView iv_main_image;
    TextView tv_openCityOfSight;
    ImageView ivPlayVideo;
    public static Sight currSight =null;
    public static int currCityIndex=-1;

    private int cityIndex;
    private int sightIndex;

    RecyclerView.Adapter msightsAdapter;
    RecyclerView recyclerViewSight;
    RecyclerView.LayoutManager layoutManagerSight;

    RecyclerView recyclerViewComment;
    RecyclerView.Adapter commentAdapter;
    RecyclerView.LayoutManager layoutManagerComment;

    RecyclerView.Adapter contentAdapter;
    RecyclerView recyclerViewContent;
    RecyclerView.LayoutManager layoutManagerContent;

    RecyclerView.Adapter videoAdapter;
    RecyclerView recyclerViewVideo;
    RecyclerView.LayoutManager layoutManagerVideo;
    VideoView videoView;
    public MediaController m;

    public void playVideo(String path) {
        if(path==null)return;

        if(videoView!=null&&videoView.isPlaying())
        {
        }
        else if(videoView!=null&&!videoView.isPlaying())
        {
            videoView.start();
        }
        else {
            videoView=findViewById(R.id.video_sight);
            videoView.setVideoURI(Uri.parse(path));
            videoView.requestFocus();
            videoView.start();
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    ivPlayVideo.setVisibility(View.VISIBLE);
                    videoView.clearFocus();
                }
            });
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
                            m= new MediaController(Cities_1.this);
                            videoView.setMediaController(m);
                            m.setAnchorView(videoView);
                        }
                    });
                }
            });

        }
    }
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (float) (earthRadius * c/1000); //in km

        return dist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_1);

        double theta=3.5;

        actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        FragmentManager manager= getSupportFragmentManager();
        manager.beginTransaction()
                .show(manager.findFragmentById(R.id.list_frag))
                .hide(manager.findFragmentById(R.id.detail_frag))
                .hide(manager.findFragmentById(R.id.sight_frag))
                .commit();
        if(currSight !=null)
        {
            manager.beginTransaction()
                    .hide(manager.findFragmentById(R.id.list_frag))
                    .hide(manager.findFragmentById(R.id.detail_frag))
                    .show(manager.findFragmentById(R.id.sight_frag))
                    .commit();
            setSightsViews();
            setSightslistener();

            ivPlayVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ivPlayVideo.setVisibility(View.INVISIBLE);
                    playVideo(currSight.getVideoPath());
                }
            });

            String name= currSight.getName();
            tv_Name.setText(currSight.getName());
            tv_Detail.setText(currSight.getDetails());
            iv_main_image.setImageResource(currSight.getImageSrcId());
            ArrayList<Comment> comments= currSight.getComments();


            commentAdapter=new CommentAdapter(comments);
            layoutManagerComment= new LinearLayoutManager(this);
            recyclerViewComment.setLayoutManager(layoutManagerComment);
            recyclerViewComment.setHasFixedSize(true);
            recyclerViewComment.setAdapter(commentAdapter);

            contentAdapter=new ContentAdapter(this, currSight.getContents());
            layoutManagerContent = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
            recyclerViewContent.setHasFixedSize(true);
            recyclerViewContent.setLayoutManager(layoutManagerContent);
            recyclerViewContent.setAdapter(contentAdapter);

        }

    }

    @Override
    public void stopVideo() {
        if(videoView!=null) {
            videoView.stopPlayback();
            videoView=null;
            ivPlayVideo.setVisibility(View.VISIBLE);
        }

    }

    void setCitiesViews(){
        tv_distanceCity=findViewById(R.id.tv_distance_city);
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
        tv_openCityOfSight=findViewById(R.id.tv_city_of_sight);
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
        ivPlayVideo=findViewById(R.id.ivplay);
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


    public void onSightSelectedItem(final Sight sight) {
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
        tv_openCityOfSight.setText(list_fragment.cities.get(currCityIndex).getName());
           ivPlayVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ivPlayVideo.setVisibility(View.INVISIBLE);
                    playVideo(sight.getVideoPath());
                }
            });

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
        currCityIndex=index;
        tv_Name.setText(currCity.getName());
        tv_Detail.setText(currCity.getDetails());
        iv_main_image.setImageResource(currCity.getImageSrcID());
        tv_distanceCity.setText("Distance: "+currCity.getDistance()+"  km");
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

        tv_openCityOfSight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currCityIndex>=0)
                    onItemSelected(currCityIndex);
            }
        });
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

    @Override
    protected void onStop() {
        super.onStop();
        currSight =null;
        currCityIndex=-1;
        stopVideo();
    }
}
