#sum()
result = sum([1, 2, 3, 4, 5])
print(result)

# min(), max()
min_result = min([7, 3, 5, 2]) # 인자로 list 가능
max_result = max(7, 3, 5, 2)  # 여러 인자가 들어갈 수 있음
print(min_result, max_result)

# eval() : 계산을 반환함
result = eval("(3+5)*7")
print(result)

# sorted()
result = sorted([9, 1, 8, 5, 4])
reverse_result = sorted([9, 1, 8, 5, 4], reverse = True)
print(result)
print(reverse_result)

# sorted() with key (key는 정렬 기준을 명시해주는 함수)
array = [('홍길동', 35), ('이순신', 75), ('아무개', 50)]
result = sorted(array, key=lambda x: x[1], reverse = True)
print(result)