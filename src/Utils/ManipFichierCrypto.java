package Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import UtilsCrypto.FichierConfig;
import static UtilsCrypto.FichierConfig.getUserdir;
import Crypto.Cle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.IOUtils;

/**
 *
 * @author bobmastrolilli
 */
public class ManipFichierCrypto {

    public void CreateFileByte(String NomFile, byte[] myByteArray) throws FileNotFoundException, IOException 
    {
        String fichier;
        fichier = FichierConfig.getUserdir() + NomFile;
        System.out.println("CreateFileByte = " + fichier);
        FileOutputStream fos = new FileOutputStream(fichier);
        fos.write(myByteArray);
        fos.close();
        System.out.println("Le fichier " + fichier + " a été créé!");
    }

    public Cle ReadFileKey(String NomFileCle) throws FileNotFoundException, IOException, ClassNotFoundException 
    {
        String fichier;

        fichier = FichierConfig.getUserdir() + NomFileCle;
        System.out.println("chemin = " + FichierConfig.getUserdir() + NomFileCle);
        System.out.println("ReadFileKey : " + fichier);
        FileInputStream fin = new FileInputStream(fichier);
        ObjectInputStream ois = new ObjectInputStream(fin);
        Cle cle = (Cle) ois.readObject();
        return cle;

    }

    public Object ReadFile(String NomFileByte) throws FileNotFoundException, IOException, ClassNotFoundException {
        String fichier;

        fichier = FichierConfig.getUserdir() + NomFileByte;
        System.out.println("ReadFile : " + fichier);
        FileInputStream fin = new FileInputStream(fichier);
        ObjectInputStream ois = new ObjectInputStream(fin);
        Object Lesbytes = (Cle) ois.readObject();
        return Lesbytes;

    }

    public static String LireFichier(String NomFicher_TF) throws FileNotFoundException, IOException {
        String fichier;
        String chaine = "";
        fichier = FichierConfig.getUserdir() + NomFicher_TF;
        System.out.println("LireFichier : " + fichier);
        InputStream ips = new FileInputStream(fichier);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String ligne;
        while ((ligne = br.readLine()) != null) {
            System.out.println(ligne);
            chaine += ligne;
            chaine += "\n";
        }
        System.out.println("chaine = " + chaine);
        return chaine;
    }

    public static void EcrireFichier(String NomFicher_TF, String chaine) throws FileNotFoundException, IOException {
        BufferedWriter writer = null;
        String fichier = FichierConfig.getUserdir() + NomFicher_TF;
        writer = new BufferedWriter(new FileWriter(fichier));
        writer.write(chaine);

        if (writer != null) {
            writer.close();
        }

    }

    public static byte[] LireFichierByte(String NomFicher_TF) throws FileNotFoundException, IOException {
        String fichier;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        fichier = FichierConfig.getUserdir() + NomFicher_TF;
        System.out.println("LireFichierByte : " + fichier);
        byte[] buf = new byte[8];
        Path path = Paths.get(fichier);
        byte[] data = Files.readAllBytes(path);

        return buf;
    }

    static String ReadFichier(String nomFichier) {

        String chaine = "";
        String fichier = getUserdir() + nomFichier;

        //lecture du fichier texte	
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            if ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
                chaine += ligne;
            }
            br.close();
            return ligne;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    static void CreateCipherFile(String NomFileCipher, Object msgCipher) throws IOException {
        String fichier;

        fichier = getUserdir() + NomFileCipher;

        FileWriter fw = null;
        fw = new FileWriter(fichier);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter fichierSortie = new PrintWriter(bw);
        fichierSortie.println(msgCipher);
        fichierSortie.close();
        System.out.println("DANS CreateCipherFILE : Le fichier " + NomFileCipher + " a été créé!");
        fw.close();
    }

    public void CreateFileKey(String NomFileCle, Cle Cle) throws FileNotFoundException, IOException {
        String fichier;

        fichier = FichierConfig.getUserdir() + NomFileCle;
        FileOutputStream fout = new FileOutputStream(fichier);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(Cle);
        oos.close();
        System.out.println("Le fichier " + fichier + " a été créé!");
    }
}
