import decimal

f = open("main/input.txt", "r")
data = f.read()

data_as_array = data.split("\n\n")       

def get_xy(value, symbol = "+"):
    x = value[value.index(f"{symbol}") + 1: value.index(",")]
    y = value[value.index(f"Y{symbol}") + 2: ]


    return [int(x), int(y)]

def is_whole(value):
    return decimal.Decimal(value).as_tuple().exponent >= 0

tokens = 0
for arr_item in data_as_array:
    [a, b, prize] = arr_item.split("\n")
    [ax, ay] = get_xy(a)
    [bx, by] = get_xy(b)
    [prize_x, prize_y] = get_xy(prize, "=")
    
    y = (ay * prize_x - prize_y * ax) / (by * ax - bx * ay)
    y = y if y > 0 else y * -1
    x = (prize_x - bx * y) / ax
    
    if not is_whole(x) or not is_whole(y):
        continue

    print(y)
    print(x)
    tokens += y + x * 3

print(tokens)