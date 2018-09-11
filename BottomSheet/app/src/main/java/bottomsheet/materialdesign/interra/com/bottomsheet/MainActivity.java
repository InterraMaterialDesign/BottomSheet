package bottomsheet.materialdesign.interra.com.bottomsheet;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public boolean checkPlay = false;
    public boolean checkPlayPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout linearLayout = findViewById(R.id.linear_bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        final ImageButton expandMore = findViewById(R.id.expand_more);
        final ImageButton playPause = findViewById(R.id.play_pause);
        final TextView songName = findViewById(R.id.song_name);
        final TextView songNameExpanded = findViewById(R.id.song_name_expanded);
        final SeekBar seekBar = findViewById(R.id.seek_bar);
        final ImageView cover = findViewById(R.id.cover);
        final ImageButton add = findViewById(R.id.add);
        final NestedScrollView nestedScrollView = findViewById(R.id.nested);
        final ImageButton play = findViewById(R.id.play);

        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkPlayPause) {
                    playPause.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                    checkPlayPause = true;
                }
                else {
                    playPause.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    checkPlayPause = false;
                }
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkPlay) {
                    play.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                    checkPlay = true;
                }
                else {
                    play.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    checkPlay = false;
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(linearLayout, "Added to your Library!", Snackbar.LENGTH_LONG).show();
            }
        });


        ImageButton modalSheetHori = findViewById(R.id.expanded_modal_sheet);
        modalSheetHori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpandListDialogFragment.newInstance(songName.getText().toString(), cover).show(getSupportFragmentManager(), "dialog");
            }
        });


        expandMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED)
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                else
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    nestedScrollView.setVisibility(View.GONE);
                }
                else if(newState == BottomSheetBehavior.STATE_COLLAPSED || newState == BottomSheetBehavior.STATE_DRAGGING) {
                    nestedScrollView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset <= 1 && slideOffset > 0) {
                    expandMore.setImageDrawable(getDrawable(R.drawable.ic_expand_more_black_24dp));
                    playPause.setVisibility(View.INVISIBLE);
                    songName.setVisibility(View.INVISIBLE);
                    seekBar.setVisibility(View.INVISIBLE);
                    nestedScrollView.setVisibility(View.VISIBLE);
                }
                else if (slideOffset == 0) {
                    expandMore.setImageDrawable(getDrawable(R.drawable.ic_expand_less_black_24dp));
                    playPause.setVisibility(View.VISIBLE);
                    songName.setVisibility(View.VISIBLE);
                    seekBar.setVisibility(View.VISIBLE);
                }
            }
        };
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);


        //favourites
        RecyclerView recyclerViewFav = findViewById(R.id.recyclerViewFav);
        ArrayList<PlayList> playListFav = new ArrayList<>();
        PlayList song = new PlayList(R.drawable.weekly_discovery, "Weekly\nDiscovery");
        playListFav.add(song);
        song = new PlayList(R.drawable.all_out_90s, "All Out\n90s");
        playListFav.add(song);
        song = new PlayList(R.drawable.jazz_classics, "Jazz\nClassics");
        playListFav.add(song);
        song = new PlayList(R.drawable.chill_songs, "Chill\nSongs");
        playListFav.add(song);
        song = new PlayList(R.drawable.all_out_00s, "All Out\n00s");
        playListFav.add(song);
        song = new PlayList(R.drawable.summer_songs, "18 Summer\nSongs");
        playListFav.add(song);
        song = new PlayList(R.drawable.pride_classics, "Pride\nClassics");
        playListFav.add(song);
        song = new PlayList(R.drawable.latin_pop_classics, "Latin Pop\nClassics");
        playListFav.add(song);

        PlayListAdapter adapterFav = new PlayListAdapter(this, playListFav, songName, songNameExpanded, cover);
        recyclerViewFav.setAdapter(adapterFav);

        recyclerViewFav.setHasFixedSize(true);

        //hits
        RecyclerView recyclerViewHit = findViewById(R.id.recyclerViewHit);
        ArrayList<PlayList> playListHit = new ArrayList<>();
        PlayList hit = new PlayList(R.drawable.happy_hits, "Happy\nHits");
        playListHit.add(hit);
        hit = new PlayList(R.drawable.hits, "2018\nHits");
        playListHit.add(hit);
        hit = new PlayList(R.drawable.today_top_hits, "Today's\nTop Hits");
        playListHit.add(hit);
        hit = new PlayList(R.drawable.sing_along_indie, "Sing-Along\nIndie Hits");
        playListHit.add(hit);
        hit = new PlayList(R.drawable.summer_hits, "Summer\nHits");
        playListHit.add(hit);
        hit = new PlayList(R.drawable.hot_hits, "Hot Hits\n");
        playListHit.add(hit);
        hit = new PlayList(R.drawable.soft_pop_hits, "Soft Pop\nHits");
        playListHit.add(hit);
        hit = new PlayList(R.drawable.chill_hits, "Chill\nHits");
        playListHit.add(hit);

        PlayListAdapter adapterHit = new PlayListAdapter(this, playListHit, songName, songNameExpanded, cover);
        recyclerViewHit.setAdapter(adapterHit);

        recyclerViewHit.setHasFixedSize(true);


        //moods
        RecyclerView recyclerViewMood = findViewById(R.id.recyclerViewMood);
        ArrayList<PlayList> playListMood = new ArrayList<>();
        PlayList album = new PlayList(R.drawable.friday_funday, "Friday\nFunday");
        playListMood.add(album);
        album = new PlayList(R.drawable.acoustic, "Acoustic\n");
        playListMood.add(album);
        album = new PlayList(R.drawable.feelin_good, "Feelin'\nGood");
        playListMood.add(album);
        album = new PlayList(R.drawable.deep_dark_indie, "Deep Dark\nIndie");
        playListMood.add(album);
        album = new PlayList(R.drawable.have_a_great_day, "Have a\nGreat Day!");
        playListMood.add(album);
        album = new PlayList(R.drawable.morning_coffee, "Morning\nCoffee");
        playListMood.add(album);
        album = new PlayList(R.drawable.rainy_day, "Rainy\nDay");
        playListMood.add(album);
        album = new PlayList(R.drawable.soft_morning, "Soft\nMorning");
        playListMood.add(album);
        album = new PlayList(R.drawable.soft_slow, "Soft &\nSlow");
        playListMood.add(album);

        PlayListAdapter adapterMood = new PlayListAdapter(this, playListMood,  songName, songNameExpanded, cover);
        recyclerViewMood.setAdapter(adapterMood);

        recyclerViewMood.setHasFixedSize(true);
    }
}
