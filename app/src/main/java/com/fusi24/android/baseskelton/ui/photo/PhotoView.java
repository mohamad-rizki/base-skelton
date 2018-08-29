package com.fusi24.android.baseskelton.ui.photo;

import com.fusi24.android.baseskelton.base.BaseView;
import com.fusi24.android.baseskelton.data.entity.Photo;

import java.util.List;

interface PhotoView extends BaseView {

    void showPhotoAll(List<Photo> photoList);
}
