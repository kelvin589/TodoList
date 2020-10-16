# To-do List
A simple to-do list created with Java

## Table of Contents
* [Introduction](#introduction)
* [Possible Improvements](#possible-improvements)
* [Technologies](#technologies)
* [Setup](#setup)

## Introduction
This is the first project I have uploaded to github. It is a simple to-do list created using Java and JavaFX for the user interface. The application is relatively simple in the functions provided, of which these include:
* Adding a new task to the list
* Removing a task from the list
* Marking a task as complete (as well as incomplete)
* The text files read/saved have a specific pattern (`task` `boolean`)
  * Reading a text file from a given path (a text file is created if it does not exist)
  * Saving to a text file with a given path (a text file is created if it does not exist)
  
<img src="https://user-images.githubusercontent.com/72221490/95793778-408a2380-0cde-11eb-9cc3-d4288ad82db1.png" alt="Image of the todo list" width="300">

## Possible Improvements
Some improvements which could be added to the program to improve its overall usability and add functionality:
* The option to remember a default path to a text file and displaying this list when opening the program
* Remembering previous paths to files
* Improving the look of the UI to make it more modern
* Incorporating deadlines for tasks
* Instead of opening a dialog for adding a task, the user should be able to click the list and start typing
* Include stylization options for text such as bold, italic and colour
* Have multiple tabs linking to different to-do list files
* Take shortcuts from the user, for instance, the commands `ctrl + s` would save the file

## Technologies
This project is created with:
* Java 13.0.1
* JavaFX 11.0.2

## Setup
To run this project:
1. Fork this project to add it to your own github account
2. Download the project or clone the project to get a local copy
3. Import the project into your IDE
4. Run the project using the `Main` class
