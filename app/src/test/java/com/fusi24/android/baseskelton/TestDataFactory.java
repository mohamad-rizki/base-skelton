package com.fusi24.android.baseskelton;

import com.fusi24.android.baseskelton.data.entity.Album;
import com.fusi24.android.baseskelton.data.entity.Photo;
import com.fusi24.android.baseskelton.data.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestDataFactory {

    public static List<User> mockUserList() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName(UUID.randomUUID().toString());
            user.setEmail(UUID.randomUUID().toString());
            user.setPhone(UUID.randomUUID().toString());
            userList.add(user);
        }
        return userList;
    }

    public static List<Album> mockAlbumList() {
        List<Album> albumList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Album album = new Album();
            album.setUserId(i + 1);
            album.setId(i);
            album.setTitle(UUID.randomUUID().toString());
            albumList.add(album);
        }
        return albumList;
    }

    public static List<Photo> mockPhotoList() {
        List<Photo> photoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Photo photo = new Photo();
            photo.setAlbumId(i + 1);
            photo.setId(i);
            photo.setTitle(UUID.randomUUID().toString());
            photo.setUrl(UUID.randomUUID().toString());
            photoList.add(photo);
        }
        return photoList;
    }
}
