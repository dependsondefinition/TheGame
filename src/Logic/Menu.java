package Logic;

public class Menu {
    private GameProcess game;
    public Menu() {
        System.out.println("Start New Game(N) or Load(L)?");
        String ans = GameProcess.scan.next();
        if(ans.equals("N")) {
            game = new GameProcess();

        } else if(ans.equals("L")){
            GameProcess.manager.showGames();
            System.out.println("Enter the file name");
            game = new GameProcess(GameProcess.manager.LoadGame(GameProcess.scan.next()));
        }
        game.Process();
    }
}
