
largest = [0, 0, 0]
current = 0


def populate_largets(current=0):
    with open("./input.txt") as file:
        for line in file:
            if len(line) > 1:
                current += int(line)
            else:
                for index, value in enumerate(largest):
                    if current > value:
                        largest[index] = current
                        largest.sort()
                        break
                current = 0
    print(largest)


def top_three_elves_total():
    total = 0

    for value in largest:
        total += value

    print(total)


populate_largets(current)
top_three_elves_total()
