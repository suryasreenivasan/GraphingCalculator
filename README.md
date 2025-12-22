# Graphing Software in Java

A graphing tool built in Java, inspired by Desmos. This project allows users to input mathematical expressions and visualize their graphs in a simple GUI.

---

## Features

- **Graphing Functions:** Plot mathematical functions like `sin(x)`, `cos(x)`, `tan(x)`, `ln(x)`, `log(x)`, and more.  
- **Basic Arithmetic:** Supports `+`, `-`, `*`, `/`, and `^` operators.  
- **Function Parsing:** Handles nested functions, parentheses, and unary minus.  
- **Custom Math Engine:** Implements trigonometric, logarithmic, and inverse functions from scratch without relying on `Math.*` for all calculations.  
- **Zoomable Scale:** Adjustable graph scale (default is 50 pixels/unit).  
- **Interactive Input:** Enter expressions in a text field and render the graph instantly.  

---

## Limitations

- **No Implicit Multiplication:** Must explicitly use `*` (e.g., `2x` is **invalid**, use `2*x`).  
- **Infinite Discontinuities:** Expressions like `1/x` may show vertical jumps since the program doesn’t handle limits or asymptotes.  
- **Limited Function Support:** Only the following functions are implemented:  
  - `sin`, `cos`, `tan`  
  - `asin`, `acos`, `atan`  
  - `ln`, `log`  
- **Precision Issues:** The custom MathEngine uses Taylor series approximations, which may be inaccurate for very large inputs and causes small discontinuities in functions such as sin(x) during sign switches. 
- **No Multi-Variable Support:** Only single-variable functions of `x` are supported.  
- **No Implicit Error Handling:** Some invalid expressions may throw runtime exceptions.  

---

## Usage

1. Download Java 22 or newer
2. Run the JAR File
