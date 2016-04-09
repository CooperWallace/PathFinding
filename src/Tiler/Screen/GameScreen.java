package Tiler.Screen;

import Tiler.MainObjects.Renderer;
import Tiler.MainObjects.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen{

	private World MyWorld;
	private Renderer WorldRenderer;
	private OrthographicCamera OrthoCam;
	
	public GameScreen(){
		MyWorld = new World();
		WorldRenderer = new Renderer(MyWorld);

		OrthoCam= new OrthographicCamera();
		OrthoCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
	}
	
	@Override
	public void render(float delta) {
		MyWorld.Update(delta, OrthoCam);
		WorldRenderer.Render(OrthoCam);
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
