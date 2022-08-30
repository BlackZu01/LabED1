#Laboratorio 1 Estructura de Datos (Archivos) punto 2
Luego de hacer una busqueda a lo largo de los diferentes lenguajes de programacion, se tiene como resultado que se emplean metodos similares por no decir iguales en cuanto a la lectura y escritura de un archivo. Realmente la unica diferencia notoria como era de esperarse es la sintaxis implementada.
> **🔑 Nota:** Cuando necesitamos que nuestro codigo realice una lectura de datos de un archivo, le proovemos a nuestro codgio una ubicacion en el archivo y posteriormente el computador trae esos datos a su RAM y los analiza desde alla. Del mismo modo, cuando tu código necesita escribir datos en un archivo, el ordenador coloca los nuevos datos en el búfer de escritura en memoria del sistema y los sincroniza con el archivo en el dispositivo de almacenamiento.

Uno de los pasos a seguir implemenando pseudo codigo pueden ser:
 * Subir un archivo en memoria 
 * Leer el contenido del archivo, o escribir datos a el mismo.
 * Cerrar el archivo

## **_Leer datos de un archivo_**
**Usando Python 🐍**

Python abstrae aún más el proceso. No tienes que crear conscientemente un flujo de datos; simplemente asignas una variable a los resultados de una función abierta y luego analizas el contenido de la variable. Es rápido, mínimo y fácil.
 ```
import time

start_time = time.time()
file = open("finaldata.csv" , "r")
read = file.readlines()
modified = []

for line in read:
   if line not in modified:
      modified.append(line.strip())

print(modified)
print("--- %s seconds ---" % (time.time() - start_time))
 ```
**Usando Go 🐹** 

Para leer desde archivos en tu sistema de archivos local, tendrás que usar el módulo io o ioutil. Primero tendrá que extraer el contenido de un archivo en la memoria llamando a ioutil.ReadFile("/ruta/archivo.txt") que tomará la ruta del archivo que desea leer como único parámetro. Esto devolverá los datos del archivo, o un error que puede ser manejado como normalmente se manejan los errores en go

```
package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
	"time"
)

func main() {
	start := time.Now()

	time.Sleep(2 * time.Second)

	file, ferr := os.Open("finaldata.csv")
	if ferr != nil {
		panic(ferr)
	}

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		line := scanner.Text()
		items := strings.Split(line, ",")

		fmt.Printf("Name: %s %s Email: %s\n", items[1], items[2], items[3])
		fmt.Println("------")
		log.Printf("main, execution time %s\n", time.Since(start))
	}
}
```
## **_Escribir datos en un archivo_**
En términos de código, la escritura es la inversa de la lectura. Como tal, el proceso algoritmico de escritura de datos en un archivo es básicamente el mismo que el de lectura de datos del mismo, solo que la diferencia es que utilizan funciones diferentes.

**Usando Python 🐍**
empleamos una metodologia similar con respecto a su lectura de archivos, en este proceos se usan funciones llamadas _open_ para cargar un archivo, _write_ para poner datos en él, y _close_ para cerrar el archivo.

```
myFile = open('example.txt', 'w')
myFile.write('hello world')
myFile.close()
```

**Usando Go 🐹**
A pesar de que el proceso no es totalmente analogo al de lectura, las librerias propias de Go nos permiten utilizar una amplia gama de recursos para poder tener varias funcionalidades al momento de escribir en nuestro archivo, sin embargo aca solo haremos mencion del proceso mas comun. 

* ✅ Para escribir en archivos en Go, utilizamos los paquetes os, ioutil y fmt y poseen una estructura similar a lo que ves a continuacion. Como agregado, las funciones que utilizamos suelen devolver el número de bytes escritos y un error en caso de que exista.
```
func (f *File) WriteString(s string) (n int, err error)
```
- 🔨 Ahora veamos como la función File.WriteString escribe el contenido de una cadena en un archivo.

```
package main

	import (
    "fmt"
    "log"
    "os"
	)

 func main() {

    	f, err := os.Create("data.txt")

    	if err != nil {
        log.Fatal(err)
     }

    	defer f.Close()

    	_, err2 := f.WriteString("Un string a tu gusto\n")

    	if err2 != nil {
         log.Fatal(err2)
    }

    	fmt.Println("done")
}
```

👉 En este ejemplo lo que estamos haciendo es escribir una cadena en un archivo de texto.
```
f, err := os.Create("data.txt")
```
🧐 El os.Create crea o "ancla" el archivo nombrado. Si el archivo ya existe, se sobreescribe.

```
_, err2 := f.WriteString("Un string a tu gusto\n")
```

😲 La cadena se escribe en el archivo creado con WriteString.
```
$ go run write_string.go
done
$ cat data.txt
Un string a tu gusto
```
##_**Modos de archivo**_
Muchos de los lenguajes de programacion que implementamos especifican un "modo" al abrir archivos. Los modos varían, pero esta es la notación común:

- w para escribir
- r para leer
- r+ para leer y escribir
- a para añadir sólo

> **🔑 Nota:** Sea cual sea la forma en que tu lenguaje de programación determine el modo de un archivo, depende de ti asegurarte de que estás añadiendo datos, a menos que pretendas sobrescribir un archivo con nuevos datos. Los lenguajes de programación no tienen avisos incorporados para advertirte de la pérdida de datos, como lo hacen los selectores de archivos.