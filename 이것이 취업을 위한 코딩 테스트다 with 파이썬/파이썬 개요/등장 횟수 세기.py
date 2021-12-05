# collections 라이브러리의 Counter는 등장 횟수를 세는 기능을 제공
# 리스트와 같은 반복 가능한(iterable) 객체가 주어졌을 때 내부의 원소가 몇 번씩 등장했는지를 알려줌

from collections import Counter

counter = Counter(['red', 'blue', 'red', 'green', 'blue', 'blue']) # list를 입력으로 받음

print(counter['blue']) # blue가 등장한 횟수 출력
print(counter['green']) # green이 등장한 횟수 출력
print(dict(counter)) # 사전 자료형으로 변환