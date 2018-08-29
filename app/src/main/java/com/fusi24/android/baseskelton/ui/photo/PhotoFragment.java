package com.fusi24.android.baseskelton.ui.photo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fusi24.android.baseskelton.R;
import com.fusi24.android.baseskelton.base.BaseFragment;
import com.fusi24.android.baseskelton.data.entity.Photo;
import com.fusi24.android.baseskelton.util.extension.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class PhotoFragment extends BaseFragment implements PhotoView {

    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private Context context;
    private Unbinder unbinder;
    private PhotoPresenter presenter;

    private Integer albumId;

    private List<Photo> photoList;
    private PhotoAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        unbinder = ButterKnife.bind(this, view);

        initView();
        initEvent();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new PhotoPresenter(getManager());
        presenter.attachView(this);

        if (getArguments() != null) {
            albumId = getArguments().getInt("album_id", 0);
        }

        loadingData();
    }

    @Override
    public void showPhotoAll(List<Photo> photoList) {

        this.photoList.addAll(photoList);
        adapter.notifyDataSetChanged();

        refresh.setRefreshing(false);
    }

    @Override
    public void showError(Throwable throwable) {

        Timber.w(throwable);

        refresh.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initView() {

        photoList = new ArrayList<>();
        adapter = new PhotoAdapter(context, photoList);

        rvPhoto.setLayoutManager(new LinearLayoutManager(context));
        rvPhoto.setItemAnimator(new DefaultItemAnimator());
        rvPhoto.setAdapter(adapter);
    }

    private void initEvent() {

        rvPhoto.addOnItemTouchListener(new RecyclerTouchListener(context, (view, position) -> {


        }));

        refresh.setOnRefreshListener(() -> {
            photoList.clear();
            loadingData();
        });
    }

    private void loadingData() {

        refresh.setRefreshing(true);
        presenter.getPhotoAllByAlbum(albumId);
    }
}
