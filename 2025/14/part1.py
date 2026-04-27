f = open("main/input.txt", "r")
data = f.read()

data_as_array = data.splitlines()       

WIDTH = 101
HEIGHT = 103
SECONDS = 100

# print(data_as_array)
def get_xy_values(values):
    x = values[values.index("=") + 1: values.index(",")]
    y = values[values.index(",") + 1:]

    return [int(x), int(y)]

def move(position, velocity):
    [initial_x, initial_y] = get_xy_values(position)
    [vx, vy] = get_xy_values(velocity)

    new_x_position: int = initial_x + vx
    new_y_position: int = initial_y + vy

    if new_x_position < 0:
        new_x_position = WIDTH + new_x_position
    elif new_x_position >= WIDTH:
        new_x_position = new_x_position - WIDTH
    
    if new_y_position < 0:
        new_y_position = HEIGHT + new_y_position
    elif new_y_position >= HEIGHT:
        new_y_position = new_y_position - HEIGHT
    
    return f"p={new_x_position},{new_y_position} "
        
for x in range(SECONDS):
    for arr_index, arr_item in enumerate(data_as_array):
        [p, v] = arr_item.split(" ")
        data_as_array[arr_index] = move(p, v) + v

half_width = int(WIDTH / 2)
half_height = int(HEIGHT / 2)

quarters = [0, 0, 0, 0]
for arr_item in data_as_array:
    [p, v] = arr_item.split(" ")
    if f"{half_width}," in p or f",{half_height}" in p:
            continue
    
    [px, py] = get_xy_values(p)

    if px < half_width:
        if py < half_height:
            quarters[0] += 1
        else:
            quarters[2] += 1
    else:
        if py < half_height:
            quarters[1] += 1
        else:
            quarters[3] += 1
    

    
print(quarters)

product = 1

for quarter in quarters:
    product *= quarter

print(product)