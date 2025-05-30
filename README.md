# 🧪 JUnit 5 Testing Project – HW-2-QA

This project demonstrates unit testing in Java using **JUnit 5**. It includes multiple business logic classes and their corresponding test cases.

---

## 🗂️ Project Structure

```
Junit5-HW-50D4/
├── src/
│   └── main/
│       └── najah/
│           ├── code/         # Java classes (e.g., Calculator, Product, Recipe)
│           └── test/         # Test classes (e.g., CalculatorTest, RecipeTest)
├── bin/                     # Compiled `.class` files
```

---

## 🚀 How to Run the Project

1. ✅ Ensure JDK 17+ is installed.
2. ✅ Clone or extract the project.
3. ✅ Open the project in **IntelliJ IDEA** or any Java IDE.
4. ✅ Navigate to `src/main/najah/test/` folder.
5. ✅ Right-click any test file and click **Run**.
6. ✅ Alternatively, use the terminal:
   ```bash
   cd Junit5-HW-50D4
   javac -d bin src/main/najah/code/*.java src/main/najah/test/*.java
   java -jar junit-platform-console-standalone.jar --class-path bin --scan-class-path
   ```

---

## 🧾 Classes Included

| Class             | Description                           |
|------------------|---------------------------------------|
| Calculator        | Basic arithmetic operations           |
| Product           | Represents a product entity           |
| Recipe            | Represents a cooking recipe           |
| RecipeBook        | Manages a collection of recipes       |
| UserService       | Handles user authentication logic     |

---

## ✅ Test Coverage

Each class is thoroughly tested using:
- ✅ Parameterized Tests
- ✅ Multiple Assertions
- ✅ Test Suites
- ✅ Lifecycle Hooks
- ✅ Timeout Checks
- ✅ Disabled Tests
