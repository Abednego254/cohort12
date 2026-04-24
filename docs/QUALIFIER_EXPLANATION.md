# CDI Qualifiers: Resolving Dependency Ambiguity

This document explains the implementation of CDI Qualifiers in the `cohort12` project to handle multiple implementations of the same interface.

## The Problem: Dependency Ambiguity

When we have an interface (like `Validate`) and multiple classes implementing it (like `ValidateTrainer` and `ValidateTrainee`), the CDI container gets confused. If you try to `@Inject Validate validate;`, the server won't know which one to provide and will fail to deploy with an **AmbiguousResolutionException**.

## The Solution: Qualifiers

Qualifiers are custom annotations that allow us to "tag" specific implementations and then request them at the injection point.

### 1. Creating the Qualifier (`ValidatorQualifier.java`)
We created a custom qualifier using the `@Qualifier` meta-annotation. It includes an enum to choose the specific validation target.
```java
@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface ValidatorQualifier {
    ValidationChoice value();
    enum ValidationChoice { TRAINEE, TRAINER, COURSE, SCHOOL }
}
```

### 2. Tagging Implementations
Each implementation is now marked with the qualifier and a specific choice:
- **ValidateTrainer**: `@ValidatorQualifier(ValidationChoice.TRAINER)`
- **ValidateTrainee**: `@ValidatorQualifier(ValidationChoice.TRAINEE)`

### 3. Requesting a Specific Bean
In our servlets, we now tell the container exactly which implementation we need:
```java
@Inject
@ValidatorQualifier(ValidatorQualifier.ValidationChoice.TRAINER)
Validate validate;
```

## Benefits
- **Type Safety**: The compiler and container ensure the right beans are used.
- **Flexibility**: We can easily add new validation logic for new entities without changing existing code.
- **Clean Code**: Injection points are explicit about their dependencies.

---
*Created as part of the cohort12 CDI Qualifier implementation task.*
