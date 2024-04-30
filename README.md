<h1>dosl4j</h1>

DOSL for java is a lightweight java library for .dosl files.

## Getting started

## DOSL language guide

### Paths

You can specify local and network paths. You can create absolute and relative paths.

```kotlin
"https://example.com"
"org/example/file.json"
```

Paths must be wrapped in parentheses (`"`).

### Labels

You can add one or multiple labels before a path to annotate it.

```json
@label "https://example.com"
```

```json
@label1 @label2 "https://example.com"
```

A label must be placed before a path. A label must start with an at symbol (`@`).

### Groups

You can wrap multiple paths with a group. All entries in a group get all properties of the surrounding group.

```kotlin
"https://" {
    "example.com"
    "java.com"
}
```

Groups are created by a leading and closing bracket (`{`, `}`).
If you annotate a group with a label, all entries are annotated with the label. You can specify any number of labels.

```kotlin
@label {
    "https://example.com"
    "org/example/file.json"
}
```

You can create groups inside a group. You can mix a path with any number of labels before a group as you want. Please
note that a group needs to have at least one label or path specified before it. A group can be empty (no entries).
Empty groups will be ignored.

```kotlin
@network "https://" {
    @github @project {
        "Kiyotoko/dosl4j"
    }
    
    @google {
        "google.com"
        "youtube.com"
    }
    
    @empty {
        
    }
}
```

The resulting paths will be:
```text
https://Kiyotoko/dosl4j
https://google.com
https://youtube.com
```

And the following annotations will be created:
```text
network: [https://Kiyotoko/dosl4j, https://google.com, https://youtube.com]
github: [https://Kiyotoko/dosl4j]
project: [https://Kiyotoko/dosl4j]
google: [https://google.com, https://youtube.com]
```

### Comments

You can use comments inside DOSL files.

```kotlin
/* 
 * This is a
 * block comment
 */

// This is a line comment
```
