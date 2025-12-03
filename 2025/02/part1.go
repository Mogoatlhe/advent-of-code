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

			if !(start_length % 2 == 0){
				new_start, _ := strconv.Atoi("1" + strings.Repeat("0", start_length))
				start = new_start
				continue
			}

			first_half := start_as_string[:(start_length / 2)]
			second_half := start_as_string[(start_length / 2):]
		
			if first_half == second_half {
				total_invalid += start
			}
			
			start += 1
		}

	}
	
	fmt.Println("total_invalid", total_invalid)
}

