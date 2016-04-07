import Tiler.Helper.AssetLoader;
import Tiler.Screen.GameScreen;

import com.badlogic.gdx.Game;


public class TilerGame extends Game{

	
	@Override
	public void create() {
		AssetLoader.Load();
		setScreen(new GameScreen());
		
	}

	@Override
	public void dispose() {
		
	}


}
