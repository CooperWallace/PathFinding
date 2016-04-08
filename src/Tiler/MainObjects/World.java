package Tiler.MainObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import Tiler.Helper.TileEditor;
import Tiler.Helper.TileManager;

public class World {

	private TileManager TileManager;
	private TileEditor Editor;

	public World() {
		TileManager = new TileManager();
		Editor = new TileEditor(TileManager.getGrid());

	}

	public void Update(float Delta) {

		if(Gdx.input.isKeyPressed(Keys.R)){
			TileManager.UpdatePathing();
		}
		
		Editor.UpdateInputMovements();
	}

	public TileManager getTileManager() {
		return TileManager;
	}

	public TileEditor getEditor(){
		return Editor;
	}
	
}
