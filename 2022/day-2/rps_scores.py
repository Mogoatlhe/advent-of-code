
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
    my_hands = ["X", "Y", "Z"]
    opponent_hands = ["A", "B", "C"]

    my_curr_hand = my_hands.index(b[0]) + 1
    opponent_curr_hand = opponent_hands.index(a[0]) + 1
    difference = my_curr_hand - opponent_curr_hand

    if my_curr_hand == opponent_curr_hand:
        return DRAW + opponent_curr_hand
    elif (difference == -2 and my_curr_hand == 1) or difference == 1:
        return WIN + my_curr_hand
    else:
        return LOSE + my_curr_hand


calculate_score()
