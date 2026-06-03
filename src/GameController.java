import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
    private GameModel model;

    public GameController(GameModel model) {
        this.model = model;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            model.startGame();;
            return;
        }
        if (key == KeyEvent.VK_R) {
            model.resetGame();
            return;
        }
        if (key == KeyEvent.VK_P) {
            model.togglePause();
            return;
        }
        if (!model.isPlaying()) {
            return;
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            model.setLeftPressed(true);
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            model.setRightPressed(true);
        }
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
            model.jumpPlayer();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (!model.isPlaying()) {
            return;
        }
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            model.setLeftPressed(false);
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            model.setRightPressed(false);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
}