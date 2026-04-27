package main

import(
	"fmt"
	"os"
	"bufio"
	"strings"
	"strconv"
	"math"
	"slices"
)

func main(){
	f, err := os.Open("./test/input.txt")
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

type Smallest_Pairing struct{
	x string
	y string
	difference float64
}

func unpack(src []string, dst ...*int){
	for index, value := range dst {
		int_value, _ := strconv.Atoi(src[index])
		*value = int_value
	}
}

func process_lines(lines [] string){
	
	// compare all elements with each other
	// store the closest together
	// - a new element can still be added to an already existing group 
	// compare again until there are no more elements left to compare
	// ????
	circuits := []string{}

	for len(circuits) < len(lines) {
		smallest_pairing := Smallest_Pairing{"", "", 0.0}

		for i, _ := range lines {
			el_i := lines[i]
			el_i_arr := strings.Split(el_i, ",")
			var i_x, i_y, i_z int
			unpack(el_i_arr, &i_x, &i_y, &i_z)
		
			for j, _ := range lines {
				if i == j {
					continue
				}

				el_j := lines[j]
				el_j_arr := strings.Split(el_j, ",")
				var j_x, j_y, j_z int
				unpack(el_j_arr, &j_x, &j_y, &j_z)

				x := i_x - j_x
				y := i_y - j_y
				z := i_z - j_z

				sum := math.Pow(float64(x), 2) + math.Pow(float64(y), 2) + math.Pow(float64(z), 2)
				sqrt := math.Sqrt(sum)
				new_smallest_pairing := Smallest_Pairing{el_i, el_j, sqrt}

				if !(slices.Contains(circuits, new_smallest_pairing.x) && slices.Contains(circuits, new_smallest_pairing.y)) && (smallest_pairing.difference > sqrt || smallest_pairing.x == "") {
					smallest_pairing = new_smallest_pairing 
				}
			}
		}
	
		if (!slices.Contains(circuits, smallest_pairing.y) || !slices.Contains(circuits, smallest_pairing.x)){
			fmt.Println(smallest_pairing)
		}
	
		if !slices.Contains(circuits, smallest_pairing.x) {
			circuits = append(circuits, smallest_pairing.x)
		}

		if !slices.Contains(circuits, smallest_pairing.y) {
			circuits = append(circuits, smallest_pairing.y)
		}
	}

	fmt.Println("")
	fmt.Println(circuits)
}
