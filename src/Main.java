

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setTitle("Game Platformer 2D");
        window.setSize(1000, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        GameModel model = new GameModel();
        GamePanel gamePanel = new GamePanel(model);
        GameController controller = new GameController(model);

        window.add(gamePanel);
        window.setVisible(true);
        gamePanel.addKeyListener(controller);
        gamePanel.requestFocusInWindow();
    }
}