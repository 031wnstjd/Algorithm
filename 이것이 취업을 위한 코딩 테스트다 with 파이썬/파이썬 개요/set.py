# 집합 자료형 초기화 방법 1
data = set([1, 2, 3])
print(data)

# 집합 자료형 초기화 방법 2
data = {1, 2, 3}
print(data)

# 집합 자료형에 원소 하나 추가 : data.add(value)
data.add(4)
print(data)

# 집합 자료형에 원소 : data.update([value1, value2, ...])
data.update([5, 6])
print(data)

# 특정한 값을 삭제 : data.remove(value)
data.remove(6)
print(data)

# 리스트와 튜플과 달리 사전 자료형(dictionary)과 집합 자료형(set)은 순서가 없기 때문에 인덱싱으로 값을 얻을 수 없다.
# 사전의 키(key) 혹은 집합의 원소(Element)를 통해 시간 복잡도 O(1)로 조회함 (key나 element의 값으로는 변경 불가능한 튜플과 같은 자료형이 사용되어야 함)
