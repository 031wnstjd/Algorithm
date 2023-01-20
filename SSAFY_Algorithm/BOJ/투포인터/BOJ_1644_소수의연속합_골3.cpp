#include <iostream>
#include <cmath>

#define MAX_N (4000000)

using namespace std;

int N;

int nums[MAX_N + 1];

void Input_Data(void) {
	cin >> N;
}

void Init_Prime(void) {
	for (int i = 2; i <= N; i++) {
		nums[i] = i;
	}
	
	// 에라토스테네스의 체
	for (int i = 2; i <= sqrt(N); i++) { // sqrt(N)까지만 확인
		if (nums[i] == 0) continue;
		for (int j = i * i; j <= N; j += i) { // N이하인 i의 배수들을 0으로
			nums[j] = 0;
		}
	}
}

int	Solve(void) {
	int cnt = 0;
	int s, e, interval_sum;
	
	// 투포인터 알고리즘
	e = 2;
	interval_sum = 0;
	for (s = 2; s <= N; s++) {
		if (nums[s] == 0) continue; // 소수가 아니면 continue
	
		while (e <= N && interval_sum < N) { // N이하, 부분합이 N미만이면
			if (nums[e] != 0) { // 소수이면
				interval_sum += nums[e];
			}
			e++;
		}

		if (interval_sum == N) cnt++; // 부분합이 N이면 카운트

		interval_sum -= nums[s];
	}

	return cnt;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	Input_Data();

	Init_Prime();

	cout << Solve() << '\n';

	return 0;
}
