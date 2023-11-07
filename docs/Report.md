# CrabY 🦀 Project Report

## User Stories

1. As a user, I want to be able to add a task to the task list.

    - The task list should be able to store different types of tasks.
    - The task list should be able to store tasks with the autoformatting of date and time.

2. As a user, I want to be able to delete a task from the task list.

    - The task list should be able to delete a task by its index in the task list.
    - The task list should be able to delete all tasks at one time if the user wants to clear the task list.

3. As a user, I want to be able to mark a task as done or unmark a task as undone.

    - The task list should be able to mark a task as done by its index in the task list.
    - The task list should be able to unmark a task as undone by its index in the task list.
    - The task list should be able to mark all tasks as done at one time if the user wants to mark all tasks as done.
    - The task list should be able to unmark all tasks as undone at one time if the user wants to unmark all tasks as
      undone.

4. As a user, I want to be able to find a task by its description.

    - The task list should be able to find a task by its description or type or date or time of the task.

5. As a user, I want to be able to sort the task list by its description or type or date

    - The task list should be able to sort the task list by its description or type or date or time of the task.

6. As a user, I want to be able to reuse the task list after I exit the program and get back to the program.

    - The task list should be able to save the task list to a local file.
    - The task list should be able to load the task list from a local file.
    - Delete the local file when it empties the task list.
    - Create a new local file when it creates a new task list.

7. As a user, I want to be able to see the help message when I forget how to use the program.

8. As a user, I want to be able to see all the tasks in the task list that I have added.

9. As a user, I want to be able to undo the previous command.

    - The task list should be able to undo all the previous commands from I start the program.

10. As a user, I want to be able to switch between different task lists, so I just need to type, no need to close the
    program and open it again.

## Non-Functional Requirements

1. The program should be able to run on any platform.
2. The program should be able to run on any device.
3. The program should be able to run on any screen size.
4. The program should be able to run on Mac, Windows, and Linux.

## Show case

