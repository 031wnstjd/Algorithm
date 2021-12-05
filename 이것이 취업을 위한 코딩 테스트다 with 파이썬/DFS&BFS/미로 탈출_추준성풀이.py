from collections import deque

# bfs 정의
def bfs(x, y):
    # 시작 노드 좌표를 queue에 저장
    queue = deque()
    queue.append((x, y))
    # 큐가 빌 때까지 반복 실행
    while queue:
        # queue에 담겨 있던 원소를 빼냄
        x, y = queue.popleft()
        # 상, 하, 좌, 우 검사 => graph[nx][ny] == 0이면 continue, graph[nx][ny] == 1이면 (nx, ny)를 queue에 추가하고 graph[nx][ny] += 1 진행
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            # 만약 미로 밖으로 나가면 계속 진행
            if nx < 0 or ny < 0 or nx > n-1 or ny > m-1:
                continue
            
            # 만약 괴물을 만나면 계속 진행
            elif graph[nx][ny] == 0:
                continue

            # 길일 때만 해당 지점을 queue에 추가하고 미로에서 해당 지점의 원소 값에 1을 더해줌
            elif graph[nx][ny] == 1:
                queue.append((nx, ny))
                graph[nx][ny] = graph[x][y] + 1
            
    print(graph[n-1][m-1])

# 행과 열 정보 입력 받기
n, m = map(int, input().split())

# 미로 정보 입력 받기
graph = []
for i in range(n):
    graph.append(list(map(int, input())))

# 상, 하, 좌, 우, 방향벡터 정의
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

bfs(0, 0)
