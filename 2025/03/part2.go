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
		
		index := 0
		curr_digit := ""
		required_switches_count := 12
		curr_digit_index := 0
		remaining_characters_count := required_switches_count
		final_string_value := ""

		for len(final_string_value) < required_switches_count {
			for index < len(line_arr) && (index == 0 || curr_digit_index == -1 || ((curr_digit_index + remaining_characters_count - 1) >= (len(line)))) {
				curr_digit = line_arr[index]
				curr_digit_index = strings.Index(line, curr_digit)
				index += 1
			}

			old := line[:curr_digit_index + 1]
			new := strings.Repeat("*", len(old))
			line = strings.Replace(line, old, new, 1)

			index = 0
			final_string_value += curr_digit
			remaining_characters_count -= 1
		} 


		value, _ := strconv.Atoi(final_string_value)
		sum += value
	}
	
	fmt.Println("sum:", sum)
}