1. Show the output Duke shows when you launch the program.
     ```
         ____                  _      __   __
        / ___|  _ __    __ _  | |__   \ \ / /
       | |     | '__|  / _` | | '_ \   \ V /
       | |___  | |    | (_| | | |_) |   | |
        \____| |_|     \__,_| |_.__/    |_|
    
       ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
       Hi Amber ♡, CrabY 🦀 here!
       Which checklist do you want to work on today? (｡◕‿◕｡)
       ╰┈➤You can typing: "School" | "Work" | "Personal" |
                      or  [checklist-name] if you wanna creat a new list.
       ```
2. Todo, Deadlines, Events Give examples of command(s) and expected outputs for adding different types of tasks.

    * Todo
    ```
    TIC2002 - finish project
       ✎ added:
       ╰┈➤ TIC2002 - finish project - to your list
       Now you have: 1 tasks in your list 🗎.
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
    ```
    * Deadline
    ```
   TIC2002 - submit project /by 19/11/2023
      ✎ added:
      ╰┈➤ TIC2002 - submit project /by 19/11/2023 - to your list
      Now you have: 4 tasks in your list 🗎.
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
    ```
    * Event
    ```
   TIC2002 - sent draft to Prof. Damith /from today /to 15/11/2023 2359
      ✎ added:
      ╰┈➤ TIC2002 - sent draft to Prof. Damith /from today /to 15/11/2023 2359 - to your list
      Now you have: 5 tasks in your list 🗎.
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
    ```
3. The List Gives examples of command(s) and expected outputs for listing tasks.
   ```
   list
      ╭─────────────────────────────────────╮
      Here are the tasks in your REPORT list:
      1.  [T][҉҉҉] TIC2002 - finish project
      2.  [D][҉҉҉] TIC2002 - submit project || before: 19 Nov 2023, Sun - 12:00AM
      3.  [E][҉҉҉] TIC2002 - sent draft to Prof. Damith || from: 8 Nov 2023, Wed - 11:59PM ➞ to: 15 Nov 2023, Wed - 11:59PM
      ꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
   ```
4. Mark, Unmark Give examples of command(s) and expected outputs for marking/unmarking tasks as done.

    * Mark
   ```
   mark 1
      Nice! I've marked this task as DONE ツ:
      ╰┈➤ [T][ ✓ ] TIC2002 - finish project
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
   ```
   ```
   mark all
      Congratulations! You have COMPLETED all your tasks.
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
   ```
    * Unmark
   ```
   unmark             2
      OK, I've marked this task as ☉⌓☉ NOT DONE yet:
      ╰┈➤ [D][҉҉҉] TIC2002 - submit project || before: 19 Nov 2023, Sun - 12:00AM
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
    ```
    ```
   unmark all
      OK, I've marked ALL tasks as ☉⌓☉ NOT DONE yet:
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
    ```
5. Errors Describe what kind of errors Duke can handle. E.g., give different types of incorrect commands (and the
   expected outputs) it can handle
    - If the user types in an invalid index, CrabY will show the error message and ask the user to type in a valid
      index.
   ```
   mark TIC2002
      Oops!!! Looks like you used the wrong format (╯︵╰,)
      ╰┈➤ Try with: mark [integer] e.g: mark 1
                or: mark all - to mark all the tasks in your list.
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
   ```
   ``` 
   mark 100
      Oops!!! Something wrong! Your list only have 1 ➞ 3 tasks (╯︵╰,)
      ╰┈➤ Please try again with another number ☘
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
   ```
   **Note that:** CrabY can handle cases insensitive and multiple space in the input.

    - If the user types in an invalid date or time, CrabY will show the error message and ask the user to type in a
      valid
      date or time.
    ```
   test error date time /by 15 Nov 16:30
      Oops!!! Looks like you used the wrong format (╯︵╰,)
      Please enter in this format after used /by or /from and /to
      ╰┈➤ Try with: "/by dd/mm/yyyy hhmm" e.g: /by 12/12/2020 1800
                or: "/by mon" - CrabY will put the date to the next Monday
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
    ```
    - If the user puts the blank space in the description or just enter, CrabY will show the error message and ask the
      user to type in a
      valid description.
    ```
                       
      Oops!!! The description cannot be empty.
      ╰┈➤Type "help" if you need to see the list of command
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
    ```
   ```
   /from tomorow
      Oops!!! The description cannot be empty.
      ╰┈➤Type "help" if you need to see the list of command
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
   ```

    - If the user types in an invalid command, CrabY will still add to the task list.
    - Craby can handel 4 types of the date format, user can put the time or not, if the user doesn't put the time, CrabY
      will put the time to 12:00AM.
        - dd/mm/yyyy hhmm
        - yyyy/mm/dd hhmm
        - dd-mm-yyyy hhmm
        - yyyy-mm-dd hhmm
6. Delete Give examples of command(s) and expected outputs for deleting tasks.
   ```
   delete 1
      ✂ Noted. I've removed this task:
      ╰┈➤ [T][҉҉҉] TIC2002 - finish project - in your list
      Now you have: 2 tasks in your list 🗎.
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈

   ```

   ```
   delete all
      ✂ Ok, I have clear up all your tasks.
      ╰┈➤ Let's start a new checklist 
      ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈◦•✩•◦┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
   ```

7. Save Give a sample of the tasks as they are stored in the hard disk.
   ```
   T || 0 || TIC2002 - finish project
   D || 0 || TIC2002 - submit project || 19/11/2023 0000
   E || 0 || TIC2002 - sent draft to Prof. Damith || 08/11/2023 0000 || 15/11/2023 2359
   ```

8.Dates Give examples (i.e., screenshots of commands and outputs) of how your Duke uses dates/times in meaningful ways
i.e., what commands can use dates/times in meaningful ways? E.g., is it possible to list deadlines/events on a specific
day? Does it detect invalid dates given by the user?
- Easy to format 4 types of date format (dd/mm/yyyy hhmm, yyyy/mm/dd hhmm, dd-mm-yyyy hhmm, yyyy-mm-dd hhmm) to what I want to show to the user.
- If the event is on the same day, CrabY will show the date only once.
   ```
   group meeting /from 2023/11/14 1830 /to 14-11-2023 2000
      ✎ added:
      ╰┈➤ group meeting /from 14/11/2023 1830 /to 14/11/2023 2000 - to your list
       Now you have: 4 tasks in your list 🗎.
   ```
-->
   ```
      4.  [E][҉҉҉] Group meeting || from: 14 Nov 2023, Tue - 06:30PM ➞ to: 08:00PM
   ```
