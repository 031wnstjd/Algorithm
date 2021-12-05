#include <stdio.h>

int Factorial(int k) // k!  �Է� 
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

int nPr(int n, int k) // nPk �Է� 
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

int nCr(int n, int r) // nCr �Է� 
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
	
	printf("Factorial ���� �Է��ϼ��� : ");
	scanf("%d!", &k);
	printf("Factorial ���� %d �Դϴ�\n", Factorial(k));
	
	printf("Combination ���� �Է��ϼ��� : ");
	scanf("%dC%d", &n, &r);
	printf("Combination ���� %d �Դϴ�\n", nCr(n, r));
	
	printf("Permutation ���� �Է��ϼ��� : ");
	scanf("%dP%d", &n, &r);
	printf("Permutation ���� %d �Դϴ�\n", nPr(n, r));
	
	return 0;
}
