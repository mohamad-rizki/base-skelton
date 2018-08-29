package com.github.android.baseskelton.ui.user;

import com.github.android.baseskelton.base.BaseView;
import com.github.android.baseskelton.data.entity.User;

import java.util.List;

interface UserView extends BaseView {

    void showUserAll(List<User> userList);
}
