package main

import(
	"fmt"
	"os"
	"bufio"
	"strconv"
	"strings"
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

	line := lines[0]
	ranges := strings.Split(line, ",")
	total_invalid := 0

	for _, curr_range := range ranges {
		split_range := strings.Split(curr_range, "-")
		start, _ := strconv.Atoi(split_range[0])
		end, _ := strconv.Atoi(split_range[1])

		for start <= end {
			start_as_string := strconv.Itoa(start)
			start_length := len(start_as_string)

			char_count := 1

			char_count_loop:
			for char_count <= (start_length / 2) {
				if !(start_length % char_count == 0){
					char_count += 1
					continue
				}

				first_substr := start_as_string[0:char_count]
				lower_bound := char_count
				upper_bound := lower_bound + char_count

				for upper_bound <= start_length {
					curr_substr := start_as_string[lower_bound:upper_bound]

					if curr_substr != first_substr {
						break
						lower_bound = upper_bound
						upper_bound += char_count
					}

					if upper_bound == start_length {
						total_invalid += start
						break char_count_loop
					}
					
					lower_bound = upper_bound
					upper_bound += char_count
				}
				
				
				char_count += 1
			} 
			
			start += 1
		}

	}
	
	fmt.Println("total_invalid", total_invalid)
}
