package bottomsheet.materialdesign.interra.com.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ExpandListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ExpandListDialogFragment extends BottomSheetDialogFragment{

    // TODO: Customize parameter argument names
    public ArrayList<PlayList> list = new ArrayList<>();
    public static String listName;
    public static ImageView coverPicture;

    // TODO: Customize parameters
    public static ExpandListDialogFragment newInstance(String playListName, ImageView coverImage) {
        final ExpandListDialogFragment fragment = new ExpandListDialogFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        listName = playListName;
        coverPicture = coverImage;
        return fragment;
    }

    public int getItemCount(ArrayList<PlayList> playLists) {
        return playLists.size();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expand_list_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        PlayList x = new PlayList(R.drawable.human, "");
        list.add(x);
        x = new PlayList(R.drawable.ic_add2_black_24dp, "Save");
        list.add(x);
        x = new PlayList(R.drawable.ic_favorite_black_24dp, "Favourite");
        list.add(x);
        x = new PlayList(R.drawable.ic_queue_music_black_24dp, "Add to Queue");
        list.add(x);
        x = new PlayList(R.drawable.ic_album_black_24dp, "View the Album");
        list.add(x);
        x = new PlayList(R.drawable.ic_person_black_24dp, "View the Artist");
        list.add(x);
        x = new PlayList(R.drawable.ic_music_note_black_24dp, "Add to PlayList");
        list.add(x);
        x = new PlayList(R.drawable.ic_radio_black_24dp, "Go to Radio");
        list.add(x);
        x = new PlayList(R.drawable.ic_file_download_black_24dp, "Download");
        list.add(x);
        x  = new PlayList(R.drawable.ic_camera_alt_black_24dp, "Screenshot");
        list.add(x);
        x = new PlayList(R.drawable.ic_comment_black_24dp, "Comment");
        list.add(x);
        x = new PlayList(R.drawable.ic_help_outline_black_24dp, "Help");
        list.add(x);


        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ExpandAdapter(getItemCount(list)));
    }




    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView listItem;
        ImageView listItemIcon;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.fragment_expand_list_dialog_item, parent, false));
            listItem = itemView.findViewById(R.id.list_item_text);
            listItemIcon = itemView.findViewById(R.id.list_item_icon);
        }

    }

    private class ExpandAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final int mItemCount;

        ExpandAdapter(int itemCount) {
            mItemCount = itemCount;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.listItem.setText(list.get(position).getmName());
            holder.listItemIcon.setImageResource(list.get(position).getmPicture());
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

    }

}
