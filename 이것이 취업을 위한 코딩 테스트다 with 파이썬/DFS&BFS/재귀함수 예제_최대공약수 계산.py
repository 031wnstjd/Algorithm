def gcd(a, b): # a > b
    if a % b == 0: # 재귀함수 종료조건
        return b
    else:
        c = a % b # a를 b로 나눈 나머지 계산
        return gcd(b, c)
    

print(gcd(192, 162))