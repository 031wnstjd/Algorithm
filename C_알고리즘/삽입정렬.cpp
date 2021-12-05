#include <stdio.h>

int main()
{
	int i, j, temp;
	int a[10] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 1};
	
	for(i=0; i<9; i++) // i = 8
	{
		j = i;
		while(a[j] > a[j+1]) // j = 8, 7, 6, 5, 4, 3, 2, 1
		{
			temp = a[j];
			a[j] = a[j+1];
			a[j+1] = temp;
			
			j--; 
		}
	}
	
	for(i=0; i<10; i++)
	{
		printf("%d ", a[i]);
	}
	return 0;
}
