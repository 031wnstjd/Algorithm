# 최대 공약수를 구해야 할 때는 math 라이브러리의 gcd() 함수를 이용할 수 있다.

import math

# 최소 공배수(LCM)를 구하는 함수

def lcm(a, b):
    return a * b // math.gcd(a, b) # 최소 공배수 = 두 수의 곱 // 최대 공약수

a = 21
b = 14

print(math.gcd(a, b)) # 최대 공약수(GCD) 계산
print(lcm(a, b)) # 최소 공배수(LCM) 계산