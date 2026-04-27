f = open("main/input.txt", "r")
data = f.read()

data_as_array = data.splitlines()       


# 89010123 - 7
# 78121874 - 15
# 87430965 - 23
# 96549874 - 31
# 45678903 - 39
# 32019012 - 47
# 01329801 - 55
# 10456732 - 63

width = data.index("\n")
height = data.count("\n") + 1
no_newline_data = data.replace("\n", "")
scores = []
def follow_trail(index, current):
    
    if index >= len(no_newline_data):
        return
    
    if current == 9:
        scores.append(index)
        return
    
    y = int(index / height)
    x = index % width
    
    # go left
    if x > 0 and no_newline_data[index - 1].isdigit() and int(no_newline_data[index - 1]) == (current + 1):
        follow_trail(index - 1, current + 1)

    # go right
    if x < (width - 1) and no_newline_data[index + 1].isdigit() and int(no_newline_data[index + 1]) == (current + 1):
        follow_trail(index + 1, current + 1)

    # go up
    if y > 0 and no_newline_data[index - height].isdigit() and int(no_newline_data[index - height]) == (current + 1):
        follow_trail(index - height, current + 1)

    # go down
    if y < (height - 1) and no_newline_data[index + height].isdigit() and int(no_newline_data[index + height]) == (current + 1):
        follow_trail(index + height, current + 1)

# find the first 0
scores_sum = 0
while "0" in no_newline_data:
    zero_index = no_newline_data.index("0")
    
    # follow the trail
    follow_trail(zero_index, 0)
    # change it to a character that is not zero
    no_newline_data = no_newline_data[:zero_index] + "X" + no_newline_data[zero_index + 1:]
    scores_sum += len(scores)
    scores.clear()

print(scores_sum)