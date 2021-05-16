package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scoreboard {
    class Score {
        String nombre;
        int puntuacion;

        public Score(String nombre, int puntuacion) {
            this.nombre = nombre;
            this.puntuacion = puntuacion;
        }
    }

    List<Score> scoreList = new ArrayList<>();
    List<Score> scoreOrdenado = new ArrayList<>();

    void render(SpriteBatch batch, BitmapFont font){
        font.draw(batch, "SCOREBOARD", 200, 460);

        for (int i = 0; i < scoreOrdenado.size(); i++) {
            font.draw(batch, scoreOrdenado.get(i).nombre, 100, 400-i*30);
            font.draw(batch, ""+scoreOrdenado.get(i).puntuacion, 300, 400-i*30);
        }
    }

    void guardarPuntuacion(int puntuacion){
        try {
            FileWriter fileWriter = new FileWriter("scores.txt", true);
            fileWriter.write("alan," + puntuacion + "\n");
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        leerPuntuaciones();
        ordenarPuntuaciones();
    }

    private void ordenarPuntuaciones() {
//          scoreList.sort(new Comparator<Score>() {
//            @Override
//            public int compare(Score o1, Score o2) {
//                if(o1.puntuacion > o2.puntuacion) return -1;
//                if (o1.puntuacion == o2.puntuacion) return 0;
//               return 1; }
//          }
//          );

        Score scoreGuard = scoreList.get(0);
        int aBorrar = 0;

        for (int j = 0; j < 5; j++) {
            for (int i = 1; i < scoreList.size(); i++) {
                if (scoreGuard.puntuacion > scoreList.get(i).puntuacion){
                    scoreGuard = scoreList.get(i);
                    aBorrar = i;
                }
            }
            scoreOrdenado.add(scoreGuard);
            scoreList.remove(scoreList.get(aBorrar));
        }
    }

    void leerPuntuaciones(){
        try {
            Scanner scanner = new Scanner(new File("scores.txt"));
            scanner.useDelimiter(",|\n");

            while(scanner.hasNext()){
                String nombre = scanner.next();
                int puntos = scanner.nextInt();

                scoreList.add(new Score(nombre, puntos));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}