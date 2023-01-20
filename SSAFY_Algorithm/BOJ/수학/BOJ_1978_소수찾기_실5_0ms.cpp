#include <iostream>

using namespace std;

#define MAX_N (100)
#define MAX_M (1000)

int N;
int nums[MAX_N];
int prime_nums[MAX_M + 1];

void Input_Data() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> nums[i];
	}
}

void Init() {
	for (int i = 0; i <= MAX_M; i++) {
		prime_nums[i] = i;
	}
	
	// 에라토스테네스의 체
	prime_nums[1] = 0;
	for (int i = 2; i * i <= MAX_M; i++) {
		if (prime_nums[i] == 0) continue;
		for (int j = i * i; j <= MAX_M; j += i) {
			prime_nums[j] = 0;
		}
	}
}

int Solve() {
	int cnt = 0;
	
	for (int i = 0; i < N; i++) {
		if (prime_nums[nums[i]] == 0) continue;
		cnt++; // 소수면 카운트
	}

	return cnt;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	Input_Data();

	Init();

	cout << Solve() << '\n';

	return 0;
}
