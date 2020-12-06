package io.shelfy.presentation.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.shelfy.presentation.R;
import io.shelfy.domain.entity.Movie;
import io.shelfy.presentation.details.view.MovieViewHolder;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    @NonNull
    private final Consumer<Movie> clickListener;

    @NonNull
    private List<Movie> movies = new ArrayList<>();

    public MoviesAdapter(@NonNull Consumer<Movie> clickListener) {
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
        final Movie movie = movies.get(position);
        holder.bind(movie);
        holder.itemView.setOnClickListener(view -> clickListener.accept(movie));
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
