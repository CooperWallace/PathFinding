import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class StartGame {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Tiler";
		cfg.width = 1280;
		cfg.height = 720;
		cfg.resizable = false;

		new LwjglApplication(new TilerGame(), cfg);

	}

}