# 퀵정렬은 pivot 기준으로 왼쪽은 pivot보다 작은 수들의 모임이 되고, pivot 기준 오른쪽은 pivot보다 큰 수들의 모임이 됨
# 이러한 특징을 활용해 만든 파이썬 소스 코드가 다음과 같다

array = [5, 7, 9, 0, 3, 1, 6, 2, 4, 8]

def quick_sort(array):
    # 리스트가 하나 이하의 원소만을 담고 있다면 종료
    if len(array) <= 1:
        return array
    pivot = array[0] # 피벗은 첫 번째 원소
    tail = array[1:] # 피벗을 제외한 리스트

    left_side = [x for x in tail if x <= pivot] # 분할된 왼쪽 부분
    right_side = [x for x in tail if x > pivot] # 분할된 오른쪽 부분
    
    # 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 퀵정렬 수행하고, 전체 리스트 반환
    return quick_sort(left_side) + [pivot] + quick_sort(right_side) # pivot 자체는 list가 아니라 int형 숫자이므로 list를 +로 합치기 위해서는 [pivot] 처리를 해줘야함
    
print(quick_sort(array))
