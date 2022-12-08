
def detect_message_marker(line):
    unique_fourteen_arr = []

    count = 0
    for letter in line:

        if letter in unique_fourteen_arr and len(unique_fourteen_arr) != 14:
            letter_position = unique_fourteen_arr.index(letter)
            while letter_position >= 0:
                unique_fourteen_arr.pop(0)
                letter_position -= 1

        if len(unique_fourteen_arr) == 14:
            break

        count += 1
        unique_fourteen_arr.append(letter)

    print(unique_fourteen_arr)
    print(count)
    print()


with open("input.txt") as file:
    for line in file:
        detect_message_marker(line)
