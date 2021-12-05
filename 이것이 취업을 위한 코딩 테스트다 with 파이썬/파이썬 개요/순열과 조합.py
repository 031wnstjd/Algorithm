from itertools import permutations # 순열 라이브러리
from itertools import combinations # 조합 라이브러리
from itertools import product # 중복 순열 라이브러리
from itertools import combinations_with_replacement # 중복 조합 라이브러리

data = ['A', 'B', 'C'] # 데이터 준비

result1 = list(permutations(data, 3)) # 모든 순열 구하기 => permutations(data, N) : data에 있는 원소들 중에 N개를 골라 나열하는 모든 경우를 list로 반환
print(result1)

result2 = list(combinations(data, 2)) # 모든 조합 구하기 => combinations(data, N) : data에 있는 원소들 중에 N개를 뽑는 경우를 list로 반환
print(result2)

result3 = list(product(data, repeat=2)) # 2개를 뽑는 모든 순열 구하기 (중복 허용)
print(result3)

result4 = list(combinations_with_replacement(data, 2)) # 2개를 뽑는 모든 조합 구하기 (중복 허용)
print(result4)
