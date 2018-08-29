package com.fusi24.android.baseskelton.ui.user;

import com.fusi24.android.baseskelton.TestDataFactory;
import com.fusi24.android.baseskelton.data.DataManager;
import com.fusi24.android.baseskelton.data.entity.User;

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
public class UserPresenterTest {

    private UserView view;

    private DataManager manager;

    private UserPresenter presenter;

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

        view = mock(UserView.class);
        manager = mock(DataManager.class);

        presenter = new UserPresenter(manager);
        presenter.attachView(view);
    }

    @After
    public void tearDown() {

        presenter.detachView();
    }

    @Test
    public void getUserAll_withSuccess() {

        List<User> mockUserList = TestDataFactory.mockUserList();

        when(manager.getUserAll())
                .thenReturn(Observable.just(mockUserList));

        presenter.getUserAll();

        verify(view).showUserAll(mockUserList);
    }

    @Test
    public void getUserAll_withError() {

        when(manager.getUserAll())
                .thenReturn(Observable.error(new RuntimeException("Error")));

        presenter.getUserAll();

        verify(view).showError(new RuntimeException("Error"));
    }
}