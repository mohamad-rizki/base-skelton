package com.github.android.baseskelton.ui.album;

import com.github.android.baseskelton.base.BaseView;
import com.github.android.baseskelton.data.entity.Album;

import java.util.List;

interface AlbumView extends BaseView {

    void showAlbumAll(List<Album> albumList);
}
