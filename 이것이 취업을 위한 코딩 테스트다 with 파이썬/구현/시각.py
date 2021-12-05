h = int(input()) # 0<=N<=23

cnt = 0
for i in range(h+1):
    for j in range(60):
        for k in range(60):
            # 매 시각 안에 '3'이 포함되어 있다면 카운트 증가
            if '3' in str(i) + str(j) + str(k): # 시분초를 문자열로 결합시킨 후 '3'이 포함되어 있는지를 따짐
                cnt += 1
print(cnt)