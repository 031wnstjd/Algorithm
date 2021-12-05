# 현재 나이트의 위치 입력받기
input_data = input()
row = int(input_data[1])
column = int(ord(input_data[0])-int(ord('a'))) + 1 # ord('a') : 'a'의 아스키코드 값을 반환

# 나이트가 이동할 수 있는 8가지 방향 벡터 정의 (2차원 배열)
# 나이트가 이동할 수 있는 방향 벡터는 변하지 않고 규칙에 맞게 정해져 있으므로 튜플 형태로 저장
steps = [(-2, -1), (-1, -2), (1, -2), (2, -1), (2, 1), (1, 2), (-1, 2), (-2, 1)]

# 8가지 방향에 대하여 각 위치로 이동이 가능한지 확인
result = 0
for step in steps:
    # 이동하고자 하는 위치 확인
    next_row = row + step[0]
    next_column = column + step[1]
    # 해당 위치로 이동 가능하다면 카운트 증가
    if next_row>=1 and next_row<=8 and next_column>=1 and next_column<=8:
        result += 1

print(result)