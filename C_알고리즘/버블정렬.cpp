#include <stdio.h>

int main()
{	
	int i, j, temp;
	int arr[10] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 1};
	
	for(i=0; i<10; i++)
	{	
		for(j=0; j<9-i; j++)
		{
			if(arr[j] > arr[j+1])
			{
				temp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = temp;
			}
		}
	}
	
	for(i=0; i<10; i++)
	{
		printf("%d ", arr[i]);
	}
	
	return 0;
}
