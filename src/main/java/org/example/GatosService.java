package org.example;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GatosService {

    public static void verGatos() throws IOException {
        //1. Traemos los datos de la AP
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        //2. Acortar el primer corchete y el ultimo
        String responseJson = response.body().string();
        responseJson = responseJson.substring(1, responseJson.length());
        responseJson = responseJson.substring(0, responseJson.length()-1);

        //3. Crear un objeto de la clase gson
        Gson gson = new Gson();
        Gatos gatos = gson.fromJson(responseJson, Gatos.class);

        //4. Redimencionar imagen, en caso de ser necesario
        Image image = null;
        try {
            URL url = new URL(gatos.getUrl());
            image = ImageIO.read(url);

            ImageIcon fondoGato = new ImageIcon(image);

            if (fondoGato.getIconWidth()>800) {
                Image fondo = fondoGato.getImage();
                Image fotoModificada = fondo.getScaledInstance(800,600, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(fotoModificada);
            }

            String menu = "Opciones: \n"
                    +" 1. Ver otra imagen \n"
                    +" 2. Marcar como favorito \n"
                    + " 3. Volver \n";

            String [] botones = { "ver otra imagen", "favorito", "volver"};
            String idGato = gatos.getId();
            String opcion = (String) JOptionPane.showInputDialog(null, menu, idGato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

            int seleccion = -1;
            for (int i = 0; i < botones.length; i++){
                if(opcion.equals(botones[i])){
                    seleccion = i;
                }
            }

            switch (seleccion) {
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gatos);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void favoritoGato(Gatos gato) {
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"image_id\": \"" + gato.getId() + "\"}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gato.getApiKey())
                    .build();
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void verFavoritos(String apiKey) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .method("GET", null)
                .addHeader("x-api-key", apiKey)
                .build();
        Response response = client.newCall(request).execute();

        String resultJson = response.body().string();

        Gson gson = new Gson();

        GatosFav[] gatosArray = gson.fromJson(resultJson, GatosFav[].class);

        if(gatosArray.length > 0) {
            int min = 1;
            int max = gatosArray.length;
            int aleatorio = (int) (Math.random() * ((max - min) + 1)) + min;
            int indice = aleatorio - 1;

            GatosFav gatoFav = gatosArray[indice];

            Image image = null;
            try {
                URL url = new URL(gatoFav.image.getUrl());
                image = ImageIO.read(url);

                ImageIcon fondoGato = new ImageIcon(image);

                if (fondoGato.getIconWidth()>800) {
                    Image fondo = fondoGato.getImage();
                    Image fotoModificada = fondo.getScaledInstance(800,600, java.awt.Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(fotoModificada);
                }

                String menu = "Opciones: \n"
                        +" 1. Ver otra imagen \n"
                        +" 2. Eliminar favorito \n"
                        + " 3. Volver \n";

                String [] botones = { "ver otra imagen", "eliminar favorito", "volver"};
                String idGato = gatoFav.getId();
                String opcion = (String) JOptionPane.showInputDialog(null, menu, idGato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

                int seleccion = -1;
                for (int i = 0; i < botones.length; i++){
                    if(opcion.equals(botones[i])){
                        seleccion = i;
                    }
                }

                switch (seleccion) {
                    case 0:
                        verFavoritos(apiKey);
                        break;
                    case 1:
                        borrarFavorito(gatoFav);
                        break;
                    default:
                        verGatos();
                        break;
                }
            } catch (IOException e) {
                System.out.println(e);
            }

        }

    }

    private static void borrarFavorito(GatosFav gatoFav) {
        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/" + gatoFav.getId())
                    .method("DELETE", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gatoFav.getApiKey())
                    .build();
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
