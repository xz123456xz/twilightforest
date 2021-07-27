// Date: 11/5/2012 7:35:56 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
package twilightforest.client.model.entity.legacy;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import twilightforest.entity.PinchBeetleEntity;

public class PinchBeetleLegacyModel extends ListModel<PinchBeetleEntity> {
	//fields
	ModelPart thorax;
	ModelPart head;
	ModelPart connector2;
	ModelPart RearEnd;
	ModelPart Leg6;
	ModelPart Leg4;
	ModelPart Leg2;
	ModelPart Leg5;
	ModelPart Leg3;
	ModelPart Leg1;
	ModelPart connector1;
	ModelPart jaw1a;
	ModelPart jaw1b;
	ModelPart jaw2a;
	ModelPart jaw2b;
	ModelPart antenna1;
	ModelPart antenna2;
	ModelPart eye1;
	ModelPart eye2;
	ModelPart tooth1a;
	ModelPart tooth1b;
	ModelPart tooth1c;
	ModelPart tooth2a;
	ModelPart tooth2b;
	ModelPart tooth2c;

	public PinchBeetleLegacyModel() {
		texWidth = 64;
		texHeight = 32;

		thorax = new ModelPart(this, 0, 22);
		thorax.addBox(-4.5F, -4F, 0F, 9, 8, 2);
		thorax.setPos(0F, 18F, -4.5F);

		connector1 = new ModelPart(this, 0, 12);
		connector1.addBox(-3F, -3F, 0F, 6, 6, 1);
		connector1.setPos(0F, 18F, -3F);

		connector2 = new ModelPart(this, 0, 12);
		connector2.addBox(-3F, -3F, -1F, 6, 6, 1);
		connector2.setPos(0F, 18F, -4F);

		RearEnd = new ModelPart(this, 28, 14);
		RearEnd.addBox(-5F, -9F, -4F, 10, 10, 8);
		RearEnd.setPos(0F, 18F, 7F);
		setRotation(RearEnd, 1.570796F, 0F, 0F);

		Leg6 = new ModelPart(this, 40, 0);
		Leg6.addBox(-1F, -1F, -1F, 10, 2, 2);
		Leg6.setPos(4F, 21F, -4F);
		setRotation(Leg6, 0F, 0.2792527F, 0.3490659F);

		Leg5 = new ModelPart(this, 40, 0);
		Leg5.mirror = true;
		Leg5.addBox(-9F, -1F, -1F, 10, 2, 2);
		Leg5.setPos(-4F, 21F, -4F);
		setRotation(Leg5, 0F, -0.2792527F, -0.3490659F);

		Leg4 = new ModelPart(this, 40, 0);
		Leg4.addBox(-1F, -1F, -1F, 10, 2, 2);
		Leg4.setPos(4F, 21F, -1F);
		setRotation(Leg4, 0F, -0.2792527F, 0.3490659F);

		Leg2 = new ModelPart(this, 40, 0);
		Leg2.addBox(-1F, -1F, -1F, 10, 2, 2);
		Leg2.setPos(4F, 21F, 4F);
		setRotation(Leg2, 0F, -0.6981317F, 0.3490659F);

		Leg3 = new ModelPart(this, 40, 0);
		Leg3.mirror = true;
		Leg3.addBox(-9F, -1F, -1F, 10, 2, 2);
		Leg3.setPos(-4F, 21F, -1F);
		setRotation(Leg3, 0F, 0.2792527F, -0.3490659F);

		Leg1 = new ModelPart(this, 40, 0);
		Leg1.mirror = true;
		Leg1.addBox(-9F, -1F, -1F, 10, 2, 2);
		Leg1.setPos(-4F, 21F, 4F);
		Leg1.setTexSize(64, 32);
		setRotation(Leg1, 0F, 0.6981317F, -0.3490659F);

		head = new ModelPart(this, 0, 0);
		head.addBox(-4F, -4F, -6F, 8, 6, 6);
		head.setPos(0F, 19F, -5F);

		jaw1a = new ModelPart(this, 40, 6);
		jaw1a.addBox(-1F, -1F, -1.5F, 8, 2, 3);
		jaw1a.setPos(-3F, 1F, -6F);
		setRotation(jaw1a, 0F, 2.6354471F, 0F);

		jaw1b = new ModelPart(this, 40, 10);
		jaw1b.addBox(-1F, -1F, -1F, 10, 2, 2);
		jaw1b.setPos(7F, 0F, 0F);
		setRotation(jaw1b, 0F, -1.047197F, 0F);

		jaw2a = new ModelPart(this, 40, 6);
		jaw2a.addBox(-1F, -1F, -1.5F, 8, 2, 3);
		jaw2a.setPos(3F, 1F, -6F);
		setRotation(jaw2a, 0F, 0.5410520F, 0F);

		jaw2b = new ModelPart(this, 40, 10);
		jaw2b.addBox(-1F, -1F, -1F, 10, 2, 2);
		jaw2b.setPos(7F, 0F, 0F);
		setRotation(jaw2b, 0F, 1.047197F, 0F);

		antenna1 = new ModelPart(this, 42, 4);
		antenna1.addBox(0F, -0.5F, -0.5F, 10, 1, 1);
		antenna1.setPos(1F, -3F, -5F);
		setRotation(antenna1, 0F, 1.047198F, -0.296706F);

		antenna2 = new ModelPart(this, 42, 4);
		antenna2.addBox(0F, -0.5F, -0.5F, 10, 1, 1);
		antenna2.setPos(-1F, -3F, -5F);
		setRotation(antenna2, 0F, 2.094395F, 0.296706F);

		eye1 = new ModelPart(this, 15, 12);
		eye1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		eye1.setPos(-3F, -2F, -5F);

		eye2 = new ModelPart(this, 15, 12);
		eye2.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		eye2.setPos(3F, -2F, -5F);

		tooth1a = new ModelPart(this, 0, 0);
		tooth1a.addBox(0F, -0.5F, -0F, 2, 1, 1);
		tooth1a.setPos(9F, 0F, 0F);
		setRotation(tooth1a, 0F, -0.5235987F, 0);

		tooth1b = new ModelPart(this, 0, 0);
		tooth1b.addBox(-2.5F, -0.5F, -0F, 2, 1, 1);
		tooth1b.setPos(6F, 0F, 0F);
		setRotation(tooth1b, 0F, 1.5707963F, 0);

		tooth1c = new ModelPart(this, 0, 0);
		tooth1c.addBox(-2.5F, -0.5F, -0F, 2, 1, 1);
		tooth1c.setPos(3F, 0F, 0F);
		setRotation(tooth1c, 0F, 1.5707963F, 0);

		tooth2a = new ModelPart(this, 0, 0);
		tooth2a.addBox(0F, -0.5F, -1F, 2, 1, 1);
		tooth2a.setPos(9F, 0F, 0F);
		setRotation(tooth2a, 0F, 0.5235987F, 0);

		tooth2b = new ModelPart(this, 0, 0);
		tooth2b.addBox(-2.5F, -0.5F, -1F, 2, 1, 1);
		tooth2b.setPos(6F, 0F, 0F);
		setRotation(tooth2b, 0F, -1.5707963F, 0);

		tooth2c = new ModelPart(this, 0, 0);
		tooth2c.addBox(-2.5F, -0.5F, -1F, 2, 1, 1);
		tooth2c.setPos(3F, 0F, 0F);
		setRotation(tooth2c, 0F, -1.5707963F, 0);

		head.addChild(jaw1a);
		jaw1a.addChild(jaw1b);
		jaw1b.addChild(tooth1a);
		jaw1b.addChild(tooth1b);
		jaw1b.addChild(tooth1c);
		jaw2b.addChild(tooth2a);
		jaw2b.addChild(tooth2b);
		jaw2b.addChild(tooth2c);
		head.addChild(jaw2a);
		jaw2a.addChild(jaw2b);
		head.addChild(antenna1);
		head.addChild(antenna2);
		head.addChild(eye1);
		head.addChild(eye2);
	}

//	@Override
//	public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
//		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
//		setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
//	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableList.of(
				thorax,
				head,
				connector2,
				RearEnd,
				Leg6,
				Leg4,
				Leg2,
				Leg5,
				Leg3,
				Leg1,
				connector1
		);
	}

	private void setRotation(ModelPart model, float x, float y, float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(PinchBeetleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);

		float legZ = ((float) Math.PI / 11F);
		this.Leg1.zRot = -legZ;
		this.Leg2.zRot = legZ;
		this.Leg3.zRot = -legZ * 0.74F;
		this.Leg4.zRot = legZ * 0.74F;
		this.Leg5.zRot = -legZ;
		this.Leg6.zRot = legZ;

		float var9 = -0.0F;
		float var10 = 0.3926991F;
		this.Leg1.yRot = var10 * 2.0F + var9;
		this.Leg2.yRot = -var10 * 2.0F - var9;
		this.Leg3.yRot = var10 + var9;
		this.Leg4.yRot = -var10 - var9;
		this.Leg5.yRot = -var10 * 2.0F + var9;
		this.Leg6.yRot = var10 * 2.0F - var9;

		float var11 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
		float var12 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
		float var14 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;

		float var15 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
		float var16 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
		float var18 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;

		this.Leg1.yRot += var11;
		this.Leg2.yRot += -var11;
		this.Leg3.yRot += var12;
		this.Leg4.yRot += -var12;
		this.Leg5.yRot += var14;
		this.Leg6.yRot += -var14;

		this.Leg1.zRot += var15;
		this.Leg2.zRot += -var15;

		this.Leg3.zRot += var16;
		this.Leg4.zRot += -var16;

		this.Leg5.zRot += var18;
		this.Leg6.zRot += -var18;
	}

	@Override
	public void prepareMobModel(PinchBeetleEntity entity, float limbSwing, float limbSwingAmount, float partialTicks) {
		if (entity.isVehicle()) {
			// open jaws
			this.jaw1a.yRot = 2.96705972839036F;
			this.jaw2a.yRot = 0.3490658503988659F;
		} else {
			// close jaws
			this.jaw1a.yRot = 2.356194490192345F;
			this.jaw2a.yRot = 0.7853981633974483F;
		}
	}
}