package com.jxs5476.showtracker.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.jxs5476.showtracker.R;
import com.jxs5476.showtracker.Show;
import com.jxs5476.showtracker.ShowDatabase;
import com.jxs5476.showtracker.ShowDetail;
import com.jxs5476.showtracker.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    public ShowDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        final RecyclerView recyclerView = binding.showList;
        db = ShowDatabase.getInstance(getContext());
        List<Show> Shows = db.showDao().getWatchingShows(true);
        recyclerView.setAdapter(new ShowAdapter(Shows));
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private class ShowAdapter extends RecyclerView.Adapter<ShowHolder> {

        private final List<Show> show;

        private ShowAdapter(List<Show> show) {
            this.show = show;
        }

        @NonNull
        @Override
        public ShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ShowHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ShowHolder holder, int position) {
            Show show = this.show.get(position);
            holder.bind(show);
            holder.itemView.setTag(show.getId());
            holder.itemView.setOnClickListener(itemView -> {
                Intent intent = new Intent(getContext(), ShowDetail.class);

                intent.putExtra("id", (int)show.getId());
                startActivity(intent);

            });
        }

        @Override
        public int getItemCount() {
            return show.size();
        }
    }

    private static class ShowHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;
        private final TextView mNameTextView2;

        public ShowHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_show, parent, false));
            mNameTextView = itemView.findViewById(R.id.show_name);
            mNameTextView2 = itemView.findViewById(R.id.show_progress);
        }

        public void bind(Show show) {
            mNameTextView.setText(show.getName());
            mNameTextView2.setText(show.getProgress());
        }
    }



    public void onResume() {
        super.onResume();
        final RecyclerView recyclerView = binding.showList;
        db= ShowDatabase.getInstance(getContext());
        List<Show> Shows = db.showDao().getWatchingShows(true);
        recyclerView.setAdapter(new ShowAdapter(Shows));
    }

}