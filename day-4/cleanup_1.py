def get_range_values(line):
    pair = []

    pair.append(line[:line.index("-"):1])
    pair.append(line[line.index("-") + 1:line.index(","):1])
    pair.append(line[line.index(",") + 1:line.rindex("-"):1])
    pair.append(line[line.rindex("-") + 1:len(line) - 1:1])

    return pair


with open("input.txt") as file:
    for line in file:
        print(get_range_values(line))
        break
