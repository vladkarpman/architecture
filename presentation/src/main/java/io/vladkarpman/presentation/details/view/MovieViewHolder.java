package io.vladkarpman.presentation.details.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import io.vladkarpman.presentation.R;
import io.vladkarpman.domain.entity.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvTitle;
    private final TextView tvOverview;
    private final ImageView ivPoster;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvOverview = itemView.findViewById(R.id.tvOverview);
        ivPoster = itemView.findViewById(R.id.ivPoster);
    }

    @UiThread
    public void bind(@NonNull Movie movie) {
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getDescription());
        // TODO: 10/30/20 do it more generically
        Glide.with(itemView.getContext()).load(movie.getPosterUrl()).into(ivPoster);
    }
}
