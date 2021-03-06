# 다이나믹 프로그래밍과 분할 정복( ex) 퀵정렬 )의 차이점
# => 큰 문제를 작은 문제로 나눌 수 있으며 작은 문제의 답을 모아서 큰 문제를 해결할 수 있는 상황
# 다이나믹 프로그래밍과 분할 정복의 차이점은 '부분 문제의 중복'이다
# 다이나믹 프로그래밍 문제에서는 각 부분 문제들이 서로 영향을 미치며 부분 문제가 중복됨
# 분할 정복 문제에서는 동일한 부분 문제가 반복적으로 계산되지 않음

# 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
d = [0] * 100

# 첫 번째 피보나치 수와 두 번째 피보나치 수는 1
d[1] = 1
d[2] = 1
n = 99

# 피보나치 함수를 반복문으로 구현(바텀업 다이나믹 프로그래밍)
for i in range(3, n+1):
    d[i] = d[i-1] + d[i-2]

print(d[n])