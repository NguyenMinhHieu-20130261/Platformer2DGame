

import javax.swing.JFrame;

import controller.GameController;
import model.GameModel;
import view.GamePanel;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        // Màn game
        window.setTitle("Game Platformer 2D");
        window.setSize(1000, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        //  Model, View, Controller
        GameModel model = new GameModel();
        GamePanel gamePanel = new GamePanel(model);
        GameController controller = new GameController(model);
        // Thêm panel, controller vào window
        gamePanel.addKeyListener(controller);
        window.add(gamePanel);
        window.setVisible(true);
        gamePanel.requestFocusInWindow();
    }
}