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
	
	roll_of_paper := "@"
	lines_as_string := strings.Join(lines, "")
	accessible_rolls := 0
	width := len(lines[0])
        height := len(lines)	

	fmt.Println(width, height)

	for index, _ := range lines_as_string {
		adjacent_rolls_count := 0

		if string(lines_as_string[index]) != roll_of_paper {
			continue
		}
		
		if index < (height * width - width - 1) {
			temp_index := index + width
			if string(lines_as_string[temp_index]) == roll_of_paper {
				adjacent_rolls_count += 1
			}
			
			adjacent_rolls_count += go_left(width, lines_as_string, temp_index, roll_of_paper)
     		        adjacent_rolls_count += go_right(width, lines_as_string, temp_index, roll_of_paper)
		}

		if index >= width {
			temp_index := index - width
			if string(lines_as_string[temp_index]) == roll_of_paper {
				adjacent_rolls_count += 1
			}

			adjacent_rolls_count += go_left(width, lines_as_string, temp_index, roll_of_paper)
                	adjacent_rolls_count += go_right(width, lines_as_string, temp_index, roll_of_paper)
		}

		adjacent_rolls_count += go_left(width, lines_as_string, index, roll_of_paper)
		adjacent_rolls_count += go_right(width, lines_as_string, index, roll_of_paper)

		if adjacent_rolls_count < 4 {
			accessible_rolls += 1
		}

	       // fmt.Println(index, adjacent_rolls_count)
	}

	fmt.Println("accessible rolls of paper", accessible_rolls)
}

func go_left (width int, lines_as_string string, index int, roll_of_paper string) int {
	if index > 0 && index % width != 0 {
		if string(lines_as_string[index - 1]) == roll_of_paper {
			return 1
		}
	}

	return 0
}

func go_right (width int, lines_as_string string, index int, roll_of_paper string) int {
	if (index + 1) % width != 0 {
		if string(lines_as_string[index + 1]) == roll_of_paper {
			return 1
		}
	}

	return 0
}
