n, k = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))

# a를 오름차순 정렬 (가장 작은 값부터 순차적으로 k개 추출)
a.sort()
# b를 내림차순 정렬 (가장 큰 값부터 순차적으로 k개 추출)
b.sort(reverse=True)

# k번의 바꿔치기 수행
for i in range(k):
    if a[i] < b[i]:
        a[i], b[i] = b[i], a[i]
    else:
        break
        
print(sum(a))
    