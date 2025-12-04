package main

import(
	"fmt"
	"os"
	"bufio"
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
	
	lines_as_string := strings.Join(lines, "")
	accessible_rolls := 0
	temp_accessible_rolls := 0
	width := len(lines[0])
        height := len(lines)	

	indicies := []int{}
	
	for true {
		for index, _ := range lines_as_string {
			adjacent_rolls_count := 0

			if string(lines_as_string[index]) != "@" {
				continue
			}
		
			if index < (height * width - width - 1) {
				temp_index := index + width
				if string(lines_as_string[temp_index]) == "@" {
					adjacent_rolls_count += 1
				}
			
				adjacent_rolls_count += go_left(width, lines_as_string, temp_index)
     		        	adjacent_rolls_count += go_right(width, lines_as_string, temp_index)
			}

			if index >= width {
				temp_index := index - width
				if string(lines_as_string[temp_index]) == "@" {
					adjacent_rolls_count += 1
				}

				adjacent_rolls_count += go_left(width, lines_as_string, temp_index)
                		adjacent_rolls_count += go_right(width, lines_as_string, temp_index)
			}

			adjacent_rolls_count += go_left(width, lines_as_string, index)
			adjacent_rolls_count += go_right(width, lines_as_string, index)

			if adjacent_rolls_count < 4 {
				accessible_rolls += 1
				temp_accessible_rolls += 1
				indicies = append(indicies, index)
			}
		}

		for _, i := range indicies {
			lines_as_string = lines_as_string[:i] + "." + lines_as_string[i + 1:]
		}

		indicies = nil
		if temp_accessible_rolls == 0{
			break
		}

		temp_accessible_rolls = 0
	}

	fmt.Println("accessible rolls of paper", accessible_rolls)
}

func go_left (width int, lines_as_string string, index int) int {
	if index > 0 && index % width != 0 {
		if string(lines_as_string[index - 1]) == "@" {
			return 1
		}
	}

	return 0
}

func go_right (width int, lines_as_string string, index int) int {
	if (index + 1) % width != 0 {
		if string(lines_as_string[index + 1]) == "@" {
			return 1
		}
	}

	return 0
}
