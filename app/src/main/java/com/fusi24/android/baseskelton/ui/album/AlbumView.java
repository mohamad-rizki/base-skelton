package com.fusi24.android.baseskelton.ui.album;

import com.fusi24.android.baseskelton.base.BaseView;
import com.fusi24.android.baseskelton.data.entity.Album;

import java.util.List;

interface AlbumView extends BaseView {

    void showAlbumAll(List<Album> albumList);
}
