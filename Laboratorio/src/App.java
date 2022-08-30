import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.FieldPosition;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.text.html.FormSubmitEvent;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner read = new Scanner(System.in);


        /*
                               [+]  COMENTARIOS IMPORTANTES ANTES DE EJECUTAR !!  [+]

            Descomentar primero FillCLient() y OrderClient() y luego comentarlos de nuevo, posteriormente descomentar
            FillBill() y OrderBills() y luego nuevamente comentarlos y de manera ánaloga con FillProducts() y OrderProd().

            Finalmente descomentar el codigo desde String infoTot[][] hasta MajorProduct() para imprimir el resultado buscado en el ejercicio.
        */

        // FillClient(read, "ClientesTemp");
        // OrderClients(read, "ClientesTemp");
        // FillBill(read, "FacturaTemp");
        // OrderBills(read, "FacturaTemp");
        // FillProducts(read, "Productos");
        // OrderProd(read, "Productos");

        String infoTot[][] = new String[100][100];
        int filasTot = 0;
        ClientBills("Clientes", "Facturas", "ProductosOrd", infoTot);
        filasTot = NumFilas(infoTot);
        ClientData(infoTot ,"Clientes", filasTot);

        MajorProduct("Facturas", "ProductosOrd");

        read.close();
    }

    public static void ClientBills(String fileName1, String fileName2, String fileName3, String infoTot[][]) {
        try {
            File originalFile = new File(fileName1 + ".txt");
            BufferedReader register_cliente = new BufferedReader(new FileReader(originalFile));
            File originalFile2 = new File(fileName2 + ".txt");
            BufferedReader register_fact = new BufferedReader(new FileReader(originalFile2));
            File originalFile3 = new File(fileName3 + ".txt");
            BufferedReader register_prod = new BufferedReader(new FileReader(originalFile3));

            String line_cliente = null;
            String line_facturas = null;

            String W_prod[][] = new String[100][100];
            String deudas[][] = new String[100][100];
            String fact[][] = new String[100][100];
            int t = 0, x = 0, y = 0;

            line_cliente = register_cliente.readLine();
            line_facturas = register_fact.readLine();

            while (line_cliente != null || line_facturas != null) {
                String v_client[] = line_cliente.split("\t");
                String line = null;

                if (line_facturas != null) {
                    String w_fact[] = line_facturas.split("\t");

                    if (v_client[0].equalsIgnoreCase(w_fact[0])) {

                        while ((line = register_prod.readLine()) != null) {
                            String VecTemp[] = line.split("\t");

                            for (int k = 0; k < VecTemp.length; k++) {
                                W_prod[t][k] = VecTemp[k];
                            }
                            t++;
                        }

                        // Asignacion de la matriz de facturas
                        for (int k=0 ; k<4 ; k++) {
                            fact[x][k] = w_fact[k];
                        }
                        x++;
                        // fact[x][0] = w_fact[0];
                        // fact[x][1] = w_fact[1];
                        // fact[x][2] = w_fact[2];
                        // fact[x][3] = w_fact[3];
                        // x++;

                        line_facturas = register_fact.readLine();
                        // System.out.println(w_fact[0]+ " - " +w_fact[1]+ " - " +w_fact[2]+ " - "
                        // +w_fact[3]+ "");

                    } else if (Integer.parseInt(v_client[0]) > Integer.parseInt(w_fact[0])) {
                        System.out.println("Esto no es posible !!!");
                        line_facturas = register_fact.readLine();
                    } else if (Integer.parseInt(v_client[0]) < Integer.parseInt(w_fact[0])) {
                        // System.out.println("No encontre a nadie iwal " +v_client[0]+ " - "
                        // +w_fact[0]);
                        line_cliente = register_cliente.readLine();
                    }
                } else {
                    break;
                }
                for (int k = 0; k < x; k++) {
                    for (int j = 0; j < t; j++) {
                        if (fact[k][2].equals(W_prod[j][0])) {
                            deudas[k][0] = fact[k][0];
                            deudas[k][1] = fact[k][2];
                            deudas[k][2] = String
                                    .valueOf(Integer.parseInt(fact[k][3]) * Integer.parseInt(W_prod[j][2]));

                        }
                    }
                }
            }
            BufferedReader register_client = new BufferedReader(new FileReader(originalFile));
            BufferedReader register_fac = new BufferedReader(new FileReader(originalFile2));
            line_cliente = null;
            line_facturas = null;
            line_cliente = register_client.readLine();
            line_facturas = register_fac.readLine();
            int k = 0;

            while (line_cliente != null || line_facturas != null) {
                String v_client[] = line_cliente.split("\t");
                String line = null;

                if (line_facturas != null) {
                    String w_fact[] = line_facturas.split("\t");

                    if (v_client[0].equalsIgnoreCase(w_fact[0])) {
                        int suma = 0;
                        int e = 0;
                        while (deudas[k][0] != null & fact[k][0] != null) {

                            if (deudas[k][0].equals(fact[k][0])) {
                                suma += Integer.parseInt(deudas[k][2]);
                                // System.out.println("La deuda de " + deudas[k][0] + " es " + suma);
                                if (fact[k + 1][0] != null) {
                                    if (Integer.parseInt(deudas[k][0]) != Integer.parseInt(fact[k + 1][0])) {
                                        infoTot[e][0] = deudas[k][0];
                                        infoTot[e][1] = String.valueOf(suma);
                                        e++;
                                        suma = 0;
                                    }
                                }
                                if (fact[k + 1][0] == null) {
                                    infoTot[e][0] = deudas[k][0];
                                    infoTot[e][1] = String.valueOf(suma);
                                    e++;
                                }
                            }
                            k++;
                        }

                        line_facturas = register_fac.readLine();
                        // System.out.println(w_fact[0]+ " - " +w_fact[1]+ " - " +w_fact[2]+ " - "
                        // +w_fact[3]+ "");

                    } else if (Integer.parseInt(v_client[0]) > Integer.parseInt(w_fact[0])) {
                        System.out.println("Esto no es posible !!!");
                        line_facturas = register_fac.readLine();
                    } else if (Integer.parseInt(v_client[0]) < Integer.parseInt(w_fact[0])) {
                        // System.out.println("No encontre a nadie iwal " +v_client[0]+ " - "
                        // +w_fact[0]);
                        line_cliente = register_client.readLine();
                    }
                } else {
                    break;
                }

            }

            register_client.close();
            register_fac.close();

            register_prod.close();
            register_cliente.close();
            register_fact.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void ClientData (String infoTot[][], String fileName, int e) {
        String W[][] = new String[100][100];
        boolean hayCliente = false;

        while (!hayCliente) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
                String line = null;
                int t = 0;
                while ((line = br.readLine()) != null) {
                    String VecTemp[] = line.split("\t");

                    for (int k = 0; k < VecTemp.length; k++) {
                        W[t][k] = VecTemp[k];
                    }   
                    t++;
                }
                
                for (int i = 0 ; i < e ; i++) {
                    for (int j = 0 ; j < t ; j++) {

                     if (Integer.parseInt(infoTot[i][0])==Integer.parseInt(W[j][0])) {
                            System.out.println("\n || Informacion del cliente ||");
                            System.out.print("\n[+] Nombre cliente: " +W[j][1]+ "\n[+] Número Identificacion: " +W[j][0]+ 
                                             "\n[+] Celular: " +W[j][3]+ "\n[+] Email: " +W[j][4]+ "\n[+] Dirección: " +W[j][2]+ "\n[+] Deuda total: " +infoTot[i][1]+"\n"); 
                        }  
                    }
                  
                }

                br.close();
                hayCliente = true;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
    
    public static void MajorProduct (String fileName1, String fileName2) {
        String W_facts [][] = new String[100][100];
        String W_prod [][] = new String[100][100];
        int numeroOcurrencias [] = new int[100];

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName1+ ".txt"));
            BufferedReader br2 = new BufferedReader(new FileReader(fileName2+ ".txt"));
            String line = null;
            String line2 = null;
            int t = 0, p=0;

            //Matriz para Facturas.txt
            while ((line = br.readLine()) != null) {
                String VecTemp[] = line.split("\t");

                for (int k = 0 ; k < VecTemp.length ; k++) {
                    W_facts[t][k] = VecTemp[k];
                }   
                t++;
            }
            //Matriz para ProductosOrd.txt
            int x=0;
            while ((line2 = br2.readLine()) != null) {
                String VecTemp2[] = line2.split("\t");

                for (int k = 0 ; k < VecTemp2.length ; k++) {
                    W_prod[x][k] = VecTemp2[k];
                }   
                x++;
            }
            
            for (int k=0 ; k < t ; k++) {
                numeroOcurrencias[k] = Integer.parseInt(W_facts[k][2]);
            }

            String moda = String.valueOf(ModaVector(numeroOcurrencias, t));

            for (int k = 0 ; k < x ; k++) {
                if(Integer.parseInt(moda)==Integer.parseInt(W_prod[k][0])) {
                    System.out.println("\nEl producto más seleccionado por los clientes fue \n[+] " +W_prod[k][1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FillClient(Scanner read, String fileName) {
        String cedula, nombre, dir, celular, email;
        String datos[] = new String[100];

        try {
            FileWriter outFile = new FileWriter(fileName + ".txt", false);
            PrintWriter register = new PrintWriter(outFile);

            String hayRegistro;
            System.out.print("\n¿Desea agregar un nuevo cliente? (Sí - No)\n[+] ");
            hayRegistro = read.nextLine();

            while (hayRegistro.equalsIgnoreCase("si")) {
                ClientMenu(read, datos);
                cedula = datos[0];
                nombre = datos[1];
                dir = datos[2];
                celular = datos[3];
                email = datos[4];

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

    public static void FillBill(Scanner read, String fileName) {
        String cedula, numFactura, codProductor, cantidad;

        try {
            FileWriter outFile = new FileWriter(fileName + ".txt", false);
            PrintWriter register_bill = new PrintWriter(outFile);

            String hayFactura;
            System.out.print("\n¿Desea ingresar una nueva factura? (sí - No)\n[+] ");
            hayFactura = read.nextLine();

            while (hayFactura.equalsIgnoreCase("si")) {
                String datos[] = new String[100];
                System.out.println(" ");
                BillMenu(read, datos);
                cedula = datos[0];
                numFactura = datos[1];
                codProductor = datos[2];
                cantidad = datos[3];

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

    public static void FillProducts(Scanner read, String fileName) {
        String datos[] = new String[100];
        String codigo, producto, precio;

        try {
            FileWriter outfile = new FileWriter(fileName + ".txt");
            PrintWriter register_prod = new PrintWriter(outfile);

            String hayProducto;
            System.out.print("\n¿Desea agregar un nuevo producto? (Sí - No)\n[+] ");
            hayProducto = read.nextLine();

            while (hayProducto.equalsIgnoreCase("si")) {
                ProductMenu(read, datos);
                codigo = datos[0];
                producto = datos[1];
                precio = datos[2];

                System.out.print("\n¿Desea agregar un nuevo producto? (Sí - No)\n[+] ");
                hayProducto = read.nextLine();

                if (!codigo.isEmpty() && !producto.isEmpty() && !precio.isEmpty()) {
                    register_prod.println(codigo + "\t" + producto + "\t" + precio);
                }
            }

            register_prod.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void OrderProd(Scanner read, String fileName) {
        boolean hayProd = false;
        String W[][] = new String[100][100];
        String W_order[][] = new String[100][100];
        int t = 0, x = 0;

        while (hayProd == false) {
            try {
                File Productos = new File("ProductosOrd.txt");
                PrintWriter register_prod = new PrintWriter(new FileWriter(Productos));

                BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
                String line = null;
                String orderVec[] = new String[100];

                while ((line = br.readLine()) != null) {
                    String VecTemp[] = line.split("\t");

                    for (int k = 0; k < VecTemp.length; k++) {
                        W[t][k] = VecTemp[k];
                    }

                    orderVec[t] = W[t][0];
                    t++;
                }

                OrdenarVector(orderVec, t);
                while (x < t) {
                    for (int k = 0 ; k < t ; k++) {
                        if (orderVec[x] == null) {
                            break;
                        }
                        if (Integer.parseInt(orderVec[x]) == Integer.parseInt(W[k][0])) {
                            for (int y = 0 ; y < t ; y++) {
                                W_order[x][y] = W[k][y];
                            }
                            x++;
                        }
                    }
                }

                for (int k = 0 ; k < t ; k++) {
                    if (!W_order[k][0].isEmpty() && !W_order[k][1].isEmpty()
                            && !W_order[k][2].isEmpty()) {
                        register_prod.println(
                                W_order[k][0] + "\t" + W_order[k][1] + "\t" + W_order[k][2]);
                    }
                }

                register_prod.close();
                br.close();
                hayProd = true;

            } catch (IOException e) {
                System.out.println("No se encontro archivo");
                hayProd = false;
                fileName = read.nextLine();
            }

        }
    }

    public static void OrderBills(Scanner read, String fileName) {
        boolean hayFacturas = false;
        String W[][] = new String[100][100];
        String W_order[][] = new String[100][100];
        int t = 0, x = 0;

        while (hayFacturas == false) {
            try {
                File Facturas = new File("Facturas.txt");
                PrintWriter register_bill = new PrintWriter(new FileWriter(Facturas));

                BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
                String line = null;
                String orderVec[] = new String[100];

                while ((line = br.readLine()) != null) {
                    String VecTemp[] = line.split("\t");

                    for (int k = 0 ; k < VecTemp.length ; k++) {
                        W[t][k] = VecTemp[k];
                    }

                    orderVec[t] = W[t][0];
                    t++;
                }

                OrdenarVector(orderVec, t);
                while (x < t) {
                    for (int k = 0; k < t; k++) {
                        if (orderVec[x] == null) {
                            break;
                        }
                        if (Integer.parseInt(orderVec[x]) == Integer.parseInt(W[k][0])) {
                            for (int y = 0 ; y < t ; y++) {
                                W_order[x][y] = W[k][y];
                            }
                            x++;
                        }
                    }
                }

                for (int k = 0 ; k < t ; k++) {
                    if (!W_order[k][0].isEmpty() && !W_order[k][1].isEmpty()
                            && !W_order[k][2].isEmpty() && !W_order[k][3].isEmpty()) {
                        register_bill.println(
                                W_order[k][0] + "\t" + W_order[k][1] + "\t" + W_order[k][2] + "\t" + W_order[k][3]);
                    }
                }

                register_bill.close();
                br.close();
                hayFacturas = true;

            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                hayFacturas = false;
                fileName = read.nextLine(); 
            }
        }
    }

    public static void OrderClients(Scanner read, String fileName) {
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

                    for (int k = 0 ; k < temp.length ; k++) {
                        V[t][k] = temp[k];
                    }

                    vec[t] = V[t][0];

                    t++;
                }

                OrdenarVector(vec, t);
                // Buscar en la matriz el primer elemento del vector ordenado
                while (x < t) {
                    for (int k = 0 ; k < t ; k++) {
                        if (Integer.parseInt(vec[x]) == Integer.parseInt(V[k][0])) {
                            for (int y = 0 ; y < t ; y++) {
                                Temp[x][y] = V[k][y];
                            }
                        }
                    }
                    x++;
                }
                // Creamos el archivo Clientes.txt ya ordenado
                for (int k = 0 ; k < x ; k++) {
                    if (!Temp[k][0].isEmpty() && !Temp[k][1].isEmpty()
                            && !Temp[k][2].isEmpty() && !Temp[k][3].isEmpty()
                            && !Temp[k][4].isEmpty()) {
                        register_temp.println(Temp[k][0] + "\t" + Temp[k][1] + "\t" + Temp[k][2] + "\t" + Temp[k][3]
                                + "\t" + Temp[k][4]);
                    }
                }

                register_temp.close();
                br.close();
                hayClientes = true;

            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                hayClientes = false;
                fileName = read.nextLine(); 
            }
        }
    }

    public static void OrdenarVector(String V[], int t) {
        for (int k = 0 ; k < t ; k++) {
            for (int j = 0 ; j < (t - k - 1) ; j++) {
                if (Integer.parseInt(V[j + 1]) < Integer.parseInt(V[j])) {
                    String temp = V[j];
                    V[j] = V[j + 1];
                    V[j + 1] = temp;
                }
            }
        }
    }

    public static void BillMenu(Scanner read, String datos[]) {
        String titles[] = { "número de identificación", "número de factura", "código de productor",
                "cantidad de ventas" };

        for (int k = 0 ; k < titles.length ; k++) {
            System.out.print("Ingrese " + titles[k] + ": ");
            datos[k] = read.nextLine();
        }
    }

    public static void ClientMenu(Scanner read, String datos[]) {
        String titles[] = { "número de identificación", "nombre", "dirección", "número de contacto (celular)",
                "correo electronico" };

        for (int k = 0 ; k < titles.length ; k++) {
            System.out.println("Ingrese " + titles[k] + ": ");
            datos[k] = read.nextLine();
        }
    }

    public static void ProductMenu(Scanner read, String datos[]) {
        String titles[] = { "código", "descripción", "precio" };

        for (int k = 0 ; k < titles.length ; k++) {
            System.out.print("Ingrese " + titles[k] + ": ");
            datos[k] = read.nextLine();
        }
    }

    public static int NumFilas (String V[][]) {
        int x=0;

        for (int k=0 ; k<V.length ; k++) {
            if (V[k][0] == null) {
                break;
            }
            x++;
        }
        return x;
    }

    public static int ModaVector (int vec[], int size) {
        int moda = 0;
        int maxV = 0;
        
        for (int i = 0 ; i < size ; i++) {
            int vR = 0;
            for (int j = 0 ; j < size ; j++) {
                if (vec[i] == vec[j])
                    vR++;
            }
            if (vR > maxV) {
                moda = vec[i];
                maxV = vR;
            }
        }
        return moda;
    }
}