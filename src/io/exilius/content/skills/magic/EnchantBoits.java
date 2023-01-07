package io.exilius.content.skills.magic;

import io.exilius.model.entity.player.Player;
import io.exilius.util.Misc;

/**
 *
 * @author Sgsrocks 7/6/2020
 *
 */
public class EnchantBoits {

    public static void OpenEnchantBoits(Player c) {
        c.getPA().showInterface(25553);
        UpdateItems(c);
    }
    public static void UpdateItems(Player c) {
        int amountCosmic = c.getItems().getItemCount(564, false);
        int amountAir = c.getItems().getItemCount(556, false);
        int amountEarth = c.getItems().getItemCount(557, false);
        int amountNat = c.getItems().getItemCount(561, false);
        int amountFire = c.getItems().getItemCount(554, false);
        int amountLaw = c.getItems().getItemCount(563, false);
        int amountBlood = c.getItems().getItemCount(565, false);
        int amountWater = c.getItems().getItemCount(555, false);
        int amountSoul = c.getItems().getItemCount(566, false);
        int amountMind = c.getItems().getItemCount(558, false);
        int amountDeath = c.getItems().getItemCount(560, false);
        c.getPA().itemOnInterface(25664,150, 9354);//Sapphire
        c.getPA().itemOnInterface(25651, 150, 9346);//Jade
        c.getPA().itemOnInterface(25656, 150, 3822);//Pearl
        c.getPA().itemOnInterface(25668, 150, 9358);//Emerald
        c.getPA().itemOnInterface(25660, 150, 9350);//Red Topaz
        c.getPA().itemOnInterface(26572, 150, 9362);//Ruby
        c.getPA().itemOnInterface(25676, 150, 9366);//Diamond
        c.getPA().itemOnInterface(25680, 150, 9370);//Dragonstone
        c.getPA().itemOnInterface(25684, 150, 9374);//Onyx
        if(amountCosmic >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountCosmic)+"/1", 25686);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25686);
        }
        if(amountAir >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountAir)+"/1", 25707);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25707);
        }
        if(amountCosmic >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountCosmic)+"/1", 25694);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25694);
        }
        if(amountWater >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountWater)+"/1", 25719);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25719);
        }
        if(amountMind >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountMind)+"/1", 25717);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25717);
        }
        if(amountCosmic >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountCosmic)+"/1", 25688);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25688);
        }
        if(amountEarth >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountEarth)+"/1", 25711);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25711);
        }
        if(amountCosmic >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountCosmic)+"/1", 25690);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25690);
        }
        if(amountWater >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountWater)+"/1", 25713);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25713);
        }
        if(amountCosmic >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountCosmic)+"/1", 25696);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25696);
        }
        if(amountAir >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountAir)+"/1", 25725);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25725);
        }
        if(amountNat >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountNat)+"/1", 25733);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25733);
        }
        if(amountCosmic >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountCosmic)+"/1", 25692);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25692);
        }
        if(amountFire >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountFire)+"/1", 25715);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25715);
        }
        if(amountCosmic >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountCosmic)+"/1", 25698);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25698);
        }
        if(amountFire >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountFire)+"/1", 25723);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25723);
        }
        if(amountBlood >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountBlood)+"/1", 25721);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25721);
        }
        if(amountCosmic >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountCosmic)+"/1", 25700);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25700);
        }
        if(amountEarth >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountEarth)+"/1", 25746);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25746);
        }
        if(amountLaw >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountLaw)+"/1", 25727);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25727);
        }
        if(amountCosmic >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountCosmic)+"/1", 25702);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25702);
        }
        if(amountEarth >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountEarth)+"/1", 25709);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25709);
        }
        if(amountSoul >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountSoul)+"/1", 25731);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25731);
        }
        if(amountCosmic >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountCosmic)+"/1", 25704);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25704);
        }
        if(amountFire >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountFire)+"/1", 25748);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25748);
        }
        if(amountDeath >= 1) {
            c.getPA().sendFrame126(" @gre@"+Misc.getValueWithoutRepresentation(amountDeath)+"/1", 25729);
        } else {
            c.getPA().sendFrame126(" @dre@0/1", 25729);
        }
    }

    public static void EnchantButton(Player c , int Button) {

        switch(Button) {
            case 100142:
                c.getPA().closeAllWindows();
                break;
            case 75007:
                if(c.playerLevel[c.playerMagic] >= 4) {
                    OpenEnchantBoits(c);
                } else {
                    c.sendMessage("You need a Magic Level of 4 to Enchant bolts.");
                }
                break;
            case 100047://Opal bolts
                if(c.playerLevel[c.playerMagic] >= 4) {
                    if(!c.getItems().hasFreeSlots(1)) {
                        c.sendMessage("You need 1 Inv space.");
                        return;
                    }
                    if(c.getItems().playerHasItem(564, 1) && c.getItems().playerHasItem(556, 2)) {
                        if(c.getItems().playerHasItem(879)) {
                            c.getItems().deleteItem2(564, 1);
                            c.getItems().deleteItem2(556, 2);
                            c.getItems().deleteItem2(879, 10);
                            c.getItems().addItem(9236, 10);
                            c.startAnimation(4462);
                            c.getPA().closeAllWindows();
                            UpdateItems(c);
                            c.getPA().addSkillXPMultiplied(9, c.playerMagic, true);
                        }else {
                            c.sendMessage("You need Opal bolts.");
                        }
                    } else {
                        c.sendMessage("You don't have the runes for this spell.");
                    }
                } else {
                    c.sendMessage("You need a Magic Level of 4 to Enchant this.");
                }
                break;
            case 100065://Sapphire bolts
                if(c.playerLevel[c.playerMagic] >= 7) {
                    if(!c.getItems().hasFreeSlots(1)) {
                        c.sendMessage("You need 1 Inv space.");
                        return;
                    }
                    if(c.getItems().playerHasItem(564, 1) && c.getItems().playerHasItem(555, 1) && c.getItems().playerHasItem(558, 1)) {
                        if(c.getItems().playerHasItem(9337)) {
                            c.getItems().deleteItem2(564, 1);
                            c.getItems().deleteItem2(555, 1);
                            c.getItems().deleteItem2(558, 1);
                            c.getItems().deleteItem2(9337, 10);
                            c.getItems().addItem(9240, 10);
                            c.startAnimation(4462);
                            c.getPA().closeAllWindows();
                            UpdateItems(c);
                            c.getPA().addSkillXPMultiplied(17, c.playerMagic, true);
                        }else {
                            c.sendMessage("You need Sapphire bolts.");
                        }
                    } else {
                        c.sendMessage("You don't have the runes for this spell.");
                    }
                } else {
                    c.sendMessage("You need a Magic Level of 7 to Enchant this.");
                }
                break;
            case 100052://Jade bolts
                if(c.playerLevel[c.playerMagic] >= 14) {
                    if(!c.getItems().hasFreeSlots(1)) {
                        c.sendMessage("You need 1 Inv space.");
                        return;
                    }
                    if(c.getItems().playerHasItem(564, 1) &&c.getItems().playerHasItem(557, 2)) {
                        if(c.getItems().playerHasItem(9335)) {
                            c.getItems().deleteItem2(564, 1);
                            c.getItems().deleteItem2(557, 2);
                            c.getItems().deleteItem2(9335, 10);
                            c.getItems().addItem(9237, 10);
                            c.startAnimation(4462);
                            c.getPA().closeAllWindows();
                            UpdateItems(c);
                            c.getPA().addSkillXPMultiplied(19, c.playerMagic, true);
                        }else {
                            c.sendMessage("You need Jade bolts.");
                        }
                    } else {
                        c.sendMessage("You don't have the runes for this spell.");
                    }
                } else {
                    c.sendMessage("You need a Magic Level of 14 to Enchant this.");
                }
                break;
            case 100057:// Pearl bolts
                if(c.playerLevel[c.playerMagic] >= 24) {
                    if(!c.getItems().hasFreeSlots(1)) {
                        c.sendMessage("You need 1 Inv space.");
                        return;
                    }
                    if(c.getItems().playerHasItem(564, 1) &&c.getItems().playerHasItem(555, 2)) {
                        if(c.getItems().playerHasItem(880)) {
                            c.getItems().deleteItem2(564, 1);
                            c.getItems().deleteItem2(555, 2);
                            c.getItems().deleteItem2(880, 10);
                            c.getItems().addItem(9238, 10);
                            c.startAnimation(4462);
                            c.getPA().closeAllWindows();
                            UpdateItems(c);
                            c.getPA().addSkillXPMultiplied(29, c.playerMagic, true);
                        }else {
                            c.sendMessage("You need Pearl bolts.");
                        }
                    } else {
                        c.sendMessage("You don't have the runes for this spell.");
                    }
                } else {
                    c.sendMessage("You need a Magic Level of 24 to Enchant this.");
                }
                break;
            case 100069:// Emerald bolts
                if(c.playerLevel[c.playerMagic] >= 27) {
                    if(!c.getItems().hasFreeSlots(1)) {
                        c.sendMessage("You need 1 Inv space.");
                        return;
                    }
                    if(c.getItems().playerHasItem(564, 1) && c.getItems().playerHasItem(556, 3) && c.getItems().playerHasItem(561, 1)) {
                        if(c.getItems().playerHasItem(9338)) {
                            c.getItems().deleteItem2(564, 1);
                            c.getItems().deleteItem2(556, 3);
                            c.getItems().deleteItem2(561, 1);
                            c.getItems().deleteItem2(9338, 10);
                            c.getItems().addItem(9241, 10);
                            c.startAnimation(4462);
                            c.getPA().closeAllWindows();
                            UpdateItems(c);
                            c.getPA().addSkillXPMultiplied(37, c.playerMagic, true);
                        }else {
                            c.sendMessage("You need Emerald bolts.");
                        }
                    } else {
                        c.sendMessage("You don't have the runes for this spell.");
                    }
                } else {
                    c.sendMessage("You need a Magic Level of 27 to Enchant this.");
                }
                break;
            case 100061:// Red Topaz bolts
                if(c.playerLevel[c.playerMagic] >= 29) {
                    if(!c.getItems().hasFreeSlots(1)) {
                        c.sendMessage("You need 1 Inv space.");
                        return;
                    }
                    if(c.getItems().playerHasItem(564, 1) && c.getItems().playerHasItem(554, 2)) {
                        if(c.getItems().playerHasItem(9336)) {
                            c.getItems().deleteItem2(564, 1);
                            c.getItems().deleteItem2(554, 2);
                            c.getItems().deleteItem2(9336, 10);
                            c.getItems().addItem(9239, 10);
                            c.startAnimation(4462);
                            c.getPA().closeAllWindows();
                            UpdateItems(c);
                            c.getPA().addSkillXPMultiplied(33, c.playerMagic, true);
                        }else {
                            c.sendMessage("You need Red Topaz bolts.");
                        }
                    } else {
                        c.sendMessage("You don't have the runes for this spell.");
                    }
                } else {
                    c.sendMessage("You need a Magic Level of 29 to Enchant this.");
                }
                break;
            case 100073:// Ruby bolts
                if(c.playerLevel[c.playerMagic] >= 49) {
                    if(!c.getItems().hasFreeSlots(1)) {
                        c.sendMessage("You need 1 Inv space.");
                        return;
                    }
                    if(c.getItems().playerHasItem(564, 1) && c.getItems().playerHasItem(554, 5) && c.getItems().playerHasItem(565, 1)) {
                        if(c.getItems().playerHasItem(9339)) {
                            c.getItems().deleteItem2(564, 1);
                            c.getItems().deleteItem2(554, 5);
                            c.getItems().deleteItem2(565, 1);
                            c.getItems().deleteItem2(9339, 10);
                            c.getItems().addItem(9242, 10);
                            c.startAnimation(4462);
                            c.getPA().closeAllWindows();
                            UpdateItems(c);
                            c.getPA().addSkillXPMultiplied(59, c.playerMagic, true);
                        }else {
                            c.sendMessage("You need Ruby bolts.");
                        }
                    } else {
                        c.sendMessage("You dont have the runes for this spell,");
                    }
                } else {
                    c.sendMessage("You need a Magic Level of 49 to Enchant this.");
                }
                break;
            case 100077:// Diamond bolts
                if(c.playerLevel[c.playerMagic] >= 57) {
                    if(c.getItems().playerHasItem(564, 1) && c.getItems().playerHasItem(557, 10) && c.getItems().playerHasItem(563, 2)) {
                        if(c.getItems().playerHasItem(9340)) {
                            c.getItems().deleteItem2(564, 1);
                            c.getItems().deleteItem2(557, 10);
                            c.getItems().deleteItem2(563, 2);
                            c.getItems().deleteItem2(9340, 10);
                            c.getItems().addItem(9243, 10);
                            c.startAnimation(4462);
                            c.getPA().closeAllWindows();
                            UpdateItems(c);
                            c.getPA().addSkillXPMultiplied(67, c.playerMagic, true);
                        }else {
                            c.sendMessage("You need Diamond bolts.");
                        }
                    } else {
                        c.sendMessage("You don't have the runes for this spell.");
                    }
                } else {
                    c.sendMessage("You need a Magic Level of 57 to Enchant this.");
                }
                break;
            case 100081:// Dragonstone bolts
                if(c.playerLevel[c.playerMagic] >= 68) {
                    if(!c.getItems().hasFreeSlots(1)) {
                        c.sendMessage("You need 1 Inv space.");
                        return;
                    }
                    if(c.getItems().playerHasItem(564, 1) && c.getItems().playerHasItem(557, 15) && c.getItems().playerHasItem(566, 1)) {
                        if(c.getItems().playerHasItem(9341)) {
                            c.getItems().deleteItem2(564, 1);
                            c.getItems().deleteItem2(557, 15);
                            c.getItems().deleteItem2(566, 1);
                            c.getItems().deleteItem2(9341, 10);
                            c.getItems().addItem(9244, 10);
                            c.startAnimation(4462);
                            c.getPA().closeAllWindows();
                            UpdateItems(c);
                            c.getPA().addSkillXPMultiplied(78, c.playerMagic, true);
                        }else {
                            c.sendMessage("You need Dragonstone bolts.");
                        }
                    } else {
                        c.sendMessage("You don't have the runes for this spell.");
                    }
                } else {
                    c.sendMessage("You need a Magic Level of 68 to Enchant this.");
                }
                break;
            case 100085:// Onyx bolts
                if(c.playerLevel[c.playerMagic] >= 87) {
                    if(!c.getItems().hasFreeSlots(1)) {
                        c.sendMessage("You need 1 Inv space.");
                        return;
                    }
                    if(c.getItems().playerHasItem(564, 1) && c.getItems().playerHasItem(554, 20) && c.getItems().playerHasItem(560, 1)) {
                        if(c.getItems().playerHasItem(9342)) {
                            c.getItems().deleteItem2(564, 1);
                            c.getItems().deleteItem2(554, 20);
                            c.getItems().deleteItem2(560, 1);
                            c.getItems().deleteItem2(9342, 10);
                            c.getItems().addItem(9245, 10);
                            c.startAnimation(4462);
                            c.getPA().closeAllWindows();
                            UpdateItems(c);
                            c.getPA().addSkillXPMultiplied(97, c.playerMagic, true);
                        }else {
                            c.sendMessage("You need Onyx bolts.");
                        }
                    } else {
                        c.sendMessage("You don't have the runes for this spell.");
                    }
                } else {
                    c.sendMessage("You need a Magic Level of 87 to Enchant this.");
                }
                break;
        }
    }
}
