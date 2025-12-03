package main

import(
	"fmt"
	"os"
	"bufio"
	"strconv"
	"slices"
	"strings"
	"sort"
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
	sum := 0

	for _, line := range lines {
		line_arr := strings.Split(line, "")
		sort.Slice(line_arr, func(i, j int) bool {
			a, _ := strconv.Atoi(line_arr[i])
			b, _ := strconv.Atoi(line_arr[j])
			
			return a < b
		})

		slices.Reverse(line_arr)

		first_digit := line_arr[0]
		second_digit := line_arr[1]
		last_index_of_first_digit := strings.LastIndex(line, first_digit)
		last_index_of_second_digit := strings.LastIndex(line, second_digit)

		index := 2
		old_second_digit := second_digit
		old_last_index_of_second_digit := last_index_of_second_digit
		for index < len(line_arr) && last_index_of_first_digit > last_index_of_second_digit {
			second_digit = line_arr[index]
			last_index_of_second_digit = strings.LastIndex(line, second_digit)
			index += 1
		}

		if index > 2 && index != len(line_arr){
			last_index_of_first_digit = -1
		}
		
		if last_index_of_first_digit > old_last_index_of_second_digit {
			temp := first_digit
			first_digit = old_second_digit
			second_digit = temp	
		}

		value, _ := strconv.Atoi(first_digit + second_digit) 
		sum += value
		fmt.Println("line", line, value)	
	}
	
	fmt.Println("sum:", sum)
}

