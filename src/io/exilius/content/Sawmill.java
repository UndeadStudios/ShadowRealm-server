package io.exilius.content;

import io.exilius.Server;
import io.exilius.model.Items;
import io.exilius.model.entity.player.Player;

public class Sawmill {

    public static int INTERFACE = 24786;

    public static void OpemSawMill(Player player) {
        if(player.sawmill) {
            player.getPA().showInterface(INTERFACE);
        }
    }

    public static void HandleBottons(Player c, int button) {
        switch (button){
            case 97060:
                if(c.sawmill){
                    c.getPA().closeAllWindows();
                    c.sawmill = false;
                }
                break;
            case 97061:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 100)) {
                        if (c.getItems().playerHasItem(Items.LOGS, 1)) {
                            c.getItems().deleteItem2(995, 100);
                            c.getItems().deleteItem2(Items.LOGS, 1);
                            c.getItems().addItem(Items.PLANK, 1);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make a plank.");
                        } else {
                            c.sendMessage("You need logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97059:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 500)) {
                        if (c.getItems().playerHasItem(Items.LOGS, 5)) {
                            c.getItems().deleteItem2(995, 500);
                            c.getItems().deleteItem2(Items.LOGS, 5);
                            c.getItems().addItem(Items.PLANK, 5);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make 5 planks.");
                        } else {
                            c.sendMessage("You need 5 logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97058:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 1000)) {
                        if (c.getItems().playerHasItem(Items.LOGS, 10)) {
                            c.getItems().deleteItem2(995, 1000);
                            c.getItems().deleteItem2(Items.LOGS, 10);
                            c.getItems().addItem(Items.PLANK, 10);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make 10 planks.");
                        } else {
                            c.sendMessage("You need 10 logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97057:
                if(c.sawmill) {
                    c.xInterfaceId = 97057;
                    c.getPA().sendEnterAmount(0);
                }
                break;
            case 97048:
                if(c.sawmill) {
                    int ammount = c.getItems().getItemAmount(Items.LOGS);
                    if (c.getItems().playerHasItem(995, ammount*100)) {
                        if (c.getItems().playerHasItem(Items.LOGS, ammount)) {
                            c.getItems().deleteItem2(995, ammount*100);
                            c.getItems().deleteItem2(Items.LOGS, ammount);
                            c.getItems().addItem(Items.PLANK, ammount);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make "+ammount+" planks.");
                        } else {
                            c.sendMessage("You need "+ammount+" logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97066:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 250)) {
                        if (c.getItems().playerHasItem(Items.OAK_LOGS, 1)) {
                            c.getItems().deleteItem2(995, 250);
                            c.getItems().deleteItem2(Items.OAK_LOGS, 1);
                            c.getItems().addItem(Items.OAK_PLANK, 1);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make a oak plank.");
                        } else {
                            c.sendMessage("You need oak logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97065:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 1250)) {
                        if (c.getItems().playerHasItem(Items.OAK_LOGS, 5)) {
                            c.getItems().deleteItem2(995, 1250);
                            c.getItems().deleteItem2(Items.OAK_LOGS, 5);
                            c.getItems().addItem(Items.OAK_PLANK, 5);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make 5 oak planks.");
                        } else {
                            c.sendMessage("You need 5 oak logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97064:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 2500)) {
                        if (c.getItems().playerHasItem(Items.OAK_LOGS, 10)) {
                            c.getItems().deleteItem2(995, 2500);
                            c.getItems().deleteItem2(Items.OAK_LOGS, 10);
                            c.getItems().addItem(Items.OAK_PLANK, 10);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make 10 oak planks.");
                        } else {
                            c.sendMessage("You need 10 oak logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97063:
                if(c.sawmill) {
                    c.xInterfaceId = 97063;
                    c.getPA().sendEnterAmount(0);
                }
                break;
            case 97049:
                if(c.sawmill) {
                    int ammount = c.getItems().getItemAmount(Items.OAK_LOGS);
                    if (c.getItems().playerHasItem(995, ammount*250)) {
                        if (c.getItems().playerHasItem(Items.OAK_LOGS, ammount)) {
                            c.getItems().deleteItem2(995, ammount*250);
                            c.getItems().deleteItem2(Items.OAK_LOGS, ammount);
                            c.getItems().addItem(Items.OAK_PLANK, ammount);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make "+ammount+" oak planks.");
                        } else {
                            c.sendMessage("You need "+ammount+" oak logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97071:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 500)) {
                        if (c.getItems().playerHasItem(Items.TEAK_LOGS, 1)) {
                            c.getItems().deleteItem2(995, 500);
                            c.getItems().deleteItem2(Items.TEAK_LOGS, 1);
                            c.getItems().addItem(Items.TEAK_PLANK, 1);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make a teak plank.");
                        } else {
                            c.sendMessage("You need teak logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97070:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 2500)) {
                        if (c.getItems().playerHasItem(Items.TEAK_LOGS, 5)) {
                            c.getItems().deleteItem2(995, 2500);
                            c.getItems().deleteItem2(Items.TEAK_LOGS, 5);
                            c.getItems().addItem(Items.TEAK_PLANK, 5);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make 5 teak planks.");
                        } else {
                            c.sendMessage("You need 5 teak logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97069:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 5000)) {
                        if (c.getItems().playerHasItem(Items.TEAK_LOGS, 10)) {
                            c.getItems().deleteItem2(995, 5000);
                            c.getItems().deleteItem2(Items.TEAK_LOGS, 10);
                            c.getItems().addItem(Items.TEAK_PLANK, 10);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make 10 teak planks.");
                        } else {
                            c.sendMessage("You need 10 teak logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97068:
                if(c.sawmill) {
                    c.xInterfaceId = 97068;
                    c.getPA().sendEnterAmount(0);
                }
                break;
            case 97050:
                if(c.sawmill) {
                    int ammount = c.getItems().getItemAmount(Items.TEAK_LOGS);
                    if (c.getItems().playerHasItem(995, ammount*500)) {
                        if (c.getItems().playerHasItem(Items.TEAK_LOGS, ammount)) {
                            c.getItems().deleteItem2(995, ammount*500);
                            c.getItems().deleteItem2(Items.TEAK_LOGS, ammount);
                            c.getItems().addItem(Items.TEAK_PLANK, ammount);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make "+ammount+" teak planks.");
                        } else {
                            c.sendMessage("You need "+ammount+" teak logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;

            case 97076:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 1500)) {
                        if (c.getItems().playerHasItem(Items.MAHOGANY_LOGS, 1)) {
                            c.getItems().deleteItem2(995, 1500);
                            c.getItems().deleteItem2(Items.MAHOGANY_LOGS, 1);
                            c.getItems().addItem(Items.MAHOGANY_PLANK, 1);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make a mahogany plank.");
                        } else {
                            c.sendMessage("You need mahogany logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97075:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 7500)) {
                        if (c.getItems().playerHasItem(Items.MAHOGANY_LOGS, 5)) {
                            c.getItems().deleteItem2(995, 7500);
                            c.getItems().deleteItem2(Items.MAHOGANY_LOGS, 5);
                            c.getItems().addItem(Items.MAHOGANY_PLANK, 5);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make 5 mahogany planks.");
                        } else {
                            c.sendMessage("You need 5 mahogany logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97074:
                if(c.sawmill) {
                    if (c.getItems().playerHasItem(995, 15000)) {
                        if (c.getItems().playerHasItem(Items.MAHOGANY_LOGS, 10)) {
                            c.getItems().deleteItem2(995, 15000);
                            c.getItems().deleteItem2(Items.MAHOGANY_LOGS, 10);
                            c.getItems().addItem(Items.MAHOGANY_PLANK, 10);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make 10 mahogany planks.");
                        } else {
                            c.sendMessage("You need 10 mahogany logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
            case 97073:
                if(c.sawmill) {
                    c.xInterfaceId = 97073;
                    c.getPA().sendEnterAmount(0);
                }
                break;
            case 97051:
                if(c.sawmill) {
                    int ammount = c.getItems().getItemAmount(Items.MAHOGANY_LOGS);
                    if (c.getItems().playerHasItem(995, ammount*1500)) {
                        if (c.getItems().playerHasItem(Items.MAHOGANY_LOGS, ammount)) {
                            c.getItems().deleteItem2(995, ammount*1500);
                            c.getItems().deleteItem2(Items.MAHOGANY_LOGS, ammount);
                            c.getItems().addItem(Items.MAHOGANY_PLANK, ammount);
                            c.getPA().closeAllWindows();
                            c.sawmill = false;
                            if(!Server.itemHandler.itemExists(c, 9468, c.absX, c.absY, c.heightLevel)) {
                                Server.itemHandler.createGroundItem(c, 9468, c.absX, c.absY, c.heightLevel, 1, c.getIndex());
                            }
                            c.sendMessage("You make "+ammount+" mahogany planks.");
                        } else {
                            c.sendMessage("You need "+ammount+" mahogany logs to do this.");
                        }
                    } else {
                        c.sendMessage("You don't have enough coins.");
                    }
                }
                break;
        }
    }
}

