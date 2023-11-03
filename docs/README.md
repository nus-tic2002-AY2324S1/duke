# <img src="./../src/main/resources/images/wonky.png" alt="Wonky Bot" height="50"> Wonky Bot User Guide <img src="./../src/main/resources/images/user.png" alt="User" height="50">
## <img src="./../src/main/resources/images/misc/leaf.png" alt="Maple leaf" width="30" height="30"> Features 

### Friendly Personality
Wonky Bot is designed to interact with users in a friendly, conversational manner. It responds to commands not just with the requested output, but also with encouraging messages, light-hearted jokes, and helpful suggestions. This feature aims to make task management feel less like a chore and more like a conversation with a helpful friend.

### Storage System
The Storage System feature allows for persistent data storage. This means that tasks added to Wonky Bot will be stored and remain available across different usage sessions. Even if you exit and restart Wonky Bot, your tasks will still be there. This is particularly useful for keeping track of tasks over a long period of time.

### Task Categorisation
Wonky Bot offers task categorisation features, allowing users to classify tasks into different types such as 'Todo', 'Deadline', and 'Event'. This helps in organising tasks more efficiently and makes it easier for users to manage and prioritise their tasks.

## <img src="./../src/main/resources/images/misc/leaf.png" alt="Maple leaf" width="30" height="30"> Commands

### `list` - Lists all tasks.
`list`

### `help` - Displays a help message with all commands.
`help`

### `bye` - Exits the chatbot.
`bye`

### `todo` - Adds a todo task.
`todo <description>`

### `deadline` - Adds a task with a deadline.
`deadline <description> | <YYYY-MM-DD HH:mm>`
```
This is a tip. You can use Natural dates as well!
```  
Examples  
`deadline hwk | 2023-11-25 17:25`  
`deadline hwk2 | Mon`
### `event` - Adds an event.
`event <description> | <YYYY-MM-DD HH:mm> | <YYYY-MM-DD HH:mm>`

### `mark` - Marks a task as done.
`mark <task number>`

### `unmark` - Marks a task as not done.
`unmark <task number>`

### `delete` - Deletes a task.
`delete <task number>`

### `find` - Finds tasks that contain a certain keyword.
`find <description>`

### `stash list` - Shows a list of stashes.
`stash list`

### `stash clear` - Deletes all stashes.
`stash clear`

### `stash add` - Adds the current tasks to a stash.
`stash add | <stash name>`

### `stash pop` - Removes and applies a task to current tasks.
`stash pop | <stash name>`

