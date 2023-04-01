package com.lelestacia.common.request_param

enum class AnimeRating(name: String, title: String) {
    G(name = "g", title = "G - All Ages"),
    PG(name = "pg", title = "PG - Children"),
    PG_13(name = "pg13", title = "PG-13 - Teens 13 or older"),
    R_17(name = "r17", title = "R-17+ (violence & profanity"),
    R(name = "r", title = "R+ - Mild Nudity"),
    RX(name = "rx", title = "Rx - Hentai")
}