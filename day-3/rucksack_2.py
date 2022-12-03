def get_longest_string_index(arr):
    longest_string = max(arr, key=len)
    return arr.index(longest_string)


def compare_items(elves):
    for item in elves[0]:
        if item in elves[1] and item in elves[2]:
            return item


def get_item_score(item):
    UPPER_STARTING = 64
    LOWER_STARING = 96

    if item.isupper():
        return ord(item) - UPPER_STARTING + 26

    return ord(item) - LOWER_STARING


with open("test.txt") as file:
    sum = 0
    elves = []
    for line in file:
        rucksack = line.strip()
        elves.append(rucksack)

        if len(elves) == 3:
            index = get_longest_string_index(elves)
            # matching_item = compare_items(elves)
            elves = []
            # sum += get_item_score(matching_item)

# print(sum)
