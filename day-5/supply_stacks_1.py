
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


def prepend_matrix(old_matrix, length):

    arr = []
    new_matrix = []
    for i in range(length):
        arr.append("*")

    new_matrix.append(arr)
    new_matrix.extend(old_matrix)

    return new_matrix


def get_first_row(matrix):

    first_row = []
    for i in range(len(matrix[0])):
        for j in range(len(matrix)):
            if matrix[j][i] != "*":
                first_row.append(matrix[j][i])
                break

    return first_row


def move(quantity, location, destination, matrix):
    i = 0
    j = 0
    k = 0
    while j < quantity:
        if matrix[k][destination] != "*":
            matrix = prepend_matrix(matrix, len(matrix[k]))
            k = 0

        while k + 1 < len(matrix) and matrix[k + 1][destination] == "*":
            k += 1

        while matrix[i][location] == "*":
            i += 1

        matrix[k][destination] = matrix[i][location]
        matrix[i][location] = "*"
        k -= 1
        i += 1
        j += 1

    return matrix


matrix = []
is_stack = False
mxm = 0
with open("input.txt") as file:
    for line in file:
        if len(line) > 1 and line[1] == "1":
            is_stack = True

        if not is_stack:
            row = get_row(line)
            matrix.append(row)
        elif "move" in line:
            line = line[line.index(" ") + 1::1]
            quantity = int(line[:line.index(" "):1])
            line = line[line.index("m") + 2::1]
            location = int(line[:line.index(" "):1]) - 1
            destination = int(line[line.rindex(" ") + 1:: 1]) - 1
            matrix = move(quantity, location, destination, matrix)
            mxm += 1
            # if mxm == 17:
            # print('\n'.join(['\t'.join([str(cell)
            #       for cell in row]) for row in matrix]))
            # break
print('\n'.join(['\t'.join([str(cell) for cell in row]) for row in matrix]))
print()
print(get_first_row(matrix))
