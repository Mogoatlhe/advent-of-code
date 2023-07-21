def get_longest_string_index(arr):
    longest_string = max(arr, key=len)
    return arr.index(longest_string)


def compare_items(elves, index):

    elf_1 = ""
    elf_2 = ""

    if index == 0:
        elf_1 = elves[1]
        elf_2 = elves[2]
    if index == 1:
        elf_1 = elves[0]
        elf_2 = elves[2]
    if index == 2:
        elf_1 = elves[0]
        elf_2 = elves[1]

    for item in elves[index]:
        if item in elf_1 and item in elf_2:
            return item


def get_item_score(item):
    UPPER_STARTING = 64
    LOWER_STARING = 96

    if item.isupper():
        return ord(item) - UPPER_STARTING + 26

    return ord(item) - LOWER_STARING


with open("input.txt") as file:
    sum = 0
    elves = []
    for line in file:
        rucksack = line.strip()
        elves.append(rucksack)

        if len(elves) == 3:
            index = get_longest_string_index(elves)
            matching_item = compare_items(elves, index)
            elves = []
            sum += get_item_score(matching_item)

print(sum)
