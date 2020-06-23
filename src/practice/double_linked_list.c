#include <stdlib.h>
#include <stdio.h>
#include "double_linked_list.h"

struct DoublyLinkedList* createDll() {
  struct DoublyLinkedList *list;
  list = (struct DoublyLinkedList*) malloc(sizeof(struct DoublyLinkedList));
  (*list).head = NULL;
  (*list).tail = NULL;
  (*list).count = 0;
  return list;
}

int insertIntoDll(struct DoublyLinkedList *list, char *value){
  struct DoublyLinkedList l = *list;
  struct DllNode *node = (struct DllNode*) malloc(sizeof(struct DllNode));
  (*node).next = NULL;
  (*node).previous = NULL;
  (*node).value = value;
  if((*list).count == 0){
    (*list).head = node;
    (*list).tail = node;
  }else{
    (*(*list).tail).next = node;
    (*node).previous = (*list).tail;
    (*list).tail = node;
  }
  (*list).count = (*list).count + 1;
  return 0;
}

int printDll(struct DoublyLinkedList *list){
  struct DllNode *curNode = (*list).head;
  printf( "printing list\n");
  while(curNode != NULL){
    printf("%s", (*curNode).value);
    printf("->");
    curNode = (*curNode).next;
  }
  printf("\n");
  return 0;
}