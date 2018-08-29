package com.github.android.baseskelton.data;

import com.github.android.baseskelton.TestDataFactory;
import com.github.android.baseskelton.data.api.RestService;
import com.github.android.baseskelton.data.entity.Album;
import com.github.android.baseskelton.data.entity.Photo;
import com.github.android.baseskelton.data.entity.User;

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
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    private RestService restService;

    private DataManager manager;

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

        restService = mock(RestService.class);
        manager = new DataManager(restService);
    }

    @After
    public void tearDown() {

        manager = null;
    }

    @Test
    public void getUserAll_withSuccess() {

        List<User> mockUserList = TestDataFactory.mockUserList();

        when(restService.getUserAll())
                .thenReturn(Observable.just(mockUserList));

        TestObserver<List<User>> testObserver = new TestObserver<>();
        manager.getUserAll().subscribe(testObserver);

        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }

    @Test
    public void getAlbumAllByUser_withSuccess() {

        List<Album> mockAlbumList = TestDataFactory.mockAlbumList();
        Integer mockUserId = 1;

        when(restService.getAlbumAllByUser(mockUserId))
                .thenReturn(Observable.just(mockAlbumList));

        TestObserver<List<Album>> testObserver = new TestObserver<>();
        manager.getAlbumAllByUser(mockUserId).subscribe(testObserver);

        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }

    @Test
    public void getPhotoAllByAlbum_withSuccess() {

        List<Photo> mockPhotoList = TestDataFactory.mockPhotoList();
        Integer mockPhotoId = 1;

        when(restService.getPhotoAllByAlbum(mockPhotoId))
                .thenReturn(Observable.just(mockPhotoList));

        TestObserver<List<Photo>> testObserver = new TestObserver<>();
        manager.getPhotoAllByAlbum(mockPhotoId);

        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }
}