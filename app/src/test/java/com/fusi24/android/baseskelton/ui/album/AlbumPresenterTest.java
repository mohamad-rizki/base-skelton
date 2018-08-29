package com.fusi24.android.baseskelton.ui.album;

import com.fusi24.android.baseskelton.TestDataFactory;
import com.fusi24.android.baseskelton.data.DataManager;
import com.fusi24.android.baseskelton.data.entity.Album;

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
public class AlbumPresenterTest {

    private AlbumView view;

    private DataManager manager;

    private AlbumPresenter presenter;

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

        view = mock(AlbumView.class);
        manager = mock(DataManager.class);

        presenter = new AlbumPresenter(manager);
        presenter.attachView(view);
    }

    @After
    public void tearDown() {

        presenter.detachView();
    }

    @Test
    public void getAlbumAllByUser_withSuccess() {

        List<Album> mockAlbumList = TestDataFactory.mockAlbumList();
        Integer mockUserId = 1;

        when(manager.getAlbumAllByUser(mockUserId))
                .thenReturn(Observable.just(mockAlbumList));

        presenter.getAlbumAllByUser(mockUserId);

        verify(view).showAlbumAll(mockAlbumList);
    }

    @Test
    public void getAlbumAllByUser_withError() {

        Integer mockUserId = 1;

        when(manager.getAlbumAllByUser(mockUserId))
                .thenReturn(Observable.error(new RuntimeException("Error")));

        presenter.getAlbumAllByUser(mockUserId);

        verify(view).showError(new RuntimeException("Error"));
    }
}