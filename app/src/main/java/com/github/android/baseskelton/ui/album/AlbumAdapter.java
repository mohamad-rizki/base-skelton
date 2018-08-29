package com.github.android.baseskelton.ui.album;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.android.baseskelton.R;
import com.github.android.baseskelton.base.BaseAdapter;
import com.github.android.baseskelton.data.entity.Album;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumAdapter extends BaseAdapter<AlbumAdapter.AlbumHolder> {

    private Context context;
    private List<Album> albumList;

    AlbumAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    class AlbumHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_album_title)
        TextView tvAlbumTitle;

        AlbumHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_album, parent, false);
        return new AlbumHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {

        Album album = albumList.get(position);

        holder.tvAlbumTitle.setText(album.getTitle());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
