package main

import(
	"fmt"
	"os"
	"bufio"
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
	curr_position := 50
	zero_count := 0

	for _, line := range lines {
		direction := string(line[0])
		steps, _ := strconv.Atoi(line[1:])

		if direction == "R" {
			curr_position += steps
			
			for curr_position > 99 {
				curr_position -= 100
				zero_count += 1
			}				
		} else if direction == "L" {
			curr_position -= steps

			if curr_position < 0 && (curr_position + steps) != 0 {
				zero_count += 1
			}


			for curr_position < 0 {
				curr_position += 100
				
				if curr_position < 0 {
					zero_count += 1
				}				
			}

			if curr_position == 0 {
				zero_count += 1
			}
		}

	}
	
	fmt.Println("zero count:", zero_count)
}

