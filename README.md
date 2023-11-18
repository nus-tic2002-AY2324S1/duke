# DukeBot

A task manager with both CLI and GUI.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/nus/duke/Launcher.java` file, right-click it, and choose `Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
    ______   __   __  ___   _  _______  _______  _______  _______ 
   |      | |  | |  ||   | | ||       ||  _    ||       ||       |
   |  _    ||  | |  ||   |_| ||    ___|| |_|   ||   _   ||_     _|
   | | |   ||  |_|  ||      _||   |___ |       ||  | |  |  |   |  
   | |_|   ||       ||     |_ |    ___||  _   | |  |_|  |  |   |  
   |       ||       ||    _  ||   |___ | |_|   ||       |  |   |  
   |______| |_______||___| |_||_______||_______||_______|  |___|  
   Hello! I'm DukeBot.
   What can I do for you?
   HINT: Use the 'help' command for more information.
   ```
4. DukeBot is released under an MIT license.
