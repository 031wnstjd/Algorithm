# 0과 1은 무조건 더하기로, 나머지는 곱하기

N = input()
result = 0

# 0 또는 1이면 더하기, 아니면 곱셈
for i in N:
    if i == '0' or i == '1': # 0 또는 1인 경우
        result += int(i)
    else:
        if result == 0 or result == 1: # result 가 0또는 1인 경우 더함(왼쪽부터 순서대로 연산이므로)
            result += int(i)
        else:
            result *= int(i) # i가 0 또는 1이 아니고 result가 0이 아니면 result에 해당 숫자를 곱함

print(result)    