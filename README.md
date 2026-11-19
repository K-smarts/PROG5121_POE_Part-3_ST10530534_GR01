# PROG5121 POE — Registration/Login + Chat App
# PROG5121 POE - Part 1

**Student Information:** Katleho Mphuthi 
**Student Number:** ST10530534
**Module:** PROG5121 Programming 1A

## Project layout
```
POE/
├── pom.xml
├── README.md
└── src
    ├── main/java
    │   ├── login/
    │   │   ├── Login.java     — registration & login logic
    │   │   └── Main.java      — console runner for registration/login
    │   └── chat/
    │       ├── Message.java   — chat message logic (send/store/disregard, reports)
    │       └── ChatMain.java  — console runner for the chat app
    └── test/java
        ├── login/LoginTest.java
        └── chat/MessageTest.java
```

## Project layout
```
POE/
├── pom.xml
├── README.md
└── src
    ├── main/java
    │   ├── login/
    │   │   ├── Login.java     — registration & login logic
    │   │   └── Main.java      — console runner for registration/login
    │   
    └── test/java
        ├── login/LoginTest.java
        
```

## Part 1 — Login 
`Login.java` implements the six methods from the method table
(`checkUserName`, `checkPasswordComplexity`, `checkCellPhoneNumber`,
`registerUser`, `loginUser`, `returnLoginStatus`), with messages and
validation rules taken from the "Conditions and Messages" tables in
your screenshots. `LoginTest.java` uses the exact test data shown
(e.g. `"kyl_1"`, `"Ch&&sec@ke99!"`, `"+27838968976"`).


## My Logic and Flow
- checkUserName: Checks if username contains underscore "_" and length <=5 using if-statement and String.contains().
- checkPasswordComplexity: Checks length >=8, has capital letter, number, and special character using regex.
- registerUser: Returns success/failure messages based on validation.
- loginUser: Compares entered credentials with stored credentials.


## Running the tests
This sandbox has no internet access to Maven Central, so I couldn't
run `mvn test` here. I opened the project in NetBeans with
internet access and run:
```
mvn test
```
Both `LoginTest` and `MessageTest` passed.

## More of what is done for Part 1
- Push to GitHub with a minimum of six commits per part.
- No GUIs / no JOptionPane — `Main.java` is a
  plain console app.


## How to Run
1. Open project in NetBeans.
2. Right-click project > Run.
3. To run tests: Click Test or run `mvn test` (All tests should show "BUILD SUCCESS").

## GitHub Commits
This repository has 6+ commits showing Part 1 development process.
- Commits include: initial code, validation methods, tests, .gitignore fix, README update.

## Video Presentation
YouTube Link: https://youtu.be/A7G5dlRZiNM

## References
- No AI voice used, I used my own voice for presentation.


# PROG5121 POE - Part 1

## Project Description

This is Part 2 of the Chat App and it is an extension for Part 1 login. The app allows a user to send QuickChat messages after login. It uses JOptionPane for input and output.

## Part 2 chat Features:

#Asks how many messages the user wants to send (JOptionPane).

#CheckMessageID() - checks if Message ID is not more than 10 characters.

#CheckRecipientCell() - checks recipient starts with +27 and is <=12 chars including +.

#CreateMessageHash() - creates hash in format 00:0:WORD - first 2 digits of ID, message number, first and last word uppercase.

#SentMessage() - options: 1=Send, 2=Disregard, 3=Store - returns "Message successfully sent." etc.

#Checks message length - if >250 shows "Message exceeds 250 characters...".

#Displays total messages sent at the end.

## How to Run
1. Open project in NetBeans.
2. Right-click project > Run.
3. To run tests: Click Test or run `mvn test` (All tests should show "BUILD SUCCESS").



# PROG5121 - POE Part 3

## Project Description
Part 3 of the Chat Application. This part focuses on managing messages
after the user has logged in.

## Features - Part 3

- Send multiple messages
- Store messages in an array
- Display all sent messages with sender and recipient
- Display the longest message sent
- Search for a message by Message ID
- Search for all messages sent to a specific recipient
- Delete a message using its Message Hash
- Display a full report of all sent messages
- Menu system with options 1 to 8, and 9 to quit

## How To Run
1. Open the project in Apache NetBeans
2. Make sure the Main Class is set to `login.Main`
3. Click Run (Green Play button)
4. Login with your registered details
5. Use the menu to send and manage messages

## Main Menu Options
1. Send Message
2. Show Recently Sent Messages
3. Display Longest Message
4. Search Message by ID
5. Search Messages by Recipient
6. Delete Message by Hash
7. Display Report
8. Show All Messages
9. Quit

## Project Structure
- Login.java - Handles registration and login
- Message.java - Handles message creation, validation and hash
- Main.java - Main menu and message management for Part 3

