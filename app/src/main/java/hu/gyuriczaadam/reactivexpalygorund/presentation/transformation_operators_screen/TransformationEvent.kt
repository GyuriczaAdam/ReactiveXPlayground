package hu.gyuriczaadam.reactivexpalygorund.presentation.transformation_operators_screen

sealed class TransformationEvent {
    data class OnQueryChange(val query: String) : TransformationEvent()
    object OnSearch:TransformationEvent()
    object OnThrottleListClick:TransformationEvent()
}