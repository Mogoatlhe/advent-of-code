def get_range_values(line):
    pair = []

    pair.append(line[:line.index("-"):1])
    pair.append(line[line.index("-") + 1:line.index(","):1])
    pair.append(line[line.index(",") + 1:line.rindex("-"):1])
    pair.append(line[line.rindex("-") + 1::1])

    return pair


def is_overlap(pair):
    if int(pair[0]) <= int(pair[2]) and int(pair[1]) >= int(pair[3]):
        return True
    if int(pair[2]) <= int(pair[0]) and int(pair[3]) >= int(pair[1]):
        return True
    return False


with open("input.txt") as file:
    contained_pairs_count = 0
    for line in file:
        pair = get_range_values(line)

        if is_overlap(pair):
            contained_pairs_count += 1

print(contained_pairs_count)
