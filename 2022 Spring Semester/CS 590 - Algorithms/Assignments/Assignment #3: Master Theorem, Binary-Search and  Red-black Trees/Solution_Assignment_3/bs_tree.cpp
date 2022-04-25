#include "bs_tree.h"
#include <list>

using namespace std;

/*
 * constructor/destructor
 */
bs_tree::bs_tree()
{
    T_nil = new bs_tree_node();
    T_root = nullptr;
    T_nil -> left = T_nil;
    T_nil -> right = T_nil;
    T_root = T_nil;
}

void bs_tree::remove_all(bs_tree_node* x)
{ /*<<<*/
/*
 * recursively remove all tree elements
 */
    if (x != T_nil)
    {
        remove_all(x -> left);
        remove_all(x -> right);

        delete x;
    }
}

bs_tree::~bs_tree()
{
    remove_all(T_root);
    delete T_nil;
}

void bs_tree::insert(int key, bs_tree_i_info& t_info)
{
    bs_tree_node* z;

    z = new bs_tree_node;
    z -> key = key;

    insert(z, t_info);
}

void bs_tree::insert(bs_tree_node* z, bs_tree_i_info& t_info)
{ /*<<<*/
    bs_tree_node* x;
    bs_tree_node* y;

    y = T_nil;
    x = T_root;
    while (x != T_nil)
    {
        y = x;
        if (z -> key == x -> key){
            t_info.i_duplicate = t_info.i_duplicate + 1;
            return;
        }

        if (z->key < x->key){
            x = x -> left;
        }
        else{
            x = x -> right;
        }
    }

    z -> p = y;
    if (y == T_nil)
        T_root = z;
    else
    {
        if (z -> key < y -> key)
            y -> left = z;
        else
            y -> right = z;
    }

    z -> left = T_nil;
    z -> right = T_nil;

}

// TODO: modified inorder tree walk method to save the 
// sorted numbers in the first argument: int* array.
// question 2

int j = 0;
void bs_tree::convert_bs(bs_tree_node* x, int* array)
{ /*<<<*/
/*
 * in order walk through binary tree
 * -> see book or slides
 */
    if (x != T_nil)
    {
        convert_bs(x -> left, array);
        array[j] = x -> key;
        j++;

        convert_bs(x -> right, array);
    }
}

int bs_tree::convert(int* array, int n)
{
    convert_bs(T_root,array);
    n = j;
    return n;
}



