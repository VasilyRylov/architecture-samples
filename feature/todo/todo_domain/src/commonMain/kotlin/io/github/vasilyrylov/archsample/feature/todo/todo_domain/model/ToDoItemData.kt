import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

data class ToDoItemData(val id: ToDoItemId = ToDoItemId(uuid4()), val text: String, val completed: Boolean)

data class ToDoItemId(val value: Uuid)