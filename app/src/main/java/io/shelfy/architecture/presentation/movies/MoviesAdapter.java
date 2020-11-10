package io.shelfy.architecture.presentation.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.shelfy.architecture.R;
import io.shelfy.architecture.domain.entity.Movie;
import io.shelfy.architecture.presentation.details.view.MovieViewHolder;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    @NonNull
    private final Consumer<Integer> clickListener;

    @NonNull
    private List<Movie> movies = new ArrayList<>();

    public MoviesAdapter(@NonNull Consumer<Integer> clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
        holder.itemView.setOnClickListener(view -> clickListener.accept(position));
    }

    @UiThread
    public void setMovies(@NonNull List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
