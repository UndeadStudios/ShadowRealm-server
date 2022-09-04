package io.exilius.model.entity.npc;

//import godzhell.util.Stream;
import io.exilius.Server;
import io.exilius.util.Stream;
import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * A class that loads & manages NPC configurations.
 *
 * <p>An <code>NPCDefinition</code> is a component of the NPC configuration file
 * that defines several aspects of the NPC (such as models, model colors, animations,
 * name, description, and combat level). We use these definitions both client and server-
 * sided in order to display information on the client and pull data when necessary
 * server-sided (i.e. - for combat formulas). </p>
 * @author Craze/Warren
 * @date Sep 20, 2015 5:13:47 PM

 */
public class NPCCacheDefinition {

    /**
     * Represents the total whole number integer of NPCs.
     */
    public static int NPC_TOTAL = 11474;
    private String opcode112;
    private int[] dialogueModels;
    private boolean onMinimap;
    private int anInt91;
    private int anInt86;
    private boolean aBoolean93;
    private int anInt85;
    private int anInt92;
    private int anInt75;
    private int getDegreesToTurn;

    /**
     * Returns a {@link NPCCacheDefinition} for the specified ID.
     * @param i	the id of the NPC to get the definition for
     * @return	the definition
     */
    public static final NPCCacheDefinition forID(int i) {
        for(int j = 0; j < 20; j++)
            if(cache[j].type == i)
                return cache[j];

        cacheIndex = (cacheIndex + 1) % 20;
        NPCCacheDefinition npcDef = cache[cacheIndex] = new NPCCacheDefinition();
        if(i < streamIndices.length)
            npcData.currentOffset = streamIndices[i];
        npcDef.type = i;
        if(i < totalNpcs)
            npcDef.readValues(npcData);
        if (i == 8184) {
            npcDef.name = "Theatre Of Blood Wizard";
            npcDef.actions = new String[5];
            npcDef.actions[0] = "Teleport";
        }
        if (i == 7599) {
            npcDef.name = "Exilius Guide";
        }
        if (i == 4305) {
            npcDef.name = "Drunken cannoneer";
            npcDef.actions = new String[5];
            npcDef.actions[0] = "Pickpocket";
        }
        if (i == 3247) {
            npcDef.name = "Wizard";
            npcDef.actions = new String[5];
            npcDef.actions[0] = "Teleport";
        }
        if (i == 6517) {
            npcDef.name = "Daily-reward wizard";
            npcDef.actions = new String[5];
            npcDef.actions[0] = "Talk-to";
            npcDef.actions[2] = "View rewards";
        }
        if (i == 3428 || i == 3429) {
            npcDef.name = "Elf warrior";
        }
        if (i == 5044) { // sanfew (decant)
            npcDef.actions = new String[5];
            npcDef.actions[0] = "Decant-potions";
        }
        if (i == 8026) {
            npcDef.combatLevel = 392;
        }
        if (i == 7913) {
            npcDef.combatLevel = 0;
            npcDef.name = "Ironman shop keeper";
            npcDef.description = "A shop specifically for iron men.";
        }
        if (i == 8906) {
            npcDef.combatLevel = 0;
            npcDef.name = "Santa's little elf";
            npcDef.description = "A helper sent from santa himself.";
            npcDef.actions = new String[] { "Talk-To", null, "Christmas Shop", "Return-Items", null };
        }
        if (i == 954) {
            npcDef.combatLevel = 0;
            npcDef.name = "Crystal Seed Trader";
            npcDef.description = "Use a seed on me to get a Crystal Bow.";

        }
        if (i == 6970) {
            npcDef.combatLevel = 0;
            npcDef.name = "Theif";
            npcDef.actions = new String[] { null, null, "Pickpocket",  null,  null };
        }
        if (i == 8761) {
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Talk-to", null, "Assignment", "Trade", "Rewards" };

        }
        if (i == 9400) {
            npcDef.name = "Ted O'bombr";
        }
        if (i == 8026 || i == 8027 || i == 8028) {
            npcDef.size = 9;
        }
        if (i == 7954) {
            npcDef.combatLevel = 0;
            npcDef.name = "Achievement Master";
            npcDef.actions = new String[] { "Trade", null, "Open Achievements", null, null, };

        }
        if (i == 5870) {
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Talk-to", null, "Assignment", "Trade", "Rewards" };

        }
        if (i == 3400) {
            npcDef.combatLevel = 0;
            npcDef.name = "Giveaway Manager";
            npcDef.actions = new String[] { "Open-manager", null, null, null, null };

        }
        if (i == 1013) {
            npcDef.combatLevel = 0;
            npcDef.name = "Gambler Shop";
            npcDef.description = "A shop specifically for gamblers.";
        }
        if (i == 308) {
            npcDef.combatLevel = 0;
            npcDef.name = "PKP Manager";
        }
        if (i == 13) {
            npcDef.combatLevel = 0;
            npcDef.name = "Referral Tutor";
            npcDef.description = "He manages referrals.";
        }
        if (i == 5293) {
            npcDef.combatLevel = 0;
            npcDef.name = "Elven Keeper";
        }
        if(i==3218 || i ==3217){
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if(i==2897){
            npcDef.combatLevel = 0;
            npcDef.name = "Trading Post Manager";
            npcDef.actions = new String[] { "Open", null, "Collect", null, null };
        }
        if(i==1306){
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Make-over", null, null, null, null };
        }
        if(i==3257){
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if(i==1011){
            npcDef.combatLevel = 0;
            npcDef.name = "Item Gambler";
            npcDef.actions = new String[] { "Info", null, "Gamble", null, null };
        }
        if(i==3248){
            npcDef.combatLevel = 0;
            npcDef.name = "Exilius Wizard";
            npcDef.actions = new String[] { "Teleport", null, "Previous Location", null, null };
        }
        if(i==1520){
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Small Net", null, "Harpoon", null, null };
        }
        if(i==8920){

            npcDef.actions = new String[] { null, "Attack", null, null, null };
        }
        if(i==8921){
            npcDef.name = "Crystal Whirlwind";
        }
        if(i==9120){
            npcDef.combatLevel = 0;
            npcDef.name = "Donator Shop";
            npcDef.actions = new String[] { "Trade", null, "Rewards", null, null };
        }
        if(i == 2662){
            npcDef.combatLevel = 0;
            npcDef.name = "Tournament Manager";
            npcDef.actions = new String[] { "Open-Shop", null, null, null, null };
        }
        if(i==603){
            npcDef.combatLevel = 0;
            npcDef.name = "Captain Kraken";
            npcDef.actions = new String[] { "Talk-to", null, null, null, null };
        }
        if(i==7041){
            npcDef.combatLevel = 0;
            npcDef.name = "Ticket Exchange";
            npcDef.actions = new String[] { "Exchange", null, null, null, null };
        }
        if(i==3894){
            npcDef.combatLevel = 0;
            npcDef.name = "Sigmund The Merchant";
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }

        if (i==7413) {
            npcDef.name = "Max Dummy";
            npcDef.actions[0] = null;
        }
        if(i==9011){
            npcDef.combatLevel = 0;
            npcDef.name = "Vote Shop";
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if(i==1933){
            npcDef.combatLevel = 0;
            npcDef.name = "Mills";
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if(i==8819){
            npcDef.combatLevel = 0;
            npcDef.name = "Boss point shop";
            npcDef.actions = new String[] { null, null, "Trade", null, null };
        }
        if(i==8688){
            npcDef.combatLevel = 0;
            npcDef.name = "Fat Tony";
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if(i==7769){
            npcDef.combatLevel = 0;
            npcDef.name = "Shop Keeper";
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if(i==6987){
            npcDef.combatLevel = 0;
            npcDef.name = "Man";
            npcDef.actions = new String[] { "Talk", null, "Pickpocket", null, null };
        }
        if(i==5730){
            npcDef.combatLevel = 0;
            npcDef.name = "Master Farmer";
            npcDef.actions = new String[] { "Pickpocket", null, "Trade", null, null };
        }
        if(i==1501){
            npcDef.combatLevel = 0;
            npcDef.name = "Hunter Store";
            npcDef.actions = new String[] { null, null, null, null, "Trade" };
        }
        if(i==2913){
            npcDef.combatLevel = 0;
            npcDef.name = "Fishing Store";
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if(i==5809){
            npcDef.combatLevel = 0;
            npcDef.name = "Crafting and Tanner";
            npcDef.actions = new String[] { "Tan", null, "Trade", null, null };
        }
        if(i==555){
            npcDef.combatLevel = 0;
            npcDef.name = "Sell Me Store";
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if(i==9168){
            npcDef.combatLevel = 0;
            npcDef.name = "Flex";
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if(i==8208){
            npcDef.combatLevel = 0;
            npcDef.name = "Pet Collector";
            npcDef.actions = new String[] { "Talk-to", null, null, null, null };
        }
        if(i==8202){
            npcDef.actions = new String[] { "Talk-to", "Pick-Up", null, null, null };
        }
        if(i==4921){
            npcDef.combatLevel = 0;
            npcDef.name = "Supplies";
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if (i == 5314) {
            npcDef.combatLevel = 0;
            npcDef.name = "Mystical Wizard";
            npcDef.actions = new String[] { "Teleport", "Previous Location", null, null, null };
            npcDef.description = "This wizard has the power to teleport you to many locations.";
        }
        if (i == 8781) {
            npcDef.name = "@red@Queen Latsyrc";
            npcDef.combatLevel = 982;
            npcDef.onMinimap = true;
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { null, "Attack", null, null, null };
        }
        if(i==1577){
            npcDef.combatLevel = 0;
            npcDef.name = "Melee Shop";
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if(i==1576){
            npcDef.combatLevel = 0;
            npcDef.name = "Range Shop";
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if(i==1578){
            npcDef.combatLevel = 0;
            npcDef.name = "Mage Shop";
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Trade", null, null, null, null };
        }
        if (i == 8026) {
            npcDef.name = "Vorkath";
            // entityDef.combatLevel = 732;
            npcDef.models = new int[] { 35023 };
            npcDef.standAnim = 7946;
            npcDef.onMinimap = true;
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Poke", null, null, null, null };
            npcDef.anInt86 = 100;
            npcDef.anInt91 = 100;
        }
        if (i == 7852 || i == 7853 || i == 7884) {//Dawn
            npcDef.standAnim = 7775;
            npcDef.walkAnim = 7775;
        }
        if (i == 5518) {
            npcDef.standAnim = 185;
        }
        if (i == 8019) {
            npcDef.standAnim = 185;
            npcDef.actions = new String[5];
            npcDef.actions[0] = "Talk-to";
            npcDef.actions[2] = "Trade";
        }
        if (i == 308) {
            npcDef.actions = new String[5];
            npcDef.actions[0] = "Talk-to";
            npcDef.actions[2] = "Trade";
            npcDef.actions[3] = "Disable Interface";
            npcDef.actions[4] = "Skull";
        }
        if (i == 6088) {
            npcDef.standAnim = 185;
            npcDef.actions = new String[5];
            npcDef.actions[0] = "Talk-to";
            npcDef.actions[2] = "Travel";
        }
        if (i == 1434 || i == 876 || i == 1612) {//gnome fix
            npcDef.standAnim = 185;
        }
        if (i == 7674 || i == 8009 || i == 388 || i == 8010) {

            npcDef.combatLevel = 0;
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", "Metamorphosis", null };
        }
        if (i == 8492 || i == 8493 || i == 8494 || i == 8495) {
            npcDef.combatLevel = 0;
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", "Metamorphosis", null };
        }
        if (i == 8737 || i == 8738 || i == 8009 || i == 7674) {
            npcDef.combatLevel = 0;
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", "Metamorphosis", null };
        }
        if (i == 326 || i == 327) {
            npcDef.combatLevel = 0;
            npcDef.anInt86 = 85;
            npcDef.anInt91 = 85;
            npcDef.name = "Vote Pet";
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", "Metamorphosis", null };
        }
        if (i >= 7354 && i <=7367) {
            npcDef.combatLevel = 0;
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", "Metamorphosis", null };
        }
        if (i == 5559 || i == 5560) {
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", "Metamorphosis", null };
        }
        if (i == 2149 || i == 2150 || i == 2151 || i == 2148) {
            npcDef.name = "Trading Clerk";
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Bank", null, "Trading Post", null, null };
        }
        if (i == 6473) { //terror dog
            npcDef.combatLevel = 0;
            npcDef.anInt86 = 50; //WIDTH
            npcDef.anInt91 = 50; // HEIGH
        }
        if (i == 3510) { //outlast shop
            npcDef.name = "Trader";
            npcDef.combatLevel = 0;
            npcDef.onMinimap = true;
            npcDef.anInt86 = 150; //WIDTH
            npcDef.anInt91 = 150; // HEIGH
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Open-Shop", null, null, null, null };
        }
        if (i == 488) { //rain cloud
            npcDef.combatLevel = 0;
            npcDef.size = 1;
            npcDef.onMinimap = true;
            npcDef.anInt86 = 150; //WIDTH
            npcDef.anInt91 = 150; // HEIGH
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
        }
        if (i == 7668) { //voice of yama
            npcDef.name = "Kratos";
            npcDef.size = 2;
            npcDef.combatLevel = 0;
            npcDef.anInt86 = 90; //WIDTH
            npcDef.anInt91 = 90; // HEIGH
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };

        }
        if (i == 1377) {
            npcDef.size = 3;
            npcDef.anInt86 = 300; //WIDTH
            npcDef.anInt91 = 300; // HEIGH
            npcDef.actions[0] = null;


        }
        if (i == 2105) {
            npcDef.size = 4;
            npcDef.anInt86 = 600; //WIDTH
            npcDef.anInt91 = 600; // HEIGH
        }
        if (i == 2107) {
            npcDef.size = 4;
            npcDef.anInt86 = 600; //WIDTH
            npcDef.anInt91 = 600; // HEIGH
        }
        if (i == 2850) {
            npcDef.name = "GIM Tracker";
            npcDef.actions = new String[] { "Open", null, null, null, null };

        }
        if (i == 6119) { //weird monster
            npcDef.size = 1;
            npcDef.combatLevel = 0;
            npcDef.anInt86 = 30; //WIDTH
            npcDef.anInt91 = 30; // HEIGH
        }
        if (i == 763) { //roc

            npcDef.size = 1;
            npcDef.combatLevel = 0;
            npcDef.anInt86 = 30; //WIDTH
            npcDef.anInt91 = 30; // HEIGH
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", "Metamorphosis", null };


        }
        if (i == 762) { //foe small bird
            npcDef.size = 1;
            npcDef.combatLevel = 0;
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", "Metamorphosis", null };
        }
        if (i == 4987 || i == 6292 || i == 6354 ) { //chronzon
            npcDef.size = 1;
            npcDef.combatLevel = 0;
            npcDef.anInt86 = 45; //WIDTH
            npcDef.anInt91 = 45; // HEIGH
        }
        if (i == 8709) {
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.name = "Corrupt Beast";
            npcDef.combatLevel = 0;
            npcDef.anInt86 = 60; //WIDTH
            npcDef.anInt91 = 60; // HEIGH
            npcDef.size = 1;
        }
        if (i == 7025) { //guard dog
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.anInt86 = 85; //WIDTH
            npcDef.anInt91 = 85; // HEIGH
        }

        if (i == 6716) {//prayer
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.anInt86 = 65; //WIDTH
            npcDef.anInt91 = 65; // HEIGH
            npcDef.combatLevel = 0;


        }
        if (i == 6723) {//healer
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.anInt86 = 65; //WIDTH
            npcDef.anInt91 = 65; // HEIGH
            npcDef.combatLevel = 0;

        }
        if (i == 1088) {
            npcDef.name = "Seren";
            npcDef.models = new int[] { 38605 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.anInt86 = 65; //WIDTH
            npcDef.anInt91 = 65; // HEIGH
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 8372;
            npcDef.walkAnim = 8372;
            npcDef.models = new int[] { 38605 };

        }
        if (i == 1089) {
            npcDef.name = "Lil mimic";
            npcDef.models = new int[] { 37142 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.anInt86 = 25; //WIDTH
            npcDef.anInt91 = 25; // HEIGH
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 8307;
            npcDef.walkAnim = 8306;
            npcDef.models = new int[] { 37142 };

        }
        if (i == 2120) {
            npcDef.name = "Shadow Ranger";
            npcDef.models = new int[] { 29267 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.anInt86 = 85; //WIDTH
            npcDef.anInt91 = 85; // HEIGH
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 8526;
            npcDef.walkAnim = 8527;
            npcDef.models = new int[] { 29267 };

        }
        if (i == 2121) {
            npcDef.name = "Shadow Wizard";
            npcDef.models = new int[] { 29268 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.anInt86 = 85; //WIDTH
            npcDef.anInt91 = 85; // HEIGH
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 8526;
            npcDef.walkAnim = 8527;
            npcDef.models = new int[] { 29268 };
        }
        if (i == 2122) {
            npcDef.name = "Shadow Warrior";
            npcDef.models = new int[] { 29266 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.anInt86 = 85; //WIDTH
            npcDef.anInt91 = 85; // HEIGH
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 8526;
            npcDef.walkAnim = 8527;
            npcDef.models = new int[] { 29266 };
        }

        if (i == 7216 || i == 6473) {//green monkey and green dog
            npcDef.actions = new String[5];
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
        }
        if (i == 6723 || i == 6716 || i == 8709) {
            npcDef.actions = new String[5];
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
        }
        if (i == 3291) {//postie pete
            npcDef.actions = new String[5];
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
        }
        if (i == 5738) {//imp
            npcDef.actions = new String[5];
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };

        }
        if (i == 5240) {//toucan
            npcDef.actions = new String[5];
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };

        }
        if (i == 834) {
            npcDef.name = "King penguin";
            npcDef.actions = new String[5];
            npcDef.combatLevel = 0;
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };

        }
        if (i == 1873) {//klik
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.standAnim = 3345;
            npcDef.walkAnim = 3346;

        }
        //dark pets
        //dark pets
        if (i == 2300) {
            npcDef.models = new int[1];
            npcDef.name = "Dark postie pete";
            npcDef.models = new int[] { 46601 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 3948;
            npcDef.walkAnim = 3947;
        }
        if (i == 2301) {
            npcDef.name = "Dark imp";
            npcDef.models = new int[] { 46602 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 171;
            npcDef.walkAnim = 168;
        }
        if (i == 2302) {
            npcDef.name = "Dark toucan";
            npcDef.models = new int[] { 46603, 46604 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 6772;
            npcDef.walkAnim = 6774;
        }
        if (i == 2303) {
            npcDef.name = "Dark king penguin";
            npcDef.models = new int[] { 46605 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 5668;
            npcDef.walkAnim = 5666;
        }
        if (i == 2304) {
            npcDef.name = "Dark k'klik";
            npcDef.models = new int[] { 46606, 46607, 46608 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 3346;
            npcDef.walkAnim = -1;
        }
        if (i == 2305) {
            npcDef.name = "Dark shadow warrior";
            npcDef.models = new int[] { 46609 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 8526;
            npcDef.walkAnim = 8527;
            npcDef.anInt86 = 85; //WIDTH
            npcDef.anInt91 = 85; // HEIGH
        }
        if (i == 2306) {
            npcDef.name = "Dark shadow archer";
            npcDef.models = new int[] { 46610 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 8526;
            npcDef.walkAnim = 8527;
            npcDef.anInt86 = 85; //WIDTH
            npcDef.anInt91 = 85; // HEIGH
        }
        if (i == 2307) {
            npcDef.name = "Dark shadow wizard";
            npcDef.models = new int[] { 46611 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 8526;
            npcDef.walkAnim = 8527;
            npcDef.anInt86 = 85; //WIDTH
            npcDef.anInt91 = 85; // HEIGH
        }
        if (i == 2308) {
            npcDef.name = "Dark healer death spawn";
            npcDef.models = new int[] { 46612, 46613, 46614, 46615, 46616, 46616, 46617,};
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.anInt86 = 65; //WIDTH
            npcDef.anInt91 = 65; // HEIGH
            npcDef.combatLevel = 0;
            npcDef.standAnim = 1539;
            npcDef.walkAnim = 1539;
        }
        if (i == 2309) {
            npcDef.name = "Dark holy death spawn";
            npcDef.models = new int[] { 46624, 46623, 46622, 46621, 46620, 46619, 46618 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.anInt86 = 65; //WIDTH
            npcDef.anInt91 = 65; // HEIGH
            npcDef.combatLevel = 0;
            npcDef.standAnim = 1539;
            npcDef.walkAnim = 1539;
        }
        if (i == 2310) {
            npcDef.name = "Dark seren";
            npcDef.models = new int[] { 46625 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.standAnim = 8372;
            npcDef.walkAnim = 8372;
            npcDef.anInt86 = 65; //WIDTH
            npcDef.anInt91 = 65; // HEIGH
        }
        if (i == 2311) {
            npcDef.name = "Dark corrupt beast";
            npcDef.models = new int[] { 46626 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.combatLevel = 0;
            npcDef.anInt86 = 60; //WIDTH
            npcDef.anInt91 = 60; // HEIGH
            npcDef.size = 1;
            npcDef.standAnim = 5616;
            npcDef.walkAnim = 5615;
        }
        if (i == 2312) {
            npcDef.name = "Dark roc";
            npcDef.models = new int[] { 46627, 46628 };
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { "Talk-to", null, "Pick-Up", null, null };
            npcDef.originalColors = null;
            npcDef.newColors = null;
            npcDef.standAnim = 5021;
            npcDef.walkAnim = 5022;
            npcDef.size = 1;
            npcDef.combatLevel = 0;
            npcDef.anInt86 = 30; //WIDTH
            npcDef.anInt91 = 30; // HEIGH
        }
        if (i == 8027) {
            npcDef.name = "Vorkath";
            npcDef.combatLevel = 732;
            npcDef.models = new int[] { 35023 };
            npcDef.standAnim = 7950;
            npcDef.onMinimap = true;
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { null, null, null, null, null };
            npcDef.anInt86 = 100;
            npcDef.anInt91 = 100;
        }
        if (i == 8028) {
            npcDef.name = "Vorkath";
            npcDef.combatLevel = 732;
            npcDef.models = new int[] { 35023 };
            npcDef.standAnim = 7948;
            npcDef.onMinimap = true;
            npcDef.actions = new String[5];
            npcDef.actions = new String[] { null, "Attack", null, null, null };
            npcDef.anInt86 = 100;
            npcDef.anInt91 = 100;
        }
        if(i==7144){
            npcDef.anInt75 = 0;
        }
        if(i==963){
            npcDef.anInt75 = 6;
        }
        if(i==7145){
            npcDef.anInt75 = 1;
        }
        if(i==7146){
            npcDef.anInt75 = 2;
        }
        if (npcDef.name != null && npcDef.name.toLowerCase().contains("chinchompa") && !npcDef.name.toLowerCase().contains("baby")) {
            npcDef.actions = new String[5];
        }
        return npcDef;
    }

    /**
     * Unpacks the NPC configurations.
     */
    public static final void unpackConfig() {
        try {
            npcData = new Stream(FileUtils.readFileToByteArray(new File(Server.getDataDirectory() + "/data/npc.dat")));
            Stream indexStream = new Stream(FileUtils.readFileToByteArray(new File(Server.getDataDirectory() + "/data/npc.idx")));
            totalNpcs = indexStream.readUnsignedWord();
            streamIndices = new int[totalNpcs];
            int i = 2;
            for(int j = 0; j < totalNpcs; j++)
            {
                streamIndices[j] = i;
                i += indexStream.readUnsignedWord();
            }

            cache = new NPCCacheDefinition[20];
            for(int k = 0; k < 20; k++)
                cache[k] = new NPCCacheDefinition();
            System.out.println("Successfully loaded: " + totalNpcs + " NPC definitions.");
        } catch (Exception e) {
            System.err.println("An error has occurred whilst loading NPC definitions!");
            e.printStackTrace();
        }
    }

    /**
     * Reads opcode values from the {@link Stream}.
     * @param str	the stream
     */
    private final void readValues(Stream str) {
        do
        {
            int opcode = str.readUnsignedByte();
            if(opcode == 0)
                return;
            if(opcode == 1)
            {
                int j = str.readUnsignedByte();
                models = new int[j];
                for (int j1 = 0; j1 < j; j1++)
                    models[j1] =str.readUnsignedWord();
            } else
            if(opcode == 2)
                name = str.readString().replaceAll("_", " ");
            else
            if(opcode == 3)
                description = str.readString();
            else
            if(opcode == 12)
                size = str.readSignedByte();
            else
            if(opcode == 13)
                standAnim = str.readUnsignedWord();
            else
            if(opcode == 14)
                walkAnim = str.readUnsignedWord();
            else
            if(opcode == 17) {
                walkAnim = str.readUnsignedWord();
                str.readUnsignedWord();
                str.readUnsignedWord();
                str.readUnsignedWord();
            } else if(opcode == 18) {
                Category = str.readUnsignedWord();
            } else if(opcode >= 30 && opcode < 40)
            {
                if(actions == null)
                    actions = new String[10];
                try {
                    actions[opcode - 30] = str.readString();
                    if(actions[opcode - 30].equalsIgnoreCase("hidden"))
                        actions[opcode - 30] = null;
                } catch(Exception e) { }
            } else
            if(opcode == 40)
            {
                int k = str.readSignedByte();
                originalColors = new int[k];
                newColors = new int[k];
                for(int k1 = 0; k1 < k; k1++)
                {
                    originalColors[k1] = str.readUnsignedWord();
                    newColors[k1] = str.readUnsignedWord();
                }
            } else
            if(opcode == 41)
            {
                int k = str.readSignedByte();
                originalTextures = new short[k];
                newTextures = new short[k];
                for(int k1 = 0; k1 < k; k1++)
                {
                    originalTextures[k1] = (short) str.readUnsignedWord();
                    newTextures[k1] = (short) str.readUnsignedWord();
                }
            } else
            if(opcode == 60)
            {
                int l = str.readUnsignedByte();
                dialogueModels = new int[l];
                for (int l1 = 0; l1 < l; l1++)
                    dialogueModels[l1] = str.readUnsignedWord();
            } else
            if(opcode == 93) {
                onMinimap = false;
            } else
            if(opcode == 95)
                combatLevel =  str.readUnsignedWord();
            else
            if(opcode == 97)
                anInt91 = str.readUnsignedWord();
            else
            if(opcode == 98)
                anInt86 =str.readUnsignedWord();
            else
            if(opcode == 99) {
                aBoolean93 = true;
            } else
            if(opcode == 100)
                anInt85 = str.readSignedByte();
            else
            if(opcode == 101)
                anInt92 = str.readSignedByte();
            else
            if(opcode == 102)
                anInt75 = str.readUnsignedWord();
            else
            if(opcode == 103)
                getDegreesToTurn = str.readUnsignedWord();
            else
            if(opcode == 106 || opcode == 118) {
                int varbit = str.readUnsignedWord();

                if (varbit == 65535) {
                    varbit = -1;
                }

                int varp = str.readUnsignedWord();

                if (varp == 65535) {
                    varp = -1;
                }

                int value = -1;

                if (opcode == 118) {
                    value = str.readUnsignedWord();
                }
                int len = str.readUnsignedByte();
                morphisms = new int[len + 2];
                for (int i = 0; i <= len; i++) {
                    morphisms[i] = str.readUnsignedWord();
                    if (morphisms[i] == 65535) {
                        morphisms[i] = -1;
                    }
                }
                morphisms[len + 1] = value;
            } else if (opcode == 107){
               // aBoolean84 = false;
            } else if(opcode == 109) {
               // this.isClickable = false;
            } else if(opcode == 111) {
                //this.aBool2190 = true;
            } else if(opcode == 114) {
                 str.readUnsignedWord();
            } else if(opcode == 115) {
                 str.readUnsignedWord();
                 str.readUnsignedWord();
                 str.readUnsignedWord();
                 str.readUnsignedWord();
            } else if(opcode == 116) {
                 str.readUnsignedWord();
            } else if(opcode == 117) {
                 str.readUnsignedWord();
                 str.readUnsignedWord();
                 str.readUnsignedWord();
                 str.readUnsignedWord();
            }
        } while(true);
    }
    NPCCacheDefinition()
    {
        size = 1;
        type = -1L;
        Category = -1;
    }

    /**
     * Represents an array of {@link NPCCacheDefinition}s.
     */
    public static NPCCacheDefinition[] definitions = new NPCCacheDefinition[NPC_TOTAL];

    /**
     * Gets all definitions in the form of an array.
     * @return	definitions	the {@link NPCCacheDefinition} in array form
     */
    public static NPCCacheDefinition[] getDefinitions() {
        return definitions;
    }

    /**
     * Gets the NPC's stand index.
     * @return
     */
    public int getStandAnim() {
        return standAnim;
    }

    /**
     * Gets the NPC's walk index.
     * @return
     */
    public int getWalkAnim() {
        return walkAnim;
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    /**
     * Gets the size of the NPC.
     * @return	size	the size of the NPC
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the name of the NPC.
     * @return	name	the name of the NPC.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the examine string for the NPC.
     * @return	examine	the examine string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the health of the NPC
     * @return	health	the NPC's health
     */
    public int getHealth() {
        return health;
    }
    public boolean attackable() {
        if(actions[1].equalsIgnoreCase("attack")) {
            return true;
        }
        return false;
    }

    private static int cacheIndex;
    private static Stream npcData;
    public static int totalNpcs;
    public static int newTotalNpcs;
    public int[] models;
    private String name;
    public int[] originalColors;
    public int[] newColors;
    public short[] originalTextures, newTextures;
    public String actions[];
    public byte size;
    private static int streamIndices[];
    public long type;
    public static NPCCacheDefinition cache[];
    private int standAnim;
    private int walkAnim;
    private int health;
    public int morphisms[];
    public int combatLevel;
    private int Category;
    private String description;

}
