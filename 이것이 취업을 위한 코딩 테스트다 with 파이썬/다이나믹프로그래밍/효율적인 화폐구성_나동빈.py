# 정수 N, M을 입력 받기
n,m = map(int, input().split())
# N개의 화폐 단위 정보를 입력받기
array = []
for i in range(n):
    array.append(int(input()))

# 한 번 계산된 결과를 저장하기 위한 DP 테이블 초기화
d = [10001] * (m+1)

# 다이나믹 프로그래밍 진행(바텀업) => 기본적으로, 초기 상태(n=0, 1, 2)와 임의의 마지막 상태(n= i, i-1)에서 생각하기
d[0] = 0
for i in range(n): # array[i]는 각각의 화폐 단위
    for j in range(array[i], m+1): # j는 금액
        if d[j-array[i]] != 10001: # (i-k)원을 만드는 방법이 존재하는 경우
            d[j] = min(d[j], d[j-array[i]] + 1) #  min(d[j], d[j-array[i]]+1)에서 d[j]는 기존의 값, d[j-array[i]]+1은 새로 비교하는 값

# 계산된 결과 출력 
if d[m] != 10001: # 최종적으로 M원을 만드는 방법이 없는 경우
    print(d[m])             
else:
    print(-1)