#include <stdio.h>

int d[1001] = {}; 

int dp(int a){
	if(a==1) return 1;
	if(a==2) return 1;
	if(d[a]!=0) return d[a]; // 만약 a = 10으로 입력이 주어진다면 해당 if문으로 이동 
	// dp(a-1)이 실행되면, 즉 dp(9)이 실행되면 dp(8), dp(7), dp(6)... 쭉 실행되어 save[]에 저장됨 
	// 이 과정에서 dp(a-2)는 save[a-2]에 저장됨
	// 그리고 dp(a-2)!=0이 되므로 dp(a-2)를 실행할 때 if문으로 들어가지 않고 바로 return으로 넘어가서 dp(a-1)을 실행할 때 저장했던 save[a-2]값을 반환함     
	return d[a] = dp(a-1) + dp(a-2);
}

int main(){
	int result;
	
	result = dp(10);
	
	printf("%d", result);
}
