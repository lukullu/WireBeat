package com.lukullu.undersquare.common;

import com.kilix.processing.ProcessingClass;
import  com.lukullu.undersquare.common.Constants.*;
import com.lukullu.undersquare.UnderSquare;
import com.lukullu.undersquare.common.data.Vector2;
import com.lukullu.undersquare.editor.LevelEditor;
import com.lukullu.undersquare.game.LevelMap;
import com.lukullu.undersquare.game.entity.enemy.Bouncer;
import com.lukullu.undersquare.game.entity.player.Player;
import com.lukullu.undersquare.game.geometry.LevelGeometry;
import com.lukullu.undersquare.game.geometry.MapElement;
import com.lukullu.undersquare.game.item.ItemBox;
import com.lukullu.undersquare.widgets.button.ButtonWidget;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import static com.lukullu.undersquare.common.Constants.*;
import static com.lukullu.undersquare.common.Constants.itemBoxDimensions;
import static com.lukullu.undersquare.common.msc.Translation.scaleToScreenX;
import static com.lukullu.undersquare.common.msc.Translation.scaleToScreenY;
import static com.lukullu.undersquare.game.item.Potion.LARGE_POTION;
import static com.lukullu.undersquare.game.item.Weapon.*;

public class IO implements ProcessingClass {

    public static boolean[][] convertMapDataToCollisionData(char[][] inputMap){

        boolean[][] output = new boolean[inputMap.length][inputMap[0].length];
        for(int i = 0; i < output.length; i++){
            for(int j = 0; j < output[0].length; j++){
                if(inputMap[i][j] == '#'){
                    output[i][j] = true;
                }
            }
        }
        return output;
    }

    //TODO: Read item and enemy types from pos and index in int array
    public static LevelGeometry[][] createMapElements(char[][] mapData, boolean[][] collisionData){

        LevelGeometry[][] output = new LevelGeometry[mapData.length][mapData[0].length];
        UnderSquare.getGameHandler().entities = new ArrayList<>();

        for(int i = 0; i < mapData.length; i++){
            for(int j = 0; j < mapData[0].length; j++){
                if(collisionData[i][j]){
                    output[i][j] = new LevelGeometry(new Vector2(j * mapGridSize, i * mapGridSize),new Vector2(mapGridSize,mapGridSize), Color.black, true);
                }else{
                    if(mapData[i][j] == 'p'){ UnderSquare.getGameHandler().entities.add(new Player(new Vector2(j * mapGridSize, i * mapGridSize), new Vector2(playerDimensions,playerDimensions))); }
                    if(mapData[i][j] == 'e'){ UnderSquare.getGameHandler().entities.add(new Bouncer(new Vector2(j * mapGridSize, i * mapGridSize), new Vector2(enemyDimensions,enemyDimensions))); }
                    if(mapData[i][j] == 'i'){ UnderSquare.getGameHandler().entities.add(new ItemBox(new Vector2(j * mapGridSize, i * mapGridSize), new Vector2(itemBoxDimensions,itemBoxDimensions), QUADSHOT)); }
                }
            }
        }
        return output;
    }

    public static Map<String, File> collectFiles() {

        File[] mapFiles = MAPS_BASE_DIR.listFiles((file) -> file.isFile() && file.getName().toLowerCase(Locale.ROOT).endsWith(".json"));

        Map<String,File> output = new HashMap<>();

        if(mapFiles == null){ return output; }

        for(File file : mapFiles){
            try {
                String mapName;
                mapName = GSON.fromJson(new FileReader(file), LevelMap.class).name;

                if(output.containsKey(mapName))
                    for(int i = 0; output.containsKey(mapName + "-" + i); i++) mapName = mapName + "-" + i;

                output.put(mapName,file);

            }catch(Exception e){}
        }

        return output;
    }

    public static void saveLevelMapAsJson(LevelMap levelmap, File file){

        try {
            String json = GSON.toJson(levelmap);
            if (file == null) {
                file = new File(MAPS_BASE_DIR, levelmap.name + ".json");
            }

            if (!file.isFile()) {
                if (file.exists()) throw new RuntimeException("is not file you fucknut!");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                file.createNewFile();
            }

            PrintWriter writer = new PrintWriter(file);
            writer.println(json);
            writer.flush();
            writer.close();

        }catch(Exception e){}

    }


}
