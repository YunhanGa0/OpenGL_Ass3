package objects3D;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class TexCube {

	public TexCube() {

	}

	// Implement using notes and looking at TexSphere
	public void drawTexCube(Texture texture) {
		Point4f vertices[] = { 
			new Point4f(-1.0f, -1.0f, -1.0f, 0.0f),
			new Point4f(-1.0f, -1.0f, 1.0f, 0.0f),
			new Point4f(-1.0f, 1.0f, -1.0f, 0.0f),
			new Point4f(-1.0f, 1.0f, 1.0f, 0.0f),
			new Point4f(1.0f, -1.0f, -1.0f, 0.0f),
			new Point4f(1.0f, -1.0f, 1.0f, 0.0f),
			new Point4f(1.0f, 1.0f, -1.0f, 0.0f),
			new Point4f(1.0f, 1.0f, 1.0f, 0.0f)
		};

		int faces[][] = {
			{0, 4, 5, 1}, // front
			{0, 2, 6, 4}, // right
			{0, 1, 3, 2}, // top
			{4, 6, 7, 5}, // back
			{1, 5, 7, 3}, // left
			{2, 3, 7, 6}  // bottom
		};

		texture.bind();
		glEnable(GL_TEXTURE_2D);

		glBegin(GL_QUADS);
		for (int face = 0; face < 6; face++) {
			Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
			Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
			Vector4f normal = v.cross(w).Normal();
			glNormal3f(normal.x, normal.y, normal.z);

			// 为每个面设置纹理坐标
			glTexCoord2f(0.0f, 0.0f);
			glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
			
			glTexCoord2f(1.0f, 0.0f);
			glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
			
			glTexCoord2f(1.0f, 1.0f);
			glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
			
			glTexCoord2f(0.0f, 1.0f);
			glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);
		}
		glEnd();
		
		glDisable(GL_TEXTURE_2D);
	}

}

/*
 * 
 * 
 * }
 * 
 */
