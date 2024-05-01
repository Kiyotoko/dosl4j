<h1 align="center">dosl4j</h1>

<div align="center">
    <a href="https://github.com/Kiyotoko/dosl4j/actions/workflows/gradle-publish.yml">
        <img alt="Gradle Package" src="https://github.com/Kiyotoko/dosl4j/actions/workflows/gradle-publish.yml/badge.svg">
    </a>
    <a href="https://github.com/Kiyotoko/dosl4j/actions/workflows/gradle.yml">
        <img alt="Java CI with Gradle" src="https://github.com/Kiyotoko/dosl4j/actions/workflows/gradle.yml/badge.svg">
    </a>
</div>

DOSL for java is a lightweight java library for `.dosl` files.

## Getting started

### Installation

Check out the latest release for the maven dependency.

```xml
<dependency>
    <groupId>org.dosl</groupId>
    <artifactId>dosl4j</artifactId>
    <version>0.1.0</version>
</dependency>
```

### Usage

```java
import org.dosl.DoslUtilsKt;

...

// Parse dosl file
DoslListing listing = DoslUtilsKt.parseDoslFile(getClass().getResource("Path/To/File.dosl").getPath());

listing.getLabels(); // Map of all labels
listing.getPaths(); // Set of all paths
```

## DOSL language guide

### Paths

You can specify local and network paths. You can create absolute and relative paths.

```kotlin
"https://example.com"
"org/example/file.json"
```

Paths must be wrapped in quotes (`"`).

### Labels

You can add one or multiple labels before a path to annotate it.

```kotlin
@label "https://example.com"
```

```kotlin
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
