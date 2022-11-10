package me.dev.myclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Keyboard;

import me.dev.myclient.Skidmark.Event.EventMotion;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;

public class Skidmark {

	public static Minecraft mc;
	
	public static Skidmark SkidmarkInstance = new Skidmark(Minecraft.getMinecraft());
	
	public static CopyOnWriteArrayList<Module> theModulesList;
	
	public static boolean start = false;
	
	public Skidmark(Minecraft minecraft) {
		mc = minecraft;
		if(minecraft == null) {
			mc = Minecraft.getMinecraft();
		}
		
		new Forcefield() {{
			keybind = Keyboard.KEY_K;
		}};
		
	}
	
	public static void onTheEvent(Event e, boolean lambda) throws IOException {
		if(!start) return;
		if(lambda) {
			theModulesList.forEach(Module -> {
				
				if(Module != null) Module.onAnEvent(e);
				
				if(e == null) System.out.println("Failed to event was null!");
				
			});
		}else {
		for(Module module : theModulesList) { 
			if(module.toggled == true)
			module.onAnEvent(e); else;
			
		if(module == null) continue;
			
		};}
		
	}
	
	public static class Event {
		public boolean stopped = false;
		public boolean noDataCarried = false;
		public void set_Stopped(boolean state) {
			stopped = state;
		}
		public boolean is_Stopped() {
			if(stopped) return true; else return false;
		}
		
		public static class EventMotion extends Event {
			public double x;
			public double y;
			public double z;
			public float yawRotation;
			public float pitchRotation;
			public boolean groundState;
			public boolean pre;
			public EventMotion(double x, double y, double z, float yawRotation, float pitchRotation, boolean groundState, boolean pre) {
				setX(x); setY(y); setZ(z); 
				setYawRotation(yawRotation); setPitchRotation(pitchRotation); 
				setGroundState(groundState);
				setPre(pre);
			}
			public double getX() {
				return x;
			}
			public void setX(double x) {
				this.x = x;
			}

			public double getY() {
				return y;
			}

			public void setY(double y) {
				this.y = y;
			}

			public double getZ() {
				return z;
			}

			public void setZ(double z) {
				this.z = z;
			}

			public float getYawRotation() {
				return yawRotation;
			}

			public void setYawRotation(float yawRotation) {
				this.yawRotation = yawRotation;
			}

			public float getPitchRotation() {
				return pitchRotation;
			}

			public void setPitchRotation(float pitchRotation) {
				this.pitchRotation = pitchRotation;
			}

			public boolean isGroundState() {
				return groundState;
			}

			public void setGroundState(boolean groundState) {
				this.groundState = groundState;
			}
			public boolean isPre() {
				return pre != true ? false : true;
			}
			public void setPre(boolean pre) {
				this.pre = pre;
			}
			
		}

		public static class EventRenderHUD extends Event {
			
			public EventRenderHUD() {
				// this means that there is no data that is going to be carried and transmitted
				noDataCarried = true;
			}
			
		}

		public class EventSendPacket extends Event {
			
			public Packet data;
			public SkidmarkPacket skidmarkPacket;
			public EventSendPacket(Packet packet) {
				setData(packet);
			}
			
