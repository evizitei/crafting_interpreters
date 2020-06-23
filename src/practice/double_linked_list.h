struct DllNode {
  struct DllNode *previous;
  struct DllNode *next;
  char *value;
};

struct DoublyLinkedList {
  struct DllNode *head;
  struct DllNode *tail;
  int count;
};

struct DoublyLinkedList* createDll();
int insertIntoDll(struct DoublyLinkedList *list, char *value);
int printDll(struct DoublyLinkedList *list);