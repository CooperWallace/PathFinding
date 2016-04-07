import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Tiler";
		cfg.width = 800;
		cfg.height = 480;
		cfg.resizable = false;

		new LwjglApplication(new TilerGame(), cfg);

	}

}