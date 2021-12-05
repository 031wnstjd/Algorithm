array = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8] # len(array) == 10

for i in range(len(array)): # i는 검사 시작 인덱스
    min_index = i # 가장 작은 원소의 인덱스
    for j in range(i+1, len(array)):
        if array[min_index] > array[j]:
            min_index = j
    array[i], array[min_index] = array[min_index], array[i] # 스와프 (파이썬에서는 temp 변수 없이 이렇게 간단히 스왑 가능)

print(array)
        