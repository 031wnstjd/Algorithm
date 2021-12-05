def quick_sort(arr):
    if len(arr) <= 1: # 원소의 개수가 한 개면 
        return arr
    pivot = arr[0] # 첫 번째 원소를 pivot으로 선정
    tail = arr[1:]
    left_side = [x for x in tail if x <= pivot] # pivot 보다 작은 원소들은 왼쪽에 담음
    right_side = [x for x in tail if x > pivot] # pivot 보다 큰 원소들은 오른쪽에 담음
    
    return quick_sort(left_side) + [pivot] + quick_sort(right_side)

array = [5, 7, 9, 0, 3, 1, 6, 2, 4, 8]
print(quick_sort(array))
