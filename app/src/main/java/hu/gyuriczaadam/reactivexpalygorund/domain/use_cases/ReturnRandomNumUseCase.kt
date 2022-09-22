package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases

import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import javax.inject.Singleton
import kotlin.random.Random
@ViewModelScope
@Singleton
class ReturnRandomNumUseCase {
    operator fun invoke():Int{
        return Random.nextInt(10,100)
    }
}