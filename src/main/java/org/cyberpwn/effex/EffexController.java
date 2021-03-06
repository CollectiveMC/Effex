package org.cyberpwn.effex;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.cyberpwn.effex.effect.VampiricEffect;
import org.cyberpwn.effex.enchantments.AlchemistEnchantment;
import org.cyberpwn.effex.enchantments.AntiGravityEnchantment;
import org.cyberpwn.effex.enchantments.BeheadingEnchantment;
import org.cyberpwn.effex.enchantments.BlastEnchantment;
import org.cyberpwn.effex.enchantments.BlessingEnchantment;
import org.cyberpwn.effex.enchantments.ButcherEnchantment;
import org.cyberpwn.effex.enchantments.ColdBloodedEnchantment;
import org.cyberpwn.effex.enchantments.DeflectEnchantment;
import org.cyberpwn.effex.enchantments.DefusedEnchantment;
import org.cyberpwn.effex.enchantments.DemolitionEnchantment;
import org.cyberpwn.effex.enchantments.DemolitionExpertEnchantment;
import org.cyberpwn.effex.enchantments.DevilsGraceEnchantment;
import org.cyberpwn.effex.enchantments.DoubleStrikeEnchantment;
import org.cyberpwn.effex.enchantments.DrillEnchantment;
import org.cyberpwn.effex.enchantments.EarthquakeEnchantment;
import org.cyberpwn.effex.enchantments.EmpowermentEnchantment;
import org.cyberpwn.effex.enchantments.EnderEnchantment;
import org.cyberpwn.effex.enchantments.EssenceEnchantment;
import org.cyberpwn.effex.enchantments.ForgeEnchantment;
import org.cyberpwn.effex.enchantments.FrostEnchantment;
import org.cyberpwn.effex.enchantments.HeatSeekerEnchantment;
import org.cyberpwn.effex.enchantments.HellsCurseEnchantment;
import org.cyberpwn.effex.enchantments.InfernoEnchantment;
import org.cyberpwn.effex.enchantments.IronGraspEnchantment;
import org.cyberpwn.effex.enchantments.LongShotEnchantment;
import org.cyberpwn.effex.enchantments.MagnetEnchantment;
import org.cyberpwn.effex.enchantments.MossEnchantment;
import org.cyberpwn.effex.enchantments.NightmareEnchantment;
import org.cyberpwn.effex.enchantments.NocturnalEnchantment;
import org.cyberpwn.effex.enchantments.PoisonEnchantment;
import org.cyberpwn.effex.enchantments.RageEnchantment;
import org.cyberpwn.effex.enchantments.ReinforcedEnchantment;
import org.cyberpwn.effex.enchantments.ShockEnchantment;
import org.cyberpwn.effex.enchantments.SnareEnchantment;
import org.cyberpwn.effex.enchantments.SoulStealerEnchantment;
import org.cyberpwn.effex.enchantments.SoullessEnchantment;
import org.cyberpwn.effex.enchantments.SpringEnchantment;
import org.cyberpwn.effex.enchantments.StaminaEnchantment;
import org.cyberpwn.effex.enchantments.StickyEnchantment;
import org.cyberpwn.effex.enchantments.VampiricEnchantment;
import org.phantomapi.Phantom;
import org.phantomapi.clust.ConfigurableController;
import org.phantomapi.clust.Keyed;
import org.phantomapi.command.Command;
import org.phantomapi.command.CommandAlias;
import org.phantomapi.command.CommandFilter.Permission;
import org.phantomapi.command.CommandFilter.PlayerOnly;
import org.phantomapi.command.PhantomCommand;
import org.phantomapi.command.PhantomSender;
import org.phantomapi.construct.Controllable;
import org.phantomapi.construct.ControllerMessage;
import org.phantomapi.construct.Ticked;
import org.phantomapi.core.SyncStart;
import org.phantomapi.currency.ExperienceCurrency;
import org.phantomapi.currency.Transaction;
import org.phantomapi.event.FalseBlockBreakEvent;
import org.phantomapi.event.PlayerKillPlayerEvent;
import org.phantomapi.event.PlayerMoveBlockEvent;
import org.phantomapi.event.TNTDispenseEvent;
import org.phantomapi.event.TNTPrimeEvent;
import org.phantomapi.gui.Click;
import org.phantomapi.gui.Element;
import org.phantomapi.gui.Guis;
import org.phantomapi.gui.PhantomElement;
import org.phantomapi.gui.PhantomWindow;
import org.phantomapi.gui.Slot;
import org.phantomapi.gui.Window;
import org.phantomapi.inventory.PhantomInventory;
import org.phantomapi.lang.GList;
import org.phantomapi.lang.GMap;
import org.phantomapi.lang.GSound;
import org.phantomapi.multiblock.Multiblock;
import org.phantomapi.nms.NMSX;
import org.phantomapi.physics.VectorMath;
import org.phantomapi.stack.Stack;
import org.phantomapi.sync.Task;
import org.phantomapi.sync.TaskLater;
import org.phantomapi.util.C;
import org.phantomapi.util.F;
import org.phantomapi.util.FinalInteger;
import org.phantomapi.util.M;
import org.phantomapi.util.P;
import org.phantomapi.vfx.LineParticleManipulator;
import org.phantomapi.vfx.ParticleEffect;
import org.phantomapi.world.Area;
import org.phantomapi.world.Blocks;
import org.phantomapi.world.MaterialBlock;
import org.phantomapi.world.PE;
import org.phantomapi.world.W;
import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;
import net.md_5.bungee.api.ChatColor;

@SyncStart
@Ticked(1)
public class EffexController extends ConfigurableController
{
	private GMap<Location, GMap<String, Integer>> enchanted;
	private GMap<Player, Float> affected;
	private GList<CustomEnchantment> enchantments;
	public static EffexController inst;
	private NPCController npcController;
	
	@Keyed("math.tiers")
	public GList<String> tiers = new GList<String>().qadd("apprentice").qadd("novice").qadd("scholar").qadd("expert").qadd("master");
	
	@Keyed("max.enchants")
	public int maxEnchantments = 4;
	
	private GMap<Entity, Integer> ebs;
	private int k;
	
	public EffexController(Controllable parentController)
	{
		super(parentController, "config");
		
		inst = this;
		enchanted = new GMap<Location, GMap<String, Integer>>();
		affected = new GMap<Player, Float>();
		enchantments = new GList<CustomEnchantment>();
		npcController = new NPCController(this);
		ebs = new GMap<Entity, Integer>();
		k = 0;
		
		register(npcController);
	}
	
