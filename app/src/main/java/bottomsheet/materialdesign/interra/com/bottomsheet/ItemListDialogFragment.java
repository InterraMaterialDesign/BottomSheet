package bottomsheet.materialdesign.interra.com.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ItemListDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    public ArrayList<PlayList> playLists = new ArrayList<>();


    public int getItemCount(ArrayList<PlayList> mList) {
        return mList.size();
    }

    // TODO: Customize parameters
    public static ItemListDialogFragment newInstance() {
        final ItemListDialogFragment fragment = new ItemListDialogFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        PlayList item = new PlayList(R.drawable.ic_add2_black_24dp, "Save");
        playLists.add(item);
        item = new PlayList(R.drawable.ic_favorite_black_24dp, "Add to Favourite");
        playLists.add(item);
        item = new PlayList(R.drawable.ic_person_black_24dp, "View Singer");
        playLists.add(item);
        item = new PlayList(R.drawable.ic_album_black_24dp, "View Album");
        playLists.add(item);
        item = new PlayList(R.drawable.ic_remove_from_queue_black_24dp, "Remove from Queue");
        playLists.add(item);
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(getItemCount(playLists)));
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;
        ImageView imageView;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.fragment_item_list_dialog_item, parent, false));
            text = itemView.findViewById(R.id.list_item);
            imageView = itemView.findViewById(R.id.left_icon);

        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final int mItemCount;

        ItemAdapter(int itemCount) {
            mItemCount = itemCount;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(playLists.get(position).getmName());
            holder.imageView.setImageResource(playLists.get(position).getmPicture());
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

    }

}
