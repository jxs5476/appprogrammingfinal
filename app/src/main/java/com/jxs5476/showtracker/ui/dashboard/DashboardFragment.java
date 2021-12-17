package com.jxs5476.showtracker.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.jxs5476.showtracker.AddShow;
import com.jxs5476.showtracker.R;
import com.jxs5476.showtracker.Show;
import com.jxs5476.showtracker.ShowDatabase;
import com.jxs5476.showtracker.ShowDetail;
import com.jxs5476.showtracker.databinding.FragmentDashboardBinding;

import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    public ShowDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        final RecyclerView recyclerView = binding.showList;
        db = ShowDatabase.getInstance(getContext());
        List<Show> Shows = db.showDao().getNewShows(false,false);
        recyclerView.setAdapter(new ShowAdapter(Shows));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class ShowAdapter extends RecyclerView.Adapter<DashboardFragment.ShowHolder> {

        private final List<Show> shows;

        private ShowAdapter(List<Show> shows) {
            this.shows = shows;
        }

        @NonNull
        @Override
        public DashboardFragment.ShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ShowHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(DashboardFragment.ShowHolder holder, int position) {
            Show currshow = this.shows.get(position);
            holder.bind(currshow);
            holder.itemView.setTag(currshow.getId());
            holder.itemView.setOnClickListener(itemView -> {
                Intent intent = new Intent(getContext(), ShowDetail.class);

                intent.putExtra("id", (int)currshow.getId());
                startActivity(intent);

            });
        }

        @Override
        public int getItemCount() {
            return shows.size();
        }
    }

    private static class ShowHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;

        public ShowHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_prev, parent, false));
            mNameTextView = itemView.findViewById(R.id.show_name);
        }

        public void bind(Show show) {
            mNameTextView.setText(show.getName());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        final RecyclerView recyclerView = binding.showList;
        db = ShowDatabase.getInstance(getContext());
        List<Show> Shows = db.showDao().getNewShows(false,false);
        recyclerView.setAdapter(new ShowAdapter(Shows));
    }

    public void newShowButton(View view){
        Intent intent = new Intent(getContext(), AddShow.class);
        intent.putExtra("id", -1);
        startActivity(intent);
    }
}