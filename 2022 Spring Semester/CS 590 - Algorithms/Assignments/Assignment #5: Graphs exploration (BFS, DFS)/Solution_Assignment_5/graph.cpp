#include <iostream>
#include <set>
#include <queue>

#include "graph.h"

using namespace std;

Graph::Graph(int nodes)
{
    this->nodes = nodes;
    this->matrix = new int*[nodes];

    for (int i = 0; i < nodes; ++i) {
        (this->matrix)[i] = new int[nodes];

        for (int j = 0; j < nodes; ++j) {
            (this->matrix)[i][j] = 0;
        }
    }
}

Graph::Graph(int **matrix, int nodes) {
    this->nodes = nodes;
    this->matrix = new int*[nodes];

    for (int i = 0; i < nodes; ++i) {
        (this->matrix)[i] = new int[nodes];

        for (int j = 0; j < nodes; ++j) {
            (this->matrix)[i][j] = matrix[i][j];
        }
    }
}

Graph::~Graph()
{
    for (int i = 0; i < this->nodes; ++i)
        delete[] (this->matrix)[i];
    delete[] this->matrix;
}

bool Graph::set_edge(int i, int j, int edge)
{
    if (i < this->nodes && j < this->nodes && i >= 0 && j >= 0) {
        (this->matrix)[i][j] = edge;
        return true;
    }
    return false;
}

void Graph::dfs()
{
    set<int> visited;
    for (int i = 0; i < this->nodes; i++) {
        if (visited.find(i) == visited.end())
            this->dfs(i, &visited);
    }
}

void Graph::dfs(int start, set<int> *visited)
{
    visited->insert(start);
    cout << " -> " << start;
    for (int i = 0; i < this->nodes; i++) {
        if (matrix[start][i] != 0 && visited->find(i) == visited->end())
            dfs(i, visited);
    }
}

void Graph::bfs(int start)
{
    if (start >= this->nodes || start < 0) {
        cout<< "Enter valid start node from [0 to "<<this->nodes-1<<"]!";
        return;
    }
    set<int> visited;
    this->bfs(start, &visited);
}

void Graph::bfs(int start, set<int> *visited)
{
    queue<int> myQueue;
    myQueue.push(start);
    visited->insert(start);
    cout << " -> " << start;
    while (!myQueue.empty()) {
        int v = myQueue.front();
        myQueue.pop();
        for (int i = 0; i < this->nodes; i++) {
            if (this->matrix[v][i] != 0 && visited->find(i) == visited->end()) {
                myQueue.push(i);
                visited->insert(i);
                cout << " -> "<< i;
            }
        }
    }
}

