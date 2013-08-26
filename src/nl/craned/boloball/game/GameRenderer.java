package nl.craned.boloball.game;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import nl.craned.boloball.grid.Grid;
import nl.craned.boloball.grid.GridGenerator;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import java.util.Random;

public class GameRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "MyGLRenderer";
    private static final int SIDE_BAR_PERCENTAGE = 10;
    private Square[][] squares;
    private Grid grid;
    private float squareWidth;
    private float squareWidthOpengl;
   
    private float sideWidth;

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjMatrix = new float[16];
    private final float[] mVMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];

    // Declare as volatile because we are updating it from another thread
    public volatile float mAngle;

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {

        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        grid = GridGenerator.emptyGrid();
        squares = new Square[grid.getWidth()][grid.getHeight()];;
        
        // screen height and width	
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        sideWidth = (float) (width * SIDE_BAR_PERCENTAGE) / 100;
        squareWidth = (float) (width - ((float) width*SIDE_BAR_PERCENTAGE/100)) / grid.getWidth();
        squareWidthOpengl = (float) (3.10f - (3.10f*SIDE_BAR_PERCENTAGE/100)) / grid.getWidth();
        if(squareWidthOpengl * grid.getHeight() > 2f){
        	squareWidth = (float) height/grid.getHeight();
        	squareWidthOpengl = (float) 2f / grid.getHeight();
        }
        
        Random randomFloat = new Random();
        float y = 1f;
        float yPixel = 0f;
		for(int i=0;i<grid.getHeight();i++){
	        float x = 1.55f - (3.10f*SIDE_BAR_PERCENTAGE/100);
	        float xPixel=sideWidth;
			for(int j=0;j<grid.getWidth();j++){
				float coordinates[] = { x-squareWidthOpengl,  y, 0.0f,   // top right
						x-squareWidthOpengl, y-squareWidthOpengl, 0.0f,   // bottom right
	                     x, y-squareWidthOpengl, 0.0f,   // bottom right
	                     x,  y, 0.0f }; // top right
				float colors[] = { randomFloat.nextFloat(), randomFloat.nextFloat(), randomFloat.nextFloat(), 1.0f };
				Square mSquare = new Square(colors, coordinates, xPixel, yPixel);
				squares[j][i] = mSquare;
				x-=squareWidthOpengl;
				xPixel+=squareWidth;
		    }	
			y-=squareWidthOpengl;
			yPixel+=squareWidth;
        }
    }

    @Override
    public void onDrawFrame(GL10 unused) {

        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(mVMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mVMatrix, 0);

        // Draw square
        for(Square[] row : squares){
        	for(Square mSquare : row){
        		mSquare.draw(mMVPMatrix);
        	}
        }

        // Create a rotation for the triangle
//        long time = SystemClock.uptimeMillis() % 4000L;
//        float angle = 0.090f * ((int) time);
        Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, -1.0f);

        // Combine the rotation matrix with the projection and camera view
        Matrix.multiplyMM(mMVPMatrix, 0, mRotationMatrix, 0, mMVPMatrix, 0);


    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    /**
     * Utility method for debugging OpenGL calls. Provide the name of the call
     * just after making it:
     *
     * <pre>
     * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
     * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
     *
     * If the operation is not successful, the check throws an error.
     *
     * @param glOperation - Name of the OpenGL call to check.
     */
    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

	public boolean onTouch(float x, float y) {
		Random randomFloat = new Random();
		for(int i=0;i<grid.getHeight();i++){
			for(int j=0;j<grid.getWidth();j++){
				Square square = squares[j][i];
				if(x>=square.xPixel && x<=square.xPixel+squareWidth
						&& y>=square.yPixel && y<=square.yPixel+squareWidth){
					float colors[] = { randomFloat.nextFloat(), randomFloat.nextFloat(), randomFloat.nextFloat(), 1.0f };
					square.color = colors;
					square.draw(mMVPMatrix);
					return true;
				}
		    }	
        }
		return false;
	}
}
