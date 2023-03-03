package com.lelestacia.data.mapper

import com.lelestacia.model.Anime
import com.lelestacia.network.model.anime.AnimeResponse

fun AnimeResponse.asAnime(): Anime =
    Anime(
        malID = malId,
        coverImages = coverImages.webp.largeImageUrl,
        trailer = Anime.Trailer(
            youtubeId = trailer?.youtubeId,
            url = trailer?.url,
            images = trailer?.images?.maximumImageUrl
        ),
        title = title,
        titleEnglish = titleEnglish,
        titleJapanese = titleJapanese,
        type = type ?: "Unknown",
        source = source,
        episodes = episodes,
        status = status,
        airing = airing,
        startedDate = aired.from,
        finishedDate = aired.to,
        duration = duration,
        rating = rating ?: "Unknown",
        score = score,
        scoredBy = scoredBy,
        rank = rank,
        synopsis = synopsis,
        season = season,
        year = year,
        studios = studio.map { it.name },
        genres = genres.map { it.name },
        isFavorite = false
    )