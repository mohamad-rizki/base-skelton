package com.fusi24.android.baseskelton.ui.album;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fusi24.android.baseskelton.R;
import com.fusi24.android.baseskelton.base.BaseFragment;
import com.fusi24.android.baseskelton.data.entity.Album;
import com.fusi24.android.baseskelton.util.extension.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class AlbumFragment extends BaseFragment implements AlbumView {

    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private Context context;
    private Unbinder unbinder;
    private AlbumPresenter presenter;

    private Integer userId;

    private List<Album> albumList;
    private AlbumAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        unbinder = ButterKnife.bind(this, view);

        initView();
        initEvent();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new AlbumPresenter(getManager());
        presenter.attachView(this);

        if (getArguments() != null) {
            userId = getArguments().getInt("user_id", 0);
        }

        loadingData();
    }

    @Override
    public void showAlbumAll(List<Album> albumList) {

        this.albumList.addAll(albumList);
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

        albumList = new ArrayList<>();
        adapter = new AlbumAdapter(context, albumList);

        rvUser.setLayoutManager(new LinearLayoutManager(context));
        rvUser.setItemAnimator(new DefaultItemAnimator());
        rvUser.setAdapter(adapter);
    }

    private void initEvent() {

        rvUser.addOnItemTouchListener(new RecyclerTouchListener(context, (view, position) -> {

            Album album = albumList.get(position);

            Bundle bundle = new Bundle();
            bundle.putInt("album_id", album.getId());
            Navigation.findNavController(view)
                    .navigate(R.id.action_albumFragment_to_photoFragment, bundle);
        }));

        refresh.setOnRefreshListener(() -> {
            albumList.clear();
            loadingData();
        });
    }

    private void loadingData() {

        refresh.setRefreshing(true);
        presenter.getAlbumAllByUser(userId);
    }
}
