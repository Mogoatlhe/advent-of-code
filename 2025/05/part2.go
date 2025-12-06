package main

import(
	"fmt"
	"os"
	"bufio"
	"strings"
	"strconv"
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

type Range struct {
	min int
	max int
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
	fresh_ranges := []Range{}

	for _, el := range ranges {
		el_as_arr := strings.Split(el, "-")
		min, _ := strconv.Atoi(el_as_arr[0])
		max, _ := strconv.Atoi(el_as_arr[1])

		fresh_ranges = append(fresh_ranges, Range{min, max})
	}

	sort.Slice(fresh_ranges, func (a int, b int) bool {
		return fresh_ranges[a].min < fresh_ranges[b].min
	})

	for x, _ := range fresh_ranges {
		for y, _ := range fresh_ranges{
			if fresh_ranges[x].min == fresh_ranges[y].min && fresh_ranges[x].max == fresh_ranges[y].max {
				continue
			}

			if fresh_ranges[x].min < fresh_ranges[y].min {
				if fresh_ranges[x].max >= fresh_ranges[y].min && fresh_ranges[x].max <= fresh_ranges[y].max {
					fresh_ranges[y].min = fresh_ranges[x].min
				} else if fresh_ranges[x].max > fresh_ranges[y].max {
					fresh_ranges[y].max = fresh_ranges[x].max
					fresh_ranges[y].min = fresh_ranges[x].min
				}
			} else if fresh_ranges[x].min >= fresh_ranges[y].min {
				if fresh_ranges[x].min < fresh_ranges[y].max && fresh_ranges[x].max <= fresh_ranges[y].max {
				} else if fresh_ranges[x].min <= fresh_ranges[y].max && fresh_ranges[x].max > fresh_ranges[y].max {
					fresh_ranges[y].max = fresh_ranges[x].max
				}
			}
		}
	}

	m := make(map[string]Range)

	for _, el := range fresh_ranges {
		key := "_" + strconv.Itoa(el.min) + strconv.Itoa(el.max)
		m[key] = el
	}

	sum := 0
	for _, el := range m {
		fresh_ranges = append(fresh_ranges, el)
		sum += el.max - el.min + 1
	}

	fmt.Println("fresh ingredients count", sum)
}

