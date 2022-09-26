package hu.gyuriczaadam.reactivexpalygorund.presentation.transformation_operators_screen

import hu.gyuriczaadam.reactivexpalygorund.data.flatmap_example.dto.Post

data class TransformationScreenState(
    val isLoading:Boolean = false,
    val posts:List<Post?> = emptyList(),
    val error:String =""
)
