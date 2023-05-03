## ChessMeister A Team (Team Red)


## Authors
** Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat**


## Date of Submission
04/19/2023


## Usage 'How to run the program?' via Command Line

Step 0. Ensure you are in the folder where the src folder is, you can check by running the command
'ls' if on MacOS/Linux or 'DIR' on Windows 

Step 1. javac src/controller/*.java src/databases/*.java src/enums/*.java src/interfaces/*.java src/model/*.java src/ui_cli/*.java

Step 2. java Driver


## Usage 'How to run the program?' via Eclipse or Intellij

Step 0. Import the Java Project

Step 1. Click on the Driver.java file in the workspace then press run.

## Video Walkthrough

<img src='https://i.gyazo.com/9436002a405c842b3af74ad5fc8a14e8.mp4'/>



## Description 'What is the program supposed to do?'
The program opens up a main menu that offers options for the player. The options that the main 
menu offers are viewing the rules, signing in or signing up if the user hasn't enter their
information to the database before playing, defining of who are playing, loading in a game that
the user was previously playing, altering settings, and, of course, playing chess. In regards to
the settings, the default option for viewing the game is BoardColorCLI, which can be changed to 
MonoColorCLI. The options for undo/redo and show moves are also enabled by default.

When the player initiates a game, they can move, undo (if enabled), redo, show moves (if enabled),
save game, offer draw, and concede. Move prompts the user the current location of the piece
that they want to move and the destination that they want to move to, which needs to be written
in chess notation. If the opponent is in check and has no valid moves, then that's checkmate for 
the user with the game ending and the user's wins/losses being incremented if they are within the
database.

Undo allows the user to go back to a previous turn and redo allows the user to go back to a turn
above the current turn. Show move prompts the user for a specific piece so that they have a visual
display of all of the valid destinations that the user can choose. Save prompts the user to create
a save file after progress has been made within the game. The user inputs a file path to store the
location of their save progress and the game concludes with no wins/losses/draws. Draw prompts the
opponent to agree to a draw. If the opponent agrees by inputting yes, a draw is updated to both
of the player's stats in the database and the game is concluded. The last option allows the user to
accept a loss and end the game.

Other conditions that end the game include draw by stalemate, draw by the 50 move rule, and draw 
by the threefold repetition rule. Draw by stalemate ends similarily to a checkmate except that the
opponent isn't in check. Draw by the 50 move rule checks if the game made it past 50 turns of no 
pawn movement or no capturing occurs during that time. Finally, draw by threefold reptition checks 
if a board state occurs three times in a row in the game.


## Existing Errors
None.


### Requirements
Azul Java SDK Version 17
