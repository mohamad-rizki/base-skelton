package com.fusi24.android.baseskelton.ui.photo;

import com.fusi24.android.baseskelton.TestDataFactory;
import com.fusi24.android.baseskelton.data.DataManager;
import com.fusi24.android.baseskelton.data.entity.Photo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhotoPresenterTest {

    private PhotoView view;

    private DataManager manager;

    private PhotoPresenter presenter;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxAndroidPlugins.initMainThreadScheduler(Schedulers::trampoline);
        RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @AfterClass
    public static void tearDownClass() {
        RxAndroidPlugins.reset();
    }

    @Before
    public void setUp() {

        view = mock(PhotoView.class);
        manager = mock(DataManager.class);

        presenter = new PhotoPresenter(manager);
        presenter.attachView(view);
    }

    @After
    public void tearDown() {

        presenter.detachView();
    }

    @Test
    public void getPhotoAllByAlbum_withSuccess() {

        List<Photo> mockPhotoList = TestDataFactory.mockPhotoList();
        Integer mockAlbumId = 1;

        when(manager.getPhotoAllByAlbum(mockAlbumId))
                .thenReturn(Observable.just(mockPhotoList));

        presenter.getPhotoAllByAlbum(mockAlbumId);

        verify(view).showPhotoAll(mockPhotoList);
    }

    @Test
    public void getPhotoAllByAlbum_withError() {

        Integer mockAlbumId = 1;

        when(manager.getPhotoAllByAlbum(mockAlbumId))
                .thenReturn(Observable.error(new RuntimeException("Error")));

        presenter.getPhotoAllByAlbum(mockAlbumId);

        verify(view).showError(new RuntimeException("Error"));
    }
}