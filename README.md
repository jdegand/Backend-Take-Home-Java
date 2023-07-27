# Backend Take Home Java

My solution to Hologram IO 's [Backend Take Home Java](https://github.com/hologram-io/Backend-Take-Home-Java).

## Screenshot

![](screenshots/usage-parser-test-results.png)

## Directions

### Introduction

Hello engineering candidate! This is Hologram's take-home exercise for software engineers with a backend or full-stack focus. The challenge will be to implement a “usage parser” class to handle several types of incoming data strings, differentiated by id, and parse their constituent data. To facilitate this, we’ve done the TDD part for you: you have a set of tests that should pass when the class is complete!

### Setup

This exercise uses Maven and Java 17. To run the tests, use the command `mvn clean test`. It may also be helpful to run `mvn clean install` for your IDE to take advantage of the provided & generated Usage object.

### Evaluation

We'll be evaluating each submission based on the following criteria:

**Passing Tests -** Your code should fulfill the basic requirements of the challenge. We’ll expect to see the original tests passing for your code.

**Readability / Maintainability -** This isn’t full production code, but the results of your solution should be well-organized and readable. We value long-term maintainability of code.

### Requirements

**Parser Class**

The skeleton parser class has been provided in the repository. The `Parse` method should accept an array of strings. It should perform the parsing and return an array of `UsageResult`'s containing the id and the available values as depicted below. Values not present in the parsing strings should be included in the returned object as the type default.

**ID Parsing**

All strings will have at least two comma-separated values. The first value will always be the `id`.

- IDs ending with 4 should use the “extended” string parsing scheme to store the data
- IDs ending with 6 should use the “hex” string parsing scheme
- All other IDs should use the “basic” string parsing scheme

**Basic String Parsing**

Basic strings are comma separated with just two values, the id and the bytes used, both integers.

`<id>,<bytes_used>`

**Extended String Parsing**

Extended strings are comma separated with values for multiple fields. All values are integers except `dmcc` which is a string. Fields are always in the same order.

`<id>,<dmcc>,<mnc>,<bytes_used>,<cellid>`

**Hex String Parsing**

`<id>,<hex>`

Hex strings consist of two comma separate values: the id, and a string of hex bytes representing more rich data. To access the values, the hex string will have to be parsed.

The hex string is a 24-character (12-byte) non-separated string with fixed position elements noted below. Each byte of the string (**two characters, e.g. `a0`**) is part of a value. Hex values should be converted to appropriate types when they parsed into fields. All values are integers (e.g. `3a09` would be `14857`) except for `ip` which is a string.

- Bytes 1-2 → `mnc`
- Bytes 3-4 > `bytes_used`
- Bytes 5-8 → `cellid`
- Bytes 9-12 → `ip`
    - String
    - Each byte is one segment of the ip, separated by a period: e.g. `c0a80001` would be `"192.168.0.1"`

**Example parsed objects:**

The following example is in JSON format for illustrative purposes.

```json
[
  {
    "id": 7294,
    "mnc": 182,
    "bytes_used": 293451,
    "dmcc": null,
    "cellid": 31194,
    "ip": "192.168.0.1"
  },
  {
    "id": 3,
    "mnc": 9523,
    "bytes_used": 1024,
    "dmcc": "a03",
    "cellid": 193955,
    "ip": null
  }        
]
```

### Submitting your exercise

When you have something you're happy with, please respond with the URL of your repo, and we'll take a look. While it’s intended to be possible to do very well in only a couple of hours, time pressure is not intended to be a factor, so feel free to take a week to find time to work on it, or longer if your schedule requires.

## Thoughts

- Delimiter variable not used in the tests.  I commented it out.
- Codingbat practice helped me get the last digit of the id.  
- Solved the basic string parsing first.
- Solved the extended string parsing second.
- Solved the hex string parsing last.  
- Used [sololearn java playground](https://www.sololearn.com/compiler-playground/java) to work out the logic.
- Took me a little over 2 hours of work. 
- Obviously, code could be refactored and improved.  

## Useful Resources

- [Project Lombok](https://projectlombok.org/features/EqualsAndHashCode) - EqualsAndHashCode
- [Stack Overflow](https://stackoverflow.com/questions/5886619/hexadecimal-to-integer-in-java) - hexadecimal to integer in java