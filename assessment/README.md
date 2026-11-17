# Lab Assessment: Smart Campus Device Management

**Course:** CSBU107 – Object-Oriented Programming

Console program that manages smart devices on a campus. Each device type has its own
efficiency-score formula; a `List<SmartDevice>` holds all of them and the operations run
through dynamic binding.

## Files

| File | Purpose |
|------|---------|
| [Main.java](Main.java) | Menu, input handling, and the reporting operations |
| [SmartDevice.java](SmartDevice.java) | Abstract base class: shared fields, getters, `displayInfo` |
| [SmartLight.java](SmartLight.java) | Smart light with automatic brightness adjustments |
| [SmartAC.java](SmartAC.java) | Air conditioner with set temperature and energy-saving hours |
| [SmartCamera.java](SmartCamera.java) | Camera with resolution and abnormal detections |
| [input.txt](input.txt) | Sample input for the program |
| [25560052_LabExam.zip](25560052_LabExam.zip) | Submission archive |

Device types in the entry form: `1` SmartLight, `2` SmartAC, `3` SmartCamera.

## Features

| # | Menu option | Status |
|---|-------------|--------|
| 1 | Enter a list of smart devices | done |
| 2 | Display detailed information | done |
| 3 | Classify devices by daily operating time | done |
| 4 | Highest efficiency score device per type | done |
| 5 | Search by installation-area keyword (case-sensitive) | done |
| 0 | Exit | done |

Operating-time classes: low (< 6 h/day), medium (6 to < 12 h/day), high (12+ h/day).
Efficiency scores are clamped to the 0–100 range.

## OOP concepts used

Abstraction and abstract methods (`SmartDevice.calculateEfficiencyScore`), inheritance and
`super` constructor chaining, method overriding (`displayInfo`), polymorphism through the
`SmartDevice` list, encapsulation with private fields, and constructor overloading
(no-arg, full, and copy constructors).

## Run

From the assessment directory:

```bash
javac -d out Main.java
java -cp out Main
```

[input.txt](input.txt) holds sample device-entry data for menu option 1 (6 devices), so it
covers the entry step only:

```bash
java -cp out Main < input.txt
```

A Java JDK is required.
