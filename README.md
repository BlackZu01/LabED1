# Laboratorio 1 Estructura de Datos (Archivos)
## Punto 2
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
**Usando Ruby 🏮**

Maneja metodologias similares a las implementadas por Python, solo tenemos que instanciar una variable a los resultados de la lectura por filas de nuestro archivo implementando el uso de una función abierta y luego analizar el contenido de nuestra variable. Destacando de esta manera en eficacia y sencillez.
```
require 'benchmark'

def print_measures
 time = Benchmark.measure {
    file = File.open('C:\Users\user\Documents\Lab1ED\LabED1\FilesPunto2\finaldata.csv')

    puts file.readlines
    
    file.close
 }
 puts time.real #or save it to logs
end
print_measures()
```


## **_Escribir datos en un archivo_**
En términos de código, la escritura es la inversa de la lectura. Como tal, el proceso algoritmico de escritura de datos en un archivo es básicamente el mismo que el de lectura de datos del mismo, solo que la diferencia es que utilizan funciones diferentes.

**Usando Python 🐍**

Empleamos una metodologia similar con respecto a su lectura de archivos, en este proceos se usan funciones llamadas _open_ para cargar un archivo, _write_ para poner datos en él, y _close_ para cerrar el archivo.

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
**Usando Ruby 🏮**

Si quieres escribir en un archivo usando Ruby

- Abre el archivo en modo de escritura (indicativo "w")
- Utiliza el método de escritura para añadir datos al archivo
- Si no has utilizado la versión en bloque, recuerda cerrar

*Ejemplo*

```
File.open("archivo.txt", "w") { |f| f.write "#{Time.now} - Oh! Un nuevo registro\n" }
```
> **✔ Importante:** Esto reescribirá el contenido del archivo anterior. 
Si desea añadir nuevo contenido al archivo, utiliza el indicativo "a" (append), en lugar del indicativo "w" (write).


## _**Modos de archivo**_
Muchos de los lenguajes de programacion que implementamos especifican un "modo" al abrir archivos. Los modos varían, pero esta es la notación común:

- w para escribir
- r para leer
- r+ para leer y escribir
- a para añadir sólo

> **🔑 Nota:** Sea cual sea la forma en que tu lenguaje de programación determine el modo de un archivo, depende de ti asegurarte de que estás añadiendo datos, a menos que pretendas sobrescribir un archivo con nuevos datos. Los lenguajes de programación no tienen avisos incorporados para advertirte de la pérdida de datos, como lo hacen los selectores de archivos.

## _**Comparativo entre lenguajes**_
En este apartado no ahondaremos mucho en las metricas para evaluar la eficiencia de estos algoritmos de lectura, ya que hay muchas variables a tener en cuenta y que pueden interferir en nuestros resultados. Por ello, para mantener la simpleza solo vamos a tener en cuenta 3 perspectivas: Tiempo, facilidad, lineas de codigo.

Los indicativos seran expuestos con un ✅. Donde 3 es la nota maxima y 1 es la nota minima

**Python 🐍** 
> *Facilidad:* En cuanto este apartado, Python puede ser el claro ganador ya que su gran reconocimiento y accesibilidad en linea nos permite poder obtener informacion de mucha utilidad referente a la lectura y escritura de archivos. Como adicional, su sintaxis sencilla facilita el entendimiento del script.

**Nota:** ✅ ✅ ✅

> *Lineas de codigo:* La alta versatilidad de Python nos permite leer este tipo de archivos haciendo uso de pocas lineas de codigo, como se obersvo anteriormente solo fue necesario la declaracion de las variables, el uso de funciones y un ciclo for para recorrer las lineas de nuestro archivo.

**Nota:** ✅ ✅ ✅

>*Tiempo de ejecucion:* 
```
--- 4.203799724578857 seconds ---
```

**Nota:** ✅ ✅ ✅ 

**Golang 🐹** 
> *Facilidad:* El codigo de Golang puede ser retador para una persona que se introduce en el lenguaje, ademas, el manejo de archivos es manejado en su mayoria por librerias de terceros que realizan constantes mejoras a las implementaciones incorporadas por el propio lenguaje. Por otra parte, el constante manejo de errores y su sintaxis hacen que la lectura de nuestro script no sea lo suficientemente autocontenida como se espera. 

**Nota:** ✅ 

> *Lineas de codigo:* Nuevamente, Golang deja mucho que desear en este apartado a comparacion de sus otros "contendientes". Su sintaxis hace que la lectura de los archivos se haga un poco mas complicada ya que segun mas grande sea el archivo, mas validaciones y manejo de errores se tendran que hacer.

**Nota:** ✅ 

>*Tiempo de ejecucion:* 
```
--- execution time 1m59.8970604s ---
```


**Nota:** ✅ 


**Ruby 🏮** 
> *Facilidad:* Similarmente a Python, Ruby maneja una sintaxis comprensible y que permite ser autocontenida para aquella persona que manipula nuestro codigo, adicionalmente las librerias incorporadas por el mismo lenguaje en la lectura de archivos son lo suficientememte buenas para permitirnos un manejo rapido y eficaz de nuestras tareas

**Nota:** ✅ ✅ ✅

> *Lineas de codigo:* La extension del script es demasiado breve, de hecho son 3 lineas pero lo que hace extender un poco mas nuestro codigo es la funcionalidad adicional de poder cronometrar los tiempos empleados por nuestra funcion en la lectura del archivo. Por eso como nota se tiene un solido 3

**Nota:** ✅ ✅ ✅

>*Tiempo de ejecucion:* 
```
--- 41.784799300003215 seconds ---
```


**Nota:** ✅ ✅ 

Finalmente, luego de este analisis concluimos segun nuestro criterio que los mejores lenguajes en cuanto a la lectura de archivos en esta comparativa son:
- **Python 🐍**
- **Ruby 🏮**
- **Golang 🐹**


## _**Comparativo entre algoritmos de archivos y los usados en clase**_

En nuestra opinion, creemos que no hay diferencias significativas en las implementaciones actuales de lectura de archivos en comparacion con las vistas en clase. Ya que el proceso de lectura, buffer y cierre de archivo es implicito en cada una de las implementaciones de los diferentes lenguajes. Sin embargo, con toda la revolucion del mundo de los datos, al hacerse mas grande el volumen de los mismos. Los propios lenguajes han tenido que optar por mejoras internas en la optimizacion de sus metodos y a su vez apoyar los recursos ofrecidos por terceros tales como lo son las librerias de modo que el proceso de lectura, escritura y compresion se pueda realizar de manera rapida e intuitiva por el usuario. 
Con esto, concluimos todo lo solicitado por el punto 2 (Parte teorica)
Esperamos sea de su agrado y hasta la proxima  
