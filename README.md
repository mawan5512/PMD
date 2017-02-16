### Compiling:
1. Either install gradle and run `gradle shadowJar` or run `./gradlew shadowJar` on *NIX or `gradlew shadowJar` on Windows.
2. A jar containing all dependencies is built at `build/libs/PMD-1.0-SNAPSHOT-all.jar`.

### Checking out into IntelliJ IDEA:
1. Install Git.
2. Copy Git URL from this GitHub page.
3. Navigate to where you want your local repo and run `git clone URL`.
4. Start IntelliJ IDEA. Select `Import Project`.
5. Navigate to where you cloned the repo and select the project root folder.
6. Select Gradle for the external model.
7. Check `Create directory for empty content roots automatically` and a JVM for Gradle.
8. If IntelliJ does not ask to register VCS root, taskbar > VCS > Enable Version Control Integeration > Git > OK
