# CrabY 🦀 Personal Assistant Chat bot

Welcome to your **CrabY project**. This one is a part of TIC2002 Introduction to Software Engineering module.

This project is a simple chatbot that help you to manage things to remember, optimized for users who prefer to type over mouse/GUI.
## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/craby.Duke.java` file, right-click it, and choose `Run craby.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
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
