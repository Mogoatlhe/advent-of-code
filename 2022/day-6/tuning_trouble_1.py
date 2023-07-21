
def detect_packet_marker(line):
    unique_four_arr = []

    count = 0
    for letter in line:

        if letter in unique_four_arr:
            index = 0
            letter_position = unique_four_arr.index(letter)
            while index <= letter_position:
                unique_four_arr.pop(letter_position)
                letter_position -= 1

        if len(unique_four_arr) == 4:
            break

        count += 1
        unique_four_arr.append(letter)

    print(unique_four_arr)
    print(count)


with open("input.txt") as file:
    for line in file:
        detect_packet_marker(line)
