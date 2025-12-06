package main

import(
	"fmt"
	"os"
	"bufio"
	"strings"
	"strconv"
	"math/big"
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
	operations := lines[len(lines) - 1]
	operations_arr := strings.Fields(operations)

	results := new(big.Int).SetInt64(0)
	i := 0 
	values := []big.Int{}
	operator_index := 0

	for i < len(lines[0]){
		value := ""

		j := 0

		for j < len(lines) - 1 {
			if string(lines[j][i]) != " " {
				value += string(lines[j][i])
			} 

			j += 1
		}

		i += 1

		if value == "" {
			results = add_by_position(operations_arr, operator_index, values, results) 
			values = nil

			operator_index += 1
			continue
		} else {
			value_as_int, _ := strconv.Atoi(value)
			values = append(values, *new(big.Int).SetInt64(int64(value_as_int)))
		}
	}

	fmt.Println("results", add_by_position(operations_arr, operator_index, values, results))
}

func add_by_position(operations_arr []string, operator_index int, values []big.Int, results *big.Int) *big.Int  {
	operator := string(operations_arr[operator_index])
	curr_result := new(big.Int).SetInt64(0)
	for _, el := range values {	
		
		if curr_result.Cmp(new(big.Int).SetInt64(0)) == 0 {
			curr_result.Set(&el)
		} else if operator == "*"{
			curr_result = new(big.Int).Mul(curr_result, &el) 
		} else if operator == "+"{
			curr_result = new(big.Int).Add(curr_result, &el) 
		}
	}

	return new(big.Int).Add(results, curr_result)
}	
