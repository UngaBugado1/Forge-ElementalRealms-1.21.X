package net.matos.elementalrealms.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.matos.elementalrealms.entity.client.animation.TectoraxAnimations;
import net.matos.elementalrealms.entity.custom.TectoraxEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class TectoraxModel<T extends TectoraxEntity> extends HierarchicalModel<T> {

    private final ModelPart Body;
    private final ModelPart tail1;
    private final ModelPart tier1;
    private final ModelPart tier2;
    private final ModelPart tier3;
    private final ModelPart torso;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart helmet;

    public TectoraxModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.tail1 = this.Body.getChild("tail1");
        this.torso = this.Body.getChild("torso");
        this.neck = this.torso.getChild("neck");
        this.head = this.neck.getChild("head");
        this.helmet = this.head.getChild("helmet");

        this.tier1 = tail1.getChild("chests").getChild("tier1");
        this.tier2 = tail1.getChild("chests").getChild("tier2");
        this.tier3 = tail1.getChild("chests").getChild("tier3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 12.4728F, -24.4721F));

        PartDefinition tail1 = Body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(84, 84).addBox(-8.0F, -8.0F, -1.0F, 16.0F, 18.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.4728F, 30.4721F));

        PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(84, 121).addBox(-6.0F, -4.0F, 0.0F, 12.0F, 12.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 18.0F));

        PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(48, 151).addBox(-3.9F, -1.7085F, 0.0F, 7.0F, 7.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.4F, 1.2085F, 18.0F));

        PartDefinition cube_r1 = tail3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 123).mirror().addBox(-0.5F, 5.5F, 0.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.4F, -1.2085F, 6.0F, 1.5708F, 0.0F, 0.5672F));

        PartDefinition cube_r2 = tail3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(108, 196).mirror().addBox(-3.5F, -3.5F, 0.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.4F, -1.2085F, 6.0F, 1.5708F, 0.0F, -0.5672F));

        PartDefinition cube_r3 = tail3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(80, 184).addBox(-0.5F, -3.5F, 0.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, -1.2085F, 6.0F, 1.5708F, 0.0F, 0.5672F));

        PartDefinition cube_r4 = tail3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 137).addBox(-3.5F, 5.5F, 0.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -1.2085F, 6.0F, 1.5708F, 0.0F, -0.5672F));

        PartDefinition spike_tail = tail2.addOrReplaceChild("spike_tail", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 7.75F));

        PartDefinition cube_r5 = spike_tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(192, 15).addBox(-1.5F, -7.0529F, -2.0441F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.25F, 0.0F, -0.6981F, 0.0F, 0.0F));

        PartDefinition left_back_leg = tail1.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(30, 218).addBox(-5.0F, 0.0F, -5.0F, 8.0F, 21.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(10.5F, 3.0F, 7.0F));

        PartDefinition right_back_leg = tail1.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(138, 162).mirror().addBox(-3.0F, 0.0F, -5.0F, 8.0F, 21.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-10.5F, 3.0F, 7.0F));

        PartDefinition chests = tail1.addOrReplaceChild("chests", CubeListBuilder.create(), PartPose.offset(0.0F, 0.025F, 0.0F));

        PartDefinition tier1 = chests.addOrReplaceChild("tier1", CubeListBuilder.create().texOffs(55, 197).addBox(-8.5F, -9.0F, 0.0F, 17.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(46, 194).addBox(0.5F, -16.0F, 11.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(152, 119).addBox(-8.5F, 11.0F, 13.0F, 17.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(152, 121).addBox(-8.5F, 11.0F, 0.0F, 17.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(82, 176).mirror().addBox(6.5F, -14.0F, 18.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(164, 59).addBox(-6.5F, -14.0F, 18.0F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(186, 34).addBox(-1.0F, -12.0F, 18.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(134, 184).addBox(-8.5F, -14.0F, 17.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(138, 194).mirror().addBox(-8.5F, -7.0F, 0.0F, 0.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(150, 193).mirror().addBox(-8.5F, -14.0F, 3.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(180, 18).mirror().addBox(-8.5F, -12.0F, 3.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(180, 33).addBox(7.5F, -12.0F, 3.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(134, 190).addBox(7.5F, -14.0F, 17.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(174, 176).addBox(7.5F, -14.0F, 3.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(180, 23).addBox(7.5F, -12.0F, 10.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(146, 194).addBox(8.5F, -7.0F, 13.0F, 0.0F, 18.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(22, 193).addBox(8.5F, -7.0F, 5.5F, 0.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(142, 194).addBox(8.5F, -7.0F, 0.0F, 0.0F, 18.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(88, 176).addBox(-8.5F, -14.0F, 18.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(180, 28).mirror().addBox(-8.5F, -12.0F, 10.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(134, 194).mirror().addBox(-8.5F, -7.0F, 13.0F, 0.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(22, 184).mirror().addBox(-8.5F, -7.0F, 5.5F, 0.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tier2 = chests.addOrReplaceChild("tier2", CubeListBuilder.create().texOffs(48, 176).addBox(-7.5F, -18.0F, 10.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tier3 = chests.addOrReplaceChild("tier3", CubeListBuilder.create().texOffs(27, 200).mirror().addBox(8.0F, -3.5F, -4.0F, 3.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(2, 178).addBox(-11.0F, -3.5F, -3.5F, 3.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 7.0F));

        PartDefinition tail_armor = tail1.addOrReplaceChild("tail_armor", CubeListBuilder.create().texOffs(192, 21).addBox(-8.5F, -8.25F, -1.5F, 17.0F, 0.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r6 = tail_armor.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(151, 142).mirror().addBox(-9.0F, 0.0F, -10.0F, 9.0F, 0.0F, 20.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.5F, -8.25F, 8.5F, 0.0F, 0.0F, -1.6144F));

        PartDefinition cube_r7 = tail_armor.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(145, 133).addBox(-8.5F, 0.0F, -9.0F, 17.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.25F, 18.5F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r8 = tail_armor.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(204, 130).addBox(-8.5F, 0.0F, -9.0F, 17.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -1.5F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r9 = tail_armor.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(169, 142).addBox(0.0F, 0.0F, -10.0F, 9.0F, 0.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, -8.25F, 8.5F, 0.0F, 0.0F, 1.6144F));

        PartDefinition torso = Body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-10.5F, -12.025F, -24.0F, 21.0F, 24.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.4479F, 30.4721F));

        PartDefinition spike_front = torso.addOrReplaceChild("spike_front", CubeListBuilder.create(), PartPose.offset(0.0F, -10.775F, -17.25F));

        PartDefinition cube_r10 = spike_front.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(190, 203).mirror().addBox(-1.5F, -6.8614F, -1.8834F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.0F, 5.75F, -3.0F, -0.5713F, 0.4259F, 0.5713F));

        PartDefinition cube_r11 = spike_front.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(190, 192).addBox(-1.5F, -6.8614F, -1.8834F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 5.75F, -3.0F, -0.5713F, -0.4259F, -0.5713F));

        PartDefinition spike_back = torso.addOrReplaceChild("spike_back", CubeListBuilder.create(), PartPose.offset(0.0F, -10.85F, -8.25F));

        PartDefinition cube_r12 = spike_back.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(192, 26).mirror().addBox(-1.5F, -3.5F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.0F, 1.325F, 2.25F, -0.5355F, 0.4718F, 0.6537F));

        PartDefinition cube_r13 = spike_back.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(192, 26).addBox(-1.5F, -3.5F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 1.325F, 2.25F, -0.5355F, -0.4718F, -0.6537F));

        PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(154, 93).addBox(-7.5F, -9.5F, -7.0F, 15.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.525F, -24.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 84).addBox(-8.5F, -12.2679F, -25.1464F, 17.0F, 14.0F, 25.0F, new CubeDeformation(0.0F))
                .texOffs(174, 192).addBox(-8.5F, 1.732F, -25.1464F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(174, 192).mirror().addBox(6.5F, 1.732F, -25.1464F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -0.8555F, -6.3326F));

        PartDefinition cube_r14 = head.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(106, 184).addBox(-2.5F, -2.75F, 4.5F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(192, 0).addBox(-2.5F, -2.75F, -0.5F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.7679F, -31.3964F, 1.0472F, 0.0F, 0.0F));

        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 48).addBox(-9.5F, -0.0963F, -26.0069F, 19.0F, 10.0F, 26.0F, new CubeDeformation(0.0F))
                .texOffs(0, 193).addBox(-9.5F, -4.0963F, -11.0069F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(166, 34).addBox(-3.0F, -4.0963F, -26.0069F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 193).mirror().addBox(8.5F, -4.0963F, -11.0069F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 1.8284F, -0.1395F));

        PartDefinition cube_r15 = jaw.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(6, 246).mirror().addBox(-1.0F, -1.0F, -6.75F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.5555F, -7.0427F, -21.1215F, -1.4093F, 0.0665F, 0.0926F));

        PartDefinition cube_r16 = jaw.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 207).mirror().addBox(-1.0F, -1.0F, -6.5F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.5F, -3.3389F, -17.2555F, -1.0472F, 0.0F, 0.48F));

        PartDefinition cube_r17 = jaw.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(6, 237).addBox(-1.0F, -1.0F, -6.75F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.5555F, -7.0427F, -21.1215F, -1.4093F, -0.0665F, -0.0926F));

        PartDefinition cube_r18 = jaw.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 222).addBox(-3.0F, -1.0F, -6.5F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, -3.3389F, -17.2555F, -1.0472F, 0.0F, -0.48F));

        PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(90, 21).addBox(-9.0F, -13.25F, -20.0F, 18.0F, 1.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(174, 162).addBox(-9.0F, -13.25F, 0.0F, 18.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 151).addBox(-9.0F, -13.25F, -20.0F, 0.0F, 13.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(98, 138).mirror().addBox(9.0F, -13.25F, -20.0F, 0.0F, 13.0F, 20.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(72, 176).addBox(-1.0F, -17.25F, -12.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r19 = helmet.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(14, 193).addBox(-1.0F, -8.25F, -0.5F, 3.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(220, 127).addBox(0.5F, -12.25F, 0.5F, 0.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -17.25F, -10.5F, 1.3526F, 0.0F, 0.0F));

        PartDefinition right_front_leg = torso.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(77, 42).addBox(-8.0F, 0.25F, -7.0F, 11.0F, 15.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(66, 218).addBox(-9.0F, 15.25F, -8.0F, 13.0F, 12.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.5F, -3.275F, -19.0F));

        PartDefinition left_front_leg = torso.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 150).mirror().addBox(-3.0F, 0.25F, -7.0F, 11.0F, 15.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 123).mirror().addBox(-4.0F, 15.25F, -8.0F, 13.0F, 12.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(10.5F, -3.275F, -19.0F));

        PartDefinition blanky = torso.addOrReplaceChild("blanky", CubeListBuilder.create().texOffs(159, 47).addBox(-10.0F, -16.0F, -4.0F, 20.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(191, 0).addBox(-9.0F, -12.25F, -24.0F, 18.0F, 0.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(191, 171).addBox(9.0F, -12.25F, -24.0F, 2.0F, 0.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(195, 171).mirror().addBox(-11.0F, -12.25F, -24.0F, 2.0F, 0.0F, 21.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(139, 66).addBox(-11.0F, -13.025F, -24.5F, 22.0F, 0.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r20 = blanky.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(187, 206).mirror().addBox(-10.0F, 0.0F, -12.5F, 10.0F, 0.0F, 25.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-11.0F, -13.0F, -12.0F, 0.0F, 0.0F, -1.5272F));

        PartDefinition cube_r21 = blanky.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(119, 66).addBox(0.0F, 0.0F, -12.5F, 10.0F, 0.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -13.0F, -12.0F, 0.0F, 0.0F, 1.5272F));

        PartDefinition cube_r22 = blanky.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(202, 161).addBox(-11.0F, 0.0F, -10.0F, 22.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, -24.5F, 1.5272F, 0.0F, 0.0F));

        PartDefinition chestplate = torso.addOrReplaceChild("chestplate", CubeListBuilder.create().texOffs(183, 66).addBox(-11.0F, -12.525F, -24.5F, 22.0F, 0.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r23 = chestplate.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(187, 41).mirror().addBox(-14.0F, 0.0F, -12.0F, 14.0F, 0.0F, 25.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-11.0F, -12.525F, -12.5F, 0.0F, 0.0F, -1.5708F));

        PartDefinition cube_r24 = chestplate.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(191, 105).addBox(0.0F, 0.0F, -12.0F, 14.0F, 0.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -12.525F, -12.5F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r25 = chestplate.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(194, 91).addBox(-11.0F, 0.0F, -0.5F, 22.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.525F, -24.5F, -1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r26 = chestplate.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(198, 192).addBox(-11.0F, 0.0F, 0.0F, 22.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.525F, 0.5F, -1.5708F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(TectoraxEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(TectoraxAnimations.ANIM_TECTORAX_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, TectoraxAnimations.ANIM_TECTORAX_IDLE, ageInTicks, 1f);

        this.animate(entity.sitDownAnimationState, TectoraxAnimations.ANIM_TECTORAX_SIT, ageInTicks, 1.0F);
        this.animate(entity.sitPoseAnimationState, TectoraxAnimations.ANIM_TECTORAX_SITTING, ageInTicks, 1.0F);
        this.animate(entity.sitUpAnimationState, TectoraxAnimations.ANIM_TECTORAX_EMERGE, ageInTicks, 1.0F);

        this.animate(entity.knockOutAnimationState, TectoraxAnimations.ANIM_TECTORAX_KNOCKED, ageInTicks, 1.0F);
        this.animate(entity.sleepAnimationState, TectoraxAnimations.ANIM_TECTORAX_SLEEPING, ageInTicks, 1.0F);
        this.animate(entity.wakeUpAnimationState, TectoraxAnimations.ANIM_TECTORAX_WAKE_UP, ageInTicks, 1.0F);

        this.animate(entity.attackAnimationState, TectoraxAnimations.ANIM_TECTORAX_ATTACK, ageInTicks, 1.0F);


        // VERY IMPORTANT AND VERY COOL!//
        tier1.visible = entity.hasTier1Chest();
        tier2.visible = entity.hasTier2Chest();  //THE VISIBILITY OF CERTAIN BODY PARTS DEPENDS OF A CUSTOM METHOD!
        tier3.visible = entity.hasTier3Chest();
        helmet.visible = entity.isWearingBodyArmor();
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return Body;
    }
}
