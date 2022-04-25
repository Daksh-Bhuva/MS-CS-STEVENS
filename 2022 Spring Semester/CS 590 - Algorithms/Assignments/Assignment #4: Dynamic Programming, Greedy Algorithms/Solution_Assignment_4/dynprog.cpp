#include <cstring>
#include <iostream>
#include <limits.h>

#include "dynprog.h"

using namespace std;

/*
 * Bottom up implementation of Smith-Waterman algorithm
 */
void SW_bottomUp(char* X, char* Y, char** P, int** H, int n, int m) {
    for(int i = 0; i <= n; i++) {
        for(int j = 0; j <= m; j++) {
            if (i == 0 or j == 0) {
                H[i][j] = 0;
                P[i][j] = 0;
                continue;
            }

            int p1 = 0;
            if (X[i-1] == Y[j-1])
                p1 = H[i-1][j-1] + 2;

            else
                p1 = H[i-1][j-1] - 1;

            int p2, p3 = 0;
            p2 = H[i-1][j] - 1;
            p3 = H[i][j-1] - 1;

            H[i][j] = max(max(p1, p2), p3);
            if(H[i][j] == p1)
                P[i][j] = 'D' ;

            else if(H[i][j] == p2)
                P[i][j] = 'U' ;

            else
                P[i][j] = 'L' ;

            if(H[i][j] < 0)
                H[i][j] = 0;
        }
    }

    for(int i = 0; i <= n; i++) {
        for(int j = 0; j <= m; j++) {
            cout<< H[i][j]<<" ";
        }
        cout<< endl;
    }

    for(int i = 0; i <= n; i++) {
        for(int j = 0; j <= m; j++) {
            cout<< P[i][j]<<" ";
        }
        cout<< endl;
    }
}

/*
 * Top-down with memoization implementation of Smith-Waterman algorithm
 */
void memoized_SW(char* X, char* Y, char** P, int** H, int n, int m){
    for(int i = 0; i <= n; i++) {
        for(int j = 0; j <= m; j++) {
            H[i][j] = 0;
        }
    }
    memoized_SW_AUX(X, Y, P, H, n, m);
}

/*
 * Auxiliary recursive function of top-down with memoization implementation of Smith-Waterman algorithm
 */
void memoized_SW_AUX(char* X, char* Y, char** P, int** H, int n, int m){
    if (H[n][m] == 0) {
        if (n == 0 or m == 0)
            H[n][m] = 0;

        else {
            memoized_SW_AUX(X, Y, P, H, n - 1, m - 1);
            memoized_SW_AUX(X, Y, P, H, n - 1, m);
            memoized_SW_AUX(X, Y, P, H, n, m - 1);

            int p1 = 0;
            if (X[n-1] == Y[m-1])
                p1 = H[n-1][m-1] + 2;

            else
                p1 = H[n-1][m-1] -1;

            int p2, p3 = 0;
            p2 = H[n-1][m] -1;
            p3 = H[n][m-1] -1;

            H[n][m] = max(max(p1, p2), p3);
            if(H[n][m] == p1)
                P[n][m] = 'D' ;

            else if(H[n][m] == p2)
                P[n][m] = 'U' ;

            else
                P[n][m] = 'L' ;

            if(H[n][m] < 0)
                H[n][m] = 0;
        }
    }
}

/*
 * Print X'
 */
void print_Seq_Align_X(char* X, char** P, int n, int m){
    if(n < 0 || m < 0)
        return;

    if(P[n][m]  == 'D') {
        print_Seq_Align_X(X, P, n-1, m-1);
        cout<< X[n-1];
    }

    else if(P[n][m]  == 'L') {
        print_Seq_Align_X(X, P, n, m-1);
        cout<< "-";
    }

    else {
        print_Seq_Align_X(X, P, n-1, m);
        cout<< X[n-1];
    }
}

/*
 * Print Y'
 */
void print_Seq_Align_Y(char* Y, char** P, int n, int m){
    if(n<0 || m< 0)
        return;

    if(P[n][m]  == 'D') {
        print_Seq_Align_Y(Y, P, n-1, m-1);
        cout<< Y[m-1];
    }

    else if(P[n][m]  == 'U') {
        print_Seq_Align_Y(Y, P, n-1, m);
        cout<< "-";
    }

    else {
        print_Seq_Align_Y(Y, P, n, m-1);
        cout<< Y[m-1];
    }
}
