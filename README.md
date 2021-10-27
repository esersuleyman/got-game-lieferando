<p align="center">
  <img src="game-of-three-api\src\main\resources\static\logo.png">
</p>

# GOT - Game Of Three Coding Challenge

## Goal
The Goal is to implement a game with two independent units – the players –
communicating with each other using an API.

## Description
When a player starts, it incepts a random (whole) number and sends it to the second
player as an approach of starting the game. The receiving player can now always choose
between adding one of {1, 0, -1} to get to a number that is divisible by 3. Divide it by three. The
resulting whole number is then sent back to the original sender.

The same rules are applied until one player reaches the number 1(after the division).
See example below.

For each "move", a sufficient output should get generated (mandatory: the added, and
the resulting number). Both players should be able to play automatically without user input. The
type of input (manual, automatic) should be optionally adjustable by the player.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to run the software

```
java11
maven
```

### Installing & running

A step by step guide that tell you how to get a development env running


```
mvn clean install
java -jar ./game-of-three-api/target/game-of-three-api-0.0.1-SNAPSHOT.jar

```
```

## Authors

- **Süleyman Eser** - [Süleyman Eser](https://github.com/esersuleyman)
