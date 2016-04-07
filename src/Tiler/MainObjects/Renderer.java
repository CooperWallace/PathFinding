package Tiler.MainObjects;

import Tiler.Helper.TileManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Renderer {

	private World myWorld;
	private SpriteBatch Batcher; // Rendering Objects
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private TileManager TL;

	public Renderer(World InWorld) {
		myWorld = InWorld;

		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Batcher = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		cam.update();

		TL = new TileManager();
		
	}

	public void Render() {
		
		// 1. We draw a black background to prevent flickering.
		Gdx.gl.glClearColor(255f, 255f, 105 / 255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		TL.render(shapeRenderer);

		
	}
	
	public void Update(float Delta){
		
		cam.translate(5*Delta, 0);
		
		
	}

}
