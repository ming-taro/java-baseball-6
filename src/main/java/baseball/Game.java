package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.ArrayList;
import java.util.List;


public class Game {
    final private List<Integer> computer;
    final private List<Integer> user;
    private Script script;
    private Judgment judgment;

    public Game() {
        computer = new ArrayList<>();
        user = new ArrayList<>();

        script = new Script();
        judgment = new Judgment(computer, user);
    }

    private void createComputerNumber() {
        int randomNumber;

        while (computer.size() < 3) {
            randomNumber = Randoms.pickNumberInRange(1, 9);

            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
    }

    private void inputUserNumber() {
        String[] userInput = readLine().split("");

        user.clear();

        for (int index = 0; index < 3; index++) {
            user.add(Integer.parseInt(userInput[index]));
        }
    }

    private boolean isRetry() {
        String userInput = readLine();

        if (userInput.equals("1")) {
            return true;
        }

        return false;
    }

    public void play(){
        createComputerNumber();    //컴퓨터 난수 생성
        script.startGame();

        while (true) {
            script.inputNumber();  //유저 - 숫자입력
            inputUserNumber();

            judgment.judge();      //숫자 야구 판정

            if (judgment.isUserWin()) {
                script.endGame();

                if (isRetry()) {               //정답을 맞힌 후 게임 종료 여부를 입력 받음
                    createComputerNumber();
                    script.startGame();
                } else {
                    break;
                }
            }
        }

    }
}
