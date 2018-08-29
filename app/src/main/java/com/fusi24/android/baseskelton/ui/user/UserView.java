package com.fusi24.android.baseskelton.ui.user;

import com.fusi24.android.baseskelton.base.BaseView;
import com.fusi24.android.baseskelton.data.entity.User;

import java.util.List;

interface UserView extends BaseView {

    void showUserAll(List<User> userList);
}
