
rows = 0
cols = 0


def get_row(line):
    index = 0
    matrix_row = []
    while index < len(line) - 1:
        item = line[index:index + 4:]
        item = "*" if "[" not in item and "]" not in item else item.strip()[1]
        matrix_row.append(item)
        index += 4

    return matrix_row


matrix = []
is_stack = False
with open("test.txt") as file:
    for line in file:
        if len(line) > 1 and line[1] == "1":
            is_stack = True
            break

        if not is_stack:
            row = get_row(line)
            matrix.append(row)

print(matrix)
