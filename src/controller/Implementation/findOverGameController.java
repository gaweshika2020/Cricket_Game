package controller.Implementation; //To implement methods in Abstract class

import com.company.models.Player;
import com.company.models.Team;
import controller.GameController;

import java.util.Random;
import java.util.Scanner;

public class findOverGameController extends GameController {
    private final static int NO_OF_OVERS=5;
    private final static int NO_OF_BALLS=3;
    public static final String OUT = "OUT";
    public static final String BOWLED = "BOWLED";
    public static final String CAUGHT= "CAUGHT";
    private int ball;

    @Override
    protected int bat(Team battingTeam, int targetScore, Scanner scanner) {
        System.out.println("Team "+battingTeam.getName()+" is batting. ");

        int totNoOfBalls=NO_OF_OVERS*NO_OF_BALLS;
        Random random=new Random();

        Player player=battingTeam.getNextPlayer();
        for(int i=0;i<totNoOfBalls;i++) {
            getUserInputAndValidate(scanner);
            int result = player.bat(random);

            if (result == 5 || result == 7) {
                System.out.println("Player " + player.getName() + " is out. ");
                player.setStatus(OUT);
                player.setGotOutBy(result == 5 ? BOWLED : CAUGHT);

                player = battingTeam.getNextPlayer();

                result=0;//Because the player get out, runs should be zero

                if (player == null) {
                    System.out.println("All out for team " + battingTeam.getName() + ".");
                    break;
                }
            } else {
                System.out.println(result == 0 ? (result + " Out Ball.") : (result + " runs were scored by player " + player.getName() +"."))
                ;
                player.updateScore(result);
            }

            battingTeam.updateTotalScore(result);
            String overs = "(" + ((ball +1)/ 3) + "/" + ((ball +1)% 3) + ")" + "\n";
            String totalSummary = battingTeam.getSummary();

            System.out.println("Score Summary " + totalSummary + " " + overs);

            if (ball % 3 == 0 && ball!=0) {
                System.out.println("End of over!!!");
            }
            if (targetScore > -1 && battingTeam.getTotalScore() > targetScore) {
                break;
            }
        }
        return battingTeam.getTotalScore();
    }
}

