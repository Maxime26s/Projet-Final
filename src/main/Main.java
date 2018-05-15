package main;

import centre.*;
import planete.*;
import vaisseau.*;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Main {
    public static Scanner sc=new Scanner(System.in);
    public static Queue<Vaisseau> listeVaisseau=new LinkedList<>();
    public static List<Centre> listeCentre=new ArrayList<>();
    public static List<Planete> listePlanete=new ArrayList<>();
    public static int nbCentre=0;
    public static float[][] dechets=new float[5][2];
    public static float[][] planetes = new float[5][5];
    public static int[] vaisseaux = new int[3];
    public static void main(String[] args) {
        getData();
        int nbVaisseau=vaisseaux[0]+vaisseaux[1]+vaisseaux[2];
        creerCentre();
        creerPlanete();
        creerVaisseau();
        for(int i=0;i<nbVaisseau;i++){
            envoieVaisseau(listeVaisseau.poll());
        }
        System.out.println("SIMULATION TERMINÉE: VAISSEAUX TOUS ENVOYÉS");
        affichageFin();
    }
    public static void envoieVaisseau(Vaisseau vaisseau){
        int random=(int)(Math.random()*5);
        listePlanete.get(random).charge(vaisseau);
        vaisseau.setCentreActuel(vaisseau.getCentreActuel()+1);
        try {
            listeCentre.get(vaisseau.getCentreActuel()).decharger(vaisseau);
        } catch (Exception e){
            System.out.println("SIMULATION TERMINÉE: TOUTES LES FILES D'ATTENTE SONT PLEINES");
            affichageFin();
        }
    }
    public static void creerPlanete(){
        listePlanete.add(new Planete(planetes[0][0],planetes[0][1],planetes[0][2],planetes[0][3],planetes[0][4]));
        listePlanete.add(new Planete(planetes[1][0],planetes[1][1],planetes[1][2],planetes[1][3],planetes[1][4]));
        listePlanete.add(new Planete(planetes[2][0],planetes[2][1],planetes[2][2],planetes[2][3],planetes[2][4]));
        listePlanete.add(new Planete(planetes[3][0],planetes[3][1],planetes[3][2],planetes[3][3],planetes[3][4]));
        listePlanete.add(new Planete(planetes[4][0],planetes[4][1],planetes[4][2],planetes[4][3],planetes[4][4]));
    }
    public static void creerCentre(){
        for(int i=0;i<nbCentre;i++) {
            listeCentre.add(new Centre());
        }
    }
    public static void creerVaisseau(){
        for(int i=0;i<vaisseaux[0];i++) {
            listeVaisseau.add(new VaisseauLeger());
        }
        for(int i=0;i<vaisseaux[1];i++) {
            listeVaisseau.add(new VaisseauNormal());
        }
        for(int i=0;i<vaisseaux[2];i++) {
            listeVaisseau.add(new VaisseauLourd());
        }
    }
    public static void affichageFin(){
        for(int i=0;i<listeCentre.size();i++){
            System.out.println("Centre #"+(i+1));
            System.out.println("Nombre de vaisseaux en attente: "+listeCentre.get(i).getFileVaisseau().size());
            System.out.println("Pile de Gadolinium: "+listeCentre.get(i).getListePile().get("Gadolinium").getPile().size());
            System.out.println("Pile de Neptunium: "+listeCentre.get(i).getListePile().get("Neptunium").getPile().size());
            System.out.println("Pile de Plutonium: "+listeCentre.get(i).getListePile().get("Plutonium").getPile().size());
            System.out.println("Pile de Terbium: "+listeCentre.get(i).getListePile().get("Terbium").getPile().size());
            System.out.println("Pile de Thulium: "+listeCentre.get(i).getListePile().get("Thulium").getPile().size());
        }
        sendData();
        System.exit(0);
    }
    public static void getData(){
        try {
            Socket socket = new Socket("127.0.0.1", 8080);

            OutputStream fluxSortant = socket.getOutputStream();
            OutputStreamWriter sortie = new OutputStreamWriter(fluxSortant);
            sortie.write("getData\n");
            sortie.flush();

            InputStream fluxEntrant = socket.getInputStream();
            BufferedReader entree = new BufferedReader(new InputStreamReader(fluxEntrant));
            nbCentre = Integer.parseInt(entree.readLine());
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 2; j++)
                    dechets[i][j] = Float.parseFloat(entree.readLine());
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    planetes[i][j] = Float.parseFloat(entree.readLine());
            for (int i = 0; i < 3; i++)
                vaisseaux[i] = Integer.parseInt(entree.readLine());
        } catch(Exception e){
            e.printStackTrace();
            System.out.println(" ERREUR: Connexion impossible");
        }
    }
    public static void sendData(){
        try {
            Socket socket = new Socket("127.0.0.1", 8080);

            OutputStream fluxSortant = socket.getOutputStream();
            OutputStreamWriter sortie = new OutputStreamWriter(fluxSortant);
            sortie.write("sendData\n");
            sortie.write(listeCentre.size()+"\n");
            for(int i=0;i<listeCentre.size();i++) {
                sortie.write(listeCentre.get(i).getFileVaisseau().size()+"\n");
                sortie.write(listeCentre.get(i).getListePile().get("Gadolinium").getPile().size()+"\n");
                sortie.write(listeCentre.get(i).getListePile().get("Neptunium").getPile().size()+"\n");
                sortie.write(listeCentre.get(i).getListePile().get("Plutonium").getPile().size()+"\n");
                sortie.write(listeCentre.get(i).getListePile().get("Terbium").getPile().size()+"\n");
                sortie.write(listeCentre.get(i).getListePile().get("Thulium").getPile().size()+"\n");
            }
            sortie.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
