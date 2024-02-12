# Part 1 Bugs
I picked the reverseInPlace bug contained in the ArrayExamples.java.

Here is the J-Unit Test I wrote to detect the bug

```
public void testReverseInPlace() {
    int[] input1 = { 3 };
    ArrayExamples.reverseInPlace(input1);
    assertArrayEquals(new int[]{ 3 }, input1);
    int[] input2 = { 2 , 3};
    ArrayExamples.reverseInPlace(input2);
    assertArrayEquals(new int[]{ 3, 2},input2);
    int[] input3 = { 1 , 2 , 3};
    ArrayExamples.reverseInPlace(input3);
    assertArrayEquals(new int[]{ 3, 2, 1},input3);
}
```

Here is the J-Unit Test I wrote that doesn't detect the bug even though the bug is still in the program

```
public void testReverseInPlace() {
    int[] input1 = { 3 };
    ArrayExamples.reverseInPlace(input1);
    assertArrayEquals(new int[]{ 3 }, input1);
}
```

Here are the symptoms of running the test that induce a failure


