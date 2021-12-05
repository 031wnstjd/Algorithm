# 그리디, 구현, 완전탐색으로 안풀리면 dp 떠올리기

n = int(input())

d = [0] * 30001
d[0] = 1

for i in range(1, n+1): # i => 1 ~ 6
    for j in {1, 3, 5}:
        if j == 1:
            d[i] = d[i-j]
        if j == 3 and i >= 3:
            d[i] += d[i-j] 
        if j == 5 and i >= 5:
            d[i] += d[i-j]

print(d[n])