package com.example.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;
import android.util.AttributeSet;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyGLView view = new MyGLView(this);
        view.setRenderer(new MyRender());
        setContentView(view); //设置view
        
    }
    class MyGLView extends GLSurfaceView{

		public MyGLView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public MyGLView(Context context) {
			super(context);
			// 
		}
    	
    }
    
    // 渲染器
    class MyRender implements android.opengl.GLSurfaceView.Renderer{

    	private float ratio;

		// 表层创建
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			
			// 清除屏幕颜色
			gl.glClearColor(0, 0, 0, 1);
			
			// 启用顶点缓冲区
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		}

		// 表层size改变
		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			
			// 视口为屏幕
			gl.glViewport(0, 0, width, height);
			
			// 矩阵模式：投影矩阵
			gl.glMatrixMode(GL10.GL_PROJECTION);
			// 加载单位矩阵，单位矩阵和自然数1类型
			gl.glLoadIdentity();
			
			ratio = (float) width / (float)height;
			gl.glFrustumf(-1, 1, -ratio, ratio, 3, 7); // 平截头体
		}

		// 绘图
		@Override
		public void onDrawFrame(GL10 gl) {
			
			// 清除颜色缓冲区
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			// 矩阵模式：模型视图矩阵
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			
			// eyes		眼睛的坐标,
			// center	眼睛的观察点
			// up 		眼睛怎么放置，方向向量
			GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
			
			// 三角形
			float[] coorde ={
					0, ratio, 1.999f,
					-1f, -ratio, 1.999f,
					1f, -ratio, 1.999f
			};
			// 分配字节缓冲区，存放定点坐标
			ByteBuffer ibb = ByteBuffer.allocateDirect(coorde.length * 4);
			// 设置顺序
			ibb.order(ByteOrder.nativeOrder());
			// 放置顶点坐标数组
			FloatBuffer fbb = ibb.asFloatBuffer();
			fbb.put(coorde);
			// 定位指针的位置，从0开始读取
			ibb.position(0);
			
			// 绘图的颜色
			gl.glColor4f(1, 0, 0, 1);
			// size 一个点怎么表示， 纬度
			// type 点的数据类型
			// stride 跨度：每隔stride读取一个点
			// buffer 数据
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, ibb);
			// 3表示用3个点绘制图形
			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		}
    	
    }
    
}
