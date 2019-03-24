# Tips

# How to control [Random](https://docs.oracle.com/javase/7/docs/api/java/util/Random.html)

Take a look to the [contructor with seed](https://docs.oracle.com/javase/7/docs/api/java/util/Random.html#Random(long)).

```java
@Test
public void random_does_not_return_the_same_number() throws Exception {
  assertEquals(new Random().nextInt(), new Random().nextInt());
}
```

```java
@Test
public void random_returns_the_same_number() throws Exception {
  assertEquals(new Random(1).nextInt(), new Random(1).nextInt());
}
```

# Extract Method

These two codes produce the same result.

```java
public static void printHello() {
  System.out.println("Hello");
}
```

```java
public static void printHello() {
  printStream().println("Hello");
}

private static void PrintStream printStream() {
  return System.out;
}
```

```java
public static void printHello() {
  printHello(printStream());
}

private static void printHello(PrintStream printStream) {
  printStream.println("Hello");
}

private static PrintStream printStream() {
  return System.out;
}
```

# How to save what a PrintStream printed

```java
@Test
public void shouldReturnHello() {
  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  PrintStream printStream = new PrintStream(outputStream, true);
  printHello(printStream);
  assertEquals("Hello\n", outputStream.toString());
}

private static void printHello(PrintStream printStream) {
  printStream.println("Hello");
}
```

# How to save outputs with [Approval](http://approvaltests.com/)

```java
public class ApprovalTest {

private PrintStream printStream;
private ByteArrayOutputStream outputStream;

  @Before
  public void setUp() {
    outputStream = new ByteArrayOutputStream();
    printStream = new PrintStream(outputStream, true);
  }

  @Test
  public void shouldReturnHello() {
    String[] names = new String[]{"Matteo", "Nelis"};
    Approvals.verifyAll(names, this::greetings);
  }

  private String greetings(String name) {
    printHello(printStream, name);
    return outputStream.toString();
  }

  private static void printHello(PrintStream printStream, String name) {
    printStream.println("Hello " + name);
  }
}
```

In case you have more than one input you can use 

```java
  CombinationApprovals.verifyAllCombinations(<function here>, <first inputs>, <second inputs>, ....);
```