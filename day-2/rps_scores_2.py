
def calculate_score():
    score = 0
    with open("input.txt") as file:
        for line in file:
            plays = line.split(" ")

            if len(plays) == 2:
                score += get_round_score(plays[0], plays[1])
    print(score)


def get_round_score(a, b):

    WIN = 6
    DRAW = 3
    LOSE = 0

    # rock paper scissors
    hands = ["A", "B", "C"]

    opponent_curr_hand = hands.index(a[0]) + 1
    if b[0] == "Y":
        return DRAW + opponent_curr_hand
    elif b[0] == "X":
        return LOSE + get_my_hand(hands, opponent_curr_hand, True)
    else:
        return WIN + get_my_hand(hands, opponent_curr_hand, False)


def get_my_hand(hands, opp_hand, negative):

    one = -1 if negative else 1
    two = 2 if negative else -2

    for index in enumerate(hands):
        position = index[0] + 1
        if position - opp_hand == one or position - opp_hand == two:
            return position


calculate_score()
