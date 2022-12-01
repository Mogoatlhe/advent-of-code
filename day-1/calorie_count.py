
largest = 0
current = 0

with open("./input.txt") as file:
    for line in file:
        if len(line) > 1:
            current += int(line)
        else:
            if current > largest:
                largest = current
            current = 0

print(largest)
