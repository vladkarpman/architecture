package io.shelfy.architecture.presentation.details.viewmodel.formatters;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;

import io.shelfy.architecture.domain.entity.Movie;

class MovieDescriptionFormatter {

    public Spannable format(Movie movie) {
        String description = movie.getDescription();
        final SpannableStringBuilder formattedDescription = new SpannableStringBuilder(description);
        formattedDescription.setSpan(
                new BackgroundColorSpan(Color.CYAN),
                0,
                description.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        return formattedDescription;
    }
}

