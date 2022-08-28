import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner read = new Scanner(System.in);

        // FillClient(read, "Clientes");
        Ordenar(read, "ClientesTemp");
        read.close();
    }

    public static void FillClient(Scanner read, String filename) {
        String cedula, nombre, dir, celular, email;

        try {
            FileWriter outFile = new FileWriter(filename + ".txt", false);
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
                    register.println(cedula + "\t" + nombre + "\t" + dir + "\t" + celular + "\t" + email);
                }
                System.out.print("\n¿Desea agregar un nuevo cliente? (sí - No)\n[+]");
                hayRegistro = read.nextLine();
            }
            register.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FillBill(Scanner read, String filename) {
        try {
            FileWriter outFile = new FileWriter(filename + ".txt", false);
            PrintWriter register = new PrintWriter(outFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Ordenar(Scanner sc, String file_name) {
        boolean hay = false;
        String V[][] = new String[100][100];
        String Temp[][] = new String[100][100];
        int t = 0, x = 0;

        while (hay == false) {
            try {
                File Clientes = new File("Clientes.txt");
                PrintWriter register_temp = new PrintWriter(new FileWriter(Clientes));
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
                String line = null;
                String vec[] = new String[100];

                while ((line = br.readLine()) != null) {
                    String temp[] = line.split("\t");

                    for (int k = 0; k < temp.length; k++) {
                        V[t][k] = temp[k];
                    }

                    vec[t] = V[t][0];

                    t++;
                }

                Ordenar(vec, t);
                // Buscar en la matriz el primer elemento del vector ordenado
                while (x < t) {
                    for (int k = 0; k < t; k++) {
                        if (Integer.parseInt(vec[x]) == Integer.parseInt(V[k][0])) {
                            for (int y = 0; y < t; y++) {
                                Temp[x][y] = V[k][y];
                            }
                        }
                    }
                    x++;
                }
                // Creamos el archivo Clientes.txt ya ordenado
                for (int k = 0; k < x; k++) {
                    if (!Temp[k][0].isEmpty() && !Temp[k][1].isEmpty()
                            && !Temp[k][2].isEmpty() && !Temp[k][3].isEmpty()
                            && !Temp[k][4].isEmpty()) {
                        register_temp.println(Temp[k][0] + "\t" + Temp[k][1] + "\t" + Temp[k][2] + "\t" + Temp[k][3]+ "\t" + Temp[k][4]);
                    }
                }

                register_temp.close();
                br.close();
                hay = true;

            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                hay = false;
                file_name = sc.nextLine(); // Archivo
            }
        }
    }

    public static void Ordenar(String V[], int t) {
        for (int k = 0; k < t; k++) {
            for (int j = 0; j < t - k - 1; j++) {
                if (Integer.parseInt(V[j + 1]) < Integer.parseInt(V[j])) {
                    String temp = V[j];
                    V[j] = V[j + 1];
                    V[j + 1] = temp;
                }
            }
        }
    }
}