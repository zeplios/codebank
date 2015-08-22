#include <stdio.h>
#include <stdlib.h>

#define KETT int
#define VALT int
#define KEY2INT(key) key

typedef struct HashNode {
    KETT key;
    VALT value;
    struct HashNode* next;
} HashNode;

typedef struct HashTable {
    struct HashNode** buckets;
    int bucketSize;
} HashTable;

HashTable* hash_create(int size) {
    HashTable* table = malloc(sizeof(HashTable));
    table->bucketSize = size;
    table->buckets = calloc(size, sizeof(HashTable*));
    return table;
}

HashNode* hash_get(HashTable* table, KETT key) {
    int hash = abs(KEY2INT(key)) % table->bucketSize;
    HashNode* node = table->buckets[hash];
    while(node != NULL && node->key <= key) {
        if(node->key == key) {
            return node;
        }
        node = node->next;
    }
    return NULL;
}
void hash_set(HashTable* table, KETT key, VALT value) {
    int hash = abs(KEY2INT(key)) % table->bucketSize;
    struct HashNode* node = table->buckets[hash];
    if (node == NULL || node->key > key) {
        HashNode* tmp = malloc(sizeof(HashNode));
        tmp->key = key;
        tmp->value = value;
        tmp->next = node; // node != NULL ? node : NULL
        table->buckets[hash] = tmp;
        return;
    }

    if (node->key == key) {
        node->value = value;
        return;
    }

    while (node->next != NULL && node->next->key < key) {
        node = node->next;
    }
    if (node->next != NULL && node->next->key == key) {
        node->next->value = value;
        return;
    }
    HashNode * tmp = malloc(sizeof(HashNode));
    tmp->key = key;
    tmp->value = value;
    tmp->next = node->next;
    node->next = tmp;
}

/**
 * usage
 * HashTable* hashtable = hash_create(10);
 * hash_set(hashtable, 1, 2.5);
 * hash_set(hashtable, 2, 5.0);
 * hash_set(hashtable, 13, 7.1);
 * hash_set(hashtable, 2, 6.2);
 * printf("%d,%d,%d\n", hash_get(hashtable, 1)->value, hash_get(hashtable, 2)->value, hash_get(hashtable, 13)->value);
 **/
