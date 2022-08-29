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
