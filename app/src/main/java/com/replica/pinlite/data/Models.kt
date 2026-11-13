package com.replica.pinlite.data

data class Pin(
    val id: String,
    val title: String,
    val imageUrl: String,
    val author: String,
    val aspectRatio: Float = 1.33f,
    val isVideo: Boolean = false,
    val category: String = "Design"
)

data class Board(
    val id: String,
    val name: String,
    val pinIds: List<String> = emptyList(),
    val isMoodBoard: Boolean = false
)

object PinRepository {
    val samplePins = listOf(
        Pin(
            id = "p1",
            title = "Air Max 90 Kinetic Volt",
            imageUrl = "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=800&auto=format&fit=crop&q=80",
            author = "SneakerArchive",
            aspectRatio = 1.25f,
            category = "Sneakers"
        ),
        Pin(
            id = "p2",
            title = "Brutalist Concrete Pavilion",
            imageUrl = "https://images.unsplash.com/photo-1513694203232-719a280e022f?w=800&auto=format&fit=crop&q=80",
            author = "Studio Mono",
            aspectRatio = 1.5f,
            category = "Architecture"
        ),
        Pin(
            id = "p3",
            title = "Fluorescent Typographic Poster",
            imageUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=800&auto=format&fit=crop&q=80",
            author = "AcidDesignLab",
            aspectRatio = 0.75f,
            category = "Editorial"
        ),
        Pin(
            id = "p4",
            title = "Cybernetic Industrial Concept",
            imageUrl = "https://images.unsplash.com/photo-1550745165-9bc0b252726f?w=800&auto=format&fit=crop&q=80",
            author = "FutureKinetics",
            aspectRatio = 1.1f,
            category = "Design"
        ),
        Pin(
            id = "p5",
            title = "Minimal Leather Runner",
            imageUrl = "https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?w=800&auto=format&fit=crop&q=80",
            author = "CraftFootwear",
            aspectRatio = 1.4f,
            category = "Sneakers"
        ),
        Pin(
            id = "p6",
            title = "High Contrast Monolith",
            imageUrl = "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?w=800&auto=format&fit=crop&q=80",
            author = "UrbanShapes",
            aspectRatio = 0.8f,
            category = "Architecture"
        ),
        Pin(
            id = "p7",
            title = "Kinetic Motion Showcase (Tap to Play)",
            imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=800&auto=format&fit=crop&q=80",
            author = "MotionStudio",
            aspectRatio = 1.6f,
            isVideo = true,
            category = "Motion"
        ),
        Pin(
            id = "p8",
            title = "Modernist Chair Specimen",
            imageUrl = "https://images.unsplash.com/photo-1586023492125-27b2c045efd7?w=800&auto=format&fit=crop&q=80",
            author = "NordicAtelier",
            aspectRatio = 1.3f,
            category = "Design"
        )
    )

    var boards = mutableListOf(
        Board(id = "b1", name = "Acid Neon Inspo", pinIds = listOf("p1", "p3"), isMoodBoard = true),
        Board(id = "b2", name = "Sneaker Silhouettes", pinIds = listOf("p1", "p5"), isMoodBoard = false),
        Board(id = "b3", name = "Brutalist Spaces", pinIds = listOf("p2", "p6"), isMoodBoard = true)
    )
}
