package hu.gyuriczaadam.reactivexpalygorund.presentation.main_screen

import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.dto.Post

data class MainScreenState(
    val isLoading:Boolean = false,
    val posts:List<Post?> = emptyList(),
    val error:String =""
)
