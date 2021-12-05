array = [i for i in range(10) if i%2 == 0]
print("array에 관한 것들")

array.append(10) # array.append(value) : list 맨 뒷 쪽에 value 추가
print(array)

array.sort(reverse=True) # array.sort(reverse=True) : list 내림차순 정렬
print(array)

array.sort() # array.sort() : list 오름차순 정렬
print(array)

array.reverse() # array.reverse() : list 배열 거꾸로 정렬
print(array)

array.insert(2, 6) # array.insert(index, value(int형)) : list의 index자리에 value 추가
print(array)

print(array.count(6)) # array.count(value) : list 내에 value에 해당하는 원소가 몇 개인지 반환

array.remove(6) # array.remove(value) : list 내에 value에 해당하는 원소를 지움.(해당하는 원소가 여러 개일 경우 리스트 맨 앞쪽에 있는 원소를 지움)
print(array)

print("-------------------------------------------")
print("b에 관한 것들")
### list 내의 특정 원소를 모두 지우기 위해선 별도의 코드 작성 필요 ###

b = [1, 2, 3, 4, 5, 5, 5]
print(b)
remove_set = {3, 5} # 집합 자료형 (특정 원소의 존재 유무만을 체크하고자 할 때 유용하게 사용되는 자료형)
result = [i for i in b if i not in remove_set]
print(result)

