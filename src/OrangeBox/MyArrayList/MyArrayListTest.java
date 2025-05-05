package OrangeBox.MyArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for MyArrayList using the ZOMB(IES) approach.
 */
class MyArrayListTest {
  private MyArrayList<String> list;

  @BeforeEach
  void setUp() {
    list = new MyArrayList<>();
  }

  @Test
    // Zero: Test on empty list that isEmpty returns true and size is 0
  void testEmptyList() {
    assertTrue(list.isEmpty(), "List should be empty");
    assertEquals(0, list.size(), "Size should be zero");
  }

  @Test
    // One: Add a single element and test get, contains, toString
  void testAddOneElement() {
    list.add("A"); // Using "One" approach
    assertFalse(list.isEmpty(), "List should not be empty");
    assertEquals(1, list.size(), "Size should be one");
    assertEquals("A", list.get(0), "Element at index 0 should be 'A'");
    assertTrue(list.contains("A"), "List should contain 'A'");
    assertEquals("{A}", list.toString(), "toString should be '{A}'");
  }

  @Test
    // Many: Add multiple elements to exceed default capacity to trigger expandCapacity
  void testAddManyElements() {
    for (int i = 0; i < 150; i++) {
      list.add("X" + i);
    }
    assertEquals(150, list.size(), "Size should reflect 150 elements");
    assertEquals("X0", list.get(0), "First element should be 'X0'");
    assertEquals("X149", list.get(149), "Last element should be 'X149'");
  }

  @Test
    // Boundary: Test add at index 0 and at size (end)
  void testAddAtBoundaries() {
    list.add(0, "Start"); // Boundary low
    list.add(list.size(), "End"); // Boundary high
    assertEquals("Start", list.get(0));
    assertEquals("End", list.get(1));
  }

  @Test
    // Interface: Test set replaces element correctly
  void testSetElement() {
    list.add("A");
    list.set(0, "B"); // Interface behavior
    assertEquals("B", list.get(0), "set should replace 'A' with 'B'");
  }

  @Test
    // Exceptions: get with invalid index should throw IllegalStateException (bug: should be IndexOutOfBounds)
  void testGetInvalidIndex() {
    assertThrows(IllegalStateException.class, () -> list.get(0),
        "get on empty should throw IllegalStateException (unexpected)");
  }

  @Test
    // Exceptions: remove(T) on non-existent element throws IllegalStateException
  void testRemoveNonExistentElement() {
    assertThrows(IllegalStateException.class, () -> list.remove("Nope"),
        "remove(element) should throw IllegalStateException if not found");
  }

  @Test
    // Size: After removals, size should decrement
  void testRemoveAndSize() {
    list.add("A");
    list.add("B");
    String removed = list.remove(0);
    assertEquals("A", removed, "Removed element should be 'A'");
    assertEquals(1, list.size(), "Size should decrement to 1");
  }

  @Test
    // Exceptions: add with invalid negative index
  void testAddInvalidIndex() {
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "X"),
        "add with negative index should throw IndexOutOfBoundsException");
  }

  @Test
    // ZOMBIES full: isFull always returns true (bug)
  void testIsFull() {
    // Interface/State: list is unbounded, but isFull returns true
    assertTrue(list.isFull(), "isFull should be false for unbounded list, but returns true (bug)");
  }
}
