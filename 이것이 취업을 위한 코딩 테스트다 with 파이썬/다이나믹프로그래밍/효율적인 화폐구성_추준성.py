# 숫자 N 구성 방법의 총 개수와 다른 점은, 이 문제는 '총'이 아닌 '최소' 화폐 개수를 요구한다는 점
# n : 화폐 종류의 개수, m : n개의 화폐를 활용해 만들고자하는 가치 m원
n, m = map(int, input().split())

data = []
for i in range(n):
    data.append(int(input()))

data.sort() # 오름차순 정렬

d = [0] * (m+1)

for j in data:
    for i in range(1, m+1): # 1부터 m까지
        if i % j == 0: # i가 j의 배수이면
            d[i] = d[i-j] + 1 # i-j에 해당하는 화폐 개수에 1을 더함
        else:
            for k in range(1, m+1): # 1부터 m까지 조사
                if d[k] != 0 and k % j != 0: # 계산 될 수 있는 수라는 의미
                    d[k+j] = d[k] + 1

if d[m] == 0:
    print(-1)
else:
    print(d[m])

                
                
