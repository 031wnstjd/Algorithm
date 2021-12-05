data = input()

num_check = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
remove_set = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}
arr_str = []
arr_num = []

# 숫자 원소 합 구하기
for i in data:
    for j in num_check: # 숫자 원소 크로스 체킹
        if i == j: # 만약 0~9 사이의 숫자가 있다면 arr_num에 담기
            arr_num.append(int(j))

num_sum = sum(arr_num)
num_sum = str(num_sum)

# 문자 원소 오름차순 배열 구하기
arr_str = [i for i in data if i not in remove_set]
arr_str.sort()
arr_str = ''.join(arr_str) # 리스트를 문자열로 변환

result = arr_str + num_sum
print(result)

# 문자열 str = 'ABCD'가 있고, 리스트 list = [A, B, C, D]가 있다고 가정
# 리스트를 문자열로 변환하는 코드 : result = ''.join(list)  
# 문자열을 리스트로 변환하는 코드 : result = list(str) 