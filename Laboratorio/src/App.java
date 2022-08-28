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
        //OrderClients(read, "ClientesTemp");
        //FillBill(read, "FacturaTemp");
        OrderBills(read, "FacturaTemp");
        read.close();
    }

    public static void FillClient (Scanner read, String fileName) {
        String cedula, nombre, dir, celular, email;

        try {
            FileWriter outFile = new FileWriter(fileName + ".txt", false);
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

    public static void FillBill (Scanner read, String fileName) {
        String cedula, numFactura, codProductor, cantidad;
        

        try {
            FileWriter outFile = new FileWriter(fileName + ".txt", false);
            PrintWriter register_bill = new PrintWriter(outFile);

            String hayFactura;
            System.out.print("\n¿Desea ingresar una nueva factura? (sí - No)\n[+] ");
            hayFactura = read.nextLine();

            while (hayFactura.equalsIgnoreCase("si")) {
                String datos [] = new String[100];
                System.out.println(" ");
                BillMenu(read, datos);
                cedula = datos[0]; numFactura = datos[1]; codProductor = datos[2]; cantidad = datos[3];

                if (!cedula.isEmpty() && !numFactura.isEmpty()
                        && !codProductor.isEmpty() && !cantidad.isEmpty()) {
                    register_bill.println(cedula + "\t" + numFactura + "\t" + codProductor + "\t" + cantidad);
                }
                System.out.print("\n¿Desea ingresar una nueva factura? (sí - No)\n[+] ");
                hayFactura = read.nextLine();
            }

            register_bill.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void OrderBills (Scanner read, String fileName) {
        boolean hayFacturas = false;
        String W[][] = new String[100][100];
        String W_order[][] = new String[100][100];
        int t= 0, x=0;


        while(hayFacturas == false){
            try {
                File Facturas = new File("Facturas.txt");
                PrintWriter register_temp = new PrintWriter(new FileWriter(Facturas));

                BufferedReader br = new BufferedReader(new FileReader(fileName+".txt"));
                String line = null;
                String orderVec [] = new String[100];

                while((line = br.readLine()) != null){
                    String VecTemp [] = line.split("\t");
                    
                    for (int k=0 ; k < VecTemp.length ; k++) {
                        W[t][k] = VecTemp[k];
                    }

                    orderVec[t] = W[t][0];
                    t++;
                }

                OrdenarVector(orderVec, t);
                while (x < t) {
                    for (int k=0 ; k < t ; k++) {
                        if (Integer.parseInt(orderVec[x]) == Integer.parseInt(W[k][0])) {
                            for (int y=0 ; y < t ; y++) {
                                W_order[x][y] = W[k][y];
                            }
                        }
                    }
                    x++;
                }
                
                for (int k=0 ; k < t ; k++) {
                    for (int j=0 ; j<4 ; j++) {
                        System.out.print(W_order[k][j]+ " ");
                    }
                    System.out.println(" ");
                }
                
                register_temp.close();
                br.close();
                hayFacturas = true;

            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                hayFacturas = false;
                fileName = read.nextLine(); // Archivo
            }
        }
    }

    public static void OrderClients (Scanner read, String fileName) {
        boolean hayClientes = false;
        String V[][] = new String[100][100];
        String Temp[][] = new String[100][100];
        int t = 0, x = 0;

        while (hayClientes == false) {
            try {
                File Clientes = new File("Clientes.txt");
                PrintWriter register_temp = new PrintWriter(new FileWriter(Clientes));
                BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
                String line = null;
                String vec[] = new String[100];

                while ((line = br.readLine()) != null) {
                    String temp[] = line.split("\t");

                    for (int k=0 ; k < temp.length ; k++) {
                        V[t][k] = temp[k];
                    }

                    vec[t] = V[t][0];

                    t++;
                }

                OrdenarVector(vec, t);
                // Buscar en la matriz el primer elemento del vector ordenado
                while (x < t) {
                    for (int k=0 ; k < t ; k++) {
                        if (Integer.parseInt(vec[x]) == Integer.parseInt(V[k][0])) {
                            for (int y=0 ; y < t ; y++) {
                                Temp[x][y] = V[k][y];
                            }
                        }
                    }
                    x++;
                }
                // Creamos el archivo Clientes.txt ya ordenado
                for (int k=0 ; k < x ; k++) {
                    if (!Temp[k][0].isEmpty() && !Temp[k][1].isEmpty()
                            && !Temp[k][2].isEmpty() && !Temp[k][3].isEmpty()
                            && !Temp[k][4].isEmpty()) {
                        register_temp.println(Temp[k][0] + "\t" + Temp[k][1] + "\t" + Temp[k][2] + "\t" + Temp[k][3]+ "\t" + Temp[k][4]);
                    }
                }

                register_temp.close();
                br.close();
                hayClientes = true;

            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                hayClientes = false;
                fileName = read.nextLine(); // Archivo
            }
        }
    }

    public static void OrdenarVector (String V[], int t) {
        for (int k=0 ; k < t ; k++) {
            for (int j=0 ; j < (t - k - 1) ; j++) {
                if (Integer.parseInt(V[j+1]) < Integer.parseInt(V[j])) {
                    String temp = V[j];
                    V[j] = V[j + 1];
                    V[j+1] = temp;
                }
            }
        }
    }

    public static void BillMenu (Scanner read, String datos[]) {
        String titles[] = {"número de identificación", "número de factura", "código de productor", "cantidad de ventas"};

        for (int k=0 ; k < titles.length ; k++) {
            System.out.print("Ingrese " +titles[k]+ ": ");
            datos[k] = read.nextLine();
        }
    }

    public static void ClientMenu (Scanner read, String datos[]) {
        String titles[] = {"número de identificación", "nombre", "dirección", "número de contacto (celular)", "correo electronico"};

        for (int k=0 ; k < titles.length ; k++) {
            System.out.println("Ingrese " +titles[k]+ ": ");
            datos[k] = read.nextLine();
        }
    }
}