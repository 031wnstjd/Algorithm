data = dict() # dictionary 자료형 초기화
data['판매 품목'] = "Apple"
data['날짜'] = "2021-10-09"
data['가격'] = 3000

print(data)

if '가격' in data:
    print("'가격'을 key로 가지는 dictionary입니다")

key_list = list(data.keys()) # data에서 key값들만 뽑아서 list형으로 반환
value_list = list(data.values()) # data에서 value값들만 뽑아서 list형으로 반환

print(key_list) # key 값들 모두 출력
print(value_list) # value 값들 모두 출력

for i in key_list: # key 값들 하나씩 출력
    print(i)

for j in value_list: # value 값들 하나씩 출력
    print(j)

b = {
    "이름" : "홍길동",
    "나이" : 35
}

print(b)