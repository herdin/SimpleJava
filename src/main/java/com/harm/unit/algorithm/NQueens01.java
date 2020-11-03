package com.harm.unit.algorithm;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
N 개의 여왕 문제.
크기가 N*N 인 체스 판에서 배치할 수 있는 여왕말(Queen) 의 좌표 수 구하기.
여왕말은 서로 상하좌우대각선에 있어선 안된다.
보통 1차원 배열로 푼다고한다.
matrix[] = { 0, 1, 2, 3 };
index 는 행, value 는 index 행의 여왕말의 좌표다.
(0, 0), (1, 1), (2, 2), (3, 3) 에 여왕이 위치한다는 뜻
*/
public class NQueens01 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(NQueens01.class);
        nQueens nq = new nQueens(5);
        Stopwatch stopwatch = Stopwatch.createStarted();
        nq.search(0);
        stopwatch.stop();
        logger.debug("n-queens : {}, takes {}",  nq.getCount(), stopwatch.toString());
    }
}

class nQueens {
    private int N;          //N*N의 N
    private int count = 0;  //Solution 개수
    private int chess[];    //현재 i행에 들어갈 퀸의 좌표값(열)을 의미한다.

    public nQueens(int N) {
        this.N = N;
        chess = new int[N];
    }
    //행을 하나씩 내려가면서 퀸 자리가 가능한 곳을 찾는다. 그리고 가능하면 그 다음 행에서 가능한 자리가 있는지 확인한다.
    public void search(int currentRow) {
        //행을 하나씩 내려가면서 퀸 자리가 가능한 곳을 찾다가 N에 search(N);이 되면, 더이상 찾을 곳이 없고 모든 행마다 퀸을 확정지었으니 종료.
        if(currentRow == N)
            this.count++;
        else {
            //현재 검사하려는 행의 모든 열에 퀸을 하나씩 놓아보고, 공격이 가능한지 검사한다.
            for(int i=0; i<N; i++) {
                chess[currentRow] = i;              //해당 행의 열에 i(0~N)를 하나씩 모두 넣어본다.
                if(isPossible(currentRow) == true)
                    search(currentRow+1);
                //검사 결과가 가능하다고 나오면 해당 상태(currentRow까지 확정지은)를 가지고 currentRow+1행으로 넘어가서 검사를 계속한다.
            }
        }
    }
    public boolean isPossible(int currentRow) {
        //검사하려는 행에서 열마다 퀸을 놓고 이 함수로 들어오면, 해당 위치와 사방으로 겹치는 부분이 있는지 확인한다.
        for(int i=0; i<currentRow; i++) {
            //먼저, 같은 열에 존재하는지 검사(상하로 공격가능)
            if(chess[i] == chess[currentRow])
                return false;
            //서로 대각선상에 존재하는지 검사(대각선으로 공격가능)
            if(Math.abs(chess[i]-chess[currentRow]) == Math.abs(i-currentRow))
                return false;
        }
        return true;
    }
    public int getCount() {
        return this.count;
    }
}