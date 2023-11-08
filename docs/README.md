
# User Guide

## Introduction

CrabY 🦀 is a Chat bot that helps you to manage things to remember, optimized for users who prefer to type over mouse/GUI.

## Getting Started

1. When you start CrabY, you should see something like:
    

 ```
     ____                  _      __   __
    / ___|  _ __    __ _  | |__   \ \ / /
   | |     | '__|  / _` | | '_ \   \ V /
   | |___  | |    | (_| | | |_) |   | |
    \____| |_|     \__,_| |_.__/    |_|

   ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
   Hi ♡, CrabY 🦀 here!
   Which checklist do you want to work on today? (｡◕‿◕｡)
   ╰┈➤You can typing: "School" | "Work" | "Personal" |
                  or  [checklist-name] if you wanna creat a new list.
 ```
1. Type `School` to start working on the school checklist.
2. Type `help` to see the list of commands if you not sure what CrabY can do for you.
3. Refer to the Features below for details of each command.
4. Type `bye` to exit the program.
5. The data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
6. Using the same name at the start of the program to continue working on the same checklist.

•──✦ Enjoy your time ✦──•



## Features 
* Add a task
* Delete a task
* Mark a task as done
* Unmark a task
* List all tasks
* Checklists for different task lists
* Find tasks by keyword
* Sort tasks by date
* Sort tasks by type
* Switch between different task lists
* Undo previous command
* Help message
* Exit the program
### Feature-Add

CrabY can add 3 types of tasks: todo, deadline and event.
* Todo: A task with only a description.
* Deadline: A task with a description and a deadline. — Must use `/by` to specify the deadline.
    
    E.g. `return book /by 2020-02-02 1800`
* Event: A task with a description and a time. — Must use `/from` to specify the time.

    E.g. `project meeting /from 2020-02-02 1400`


### Feature-Delete

Delete a task by index: Delete a task by its index in the task list.

E.g. `delete 1` - will delete the first task in the task list.

### Feature-Mark & Unmark

Mark a task as done: Mark a task as done by its index in the task list.

E.g. `mark 1` - will mark the first task in the task list as done.

Unmark a task: Unmark a task as done by its index in the task list.

E.g. `unmark 2` - will unmark the second task in the task list.

### Feature-List

List all tasks: List all tasks in the task list.

E.g. `list` - will list all tasks in the task list.

### Feature-Checklist

Show all task lists: Show all task lists. 

E.g. `checklist` - will list all task lists.

### Feature-Find

Find tasks by keyword: Find tasks in the task list by keyword.

E.g. `find book` - will list all tasks in the task list that contains the keyword `book`.

### Feature-Sort

Sort tasks by date: Sort tasks in the task list by date.

E.g. `sort date` - will sort all tasks in the task list by date.

Sort tasks by type: Sort tasks in the task list by type.

E.g. `sort type` - will sort all tasks in the task list by type.

### Feature-Switch

Switch between different task lists: Switch between different task lists.

E.g. `switch` - CrabY will ask you to choose a task list to switch to.

`school` - will switch to the school task list.

### Feature-Undo

Type `undo` to undo the previous command. Note: you can only undo add, delete, mark, unmark, sort commands.


### Feature-Help

Type `help` to show a help message.

### Feature-Exit

Type `bye` to exit the program.

## Usage

* `help` - Show help message

* `find` - Find tasks by keyword

* `list` - List all tasks

* `checklists` - Show all task lists

* `sort` - Sort tasks by date or type

* `switch` - Switch between different task lists

* `undo` - Undo previous command

* `bye` - Exit the program

* `todo` - Add a todo task

* `deadline` - Add a deadline task

* `event` - Add an event task

* `delete` - Delete a task

* `mark` - Mark a task as done

* `unmark` - Unmark a task



Example of usage: 

`read book`

Expected outcome:

```
   ✎ added:
   ╰┈➤ read book - to your list
   Now you have: 1 tasks in your list 🗎.
  ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
```

Example of usage:

`mark 1`

Expected outcome:

```
   Nice! I've marked this task as DONE ツ:
   ╰┈➤ [T][ ✓ ] Read book
  ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
```
Example of usage:

`bye`

Expected outcome:

```
      Bye ♡, hope to see you again soon! •ᴗ•
  ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
               __   ♡   __
              /           \
             (  / @   @ \  )
              \(_ _\_/_ _)/
            (\ `-/     \-' /)
             "===\     /==="
              .==')___(`==.
               .='     `=.
```