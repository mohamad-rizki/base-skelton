package com.fusi24.android.baseskelton.ui.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fusi24.android.baseskelton.R;
import com.fusi24.android.baseskelton.base.BaseAdapter;
import com.fusi24.android.baseskelton.data.entity.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

class UserAdapter extends BaseAdapter<UserAdapter.UserHolder> {

    private Context context;
    private List<User> userList;

    UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    class UserHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_user_phone)
        TextView tvUserPhone;

        UserHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_user, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        User user = userList.get(position);

        holder.tvUserName.setText(user.getName());
        holder.tvUserPhone.setText(user.getPhone());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
