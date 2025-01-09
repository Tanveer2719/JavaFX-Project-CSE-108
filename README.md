# CSE-108: Basic JavaFX Project 

This repository contains a basic JavaFX project that incorporates Java networking features. It serves as a simple demonstration of building graphical user interfaces with JavaFX while utilizing networking capabilities.

---

## Prerequisites

To run this project, ensure the following:

1. **Java Development Kit (JDK)**: Version 11 or higher installed.
2. **JavaFX SDK**: Download the JavaFX SDK appropriate for your platform from [Gluon](https://gluonhq.com/products/javafx/).
3. **VS Code**: Installed with the "Java Extension Pack."

---

## Setting up the Path to JavaFX Library

To run this project in **VS Code**, you need to update the path to the JavaFX library in the `launch.json` and `tasks.json` files located in the `.vscode` directory.

### Steps to Update the Path:

1. Locate your JavaFX SDK directory. For example, in my case, it is:
   ```
   /opt/javafx-sdk/lib
   ```

2. Open `.vscode/launch.json` and update the `vmArgs` entry to include the correct path:

   ```json
   {
       "vmArgs": "--module-path /opt/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml"
   }
   ```

3. Open `.vscode/tasks.json` and update the `args` entry to include the correct path:

   ```json
   {
       "args": [
           "--module-path",
           "/opt/javafx-sdk/lib",
           "--add-modules",
           "javafx.controls,javafx.fxml"
       ]
   }
   ```

4. Save the changes and reload VS Code.

---

## How to Run the Project

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/javafx-networking-project.git
   cd javafx-networking-project
   ```

2. **Open the project in VS Code:**
   Open the project folder in Visual Studio Code.

3. **Run the project:**
   - Press `F5` to start debugging.
   - Alternatively, use the Run and Debug panel to start the application.

---


Feel free to explore and modify the code. If you encounter any issues, open an issue or reach out for assistance!

