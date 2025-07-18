package net.matos.elementalrealms.entity.custom;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.entity.ai.TectoraxAttackGoal;
import net.matos.elementalrealms.entity.ai.TectoraxBreedGoal;
import net.matos.elementalrealms.entity.ai.TectoraxSleepHandler;
import net.matos.elementalrealms.item.ModItems;
import net.matos.elementalrealms.item.custom.TectoraxArmorItem;
import net.matos.elementalrealms.screen.custom.TectoraxMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class TectoraxEntity extends TamableAnimal implements ContainerListener, HasCustomInventoryScreen {


    public static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(TectoraxEntity.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState earthquakeAttackAnimationState = new AnimationState();

    public int attackAnimationTimeout = 0;  // 3 seconds * 20 ticks



    public final AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeout = 60;  // 3 seconds * 20 ticks



    public static final EntityDataAccessor<Boolean> DOWNSTATE =
            SynchedEntityData.defineId(TectoraxEntity.class, EntityDataSerializers.BOOLEAN);



    public int earthquakeAttackTick = -1;
    public long knockOutStartTick = -1;
    public long wakeUpStartTick = -1;
    public boolean wasInDownstate = false;

    public final AnimationState knockOutAnimationState = new AnimationState();
    public final AnimationState sleepAnimationState = new AnimationState();
    public final AnimationState wakeUpAnimationState = new AnimationState();


    public final AnimationState sitDownAnimationState = new AnimationState();
    public final AnimationState sitPoseAnimationState = new AnimationState();
    public final AnimationState sitUpAnimationState = new AnimationState();

    public static final EntityDataAccessor<Long> LAST_POSE_CHANGE_TICK =
            SynchedEntityData.defineId(TectoraxEntity.class, EntityDataSerializers.LONG);

    private static final EntityDataAccessor<Boolean> HAS_TIER_1_CHEST =
            SynchedEntityData.defineId(TectoraxEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_TIER_2_CHEST =
            SynchedEntityData.defineId(TectoraxEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_TIER_3_CHEST =
            SynchedEntityData.defineId(TectoraxEntity.class, EntityDataSerializers.BOOLEAN);

    protected SimpleContainer inventory;

    private final int TIER_1_CHEST_SLOT = 2;
    private final int TIER_2_CHEST_SLOT = 3;
    private final int TIER_3_CHEST_SLOT = 4;

    private static final EntityDataAccessor<ItemStack> DYE_STACK =
            SynchedEntityData.defineId(TectoraxEntity.class, EntityDataSerializers.ITEM_STACK);


    public TectoraxEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.createInventory();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(0, new SitWhenOrderedToGoal(this));

        if (!this.isBaby()) {
            this.goalSelector.addGoal(3, new TectoraxAttackGoal(this, 1.2D, true));
        }


        this.goalSelector.addGoal(2, new TectoraxBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D, Ingredient.of(ModItems.EMBEROOT.get()), true));

        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.25d, 18f, 7f));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1d));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, 10, true, false, player -> !this.isTame()));
        this.targetSelector.addGoal(3, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(4, new OwnerHurtTargetGoal(this));
    }

    private void enableGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new SitWhenOrderedToGoal(this));
        if (!this.isBaby()) {
            this.goalSelector.addGoal(3, new TectoraxAttackGoal(this, 1.2D, true));
        }
        this.goalSelector.addGoal(2, new TectoraxBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D, Ingredient.of(ModItems.EMBEROOT.get()), true));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.25d, 18f, 7f));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1d));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    private void disableGoals() {
        this.goalSelector.removeAllGoals(goal -> true);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 120D)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75D)
                .add(Attributes.ATTACK_DAMAGE, 12f)
                .add(Attributes.ARMOR_TOUGHNESS, 2f)
                .add(Attributes.ARMOR, 2f)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ModItems.EMBEROOT.get());
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    /* ANIMATION */
    private void setupAnimationStates() {
        boolean downstate = this.entityData.get(DOWNSTATE);
        boolean attacking = this.isAttacking();

        // === IDLE ANIMATION ===
        if (!downstate && !attacking && wakeUpStartTick == -1) {
            if (this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = 60;
                this.idleAnimationState.start(this.tickCount);
            } else {
                --this.idleAnimationTimeout;
            }
        } else {
            this.idleAnimationTimeout = 0;
            this.idleAnimationState.stop();
        }

        // === SITTING ANIMATIONS ===
        if (this.isVisuallySitting()) {
            this.sitUpAnimationState.stop();
            if (this.isVisuallySittingDown()) {
                this.sitDownAnimationState.startIfStopped(this.tickCount);
                this.sitPoseAnimationState.stop();
            } else {
                this.sitDownAnimationState.stop();
                this.sitPoseAnimationState.startIfStopped(this.tickCount);
            }
        } else {
            this.sitDownAnimationState.stop();
            this.sitPoseAnimationState.stop();
            this.sitUpAnimationState.animateWhen(this.isInPoseTransition() && this.getPoseTime() >= 0L, this.tickCount);
        }

        // === ATTACK ANIMATION ===
        if (attacking && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 15;
            attackAnimationState.start(this.tickCount);
        } else {
            --attackAnimationTimeout;
        }

        if (!attacking) {
            attackAnimationState.stop();
        }

        // === KO / SLEEP / WAKE LOGIC ===
        TectoraxSleepHandler.handle(this);
    }



    public boolean isDownstate() {
        return this.entityData.get(DOWNSTATE);
    }


    @Override
    public void tick() {
        super.tick();

        // === SERVER-SIDE ===
        if (!this.level().isClientSide) {
            // Prevent babies from downstate or tame state
            if (!this.isBaby()) {
                if (!this.isTame()) {
                    if (this.getHealth() < 15.0F) {
                        if (!isDownstate()) {
                            System.out.println("[DEBUG] Health below 15 - disabling goals and setting downstate true");
                            disableGoals();
                            this.entityData.set(DOWNSTATE, true);
                        }
                    } else {
                        if (isDownstate()) {
                            System.out.println("[DEBUG] Health >= 15 - enabling goals and setting downstate false");
                            enableGoals();
                            this.entityData.set(DOWNSTATE, false);
                        }
                    }
                } else {
                    if (isDownstate()) {
                        System.out.println("[DEBUG] Tamed but still in downstate — resetting");
                        enableGoals();
                        this.entityData.set(DOWNSTATE, false);
                    }
                }
            } else {
                // Babies can never be in downstate or have goals disabled
                if (isDownstate()) {
                    enableGoals();
                    this.entityData.set(DOWNSTATE, false);
                }
            }
        }

        // === CLIENT-SIDE ===
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        // === UNIVERSAL ===
        if (!this.level().isClientSide && this.isSitting() && this.isVehicle()) {
            System.out.println("[DEBUG] Forcing stand when ridden");
            this.resetLastPoseChangeTick(this.level().getGameTime());
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        // Check if holding Glowberry (replace if custom)
        boolean isGlowberry = item == Items.GLOW_BERRIES;

        // Client-side handling for hand animation and result prediction
        if (this.level().isClientSide) {
            boolean isTameItem = this.isFood(itemstack);
            if (this.isTame() && !this.isVehicle()) {
                return InteractionResult.SUCCESS;
            } else if (!this.isTame() && isTameItem && isDownstate() && !this.isBaby()) {
                return InteractionResult.SUCCESS;
            }
        }

        // Special handling for Glowberries as breeding food:
        if (isGlowberry) {
            if (this.isBaby()) {
                if (!level().isClientSide) {
                    player.displayClientMessage(Component.literal("The Tectorax is too young to tame!"), true);
                }
                return InteractionResult.CONSUME;
            }

            // Only allow breeding (love state + particles) if tamed
            if (this.isTame()) {
                if (this.canFallInLove()) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    this.setInLove(player);
                    if (!this.level().isClientSide) {
                        this.level().broadcastEntityEvent(this, (byte)7); // Heart particles
                    }
                    return InteractionResult.sidedSuccess(level().isClientSide);
                } else {
                    return InteractionResult.PASS;
                }
            } else {
                // Not tamed = no particles, no breeding
                return InteractionResult.PASS;
            }
        }

        // Existing taming logic with other foods when in downstate
        if (!this.isTame() && this.isFood(itemstack)) {
            if (this.isBaby()) {
                if (!level().isClientSide) {
                    player.displayClientMessage(Component.literal("The Tectorax is too young to tame!"), true);
                }
                return InteractionResult.CONSUME;
            }

            if (!isDownstate()) {
                if (!level().isClientSide) {
                    player.displayClientMessage(Component.literal("The Tectorax is too strong to tame right now!"), true);
                }
                return InteractionResult.CONSUME;
            }

            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (this.random.nextInt(8) == 0) { // 1 in 8 chance to tame
                this.tame(player);
                this.level().broadcastEntityEvent(this, (byte)7); // success particles
            } else {
                this.level().broadcastEntityEvent(this, (byte)6); // failure particles
            }

            return InteractionResult.CONSUME;
        }

        // Interaction when tamed by owner
        if (this.isTame() && this.isOwnedBy(player)) {
            if (player.isShiftKeyDown()) {
                // Toggle sitting only if NOT holding glowberry (handled above)
                this.toggleSitting();
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }

            // Ride only if not holding glowberry
            if (!player.isShiftKeyDown()) {
                player.startRiding(this);
                return InteractionResult.CONSUME;
            }
        }

        return super.mobInteract(player, hand);
    }









    public boolean isSitting() {
        return this.entityData.get(LAST_POSE_CHANGE_TICK) < 0L;
    }

    public boolean isInPoseTransition() {
        long i = this.getPoseTime();
        return i < (long)(this.isSitting() ? 40 : 25);  // sitting transition 40 ticks, standing 25 ticks
    }

    public boolean isVisuallySitting() {
        return this.getPoseTime() < 0L != this.isSitting();
    }

    private boolean isVisuallySittingDown() {
        return this.isSitting() && this.getPoseTime() < 25L && this.getPoseTime() >= 0L;  // 25 ticks sitting down
    }

    public void resetLastPoseChangeTick(long pLastPoseChangeTick) {
        this.entityData.set(LAST_POSE_CHANGE_TICK, pLastPoseChangeTick);
    }

    private void resetLastPoseChangeTickToFullStand(long pLastPoseChangedTick) {
        this.resetLastPoseChangeTick(Math.max(0L, pLastPoseChangedTick - 66L));  // 66 = 40 + 25 + 1 ticks
    }

    public long getPoseTime() {
        return this.level().getGameTime() - Math.abs(this.entityData.get(LAST_POSE_CHANGE_TICK));
    }

    public void setAttacking(boolean attacking)
    {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking()
    {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DOWNSTATE, false);
        pBuilder.define(ATTACKING, false);
        pBuilder.define(LAST_POSE_CHANGE_TICK, 0L);

        pBuilder.define(HAS_TIER_1_CHEST, false);
        pBuilder.define(HAS_TIER_2_CHEST, false);
        pBuilder.define(HAS_TIER_3_CHEST, false);
        pBuilder.define(DYE_STACK, ItemStack.EMPTY);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putLong("LastPoseTick", this.entityData.get(LAST_POSE_CHANGE_TICK));

        ListTag listtag = new ListTag();
        for (int x = 0; x < this.inventory.getContainerSize(); x++) {
            ItemStack itemstack = this.inventory.getItem(x);
            if (!itemstack.isEmpty()) {
                CompoundTag compoundtag = new CompoundTag();
                compoundtag.putByte("Slot", (byte)(x));
                listtag.add(itemstack.save(this.registryAccess(), compoundtag));
            }
        }
        pCompound.put("Items", listtag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        long i = pCompound.getLong("LastPoseTick");
        if (i < 0L) {
            this.setPose(Pose.SITTING);
        }
        this.resetLastPoseChangeTick(i);

        this.createInventory();
        ListTag listtag = pCompound.getList("Items", 10);
        for (int x = 0; x < listtag.size(); x++) {
            CompoundTag compoundtag = listtag.getCompound(x);
            int j = compoundtag.getByte("Slot") & 255;
            if (j < this.inventory.getContainerSize()) {
                this.inventory.setItem(j, ItemStack.parse(this.registryAccess(), compoundtag).orElse(ItemStack.EMPTY));
            }
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason,
                                        @Nullable SpawnGroupData pSpawnData) {
        this.resetLastPoseChangeTickToFullStand(pLevel.getLevel().getGameTime());
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData);
    }

    public void toggleSitting() {
        if (this.isSitting()) {
            standUp();
        } else {
            sitDown();
        }
    }

    public void sitDown() {
        if (!this.isSitting()) {
            this.makeSound(SoundEvents.CAMEL_SIT);
            this.setPose(Pose.SITTING);
            this.gameEvent(GameEvent.ENTITY_ACTION);
            this.resetLastPoseChangeTick(-this.level().getGameTime());
        }

        setOrderedToSit(true);
        setInSittingPose(true);
    }

    public void standUp() {
        if (this.isSitting()) {
            this.makeSound(SoundEvents.CAMEL_STAND);
            this.setPose(Pose.STANDING);
            this.gameEvent(GameEvent.ENTITY_ACTION);
            this.resetLastPoseChangeTick(this.level().getGameTime());
        }

        setOrderedToSit(false);
        setInSittingPose(false);
    }


    /* INVENTORY */

    public boolean hasTier1Chest() {
        return this.entityData.get(HAS_TIER_1_CHEST);
    }

    public boolean hasTier2Chest() {
        return this.entityData.get(HAS_TIER_2_CHEST);
    }

    public boolean hasTier3Chest() {
        return this.entityData.get(HAS_TIER_3_CHEST);
    }

    public void setChest(int slot, boolean chested) {
        if(slot == TIER_1_CHEST_SLOT) {
            this.entityData.set(HAS_TIER_1_CHEST, chested);
        } else if(slot == TIER_2_CHEST_SLOT) {
            this.entityData.set(HAS_TIER_2_CHEST, chested);
        } else if(slot == TIER_3_CHEST_SLOT) {
            this.entityData.set(HAS_TIER_3_CHEST, chested);
        } else {
            ElementalRealms.LOGGER.error("Can't give chest to a Slot that doesn't exist!");
        }
    }


    protected void createInventory() {
        SimpleContainer simplecontainer = this.inventory;
        this.inventory = new SimpleContainer(this.getInventorySize());
        if (simplecontainer != null) {
            simplecontainer.removeListener(this);
            int i = Math.min(simplecontainer.getContainerSize(), this.inventory.getContainerSize());

            for (int j = 0; j < i; j++) {
                ItemStack itemstack = simplecontainer.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(j, itemstack.copy());
                }
            }
        }

        this.inventory.addListener(this);
    }

    public final int getInventorySize() {
        return getInventorySize(4);
    }

    public static int getInventorySize(int columns) {
        return columns * 3 + 5;
    }

    public boolean hasInventoryChanged(Container inventory) {
        return this.inventory != inventory;
    }


    @Override
    public void containerChanged(Container container) {
        if(container.getItem(TIER_1_CHEST_SLOT).is(Items.CHEST) && !hasTier1Chest()) {
            setChest(TIER_1_CHEST_SLOT, true);
        }
        if(container.getItem(TIER_2_CHEST_SLOT).is(Items.CHEST) && !hasTier2Chest()) {
            setChest(TIER_2_CHEST_SLOT, true);
        }
        if(container.getItem(TIER_3_CHEST_SLOT).is(Items.CHEST) && !hasTier3Chest()) {
            setChest(TIER_3_CHEST_SLOT, true);
        }

        if(!container.getItem(TIER_1_CHEST_SLOT).is(Items.CHEST) && hasTier1Chest()) {
            setChest(TIER_1_CHEST_SLOT, false);
            dropChestInventory(TIER_1_CHEST_SLOT);
        }
        if(!container.getItem(TIER_2_CHEST_SLOT).is(Items.CHEST) && hasTier2Chest()) {
            setChest(TIER_2_CHEST_SLOT, false);
            dropChestInventory(TIER_2_CHEST_SLOT);
        }
        if(!container.getItem(TIER_3_CHEST_SLOT).is(Items.CHEST) && hasTier3Chest()) {
            setChest(TIER_3_CHEST_SLOT, false);
            dropChestInventory(TIER_3_CHEST_SLOT);
        }

        if(container.getItem(0).getItem() instanceof TectoraxArmorItem) {
            setBodyArmorItem(container.getItem(0));
        }
        if(container.getItem(0).isEmpty() && isWearingBodyArmor()) {
            setBodyArmorItem(ItemStack.EMPTY);
        }

        if(!container.getItem(1).isEmpty()) {
            this.entityData.set(DYE_STACK, container.getItem(1));
        }
        if(container.getItem(1).isEmpty()) {
            this.entityData.set(DYE_STACK, ItemStack.EMPTY);
        }
    }


    private void dropChestInventory(int slot) {
        if(slot == TIER_1_CHEST_SLOT) {
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(5, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(6, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(7, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(8, 64));
        }

        if(slot == TIER_2_CHEST_SLOT) {
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(9, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(10, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(11, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(12, 64));
        }

        if(slot == TIER_3_CHEST_SLOT) {
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(13, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(14, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(15, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(16, 64));
        }
    }

    /* DYEABLE */
    @Nullable
    private static DyeColor getDyeColor(ItemStack stack) {
        Block block = Block.byItem(stack.getItem());
        return block instanceof WoolCarpetBlock ? ((WoolCarpetBlock)block).getColor() : null;
    }

    @Nullable
    public DyeColor getSwag() {
        return getDyeColor(this.entityData.get(DYE_STACK));
    }


    @Override
    public void openCustomInventoryScreen(Player player) {
        if (!this.level().isClientSide && (!this.isVehicle() || this.hasPassenger(player)) && this.isTame()) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            if (player.containerMenu != player.inventoryMenu) {
                player.closeContainer();
            }

            serverPlayer.openMenu(new SimpleMenuProvider((ix, playerInventory, playerEntityx) ->
                    new TectoraxMenu(ix, playerInventory, this.inventory, this, 4), this.getDisplayName()), buf -> {
                buf.writeUUID(getUUID());
            });
        }
    }

    /* ARMOR */
    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
        if (!this.canArmorAbsorb(damageSource)) {
            super.actuallyHurt(damageSource, damageAmount);
        } else {
            ItemStack itemstack = this.getBodyArmorItem();
            itemstack.hurtAndBreak(Mth.ceil(damageAmount), this, EquipmentSlot.BODY);

            if(itemstack.getItem() instanceof TectoraxArmorItem tectoraxArmorItem) {
                int damagereducton = tectoraxArmorItem.getDefense() / 2; // depends on what armor
                super.actuallyHurt(damageSource, Math.max(0, damageAmount - damagereducton));
            }
        }
    }

    private boolean canArmorAbsorb(DamageSource damageSource) {
        return this.hasArmorOn() && !damageSource.is(DamageTypeTags.BYPASSES_WOLF_ARMOR);
    }

    public boolean hasArmorOn() {
        return isWearingBodyArmor();
    }

    /* RIDEABLE */

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isVehicle() && this.getControllingPassenger() instanceof LivingEntity rider) {
            this.setYRot(rider.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(rider.getXRot() * 0.5F);
            this.setRot(this.getYRot(), this.getXRot());
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.getYRot();

            float forward = rider.zza;
            float strafe = rider.xxa;

            if (forward <= 0.0F) {
                forward *= 0.65F; // reduce speed when going backward
            }

            this.setSpeed(0.1F); // your custom speed here
            super.travel(new Vec3(strafe, travelVector.y, forward));
        } else {
            super.travel(travelVector);
        }

    }



    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isVehicle() && this.getControllingPassenger() instanceof Player) {
            this.getNavigation().stop();
        }
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return (LivingEntity) this.getFirstPassenger();
    }
}