#include <stdio.h>

int Factorial(int k) // k!  입력 
{
	if(k == 0)
	{
		return 1;
	}
	else
	{
		return k*Factorial(k-1);
	}
}

int nPr(int n, int k) // nPk 입력 
{ 	
	if(k == 0)
	{	 
		return 1;
	}
	else
	{
		return Factorial(n) / Factorial(n - k);
		
	}
	
}

int nCr(int n, int r) // nCr 입력 
{	
	if(r == 0 || r == n)
	{
		return 1;
	}
	else
	{	
		return Factorial(n) / (Factorial(n-r) * Factorial(r));		
	}
	 	
}

int main()
{	
	int n, r, k;
	
	printf("Factorial 값을 입력하세요 : ");
	scanf("%d!", &k);
	printf("Factorial 값은 %d 입니다\n", Factorial(k));
	
	printf("Combination 값을 입력하세요 : ");
	scanf("%dC%d", &n, &r);
	printf("Combination 값은 %d 입니다\n", nCr(n, r));
	
	printf("Permutation 값을 입력하세요 : ");
	scanf("%dP%d", &n, &r);
	printf("Permutation 값은 %d 입니다\n", nPr(n, r));
	
	return 0;
}