	@Override
	public void onStart()
	{
		enchantments.add(new ShockEnchantment());
		enchantments.add(new FrostEnchantment());
		enchantments.add(new InfernoEnchantment());
		enchantments.add(new PoisonEnchantment());
		enchantments.add(new VampiricEnchantment());
		enchantments.add(new DeflectEnchantment());
		enchantments.add(new SnareEnchantment());
		enchantments.add(new StickyEnchantment());
		enchantments.add(new BlastEnchantment());
		enchantments.add(new DefusedEnchantment());
		enchantments.add(new RageEnchantment());
		enchantments.add(new NightmareEnchantment());
		enchantments.add(new EarthquakeEnchantment());
		enchantments.add(new BeheadingEnchantment());
		enchantments.add(new ReinforcedEnchantment());
		enchantments.add(new LongShotEnchantment());
		enchantments.add(new SoulStealerEnchantment());
		enchantments.add(new EmpowermentEnchantment());
		enchantments.add(new BlessingEnchantment());
		enchantments.add(new DemolitionEnchantment());
		enchantments.add(new SoullessEnchantment());
		enchantments.add(new DevilsGraceEnchantment());
		enchantments.add(new ColdBloodedEnchantment());
		enchantments.add(new ForgeEnchantment());
		enchantments.add(new AntiGravityEnchantment());
		enchantments.add(new AlchemistEnchantment());
		enchantments.add(new StaminaEnchantment());
		enchantments.add(new MossEnchantment());
		enchantments.add(new NocturnalEnchantment());
		enchantments.add(new HeatSeekerEnchantment());
		enchantments.add(new SpringEnchantment());
		enchantments.add(new EnderEnchantment());
		enchantments.add(new MagnetEnchantment());
		enchantments.add(new DrillEnchantment());
		enchantments.add(new EssenceEnchantment());
		enchantments.add(new DemolitionExpertEnchantment());
		enchantments.add(new DoubleStrikeEnchantment());
		enchantments.add(new ButcherEnchantment());
		enchantments.add(new IronGraspEnchantment());
		
		EnchantmentAPI.registerCustomEnchantment(new HellsCurseEnchantment());
		
		for(CustomEnchantment i : enchantments)
		{
			EnchantmentAPI.registerCustomEnchantment(i);
			
			for(int j = 0; j < i.getMaxLevel(); j++)
			{
				int level = j + 1;
				getConfiguration().set("enchantment." + i.getClass().getSimpleName().toLowerCase().replaceAll("enchantment", "") + ".level-" + level, "expert");
			}
		}
		
		for(String i : tiers)
		{
			getConfiguration().set("tier.cost." + i, 1024);
		}
		
		loadCluster(this);
		saveCluster(this);
	}
	
	public int getCost(String tier)
	{
		return getConfiguration().getInt("tier.cost." + tier);
	}
	
	public String getTier(Enchant e)
	{
		return getConfiguration().getString("enchantment." + e.getE().getClass().getSimpleName().toLowerCase().replaceAll("enchantment", "") + ".level-" + e.getLevel());
	}
	
	@Command("enchantments")
	@CommandAlias("enchants")
	public void onEnchants(PhantomSender sender, PhantomCommand command)
	{
		if(sender.isPlayer())
		{
			
		}
	}
	
	public void openWindow(Player p)
	{
		Window w = new PhantomWindow(C.LIGHT_PURPLE + "Enchantments", p);
		GList<Slot> slots = Guis.sortLTR(Guis.getCentered(tiers.size(), 2));
		
		for(String i : tiers)
		{
			String name = C.LIGHT_PURPLE + StringUtils.capitalize(i) + " Enchantments";
			Element e = new PhantomElement(Material.ENCHANTED_BOOK, slots.pop(), name)
			{
				@Override
				public void onClick(Player p, Click c, Window w)
				{
					if(c.equals(Click.RIGHT))
					{
						GList<Enchant> exv = new GList<Enchant>();
						
						for(CustomEnchantment k : enchantments)
						{
							for(int j = 0; j < k.getMaxLevel(); j++)
							{
								int level = j + 1;
								Enchant en = new Enchant((Enchanted) k, level);
								
								if(getTier(en).equals(i))
								{
									exv.add(en);
								}
							}
						}
						
						if(exv.isEmpty())
						{
							p.sendMessage(C.RED + "No Enchantments for this Tier");
							
							return;
						}
						
						else
						{
							Window wx = new PhantomWindow(C.LIGHT_PURPLE + name, p);
							
							int ix = 0;
							
							GMap<Enchanted, GList<Integer>> mapper = new GMap<Enchanted, GList<Integer>>();
							
							for(Enchant i : exv)
							{
								if(!mapper.containsKey(i.getE()))
								{
									mapper.put(i.getE(), new GList<Integer>());
								}
								
								mapper.get(i.getE()).add(i.getLevel());
							}
							
							for(Enchanted i : mapper.k())
							{
								Slot s = new Slot(ix);
								ix++;
								
								Element ee = new PhantomElement(Material.ENCHANTED_BOOK, s, C.LIGHT_PURPLE + i.getClass().getSimpleName().replaceAll("Enchantment", ""));
								
								for(int j : mapper.get(i))
								{
									ee.addText(C.GRAY + "Level " + M.toRoman(j));
								}
								
								wx.addElement(ee);
							}
							
							wx.setBackground(new PhantomElement(Material.STAINED_GLASS_PANE, (byte) 15, new Slot(0), " "));
							wx.open();
						}
					}
					
					else
					{
						GList<Enchant> exv = new GList<Enchant>();
						
						for(CustomEnchantment k : enchantments)
						{
							for(int j = 0; j < k.getMaxLevel(); j++)
							{
								int level = j + 1;
								Enchant en = new Enchant((Enchanted) k, level);
								
								if(getTier(en).equals(i))
								{
									exv.add(en);
								}
							}
						}
						
						if(exv.isEmpty())
						{
							p.sendMessage(C.RED + "No Enchantments for this Tier");
							
							return;
						}
						
						Enchant ex = exv.pickRandom();
						PhantomInventory pi = new PhantomInventory(p.getInventory());
						
						int has = (int) new ExperienceCurrency().get(p);
						
						if(has >= getCost(i))
						{
							if(pi.hasSpace())
							{
								ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
								ItemMeta im = book.getItemMeta();
								im.setDisplayName(C.LIGHT_PURPLE + ex.getE().getClass().getSimpleName().replaceAll("Enchantment", "") + " " + M.toRoman(ex.getLevel()));
								book.setItemMeta(im);
								((CustomEnchantment) ex.getE()).addToItem(book, ex.getLevel());
								pi.addItem(book);
								new Transaction(new ExperienceCurrency()).from(p).amount((double) getCost(i)).commit();
							}
							
							else
							{
								p.sendMessage(C.RED + "No inventory space!");
							}
						}
						
						else
						{
							p.sendMessage(C.RED + "Not enough XP!");
						}
					}
				}
			};
			
			e.addText(C.DARK_PURPLE + "Click to select from " + name);
			e.addText(C.GREEN + "Costs " + F.f(getCost(i)) + " XP");
			w.addElement(e);
		}
		
		w.setBackground(new PhantomElement(Material.STAINED_GLASS_PANE, (byte) 15, new Slot(0), " "));
		w.setViewport(3);
		w.open();
	}
	
