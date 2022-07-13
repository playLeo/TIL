# 프로그래머스 행렬 테두리 회전

```java
class Solution {
    int[][] map;
    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    
    public int[] solution(int rows, int columns, int[][] queries) {
        
        int[] answer = new int[queries.length];
        map = new int[rows][columns];
        
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                map[i][j] = i * columns + j + 1; // rows로 작성해서 col> row 큰경우 중복값이들어갔다.

        for(int i = 0; i < answer.length; i++) {
            answer[i] = lotation(queries[i]);
            //map 출력해보기
//            for (int k = 0; k < rows; k++) {
//                for (int n = 0; n < columns; n++)
//                    System.out.print(map[k][n]);
//                System.out.println("");
//            }

        }
        return answer;
//
//        for (int i : answer) {
//            System.out.print(i + " ");
//        }

    }

    int lotation(int[] arr) {
        int x1 = arr[0] - 1;
        int y1 = arr[1] - 1;
        int x2 = arr[2] - 1;
        int y2 = arr[3] - 1;
        int x = x1;
        int y = y1;
        int min = Integer.MAX_VALUE;
        int pre_temp = map[x][y];
        int temp = 0;

        for (int i = 0; i < 4; i++) {
            while (true) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= x1 && ny >= y1 && nx <= x2 && ny <= y2) {
                    temp = map[nx][ny];
                    map[nx][ny] = pre_temp;
                    min = Math.min(min, pre_temp);

                    pre_temp = temp;

                    x = nx;
                    y = ny;
                }
                else break;
            }
        }
        return min;
    }
}
```