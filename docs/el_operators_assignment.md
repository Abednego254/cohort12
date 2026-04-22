# JSP EL — Logical & Relational Operators (Assignment)

> **Project:** cohort12
> **Date:** April 21, 2026

---

## Overview

This assignment extends the JSP Expression Language lesson by implementing
**relational** and **logical** operators in EL. These allow you to compare
values and build conditional logic directly inside JSP — without a single
Java scriptlet.

---

## Operators Reference

### Relational Operators

Used to **compare two values**. Returns `true` or `false`.

| Symbolic | Alphabetic Alias | Meaning                  | Example                      | Result  |
|----------|-----------------|--------------------------|------------------------------|---------|
| `==`     | `eq`            | Equal to                 | `${10 == 10}` / `${10 eq 10}` | `true`  |
| `!=`     | `ne`            | Not equal to             | `${10 != 5}`  / `${10 ne 5}`  | `true`  |
| `<`      | `lt`            | Less than                | `${3 < 7}`    / `${3 lt 7}`   | `true`  |
| `>`      | `gt`            | Greater than             | `${9 > 4}`    / `${9 gt 4}`   | `true`  |
| `<=`     | `le`            | Less than or equal to    | `${5 <= 5}`   / `${5 le 5}`   | `true`  |
| `>=`     | `ge`            | Greater than or equal to | `${8 >= 3}`   / `${8 ge 3}`   | `true`  |

> **Why alphabetic aliases?**
> In HTML, `<` and `>` are reserved characters. Writing `${a < b}` inside
> HTML attributes can cause parsing problems. The aliases `lt` and `gt`
> are the safe alternatives: `${a lt b}`.

---

### Logical Operators

Used to **combine boolean expressions**. Returns `true` or `false`.

| Symbolic | Alphabetic Alias | Meaning        | Example                          | Result  |
|----------|-----------------|----------------|----------------------------------|---------|
| `&&`     | `and`           | Logical AND    | `${true && false}` / `${true and false}` | `false` |
| `\|\|`   | `or`            | Logical OR     | `${true \|\| false}` / `${true or false}` | `true`  |
| `!`      | `not`           | Logical NOT    | `${!true}` / `${not true}`       | `false` |

---

### Special Operators

| Operator  | Syntax                              | Purpose                                      |
|-----------|-------------------------------------|----------------------------------------------|
| `empty`   | `${empty value}`                    | `true` if value is null, empty string, or empty collection |
| Ternary   | `${condition ? trueVal : falseVal}` | Inline if/else — returns one of two values based on condition |

---

## Files Changed

### 1. [NEW] `src/main/webapp/el_operators_demo.jsp`

A **self-submitting interactive page** where the user enters two numbers
and selects a role. After submit, EL operators evaluate and display live
results in clearly labelled tables.

#### Key patterns used:

**a) Relational operators in result table cells:**
```jsp
<%-- Symbolic form --%>
${param.a == param.b}

<%-- Alphabetic alias form (safe inside HTML) --%>
${param.a eq param.b}

<%-- Both forms produce the same result --%>
${param.a < param.b}   <%-- same as --%>   ${param.a lt param.b}
${param.a > param.b}   <%-- same as --%>   ${param.a gt param.b}
${param.a <= param.b}  <%-- same as --%>   ${param.a le param.b}
${param.a >= param.b}  <%-- same as --%>   ${param.a ge param.b}
```

**b) Logical operators combining multiple conditions:**
```jsp
<%-- AND: both A and B must be positive --%>
${(param.a > 0) && (param.b > 0)}

<%-- OR: at least one must be positive --%>
${(param.a > 0) || (param.b > 0)}

<%-- NOT: negate the equality check --%>
${!(param.a == param.b)}

<%-- Combined: A > B AND role is admin --%>
${(param.a > param.b) && (param.role eq 'admin')}
```

**c) `eq` operator used in a `<select>` to restore the selected option:**
```jsp
<%-- Re-selects the correct dropdown option after form resubmits --%>
<option value="admin"   ${param.role eq 'admin'   ? 'selected' : ''}>Admin</option>
<option value="student" ${param.role eq 'student' ? 'selected' : ''}>Student</option>
<option value="guest"   ${param.role eq 'guest'   ? 'selected' : ''}>Guest</option>
```

