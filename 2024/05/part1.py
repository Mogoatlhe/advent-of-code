f = open("main/input.txt", "r")
data = f.read()

data_as_array = data.splitlines()
index_of_space = data_as_array.index("")

rules = data_as_array[:index_of_space]
page_numbers = data_as_array[-(len(data_as_array) - len(rules) - 1):]

sum = 0
for page_number in page_numbers:
    update = page_number.split(",")
    # print(update)
    is_valid = True
    for index, page in enumerate(update):
        if index == 0:
            continue

        if not is_valid:
            break
        # print(page)
        for x in range (index + 1):
            if f'{page}|{update[x]}' in rules:
                is_valid = False
                break


    if is_valid:
        half = int(round((len(update) - 1) / 2))
        # print(update, update[half], half, len(update))
        sum += int(update[half]) 

print(sum)