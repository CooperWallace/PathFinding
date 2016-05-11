package Tiler.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture;
	public static TextureRegion tile1;
	public static BitmapFont Font;
	
	public static void Load(){
		texture = new Texture(Gdx.files.internal("Textures/Untitled-1.png"));
		tile1 = new TextureRegion(texture, 0, 0, 1, 16);
		Font = new BitmapFont(Gdx.files.internal("Textures/text.fnt"));
		Font.setScale(.2f, .2f);
	}
	
	public static void dispose(){
		texture.dispose();
	}
}
