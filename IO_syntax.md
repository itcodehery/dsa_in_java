# Input and Output Syntax
## Input
How do we get input from the user?

```java
Scanner sc = new Scanner(System.in);

String s = sc.nextLine();
```

A more idiomatic way would be to use a `BufferedReader`

```java
BufferedReader reader = new BufferedReader(new InputStreamReader(input));
String line = reader.readLine(); // reads just the first line
```

## Output
```java
System.out.println();

System.out.write();
```

A stream based approach would be:

```java
OutputStream output = clientSocket.getOutputStream();

PrintWriter writer = new PrintWriter(output, true);
// the `true` = auto-flush, meaning it actually pushes bytes out after each println

writer.println("HTTP/1.1 200 OK");
writer.println("Content-Type: text/plain");
writer.println();                          // <- the blank line! headers done
writer.println("Hello, world!");
```


