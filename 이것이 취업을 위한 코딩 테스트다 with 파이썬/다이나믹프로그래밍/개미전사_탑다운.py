n = int(input())
k = list(map(int, input().split()))

d = [0] * 100

def optim(i):
    if i == 0:
        return k[0]
    if i == 1:
        return max(k[0], k[1])
    if d[i] != 0:
        return d[i]
    d[i] = max(optim(i-1), optim(i-2)+k[i])
    return d[i]

result = optim(n-1) 
print(result)