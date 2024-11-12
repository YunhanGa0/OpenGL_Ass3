package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void drawCylinder(float radius, float height, int nSegments) {
	    float x, y, z;
	    float angle;
	    
	    // 绘制圆柱体侧面
	    glBegin(GL_QUADS);
	    for(float i = 0; i < nSegments; i++) {
	        angle = (float) (2.0 * Math.PI * i / nSegments);
	        float nextAngle = (float) (2.0 * Math.PI * (i+1) / nSegments);
	        
	        // 第一个顶点
	        x = (float) (radius * Math.cos(angle));
	        y = (float) (radius * Math.sin(angle));
	        glNormal3f(x/radius, y/radius, 0);
	        glVertex3f(x, y, 0);
	        
	        // 第二个顶点
	        x = (float) (radius * Math.cos(nextAngle));
	        y = (float) (radius * Math.sin(nextAngle));
	        glNormal3f(x/radius, y/radius, 0);
	        glVertex3f(x, y, 0);
	        
	        // 第三个顶点
	        glNormal3f(x/radius, y/radius, 0);
	        glVertex3f(x, y, height);
	        
	        // 第四个顶点
	        x = (float) (radius * Math.cos(angle));
	        y = (float) (radius * Math.sin(angle));
	        glNormal3f(x/radius, y/radius, 0);
	        glVertex3f(x, y, height);
	    }
	    glEnd();
	    
	    // 绘制顶面和底面
	    for(int i = 0; i < 2; i++) {
	        glBegin(GL_TRIANGLE_FAN);
	        z = (i == 0) ? 0 : height;
	        glNormal3f(0, 0, (i == 0) ? -1 : 1);
	        glVertex3f(0, 0, z);
	        
	        for(float j = 0; j <= nSegments; j++) {
	            angle = (float) (2.0 * Math.PI * j / nSegments);
	            x = (float) (radius * Math.cos(angle));
	            y = (float) (radius * Math.sin(angle));
	            glVertex3f(x, y, z);
	        }
	        glEnd();
	    }
	}
}
