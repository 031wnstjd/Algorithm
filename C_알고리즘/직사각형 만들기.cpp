#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h> 

// v_row_len은 2차원 배열 v의 행(세로) 길이입니다.
// v_col_len은 2차원 배열 v의 열(가로) 길이입니다.
// v[i][j]는 v의 i번째 행의 j번째 열에 저장된 값을 의미합니다.
int* solution(int **v, size_t v_row_len, size_t v_col_len) {
    // return 값은 malloc 등 동적 할당을 사용해주세요. 할당 길이는 상황에 맞게 변경해주세요.
    int i, j, a;
    int cnt;
    int* answer = (int*)malloc(sizeof(long long int) * 2);
    
    // x좌표 검사
    for(i=0; i<v_row_len; i++) // 검사할 원소 추출
    {   
        cnt = 0;
        a = v[i][0];
        
        for(j=0; j<v_row_len; j++) // 검사 구간
        {
            if(v[j][0] == a)
            {
                cnt++;
            }
        }
        
        if(cnt == 1)
        {
            answer[0] = v[i][0];
            
            break;
        }
    }
    // y좌표 검사
    for(i=0; i<v_row_len; i++)
    {   
        cnt = 0;
        a = v[i][1];
        
        for(j=0; j<v_row_len; j++)
        {
            if(v[j][1] == a)
            {
                cnt++;
            }
        }
        
        if(cnt == 1)
        {
            answer[1] = v[i][1];
            
            break;
        }
    }
     
    return answer;
}

int main(){
	
	int i, j;
	int *result;
	int **ptr;
	ptr = (int **)malloc(sizeof(int*)*3);
	for(i=0; i<3;i++)
	{
		ptr[i] = (int *)malloc(sizeof(int) * 2);
	}
	
	for(i=0; i<3; i++)
	{
		for(j=0; j<2; j++)
		{
			scanf("%d", &ptr[i][j]);

		}
		printf("\n");
	}
	
	result = solution(ptr, 3, 2);
	
	for(i=0; i<2; i++)
	{
		printf("%d ", result[i]);
	}
	
	for(i=0; i<3; i++)
	{
		free(ptr[i]);
	}
	
	free(ptr);

	
	return 0;
}
