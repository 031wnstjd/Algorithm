# 문제 해결 아이디어 : 주어진 N에 대하여 최대한 많이 나누기를 수행하면 됨
# => N의 값을 줄일 때 2 이상의 수로 나누는 작업이 1을 빼는 작업보다 수를 훨씬 많이 줄일 수 있기 때문

N, K = map(int, input().split())
cnt = 0

while N > 1:
    if N%K == 0:
        N //= K
        cnt += 1
    else:
        N = N-1
        cnt += 1

print(N)
print(cnt)