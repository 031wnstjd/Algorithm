# 현재 위치 입력이 열과 행으로(a1) 주어짐
# 8x8 좌표 평면
# 행 : 1 2 3 4 5 6 7 8
# 열 : a b c d e f g h

# 8방향 이동 후 좌표 가능성 검사 -> 가능하다면 카운트 세기

data = input()
arr = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
data_modified = []
cnt = 0

# 입력 받은 열 좌표값을 문자에서 숫자로 변경
for idx, value in enumerate(arr): # enumerate(list or tuple) : index와 value를 동시에 반환
    if data[0] == value:
        data_modified.append(idx+1)

data_modified.append(int(data[1]))        

# 동 서 남 북
"""dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
move_types = ['R', 'L', 'S', 'N']"""

# 8방향 이동 후 좌표 가능성 검사 & 카운트 세기
if data_modified[0]+2<=8 and data_modified[1]-1>=1: # (RR, U)
     cnt += 1

if data_modified[0]+2<=8 and data_modified[1]+1<=8: # (RR, D)
     cnt += 1

if data_modified[0]+1<=8 and data_modified[1]-2>=1: # (UU, R)
     cnt += 1

if data_modified[0]-1>=1 and data_modified[1]-2>=1: # (UU, L)
     cnt += 1

if data_modified[0]-2>=1 and data_modified[1]-1>=1: # (LL, U)
     cnt += 1

if data_modified[0]-2>=1 and data_modified[1]+1<=8: # (LL, D)
     cnt += 1

if data_modified[0]-1>=1 and data_modified[1]+2<=8: # (DD, L)
     cnt += 1

if data_modified[0]+1<=8 and data_modified[1]+2<=8: # (DD, R)
     cnt += 1

print(cnt)
