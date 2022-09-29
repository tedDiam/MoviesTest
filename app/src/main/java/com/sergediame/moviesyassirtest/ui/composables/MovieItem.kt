package com.sergediame.moviesyassirtest.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.sergediame.moviesyassirtest.MovieUiModel
import com.sergediame.moviesyassirtest.R
import com.sergediame.moviesyassirtest.ui.theme.Typography

@Composable
fun RocketItem(
    movie: MovieUiModel,
    onMovieClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.dimen_medium)
            )
            .clickable {onMovieClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.dimen_small)
            )
        ) {
            Text(
                text = movie.title,
                style = Typography.subtitle1
            )

            Text(
                text = stringResource(
                    id = R.string.rocket_cost_per_launch,
                    movie.vote_average
                ),
                style = Typography.body1
            )

            Text(
                text = stringResource(
                    id = R.string.rocket_first_flight,
                    movie.original_title
                ),
                style = Typography.body1
            )

            Text(
                text = stringResource(
                    id = R.string.rocket_height,
                    movie.popularity
                ),
                style = Typography.body1
            )

            Text(
                text = stringResource(
                    id = R.string.rocket_weight,
                    movie.vote_average
                ),
                style = Typography.body1
            )
        }

        AsyncImage(
            model = movie.poster_path,
            contentDescription = stringResource(id = R.string.rocket_image_content_description),
            modifier = Modifier
                .weight(1f)
        )
    }
}
