array = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]

for i in range(len(array)): # i == 3, array = [5 7 9 0 3 1 6 2 4 8]
    min_index = i 
    for j in range(i-1, -1, -1): # i-1 부터 0 까지 for문 돌림
        if array[min_index] < array[j]:
            array[min_index], array[j] = array[j], array[min_index] # 값 스와핑
            min_index = j # 스와프한 인덱스도 반환

print(array)
        
