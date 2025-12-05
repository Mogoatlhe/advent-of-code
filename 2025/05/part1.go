package main

import(
	"fmt"
	"os"
	"bufio"
	"strings"
	"strconv"
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
	space_index := 0

	for index, el := range lines {
		if len(el) == 0 {
			space_index = index
			break
		}
	}

	ranges := lines[:space_index]
	ids := lines[space_index + 1:]
	fresh_ingredients := []int{}

	for _, el := range ranges {
		el_as_arr := strings.Split(el, "-")
		min, _ := strconv.Atoi(el_as_arr[0])
		max, _ := strconv.Atoi(el_as_arr[1])

		for _, id := range ids {
			id_as_num, _ := strconv.Atoi(id)

			if (id_as_num >= min && id_as_num <= max && !slices.Contains(fresh_ingredients, id_as_num)){
				fresh_ingredients = append(fresh_ingredients, id_as_num)
			}
		}
	}

	fmt.Println("fresh ingredients count", len(fresh_ingredients))
}

