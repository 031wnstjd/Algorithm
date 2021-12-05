#include <stdio.h>
#include <stdlib.h> 

int main(){
	
	// 2차원 배열을 만들기 위한 이중 포인터 변수 선언 
	
	int i, j;
	int **ptr;
	
	// 이중 포인터 변수에 메모리 공간 할당 (int형 배열 길이 10만큼의 메모리) for 10개의 포인터 변수 주소를 담기 위함 
	
	ptr = (int **)malloc(sizeof(int*) * 10);
	
	// 포인터 변수에 메모리 공간 할당 (int형 배열 길이 10만큼의 메모리) for 10개의 변수 주소를 담기 위함 
	
	for(i=0; i<10; i++)
	{
		ptr[i] = (int *)malloc(sizeof(int)*5);
	}
	
	//// 여기까지 2차원 배열 메모리 공간을 모두 할당한 것임( 10 x 10 크기의 2차원 배열) ////
	
	// 2차원 배열 모든 주소들(= 모든 메모리 공간)에 대해 값을 할당
	
	for(i=0; i<10; i++)
	{
		for(j=0; j<10; j++)
		{
			ptr[i][j] = j+1;	
		} 
	}
	
	// 2차원 배열 값 출력 
	
	for(i=0; i<10; i++)
	{
		for(j=0; j<10; j++)
		{
			printf("%d ", ptr[i][j]);
		}
		printf("\n");
	}
	
	// 동적 메모리 해제 for 메모리 공간 원상 복구 (메모리 공간 할당 순서의 반대_역순) 
	
	for(i=0; i<10; i++)
	{
		free(ptr[i]); // 포인터 변수들에 할당된 메모리 공간부터 해제 
	}
	
	free(ptr); // 이중 포인터 변수에 할당된 메모리 공간 해제 
	
	return 0;
}
