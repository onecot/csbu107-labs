# Lab 3: Encapsulation and Inheritance

**Course:** IT002 / CSBU107 – Object-Oriented Programming
**Topic:** Encapsulation, access modifiers, getters and setters, static vs instance members, inheritance and its three structures (single, multilevel, hierarchical).

## Concepts practiced

- **Encapsulation** – `private` fields, exposed through constructors, getters and validated methods; state changes are guarded by the class, not by callers.
- **Access modifiers** – `private` / package-private / `protected` / `public` and where each is reachable.
- **Getters and setters** – read-only attributes get a getter only; validation lives with the data it protects.
- **Static vs instance** – class-level members shared by all objects vs per-object members.
- **Inheritance** – `extends`, `super(...)`, method overriding with `@Override`.
- **Polymorphism** – a `List<Superclass>` holding mixed subclasses and dispatching one abstract method to each subclass implementation.
- **Composition** – a class that *has* other objects (used in Exercise 5), as distinct from *is-a* inheritance.

## Exercises

| # | Exercise | Shape of the model | Polymorphic method | Status |
|---|----------|--------------------|--------------------|--------|
| 1 | University Student Graduation Management | `Student` (abstract) → `CollegeStudent`, `UniversityStudent` | `isEligibleForGraduation()` | done |
| 2 | Ticket Management for Dam Sen Park | `Ticket` → `FullPackageTicket`, `PartialTicket` | `getPrice()` | todo |
| 3 | Hotel Room Rental Management | `Person` → `Guest`, `Staff`, plus `Room` | — | todo |
| 4 | Singer Salary Management for XYZ Company | `Singer` → `RegularSinger`, `FamousSinger` | `calculateSalary()` | todo |
| 5 | Cosmetic Store Order Management | `Order`, `Customer`, `Package` → package types, `Product` → product types | `getPrice()` | todo |

## Compile and run

Every exercise lives in its own folder and is self-contained in a single `Main.java`.

```bash
cd ex1
javac -d out Main.java
java -cp out Main
```

Feed input from a file:

```bash
java -cp out Main < input.txt
```

## Exercise 1

Requirements from `Lab_3.pdf`:

- Accept a list of students from both programs.
- Count the students who satisfy their program's graduation criteria.
- Find the University student with the highest GPA.
- Common superclass with encapsulated fields; subclass-specific methods.

Thresholds implemented:

| Program | Credits | GPA | Additional condition |
|---------|---------|-----|----------------------|
| College | ≥ 120 | ≥ 5.0 | graduation exam score ≥ 5.0 |
| University | ≥ 170 | ≥ 5.0 | thesis score ≥ 5.0 |

### Test cases

**TC1 – suggested test from the PDF** (expect `Eligible students: 1`)

```
2
1
SE001
Nguyen Van A
123 Le Loi
125
6.5
7.0
2
SE002
Tran Thi B
456 Nguyen Hue
165
8.0
AI for medical diagnosis
9.0
```

The College student (125 credits, GPA 6.5, exam 7.0) is eligible. The University
student (165 credits < 170) is not, even with GPA 8.0 and thesis 9.0.

**TC2 – boundary values** (expect `Eligible students: 2`, only C1 and U1 eligible)

```
6
1
C1
College One
Addr
120
5.0
5.0
1
C2
College Two
Addr
119
5.0
5.0
1
C3
College Three
Addr
120
4.99
5.0
1
C4
College Four
Addr
120
5.0
4.99
2
U1
Uni One
Addr
170
5.0
Thesis A
5.0
2
U2
Uni Two
Addr
169
5.0
Thesis B
5.0
```

**TC3 – highest GPA need not be eligible** (expect `Eligible students: 1`, winner `U1` with GPA 9.9)

```
2
2
U1
Uni LowCredits
Addr
100
9.9
Thesis A
9.0
2
U2
Uni Ok
Addr
180
7.5
Thesis B
7.0
```

**TC4 – no University student** (expect `No university student found.`)

```
1
1
C1
Only College
Addr
130
7.0
6.0
```

**TC5 – empty roster** (expect `Eligible students: 0` then `No university student found.`)

```
0
```

## Submission

GitHub link to this repository. See `../README.md`.
