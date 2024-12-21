f = open("main/input.txt", "r")
data = f.read()
data_as_array = data.split("\n")

left = []
right = []

for row in (data_as_array):
    left.append(row[:row.index(" ")])
    right.append(row[row.rfind(" "):])

left.sort()
right.sort()

sum = 0
for i in range(len(left)):
    sum += abs(int(left[i]) - int(right[i]))
    
print(sum)