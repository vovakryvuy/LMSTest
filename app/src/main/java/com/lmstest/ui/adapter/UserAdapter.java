package com.lmstest.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lmstest.data.view_model.UserModel;
import com.lmstest.databinding.ItemUserBinding;
import com.lmstest.databinding.LoadingPaginnationBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private static final int VIEW_TYPE_NORMAL = 0;
	private static final int VIEW_TYPE_LOADING = 1;
	private boolean isLoaderVisible = false;
	private List<UserModel> userList;

	public UserAdapter() {
		userList = new ArrayList<>();
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
		switch (viewType) {
			case VIEW_TYPE_NORMAL: {
				ItemUserBinding binding =
						ItemUserBinding.inflate(inflater, viewGroup, false);
				return new ViewHolderUser(binding);
			}
			case VIEW_TYPE_LOADING: {
				LoadingPaginnationBinding binding =
						LoadingPaginnationBinding.inflate(inflater, viewGroup, false);
				return new LoadingViewHolder(binding);
			}
		}
		ItemUserBinding binding = ItemUserBinding.inflate(inflater, viewGroup, false);
		return new ViewHolderUser(binding);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
		if (viewHolder instanceof ViewHolderUser) {
			UserModel user = userList.get(i);
			((ViewHolderUser) viewHolder).bind(user);
		}
	}

	public void addLoading() {
		isLoaderVisible = true;
		addNewUser(new UserModel());
	}

	public void removeLoading() {
		isLoaderVisible = false;
		int position = userList.size() - 1;
		if (position != -1) {
			UserModel userModel = userList.get(position);
			if (userModel != null) {
				userList.remove(position);
				notifyItemRemoved(position);
			}
		}
	}

	public void addNewUser(UserModel userModel) {
		userList.add(userModel);
		notifyItemInserted(userList.size() - 1);
	}

	@Override
	public int getItemCount() {
		return userList.size();
	}

	@Override
	public int getItemViewType(int position) {
		if (isLoaderVisible) {
			return position == userList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
		} else {
			return VIEW_TYPE_NORMAL;
		}
	}

	public void showUser(List<UserModel> userList) {
		this.userList.clear();
		this.userList.addAll(userList);
		notifyDataSetChanged();
	}

	public void addToList(List<UserModel> photoList) {
		if (userList != null) {
			userList.addAll(photoList);
			if (userList.size() > 0){
				notifyItemInserted(userList.size() - 1);
			}
		}
	}

	public class ViewHolderUser extends RecyclerView.ViewHolder {
		private final ItemUserBinding binding;

		ViewHolderUser(ItemUserBinding binding) {
			super(binding.getRoot());
			this.binding = binding;
		}

		public void bind(UserModel user) {
			binding.setUser(user);
			binding.executePendingBindings();
		}

	}
}
