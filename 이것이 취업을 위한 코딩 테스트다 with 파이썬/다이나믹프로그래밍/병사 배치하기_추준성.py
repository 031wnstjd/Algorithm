n = int(input())
array = list(map(int, input().split()))

# 15 11 7 4 8 5 2 4
# 전투력은 항상 내림차순이어야함. 병사의 수를 최대로 하는.

d = [0] * 2001
end = []
cnt = 1
for i in range(n-1):
    if array[i] < array[i+1]:
        index_small = i
        index_large = i+1
        end.append([index_small, index_large])
        cnt += 1 # 분할된 총 배열 갯수

count = 0
for i in range(cnt-1): # cnt - 1 => end에 추가된 index의 묶음의 개수
    count_1 = 0
    count_2 = 0

    index_small = end[i][0] 
    index_large = end[i][1]

    if i == 0:
        for j in array[:index_small+1]:
            if j <= array[index_large]:
                count_1 += 1
        for k in array[index_large:end[i+1][0]+1]:
            if k >= array[index_small]:
                count_2 += 1
        count += min(count_1, count_2)
    
    elif i == cnt-2:
        for j in array[end[i-1][1]:index_small+1]:
            if j <= array[index_large]:
                count_1 += 1
        for k in array[index_large:]:
            if k >= array[index_small]:
                count_2 += 1
        count += min(count_1, count_2)
    
    else:
        for j in array[end[i-1][1]:index_small+1]:
            if j <= array[index_large]:
                count_1 += 1
        for k in array[index_large:end[i+1][0]+1]:
            if k >= array[index_small]:
                count_2 += 1
        count += min(count_1, count_2)
    
print(count)
