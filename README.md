# README #

This project was part of a company hiring process. The idea is to build a pure JavaSE RPG game to be played through the command line.

In this game the player should be able to:

	- Create a character;
	- Explore;
	- Gain experience through fighting; and
	- Save and Resume
	

## How to build and test

This is a Java project that uses Gradle to perform build, test and code analysis task. 

To build this application run on the source root folder (also run the unit tests):
```
./gradlew build
```


To only run the unit tests try:
```
./gradlew test
```


You will find the report at `<repo-folder>/build/reports/test`

To analyze the code coverage run:
```sh
./gradlew jacoco
```

You will find the report at `<repo-folder>/build/reports/jacoco/test/html`

You can also analyze the code using SonarQube (you will need a Sonar Server running at localhost:9000:
```sh
./gradlew sonarqube
```


## How to run

After you build the application, you can find the JAR file at `<repo-folder>/build/libs`
To run the application you can execute the following command:
```sh
java -jar <path/to/libs>/rpg-cli-0.1.0.jar
```


If you are in the project's root folder:
```sh
java -jar build/libs/rpg-cli-0.1.0.jar
```

If you want to play a Game of Thrones themed game run:
```sh
java -jar build/libs/rpg-cli-0.1.0.jar got
```

## How to play

The gameplay is pretty simple. You should follow the instructions in the messages presented to you. Pick the options typing the letters or numbers at the beginning of each option. Remember the options are case-sensitive.

## How to extend

If you are willing to extend the game here is a brief commentary about the class types and their responsibilities:

**View**: Are the presentation layer. Responsible for write the layout to the user and read his/her input.
**Presenter**: Controls the application flow starting the View, receiving its input and calling Repositories and next Presenters.
**Model**: Are the domain objects.
**Repository**: Handles raw data conversion to Model types.
**DataHandler**: Fetch and save the data to a permanent or external source.

### Practical example
Let's say you want to add a new step on the Character creation. You want the user to pick a Weapon and the weapon options changes according to the Game theme. Additionally, this info would be coming from a network Socket.

To do so you would need to create:
* A **_PickWeaponView_** with the layout presenting and asking for the option;
* A **_PickWeaponPresenter_** that retrieves the Weapon data from the repository, set it to the view and start it, then it takes decisions according to the user input.
* A **_Weapon_** class that maps the weapon data as object (domain object)
* A **_WeaponRepository_** that transforms the raw data into a Weapon model.
* Finally, a **_SocketDataHandler_** that encapsulates all the network flow and returns the data to the repository.

Of course, always remember to add Unit tests for your new code. :)

## Creating a new theme

It is possible to create new themes to the game. You will need to:
1. Create a new folder into `<repo-folder>/src/main/resources/themes`
2. Into this folder create the files:
	* _char-classes.xcsv_ - For the character classes available; 
	* _locations.xcsv_ - For the locations available; 
	* _enemies.xcsv_ - For the enemies available; 
	* _locations-locations.xcsv_ - For the relation between closeby locations; 
	* _locations-enemies.xcsv_ - For the enemies available in each location.


It uses a eXtended Comma-Separated Values (xcsv) format, in which you can add lines starting with # to add comments.

You can follow the example in the `<repo-folder>/src/main/resources/themes/got`

In case of any question feel free to contact me: levifranca[AT]gmail.com