### Compiling:
1. Either install gradle and run `gradle shadowJar` or run `./gradlew shadowJar` on *NIX or `gradlew shadowJar` on Windows.
2. A jar containing all dependencies is built at `build/libs/PMD-1.0-SNAPSHOT-all.jar`.

### Checking out into IntelliJ IDEA:
1. Install Git.
2. Copy Git URL (ends in .git) from this GitHub page.
3. Navigate to where you want your local repo and run `git clone URL`. Project root is PMD folder.
4. Start IntelliJ IDEA. Select `Import Project`.
5. Navigate to where you cloned the repo and select the project root folder.
6. Select Gradle for the external model.
7. Check `Create directory for empty content roots automatically` and a JVM for Gradle.
8. If IntelliJ does not ask to register VCS root, taskbar > VCS > Enable Version Control Integeration > Git > OK

### Checking out into Eclipse:
1. Install Git.
2. Copy Git URL (ends in .git) from this GitHub page.
3. Navigate to where you want your local repo and run `git clone URL`. Project root is PMD folder.
4. Start Eclipse. Select a workspace.
5. Taskbar > File > Import.. > Gradle > Gradle Project
6. Navigate to where you cloned the repo and select the project root folder.

### Checking out into NetBeans:
1. Install Git.
2. Copy Git URL (ends in .git) from this GitHub page.
3. Navigate to where you want your local repo and run `git clone URL`. Project root is PMD folder.
4. Start NetBeans.
5. Taskbar > Tools > Plugins > Find and install Gradle Support plugin.
6. Taskbar > File > Open Project
7. Navigate to where you cloned the repo and select the project root folder.

### Making changes:
```
git checkout master
git pull origin master       # Update local master branch to reflect latest changes on remote master branch
git branch branch-name       # Create feature branch based off master
git checkout branch-name     # Switch to feature branch
edit files                   # Make changes to files
git commit -a                # Commit changes with message describing changes
git push origin branch-name  # Upload feature branch to remote repo
```

### Making more changes to existing branch:
```
git checkout branch-name     # Switch to feature branch
edit files                   # Make changes to files
git commit -a                # Commit changes with message describing changes
git push origin branch-name  # Upload additional changes to remote repo
```

All basic Git functionality is present in the IDEs' menus, so you don't need to use the command line.