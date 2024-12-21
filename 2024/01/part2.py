f = open("main/input.txt", "r")
data = f.read()
data_as_array = data.split("\n")

left = []
right = []

for row in (data_as_array):
    left.append(row[:row.index(" ")])
    right.append(row[row.rfind(" ") + 1:])

sum = 0
for i in range(len(left)):
    sum += int(left[i]) * right.count(left[i])
    
print(sum)