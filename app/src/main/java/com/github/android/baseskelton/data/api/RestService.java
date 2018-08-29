package com.github.android.baseskelton.data.api;

import com.github.android.baseskelton.data.entity.Album;
import com.github.android.baseskelton.data.entity.Photo;
import com.github.android.baseskelton.data.entity.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {

    @GET("/users")
    Observable<List<User>> getUserAll();

    @GET("/albums")
    Observable<List<Album>> getAlbumAllByUser(@Query("userId") Integer userId);

    @GET("/photos")
    Observable<List<Photo>> getPhotoAllByAlbum(@Query("albumId") Integer albumId);
}
