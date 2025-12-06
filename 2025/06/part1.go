package main

import(
	"fmt"
	"os"
	"bufio"
	"strings"
	"strconv"
)

func main(){
	f, err := os.Open("./main/input.txt")
	scanner := bufio.NewScanner(f)

	if err != nil {
		panic(err)
	}

	lines := []string{}
	for scanner.Scan() {
		line := scanner.Text()
		lines = append(lines, line)
	}

	process_lines(lines)
}

func process_lines(lines [] string){
	operations := lines[len(lines) - 1]
	operations_arr := strings.Fields(operations)

	numbers := make([][]string, len(lines) - 1)

	index := 0
	for index < len(lines) - 1 {
		curr := lines[index]
		curr_arr := strings.Fields(curr)

		numbers[index] = curr_arr
		index += 1
	}

	results := 0
	i := 0 
	for i < len(operations_arr) {
		row_result := 0
		operation := operations_arr[i]
		j := 0
		
		for j < len(lines) - 1 {
			value_as_int, _ := strconv.Atoi(numbers[j][i])
			
			if row_result == 0 {
				row_result = value_as_int
			} else {
				if string(operation) == "*" {
					row_result *= value_as_int
				} else if string(operation) == "+"{
					row_result += value_as_int
				}
			}

			j += 1
		}

		results += row_result
		i += 1
	}

	fmt.Println(results)
}

