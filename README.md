
<h1 align="center" style="font-size:3em; color:#4A90E2;">💬 Java Chat Application</h1>
<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Sockets-Networking-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Swing-GUI-green?style=for-the-badge" />
</p>

---

## 🧠 <span style="color:#ff6347"><b>Overview</b></span>

This is a **feature-rich, secure, and user-friendly** multithreaded Chat Application built using **Java**, featuring:
- 📡 Real-time messaging via **TCP Sockets**
- 🖥️ Graphical User Interface using **Java Swing**
- 🔐 **AES encryption** for private messages
- ✅ Reliable **message delivery status**
- ✨ Typing indicators, user list, command support, and more!

---

## 🎯 <span style="color:#f39c12"><b>Key Features</b></span>

| Feature | Description |
|--------|-------------|
| 🗨️ **Public & Private Chat** | Supports general chat and `@username`-based private messaging |
| 🔐 **Encrypted Messaging** | Messages are AES-encrypted during transmission |
| 💡 **Typing Indicators** | Shows "username is typing..." to others in real-time |
| 👥 **Online/Offline User List** | Sidebar with green dot for online users; offline users still visible |
| ✅ **Delivery Status** | Checkmarks indicate if the message was **sent** or **seen** |
| ⚡ **Auto Port Retry** | Automatically connects to available ports |
| 🧠 **Command Recognition** | `/help` and `/logout` commands recognized and executed instantly |
| 🛠️ **Robust Error Handling** | Maximum exception handling for stability and resilience |
| 🧼 **Input Validation** | Prevents malformed or blank messages/commands |
| 🎨 **Modern UI** | Swing-based interface with styled text and intuitive layout |

---

## 🖥️ <span style="color:#9b59b6"><b>Architecture</b></span>

```plaintext
        ┌─────────────┐       ┌─────────────┐       ┌─────────────┐
        │  Client 1   │ <---> │   Server    │ <---> │  Client 2   │
        └─────────────┘       └─────────────┘       └─────────────┘
              ▲                                         ▲
       GUI Interface                             Encrypted Messages
       Typing Indicators                          Private Chatting
````

* Multi-client support via **threads**
* Real-time **broadcast and private messaging**
* Central server manages clients and user statuses

---

## 🔧 <span style="color:#3498db"><b>How to Run</b></span>

### 🔹 Requirements

* Java JDK 8 or above
* IDE like IntelliJ, Eclipse or terminal/CLI

### 🧪 Launch Instructions

<details>
  <summary><strong>💻 Server</strong></summary>

```bash
cd Chat_App_updated/server
javac Server.java
java Server
```

</details>

<details>
  <summary><strong>👤 Client</strong></summary>

```bash
cd Chat_App_updated/client
javac ChatGUI.java
java ChatGUI
```

</details>

---

## 📝 <span style="color:#2ecc71"><b>Commands Supported</b></span>

| Command     | Action                   |
| ----------- | ------------------------ |
| `/help`     | View available commands  |
| `/logout`   | Gracefully exit the chat |
| `@username` | Send a private message   |

---

## 📁 <span style="color:#e67e22"><b>Project Structure</b></span>

```
Chat_App_updated/
├── client/
│   ├── ChatGUI.java         🎨 GUI interface
│   ├── Client.java          🔗 Socket connection
│   ├── AESUtil.java         🔐 AES encryption helper
│   ├── MessageStatus.java   ✅ Seen/sent status manager
├── server/
│   ├── Server.java          🧠 Core server logic
│   ├── ClientHandler.java   🧵 Handles each client thread
│   ├── users.txt            📄 Stores all usernames
```

---

## 🔐 <span style="color:#1abc9c"><b>Security Features</b></span>

* AES-256 encryption for all private messages
* Commands and input are sanitized to prevent malformed input
* Message visibility indicators show **delivered** and **read** status

---

## 🧠 <span style="color:#e74c3c"><b>Robustness & Error Handling</b></span>

* Graceful fallback for network errors and disconnections
* Handles invalid user commands with meaningful responses
* Auto-retries port connections (`MAX_PORT_TRIES = 10`)
* Prevents sending empty messages or commands

---

## 🏁 <span style="color:#34495e"><b>Final Notes</b></span>

> 🚀 This Java Chat App is more than just a project – it's a complete real-time communication system. Whether you're learning about networking or building full-scale apps, this is a solid foundation to build upon.

---

<p align="center">
  Made with ❤️ in Java • Happy Chatting! ✨
</p>

