package com.github.android.baseskelton.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.android.baseskelton.R;
import com.github.android.baseskelton.base.BaseFragment;
import com.github.android.baseskelton.data.entity.User;
import com.github.android.baseskelton.util.extension.RecyclerTouchListener;

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

public class UserFragment extends BaseFragment implements UserView {

    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private Context context;
    private Unbinder unbinder;
    private UserPresenter presenter;

    private List<User> userList;
    private UserAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);

        initView();
        initEvent();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new UserPresenter(getManager());
        presenter.attachView(this);

        loadingData();
    }

    @Override
    public void showUserAll(List<User> userList) {

        Timber.d("Size %d", userList.size());

        this.userList.addAll(userList);
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

        userList = new ArrayList<>();
        adapter = new UserAdapter(context, userList);

        rvUser.setLayoutManager(new LinearLayoutManager(context));
        rvUser.setItemAnimator(new DefaultItemAnimator());
        rvUser.setAdapter(adapter);
    }

    private void initEvent() {

        rvUser.addOnItemTouchListener(new RecyclerTouchListener(context, (view, position) -> {

            User user = userList.get(position);

            Bundle bundle = new Bundle();
            bundle.putInt("user_id", user.getId());
            Navigation.findNavController(view)
                    .navigate(R.id.action_userFragment_to_albumFragment, bundle);
        }));

        refresh.setOnRefreshListener(() -> {
            userList.clear();
            loadingData();
        });
    }

    private void loadingData() {

        Timber.d("Loading Data User");

        refresh.setRefreshing(true);
        presenter.getUserAll();
    }
}
