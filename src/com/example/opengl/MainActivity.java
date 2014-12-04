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
        setContentView(view); //����view
        
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
    
    // ��Ⱦ��
    class MyRender implements android.opengl.GLSurfaceView.Renderer{

    	private float ratio;

		// ��㴴��
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			
			// �����Ļ��ɫ
			gl.glClearColor(0, 0, 0, 1);
			
			// ���ö��㻺����
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		}

		// ���size�ı�
		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			
			// �ӿ�Ϊ��Ļ
			gl.glViewport(0, 0, width, height);
			
			// ����ģʽ��ͶӰ����
			gl.glMatrixMode(GL10.GL_PROJECTION);
			// ���ص�λ���󣬵�λ�������Ȼ��1����
			gl.glLoadIdentity();
			
			ratio = (float) width / (float)height;
			gl.glFrustumf(-1, 1, -ratio, ratio, 3, 7); // ƽ��ͷ��
		}

		// ��ͼ
		@Override
		public void onDrawFrame(GL10 gl) {
			
			// �����ɫ������
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			// ����ģʽ��ģ����ͼ����
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			
			// eyes		�۾�������,
			// center	�۾��Ĺ۲��
			// up 		�۾���ô���ã���������
			GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
			
			// ������
			float[] coorde ={
					0, ratio, 1.999f,
					-1f, -ratio, 1.999f,
					1f, -ratio, 1.999f
			};
			// �����ֽڻ���������Ŷ�������
			ByteBuffer ibb = ByteBuffer.allocateDirect(coorde.length * 4);
			// ����˳��
			ibb.order(ByteOrder.nativeOrder());
			// ���ö�����������
			FloatBuffer fbb = ibb.asFloatBuffer();
			fbb.put(coorde);
			// ��λָ���λ�ã���0��ʼ��ȡ
			ibb.position(0);
			
			// ��ͼ����ɫ
			gl.glColor4f(1, 0, 0, 1);
			// size һ������ô��ʾ�� γ��
			// type �����������
			// stride ��ȣ�ÿ��stride��ȡһ����
			// buffer ����
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, ibb);
			// 3��ʾ��3�������ͼ��
			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		}
    	
    }
    
}
