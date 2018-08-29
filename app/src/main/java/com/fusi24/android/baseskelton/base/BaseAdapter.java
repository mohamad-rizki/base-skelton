package com.fusi24.android.baseskelton.base;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected void loadImageFromURL(@NonNull Context context, @NonNull ImageView view,
                                    @NonNull String url, @Nullable Object signature) {

        if (TextUtils.isEmpty(url)) {
            return;
        }

        Picasso.with(context)
                .load(url)
                .into(view);
    }
}
