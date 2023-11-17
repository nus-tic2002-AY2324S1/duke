# archive.DukeOld project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/archive.DukeOld.java` file, right-click it, and choose `Run archive.DukeOld.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
5. Duke is released under an MIT license.


## Feature
### Level 0
1, Fork and Clone repository, implement greeting and set name function.

### Level 1
1, Add printService, get command from terminal and print corresponding message.

### Level 2
1, add "add task" and "print task" function

### Level 3
1, add Mark as Done and mark as not done function. 

2, Use the input/output redirection technique to semi-automate the testing of archive.DukeOld. change the .gitignore file, include text-ui-test folder in the remote repo

### Level 4
1, create archive.Todos, archive.Deadlines and archive.Events class, and Modify archive.DukeOld class to meet level 4 requirements

### Level 5
1,Level 5. Handle Errors,deal with errors such as incorrect inputs entered by the user

### Level 6
1, Add support for deleting tasks from the list and Use Java enum to create a archive.TaskType Class

### Level 7
1, implement Level-7 Save, A-MoreOOP, A-Packages, A-JUnit Testing

### Level 8
1, implement Level-7 Dates and Times,  Add JavaDoc comments, Automate project builds using Gradle,JUnit tests

### Level 9
1, Add individual feature 1 (undo), implement Find function, Give users a way to find a task by searching for a keyword.Tweak the code to comply with a coding standard， Increase Code Quality

### Level 10
1, Improve the individual feature and add another individual feature (Flexible data source),Use Assertions, and Package the App as a JAR file


