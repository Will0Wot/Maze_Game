

## Game Name - Knight's Quest: The Goblin Heist !

### How to Play the Game - 
The objective of the game is to navigate the Knight through a labyrinthian map, avoiding dynamite barrels and goblins 
while you seek to recover your food from the fiends. You navigate the world through the following keys:

W = move up,  
A = move left,  
S = move down,  
D = move right,  
ESC = open the menu, which allows you to pause and quit the game.  

Once you've collected all the meat present on the map, your goal is to return back to the home next to your starting 
position to win the game.

### Target Folder:

A target folder, containing all artifacts and files required for the project, has been provided for the user to utilize. If you wish to generate your own target folder to generate your own artifacts and files, type the "mvn clean" command in your terminal when its working directory is set to the Phase2 folder. This will remove the provided target directory.

### How to Run the Game -

Navigate your working directory to the Phase2 folder. Once inside, there are two ways to initialize the game that are 
listed below.

#### 1) Run the Game Using an IDE
Open your favourite IDE, such as Intellij, and use it to navigate to the Main.java file found in the src folder. This 
can be found at: 

CMPT276S24_group16/Phase2/src/main/java/com/example/Main.java.

Click on the Main.java file, and use your IDE to run the Main file. A target folder will be built on your personal 
computer, and the game will begin running.

#### 2) Run the Game Using the Terminal:
Navigate to the Phase2 folder and make it your working directory. Then, using Maven and your terminal, input the 
following commands:
    
mvn clean package  
java -jar target/Main.jar
    
The first command cleans the Phase2 folder of its target directory and then rebuilds it, creating a Main.jar file. The 
second command executes it. After these commands, the game runs as normal. If some other method is desired for running the JAR file,
the file can be found in the Phase2/target/ directory.

### How to Test the Game:

#### 1) Test the Game Through The Command Line With Maven:

To utilize this method, first open your terminal. Change its working directory the Phase2 folder of the project. Then, input one of the following two commands:

"mvn clean package"  
"mvn clean verify"

into your computer's terminal (or your IDE's terminal). This will automatically run the tests for you. WARNING: This 
option will cause tests to create multiple game windows, all of which run the game. If you have concerns about system resource usage, it 
may be more beneficial to follow method two. Once the command has been completed, files and directories related to testing will be generated within the target folder. To view the results of individual tests, merely navigate to the Phase2/target/surefire-reports directory to examine the results and time elapsed for each test. To visualize the branch and line coverage of the tests, navigate to the Phase2/target/site/jacoco directory. Then, open the index.html file in a browser of your choosing. This will open a website that displays the results of the tests and their coverage.


#### 2) Use IntelliJ to Visualize the Tests:

For this method, merely open the Phase 2 folder in IntelliJ. Navigate to the src/test/java folder, right-click it, and run the
folder with coverage. IntelliJ will then show the results of the test automatically. Tests can be run individually this way to avoid having multiple game windows open up simultaneously, as described above. This will not create the index.html file and the surefire-reports described above.

### JavaDocs:

JavaDocs for the project can be generated in a manner similar to testing the game. Merely enter either "maven clean package" or "maven clean verify" and a target folder will be produced that contains said documentation. The JavaDocs can be found at the Phase2/target/site/apidocs directory. To view the JavaDocs through a website, open either allclasses.html file or the index.html file.
