typedef enum RBColor { 
    RED = 0, 
    BLACK = 1 
} RBColor;

typedef struct RBNode {
    int value;
    struct RBNode * p;
    struct RBNode * left;
    struct RBNode * right;
    RBColor color;
} RBNode;

typedef struct RBTree {
	struct RBNode * root;
} RBTree;

RBTree * create() {
	RBTree * T = malloc(sizeof(RBTree));
	T->root = NULL;
	return T;
} 

void LeftRotate(RBTree * T, RBNode * x) {
	if (x->right == NULL) {
		return;
	}
    RBNode * y = x->right;
    x->right = y->left;
    if (y->left != NULL) {
        y->left->p = x;
    }
    y->p = x->p;
    if (x->p == NULL) {
        T->root = y;
    } else {
        if( x == x->p->left) {
            x->p->left = y;
        } else {
            x->p->right = y;
        }
    }
    y->left = x;
    x->p = y;
}

void RightRotate(RBTree * T, RBNode * x) {
	if (x->left == NULL) {
		return;
	}
    RBNode * y = x->left;
    x->left = y->right;
    if (y->right != NULL) {
        y->right->p = x;
    } 
    y->p = x->p;
    if (x->p == NULL) {
        T->root = y;
    } else {
        if (x == x->p->left) {
            x->p->left = y;
        } else {
            x->p->right = y;
        }
    }
    y->right = x;
    x->p = y;
}

void insertFixup(RBTree * T, RBNode * z) {
	if (z->p == NULL) {
		z->color = BLACK;
		return;
	}
	if (z->p->p == NULL) {
		return;
	}
    RBNode * y;
    while(z->p != NULL && z->p->color == RED) {
        if(z->p == z->p->p->left) {
            y = z->p->p->right; 
            if(y != NULL && y->color == RED) {
                z->p->color = BLACK; 
                y->color = BLACK; 
                z->p->p->color = RED; 
                z = z->p->p; 
            } else { 
                if(z == z->p->right) {
                    z = z->p;
                    LeftRotate(T, z);
                }
                z->p->color = BLACK;
                z->p->p->color = RED;
                RightRotate(T, z->p->p);
            }
        } else {
            y = z->p->p->left;
            if(y != NULL && y->color == RED) {
                z->p->color = BLACK;
                y->color = BLACK;
                z->p->p->color = RED;
                z = z->p->p;
            } else {
                if(z == z->p->left) {
                    z = z->p;
                    RightRotate(T,z);
                }
                z->p->color = BLACK;
                z->p->p->color = RED;
                LeftRotate(T, z->p->p);
            }
        }
    }
    T->root->color = BLACK;
}

int insert(RBTree * T, int val) {
    RBNode * y = NULL;
    RBNode * x = T->root;
    while(x != NULL) { 
        y = x;
        if(val < x->value) {
            x = x->left;
        } else if (val > x->value) {
            x = x->right;
        } else {
            printf("%s %d\n", "duplicate value", val);
            return 1;
        }
    }
    x = malloc(sizeof(RBNode));
    x->left = x->right = NULL;
    x->color = RED;
    x->value = val;
    x->p = y;
    if (y == NULL) {
    	T->root = x;
	} else {
		if(val < y->value) {
			y->left = x;
		} else {
			y->right = x;
		}
	}
    insertFixup(T, x);
    return 0;
}

RBNode * find(RBTree * T, int val) {
	RBNode * n = T->root;
    while(n != NULL) {
        if(val < n->value) {
            n = n->left;
        } else if (val > n->value) {
            n = n->right;
        } else {
            return n;
        }
    }
    return n;
}

RBNode * treeMinimum(RBNode * x) {
	while (x != NULL && x->left != NULL) {
		x = x->left;
	}
	return x;
}

RBNode * treeMaximum(RBNode * x) {
	while (x != NULL && x->right != NULL) {
		x = x->right;
	}
	return x;
}

RBNode * treeSuccessor(RBNode * x) {
	if (x->right != NULL) {
        return treeMinimum(x->right); 
    }
    RBNode * y = x->p;
    while (y != NULL && x == y->right) {
        x = y;
        y = y->p;
    } 
    return y;
}

RBNode * treePredecessor(RBNode * x) {
	if (x->left != NULL) {
        return treeMaximum(x->left); 
    }
    RBNode * y = x->p;
    while (y != NULL && x == y->left) {
        x = y;
        y = y->p;
    } 
    return y;
}

void deleteFixup(RBTree * T, RBNode * x) {
    while (x != T->root && x->color == BLACK) {
        if (x == x->p->left) {
            RBNode * w = x->p->right;
            if (w->color == RED) {
                w->color = BLACK;
                x->p->color = RED;
                LeftRotate(T, x->p);
                w = x->p->right;
            }
            if (w->left->color == BLACK && w->right->color == BLACK) { 
                w->color = RED; 
                x = x->p; 
            } else if (w->right->color == BLACK) {
                w->left->color = BLACK;
                w->color = RED;
                RightRotate(T, w); 
                w = x->p->right; 
            }
            w->color = x->p->color;
            x->p->color = BLACK;
            w->right->color = BLACK;
            LeftRotate(T, x->p);
            x = T->root;
        } else { 
            RBNode * w = x->p->left; 
            if (w->color == RED) {
                w->color = BLACK;
                x->p->color = RED;
                RightRotate(T, x->p);
                w = x->p->left;
            }
            if (w->left->color == BLACK && w->right->color == BLACK) {
                w->color = RED;
                x = x->p;
            } else if (w->left->color == BLACK) {
                w->color = RED;
                w->right->color = BLACK;
                LeftRotate(T, w);
                w = x->p->left; 
            } 
            w->color = x->p->color;
            x->p->color = BLACK;
            w->left->color = BLACK;
            RightRotate(T , x->p);
            x = T->root;
        }
    }
    x->color = BLACK;
} 

void delete(RBTree * T, RBNode * z) {
    RBNode * y;
    RBNode * x;
    if (z->left == NULL || z->right == NULL) { 
        y = z;
    } else { 
        y = treeSuccessor(z);
    }
    if (y->left != NULL) {
        x = y->left;
    } else {
        x = y->right;
    }
    if (x != NULL) {
    	x->p = y->p;
	}
    if (y->p == NULL) {
        T->root = x;
    } else {
        if (y == y->p->left) {
            y->p->left = x;
        } else {
            y->p->right = x;
        }
    }
    if (y != z) {
        z->value = y->value;
    }
    if (x != NULL && y->color == BLACK) { 
        deleteFixup(T, x);
    }
}

void traverse(RBNode * n) {
    if(n != NULL) {
        printf("%d[%d] ", n->value, n->color);
        if (n->left != NULL) {
        	printf("<- ");
        	traverse(n->left);
		}
        if (n->right != NULL) {
	        printf("-> ");
	        traverse(n->right);
	    }
    }
}