package Tiler.Screen;

import Tiler.MainObjects.Renderer;
import Tiler.MainObjects.World;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen{

	private World MyWorld;
	private Renderer WorldRenderer;
	
	public GameScreen(){
		MyWorld = new World();
		WorldRenderer = new Renderer(MyWorld);
		
	}
	
	@Override
	public void render(float delta) {
		MyWorld.Update(delta);
		WorldRenderer.Render();
		WorldRenderer.Update(delta);
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
}
