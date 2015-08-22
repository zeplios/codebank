#include <stdio.h>
#include <stdlib.h>

#define KETT int
#define VALT int
#define KEY2INT(key) abs(key)

typedef struct HashNode {
    KETT key;
    VALT value;
} HashNode;

typedef struct HashTable {
    HashNode ** storage;
    int cap;
    int size;
} HashTable;

HashTable * hash_create(int size){
    HashTable * hashtable = malloc(sizeof(HashTable));
    hashtable->cap = size + 1;
    hashtable->size = 0;
    hashtable->storage = calloc(hashtable->cap, sizeof(HashNode*));
    return hashtable;
}

void hash_destroy(HashTable* hashtable) {
    int i;
    for(i = 0 ; i < hashtable->cap ; i++) {
        HashNode* node;
        if((node = hashtable->storage[i])) {
            free(node);
        }
    }
    free(hashtable->storage);
    free(hashtable);
}

void hash_set(HashTable* hashtable, KETT key, VALT value) {
    if (hashtable->size >= hashtable->cap - 1) {
        printf("the map has been full\n");
        return;
    }
    int hash = KEY2INT(key) % hashtable->cap;
    HashNode* node;
    while ((node = hashtable->storage[hash])) {
        if (node->key == key) {
            node->value = value;
            return;
        }
        if (hash < hashtable->cap - 1) {
            hash++;
        } else {
            hash = 0;
        }
    }
    node = malloc(sizeof(HashNode));
    node->key = key;
    node->value = value;
    (hashtable->size)++;
    hashtable->storage[hash] = node;
}

HashNode* hash_get(HashTable* hashtable, KETT key) {
    int hash = KEY2INT(key) % hashtable->cap;
    HashNode* node;
    while ((node = hashtable->storage[hash])) {
        if (node->key == key) {
            return node;
        }

        if (hash < hashtable->cap - 1) {
            hash++;
        } else {
            hash = 0;
        }
    }
    return NULL;
}

/**
 * modify the first 3 macro if needed
 * KETT stands for type of key
 * VALT stands for type of value
 *
 * usage:
 * HashTable* hashtable = hash_create(10);
 * hash_set(hashtable, 1, 2);
 * hash_set(hashtable, 2, 5);
 * hash_set(hashtable, 13, 7);
 * hash_set(hashtable, 2, 6);
 * printf("%d,%d,%d\n", hash_get(hashtable, 1)->value, hash_get(hashtable, 2)->value, hash_get(hashtable, 13)->value);
 **/
