opengl
======

learn Opengl

使用OpenGL的步骤：
	1.设置vierport（视口）
		glViewport(0,0,width, height);
	2.操作投影矩阵，设置平截头体（比例通常和视口比例相同）	
		// 矩阵模式：投影矩阵
		glMatrixMode(GL_PROJECTION);
		// 加载单位矩阵
		glLoadIdentity();
		// 平截头体
		glFrustum(int x, int y, int top, int bottom, int near, int far);
		// 清除颜色缓冲区
		glClear(GL_COLOR_BUFFER_BIT);
		// 操纵模型视图矩阵，设置眼球参数
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
		// gluLookAt( 摄像机x,摄像机y,摄像机z, 
					  目标点x,目标点y,目标点z, 
                      摄像机顶朝向x,摄像机顶朝向y,摄像机顶朝向z)
	3.定义图形顶点坐标值数组
	
	绘制模式：
	持续绘制(默认绘图方式)，
        view.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    脏绘制（只有在设置为脏的情况下，渲染）
        view.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	绘制点
	GL_POINTS 点
	GL_LINES  线段（线集）
	GL_LINE_STRIP 线带（首尾不相接，中间相连）
	GL_TRIANGLES  三角形集
	GL_TRIANGLES_STRIP 三角形带
	GL_TRIANGLES_FAN   扇面
	
	glVertexPointer()// 顶点缓冲区
	glColorPointer()// 颜色缓冲区
	glEnableClientState(GL_COLOR_ARRAY);// 启用颜色缓冲区
	// 着色器
	glShadeModel(GL_SMOOTH);//平滑着色模式
	glShadeModel(GL_FLAT);//单调着色模式
	
	glEnable(GL_DEPTH_TEST);//深度测试
	
    // 看不见的面就不画了
	glEnable(GL_CULL_FACE);//剔除表面,
	glFrontFace(GL_GW);//设置剔除面方向，即规定正方向（顺时针CW，逆时针CCW）
                        // CCW: counter clock wise
    
	glCullFace(GL_GL_BACK);//设置剔除面, GL10.GL_FRONT
    gl.glEnable(GL10.GL_SCISSOR_TEST);//启用剪裁
    
