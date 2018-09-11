package bottomsheet.materialdesign.interra.com.bottomsheet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder> {

    private ArrayList<PlayList> mList;
    private Context mContext;
    private TextView songNameCollapsed;
    private TextView songNameExpanded;
    private ImageView cover;


    PlayListAdapter(Context mContext, ArrayList<PlayList> mList, TextView songNameCollapsed, TextView songNameExpanded, ImageView cover) {
        this.mContext = mContext;
        this.mList = mList;
        this.songNameCollapsed = songNameCollapsed;
        this.songNameExpanded = songNameExpanded;
        this.cover = cover;
    }

    @NonNull
    @Override
    public PlayListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_views, parent, false);
        return new PlayListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayListViewHolder holder, int position) {
        final PlayList playList = mList.get(position);
        holder.mPicture.setImageResource(playList.getmPicture());
        holder.mName.setText(playList.getmName());
        holder.listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonListDialogFragment.newInstance().show(((AppCompatActivity)mContext).getSupportFragmentManager(), "dialog");
            }
        });

        holder.gridLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemListDialogFragment.newInstance().show(((AppCompatActivity)mContext).getSupportFragmentManager(), "dialog");
            }
        });
        holder.mPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = playList.getmName();
                text = text.replace("\n", " ");
                songNameCollapsed.setText(text);
                songNameExpanded.setText(text);
                cover.setImageResource(playList.getmPicture());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class PlayListViewHolder extends RecyclerView.ViewHolder {
        ImageView mPicture;
        TextView mName;
        ImageButton listView;
        ImageButton gridLayout;

        PlayListViewHolder(View view) {
            super(view);

            mPicture = view.findViewById(R.id.picture);
            mName = view.findViewById(R.id.mName);
            listView = view.findViewById(R.id.listModalSheet);
            gridLayout = view.findViewById(R.id.gridLayout);
        }
    }
}
