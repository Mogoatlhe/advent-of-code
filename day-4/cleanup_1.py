with open("input.txt") as file:
    for line in file:
        elf_1_start = line[:line.index("-"):1]
        elf_1_end = line[line.index("-") + 1:line.index(","):1]
        elf_2_start = line[line.index(",") + 1:line.rindex("-"):1]
        elf_2_end = line[line.rindex("-") + 1::1]
        print(line)
        print(elf_1_start)
        print(elf_1_end)
        print(elf_2_start)
        print(elf_2_end)
        break
