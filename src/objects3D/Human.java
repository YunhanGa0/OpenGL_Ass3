package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;

public class Human {

	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

	public Human() {

	}

	// Implement using notes in Animation lecture
	public void drawHuman(float delta, boolean GoodAnimation) {
		float theta = (float) (delta * 2 * Math.PI);
		float LimbRotation;

		// 声明所有腿部角度变量
		float leftUpperLegAngle = 90;  // 默认角度
		float leftLowerLegAngle = 0;
		float rightUpperLegAngle = 90;
		float rightLowerLegAngle = 0;

		if (GoodAnimation) {
			LimbRotation = (float) Math.cos(theta) * 45;
			float TorsoRotation = (float) Math.cos(theta) * 10;
			glRotatef(TorsoRotation, 0.0f, 0.0f, 1.0f);
			float HeadRotation = (float) Math.cos(theta) * 5;
			glRotatef(HeadRotation, 0.0f, 1.0f, 0.0f);

			// 计算腿部角度
			leftUpperLegAngle = calculateLegAngle(theta, true);
			leftLowerLegAngle = calculateLegAngle(theta, false);
			rightUpperLegAngle = calculateLegAngle(theta + (float)Math.PI, true);
			rightLowerLegAngle = calculateLegAngle(theta + (float)Math.PI, false);
		} else {
			LimbRotation = 0;
		}

		Sphere sphere = new Sphere();
		Cylinder cylinder = new Cylinder();

		glPushMatrix();

		{
			glTranslatef(0.0f, 0.5f, 0.0f);
			sphere.drawSphere(0.5f, 32, 32);

			// chest
			glColor3f(green[0], green[1], green[2]);
			glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
			glPushMatrix();
			{
				glTranslatef(0.0f, 0.5f, 0.0f);
				sphere.drawSphere(0.55f, 32, 32);

				// neck
				glColor3f(orange[0], orange[1], orange[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
				glPushMatrix();
				{
					glTranslatef(0.0f, 0.0f, 0.0f);
					glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
					// glRotatef(45.0f,0.0f,1.0f,0.0f);
					cylinder.drawCylinder(0.15f, 0.7f, 32);

					// head
					glColor3f(red[0], red[1], red[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 1.0f);
						sphere.drawSphere(0.5f, 32, 32);
						glPopMatrix();
					}
					glPopMatrix();


					// left shoulder
					glColor3f(blue[0], blue[1], blue[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					glPushMatrix();
					{
						glTranslatef(0.6f, 0.4f, 0.0f);
						sphere.drawSphere(0.25f, 32, 32);

						// left arm
						glColor3f(orange[0], orange[1], orange[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

							glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
							// glRotatef(27.5f,0.0f,1.0f,0.0f);
							cylinder.drawCylinder(0.15f, 0.7f, 32);

							// left elbow
							glColor3f(blue[0], blue[1], blue[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.75f);
								sphere.drawSphere(0.2f, 32, 32);

								// left forearm
								glColor3f(orange[0], orange[1], orange[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);
									glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
									// glRotatef(90.0f,0.0f,1.0f,0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);

									// left hand
									glColor3f(blue[0], blue[1], blue[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.drawSphere(0.2f, 32, 32);

									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
					// to chest
					// right shoulder
					glColor3f(blue[0], blue[1], blue[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					glPushMatrix();
					{
						glTranslatef(-0.6f, 0.4f, 0.0f);
						sphere.drawSphere(0.25f, 32, 32);

						// right arm
						glColor3f(orange[0], orange[1], orange[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
							glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
							cylinder.drawCylinder(0.15f, 0.7f, 32);

							// right elbow
							glColor3f(blue[0], blue[1], blue[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.75f);
								sphere.drawSphere(0.2f, 32, 32);

								// right forearm
								glColor3f(orange[0], orange[1], orange[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);
									glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);

									// right hand
									glColor3f(blue[0], blue[1], blue[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.drawSphere(0.2f, 32, 32);
									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
					// right shoulder

					// right arm

					// right elbow

					// right forearm
					// right hand

					// chest

				}
				glPopMatrix();

				// pelvis

				// left hip
				glColor3f(blue[0], blue[1], blue[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				glPushMatrix();
				{
					glTranslatef(-0.4f, -0.2f, 0.0f);

					sphere.drawSphere(0.25f, 32, 32);

					// left high leg
					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 0.0f);
						glRotatef(0.0f, 0.0f, 0.0f, 0.0f);

						glRotatef((LimbRotation / 2) + 90, 1.0f, 0.0f, 0.0f);
						// glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.drawCylinder(0.15f, 1.0f, 32);

						// left knee
						glColor3f(blue[0], blue[1], blue[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 1.05f);
							glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
							sphere.drawSphere(0.2f, 32, 32);

							// left low leg
							glColor3f(orange[0], orange[1], orange[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);
								glRotatef(leftLowerLegAngle, 1.0f, 0.0f, 0.0f);
								cylinder.drawCylinder(0.12f, 1.0f, 32);

								// left foot
								glColor3f(blue[0], blue[1], blue[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 1.05f);
									sphere.drawSphere(0.2f, 32, 32);

								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();

				// pelvis

				// right hip
				// right hip
				glColor3f(blue[0], blue[1], blue[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				glPushMatrix();
				{
					glTranslatef(0.4f, -0.2f, 0.0f);
					sphere.drawSphere(0.25f, 32, 32);

					// right high leg
					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 0.0f);
						glRotatef(rightUpperLegAngle, 1.0f, 0.0f, 0.0f);
						cylinder.drawCylinder(0.15f, 1.0f, 32);

						// right knee
						glColor3f(blue[0], blue[1], blue[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 1.05f);
							sphere.drawSphere(0.2f, 32, 32);

							// right low leg
							glColor3f(orange[0], orange[1], orange[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);
								glRotatef(rightLowerLegAngle, 1.0f, 0.0f, 0.0f);
								cylinder.drawCylinder(0.12f, 1.0f, 32);

								// right foot
								glColor3f(blue[0], blue[1], blue[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 1.05f);
									sphere.drawSphere(0.2f, 32, 32);
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();

			}
			glPopMatrix();

		}

	}

	private float calculateLegAngle(float theta, boolean isUpperLeg) {
		if (isUpperLeg) {
			// 大腿的摆动范围保持在 45° ~ 135°
			return (float)(Math.cos(theta) * 45f + 90);
		} else {
			// 修改小腿的旋转逻辑
			float kneeAngle = (float)Math.cos(theta);
			if (kneeAngle > 0) { // 腿在前面时
				return -kneeAngle * 80; // 保持前摆时的弯曲
			} else { // 腿在后面时
				return 0; // 后摆时完全伸直，不再有负角度
			}
		}
	}

	private float calculateArmAngle(float theta, boolean isUpperArm) {
		if (isUpperArm) {
			// 大臂的摆动范围是 -45° ~ 45°
			return (float)(Math.cos(theta) * 45);
		} else {
			// 当大臂抬起时,小臂弯曲更大
			float elbowAngle = (float)Math.cos(theta);
			if (elbowAngle > 0) { // 手臂在前面时
				return elbowAngle * 60; // 肘部弯曲最大60度
			} else { // 手臂在后面时
				return elbowAngle * 20; // 手臂基本伸直,只有轻微弯曲
			}
		}
	}

}

/*
 *
 *
 * }
 *
 */
