package com.fusi24.android.baseskelton.ui.photo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fusi24.android.baseskelton.R;
import com.fusi24.android.baseskelton.base.BaseAdapter;
import com.fusi24.android.baseskelton.data.entity.Photo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

class PhotoAdapter extends BaseAdapter<PhotoAdapter.PhotoHolder> {

    private Context context;
    private List<Photo> photoList;

    PhotoAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    class PhotoHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_photo_image)
        ImageView ivPhotoImage;
        @BindView(R.id.tv_photo_title)
        TextView tvPhotoTitle;

        PhotoHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_photo, parent, false);
        return new PhotoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {

        Photo photo = photoList.get(position);

        holder.tvPhotoTitle.setText(photo.getTitle());
        loadImageFromURL(context, holder.ivPhotoImage, photo.getUrl(), null);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
