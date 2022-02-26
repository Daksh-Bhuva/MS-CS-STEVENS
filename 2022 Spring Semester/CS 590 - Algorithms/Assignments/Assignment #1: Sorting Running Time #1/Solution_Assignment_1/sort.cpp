#include <cstdio>
#include <cstdlib>
#include <vector>

#include "sort.h"

int ivector_length(int* v, int n)
{
  int sum;

  sum = 0;
  for (int i = 0; i < n; i++)
    sum += (v[i] < 0) ? -v[i] : v[i];

  return sum;
}

/*
 * insertion sort
 */
void insertion_sort(int** A, int n, int l, int r)
{ 
  int i;
  int* key;

  for (int j = l+1; j <= r; j++)
    {
      key = A[j];
      i = j - 1;

      while ((i >= l) && (ivector_length(A[i], n) > ivector_length(key, n)))
        {
	  A[i+1] = A[i];
	  i = i - 1;
	}

      A[i+1] = key;
    }
}

/*
*   TO IMPLEMENT: Improved Insertion Sort for problem 1.
*/
void insertion_sort_im(int** A, int n, int l, int r)
{
    int i;
    int* key;

    vector<int> sums(r+1);
    for(int j = 0; j <= r; j++) {
        sums[j]=((ivector_length(A[j], n)));
    }

    for (int j = l+1; j <= r; j++)
    {
        int key_sum = sums[j];
        key = A[j];
        i = j - 1;

        while ((i >= l) && sums[i] > key_sum)
        {
            A[i+1] = A[i];
            sums[i+1] = sums[i];
            i = i - 1;
        }

        A[i+1] = key;
        sums[i+1] = key_sum;
    }
}

/*
*   TO IMPLEMENT: Improved Merge Sort for problem 2.
*/

vector<int> sums;

void create_sums_vector(int** A, int n, int p, int r) {
    for(int i = p; i <= r; i++) {
        sums.push_back(ivector_length(A[i],n));
    }
}

void merge(int** A, int p, int q, int r)
{
    int n1 = q - p + 1;
    int n2 = r - q;

    int *a[n1], *b[n2];
    int suma[n1], sumb[n2];

    for (int i = 0; i < n1; i++) {
        a[i] = A[p + i];
        suma[i] = sums[p + i];
    }

    for (int i = 0; i < n2; i++) {
        b[i] = A[q + 1 + i];
        sumb[i] = sums[q + 1 + i];
    }

    int i = 0, j = 0, k = p;

    while (i < n1 and j < n2) {
        if (suma[i] < sumb[j]) {
            A[k] = a[i];
            sums[k++] = suma[i++];
        }
        else {
            A[k] = b[j];
            sums[k++] = sumb[j++];
        }
    }

    while (i < n1) {
        A[k] = a[i];
        sums[k++] = suma[i++];
    }

    while (j < n2) {
        A[k] = b[j];
        sums[k++] = sumb[j++];
    }
}

void merge_sort(int** A, int n, int p, int r)
{
    if (p < r) {

        int q = (p + r) / 2;
        merge_sort(A, n, p, q);
        merge_sort(A, n, q + 1, r);

        merge(A, p, q, r);
    }
}

/*
 * Simple function to check that our sorting algorithm did work
 * -> problem, if we find position, where the (i-1)-th element is 
 *    greater than the i-th element.
 */
bool check_sorted(int** A, int n, int l, int r)
{
  for (int i = l+1; i <= r; i++)
    if (ivector_length(A[i-1], n) > ivector_length(A[i], n))
      return false;
  return true;
}


/*
 * generate sorted/reverse/random arrays of type hw1type
 */
int** create_ivector(int n, int m)
{
  int** iv_array;

  iv_array = new int*[m];
  for (int i = 0; i < m; i++)
    iv_array[i] = new int[n];

  return iv_array;
}

void remove_ivector(int** iv_array, int m)
{
  for (int i = 0; i < m; i++)
    delete[] iv_array[i];
  delete[] iv_array;
}

int** create_sorted_ivector(int n, int m)
{
  int** iv_array;

  iv_array = create_ivector(n, m);
  for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
      iv_array[i][j] = (i+j)/n;

  return iv_array;
}

int** create_reverse_sorted_ivector(int n, int m)
{
  int** iv_array;

  iv_array = create_ivector(n, m);
  for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
      iv_array[i][j] = ((m-i)+j)/n;

  return iv_array;
}

int** create_random_ivector(int n, int m, bool small)
{
  random_generator rg;
  int** iv_array;

  iv_array = create_ivector(n, m);
  for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
      {
	rg >> iv_array[i][j];
	if (small)
	  iv_array[i][j] %= 100;
	else
	  iv_array[i][j] %= 65536;
      }

  return iv_array;
}