	public GList<Enchanted> getEnchantments()
	{
		GList<Enchanted> enchanted = new GList<Enchanted>();
		
		for(CustomEnchantment i : enchantments)
		{
			enchanted.add((Enchanted) i);
		}
		
		return enchanted;
	}
	
	@Override
	public void onReadConfig()
	{
		
	}
	
	@Override
	public void onStop()
	{
		
	}
	
	@Override
	public void onTick()
	{
		for(Player i : affected.k())
		{
			affected.put(i, affected.get(i) / 1.145f);
			NMSX.showWeather(i, affected.get(i) > 6f ? 6f : affected.get(i));
			affected.put(i, affected.get(i) > 6f ? 6f : affected.get(i));
			
			if(affected.get(i) < 0.1)
			{
				affected.remove(i);
			}
		}
		
		for(Player i : Phantom.instance().onlinePlayers())
		{
			if(Math.random() < 0.04)
			{
				for(ItemStack j : i.getInventory().getArmorContents())
				{
					if(EnchantmentAPI.itemHasEnchantment(j, "Moss"))
					{
						int level = EnchantmentAPI.getEnchantments(j).get(EnchantmentAPI.getEnchantment("Moss"));
						
						if(M.r(level / 15.0))
						{
							j.setDurability((short) (j.getDurability() - level < 0 ? 0 : j.getDurability() - level));
							
							if(j.getDurability() < 0 || j.getDurability() > j.getType().getMaxDurability())
							{
								j.setType(Material.AIR);
							}
						}
					}
				}
				
				if(EnchantmentAPI.itemHasEnchantment(i.getItemInHand(), "Moss"))
				{
					int level = EnchantmentAPI.getEnchantments(i.getItemInHand()).get(EnchantmentAPI.getEnchantment("Moss"));
					
					if(M.r(level / 15.0))
					{
						i.getItemInHand().setDurability((short) (i.getItemInHand().getDurability() - 1 * level < 0 ? 0 : i.getItemInHand().getDurability() - 1 * level));
						
						if(i.getItemInHand().getDurability() != 0)
						{
							ParticleEffect.VILLAGER_HAPPY.display(5.5f, 4, P.getHand(i), 3);
						}
					}
				}
			}
			
			k++;
			if(k > 20 && i.getInventory().getHelmet() != null && EnchantmentAPI.itemHasEnchantment(i.getInventory().getHelmet(), "Nocturnal"))
			{
				k = 0;
				
				for(ItemStack j : i.getInventory().getArmorContents())
				{
					if(EnchantmentAPI.itemHasEnchantment(j, "Stamina"))
					{
						i.setSaturation(100f);
					}
				}
				
				i.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 500, 0), true);
			}
			
