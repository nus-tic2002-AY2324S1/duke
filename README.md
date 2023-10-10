# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## Feature
### Level 0
1, Fork and Clone repository, implement greeting and set name function.

### Level 1
1, Add printService, get command from terminal and print corresponding message.

### Level 2
1, add "add task" and "print task" function

### Level 3
1, add Mark as Done and mark as not done function. 

2, Use the input/output redirection technique to semi-automate the testing of Duke. change the .gitignore file, include text-ui-test folder in the remote repo

### Level 4
1, create Todos, Deadlines and Events class, and Modify Duke class to meet level 4 requirements

### Level 5
1,Level 5. Handle Errors,deal with errors such as incorrect inputs entered by the user

### Level 6
1, Add support for deleting tasks from the list and Use Java enum to create a TaskType Class