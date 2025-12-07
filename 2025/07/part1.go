package main

import(
	"fmt"
	"os"
	"bufio"
	"strings"
	"slices"
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
	
	s_index := strings.Index(lines_as_string, "S")

	// go down
	// if curr char == ^ split
	// increment count
	// go down x - 1 and x + 1
	// return count

	starting_pos_x := s_index % len(lines[0])
	starting_pos_y := (s_index - starting_pos_x) / len(lines[0])
	fmt.Println(get_down(starting_pos_y + 1, starting_pos_x, lines))
}

var visited = []string{}
func get_down(y int, x int, lines[] string) int{
	if y == len(lines) || x < 0 || x >= len(lines[0]) || slices.Contains(visited, fmt.Sprintf("%d%d", y,x)){
		return 0
	}
	
	count := 0
	line := lines[y]
	y += 1

	if line[x] == '^' {
		visited = append(visited, fmt.Sprintf("%d%d", (y - 1), x))
		count += 1
		count += get_down(y, x - 1, lines) + get_down(y, x + 1, lines)
		// lines[y - 1] = lines[y - 1][:x] + "." + lines[y - 1][x + 1:]
	} else if line[x] == '.'{
		count = get_down(y, x, lines)
	}

	return count
}

