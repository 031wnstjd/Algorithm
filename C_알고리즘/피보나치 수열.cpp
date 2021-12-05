#include <stdio.h>

int d[1001] = {}; 

int dp(int a){
	if(a==1) return 1;
	if(a==2) return 1;
	if(d[a]!=0) return d[a]; // ���� a = 10���� �Է��� �־����ٸ� �ش� if������ �̵� 
	// dp(a-1)�� ����Ǹ�, �� dp(9)�� ����Ǹ� dp(8), dp(7), dp(6)... �� ����Ǿ� save[]�� ����� 
	// �� �������� dp(a-2)�� save[a-2]�� �����
	// �׸��� dp(a-2)!=0�� �ǹǷ� dp(a-2)�� ������ �� if������ ���� �ʰ� �ٷ� return���� �Ѿ�� dp(a-1)�� ������ �� �����ߴ� save[a-2]���� ��ȯ��     
	return d[a] = dp(a-1) + dp(a-2);
}

int main(){
	int result;
	
	result = dp(10);
	
	printf("%d", result);
}
