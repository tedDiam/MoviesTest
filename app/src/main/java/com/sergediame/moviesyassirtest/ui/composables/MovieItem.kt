package com.sergediame.moviesyassirtest.ui.composables

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.sergediame.moviesyassirtest.model.MovieUiModel
import com.sergediame.moviesyassirtest.R
import com.sergediame.moviesyassirtest.ui.theme.Typography

@Composable
fun MovieItem(
    movie: MovieUiModel,
    onMovieClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.dimen_medium)
            )
            .clickable { onMovieClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = movie.poster_path,
            contentDescription = stringResource(id = R.string.movie_image_content_description),
            modifier = Modifier
                .weight(1f)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = dimensionResource(id = R.dimen.dimen_small)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.dimen_small)
            )
        ) {
            Text(
                text = movie.title,
                style = Typography.subtitle1,
            )

            Row (verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(R.drawable.ic_baseline_star_rate_24),
                    contentDescription = null,
                )
                Text(
                    text = movie.vote_average.toString(),
                    style = Typography.body1
                )
            }

            Text(
                text = movie.release_date,
                style = Typography.body1,
            )
        }

    }
}
