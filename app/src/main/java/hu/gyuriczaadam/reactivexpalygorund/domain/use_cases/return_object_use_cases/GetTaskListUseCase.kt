package hu.gyuriczaadam.reactivexpalygorund.domain.use_cases.return_object_use_cases

import hu.gyuriczaadam.reactivexpalygorund.data.operators_example.Task
import hu.gyuriczaadam.reactivexpalygorund.di.ViewModelScope
import javax.inject.Singleton

@ViewModelScope
@Singleton
class GetTaskListUseCase {
    operator fun invoke():List<Task>{
        val tasks = mutableListOf<Task>()
        tasks.add(Task("Take out trash",false,1))
        tasks.add(Task("Walk the dog",true,4))
        tasks.add(Task("Make homework",false,1))
        tasks.add(Task("Do exercise",true,6))
        return tasks
    }
}