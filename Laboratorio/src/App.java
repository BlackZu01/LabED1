import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner read = new Scanner(System.in);

        //FillClient(read, "Clientes");
        Leer(read, "Clientes");
        read.close();
    }

    public static void FillClient (Scanner read, String filename) {
        String cedula, nombre, dir, celular, email;

        try {
            FileWriter outFile = new FileWriter(filename+ ".txt", false);
            PrintWriter register = new PrintWriter(outFile);

            String hayRegistro;
            System.out.print("\n¿Desea agregar un nuevo cliente? (Sí - No)\n[+] ");
            hayRegistro = read.nextLine();

            while (hayRegistro.equalsIgnoreCase("si")) {
                System.out.print("Ingrese el número de identificacion: ");
                cedula = read.nextLine();
                System.out.print("Ingrese el nombre: ");
                nombre = read.nextLine();
                System.out.print("Ingrese la dirección: ");
                dir = read.nextLine();
                System.out.print("Ingrese el número de contacto (celular): ");
                celular = read.nextLine();
                System.out.print("Ingrese correo electronico: ");
                email = read.nextLine();

                if (!cedula.isEmpty() && !nombre.isEmpty() 
                    && !dir.isEmpty() && !celular.isEmpty() 
                    && !email.isEmpty()) {
                    register.println(cedula+ "\t" +nombre+ "\t" +dir+ "\t" +celular+ "\t" +email);
                }
                System.out.print("\n¿Desea agregar un nuevo cliente? (sí - No)\n[+]");
                hayRegistro = read.nextLine();
            }
            register.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FillBill (Scanner read, String filename) {
        try {
            FileWriter outFile = new FileWriter(filename+ ".txt", false);
            PrintWriter register = new PrintWriter(outFile);
            


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Leer(Scanner sc, String file_name){
        boolean hay = false;
        String V[][] = new String[100][100];
        String temp1[][] = new String[100][100];
        int t=0;
        int x=0;

        while(hay == false){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name+".txt"));
                String line = null;
                String vec[] = new String[100];

                while((line = br.readLine()) != null){
                    String temp[] = line.split("\t");
                    
                    V[t][0] = temp[0];
                    V[t][1] = temp[1];
                    V[t][2] = temp[2];
                    V[t][3] = temp[3];
                    V[t][4] = temp[4];
                    vec[t] = V[t][0];

                    t ++;
                }
                // for (int k=0 ; k<t ;k++) {
                //     for (int j=0 ; j<5 ; j++) {
                //         System.out.print(V[k][j]+ " ");
                //     }
                //     System.out.println(" ");
                // }
                Ordenar(vec, t);

                while (x <= t) {
                    for (int k=0 ; k<t ; k++) {
                        if (Integer.parseInt(vec[k]) == Integer.parseInt(V[k][0])) {
                            temp1[x][0] = V[k][0];
                            temp1[x][1] = V[k][1];
                            temp1[x][2] = V[k][2];
                            temp1[x][3] = V[k][3];
                            temp1[x][4] = V[k][4];
                        }
                        
                    }
                    x++;
                }
                // for (int k=0 ; k<t ;k++) {
                //     for (int j=0 ; j<5 ; j++) {
                //         System.out.print(temp1[k][j]+ " ");
                //     }
                //     System.out.println(" ");
                // }

                br.close();
                hay = true;

            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                hay = false;
                file_name = sc.nextLine(); // Archivo
            }
        }   
    }

    public static void Ordenar (String V[], int t) {

        for (int k=0 ; k<t ; k++) {
            for (int j=0 ; j<t-k-1 ; j++) {
                if (Integer.parseInt(V[j+1]) < Integer.parseInt(V[j])) {
                    String temp = V[j];
                    V[j] = V[j+1];
                    V[j+1] = temp;
                }
            }
        }
    }
}
