#include <stdio.h>
#include "../../src/practice/double_linked_list.h"
int main()
{
  printf( "Testing Double Linked List\n");
  struct DoublyLinkedList *list = createDll();
  char *p1 = "foo";
  char *p2 = "bar";
  char *p3 = "baz";
  insertIntoDll(list, p1);
  printDll(list);
  insertIntoDll(list, p2);
  printDll(list);
  insertIntoDll(list, p3);
  printDll(list);
  return 0;
}