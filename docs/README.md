# User Guide

## Features 

### Feature- Task Tracker

User is able to tell Tim to keep track of a task.
Tasks are stored in a list where users are able to add, mark, unmark ,delete any task.


## Usage

### `Keyword` - Todo

Adds a Todo event into task list. ( Todo is a task without time parameters.)

Format:

`Todo [task name]`

Example of usage:

`Todo return school book`

Expected outcome:

Tim will add "return school book" Todo entry into the task list.

```
|| todo ||
Gotcha! Added this task:
1. [T] [ ] return school book
now there is: 2 item(s)
```

### `Keyword` - Deadline

Adds a Deadline into task list. ( Deadline is a task with a done-by date.)

Format:

`Deadline [task name] /by [done-by date]`

Example of usage:

`Deadline quiz 6 /by 21-10-23`

Expected outcome:

Tim will add "quiz 6" deadline with "21-10-23" as the done-by date.

```
|| deadline ||
Gotcha! Added this task:
1. [D] [ ] pay credit bill  (by: 15-10-2023)
now there is: 3 item(s)

____________________________________________________________
```

### `Keyword` - Event

Adds an Event into task list. ( Event is a time-range-bound task with start and end date.)

Format:

`Event [task name] /from [start date] /to [end date])`

Example of usage:

`Event School Camp /from 15-10-23 /to 19-10-23`

Expected outcome:

Tim will add "School Camp" Event entry into the task list with time range "from 15-10-23 to 19-10-23".


```
|| event ||
Gotcha! Added this task:
1. [E] [ ] School Camp (from: 15-10-23  to: 19-10-23)
now there is: 1 item(s)

____________________________________________________________
```
### `Keyword` - Mark

Marks an existing undone task in the list as done.

Format:

`Mark [task index]`

Example of usage:

`Mark 1`

Expected outcome:

Tim will mark task 1 as done with a [x].


```
|| mark ||
Nice! I've marked this task as done:
1. [E] [x] School Camp (from: 15-10-23  to: 19-10-23)

____________________________________________________________
```

### `Keyword` - Unmark

Unmark an existing completed task in the list as undone.

Format:

`Unmark [task index]`

Example of usage:

`Unmark 1`

Expected outcome:

Tim will mark task 1 as undone with a [ ].


```
|| unmark ||
OK, I've marked this task as not done yet:
1. [E] [ ] School Camp (from: 15-10-23  to: 19-10-23)

____________________________________________________________
```

### `Keyword` - Delete

Delete an existing task from the list.

Format:

`Delete [task index]`

Example of usage:

`Delete 1`

Expected outcome:

Tim will delete task 1, subsequent task will cascade down in index and print the updated list.


```
|| delete ||
Deleting: 1. [E] [x] School Camp (from: 15-10-23  to: 19-10-23)

____________________________________________________________
1. [T] [ ] return school book
2. [D] [ ] pay credit bill  (by: 15-10-2023)

____________________________________________________________
```