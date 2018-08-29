package com.fusi24.android.baseskelton.data;

import com.fusi24.android.baseskelton.data.api.RestService;
import com.fusi24.android.baseskelton.data.entity.Album;
import com.fusi24.android.baseskelton.data.entity.Photo;
import com.fusi24.android.baseskelton.data.entity.User;

import java.util.List;

import io.reactivex.Observable;

public class DataManager {

    private RestService restService;

    public DataManager(RestService restService) {
        this.restService = restService;
    }

    public Observable<List<User>> getUserAll() {
        return restService.getUserAll();
    }

    public Observable<List<Album>> getAlbumAllByUser(Integer userId) {
        return restService.getAlbumAllByUser(userId);
    }

    public Observable<List<Photo>> getPhotoAllByAlbum(Integer albumId) {
        return restService.getPhotoAllByAlbum(albumId);
    }
}
