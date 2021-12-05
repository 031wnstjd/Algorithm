# 절단기의 높이 = 0 ~ 10억 => 이렇게 큰 탐색 범위를 보면 가장 먼저 이진 탐색을 떠올려야 함

# 떡의 개수 n과 요청한 떡의 길이 m을 입력 받음
n, m = map(int, input().split())
# 떡의 개별 높이를 입력 받음
arr = list(map(int, input().split()))

def sect(arr, start, end):
    mid = (start + end) // 2
    h = arr[mid] 
    
    # arr[i] : 각 떡의 개별 높이
    # h : 설정된 떡 절단기의 높이

    rest = []
    for i in range(len(arr)):
        if arr[i] - h > 0: # 만약 자른 길이가 양수면
            rest.append(arr[i] - h) # rest에 해당 길이 추가

    sum_rest = sum(rest) # 절단기의 길이가 h일 때 잘려서 나온 떡 길이의 총합

    if sum_rest == m or start >= end:
        return h
    elif sum_rest < m:
        return sect(arr, start, mid-1)
    else:
        return sect(arr, mid+1, end)


arr.sort()  # [10 15 17 19] : 오름차순 정렬 => start가 작은 숫자, end가 큰 숫자
print(sect(arr, 0, n-1))
