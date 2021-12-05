# list.sort()은 list을 그 자리에서 정렬하고 목록 인덱스를 변경하고 None을 반환합니다.(원본 리스트에 영향 있음)
# sorted()은 list뿐만 아니라 반복 가능한 모든 작업에 적용할 수 있습니다.(원본 리스트에 영향 없음)
# 문자열, 튜플, 딕셔너리, 제너레이터 등 모든 요소가 포함된 반복 가능한 객체를 정렬하여 반환합니다.
# 따라서 list를 변경하려면 list.sort()를 사용하고, 새로운 정렬된 객체를 원하면 sorted()를 사용하면 됩니다.

array = [('홍길동', 50), ('이순신', 32), ('아무개', 74)]

def my_key(x):
    return x[1]

print(sorted(array, key=my_key)) # x[1]을 기준으로 정렬
print(sorted(array, key=lambda x: x[1]))

list_1 = [1, 2, 3, 4, 5]
list_2 = [6, 7, 8, 9, 10]

result = list(map(lambda x, y : x+y, list_1, list_2))
print(result)