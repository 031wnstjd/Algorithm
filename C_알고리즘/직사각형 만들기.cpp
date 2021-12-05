#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h> 

// v_row_len�� 2���� �迭 v�� ��(����) �����Դϴ�.
// v_col_len�� 2���� �迭 v�� ��(����) �����Դϴ�.
// v[i][j]�� v�� i��° ���� j��° ���� ����� ���� �ǹ��մϴ�.
int* solution(int **v, size_t v_row_len, size_t v_col_len) {
    // return ���� malloc �� ���� �Ҵ��� ������ּ���. �Ҵ� ���̴� ��Ȳ�� �°� �������ּ���.
    int i, j, a;
    int cnt;
    int* answer = (int*)malloc(sizeof(long long int) * 2);
    
    // x��ǥ �˻�
    for(i=0; i<v_row_len; i++) // �˻��� ���� ����
    {   
        cnt = 0;
        a = v[i][0];
        
        for(j=0; j<v_row_len; j++) // �˻� ����
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
    // y��ǥ �˻�
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
