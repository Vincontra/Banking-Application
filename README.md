# 🏦 Banking Application

A simple **Java Console-Based Banking Application** built using **Core Java** and **Object-Oriented Programming (OOP)** principles. The project simulates common banking operations such as account creation, deposits, withdrawals, money transfers, account statements, and customer search while following a clean layered architecture.

---

## ✨ Features

- ✅ Open a new bank account
- 💰 Deposit money
- 💸 Withdraw money
- 🔄 Transfer money between accounts
- 📜 View account statement
- 📋 List all bank accounts
- 🔍 Search accounts by customer name
- ✔️ Input validation
- ⚠️ Custom exception handling
- 🏗️ Layered architecture (Service → Repository → Domain)

---

## 🛠️ Tech Stack

- **Language:** Java
- **Concepts Used:**
    - Object-Oriented Programming (OOP)
    - Collections Framework
    - Java Streams API
    - Exception Handling
    - UUID
    - LocalDateTime
    - Layered Architecture

---

## 📂 Project Structure

```
src/
│
├── app/                # Application entry point
├── domain/             # Domain models
├── exceptions/         # Custom exceptions
├── repository/         # In-memory data storage
├── service/            # Service interfaces
├── service/impl/       # Business logic implementation
└── util/               # Validation utilities
```

---

## 🏛️ Architecture

```
                User
                  │
                  ▼
          Console Application
                  │
                  ▼
           Service Layer
                  │
                  ▼
         Repository Layer
                  │
                  ▼
         In-Memory Collections
```

---

## 📌 Banking Operations

### 1. Open Account
- Create a new customer
- Generate a unique account number
- Select account type (Savings / Current)
- Optional initial deposit

### 2. Deposit
- Deposit money into an existing account
- Balance updates automatically
- Transaction recorded

### 3. Withdraw
- Withdraw money
- Checks for sufficient balance
- Prevents invalid withdrawals

### 4. Transfer
- Transfer money between two accounts
- Updates both account balances
- Creates transaction records

### 5. Account Statement
Displays:

- Transaction Type
- Amount
- Timestamp
- Notes

### 6. List Accounts

Displays all accounts sorted by account number.

### 7. Search Customer

Search accounts using the customer name.

---

## 📁 Domain Models

- Customer
- Account
- Transaction
- Type (Transaction Type)

---

## ⚠️ Exception Handling

The application includes custom exceptions such as:

- `AccountNotFoundException`
- `DuplicateAccException`
- `InsufficientBalException`
- `ValidationException`

These improve code readability and make error handling more robust.

---

## ✔️ Input Validation

The application validates:

- Customer name
- Email format
- Account type
- Transaction amount

Invalid inputs are rejected before business logic is executed.

---

## 🎯 Concepts Demonstrated

- Object-Oriented Design
- Layered Architecture
- Encapsulation
- Abstraction
- Collections Framework
- Java Streams
- Exception Handling
- Input Validation
- Clean Code Practices

---