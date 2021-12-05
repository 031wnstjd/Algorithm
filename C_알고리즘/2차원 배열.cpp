#include <stdio.h>
#include <stdlib.h>

int main(){
	
	int i, j;
	int **ptr;
	
	ptr = (int **)malloc(sizeof(int*) * 10);
	
	for(i=0; i<10; i++){
		ptr[i] = (int *)malloc(sizeof(int) * 5);
	}
	
	for(i=0; i<10; i++){
		for(j=0; j<5; j++){
			ptr[i][j] = 1;	
		}
	}
	
	for(i=0; i<10; i++){
		for(j=0; j<5; j++){
			printf("%d ", ptr[i][j]);
		}
		printf("\n");
	}
}
