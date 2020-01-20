import java.util.Random;

/**
 * 測試玩家去賭場玩公平的遊戲，最多可以贏多少
 */
public class game {
    public static void main(String[] args) {
        long 總流水 = 0;
        //玩家本金, 20萬
        long playerMoney = 400000;
        //曾經玩家的最高紀錄;
        long playerMoneyMax = 0;
        //此局下注金額
        long playerBetAmount;
        //最低限紅
        long minBet = 50000;
        //最高限紅
        long maxBet = 50000;
        //不幸運次數
        int noLucky = 0;
        //遊戲共玩了幾局
        long gameCount = 0;

        //預計要贏到這樣子的金額
        long wannaMoney = 500000;
        //是否要開啟贏到這樣子的金額
        boolean wannaMoneyEnable = false;

        //隨機物件
        Random random = new Random();

        //第一次押注金額
        playerBetAmount = minBet;
        //可以進行遊戲
        while (playerMoney >= minBet) {
            //紀錄玩家金額最高紀錄
            if (playerMoney > playerMoneyMax) {
                playerMoneyMax = playerMoney;
            }
            System.out.println("此round遊戲開始前玩家本金:" + playerMoney);
            //重新從minBet開始
            if (playerBetAmount > playerMoney) {
                playerBetAmount = minBet;
                noLucky++;
            }
            //不可以超過最大限紅
            if(playerBetAmount > maxBet){
                playerBetAmount = minBet;
            }
            System.out.println("此round bet:" + playerBetAmount);
            //先扣押注
            playerMoney = playerMoney - playerBetAmount;
            if (playerMoney < 0) {
                System.out.println("game over");
                break;
            }
            //流水加總
            總流水 = 總流水 + playerBetAmount;
            boolean result = random.nextBoolean();
            //進行遊戲輸贏
            System.out.println("第" + (gameCount + 1) + "局");
            if (result) {
                System.out.println("贏");
                //贏, 計算結果
                playerMoney = playerMoney + playerBetAmount * 2;
                //押注金額 改成 最小押注
                playerBetAmount = minBet;
            } else {
                System.out.println("輸, 執行玩家押注策略, 倍壓");
                //輸, 執行玩家押注策略, 倍壓
                //限紅maxBet,從minBet重新押注
                if (playerBetAmount >= maxBet) {
                    playerBetAmount = minBet;
                    noLucky++;
                } else {
                    playerBetAmount = playerBetAmount * 2;
                }
            }
            System.out.println("此round遊戲結算後玩家本金:" + playerMoney);
            System.out.println("目前總流水:" + 總流水);
            gameCount++;
            //預計要贏到多少錢
            if (playerMoney >= wannaMoney && wannaMoneyEnable) {
                break;
            }

//            if(noLucky == 1){
//                break;
//            }
        }
        //印出遊戲結果
        System.out.println("-------------遊戲結果------------");
        System.out.println("遊戲結果玩家本金:" + playerMoney);
        System.out.println("遊戲結果流水:" + 總流水);
        System.out.println("爆掉幾次 noLucky:" + noLucky);
        System.out.println("遊戲次數 gameCount:" + gameCount);
        System.out.println("玩家曾經最高本金有:" + playerMoneyMax);
    }
}