**d) `empty` operator used to hide the results section until after submit:**
```jsp
<%-- CSS display is 'none' before form is submitted, 'block' after --%>
<div style="display: ${empty param.a ? 'none' : 'block'}">
    ... all result tables ...
</div>
```
> `empty param.a` returns `true` when the page first loads (param `a`
> does not exist yet), so the results section is hidden. After submit,
> `param.a` has a value, so `empty` returns `false` and the section shows.

---

### 2. [MODIFIED] `src/main/webapp/contact_us_display.jsp`

Previously, the display page showed raw `${param.*}` values with no
safety net. Now it uses `empty` and ternary operators to handle optional
fields gracefully.

#### Before:
```jsp
Name:    ${param.name}<br/>
Email:   ${param.email}<br/>
Subject: ${param.subject}<br/>
Message: ${param.message}<br/>
```

#### After:
```jsp
<%-- empty + ternary: show fallback text when field is blank --%>
Name:    ${empty param.name    ? 'Anonymous'      : param.name}<br/>
Email:   ${empty param.email   ? 'Not provided'   : param.email}<br/>
Subject: ${empty param.subject ? '(No subject)'   : param.subject}<br/>
Message: ${empty param.message ? '(No message)'   : param.message}<br/>

<%-- Logical AND with NOT: checks both required fields are filled --%>
<p>
    Complete submission:
    <strong>${!(empty param.name) && !(empty param.email)}</strong>
</p>

<%-- Relational eq: personalised greeting based on name value --%>
<p>
    ${param.name eq 'Admin' ? 'Welcome, Administrator!' : 'Thank you for reaching out!'}
</p>
```

#### Why this matters:
- `empty` protects against `null` and empty string in one check.
- The ternary `? :` replaces an `if/else` block that would have needed a scriptlet.
- `&&` chains two `empty` checks to validate both required fields at once.
- `eq` replaces `==` for string comparison (safer against HTML encoding issues).

---

### 3. [MODIFIED] `src/main/webapp/footer.jsp`

Added a navigation link to the new demo page so it is reachable from
every page that includes the footer.

```diff
+ <section>
+     <a href="./el_operators_demo.jsp">EL Operators Demo</a>
+ </section>
```

---

## Complete Operator Cheat Sheet

```jsp
<%-- RELATIONAL --%>
${a == b}    ${a eq b}    <%-- equal --%>
${a != b}    ${a ne b}    <%-- not equal --%>
${a < b}     ${a lt b}    <%-- less than --%>
${a > b}     ${a gt b}    <%-- greater than --%>
${a <= b}    ${a le b}    <%-- less than or equal --%>
${a >= b}    ${a ge b}    <%-- greater than or equal --%>

<%-- LOGICAL --%>
${x && y}    ${x and y}   <%-- AND --%>
${x || y}    ${x or y}    <%-- OR  --%>
${!x}        ${not x}     <%-- NOT --%>

<%-- SPECIAL --%>
${empty x}                <%-- true if null, "", or empty collection --%>
${x ? a : b}              <%-- ternary / inline if-else --%>
```

---

## How to Test

1. **Build & deploy** to Tomcat
2. Open **`http://localhost:8080/cohort12/`** — click **EL Operators Demo** in the footer
3. Enter two numbers (e.g. A=10, B=5), select a Role, click **Evaluate**
4. Verify:
   - **Relational table**: `==` shows `false`, `>` shows `true`, etc.
   - **Logical table**: `AND` row shows `true` when both > 0
   - **Special table**: empty row shows `false` (values are filled)
   - Ternary shows `"Positive"` for A > 0
5. Open **`http://localhost:8080/cohort12/contact_us.jsp`**
6. Submit the form **leaving Subject blank**
7. Verify `contact_us_display.jsp` shows `(No subject)` instead of an empty value
8. Verify `Complete submission: true` appears when Name + Email are filled

> [!TIP]
> Try entering **A=0** and selecting role **guest** — the logical AND and OR
> rows will show how conditions interact. Try **Name = Admin** on the contact
> form to trigger the `eq` greeting.

> [!NOTE]
> EL string comparison with `eq` is preferred over `==` because EL's `==`
> also uses `.equals()` internally for strings — but `eq` is the explicit,
> readable, HTML-safe form.
