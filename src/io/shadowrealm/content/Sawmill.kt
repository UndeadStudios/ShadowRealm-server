package io.shadowrealm.content

import io.shadowrealm.Server
import io.shadowrealm.model.entity.player.Player
import io.shadowrealm.content.Sawmill
import io.shadowrealm.content.skills.Skill
import io.shadowrealm.model.Items

object Sawmill {
    var INTERFACE = 24786
    @JvmStatic
    fun openSawMill(player: Player) {
        if (player.sawmill) {
            player.pa.showInterface(INTERFACE)
        }
    }

    @JvmStatic
    fun handleButtons(c: Player, button: Int) {
        when (button) {
            97060 -> if (c.sawmill) {
                c.pa.closeAllWindows()
                c.sawmill = false
            }
            97061 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 100)) {
                    if (c.items.playerHasItem(Items.LOGS, 1)) {
                        c.items.deleteItem2(995, 100)
                        c.items.deleteItem2(Items.LOGS, 1)
                        c.items.addItem(Items.PLANK, 1)
                        c.pa.closeAllWindows()
                        c.pa.addSkillXPMultiplied(25.0, Skill.CONSTRUCTION.id, true)
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make a plank.")
                    } else {
                        c.sendMessage("You need logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97059 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 500)) {
                    if (c.items.playerHasItem(Items.LOGS, 5)) {
                        c.items.deleteItem2(995, 500)
                        c.items.deleteItem2(Items.LOGS, 5)
                        c.items.addItem(Items.PLANK, 5)
                        c.pa.closeAllWindows()
                        c.pa.addSkillXPMultiplied((25 * 5).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make 5 planks.")
                    } else {
                        c.sendMessage("You need 5 logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97058 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 1000)) {
                    if (c.items.playerHasItem(Items.LOGS, 10)) {
                        c.items.deleteItem2(995, 1000)
                        c.items.deleteItem2(Items.LOGS, 10)
                        c.items.addItem(Items.PLANK, 10)
                        c.pa.addSkillXPMultiplied((25 * 10).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make 10 planks.")
                    } else {
                        c.sendMessage("You need 10 logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97057 -> if (c.sawmill) {
                c.xInterfaceId = 97057
                c.pa.sendEnterAmount(0)
            }
            97048 -> if (c.sawmill) {
                val ammount = c.items.getItemAmount(Items.LOGS)
                if (c.items.playerHasItem(995, ammount * 100)) {
                    if (c.items.playerHasItem(Items.LOGS, ammount)) {
                        c.items.deleteItem2(995, ammount * 100)
                        c.items.deleteItem2(Items.LOGS, ammount)
                        c.items.addItem(Items.PLANK, ammount)
                        c.pa.closeAllWindows()
                        c.pa.addSkillXPMultiplied((25 * ammount).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make $ammount planks.")
                    } else {
                        c.sendMessage("You need $ammount logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97066 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 250)) {
                    if (c.items.playerHasItem(Items.OAK_LOGS, 1)) {
                        c.items.deleteItem2(995, 250)
                        c.items.deleteItem2(Items.OAK_LOGS, 1)
                        c.items.addItem(Items.OAK_PLANK, 1)
                        c.pa.addSkillXPMultiplied(25.0, Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make a oak plank.")
                    } else {
                        c.sendMessage("You need oak logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97065 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 1250)) {
                    if (c.items.playerHasItem(Items.OAK_LOGS, 5)) {
                        c.items.deleteItem2(995, 1250)
                        c.items.deleteItem2(Items.OAK_LOGS, 5)
                        c.items.addItem(Items.OAK_PLANK, 5)
                        c.pa.addSkillXPMultiplied((25 * 5).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make 5 oak planks.")
                    } else {
                        c.sendMessage("You need 5 oak logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97064 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 2500)) {
                    if (c.items.playerHasItem(Items.OAK_LOGS, 10)) {
                        c.items.deleteItem2(995, 2500)
                        c.items.deleteItem2(Items.OAK_LOGS, 10)
                        c.items.addItem(Items.OAK_PLANK, 10)
                        c.pa.addSkillXPMultiplied((25 * 10).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make 10 oak planks.")
                    } else {
                        c.sendMessage("You need 10 oak logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97063 -> if (c.sawmill) {
                c.xInterfaceId = 97063
                c.pa.sendEnterAmount(0)
            }
            97049 -> if (c.sawmill) {
                val ammount = c.items.getItemAmount(Items.OAK_LOGS)
                if (c.items.playerHasItem(995, ammount * 250)) {
                    if (c.items.playerHasItem(Items.OAK_LOGS, ammount)) {
                        c.items.deleteItem2(995, ammount * 250)
                        c.items.deleteItem2(Items.OAK_LOGS, ammount)
                        c.items.addItem(Items.OAK_PLANK, ammount)
                        c.pa.addSkillXPMultiplied((25 * ammount).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make $ammount oak planks.")
                    } else {
                        c.sendMessage("You need $ammount oak logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97071 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 500)) {
                    if (c.items.playerHasItem(Items.TEAK_LOGS, 1)) {
                        c.items.deleteItem2(995, 500)
                        c.items.deleteItem2(Items.TEAK_LOGS, 1)
                        c.items.addItem(Items.TEAK_PLANK, 1)
                        c.pa.addSkillXPMultiplied(25.0, Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make a teak plank.")
                    } else {
                        c.sendMessage("You need teak logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97070 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 2500)) {
                    if (c.items.playerHasItem(Items.TEAK_LOGS, 5)) {
                        c.items.deleteItem2(995, 2500)
                        c.items.deleteItem2(Items.TEAK_LOGS, 5)
                        c.items.addItem(Items.TEAK_PLANK, 5)
                        c.pa.addSkillXPMultiplied((25 * 5).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make 5 teak planks.")
                    } else {
                        c.sendMessage("You need 5 teak logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97069 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 5000)) {
                    if (c.items.playerHasItem(Items.TEAK_LOGS, 10)) {
                        c.items.deleteItem2(995, 5000)
                        c.items.deleteItem2(Items.TEAK_LOGS, 10)
                        c.items.addItem(Items.TEAK_PLANK, 10)
                        c.pa.addSkillXPMultiplied((25 * 10).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make 10 teak planks.")
                    } else {
                        c.sendMessage("You need 10 teak logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97068 -> if (c.sawmill) {
                c.xInterfaceId = 97068
                c.pa.sendEnterAmount(0)
            }
            97050 -> if (c.sawmill) {
                val ammount = c.items.getItemAmount(Items.TEAK_LOGS)
                if (c.items.playerHasItem(995, ammount * 500)) {
                    if (c.items.playerHasItem(Items.TEAK_LOGS, ammount)) {
                        c.items.deleteItem2(995, ammount * 500)
                        c.items.deleteItem2(Items.TEAK_LOGS, ammount)
                        c.items.addItem(Items.TEAK_PLANK, ammount)
                        c.pa.addSkillXPMultiplied((25 * ammount).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make $ammount teak planks.")
                    } else {
                        c.sendMessage("You need $ammount teak logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97076 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 1500)) {
                    if (c.items.playerHasItem(Items.MAHOGANY_LOGS, 1)) {
                        c.items.deleteItem2(995, 1500)
                        c.items.deleteItem2(Items.MAHOGANY_LOGS, 1)
                        c.items.addItem(Items.MAHOGANY_PLANK, 1)
                        c.pa.addSkillXPMultiplied((25 * 1).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make a mahogany plank.")
                    } else {
                        c.sendMessage("You need mahogany logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97075 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 7500)) {
                    if (c.items.playerHasItem(Items.MAHOGANY_LOGS, 5)) {
                        c.items.deleteItem2(995, 7500)
                        c.items.deleteItem2(Items.MAHOGANY_LOGS, 5)
                        c.items.addItem(Items.MAHOGANY_PLANK, 5)
                        c.pa.closeAllWindows()
                        c.pa.addSkillXPMultiplied((25 * 5).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make 5 mahogany planks.")
                    } else {
                        c.sendMessage("You need 5 mahogany logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97074 -> if (c.sawmill) {
                if (c.items.playerHasItem(995, 15000)) {
                    if (c.items.playerHasItem(Items.MAHOGANY_LOGS, 10)) {
                        c.items.deleteItem2(995, 15000)
                        c.items.deleteItem2(Items.MAHOGANY_LOGS, 10)
                        c.items.addItem(Items.MAHOGANY_PLANK, 10)
                        c.pa.addSkillXPMultiplied((25 * 10).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make 10 mahogany planks.")
                    } else {
                        c.sendMessage("You need 10 mahogany logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
            97073 -> if (c.sawmill) {
                c.xInterfaceId = 97073
                c.pa.sendEnterAmount(0)
            }
            97051 -> if (c.sawmill) {
                val ammount = c.items.getItemAmount(Items.MAHOGANY_LOGS)
                if (c.items.playerHasItem(995, ammount * 1500)) {
                    if (c.items.playerHasItem(Items.MAHOGANY_LOGS, ammount)) {
                        c.items.deleteItem2(995, ammount * 1500)
                        c.items.deleteItem2(Items.MAHOGANY_LOGS, ammount)
                        c.items.addItem(Items.MAHOGANY_PLANK, ammount)
                        c.pa.addSkillXPMultiplied((25 * ammount).toDouble(), Skill.CONSTRUCTION.id, true)
                        c.pa.closeAllWindows()
                        c.sawmill = false
                        if (!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                            Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.index)
                        }
                        c.sendMessage("You make $ammount mahogany planks.")
                    } else {
                        c.sendMessage("You need $ammount mahogany logs to do this.")
                    }
                } else {
                    c.sendMessage("You don't have enough coins.")
                }
            }
        }
    }
}