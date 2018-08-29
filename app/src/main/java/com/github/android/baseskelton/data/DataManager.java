package com.github.android.baseskelton.data;

import com.github.android.baseskelton.data.api.RestService;
import com.github.android.baseskelton.data.entity.Album;
import com.github.android.baseskelton.data.entity.Photo;
import com.github.android.baseskelton.data.entity.User;

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
