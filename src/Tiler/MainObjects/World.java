package Tiler.MainObjects;

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

	}

	public TileManager getTileManager() {
		return TileManager;
	}

	public TileEditor getEditor(){
		return Editor;
	}
	
}
