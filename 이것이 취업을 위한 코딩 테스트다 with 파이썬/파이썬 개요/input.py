# 자주 사용되는 표준 입력 방법
# input() : 한 줄의 문자열을 입력 받는 함수
# map() : 리스트의 모든 원소에 각각 특정한 함수를 사용할 때 사용

import sys

# input()은 문자열을 입력 받으므로 int로 감싸줌으로써 int형으로 변환
n = int(input())

# 공백을 기준으로 구분된 정수형 데이터를 list로 입력 받을 때 다음과 같이 사용
list = list(map(int, input().split()))

print(n)
print(list)

# 빠르게 문자열 입력 받기 : sys.stdin.readline()

data = sys.stdin.readline().rstrip()
print(data)