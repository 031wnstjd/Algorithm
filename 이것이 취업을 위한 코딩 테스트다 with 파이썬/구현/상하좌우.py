N = int(input())
plan = list(input().split())

# x, y값이 1미만 또는 N+1이상이 되면 무시
# (1,1)부터 시작
x = 1
y = 1

dx = [0, 0, 1, -1] # 동 서 남 북
dy = [1, -1, 0, 0] # ex) x += dx[0], y += dy[0] 이면 동쪽 이동

for i in plan:
    if i == 'R':
        if y == N:
            continue
        else:
            x += dx[0]
            y += dy[0]
    elif i == 'L':
        if y == 1:
            continue
        else:
            x += dx[1]
            y += dy[1]
    elif i == 'D':
        if x == 5:
            continue
        else:
            x += dx[2]
            y += dy[2]
    elif i == 'U':
        if x == 1:
            continue
        else:
            x += dx[3]
            y += dy[3] 

print(x, y)