			if(i.getItemInHand() != null && EnchantmentAPI.itemHasEnchantment(i.getItemInHand(), "Rage"))
			{
				int level = EnchantmentAPI.getEnchantments(i.getItemInHand()).get(EnchantmentAPI.getEnchantment("Rage"));
				i.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2, level - 1), true);
			}
			
			for(ItemStack j : i.getInventory().getArmorContents())
			{
				int l = 0;
				
				try
				{
					if(EnchantmentAPI.itemHasEnchantment(j, "Reinforced"))
					{
						int level = EnchantmentAPI.getEnchantments(j).get(EnchantmentAPI.getEnchantment("Rage"));
						
						if(level > l)
						{
							l = level;
						}
					}
					
					if(l > 0)
					{
						i.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2, l - 1), true);
					}
				}
				
				catch(Exception e)
				{
					
				}
			}
			
			for(ItemStack j : i.getInventory().getArmorContents())
			{
				if(EnchantmentAPI.itemHasEnchantment(j, "Stamina"))
				{
					i.setSaturation(100f);
				}
			}
			
			if(i.getInventory().getBoots() != null && EnchantmentAPI.itemHasEnchantment(i.getInventory().getBoots(), "Spring"))
			{
				PE.JUMP.a(1).d(10).c(i);
			}
			
			if(Math.random() < 0.687)
			{
				ItemStack is = i.getItemInHand();
				
				if(is != null)
				{
					for(CustomEnchantment j : EnchantmentAPI.getEnchantments(is).keySet())
					{
						if(j instanceof AmbientEffect)
						{
							if(Math.random() > 0.963)
							{
								((AmbientEffect) j).ambientPlay(i);
							}
						}
					}
				}
			}
		}
		
		if(Math.random() < 0.687)
		{
			if(Math.random() > 0.33)
			{
				for(Location i : enchanted.k())
				{
					if(!i.getBlock().getType().equals(Material.TNT))
					{
						enchanted.remove(i);
						
						continue;
					}
					
					for(String j : enchanted.get(i).k())
					{
						if(Math.random() > 0.46)
						{
							if(j.equalsIgnoreCase("Sticky"))
							{
								ParticleEffect.SLIME.display(1f, 2, i.clone().add(0.5, 1, 0.5), 12);
							}
							
							if(j.equalsIgnoreCase("Inferno"))
							{
								ParticleEffect.LAVA.display(1f, 1, i.clone().add(0.5, 1, 0.5), 12);
							}
							
							if(j.equalsIgnoreCase("Blast"))
							{
								ParticleEffect.ENCHANTMENT_TABLE.display(0.9f, 6, i.clone().add(0.5, 1, 0.5), 12);
							}
							
							if(j.equalsIgnoreCase("Drill"))
							{
								ParticleEffect.SPELL_WITCH.display(0.9f, 6, i.clone().add(0.5, 1, 0.5), 12);
							}
							
							if(j.equalsIgnoreCase("Defusing"))
							{
								ParticleEffect.CRIT_MAGIC.display(0.6f, 2, i.clone().add(0.5, 1, 0.5), 12);
							}
						}
					}
				}
			}
		}
	}
	
	public void affect(Player p)
	{
		affect(p, (float) (Math.random() * 0.4f));
	}
	
	public void affect(Player p, Float f)
	{
		if(!affected.containsKey(p))
		{
			affected.put(p, f);
		}
		
		else
		{
			affected.put(p, affected.get(p) + f);
		}
	}
	
	@PlayerOnly
	@Permission("effex.god")
	@Command("enchantall")
	public void onCommand(PhantomSender sender, PhantomCommand cmd)
	{
		for(CustomEnchantment i : EnchantmentAPI.getAllValidEnchants(sender.getPlayer().getItemInHand()))
		{
			if(i instanceof Enchanted)
			{
				i.addToItem(sender.getPlayer().getItemInHand(), i.getMaxLevel());
				sender.sendMessage(C.GREEN + "+ " + i.getClass().getSimpleName().replaceAll("Enchantment", "") + " " + M.toRoman(i.getMaxLevel()));
			}
		}
	}
	
	@EventHandler
	public void on(ProjectileLaunchEvent e)
	{
		if(e.getEntity().getType().equals(EntityType.ARROW))
		{
			Arrow a = (Arrow) e.getEntity();
			
			if(a.getShooter() instanceof Player && a.isCritical())
			{
				Player p = (Player) a.getShooter();
				ItemStack is = p.getItemInHand();
				
				if(EnchantmentAPI.itemHasEnchantment(is, "Ender") && M.r(0.28))
				{
					e.setCancelled(true);
					ControllerMessage msg = new ControllerMessage(Phantom.instance().getController("CommuneController"));
					msg.set("reset-pearl", p.getName());
					sendMessage("CommuneController", msg);
					p.launchProjectile(EnderPearl.class);
				}
				
				else if(EnchantmentAPI.itemHasEnchantment(is, "Heat Seeker") && M.r(0.38))
				{
					int level = EnchantmentAPI.getEnchantments(is).get(EnchantmentAPI.getEnchantment("Heat Seeker"));
					
					new TaskLater()
					{
						@Override
						public void run()
						{
							new Task(5)
							{
								@Override
								public void run()
								{
									if(a == null || a.isDead() || a.isOnGround())
									{
										cancel();
									}
									
									Area ar = new Area(a.getLocation(), 4 * level);
									Double least = Double.MAX_VALUE;
									LivingEntity target = null;
									
									for(Entity i : ar.getNearbyEntities())
									{
										Double distance = i.getLocation().distanceSquared(a.getLocation());
										
										boolean cold = false;
										
										if(i instanceof Player)
										{
											Player tt = (Player) i;
											for(ItemStack ix : tt.getInventory().getArmorContents())
											{
												if(EnchantmentAPI.itemHasEnchantment(ix, "Cold Blooded"))
												{
													cold = true;
													break;
												}
											}
										}
										
										if(distance < least && !i.equals(p) && i instanceof LivingEntity && !cold)
										{
											least = distance;
											target = (LivingEntity) i;
										}
									}
									
									if(target != null)
									{
										new LineParticleManipulator()
										{
											@Override
											public void play(Location arg0)
											{
												ParticleEffect.FIREWORKS_SPARK.display(0f, 1, arg0, 111);
											}
										}.play(target.getEyeLocation(), a.getLocation(), 11.1);
										a.teleport(target.getEyeLocation());
									}
								}
							};
						}
					};
				}
			}
		}
	}
	
	@EventHandler
	public void on(PlayerMoveBlockEvent e)
	{
		Player p = e.getPlayer();
		
		for(ItemStack i : p.getInventory().getArmorContents())
		{
			if(EnchantmentAPI.itemHasEnchantment(i, "Hells Curse"))
			{
				if(Blocks.canModify(e.getPlayer(), e.getPlayer().getLocation().getBlock()))
				{
					e.getPlayer().setAllowFlight(true);
				}
				
				else
				{
					e.getPlayer().setAllowFlight(false);
				}
				
				if(e.getPlayer().isFlying())
				{
					ParticleEffect.SMOKE_LARGE.display(0.1f, 3, e.getPlayer().getLocation(), 54);
				}
			}
		}
	}
	
	@EventHandler
	public void on(PlayerDeathEvent e)
	{
		if(e.getEntity().getKiller() != null)
		{
			Player p = e.getEntity().getKiller();
			ItemStack is = p.getItemInHand();
			
			if(EnchantmentAPI.itemHasEnchantment(is, "Soul Stealer"))
			{
				GList<String> lore = new GList<String>(is.getItemMeta().getLore());
				GList<String> newLore = new GList<String>();
				Boolean found = false;
				
				for(String i : lore)
				{
					if(C.stripColor(i).startsWith("Kills: "))
					{
						String current = C.stripColor(i).split(": ")[1];
						String next = C.RED + "Kills: " + (Integer.valueOf(current) + 1);
						newLore.add(next);
						found = true;
					}
					
					else
					{
						newLore.add(i);
					}
				}
				
				if(!found)
				{
					newLore.add(C.RED + "Kills: 1");
				}
				
				ItemMeta im = is.getItemMeta();
				im.setLore(newLore);
				is.setItemMeta(im);
			}
		}
		
		if(M.r(0.8))
		{
			for(ItemStack i : new GList<ItemStack>(e.getDrops()))
			{
				if(EnchantmentAPI.itemHasEnchantment(i, "Iron Grasp"))
				{
					e.getDrops().remove(i);
					
					new TaskLater(10)
					{
						@Override
						public void run()
						{
							PhantomInventory pi = new PhantomInventory(e.getEntity().getInventory());
							
							if(pi.hasSpace())
							{
								pi.addItem(i);
							}
							
							else
							{
								e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), i);
							}
						}
					};
				}
			}
		}
	}
	
	@EventHandler
	public void on(EntityExplodeEvent e)
	{
		if(ebs.containsKey(e.getEntity()))
		{
			int level = ebs.get(e.getEntity());
			
			for(Block i : e.blockList())
			{
				if(i.getType().equals(Material.TNT))
				{
					continue;
				}
				
				if(M.r(0.45 + level / 10.0))
				{
					i.breakNaturally(new ItemStack(Material.IRON_PICKAXE));
				}
			}
		}
	}
	
	@EventHandler
	public void on(TNTPrimeEvent e)
	{
		if(enchanted.containsKey(e.getTntBlock().getLocation()))
		{
			if(enchanted.get(e.getTntBlock().getLocation()).containsKey("Sticky"))
			{
				new Task(0)
				{
					@Override
					public void run()
					{
						if(e.getTntEntity().isDead())
						{
							cancel();
							return;
						}
						
						e.getTntEntity().setVelocity(new Vector(0, 0, 0));
						e.getTntEntity().setFallDistance(0f);
						e.getTntEntity().teleport(e.getTntBlock().getLocation());
					}
				};
			}
			
			if(enchanted.get(e.getTntBlock().getLocation()).containsKey("Inferno"))
			{
				e.getTntEntity().setFireTicks(200);
				e.getTntEntity().setIsIncendiary(true);
			}
			
			if(enchanted.get(e.getTntBlock().getLocation()).containsKey("Drill"))
			{
				ebs.put(e.getTntEntity(), enchanted.get(e.getTntBlock().getLocation()).get("Drill"));
			}
			
			if(enchanted.get(e.getTntBlock().getLocation()).containsKey("Blast"))
			{
				e.getTntEntity().setYield(enchanted.get(e.getTntBlock().getLocation()).get("Blast") + 3f);
			}
			
			if(enchanted.get(e.getTntBlock().getLocation()).containsKey("Defusing"))
			{
				e.getTntEntity().setFuseTicks(e.getTntEntity().getFuseTicks() / enchanted.get(e.getTntBlock().getLocation()).get("Defusing"));
			}
			
			enchanted.remove(e.getTntBlock().getLocation());
		}
	}
	
	@EventHandler
	public void on(TNTDispenseEvent e)
	{
		Map<CustomEnchantment, Integer> mapped = EnchantmentAPI.getEnchantments(e.getItem());
		
		if(!mapped.isEmpty())
		{
			if(mapped.containsKey(EnchantmentAPI.getEnchantment("Sticky")))
			{
				new Task(0)
				{
					@Override
					public void run()
					{
						if(e.getTNT().isDead())
						{
							cancel();
							return;
						}
						
						e.getTNT().teleport(e.getTNT().getLocation().add(VectorMath.reverse(e.getTNT().getVelocity())));
						e.getTNT().setVelocity(new Vector(0, 0, 0));
						e.getTNT().setFallDistance(0f);
					}
				};
			}
			
			if(mapped.containsKey(EnchantmentAPI.getEnchantment("Inferno")))
			{
				e.getTNT().setFireTicks(200);
				e.getTNT().setIsIncendiary(true);
			}
			
			if(mapped.containsKey(EnchantmentAPI.getEnchantment("Blast")))
			{
				e.getTNT().setYield(mapped.get(EnchantmentAPI.getEnchantment("Blast")) + 3f);
			}
			
			if(mapped.containsKey(EnchantmentAPI.getEnchantment("Defusing")))
			{
				e.getTNT().setFuseTicks(e.getTNT().getFuseTicks() / mapped.get(EnchantmentAPI.getEnchantment("Defusing")));
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void on(PlayerCommandPreprocessEvent e)
	{
		if(e.getMessage().equalsIgnoreCase("/split"))
		{
			ItemStack is = e.getPlayer().getItemInHand();
			
			if(is.getAmount() > 1)
			{
				e.getPlayer().sendMessage(C.RED + "Please hold only ONE book to split.");
				e.setCancelled(true);
				return;
			}
			
			if(is != null && (is.getType().equals(Material.ENCHANTED_BOOK) || is.getType().equals(Material.BOOK)))
			{
				Map<CustomEnchantment, Integer> map = EnchantmentAPI.getAllEnchantments(is);
				
				if(map.size() > 1)
				{
					e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
					
					for(CustomEnchantment i : map.keySet())
					{
						ItemStack isx = new ItemStack(Material.ENCHANTED_BOOK);
						i.addToItem(isx, map.get(i));
						
						if(new PhantomInventory(e.getPlayer().getInventory()).hasSpace())
						{
							e.getPlayer().getInventory().addItem(isx);
						}
						
						else
						{
							e.getPlayer().getWorld().dropItem(e.getPlayer().getLocation(), isx);
						}
					}
					
					e.getPlayer().sendMessage(C.GREEN + "Split book into " + map.size() + " sub-books.");
					e.setCancelled(true);
					return;
				}
			}
			
			e.setCancelled(true);
			e.getPlayer().sendMessage(C.RED + "Must be a Book with multiple enchantments on it.");
		}
	}
	
	@EventHandler
	public void on(BlockPlaceEvent e)
	{
		if(e.getBlock().getType().equals(Material.TNT))
		{
			GMap<String, Integer> mapped = new GMap<String, Integer>();
			
			if(EnchantmentAPI.itemHasEnchantment(e.getItemInHand(), "Sticky"))
			{
				int level = EnchantmentAPI.getEnchantments(e.getItemInHand()).get(EnchantmentAPI.getEnchantment("Sticky"));
				mapped.put("Sticky", level);
			}
			
			if(EnchantmentAPI.itemHasEnchantment(e.getItemInHand(), "Inferno"))
			{
				int level = EnchantmentAPI.getEnchantments(e.getItemInHand()).get(EnchantmentAPI.getEnchantment("Inferno"));
				mapped.put("Inferno", level);
			}
			
			if(EnchantmentAPI.itemHasEnchantment(e.getItemInHand(), "Blast"))
			{
				int level = EnchantmentAPI.getEnchantments(e.getItemInHand()).get(EnchantmentAPI.getEnchantment("Blast"));
				mapped.put("Blast", level);
			}
			
			if(EnchantmentAPI.itemHasEnchantment(e.getItemInHand(), "Drill"))
			{
				int level = EnchantmentAPI.getEnchantments(e.getItemInHand()).get(EnchantmentAPI.getEnchantment("Drill"));
				mapped.put("Drill", level);
			}
			
			if(EnchantmentAPI.itemHasEnchantment(e.getItemInHand(), "Defusing"))
			{
				int level = EnchantmentAPI.getEnchantments(e.getItemInHand()).get(EnchantmentAPI.getEnchantment("Defusing"));
				mapped.put("Defusing", level);
			}
			
			if(!mapped.isEmpty())
			{
				enchanted.put(e.getBlock().getLocation(), mapped);
			}
		}
	}
	
	@EventHandler
	public void on(EntityDamageEvent e)
	{
		if(e.getCause().equals(DamageCause.BLOCK_EXPLOSION) || e.getCause().equals(DamageCause.ENTITY_EXPLOSION))
		{
			if(e.getEntity().getType().equals(EntityType.PLAYER))
			{
				Player p = (Player) e.getEntity();
				
				for(ItemStack i : p.getInventory().getArmorContents())
				{
					if(EnchantmentAPI.itemHasEnchantment(i, "Demolition Expert"))
					{
						e.setCancelled(true);
						e.setDamage(0);
						return;
					}
				}
			}
		}
		
		if(e.getCause().equals(DamageCause.FALL))
		{
			if(e.getEntity().getType().equals(EntityType.PLAYER))
			{
				Player p = (Player) e.getEntity();
				
				for(ItemStack i : p.getInventory().getArmorContents())
				{
					if(EnchantmentAPI.itemHasEnchantment(i, "Anti Gravity"))
					{
						e.setCancelled(true);
						e.setDamage(0);
						return;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void on(PlayerItemConsumeEvent e)
	{
		if(e.getItem().getType().equals(Material.POTION))
		{
			Player p = e.getPlayer();
			
			for(ItemStack i : p.getInventory().getArmorContents())
			{
				if(EnchantmentAPI.itemHasEnchantment(i, "Alchemist"))
				{
					int level = EnchantmentAPI.getEnchantments(i).get(EnchantmentAPI.getEnchantment("Alchemist"));
					int bottles = W.count(e.getPlayer(), new MaterialBlock(Material.GLASS_BOTTLE));
					
					if(M.r(0.38 + level / 15.0))
					{
						ItemStack is = e.getItem().clone();
						p.sendMessage("** Potion Saved **");
						
						new TaskLater()
						{
							@Override
							public void run()
							{
								p.setItemInHand(is);
								
								if(W.count(e.getPlayer(), new MaterialBlock(Material.GLASS_BOTTLE)) > bottles)
								{
									W.take(e.getPlayer(), new MaterialBlock(Material.GLASS_BOTTLE), W.count(e.getPlayer(), new MaterialBlock(Material.GLASS_BOTTLE)) - bottles);
								}
							}
						};
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void on(BlockBreakEvent e)
	{
		if(!Blocks.canModify(e.getPlayer(), e.getBlock()))
		{
			return;
		}
		
		if(e.getBlock().getType().equals(Material.TNT))
		{
			if(enchanted.containsKey(e.getBlock().getLocation()))
			{
				e.getBlock().setType(Material.AIR);
				e.setCancelled(true);
				ItemStack ntnt = new ItemStack(Material.TNT);
				
				for(String i : enchanted.get(e.getBlock().getLocation()).k())
				{
					int level = enchanted.get(e.getBlock().getLocation()).get(i);
					
					EnchantmentAPI.getEnchantment(i).addToItem(ntnt, level);
				}
				
				if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
				{
					e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), ntnt);
				}
				
				enchanted.remove(e.getBlock().getLocation());
			}
		}
		
		if(e.getPlayer().getInventory().getBoots() != null && EnchantmentAPI.itemHasEnchantment(e.getPlayer().getInventory().getBoots(), "Magnetic"))
		{
			if(!Blocks.canModify(e.getPlayer(), e.getBlock()))
			{
				return;
			}
			
			if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
			{
				if(e.getBlock().getType().equals(Material.MOB_SPAWNER))
				{
					return;
				}
				
				if(e.getPlayer().getItemInHand() != null && EnchantmentAPI.itemHasEnchantment(e.getPlayer().getItemInHand(), "Forge"))
				{
					boolean part = false;
					
					if(e.getBlock().getType().equals(Material.IRON_ORE))
					{
						part = true;
						
						if(new PhantomInventory(e.getPlayer().getInventory()).hasSpace())
						{
							e.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT));
						}
						
						else
						{
							e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
						}
					}
					
					if(e.getBlock().getType().equals(Material.GOLD_ORE))
					{
						part = true;
						
						if(new PhantomInventory(e.getPlayer().getInventory()).hasSpace())
						{
							e.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT));
						}
						
						else
						{
							e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
						}
					}
					
					if(part)
					{
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						ParticleEffect.LAVA.display(0.2f, 4, e.getBlock().getLocation(), 24);
						new GSound(Sound.FIZZ, 0.4f, 1f).play(e.getPlayer().getLocation());
						return;
					}
				}
				
				Collection<ItemStack> is = e.getBlock().getDrops(e.getPlayer().getItemInHand());
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				
				new TaskLater()
				{
					@Override
					public void run()
					{
						for(ItemStack i : is)
						{
							if(i.getType().equals(Material.HOPPER))
							{
								continue;
							}
							
							if(new PhantomInventory(e.getPlayer().getInventory()).hasSpace())
							{
								e.getPlayer().getInventory().addItem(i);
							}
							
							else
							{
								e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), i);
							}
						}
					}
				};
			}
		}
		
		if(e.getPlayer().getItemInHand() != null && EnchantmentAPI.itemHasEnchantment(e.getPlayer().getItemInHand(), "Essence"))
		{
			if(!Blocks.canModify(e.getPlayer(), e.getBlock()))
			{
				return;
			}
			
			if(e.getExpToDrop() > 0)
			{
				new TaskLater()
				{
					@Override
					public void run()
					{
						int enchantLevel = EnchantmentAPI.getEnchantments(e.getPlayer().getItemInHand()).get(EnchantmentAPI.getEnchantment("Essence"));
						
						for(int i = 0; i < enchantLevel; i++)
						{
							new Transaction(new ExperienceCurrency()).to(e.getPlayer()).amount(1 + 18 * Math.random()).noDiff().commit();
						}
					}
				};
			}
		}
		
		if(e.getPlayer().getItemInHand() != null && EnchantmentAPI.itemHasEnchantment(e.getPlayer().getItemInHand(), "Forge"))
		{
			boolean part = false;
			
			if(e.getBlock().getType().equals(Material.IRON_ORE))
			{
				part = true;
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
			}
			
			if(e.getBlock().getType().equals(Material.GOLD_ORE))
			{
				part = true;
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
			}
			
			if(part)
			{
				ParticleEffect.LAVA.display(0.2f, 4, e.getBlock().getLocation(), 24);
				new GSound(Sound.FIZZ, 0.4f, 1f).play(e.getPlayer().getLocation());
				e.getPlayer().getItemInHand().setDurability((short) (e.getPlayer().getItemInHand().getDurability() + 3));
				
				if(e.getPlayer().getItemInHand().getDurability() < 0 || e.getPlayer().getItemInHand().getDurability() > e.getPlayer().getItemInHand().getType().getMaxDurability())
				{
					e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
				}
			}
		}
		
		if(e.getPlayer().getItemInHand() != null && EnchantmentAPI.itemHasEnchantment(e.getPlayer().getItemInHand(), "Blast") && e.getPlayer().getItemInHand().getType().toString().endsWith("_PICKAXE"))
		{
			int level = EnchantmentAPI.getEnchantments(e.getPlayer().getItemInHand()).get(EnchantmentAPI.getEnchantment("Blast"));
			GList<Block> breaks = new GList<Block>();
			breaks.add(e.getBlock().getRelative(BlockFace.UP));
			breaks.add(e.getBlock().getRelative(BlockFace.DOWN));
			
			if(level > 1)
			{
				breaks.add(e.getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.NORTH));
				breaks.add(e.getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH));
				breaks.add(e.getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.EAST));
				breaks.add(e.getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.WEST));
				breaks.add(e.getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH));
				breaks.add(e.getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH));
				breaks.add(e.getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST));
				breaks.add(e.getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST));
				breaks.add(e.getBlock().getRelative(BlockFace.NORTH));
				breaks.add(e.getBlock().getRelative(BlockFace.SOUTH));
				breaks.add(e.getBlock().getRelative(BlockFace.EAST));
				breaks.add(e.getBlock().getRelative(BlockFace.WEST));
				breaks.add(e.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.UP));
				breaks.add(e.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.DOWN));
				breaks.add(e.getBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.UP));
				breaks.add(e.getBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.DOWN));
				breaks.add(e.getBlock().getRelative(BlockFace.EAST).getRelative(BlockFace.UP));
				breaks.add(e.getBlock().getRelative(BlockFace.EAST).getRelative(BlockFace.DOWN));
				breaks.add(e.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.UP));
				breaks.add(e.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.DOWN));
				breaks.add(e.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).getRelative(BlockFace.UP));
				breaks.add(e.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).getRelative(BlockFace.DOWN));
				breaks.add(e.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).getRelative(BlockFace.UP));
				breaks.add(e.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).getRelative(BlockFace.DOWN));
				breaks.add(e.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getRelative(BlockFace.UP));
				breaks.add(e.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getRelative(BlockFace.DOWN));
				breaks.add(e.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).getRelative(BlockFace.UP));
				breaks.add(e.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).getRelative(BlockFace.DOWN));
				breaks.add(e.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST));
				breaks.add(e.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST));
				breaks.add(e.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST));
				breaks.add(e.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST));
				breaks.shuffle();
			}
			
			FinalInteger bxx = new FinalInteger(0);
			breaks.removeDuplicates();
			breaks.pickRandom();
			
			new TaskLater()
			{
				@Override
				public void run()
				{
					if(e.getPlayer().getItemInHand().getDurability() < 0 || e.getPlayer().getItemInHand().getDurability() > e.getPlayer().getItemInHand().getType().getMaxDurability())
					{
						e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
					}
					
					new Task(6 - level)
					{
						@Override
						public void run()
						{
							for(int k = 0; k < 5; k++)
							{
								if(breaks.isEmpty())
								{
									cancel();
									return;
								}
								
								Block b = breaks.pop();
								
								for(Multiblock i : Phantom.instance().getMultiblockRegistryController().getMultiblocks())
								{
									if(i.contains(b.getLocation()))
									{
										continue;
									}
								}
								
								if(!b.getType().toString().endsWith("STONE") && !b.getType().toString().endsWith("_ORE") && !b.getType().toString().startsWith("NETHER"))
								{
									continue;
								}
								
								if(b.getType().equals(Material.BEDROCK))
								{
									continue;
								}
								
								if(b.getType().equals(Material.MOB_SPAWNER))
								{
									continue;
								}
								
								if(!Blocks.canModify(e.getPlayer(), b))
								{
									continue;
								}
								
								if(e.isCancelled())
								{
									continue;
								}
								
								ItemStack cust = null;
								callEvent(new FalseBlockBreakEvent(b, e.getPlayer()));
								
								try
								{
									if((b.getType().equals(Material.IRON_ORE) || b.getType().equals(Material.GOLD_ORE)) && EnchantmentAPI.itemHasEnchantment(e.getPlayer().getItemInHand(), "Forge"))
									{
										if(b.getType().equals(Material.IRON_ORE))
										{
											cust = new ItemStack(Material.IRON_INGOT);
										}
										
										if(b.getType().equals(Material.GOLD_ORE))
										{
											cust = new ItemStack(Material.GOLD_INGOT);
										}
										
										ParticleEffect.LAVA.display(0.2f, 4, b.getLocation(), 24);
										new GSound(Sound.FIZZ, 0.4f, 1f).play(b.getLocation());
									}
								}
								
								catch(Exception e)
								{
									
								}
								
								GList<ItemStack> drops = new GList<ItemStack>();
								
								if(cust != null)
								{
									NMSX.breakParticles(b.getLocation().add(0.5, 0.5, 0.5), b.getType(), 12);
									new GSound(Sound.DIG_STONE, 1f, 1f).play(b.getLocation());
									b.setType(Material.AIR);
									drops.add(cust);
								}
								
								else
								{
									NMSX.breakParticles(b.getLocation().add(0.5, 0.5, 0.5), b.getType(), 12);
									new GSound(Sound.DIG_STONE, 1f, 1f).play(b.getLocation());
									drops = new GList<ItemStack>(b.getDrops(e.getPlayer().getItemInHand()));
									b.setType(Material.AIR);
								}
								
								ItemStack boots = e.getPlayer().getInventory().getBoots();
								
								if(boots != null && EnchantmentAPI.itemHasEnchantment(boots, "Magnetic"))
								{
									for(ItemStack l : drops)
									{
										if(new PhantomInventory(e.getPlayer().getInventory()).hasSpace())
										{
											e.getPlayer().getInventory().addItem(l);
										}
										
										else
										{
											b.getLocation().getWorld().dropItem(b.getLocation(), l);
										}
									}
								}
								
								else
								{
									for(ItemStack l : drops)
									{
										b.getLocation().getWorld().dropItem(b.getLocation(), l);
									}
								}
								
								if(e.getPlayer().getItemInHand() != null && EnchantmentAPI.itemHasEnchantment(e.getPlayer().getItemInHand(), "Essence"))
								{
									if(e.getExpToDrop() > 0)
									{
										new TaskLater()
										{
											@Override
											public void run()
											{
												int enchantLevel = EnchantmentAPI.getEnchantments(e.getPlayer().getItemInHand()).get(EnchantmentAPI.getEnchantment("Essence"));
												
												for(int i = 0; i < enchantLevel; i++)
												{
													new Transaction(new ExperienceCurrency()).to(e.getPlayer()).amount(1 + 18 * Math.random()).noDiff().commit();
												}
											}
										};
									}
								}
								
								bxx.add(1);
							}
						}
					};
				}
			};
		}
	}
	
	@EventHandler
	public void on(EntityDeathEvent e)
	{
		if(e.getEntity() instanceof Player)
		{
			
		}
		
		else
		{
			Player p = e.getEntity().getKiller();
			
			if(p != null)
			{
				ItemStack is = p.getItemInHand();
				
				if(is != null && EnchantmentAPI.itemHasEnchantment(is, "Butcher"))
				{
					if(e.getEntity().getType().equals(EntityType.HORSE))
					{
						return;
					}
					
					int level = EnchantmentAPI.getEnchantments(is).get(EnchantmentAPI.getEnchantment("Butcher"));
					
					if(M.r(0.2 * level))
					{
						for(ItemStack i : new GList<ItemStack>(e.getDrops()))
						{
							e.getDrops().remove(i);
							ItemStack isx = i.clone();
							isx.setAmount(i.getAmount() * 2);
							e.getDrops().add(isx);
							ParticleEffect.CLOUD.display(0.1f, 10, e.getEntity().getLocation(), 32);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void on(PlayerKillPlayerEvent e)
	{
		Player target = e.getPlayer();
		Player user = e.getDamager();
		ItemStack is = user.getItemInHand();
		
		if(is != null && EnchantmentAPI.itemHasEnchantment(is, "Beheading"))
		{
			int enchantLevel = EnchantmentAPI.getEnchantments(is).get(EnchantmentAPI.getEnchantment("Beheading"));
			
			if(Math.random() > 0.565 - enchantLevel / 5)
			{
				Location a = P.getHand(user);
				Vector dir = VectorMath.direction(a, target.getLocation().add(0, 1.5, 0));
				Location b = target.getEyeLocation();
				
				new VampiricEffect(1.6f * enchantLevel).play(a, dir, b.add(0, 1.5, 0));
				Location s = target.getLocation().clone();
				s.setY(0);
				new GSound(Sound.SLIME_ATTACK, 2.0f, 1.8f).play(target.getLocation());
				new GSound(Sound.SLIME_ATTACK, 2.0f, 1.0f).play(target.getLocation());
				new GSound(Sound.SLIME_ATTACK, 2.0f, 0.2f).play(target.getLocation());
				new GSound(Sound.SLIME_ATTACK, 2.0f, 0.5f).play(target.getLocation());
				
				String pl = target.getName();
				Date date = new Date();
				@SuppressWarnings("deprecation")
				String dd = date.getMonth() + 1 + " / " + date.getDate() + " / " + (date.getYear() + 1900);
				ItemStack skull = new ItemStack(Material.SKULL_ITEM);
				skull.setDurability((short) 3);
				SkullMeta sm = (SkullMeta) skull.getItemMeta();
				sm.setOwner(pl);
				sm.setDisplayName(ChatColor.DARK_RED + "" + pl);
				sm.setLore(new GList<String>().qadd(C.YELLOW + "Killed by " + user.getName() + " on " + dd));
				skull.setItemMeta(sm);
				target.getLocation().getWorld().dropItem(target.getLocation(), skull);
			}
		}
	}
	
	@EventHandler
	public void on(EntityDamageByEntityEvent e)
	{
		if(e.getDamager() instanceof Arrow)
		{
			if(e.getEntity() instanceof Player)
			{
				Player p = (Player) e.getEntity();
				
				if(p.isBlocking())
				{
					try
					{
						ItemStack is = p.getItemInHand();
						
						if(is != null)
						{
							if(EnchantmentAPI.getEnchantments(is).containsKey(EnchantmentAPI.getEnchantment("Deflection")))
							{
								int level = EnchantmentAPI.getEnchantments(is).get(EnchantmentAPI.getEnchantment("Deflection"));
								
								if(Math.random() > 0.999 - level / 4.0)
								{
									e.setCancelled(true);
									NMSX.breakParticles(e.getDamager().getLocation(), Material.WOOD, 8);
									NMSX.breakParticles(e.getDamager().getLocation(), Material.STONE, 8);
									new GSound(Sound.ITEM_BREAK, 1f, 1.7f).play(e.getEntity().getLocation());
									e.getDamager().remove();
								}
							}
						}
					}
					
					catch(Exception ex)
					{
						
					}
				}
			}
		}
	}
	
	public void sendDrop(int level, Player p)
	{
		ControllerMessage cm = new ControllerMessage(this);
		cm.set("e.player", p.getName());
		cm.set("e.level", level);
		sendMessage("SkillEnchanting", cm);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void on(InventoryClickEvent e)
	{
		ItemStack drop = e.getCursor();
		ItemStack targ = e.getCurrentItem();
		
		int count = 0;
		
		if(e.getWhoClicked() instanceof Player && e.getClickedInventory() != null)
		{
			Player p = (Player) e.getWhoClicked();
			
			if(e.getClickedInventory().getType().equals(p.getInventory().getType()) && e.getClickedInventory().equals(p.getInventory()))
			{
				if(targ != null && drop != null)
				{
					if(drop.getType().equals(Material.ENCHANTED_BOOK) && (targ.getType().equals(Material.FISHING_ROD) || targ.getType().toString().contains("SWORD") || targ.getType().toString().contains("HOE") || targ.getType().toString().contains("SPADE") || targ.getType().toString().contains("AXE") || targ.getType().toString().contains("SHEAR") || targ.getType().toString().contains("BOOT") || targ.getType().toString().contains("LEGG") || targ.getType().toString().contains("CHESTPL") || targ.getType().toString().contains("HELMET") || targ.getType().toString().equals("TNT") || targ.getType().equals(Material.BOW)))
					{
						count = EnchantmentAPI.getEnchantments(targ).size() + targ.getEnchantments().size();
						
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta) drop.getItemMeta();
						
						for(CustomEnchantment i : new GList<CustomEnchantment>(EnchantmentAPI.getEnchantments(drop).keySet()))
						{
							if(count < maxEnchantments)
							{
								i.removeFromItem(targ);
								i.addToItem(targ, EnchantmentAPI.getEnchantments(drop).get(i));
								sendDrop(EnchantmentAPI.getEnchantments(drop).get(i), (Player) e.getWhoClicked());
							}
							
							else
							{
								return;
							}
						}
						
						Stack t = new Stack(targ);
						
						if(count < maxEnchantments)
						{
							for(Enchantment i : meta.getStoredEnchants().keySet())
							{
								t.getEnchantmentSet().addEnchantment(i, meta.getStoredEnchants().get(i));
								sendDrop(meta.getStoredEnchants().get(i), (Player) e.getWhoClicked());
							}
							
							e.getClickedInventory().setItem(e.getSlot(), t.toItemStack());
							
							e.setCursor(null);
							e.setCancelled(true);
						}
						
						else
						{
							return;
						}
					}
				}
				
				if(targ != null && drop != null)
				{
					if(drop.getType().equals(Material.BOOK) && (targ.getType().equals(Material.FISHING_ROD) || targ.getType().toString().contains("SWORD") || targ.getType().toString().contains("HOE") || targ.getType().toString().contains("SPADE") || targ.getType().toString().contains("AXE") || targ.getType().toString().contains("SHEAR") || targ.getType().toString().contains("BOOT") || targ.getType().toString().contains("LEGG") || targ.getType().toString().contains("CHESTPL") || targ.getType().toString().contains("HELMET") || targ.getType().toString().equals("TNT") || targ.getType().equals(Material.BOW)))
					{
						count = EnchantmentAPI.getEnchantments(targ).size() + targ.getEnchantments().size();
						
						for(CustomEnchantment i : new GList<CustomEnchantment>(EnchantmentAPI.getEnchantments(drop).keySet()))
						{
							if(count < maxEnchantments)
							{
								s(i.getClass().getSimpleName());
								
								i.removeFromItem(targ);
								i.addToItem(targ, EnchantmentAPI.getEnchantments(drop).get(i));
							}
							
							else
							{
								return;
							}
						}
						
						e.setCursor(null);
						e.setCancelled(true);
					}
				}
			}
		}
	}
}
