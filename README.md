# Laboratorio 1 Estructura de Datos (Archivos)
Luego de hacer una busqueda a lo largo de los diferentes lenguajes de programacion, se tiene como resultado que se emplean metodos similares por no decir iguales en cuanto a la lectura y escritura de un archivo. Realmente la unica diferencia notoria como era de esperarse es la sintaxis implementada.
> **🔑 Nota:** Cuando necesitamos que nuestro codigo realice una lectura de datos de un archivo, le proovemos a nuestro codgio una ubicacion en el archivo y posteriormente el computador trae esos datos a su RAM y los analiza desde alla. Del mismo modo, cuando tu código necesita escribir datos en un archivo, el ordenador coloca los nuevos datos en el búfer de escritura en memoria del sistema y los sincroniza con el archivo en el dispositivo de almacenamiento.

Uno de los pasos a seguir implemenando pseudo codigo pueden ser:
 * Subir un archivo en memoria 
 * Leer el contenido del archivo, o escribir datos a el mismo.
 * Cerrar el archivo

## Leer datos de un archivo
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



