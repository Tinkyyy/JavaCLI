# Java CLI - Java Command Line Interface

## Description
It is a simple template to start the development of a CLI with Java.
Every commands can support the `man` command.

Available commands are:
+ help: You can view the list of commands available with usage and aliases.
+ man: You can read the command manuals.

## Installation
You must import this project as a gradle project.
You must use at least JDK 11 to be able to compile the project.

When the project is ready you can compile it to have an executable version with the command:
```bash
gradlew build
```
## Example

```bash
help
```
