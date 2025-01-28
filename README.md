# Collaborative Whiteboard Application

A real-time drawing platform that enables multiple users to collaborate on a shared canvas. This project integrates modern frontend and backend technologies to ensure seamless communication, scalability, and responsiveness.

## Introduction

The Collaborative Whiteboard Application is designed to provide a robust and user-friendly platform for real-time collaboration. It allows multiple users to interact, draw, and see updates on a shared canvas simultaneously, leveraging technologies for high performance and efficiency.

## How to Run the Project

This project uses Gradle for managing dependencies and automation. Follow these steps to run the application:

### Build the Project
Navigate to the `Frontend` and `Backend` folders and build the project using:
```bash
./gradlew build
```

### Run the Application
After a successful build, execute the following commands:
- **Backend**: 
  ```bash
  java -jar build/libs/JavaProj-1.0.jar
  ```
- **Frontend**: 
  ```bash
  java -jar build/libs/SwingGradleApp-1.0-SNAPSHOT.jar
  ```

The whiteboard interface will load, allowing you to log in and start collaborating. Use any username to create or join a board.

**Note**: Pre-built JAR files are provided for convenience. Use the `java -jar` command to run them directly without rebuilding.

## Technologies Used

### Frontend
- **Swing**: For graphical user interface development.
- **WebSockets**: Enables real-time, bidirectional communication.
- **BufferedImage**: Efficient canvas rendering.
- **ExecutorService**: Handles asynchronous message transmission.

### Backend
- **Spring Boot**: Framework for RESTful APIs and WebSocket endpoints.
- **WebSocket**: Manages real-time updates.
- **Gradle**: Manages project dependencies and builds automation.
- **JdbcTemplate**: Simplifies database operations such as user and board management.
- **ConcurrentHashMap**: Ensures thread-safe management of WebSocket sessions and board locks.

## Conceptual Overview

### Frontend

#### Graphical User Interface
- **Login Panel**: Allows users to input their username and initiate a session.
- **Whiteboard Panel**: Main drawing area for real-time collaboration.
- **Tools Panel**: Includes tools like pens, erasers, and adjustable settings for pen size.

#### Real-Time Communication
- **WebSocket Connections**: Handle real-time updates, broadcasting drawing actions to all connected clients to ensure synchronized whiteboard views.

#### Data Handling
- **JSON Serialization**: Messages containing drawing points and actions are serialized into JSON for transmission.
- **Gson**: Facilitates efficient parsing of JSON data.

#### Drawing Mechanism
- Drawing actions are captured through mouse events and transmitted over WebSockets for rendering across connected clients.

### Backend

#### WebSocket Communication
- WebSocket handlers listen for drawing actions and broadcast updates to all active clients.

#### Board Management
- A centralized board matrix is stored in the database.
- Each board session ensures consistency by managing updates and persisting changes.

#### Concurrency Control
- **ReentrantLock**: Ensures thread-safe updates to the shared board.
- **ConcurrentHashMap**: Manages concurrent WebSocket sessions for each board.

#### User Management
- Users are dynamically created or retrieved upon login. Each user session is linked to the shared board.

## Challenges Faced

### Frontend Challenges
- **Handling Large Pen Sizes**: High data generation with larger pen radii led to oversized WebSocket messages.
- **Ensuring Drawing Precision**: Managing point density without pixelation.
- **Connection Stability**: Preventing WebSocket disconnects under heavy load.

### Backend Challenges
- **WebSocket Session Management**: Handling multiple concurrent sessions for the same board.
- **Concurrency Control**: Preventing race conditions during simultaneous updates.
- **Low-Latency Updates**: Optimizing performance to handle real-time board updates.
- **Data Serialization**: Efficiently transmitting large board matrices.

## Solutions Implemented

### Frontend Solutions
- **Data Deduplication**: Switched to `HashSet` for storing drawing points, eliminating redundant data.
- **Batch Processing**: Points are grouped and transmitted in smaller subsets to reduce message size.
- **Asynchronous Messaging**: `ExecutorService` handles message transmissions without blocking the GUI thread.
- **Enhanced Drawing Precision**: Removed step size to ensure smooth and detailed drawing quality.

### Backend Solutions
- **Session Mapping**: Implemented session mapping to allow multiple WebSocket connections for a single board.
- **ReentrantLock**: Ensured thread safety for simultaneous board updates.
- **Optimized Data Serialization**: Used compact string representations of the board matrix for efficient storage and transmission.

## Conclusion

The Collaborative Whiteboard Application successfully integrates frontend and backend technologies to deliver a real-time, interactive platform for users. Key strategies such as WebSocket communication, concurrency control, and optimized data management address significant challenges like high data transmission and performance bottlenecks. By leveraging Java Swing for GUI development, Spring Boot for backend services, and efficient concurrency mechanisms, the application ensures a seamless and responsive collaborative drawing experience.

Teammate:
Allen Abraham aa10770@nyu.edu
