f = open("main/input.txt", "r")
data = f.read()

data_as_array = data.splitlines()
index_of_space = data_as_array.index("")

rules = data_as_array[:index_of_space]
page_numbers = data_as_array[-(len(data_as_array) - len(rules) - 1):]

sum = 0
print(rules)
for page_number in page_numbers:
    update = page_number.split(",")
    is_valid = True
    for index, page in enumerate(update):
        if index == 0:
            continue

        for x in range (index + 1):
            if f'{page}|{update[x]}' in rules:
                # print(update)
                temp = update[x]
                update[x] = update[index]
                update[index] = temp
                print(index, x)
                is_valid = False
                x = 0


    if not is_valid:
        half = int(round((len(update) - 1) / 2))
        print(update)
        sum += int(update[half]) 

print(sum)