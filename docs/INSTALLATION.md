# INSTALLATION.md

# Installation Instructions for Make A Square Game

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- **Java Development Kit (JDK) 17 or higher**: Download and install the JDK from the [Oracle website](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or use a package manager.
- **Apache Maven**: Download and install Maven from the [Maven website](https://maven.apache.org/download.cgi) or use a package manager.
- **IDE**: A suitable IDE such as Visual Studio Code or IntelliJ IDEA for Java development.

## Clone the Repository

1. Open a terminal or command prompt.
2. Clone the repository using the following command:

   ```
   git clone https://github.com/3ab3z2/PuzzleCraft-4x4-Solver.git
   ```

3. Navigate to the project directory:

   ```
   cd Make_A_Square_Game
   ```

## Build the Project

1. Ensure you are in the root directory of the project.
2. Run the following command to build the project using Maven:

   ```
   mvn clean install
   ```

   This command will compile the source code, run tests, and package the application.

## Run the Application

1. After building the project, you can run the application using the following command:

   ```
   mvn javafx:run
   ```

   This command will start the JavaFX application.

## Additional Configuration (Optional)

- If you are using Visual Studio Code, you may want to configure the IDE settings for Java and Maven. Refer to the `.vscode` directory for configuration files that can help set up your environment.

## Troubleshooting

- If you encounter issues during installation or running the application, ensure that your JDK and Maven installations are correctly configured in your system's PATH.
- Check the console output for any error messages and consult the documentation for further assistance.

## Conclusion

You are now ready to use the Make A Square Game application. For usage instructions, please refer to the `USAGE.md` file in the `docs` directory.