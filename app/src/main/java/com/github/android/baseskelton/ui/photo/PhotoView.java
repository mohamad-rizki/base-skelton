package com.github.android.baseskelton.ui.photo;

import com.github.android.baseskelton.base.BaseView;
import com.github.android.baseskelton.data.entity.Photo;

import java.util.List;

interface PhotoView extends BaseView {

    void showPhotoAll(List<Photo> photoList);
}
