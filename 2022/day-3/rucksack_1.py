def compare_items(compartment_1, compartment_2):
    for item in compartment_1:
        if item in compartment_2:
            return item


def get_item_score(item):
    UPPER_STARTING = 64
    LOWER_STARING = 96

    if item.isupper():
        return ord(item) - UPPER_STARTING + 26

    return ord(item) - LOWER_STARING


with open("input.txt") as file:
    sum = 0
    for line in file:
        rucksack = line.strip()
        length = len(rucksack)
        half = int(length / 2)
        compartment_1 = rucksack[0:half:1]
        compartment_2 = rucksack[half:length:1]

        matching_item = compare_items(compartment_1, compartment_2)
        sum += get_item_score(matching_item)

print(sum)
