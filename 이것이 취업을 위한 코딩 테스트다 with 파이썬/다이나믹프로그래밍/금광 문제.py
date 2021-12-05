tc = int(input())

for _ in range(tc):
    # 금광 행렬 정보 입력 받음
    n, m = map(int, input().split())

    # 매장된 금의 개수 정보 입력 받음
    data = list(map(int, input().split()))

    # matrix, arr 초기화 => tc for문 2번째 돌 때 초기화 상태여야함
    dp = []
    arr = []
    
    # 매장된 금의 개수 정보를 matrix로 표현
    for i in range(n*m): 
        arr.append(data[i])
        if (i+1) % m == 0:
            dp.append(arr)
        arr = []  

    # 행의 개수 = n
    # 이동방법 RU, R, RD 총 세 개
    # 단, 범위를 벗어나지 못하므로 맨위, 맨아래에선 오른쪽 이동만 가능
    # 모든 방향의 이동에 대해서 최종 금광 개수를 저장
    # 모든 저장 값들 중 최댓값 산출
    
     # 세 방향에서 왔을 때 최댓값 담을 2차원 배열
    find_max = []

    for j in range(1, m):
        for i in range(n):
            if i == 0:
                dp[i][j] += max(dp[i][j-1], dp[i+1][j-1])
            if i == n-1:
                dp[i][j] += max(dp[i][j-1], dp[i-1][j-1])
            else:
                dp[i][j] += max(dp[i][j-1], dp[i-1][j-1], dp[i+1][j-1]) 
        if j == m-1:
            for k in range(n):
                find_max.append(dp[k][j])
    
    result = max(find_max)
    print(result)