			public class SkidmarkPacket {
				public boolean outgoing;
				public Packet data;
				public SkidmarkPacket(Packet in) {
					setData(in);
				}
				public boolean isOutgoing() {
					if(getData() instanceof C00PacketKeepAlive) setOutgoing(true);
					else if(getData() instanceof C01PacketChatMessage) setOutgoing(true);
					else if(getData() instanceof C02PacketUseEntity) setOutgoing(true);
					else if(getData() instanceof C03PacketPlayer) setOutgoing(true);
					else if(getData() instanceof C07PacketPlayerDigging) setOutgoing(true);
					else if(getData() instanceof C08PacketPlayerBlockPlacement) setOutgoing(true);
					else if(getData() instanceof C09PacketHeldItemChange) setOutgoing(true);
					else if(getData() instanceof C0APacketAnimation) setOutgoing(true);
					else if(getData() instanceof C0BPacketEntityAction) setOutgoing(true);
					else if(getData() instanceof C0DPacketCloseWindow) setOutgoing(true);
					else if(getData() instanceof C0EPacketClickWindow) setOutgoing(true);
					else if(getData() instanceof C0FPacketConfirmTransaction) setOutgoing(true);
					else if(getData() instanceof C10PacketCreativeInventoryAction) setOutgoing(true);
					else if(getData() instanceof C11PacketEnchantItem) setOutgoing(true);
					else if(getData() instanceof C12PacketUpdateSign) setOutgoing(true);
					else if(getData() instanceof C13PacketPlayerAbilities) setOutgoing(true);
					else if(getData() instanceof C14PacketTabComplete) setOutgoing(true);
					else if(getData() instanceof C15PacketClientSettings) setOutgoing(true);
					else if(getData() instanceof C16PacketClientStatus) setOutgoing(true);
					else if(getData() instanceof C17PacketCustomPayload) setOutgoing(true);
					else if(getData() instanceof C18PacketSpectate) setOutgoing(true);
					else if(getData() instanceof C19PacketResourcePackStatus) setOutgoing(true);
					else setOutgoing(false);
					
					return outgoing;
				}

				public void setOutgoing(boolean outgoing) {
					this.outgoing = outgoing;
				}

				public Packet getData() {
					return data;
				}

				public void setData(Packet data) {
					this.data = data;
				}
			}

			public Packet getData() {
				return data;
			}

			public void setData(Packet data) {
				this.data = data;
			}
			
			public void generateSkidmarkPacket() {
				
				skidmarkPacket = new SkidmarkPacket(getData());
				
			}
			
		}
		
	}
	
	public static class Module {
		
		public boolean toggled;
		public String name;
		
		public int keybind;
		
		public Module(String name) {
			this.name = name;
		}
		
		public void onAnEvent(Event event) {
			
		}
		
	}
	
	public static class Forcefield extends Module {

		public Forcefield() {
			super("Forcefield");
		}
		
		@Override
		public void onAnEvent(Event event) {
			
			if(event instanceof EventMotion) {
				
				if(((EventMotion)event).isPre()) {
					
					EntityPlayer target = null;
					
					for(Entity entity : Skidmark.getSkidmarkInstance().getMc().theWorld.loadedEntityList) {
						
						if(entity == Skidmark.getSkidmarkInstance().getMc().thePlayer) continue;
						
						if(entity instanceof EntityPlayer) {
							System.out.println("Player found!");
							Entity player = Skidmark.getSkidmarkInstance().getMc().thePlayer;
							if(target == player) continue;
							
							if(target == null) target = (EntityPlayer) entity; else {
								
								if(Skidmark.getSkidmarkInstance().getMc().thePlayer.getDistanceToEntity(entity) < Skidmark.getSkidmarkInstance().getMc().thePlayer.getDistanceToEntity(target)) {
									
									target = (EntityPlayer) entity;
									
								}
								
							}
						}
						
					}
					if(target == null) return;
					
					Skidmark.getSkidmarkInstance().getMc().thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, C02PacketUseEntity.Action.ATTACK));
					
				}
				
			}
			
		}
		
	}

	public Minecraft getMc() {
		setMc(mc);
		return mc;
	}

	public void setMc(Minecraft mc) {
		if(mc == null) mc = Minecraft.getMinecraft();
		this.mc = mc;
	}

	public static Skidmark getSkidmarkInstance() {
		return SkidmarkInstance;
	}

	public static void setSkidmarkInstance(Skidmark skidmarkInstance) {
		SkidmarkInstance = skidmarkInstance;
	}

	public static CopyOnWriteArrayList<Module> getTheModulesList() {
		if(theModulesList == null && !start) {
			theModulesList = new CopyOnWriteArrayList<>();
			start = true;
			theModulesList.add(new Forcefield() {{
				keybind = Keyboard.KEY_K;
			}});
		}
		return theModulesList;
	}

	public static void setTheModulesList(CopyOnWriteArrayList<Module> theModulesList) {
		Skidmark.theModulesList = theModulesList;
	}
}