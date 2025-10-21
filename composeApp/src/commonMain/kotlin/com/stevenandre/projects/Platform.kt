package com.stevenandre.projects

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform